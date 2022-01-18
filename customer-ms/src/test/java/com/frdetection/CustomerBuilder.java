package com.frdetection;

import lombok.Builder;

@Builder
public class CustomerBuilder {

    @Builder.Default
    private String firstName = "Joal";

    @Builder.Default
    private  String lastName = "Strauss";

    @Builder.Default
    private  String email = "joal.strauss@gmail.com";

    public RequestCustomer defaultRequest(){
        return new RequestCustomer(firstName, lastName, email);
    }

    public Customer toEntity(RequestCustomer customer){
        return new Customer(1, customer.getFirstName(), customer.getLastName(), customer.getEmail());
    }

}
