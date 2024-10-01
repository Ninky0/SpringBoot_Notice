package org.example.springboot_notice.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class User {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String userid;
    private String password;
}
