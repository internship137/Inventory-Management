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
    private String supplierName;

    @NotBlank(message = "Supplier name cannot be null or blank")
    @Column(name = "supplier_company")
    private String supplierCompany;
    
    @Pattern(regexp = "^\\d{10}$",message = "invalid mobile number entered ")
    @Column(name = "supplier_contact")
    private String supplierContact;
    
    @Email(message = "invalid email address")
    @Column(name = "supplier_email")
    private String supplierEmail;

}