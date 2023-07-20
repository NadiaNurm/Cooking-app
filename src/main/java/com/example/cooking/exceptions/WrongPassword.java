package com.example.cooking.exceptions;

public class WrongPassword extends IllegalArgumentException{
    public WrongPassword(){
        System.out.println("Неверно введен пароль");
    }

}
