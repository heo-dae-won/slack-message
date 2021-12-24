package com.heo.dae.won.slackmessage.enums;

public enum BlockType {
    header("header")
    ,section( "section");

    private String value;

    BlockType(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
