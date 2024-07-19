package org.example.repository;

import org.example.model.Product;
import org.springframework.stereotype.Repository;

import java.awt.*;
import java.math.BigInteger;
import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

@Repository
public class DBProductRepository {
    public static final String JDBC = "jdbc:mysql://localhost:5432/postgres?currentSchema=yanas&user=postgres&password=root";

    public long creatProduct(Product product) {
        final String insertSql = "INSERT INTO products (name_products, price,quantity) VALUES (?,?,?);";

        try (Connection connection = DriverManager.getConnection(JDBC);
             PreparedStatement prepareStatement = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS)) {

            prepareStatement.setString(1, product.getName());
            prepareStatement.setDouble(2, product.getPrice());
            prepareStatement.setDouble(2, product.getQuantity());

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

    public boolean deleteById(int id) {
        final String selectSql = "DELETE FROM products where id_product = ?";

        try (Connection connection = DriverManager.getConnection(JDBC);
             PreparedStatement prepareStatement = connection.prepareStatement(selectSql)) {
            prepareStatement.setInt(1, id);

            var rows = prepareStatement.executeUpdate();

            return rows > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Product> findByName(String name) {
        final String selectSql = "SELECT * FROM products where name_product  = ?";

        try (Connection connection = DriverManager.getConnection(JDBC);
             PreparedStatement prepareStatement = connection.prepareStatement(selectSql)) {
            prepareStatement.setString(1, name);

            ResultSet resultSet = prepareStatement.executeQuery();
            List<Product> products = new ArrayList<Product>();
            while (resultSet.next()) {
                int productId = resultSet.getInt("id_product");
                String names = resultSet.getString("name_product");
                Integer price = resultSet.getInt("price_product");
                Integer quantity = resultSet.getInt("quantity");
                Product product = new Product(productId, names, price, quantity);
                products.add(product);
            }

            return products;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
