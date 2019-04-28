package com.vmall.vutil.exception;

public class StoreNotEnoughException extends RuntimeException{
    public StoreNotEnoughException() {
        super();
    }

    public StoreNotEnoughException(String message) {
        super("库存不足");
    }
}
