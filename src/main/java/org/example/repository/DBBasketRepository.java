package org.example.repository;

import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class DBBasketRepository {
    public static final String JDBC = "jdbc:mysql://localhost:5432/postgres?currentSchema=yanas&user=postgres&password=root";

    public int creatBasket() {
        final String insertSql = "INSERT INTO basket (promocode) VALUES (?);";

        try (Connection connection = DriverManager.getConnection(JDBC);
             PreparedStatement prepareStatement = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS)) {
            connection.setAutoCommit(false);
            prepareStatement.setString(1, "promo ");
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

    public int creatIn(int productId, int basketId) {
        final String insertSql = "INSERT INTO products_carts (id_cart, id_product, count) VALUES (?,?,?);";

        try (Connection connection = DriverManager.getConnection(JDBC);
             PreparedStatement prepareStatement = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS)) {
            prepareStatement.setInt(1, productId);
            prepareStatement.setInt(2, basketId);
            prepareStatement.setInt(3, 1);
            prepareStatement.executeUpdate();
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

    public boolean deleteBasket(int productId, int basketId) {
        final String selectSql = "DELETE FROM products where (id_cart = ?, id_product = ?)";
        try (Connection connection = DriverManager.getConnection(JDBC);
             PreparedStatement prepareStatement = connection.prepareStatement(selectSql)) {
            prepareStatement.setInt(1, productId);
            prepareStatement.setInt(2, basketId);

            int rows = prepareStatement.executeUpdate();

            return rows > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


}
