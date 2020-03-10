package com.diao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;
    private String accountId;
    private String name;
    private String token;
    private String avatarUrl;
    private Long gmtCreate;
    private Long gmtModified;
}
