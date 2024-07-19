package org.example.repository;

import org.example.model.Client;
import org.example.model.Product;
import org.springframework.stereotype.Repository;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

@Repository
public class DBClientRepository {
    public static final String JDBC = "jdbc:mysql://localhost:5432/postgres?currentSchema=yanas&user=postgres&password=root";


    public long save(Client client, int basketID) {
        final String insertSql = "INSERT INTO clients (name_clients, username,password,email,basket_id) VALUES (?,?,?,?,?);";

        try (var connection = DriverManager.getConnection(JDBC);
             var prepareStatement = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS)) {
            connection.setAutoCommit(false);

            prepareStatement.setString(1, client.getName());
            prepareStatement.setString(2, client.getUsername());
            prepareStatement.setString(3, client.getPassword());
            prepareStatement.setString(4, client.getEmail());
            prepareStatement.setInt(5, basketID);

            prepareStatement.executeUpdate();

            connection.commit();

            ResultSet rs = prepareStatement.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                throw new RuntimeException("Ошибка при получении идентификатора");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public Optional<Client> findById(Integer id) {
        var selectSql = "SELECT * FROM clients where id_clients = ?";

        try (var connection = DriverManager.getConnection(JDBC);
             var prepareStatement = connection.prepareStatement(selectSql)) {
            prepareStatement.setInt(1, id);

            var resultSet = prepareStatement.executeQuery();

            if (resultSet.next()) {
                int clientId = resultSet.getInt("id_clients");
                String name = resultSet.getString("name_clients");
                String login = resultSet.getString("username");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                Integer basketID = resultSet.getInt("basket_id");
                Client client = new Client(clientId, name, login, password, email, basketID);

                return Optional.of(client);
            }

            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
