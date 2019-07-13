package com.example.nuc.wordremember.ViewItem;

public class StrangeWord {

    private String StrangeWord;
    private String Strangewordalphapt;
    private String StrangewordExplanation;

    public StrangeWord(String strangeWord, String strangewordalphapt, String strangewordExplanation) {
        StrangeWord = strangeWord;
        Strangewordalphapt = strangewordalphapt;
        StrangewordExplanation = strangewordExplanation;
    }




    public String getStrangeWord() {
        return StrangeWord;
    }

    public void setStrangeWord(String strangeWord) {
        StrangeWord = strangeWord;
    }

    public String getStrangewordExplanation() {
        return StrangewordExplanation;
    }

    public void setStrangewordExplanation(String strangewordExplanation) {
        StrangewordExplanation = strangewordExplanation;
    }

    public String getStrangewordalphapt() {
        return Strangewordalphapt;
    }

    public void setStrangewordalphapt(String strangewordalphapt) {
        Strangewordalphapt = strangewordalphapt;
    }







}
