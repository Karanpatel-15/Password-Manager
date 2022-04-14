package com.system.passwordmanager.services;

import com.system.passwordmanager.controllers.AccountVerificationEmailContext;
import com.system.passwordmanager.models.MasterUser;
import com.system.passwordmanager.models.SecureToken;
import com.system.passwordmanager.repository.MasterUserRepository;
import com.system.passwordmanager.repository.SecureTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.Objects;


@Service("userDetailsService")
public class MasterUserService implements UserDetailsService {

    @Autowired
    MasterUserRepository masterUserRepository;

    @Autowired
    private SecureTokenService secureTokenService;

    @Autowired
    private SecureTokenRepository secureTokenRepository;

    @Autowired
    private EmailService emailService;

    @Value("${site.base.url.https}")
    private String baseURL;

    public MasterUser addMasterUser(MasterUser masterUser) throws DataIntegrityViolationException {
        MasterUser m = masterUserRepository.save(masterUser);
        sendRegistrationConfirmationEmail(m);
        return m;
    }

    @Override
    public UserDetails loadUserByUsername(String username)  {
        MasterUser masterUser = masterUserRepository.findByUsername(username.toLowerCase());

        boolean enabled = !masterUser.isEnabled();
        UserDetails user = User.withUsername(masterUser.getUsername())
                .password(masterUser.getPassword())
                .disabled(enabled)
                .authorities("USER").build();

        return user;
    }

    public void sendRegistrationConfirmationEmail(MasterUser masterUser){
        SecureToken secureToken = secureTokenService.createSecureToken();
        secureToken.setMasterUser(masterUser);
        secureTokenRepository.save(secureToken);
        AccountVerificationEmailContext emailContext = new AccountVerificationEmailContext();
        emailContext.init(masterUser);
        emailContext.setToken(secureToken.getToken());
        emailContext.buildVerificationUrl(baseURL, secureToken.getToken());
        try {
            emailService.sendMail(emailContext);
            System.out.println("sent");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean verifyUser(String token) throws Exception {
        SecureToken secureToken = secureTokenService.findByToken(token);
        if(Objects.isNull(secureToken) || !StringUtils.equals(token, secureToken.getToken()) || secureToken.isExpired()){
            return false;
        }
        MasterUser user = masterUserRepository.getById(secureToken.getMasterUser().getId());
        if(Objects.isNull(user)){
            throw new Exception("object null");
        }
        user.setActive(true);
        masterUserRepository.save(user); // let's same user details

        // we don't need invalid password now
        secureTokenService.removeToken(secureToken);
        return true;
    }

}
