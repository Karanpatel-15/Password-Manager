package com.system.passwordmanager.controllers;

import com.system.passwordmanager.AbstractEmailContext;
import com.system.passwordmanager.models.MasterUser;
import org.springframework.web.util.UriComponentsBuilder;

public class AccountVerificationEmailContext extends AbstractEmailContext {

    private String token;

    @Override
    public <T> void init(T context){
        MasterUser masterUser = (MasterUser) context;
        put("firstName", masterUser.getFirstName());
        setTemplateLocation("email/email-verification");
        setSubject("Complete your registration");
        setFrom("s332872431@gmail.com");
        setTo( masterUser.getEmail());
    }

    public void setToken(String token) {
        this.token = token;
        put("token", token);
    }

    public void buildVerificationUrl(final String baseURL, final String token){
        final String url= UriComponentsBuilder.fromHttpUrl(baseURL)
                .path("/masteruser/verify").queryParam("token", token).toUriString();
        put("verificationURL", url);
    }
}
