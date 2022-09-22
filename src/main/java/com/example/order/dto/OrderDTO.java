package com.example.order.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class OrderDTO {
    @PastOrPresent(message = "Order date should be past or present!")
    private LocalDate date;
    @NotNull(message = "Price can not be null!")
    private long price;
    @NotNull(message = "Quantity can not be null!")
    private long quantity;
    @NotNull(message = "Address can not be null!")
    private String address;
    @NotNull(message = "User Id can not be null!")
    private long user;
    @NotNull(message = "Book Id can not be null!")
    private long book;
    private String email;
    private boolean cancel;

    public boolean getCancel() {
        return cancel;
    }
}
