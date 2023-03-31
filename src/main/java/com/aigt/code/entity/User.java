package com.aigt.code.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private Long userId;

    private String userName;

    private String nickName;

    private String email;

    private String phoneNum;

    private String sex;

    private String password;
    private String salt;

    private String status;
    private String delFlag;

    private String loginIp;

    private Date loginDate;
}
