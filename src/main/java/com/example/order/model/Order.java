package com.example.order.model;

import com.example.order.dto.OrderDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "bookstore_order")
@NoArgsConstructor
public class Order {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long orderId;
    private LocalDate date;
    private long price;
    private long quantity;
    private String address;

    private long user;


    private long book;
    private boolean cancel;

    public Order(OrderDTO orderDTO) {
        this.date=orderDTO.getDate();
        this.price=orderDTO.getPrice();
        this.quantity=orderDTO.getQuantity();
        this.address=orderDTO.getAddress();
        this.user=orderDTO.getUser();
        this.book=orderDTO.getBook();
        this.cancel=orderDTO.getCancel();
    }
}
