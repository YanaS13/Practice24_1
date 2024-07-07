package org.app;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class TransferByPhoneApp {
    private Integer transferAmount;
    private String phoneNumber;
}
