package com.inventory_management.Inventory.Management.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

    @Column(name = "employee_name")
    private String employeeName;
    @Column(name = "employee_code")
    private String employeeCode;

    @Column(name = "employee_contact")
    private Long employeeContact;

    @Column(name = "employee_email")
    private String employeeEmail;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "employee_age")
    private Long employeeAge;

    @Column(name = "place")
    private String place;
}
