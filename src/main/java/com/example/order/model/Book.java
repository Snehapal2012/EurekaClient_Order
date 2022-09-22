package com.example.order.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Book {
    private long bookId;
    private String bookName;
    private String authorName;
    private String bookDescription;
    private String bookImg;
    private long price;
    private long quantity;
}
