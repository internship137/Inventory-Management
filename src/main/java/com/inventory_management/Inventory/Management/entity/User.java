package com.inventory_management.Inventory.Management.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;


@Entity
@Data
public class User {


    @Id
    private String userName;
    private String firstName;
    private String lastName;
    private String userPassword;
    private String email;


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "USER_ROLE",
            joinColumns = {
                    @JoinColumn(name = "USER_ID")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "ROLE_ID")
            }
    )
    private Set<Role> role;

}
