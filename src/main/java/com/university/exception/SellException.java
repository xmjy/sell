package com.university.exception;

import com.university.enums.ResultEnum;
import lombok.Getter;

/**
 * @auther 方翔鸣
 * @date 2019/2/24 15:54
 */
@Getter
public class SellException extends RuntimeException {

    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public SellException(Integer code,String message) {
        super(message);
        this.code = code;
    }
}
