package com.luojian.tools.bo;

import lombok.Data;

import java.util.List;

/**
 * Created by luojian on 2019/5/26.
 */
@Data
public class PostmanDoc {
    private PostmanInfo info;
    private List<PostmanItem> item;
}
