/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for
 * license information.
 */
package com.lnt.sb.security;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

//import org.springframework.stereotype.Component;
//
//import com.microsoft.azure.spring.autoconfigure.aad.AADAuthenticationFilter;
//import com.microsoft.azure.spring.autoconfigure.aad.AADAuthenticationProperties;
//import com.microsoft.azure.spring.autoconfigure.aad.ServiceEndpointsProperties;
//import com.nimbusds.jose.util.DefaultResourceRetriever;
//import com.nimbusds.jose.util.ResourceRetriever;
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true,
        prePostEnabled = true)

@ComponentScan({"com.lnt.sb.security"})
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    public void configure(WebSecurity web) throws Exception {
        web
            .ignoring()
            .antMatchers("/resources/**");
    }
//    @Autowired
//    AADAuthenticationProperties aadAuthProps;
//    
//    @Autowired
//    ServiceEndpointsProperties serviceEndpointsProps;
//    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	System.out.println("WEB SECURITY CONFIGURATION");
    	//aadAuthFilter=new AADAuthenticationFilter(null, null, null);
    	//AADAuthenticationProperties aadAuthProps= new AADAuthenticationProperties();
    	//ServiceEndpointsProperties serviceEndpointsProps= new ServiceEndpointsProperties();
//    	ResourceRetriever resourceRetriever = new DefaultResourceRetriever(100,100);
//    	aadAuthFilter= new AADAuthenticationFilter(aadAuthProps,serviceEndpointsProps,resourceRetriever);

    	http.cors().and().authorizeRequests()
        	.antMatchers("/").permitAll()
        	.antMatchers("/redirectUrl").permitAll()
        	.antMatchers("/OpenAPIs/**").permitAll();
        http.authorizeRequests().anyRequest().permitAll();
        CookieCsrfTokenRepository cctr = new CookieCsrfTokenRepository();
        cctr.setCookieHttpOnly(false);
        cctr.setCookiePath("/");

        http.csrf().csrfTokenRepository(cctr);
      
    }
    
    @Bean
    CorsConfigurationSource corsConfigurationSource()
    {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
//        configuration.setExposedHeaders(Arrays.asList("Set-Cookie,Authorization,x-xsrf-token,Access-Control-Allow-Headers,Origin,Accept,X-Requested-With, " +
//                "Content-Type,Access-Control-Request-Method,Custom-Filter-Header".split(",")));
      
        configuration.setAllowedMethods(Arrays.asList("GET","POST","OPTIONS","PUT","DELETE"));
       
       configuration.addAllowedHeader("x-xsrf-token");
//       configuration.addAllowedHeader("XSRF-TOKEN");
//       configuration.addAllowedHeader("X-CSRF-Token");
       configuration.addAllowedHeader("Access-Control-Allow-Origin");
       configuration.addAllowedHeader("Content-Type");
        configuration.addAllowedOrigin("*");
       
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
    
}
