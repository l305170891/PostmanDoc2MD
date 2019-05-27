package com.luojian.tools.bo;

import lombok.Data;

import java.util.List;

/**
 * Created by luojian on 2019/5/26.
 */
@Data
public class PostmanItem {
    private String name;// folder leaf
    private List<PostmanItem> item;//folder
    private String description;//folder

    private PostmanRequest request;//leaf
    private List<PostmanResponse> response;//leaf

    public boolean isFolder(){
        return request == null ? true : false;
    }
}
