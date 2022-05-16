package com.example.therestaurant.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public  Integer getId() {
        return  id;
    }

    public   void setId(Integer id) {
        this.id = id;
    }

    @Column(nullable = false)
    @Size(min = 4, max = 50, message = "Firstname must be 4 to 50 characters long.")
    private String firstname;

    public String getFirstname() {
        return  firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Column(nullable = false)
    @Size(min = 4, max = 50, message = "Lastname must be 4 to 50 characters long.")
    private String lastname;

    public String getLastname() {
        return  lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Column(nullable = false)
    @Pattern(regexp = "^[a-z0-9]{4,20}$",message="Username can only contains lowercase letters and numbers. And must be between 4-20 characters.")
    private String username;

    public String getUsername() {
        return  username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String email;

    public String getEmail() {
        return  email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Pattern(regexp = "^(?!0+$)(\\+\\d{1,3}[- ]?)?(?!0+$)\\d{10}$", message = "Mobile number is invalid.")
    private String mobile;

    public String getMobile() {
        return  mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    private String address;

    public String getAddress() {
        return  address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$",message = "Password must be minimum of eight characters, at least one letter, one number and one special character")
    private String password;

    public String getPassword() {
        return  password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Transient
    private String passwordAgain;

    public String getPasswordAgain() {
        return  passwordAgain;
    }

    public void setPasswordAgain(String passwordAgain) {
        this.passwordAgain = passwordAgain;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority(role == User.isWho.Admin ? "ROLE_ADMIN" : "ROLE_CUSTOMER"));

        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public enum isWho { Customer, Admin }
    @Enumerated
    private isWho role = isWho.Customer;

    public isWho getRole() {
        return  role;
    }

    public void setRole(isWho role) {
        this.role = role;
    }

    public User(String username, String firstname, String lastname,String email, String mobile, String address, String password, isWho role) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.mobile = mobile;
        this.address = address;
        this.password = password;
        this.role = role;
    }


    public String getFullname() {
        return this.firstname + " " + this.lastname;
    }
}
