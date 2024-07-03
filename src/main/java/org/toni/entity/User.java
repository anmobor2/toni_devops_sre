package org.toni.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Entity
public class User {
    @Id
    private String username;
    private LocalDate dateOfBirth;

    public User() {
    }

}