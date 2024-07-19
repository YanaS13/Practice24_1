package org.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "productBasket")
public class ProductBasket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn(name = "idProduct", nullable = false)
    private Product product;

    @JoinColumn(name = "idBasket", nullable = false)
    private Basket basket;

    @Column(name = "quantityProduct", nullable = false)
    private int quantity;

}
