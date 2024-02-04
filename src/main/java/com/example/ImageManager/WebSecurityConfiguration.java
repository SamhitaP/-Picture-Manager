package com.example.ImageManager;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;

@Configuration
public class WebSecurityConfiguration  {

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
       return web -> web.ignoring().requestMatchers(HttpMethod.POST,"/images/catpicture/upload")
                .requestMatchers(HttpMethod.GET,"/images/catpicture/one").anyRequest();

    }
}