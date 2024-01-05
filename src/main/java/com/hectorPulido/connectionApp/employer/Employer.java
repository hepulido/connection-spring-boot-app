package com.hectorPulido.connectionApp.employer;

import com.hectorPulido.connectionApp.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "employers")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Employer{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "location")
    private String location;

    @Column(name = "requirements")
    private String requirements;

    @Column(name = "salary")
    private double salary;

    @Column(name = "description_of_job")
    private String descriptionOfJob;

    @Column(name = "length_of_job")
    private String lengthOfJob;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}