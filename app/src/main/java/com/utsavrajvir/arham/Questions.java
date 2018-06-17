package com.utsavrajvir.arham;

public class Questions {

    private String q_id,question,optionA,optionB,optionC,optionD,questionAns,q_no,stud_answer;


    public Questions() {

    }

    public Questions(String q_id, String question, String optionA, String optionB, String optionC, String optionD, String questionAns, String q_no) {
        this.q_id = q_id;
        this.question = question;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.questionAns = questionAns;
        this.q_no = q_no;
    }


    public String getStud_answer() {
        return stud_answer;
    }

    public void setStud_answer(String stud_answer) {
        this.stud_answer = stud_answer;
    }

    public String getQ_no() {
        return q_no;
    }

    public void setQ_no(String q_no) {
        this.q_no = q_no;
    }

    public String getQuestionAns() {
        return questionAns;
    }

    public void setQuestionAns(String questionAns) {
        this.questionAns = questionAns;
    }

    public String getQ_id() {
        return q_id;
    }

    public void setQ_id(String q_id) {
        this.q_id = q_id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }
}
