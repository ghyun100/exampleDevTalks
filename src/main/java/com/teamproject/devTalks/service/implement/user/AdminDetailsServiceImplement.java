package com.teamproject.devTalks.service.implement.user;

import com.teamproject.devTalks.security.AdminPrinciple;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.teamproject.devTalks.entity.user.AdminEntity;
import com.teamproject.devTalks.repository.user.AdminRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminDetailsServiceImplement implements UserDetailsService {

    private final AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AdminEntity admin = adminRepository.findByAdminEmail(email);
        if (admin == null) {
            throw new UsernameNotFoundException("Admin not found with email : " + email);
        }
        return new AdminPrinciple(admin);
    }

}
