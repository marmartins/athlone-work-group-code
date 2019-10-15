package com.neueda.adg.secureui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
@EnableOAuth2Sso
public class ReportController {

    @Autowired
    private OAuth2RestTemplate oAuth2RestTemplate;

    @Autowired
    private OAuth2ClientContext oAuth2ClientContext;

    @RequestMapping("/")
    public String loadHome() {
        return "/home";
    }


    @GetMapping("/reports")
    public String loadReport(Model model) {
        OAuth2AccessToken token = oAuth2ClientContext.getAccessToken();
        System.out.println("Token: " + token.getValue());

        ResponseEntity<ArrayList<Person>> returnedValues = oAuth2RestTemplate
                .exchange("http://localhost:8082/services/persons",
                        HttpMethod.GET, null,
                        new ParameterizedTypeReference<ArrayList<Person>>() {});

        model.addAttribute("persons", returnedValues.getBody());
        return "reports";
    }

}
