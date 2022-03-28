package com.hotelapp.hotelapp.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long userId;
    @Column(name = "user_type")
    String userType;

    public User(Long userId, String userType) {
        this.userId = userId;
        this.userType = userType;
    }

    public User() {
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
