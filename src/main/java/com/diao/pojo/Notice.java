package com.diao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notice {
    private Integer id;
    private Integer receiver;
    private Integer notifier;
    private Integer outerId;
    private Integer type;
    private Long gmtCreate;
    private Integer status;
    private String outerTitle;
    private String notifyName;
}
