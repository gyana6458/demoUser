package com.demo.security;

import com.demo.repository.UserRepository;
import entity.Role;
import entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override//all override method are present in UserDetailsService
    public UserDetails loadUserByUsername(String usernameOrEmail) throws  UsernameNotFoundException {
                                        //it find username&email
        User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail).orElseThrow(() ->
                new UsernameNotFoundException("User not found with username or email:"+ usernameOrEmail));
                    //exception is built in feature
                                            //this is built in User class nou User Entity itimport p1.A look like this
        return new  org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(), mapRolesToAuthorities(user.getRoles()));
               //If the object is found give the email and password and ROle
    }
    private Collection< ? extends GrantedAuthority>mapRolesToAuthorities(Set<Role> roles){
        return roles.stream().map(role -> new
                SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
