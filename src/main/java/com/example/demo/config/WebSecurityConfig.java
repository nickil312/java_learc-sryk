//package com.example.demo.config;
//
//
//import com.example.demo.model.ModelUser;
//import com.example.demo.repository.UserRepository;
//import jakarta.servlet.DispatcherType;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
////@EnableMethodSecurity(prePostEnabled = true)
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class WebSecurityConfig {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder(8);
//    }
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(username -> {
//            ModelUser user = userRepository.findByUsername(username);
//            if (user == null) {
//                throw new UsernameNotFoundException("Такой пользователь не существует");
//            }
//            return new org.springframework.security.core.userdetails.User(
//                    user.getUsername(),
//                    user.getPassword(),
//                    user.isActive(),
//                    true,
//                    true,
//                    true,
//                    user.getRoles()
//            );
//        }).passwordEncoder(passwordEncoder());
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//
////        http
////                .a
////                .authorizeRequests()
////
////                .antMatchers("/login", "/registration").permitAll()
////                .anyRequest().authenticated()
////                .and()
////                .formLogin()
////                .loginPage("/login")
////                .defaultSuccessUrl("/")
////                .permitAll()
////                .and()
////                .logout()
////                .permitAll()
////                .and()
////                .csrf().disable()
////                .cors().disable();
//
//
//
//        //.logout(AbstractAuthenticationFilterConfigurer::);
//
////                .formLogin((login) ->
////                        login.loginPage("/login"))
////                .csrf((csrf) ->
////                        csrf.disable())
////                .cors((cors) ->
////                        cors.disable());
//
//
////        http
////                .csrf(AbstractHttpConfigurer::disable)
////                .cors(AbstractHttpConfigurer::disable)
////                .authorizeHttpRequests(
////                        authorize -> authorize
////                                .dispatcherTypeMatchers(DispatcherType.FORWARD, DispatcherType.ERROR).permitAll()
////                                .requestMatchers("/admin").hasAuthority("ADMIN")
////                                .requestMatchers("/registration", "/login").permitAll()
//////                                .requestMatchers("/user").hasAuthority("USER")
////
////                                .anyRequest().authenticated())
////                .formLogin(AbstractAuthenticationFilterConfigurer::permitAll)
//////
////                .logout((logout) ->
////                        logout.permitAll());
////
////
////        return http.build();
//    }
//}
