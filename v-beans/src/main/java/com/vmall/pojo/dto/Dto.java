package com.vmall.pojo.dto;

public class Dto<T> {

    private Status status;
    private T data;

    private Dto(){

    }


    private Dto(Status status, T data) {
        this.status = status;
        this.data = data;
    }

    private Dto(Status status) {
        this.status = status;
    }

    public static<T> Dto success(T data){
        return new Dto(Status.SUCCESS,data);
    }

    public static<T> Dto failure(Status status){
        return new Dto(status);
    }

    public static<T> Dto success(Status status,T data){
        return new Dto(status,data);
    }


    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
