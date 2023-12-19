package com.abdirahman.kmbank.model.response;

public record UserResponseBody(long id, long accountNumber, String firstName, String lastName, int age, String email, String balance) {
}
