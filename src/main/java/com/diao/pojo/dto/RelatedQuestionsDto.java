package com.diao.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RelatedQuestionsDto {
    private String title;
    private String description;
    private Integer id;
}
