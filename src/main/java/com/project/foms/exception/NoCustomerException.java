package com.project.foms.exception;

public class NoCustomerException extends RuntimeException{
    public NoCustomerException(){
        super("Customer is not avialable");
    }
}
