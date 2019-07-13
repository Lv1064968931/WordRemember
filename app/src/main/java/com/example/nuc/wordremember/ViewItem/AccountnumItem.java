package com.example.nuc.wordremember.ViewItem;

public class AccountnumItem {

    private int accountImag_id;
    private String accountNumber;

    public AccountnumItem(int accountImag_id, String accountNumber) {
        this.accountImag_id = accountImag_id;
        this.accountNumber = accountNumber;
    }

    public int getAccountImag_id() {
        return accountImag_id;
    }

    public void setAccountImag_id(int accountImag_id) {
        this.accountImag_id = accountImag_id;
    }

    public String getAccountNumber() {
        return  accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

}
