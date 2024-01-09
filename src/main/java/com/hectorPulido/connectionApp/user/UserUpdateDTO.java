package com.hectorPulido.connectionApp.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserUpdateDTO {

    @Size(min = 2, message = "First name must have at least 2 characters")
    private String firstName;

    @Size(min = 2, message = "Last name must have at least 2 characters")
    private String lastName;

    @Email(message = "Email should be valid")
    private String email;

    @Size(min = 8, message = "Password must have at least 8 characters")
    private String password;

    @Size(min = 10, message = "Phone number not correct")
    private String phoneNumber;

    private String typeOfUser;
    private String skills;
    private String status;
    private int yearsOfExperience;
    private double payRate;
    private String title;
    private String location;
    private String requirements;
    private double salary;
    private String descriptionOfJob;
    private String lengthOfJob;

}
