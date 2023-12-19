package com.abdirahman.kmbank.util;

import com.abdirahman.kmbank.model.entity.User;
import com.abdirahman.kmbank.model.request.UserRequestBody;
import com.abdirahman.kmbank.model.response.UserResponseBody;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.Year;
import java.time.ZoneId;
import java.util.Calendar;

public class EntityMapper {
    public static User mapUserRequestBodyToUser(UserRequestBody user) {
        return new User(user.accountNumber(), user.firstName(), user.lastName(), user.dob(), user.email(), user.password());
    }

    public static UserResponseBody mapUserToUserResponseBody(User user) {
        String balance = NumberFormat.getCurrencyInstance().format(user.getBalance());

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(user.getDob());
        int age = Year.now().getValue() - calendar.get(Calendar.YEAR);

        long accountNumber = Long.parseLong(user.getAccountNumber());

        return new UserResponseBody(user.getId(), accountNumber, user.getFirstName(), user.getLastName(), age, user.getEmail(), balance);
    }
}
