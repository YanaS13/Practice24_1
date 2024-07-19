package org.example.repository;

import org.example.model.Product;
import org.example.model.Sold;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.RowMapper;


import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;


@Repository
public class ProductRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public long save(Product product) {
        final String insertSql = "INSERT INTO products (name_product, price_product, quantity) VALUES (?,?,?);";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        PreparedStatementCreator preparedStatementCreator = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setInt(2, product.getPrice());
            preparedStatement.setInt(3, product.getQuantity());

            return preparedStatement;
        };

        jdbcTemplate.update(preparedStatementCreator, keyHolder);

        return (long) keyHolder.getKeys().get("id_product");
    }
    public boolean deleteById(long productId, long basketId) {
        final String deleteSql = "DELETE FROM products where (id_cart = ?, id_product = ?)";

        PreparedStatementCreator preparedStatementCreator = connection -> {
            var prepareStatement = connection.prepareStatement(deleteSql);
            prepareStatement.setInt(1, (int) productId);
            prepareStatement.setInt(2, (int) basketId);

            int rows = prepareStatement.executeUpdate();
            return prepareStatement;
        };

        int rows = jdbcTemplate.update(preparedStatementCreator);

        return rows > 0;
    }
    public Optional<Product> findById(long productId) {
        final String selectSql = "SELECT * FROM products where id_product = ?";

        PreparedStatementCreator preparedStatementCreator = connection -> {
            var prepareStatement = connection.prepareStatement(selectSql);
            prepareStatement.setLong(1, productId);

            return prepareStatement;
        };

        RowMapper<Product> productRowMapper = getProductRowMapper();

        List<Product> products = jdbcTemplate.query(preparedStatementCreator, productRowMapper);

        return products.stream().findFirst();
    }

    private static RowMapper<Product> getProductRowMapper() {
        return (resultSet, rowNum) -> {
            int id = resultSet.getInt("id_product");
            String name = resultSet.getString("name_product");
            int price = resultSet.getInt("price_product");
            int quantity = resultSet.getInt("quantity");
            return new Product(id, name, price, quantity);
        };
    }

    public boolean deleteById(long id) {
        final String deleteSql = "DELETE FROM products where id_product = ?";

        PreparedStatementCreator preparedStatementCreator = connection -> {
            var prepareStatement = connection.prepareStatement(deleteSql);
            prepareStatement.setLong(1, id);

            return prepareStatement;
        };

        int rows = jdbcTemplate.update(preparedStatementCreator);

        return rows > 0;
    }

    public boolean update(Product product) {
        var updateSql = """
                UPDATE products
                SET 
                name_product = ?,
                price_product = ?
                quantity = ?
                where id_product = ?;
                """;

        PreparedStatementCreator preparedStatementCreator = connection -> {
            var prepareStatement = connection.prepareStatement(updateSql);
            prepareStatement.setString(1, product.getName());
            prepareStatement.setInt(2, product.getPrice());
            prepareStatement.setInt(3, product.getQuantity());

            return prepareStatement;
        };

        int rows = jdbcTemplate.update(preparedStatementCreator);

        return rows > 0;
    }

    public void sell(Sold sold) {
        String putSql = "UPDATE products SET quantity = quantity - ? WHERE id_product = ?";
        jdbcTemplate.update(putSql, sold.getQuantity(), sold.getId());
    }

}