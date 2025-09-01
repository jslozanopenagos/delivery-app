package com.solvd.delivery.model.users;

import com.solvd.delivery.enums.UserRole;

import java.util.Objects;

public abstract class User {
    private final long id;
    private String fullName;
    private String password;
    private String email;
    private String phone;
    private final UserRole role;

    public User(
            long id, String fullName,
            String password, String email,
            String phone, UserRole role
    ) {
        this.id = id;
        this.fullName = fullName;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public UserRole getRole() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "\n---User information---\n" +
                "\nUser name: " + fullName +
                "\nUser email: " + email +
                "\nUser phone: " + phone +
                "\nUser role: " + role.toString();
    }
}