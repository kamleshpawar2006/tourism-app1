package com.TM_branch_service.TMBranchService.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BranchDto {
    private Long branchId;

    @NotEmpty(message = "Branch name cannot be empty")
    private String branchName;

    @NotEmpty(message = "Place cannot be empty")
    private String place;

    @NotEmpty(message = "Website cannot be empty")
    @Pattern(regexp = "^(www\\.).*$", message = "Website should contain 'www'")
    private String website;

    @NotEmpty(message = "Contact cannot be empty")
    @Size(min = 10, max = 10, message = "Contact number must be 10 digits")
    private String contact;

    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Email should be valid")
    private String email;

    @Min(value = 50000, message = "Tariff must be at least 50000")
    @Max(value = 100000, message = "Tariff must be less than or equal to 100000")
    private Integer tariff;

    private LocalDateTime dateAdded;
}
