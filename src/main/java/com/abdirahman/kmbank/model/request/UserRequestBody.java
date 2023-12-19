package com.abdirahman.kmbank.model.request;

import java.util.Date;

public record UserRequestBody(String accountNumber, String firstName, String lastName, Date dob, String email, String password) {
}
