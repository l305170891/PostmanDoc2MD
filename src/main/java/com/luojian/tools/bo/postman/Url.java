package com.luojian.tools.bo.postman;

import lombok.Data;

import java.util.List;

/**
 * Created by luojian on 2019/5/27.
 */
@Data
public class Url {
    private String raw;
    private List<String> host;
    private List<String> path;
    private List<Param> query;
}
