package org.app;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class BankClientsApp {
    private String customerInformation;
    private Boolean isAClient = Boolean.TRUE;
}
