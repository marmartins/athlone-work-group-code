package com.neueda.adg.servicecli;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;

import java.util.Arrays;

@SpringBootApplication
public class ServiceCliApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ServiceCliApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Starting job");

        ResourceOwnerPasswordResourceDetails resourceDetails = new ResourceOwnerPasswordResourceDetails();
        resourceDetails.setClientAuthenticationScheme(AuthenticationScheme.header);
        resourceDetails.setAccessTokenUri("http://localhost:9000/oauth/token");

        resourceDetails.setScope(Arrays.asList("read"));

        resourceDetails.setClientId("neueda-adg");
        resourceDetails.setClientSecret("neueda-adg-secret");

        resourceDetails.setUsername("marcelo");
        resourceDetails.setPassword("marcelo-password");

        OAuth2RestTemplate template =  new OAuth2RestTemplate(resourceDetails);
        String token = template.getAccessToken().toString();
        System.out.println("Token: " + token);

        String persons = template.getForObject("http://localhost:8082/services/persons", String.class);
        System.out.println("Result: " + persons);
    }
}
