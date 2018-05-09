package com.musiccollector.api.login;

public class LoginService {
    public boolean validateUser(String user, String password) {
//        return user.equalsIgnoreCase("in28Minutes") && password.equals("dummy");
        return  password.equals("123");
    }

}