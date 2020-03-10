package com.diao.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GitHubUserDto {
    private String name;
    private Long id;
    private String bio;
    private String avatarUrl;
}
