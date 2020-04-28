package com.example.sweater.domain;

import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "usr")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO);
    private long id;
    private String username;
    private String password;
    private boolean activ;

    //FetchType.EAGER - жадная загрузка полей, более быстрая, но подходит для небольшой БД
    //FetchType.Leazi - ленивая загрузка полей, более медленная, но подходит для большой БД
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    //означает, что это поле будет храниться в отдельной таблице
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    //создаем таблицу user_role которая будет соединяться с текущей таблицей через user_id
    @Enumerated(EnumType.STRING) //означает, что мы будем хранить enum в виде строки
    private Set<Role> roles;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActiv() {
        return activ;
    }

    public void setActiv(boolean activ) {
        this.activ = activ;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
