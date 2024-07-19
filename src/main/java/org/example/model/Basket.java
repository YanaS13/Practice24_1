package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Basket {
    private Integer id;
    private LinkedList<String> listOfProducts;
    private String promoCod;

}
