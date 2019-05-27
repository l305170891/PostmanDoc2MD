package com.luojian.tools.bo;

import lombok.Data;

import java.util.List;

/**
 * Created by luojian on 2019/5/26.
 */
@Data
public class PostmanItem {
    private String name;
    private List<PostmanItem> item;
    private String description;
    private PostmanRequest request;
    private List<PostmanResponse> response;

    public boolean isFolder(){
        return request == null ? true : false;
    }
}
