package com.LinkTree.LinktreeClone.Exception;

public class LinkNotFoundException extends Exception{

    public LinkNotFoundException(){
        super("Link with given Id is not present in the Database!");
    }
}
