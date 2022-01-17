package com.system.passwordmanager.models;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
public class Vault {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long masterId;

    private String website;
    private String username;
    private String password;

    public Vault(long masterID, String website, String username, String password) {
        this.masterId = masterID;
        this.website = website;
        this.username = username;
        this.password = password;
    }

    public Vault() {

    }

    public long getId() {
        return id;
    }

    public long getMasterId() {
        return masterId;
    }

    public void setMasterId(long masterId) {
        this.masterId = masterId;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
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
