package com.example.nuc.wordremember.ViewItem;

import java.io.Serializable;

public class WordItem implements Serializable{

    private String word;
    private String word_alphabet;
    private String word_exp;
    private String word_example_sentence;
    private String word_example_sentence_exp;
    private int word_pron;

    public WordItem(String word, String word_alphabet, String word_exp, String word_example_sentence, String word_example_sentence_exp, int word_pron) {
        this.word = word;
        this.word_alphabet = word_alphabet;
        this.word_exp = word_exp;
        this.word_example_sentence = word_example_sentence;
        this.word_example_sentence_exp = word_example_sentence_exp;
        this.word_pron = word_pron;
    }



    public String getWord_example_sentence() {
        return word_example_sentence;
    }

    public void setWord_example_sentence(String word_example_sentence) {
        this.word_example_sentence = word_example_sentence;
    }

    public String getWord_example_sentence_exp() {
        return word_example_sentence_exp;
    }

    public void setWord_example_sentence_exp(String word_example_sentence_exp) {
        this.word_example_sentence_exp = word_example_sentence_exp;
    }


    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getWord_alphabet() {
        return word_alphabet;
    }

    public void setWord_alphabet(String word_alphabet) {
        this.word_alphabet = word_alphabet;
    }

    public String getWord_exp() {
        return word_exp;
    }

    public void setWord_exp(String word_exp) {
        this.word_exp = word_exp;
    }

    public int getWord_pron() {
        return word_pron;
    }

    public void setWord_pron(int word_pron) {
        this.word_pron = word_pron;
    }




}
