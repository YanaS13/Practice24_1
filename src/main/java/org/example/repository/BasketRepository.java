package org.example.repository;

import org.example.model.Basket;
import org.example.model.Sold;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BasketRepository  {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BasketRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public long creatBasket() {
        final String insertSql = "INSERT INTO basket (promocode) VALUES (?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        PreparedStatementCreator preparedStatementCreator = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, "promo");
            return preparedStatement;
        };

        jdbcTemplate.update(preparedStatementCreator, keyHolder);

        return (int) keyHolder.getKeys().get("id_cart");
    }
    public int creatIn(int productId, int basketId) {
        final String insertSql = "INSERT INTO products_carts (id_cart, id_product, count) VALUES (?,?,?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        PreparedStatementCreator preparedStatementCreator = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, basketId);
            preparedStatement.setInt(2, productId);
            preparedStatement.setInt(3, 5);
            preparedStatement.executeUpdate();
            return preparedStatement;
        };

        jdbcTemplate.update(preparedStatementCreator, keyHolder);

        return (int) keyHolder.getKeys().get("id_products_carts");
    }

    public List<Sold> findAll(int binId) {
        final String selectSql = "SELECT * FROM products_bins where id_bin = ?";
        return jdbcTemplate.query(selectSql, (resultSet, rowNum) ->
        {
            return new Sold(resultSet.getInt("id_product"), resultSet.getInt("quantity"));
        }, binId).stream().collect(Collectors.toList());
    }

    public void purchase(int binId) {
        final String deleteSql = "DELETE FROM products_bins where id_bin = ?";
        jdbcTemplate.update(deleteSql, binId);
    }


}

