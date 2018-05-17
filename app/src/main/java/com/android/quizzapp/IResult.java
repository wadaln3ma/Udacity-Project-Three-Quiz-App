package com.android.quizzapp;


public interface IResult {
    void showResult(int correct, int total);
    String getMessage();
}
