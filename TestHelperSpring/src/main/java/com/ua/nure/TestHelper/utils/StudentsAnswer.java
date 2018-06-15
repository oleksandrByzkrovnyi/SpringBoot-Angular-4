package com.ua.nure.TestHelper.utils;

public class StudentsAnswer {
    private String num;
    private String answer;

    public String getNum() {
        return num;
    }

    public StudentsAnswer setNum(String num) {
        this.num = num;
        return this;
    }

    public String getAnswer() {
        return answer;
    }

    public StudentsAnswer setAnswer(String answer) {
        this.answer = answer;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                "num='" + num + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }
}
