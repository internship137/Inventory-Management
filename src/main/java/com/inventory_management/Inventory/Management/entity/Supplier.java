package com.inventory_management.Inventory.Management.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supplier_id")
    private Long supplierId;

    @Column(name = "supplier_name")
    @NotBlank(message = "Supplier company name cannot be null or blank")
    @Pattern(regexp = "^[a-zA-Z0-9 ]{1,70}$", message = "Please provide a valid Supplier Name")
    private String supplierName;

    @NotBlank(message = "Supplier name cannot be null or blank")
    @Pattern(regexp = "^[a-zA-Z0-9 ]{1,70}$", message = "Please provide a valid Company Name")
    @Column(name = "supplier_company")
    private String supplierCompany;

    @NotBlank(message = "Supplier contact cannot be null or blank")
    @Pattern(regexp = "^\\d{10}$", message = "Invalid mobile number entered ")
    @Column(name = "supplier_contact", unique = true)
    private String supplierContact;

    @NotBlank(message = "Supplier Email Address should not be empty")
    @Pattern(regexp = "[a-zA_Z0-9_\\-\\.]+[@][a-z]+[\\.][a-z]{2,3}",message = "Please enter a valid Email Address")
    @Column(name = "supplier_email")
    private String supplierEmail;

}