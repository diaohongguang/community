package com.diao.pojo.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
/**
 * 用于实现分页的类，将数据库中查询的questions 封装到这里
 */

@Data
public class PageDto {
    private Integer currentPage;
    private Integer totlePage;
    private Boolean hasNext;
    private Boolean hasPre;
    private Boolean hasHead;
    private Boolean hasTail;
    private List<QuestionDto> questions;
    private List<Integer> page = new ArrayList<>();

    public void computerPage(){
        page.add(currentPage);
        //计算出显示的页码
        for (int i = 1; i <= 3; i++) {
            if (currentPage-i>0){
                page.add(currentPage-i);
            }
            if (currentPage + i <=totlePage){
                page.add(currentPage+i);
            }
        }
        //对现实的页码进行排序
        Collections.sort(page);
        if (currentPage==1){
            hasPre=false;
        }else {
            hasPre=true;
        }
        //获取总页数
        if (currentPage==totlePage){
            hasNext=false;
        }else {
            hasNext=true;
        }
        //是否包含第一页 如果包含就不显示<
        if (page.contains(1)){
            hasHead=false;
        }else {
            hasHead=true;
        }
        //是否包含最后一页 如果包含就不显示>
        if (page.contains(totlePage)){
            hasTail=false;
        }else {
            hasTail=true;
        }
    }
}
