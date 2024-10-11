package org.example.springboot_notice.service;

import lombok.RequiredArgsConstructor;
import org.example.springboot_notice.config.security.CustomUserDetails;
import org.example.springboot_notice.domain.User;
import org.example.springboot_notice.mapper.UserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {

    private final UserMapper userMapper;

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println(username);

        User user = userMapper.findByUserId(username);
        System.out.println(user.getUserid()+" , "+user.getPassword());

        if (user == null) {
            throw new UsernameNotFoundException(username+" not found");
        }

        return CustomUserDetails.builder()
                .user(user)
                .build();
    }
}
