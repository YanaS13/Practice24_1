package org.example.repository;

import org.example.model.Client;
import org.example.service.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class ClientRepository {
    private BasketService basketService;

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ClientRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public long save(Client client) {
        final String insertSql = "INSERT INTO clients (name_clients, username, password, email, basket_id) VALUES (?,?,?,?,?);";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        BasketRepository basketRepository = new BasketRepository(jdbcTemplate);
        PreparedStatementCreator preparedStatementCreator = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, client.getName());
            preparedStatement.setString(2, client.getUsername());
            preparedStatement.setString(3, client.getPassword());
            preparedStatement.setString(4, client.getEmail());
            preparedStatement.setInt(5,(int) basketService.creatBasket());
            return preparedStatement;
        };

        jdbcTemplate.update(preparedStatementCreator, keyHolder);

        return (int) keyHolder.getKeys().get("id_clients");
    }

    public Optional<Client> findById(long clientId) {
        final String selectSql = "SELECT * FROM clients where id_clients = ?";

        PreparedStatementCreator preparedStatementCreator = connection -> {
            var prepareStatement = connection.prepareStatement(selectSql);
            prepareStatement.setLong(1, clientId);

            return prepareStatement;
        };

        RowMapper<Client> productRowMapper = getProductRowMapper();

        List<Client> clients = jdbcTemplate.query(preparedStatementCreator, productRowMapper);

        return clients.stream().findFirst();
    }

    private static RowMapper<Client> getProductRowMapper() {
        return (resultSet, rowNum) -> {
            int id = resultSet.getInt("id_clients");
            String name = resultSet.getString("name_clients");
            String login = resultSet.getString("username");
            String password = resultSet.getString("password");
            String emaill = resultSet.getString("email");
            Integer basket = resultSet.getInt("basket_id");
            return new Client(id, name, login, password, emaill, basket);
        };
    }


}
