package com.vsu.entities;

import java.util.Objects;

public class User {
    private Long id;
    private String surname;
    private String name;
    private String birthday;
    private String email;
    private String phone;
    private String password;

    public User() {
    }

    public User(Long id, String surname, String name, String birthday, String email, String phone, String password) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.birthday = birthday;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    public User(String surname, String name, String birthday, String email, String phone, String password) {
        this.surname = surname;
        this.name = name;
        this.birthday = birthday;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(getId(), user.getId()) && Objects.equals(getSurname(), user.getSurname()) && Objects.equals(getName(), user.getName()) && Objects.equals(getBirthday(), user.getBirthday()) && Objects.equals(getEmail(), user.getEmail()) && Objects.equals(getPhone(), user.getPhone()) && Objects.equals(getPassword(), user.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSurname(), getName(), getBirthday(), getEmail(), getPhone(), getPassword());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", birthday='" + birthday + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
