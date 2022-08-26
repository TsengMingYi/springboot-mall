package com.kitezeng.springbootmall.model;

import javax.validation.constraints.NotBlank;

public class Email {

    @NotBlank
    @javax.validation.constraints.Email
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
