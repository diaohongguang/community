package com.diao.utils;

import com.diao.pojo.dto.TagsDto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tags {
    public static List<TagsDto> getListTag(){
     List<TagsDto> list = new ArrayList<>();
     TagsDto dto1= new TagsDto();
     dto1.setName("开发语言");
     List<String> lang1 = new ArrayList<>();
     lang1.addAll(Arrays.asList("javascropt","php","css","html","java",
             "node.js","python","c","c++","golang","objective-c","typescript","shell",
             "swift","c#","sass","ruby","bash","less","asp.net","lua","scala",
             "coffeescript","rust","erlang","perl"));
     dto1.setTag(lang1);
     list.add(dto1);


        TagsDto dto2= new TagsDto();
        dto2.setName("平台框架");
        List<String> lang2 = new ArrayList<>();
        lang2.addAll(Arrays.asList("spring","laravel","express","django","flask","yii",
                "ruby-on-rails","tornado","koa","struts"));
        dto2.setTag(lang2);
        list.add(dto2);

        TagsDto dto3= new TagsDto();
        dto3.setName("服务器");
        List<String> lang3 = new ArrayList<>();
        lang3.addAll(Arrays.asList("linux","nginx","docker","apache","nbuntu","centos",
                "缓存","tomcat","负载均衡","unix","hadoop","windows-server"));
        dto3.setTag(lang3);
        list.add(dto3);

        TagsDto dto4= new TagsDto();
        dto4.setName("数据库");
        List<String> lang4 = new ArrayList<>();
        lang4.addAll(Arrays.asList("mysql","redis","mongodb","sql","oracle","nosql","memcached","sqlserver",
                "postgresql","sqlite"));
        dto4.setTag(lang4);
        list.add(dto4);

        TagsDto dto5= new TagsDto();
        dto5.setName("开发工具");
        List<String> lang5 = new ArrayList<>();
        lang5.addAll(Arrays.asList("git","github","visual-studio-code","vim",
                "sublime-text","xcode","intellij-idea","eclipse","maven",
                "ide","svn","visual-studio","atom","emacs","textmate","hg"));
        dto5.setTag(lang5);
        list.add(dto5);

        return list;

    }

    public static Boolean isValid(String tag){
        String[] split = tag.split(",");
        List<TagsDto> listTag = getListTag();
        List<String> tags= new ArrayList<>();
        for (TagsDto dto : listTag) {
            tags.addAll(dto.getTag());
        }
        for (String s : split) {
            if (!tags.contains(s)){
                return false;
            }
        }
        return true;
    }
}
