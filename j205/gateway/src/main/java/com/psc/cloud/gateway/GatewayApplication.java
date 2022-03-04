package com.psc.cloud.gateway;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.security.oauth2.gateway.TokenRelayGatewayFilterFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.WebSession;
import reactor.core.publisher.Mono;

import java.io.PrintWriter;
import java.security.Principal;

@SpringBootApplication
@RestController
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @GetMapping("/")
    public Mono<String> index(@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient authorizedClient,
                        @AuthenticationPrincipal OAuth2User oauth2User) {
        return Mono.just("주문서비스로 바로 옮겨가게하고싶다 userName : " + oauth2User.getName() + ", clientName : " + authorizedClient.getClientRegistration().getClientName() + ", userAttributes : " + oauth2User.getAttributes());
    }

    @GetMapping(value = "/token")
    public Mono<String> token(@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient authorizedClient) {
//        return Mono.just("이것은 Access Token이야 : " + authorizedClient.getAccessToken().getTokenValue());
        return Mono.just(authorizedClient.getAccessToken().getScopes() + "ㅎㅇ" + authorizedClient.getAccessToken().getTokenType() +
                "베베베베"+authorizedClient.getAccessToken().getTokenValue());

    }

    @GetMapping("/session")
    public Mono<String> index(WebSession session) {
        return Mono.just("이것은 Session ID야 : "+session.getId());
    }
}
