package com.inventory_management.Inventory.Management.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long employeeId;

    @NotBlank(message = "Employee company name cannot be null or blank")
    @Column(name = "employee_name")
    private String employeeName;

    @NotBlank(message = "Employee code is mandatory")
    @Column(name = "employee_code", unique = true)
    private String employeeCode;

    @Pattern(regexp = "^\\d{10}$", message = "invalid mobile number entered ")
    @Column(name = "employee_contact")
    private String employeeContact;

    @Email(message = "invalid email address")
    @Column(name = "employee_email")
    private String employeeEmail;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull(message = "Please enter the Date of Birth")
    @Past(message = "Enter a valid date")
    @Column(name = "date_of_birth")
    private Date dateOfBirth;


    @Min(18)
    @Column(name = "employee_age")
    private int employeeAge;

    @NotNull(message = "Please enter the place")
    @Column(name = "place")
    private String place;

}
