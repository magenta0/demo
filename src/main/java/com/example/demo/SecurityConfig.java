// SecurityConfig.java
package com.example.demo;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer.AuthorizedUrl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    protected void configure(@NotNull HttpSecurity http) throws Exception {
        Intrinsics.checkParameterIsNotNull(http, "http");
        ((HttpSecurity)((FormLoginConfigurer)((HttpSecurity)((AuthorizedUrl)((AuthorizedUrl)http.authorizeRequests().antMatchers(new String[]{"/", "/home"})).permitAll().antMatchers(new String[]{"/user"})).authenticated().and()).formLogin().permitAll()).and()).logout().permitAll();
    }

    @Bean
    @NotNull
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder().username("user1").password("password1").roles(new String[]{"USERa"}).build();
        return (UserDetailsService)(new InMemoryUserDetailsManager(new UserDetails[]{user}));
    }
}
// MvcConfig.java

@Configuration
class MvcConfig implements WebMvcConfigurer {
    public void addViewControllers(@NotNull ViewControllerRegistry registry) {
        Intrinsics.checkParameterIsNotNull(registry, "registry");
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/login").setViewName("login");
    }
}
// SpringSecurityInitializer.java
final class SpringSecurityInitializer extends AbstractSecurityWebApplicationInitializer {
}
