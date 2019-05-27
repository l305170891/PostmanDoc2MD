package com.luojian.tools.bo.postman;

import lombok.Data;

import java.util.List;

/**
 * Created by luojian on 2019/5/27.
 */
@Data
public class Auth {
    private String type;
    private List<Param> bearer;
}
