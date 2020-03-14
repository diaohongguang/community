package com.diao.pojo;

public enum TypeEnum {
    COMMENT(1),
    REPLY(2);
    private Integer type;
    public static boolean isExit(Integer temp){
        for (TypeEnum value : values()) {
            if (value.getType().equals(temp)){
                return true;
            }
        }
        return false;
    }

    TypeEnum() {
    }
    TypeEnum(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
