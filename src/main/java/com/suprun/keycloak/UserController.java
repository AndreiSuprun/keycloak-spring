 package com.suprun.keycloak;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.IDToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

@Controller
public class UserController {

    private final static String USER =  "user";
    private final static String FIRST_NAME =  "first_name";
    private final static String LAST_NAME =  "last_name";
    private final static String EMAIL =  "email";

    @GetMapping("/user")
    public String customers(Model model) {
        KeycloakAuthenticationToken authentication = (KeycloakAuthenticationToken) SecurityContextHolder.getContext()
                .getAuthentication();
        Principal principal = (Principal) authentication.getPrincipal();
        String firstName = null;
        String lastName = null;
        String email = null;
        if (principal instanceof KeycloakPrincipal) {
            KeycloakPrincipal<KeycloakSecurityContext> kPrincipal = (KeycloakPrincipal<KeycloakSecurityContext>) principal;
            IDToken token = kPrincipal.getKeycloakSecurityContext()
                    .getIdToken();
            firstName = token.getGivenName();
            lastName = token.getFamilyName();
            email = token.getEmail();
        }
        model.addAttribute(USER, principal.getName());
        model.addAttribute(FIRST_NAME, firstName);
        model.addAttribute(LAST_NAME, lastName);
        model.addAttribute(EMAIL, email);
        return "user";
    }

    @GetMapping(path = "/logout")
    public String logout(HttpServletResponse response) throws ServletException {
        request.logout();
        return "redirect: user";
    }
}
