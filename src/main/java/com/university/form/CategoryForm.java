package com.university.form;

import lombok.Data;

/**
 * @author 方翔鸣
 * @create 2019-03-25 17:07
 **/
@Data
public class CategoryForm {

    private Integer categoryId;

    /** 类目名字. */
    private String categoryName;

    /** 类目编号. */
    private Integer categoryType;
}
