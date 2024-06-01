package com.TM_branch_service.TMBranchService.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long branchId;

    @NotEmpty(message = "Branch name cannot be empty")
    @Column(name = "branchName")
    private String branchName;

    @NotEmpty(message = "Place cannot be empty")
    @Column(name = "place")
    private String place;

    @NotEmpty(message = "Website cannot be empty")
    @Pattern(regexp = "^(www\\.).*$", message = "Website should contain 'www'")
    @Column(name = "website")
    private String website;

    @NotEmpty(message = "Contact cannot be empty")
    @Size(min = 10, max = 10, message = "Contact number must be 10 digits")
    @Column(name = "contact")
    private String contact;

    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Email should be valid")
    @Column(name = "email")
    private String email;

    @Column(name = "tariff", nullable = true)
    private Integer tariff;

    @Column(name = "dateAdded", updatable = false, insertable = false)
    private LocalDateTime dateAdded;

    @PrePersist
    protected void onCreate() {
        this.dateAdded = LocalDateTime.now();
    }
}
