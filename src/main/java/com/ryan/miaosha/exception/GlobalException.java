package com.ryan.miaosha.exception;

import com.ryan.miaosha.result.CodeMsg;

public class GlobalException extends RuntimeException {
    private final static long serialVersionUID = 1L;

    private CodeMsg cm;

    public GlobalException(CodeMsg cm) {
        super(cm.toString());
        this.cm = cm;
    }

    public CodeMsg getCm() {
        return cm;
    }
}
