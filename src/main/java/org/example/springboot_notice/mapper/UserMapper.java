package org.example.springboot_notice.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.springboot_notice.domain.User;

@Mapper
public interface UserMapper {
    void createUser(User user);
    User joinedUserByEmail(String email);
    User joinedUserByPhone(String phone);
    User validId(String userid);
    User findById(Long id);
    User findByUserId(String userid);
}
