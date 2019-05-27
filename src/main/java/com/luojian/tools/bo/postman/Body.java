package com.luojian.tools.bo.postman;

import lombok.Data;

import java.util.List;

/**
 * Created by luojian on 2019/5/27.
 */
@Data
public class Body {
    private String mode;
    private List<Param> formdata;
}
