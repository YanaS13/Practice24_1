package org.app;

import org.example.Config;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    @org.junit.jupiter.api.Test
    public void test1() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(org.app.Config.class);
        App app = context.getBean(App.class);
        app.getTransferByPhoneApp().setPhoneNumber("+7 921 158 14 55");
        String wait = app.getTransferByPhoneApp().getPhoneNumber();
        String rider = "+7 921 158 14 55";
        assertEquals(rider, wait);

    }

    @org.junit.jupiter.api.Test
    public void test2() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(org.example.Config.class);
        App app = context.getBean(App.class);
        app.getDataBase().setTransferInformation("Перевод Дмитрию на сумму 2000");
        String wait = app.getDataBase().getTransferInformation();
        String rider = "Перевод Дмитрию на сумму 2000";
        assertEquals(rider, wait);
    }

    @org.junit.jupiter.api.Test
    public void test3() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(org.example.Config.class);
        App app = context.getBean(App.class);
        BankClientsApp bankClientsApp = new BankClientsApp();
        app.customerVerification(bankClientsApp);
        String wait = app.customerVerification(bankClientsApp);
        String rider = "Является клиентом банка";
        assertEquals(rider, wait);
    }

    @org.junit.jupiter.api.Test
    public void test4() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        App app = context.getBean(App.class);
        app.getTransferByPhoneApp().setTransferAmount(2000);
        Integer wait = app.getTransferByPhoneApp().getTransferAmount();
        Integer rider = 2000;
        assertEquals(rider, wait);
    }

}