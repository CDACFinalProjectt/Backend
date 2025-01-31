package com.kdac.globeconnect.entities;

import com.kdac.globeconnect.enums.Genders;
import com.kdac.globeconnect.enums.UserAccountStatus;
import com.kdac.globeconnect.enums.Role;  // Importing the Role Enum

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "user_id")
    private Integer id;

    @Column(length = 50, nullable = false)
    private String firstName;

    @Column(length = 50, nullable = false)
    private String lastName;

    @Column(length = 12, nullable = false, unique = true)
    private String username;

    @Column(length = 12)
    @Size(min = 5, max = 12, message = "Password must be between 5 and 12 characters")
    private String password;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Column(unique = true)
    private String email;

    @Column
    @Pattern(regexp = "^\\+?[1-9][0-9]{7,14}$", message = "Invalid phone number format")
    private String mobile;

    @Column(name = "birth")
    @Past(message = "Birth date must be in the past")
    private LocalDate dob;

    @Enumerated(EnumType.STRING)
    private Genders gender;

    @Enumerated(EnumType.STRING)
    private UserAccountStatus accountStatus;

    @Column(length = 255)
    private String profileImage;

    @Column(length = 250)
    private String bio;

    @Column(nullable = false, updatable = false)
    private LocalDateTime usercreatedAt;

    @Column(nullable = false, updatable = false)
    private LocalDateTime userupdatedAt;

    // Adding the role field
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role = Role.USER;  // Default role is USER

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "user")
    private List<Post> posts = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "user")
    private List<Comment> comments = new ArrayList<>(); // Added relationship with comments

    @PrePersist
    public void onCreate() {
        usercreatedAt = LocalDateTime.now();
        userupdatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        userupdatedAt = LocalDateTime.now();
    }
}
