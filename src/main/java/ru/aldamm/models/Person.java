package ru.aldamm.models;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


/*
я бы использовал lombok, даёт возможность не писать кучу бойлер кода(геттеры, сеттеры и т.д.)
fixed
 */

@Data
public class Person {
    private int id;
    @NotEmpty(message = "Please enter valid name")
    @Size(min = 2, max = 30, message =  "Name should be between 2 to 30 symbols")
    private String name;
    @Min(value = 0, message = "Age should be greater than 0")
    private int age;
    @NotEmpty(message = "Please enter email")
    @Email(message = "This is not email")
    private String email;

    public Person() {
    }

    public Person(int id, String name, int age, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }
}
