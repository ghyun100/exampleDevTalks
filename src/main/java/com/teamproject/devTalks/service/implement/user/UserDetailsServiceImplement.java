package com.teamproject.devTalks.service.implement.user;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.teamproject.devTalks.entity.user.UserEntity;
import com.teamproject.devTalks.repository.user.UserRepository;
import com.teamproject.devTalks.security.UserPrinciple;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImplement implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUserEmail(email);
        if(user==null) throw new UsernameNotFoundException("User not found with email : " + email);
        return new UserPrinciple(user);
    }
    
}
