package ru.aldamm.models;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class Person {
    private int id;
    @NotEmpty(message = "Please enter valid first name")
    @Size(min = 2, max = 30, message = "First name should be between 2 to 30 symbols")
    private String firstName;
    @NotEmpty(message = "Please enter valid second name")
    @Size(min = 2, max = 30, message = "Second should be between 2 to 30 symbols")
    private String secondName;
    @NotNull
    @Min(value = 1, message = "Age should be greater than 0")
    private int age;
    @NotEmpty(message = "Please enter email")
    @Email(message = "This is not email")
    private String email;

    public Person() {
    }

    public Person(int id, String firstName, String secondName, int age, String email) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.age = age;
        this.email = email;
    }
}
