package com.luojian.tools.bo;

import com.alibaba.fastjson.annotation.JSONField;
import com.luojian.tools.bo.postman.OriginalRequest;
import com.luojian.tools.bo.postman.Param;
import lombok.Data;

import java.util.List;

/**
 * Created by luojian on 2019/5/26.
 */
@Data
public class PostmanResponse {
    private String name;
    private OriginalRequest originalRequest;
    private String status;
    private Integer code;
    @JSONField(name = "_postman_previewlanguage")
    private String postman_previewlanguage;
    private List<Param> header;
    private List<Param> cookie;
    private String body;
}
