package com.heo.dae.won.slackmessage.enums;

import com.heo.dae.won.slackmessage.vo.Text;

public enum TextType {
    plain_text("plain_text");

    private String value;

    TextType(String value){
        this.value = value;
    }

    public String getPlainText(){
        return value;
    }
}
