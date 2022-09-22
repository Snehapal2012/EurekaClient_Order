package com.example.order.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class User {
    private long userID;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private LocalDate dob;
    private String password;
}
