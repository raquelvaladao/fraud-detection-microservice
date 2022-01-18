package com.frdetection;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestCustomer {
    private String firstName;
    private String lastName;
    private String email;
}
