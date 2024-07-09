package org.app;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Data
public class App {
    @Autowired
    private BankClientsApp bankClientsApp;
    @Autowired
    private TransferByPhoneApp transferByPhoneApp;
    @Autowired
    private DataBase dataBase;

    public String customerVerification(BankClientsApp bankClientsApp) {
        if (!bankClientsApp.getIsAClient()) {
            return "Ошибка! Это не клиент банка!";
        }
        return "Является клиентом банка";
    }
}