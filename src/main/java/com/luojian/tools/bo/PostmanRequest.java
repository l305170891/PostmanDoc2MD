package com.luojian.tools.bo;

import com.luojian.tools.bo.postman.Auth;
import com.luojian.tools.bo.postman.Body;
import com.luojian.tools.bo.postman.Param;
import com.luojian.tools.bo.postman.Url;
import lombok.Data;

import java.util.List;

/**
 * Created by luojian on 2019/5/26.
 */
@Data
public class PostmanRequest {
    private Auth auth;
    private String method;
    private List<Param> header;
    private Body body;
    private Url url;
    private String description;
}
