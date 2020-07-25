package com.university.VO;

import lombok.Data;

import java.io.Serializable;

/**
 * http请求返回的最外层对象
 * @auther 林就远
 * @date 2019/2/23 10:28
 */
@Data
public class ResultVO<T> implements Serializable {

    private static final long serialVersionUID = 5041166457740486666L;
    /* 错误码 */
    private Integer code;

    /* 提示信息 */
    private String msg;

    /* 具体内容*/
    private T data;

}
