package com.luojian.tools.bo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * Created by luojian on 2019/5/27.
 */

@Data
public class PostmanInfo extends PostmanFolder {

    @JSONField(name = "_postman_id")
    private String postman_id;
    private String schema2;
}
