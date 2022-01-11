package com.system.passwordmanager.models;

import javax.persistence.*;

@Entity
public class Vault {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String username;
    private String password;

    public Vault(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Vault() {

    }

    public long getId() {
        return id;
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
}
