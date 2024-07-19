package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {
    private Integer id;
    private String name;
    private String username;
    private String password;
    private String email;
    private Integer basketID;

}
