package com.inventory_management.Inventory.Management.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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

    @Column(name = "supplier_name",unique = true)
    @NotBlank(message = "Supplier company name cannot be null or blank")
    @Pattern(regexp = "^[a-zA-Z0-9 ]{1,70}$", message = "Please provide a valid Supplier Name")
    private String supplierName;

    @NotBlank(message = "Supplier name cannot be null or blank")
    @Pattern(regexp = "^[a-zA-Z0-9 ]{1,70}$", message = "Please provide a valid Company Name")
    @Column(name = "supplier_company")
    private String supplierCompany;

    @NotBlank(message = "Supplier contact cannot be null or blank")
    @Pattern(regexp = "^\\d{10}$",message = "invalid mobile number entered ")
    @Column(name = "supplier_contact")
    private String supplierContact;
    
    @Email(message = "invalid email address",regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
            flags = Pattern.Flag.CASE_INSENSITIVE)
    @Column(name = "supplier_email")
    private String supplierEmail;

}