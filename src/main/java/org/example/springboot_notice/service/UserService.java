package org.example.springboot_notice.service;

import lombok.RequiredArgsConstructor;
import org.example.springboot_notice.domain.User;
import org.example.springboot_notice.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;

    public void createUser(User user) {
        userMapper.createUser(user);
    }

    public Boolean joinedUser(User user) {
        User member = userMapper.joinedUserByEmail(user.getEmail());
        User member2 = userMapper.joinedUserByPhone(user.getPhone());
        if(member!=null && member2!=null){
            return true;
        }
        return false;
    }

    public Boolean validId(User user){
        User member = userMapper.validId(user.getUserid());
        if(member==null){
            return true;
        }
        return false;
    }

}
