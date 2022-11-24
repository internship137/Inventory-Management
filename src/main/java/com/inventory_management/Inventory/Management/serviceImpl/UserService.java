package com.inventory_management.Inventory.Management.serviceImpl;

import com.inventory_management.Inventory.Management.entity.Role;
import com.inventory_management.Inventory.Management.entity.User;
import com.inventory_management.Inventory.Management.repository.RoleRepository;
import com.inventory_management.Inventory.Management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;



    public User registerNewUser(User user) {
        Role role=roleRepository.findById("User").get();
        Set<Role> roles=new HashSet<>();
        roles.add(role);
        user.setRole(roles);
        user.setUserPassword(getEncodedPassword(user.getUserPassword()));
        return userRepository.save(user);
    }
    public void initRolesAndUser(){
        Role adminRole=new Role();
        adminRole.setRoleName("Admin");
        adminRole.setRoleDescription("Admin Role");
        roleRepository.save(adminRole);

        Role userRole=new Role();
        userRole.setRoleName("User");
        userRole.setRoleDescription("default for newly created record");
        roleRepository.save(userRole);

        User adminUser=new User();
        adminUser.setUserName("admin123");
        adminUser.setFirstName("admin");
        adminUser.setLastName("admin");
        adminUser.setUserPassword(getEncodedPassword("admin@123"));
        Set<Role> adminRoles=new HashSet<>();
        adminUser.setRole(adminRoles);
        adminRoles.add(adminRole);
        userRepository.save(adminUser);

       /* User user=new User();
        user.setUserName("piya");
        user.setFirstName("priyanka");
        user.setLastName("binoy");
        user.setPassword(getEncodedPassword("priyanka@123"));
        Set<Role> userRoles=new HashSet<>();
        userRoles.add(userRole);
        user.setRole(userRoles);
        userRepository.save(user);*/

    }

    //@Bean
   // public PasswordEncoder passwordEncoder(){
        //return new BCryptPasswordEncoder();
    //}
    public String getEncodedPassword(String password){
        return passwordEncoder.encode(password);
    }
}
