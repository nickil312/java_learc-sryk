package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
public class UserProfileModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 2)
    private String phoneNumber;

    @OneToOne(mappedBy = "userProfile", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ModelUser  user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @Size(min = 2) String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(@Size(min = 2) String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public ModelUser getUser() {
        return user;
    }

    public void setUser(ModelUser user) {
        this.user = user;
    }
}
