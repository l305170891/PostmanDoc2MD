package com.luojian.tools.bo.postman;

import lombok.Data;

import java.util.List;

/**
 * Created by luojian on 2019/5/26.
 */
@Data
public class OriginalRequest {
    private String method;
    private List<Param> header;
    private Body body;
    private Url url;
}
