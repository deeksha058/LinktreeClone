package com.LinkTree.LinktreeClone.Exception;

public class UserNotFoundException extends Exception {

    public UserNotFoundException(){

        super("User Is Not present In the Database!");
    }
}
