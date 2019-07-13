package com.example.nuc.wordremember.ViewItem;

public class ExploreItem {

    public String getImag_id() {
        return imag_id;
    }

    public void setImag_id(String imag_id) {
        this.imag_id = imag_id;
    }

    private String imag_id;
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

   //带参数的构造方法
    public ExploreItem(String imag_id, String text) {
        this.imag_id = imag_id;
        this.text = text;
    }



}
