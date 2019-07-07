package com.example.expergt;

import java.util.Map;

public class QuesionBacnk {


    Map<String,Boolean> tags;
    //private static int[][] mCorrectClick;
    private String textQuestions[] = {
            "1.What were your difficulties during preparation?",
            "2.How much time did you devote to each subject?",
            "3.What did you do in your leisure time during preparation days?",
            "4.How did you manage time for the preparation?",
            "5.Syllabus completion date (from self study)?",
            "6. At exam times how we complete whole syllabus?",
            "7.How important are mock tests ?",
            "8.How many mock tests you have prepared ?",
            "9. How did the mock tests help you in the final exams?",
            "10. How many times full syllabus should be revised?",
            "11.How did you convert theoretical knowledge to application oriented knowledge to crack the exam?",
            "12.What was your last minute strategy for the Gate exam?",
            "13.What was your strategy on the examination day?",
            "14.What is your message for future aspirants?"
    };


    private String multipleChoice[][] = {
            {"Stick on to meet your deadlines",
                    "Revision at regular interval",
                    "Practicing problems that are more frequently get wrong",
                    "All of the above"},
            {"6-7 hrs","8-10 hrs","3-4 hrs","As per your capability"},
            {"Walking outside for short interval relaxing",
                    "Make distance from social media",
                    "Make distance from tv shows",
                    "Do as per your requirement"},
            {"6 hrs (class) + (2-3 hrs) self study",
                    "Ignore classes + Self study","Classes + Ignore self study",
                    "Do as per your requirements"},
            {"Before 3-4 months",
                    "Before 5-6 months",
                    "Complete as fast as possible",
                    "As per your ability"},
            {"After every revision make your notes more precise",
                    "Do part wise part",
                    "Only revise formula",
                    "Only revise short notes"},
            {"Mock tests are very helpful",
                    "Do mock tests like real exam",
                    "Give your best",
                    "All of the above"},
            {"140-150","50-60","20-30","As much as you can"},
            {"Helps in analyzing time","Helps in understanding weak areas",
                    "Providing the speed for calculations",
                    "All of the above"},
            {"12 times or more",
                    "2-3 times",
                    "one complete revision",
                    "As per your ability"},
            {"Have clear knowledge of what you have learnt.",
                    "Correct interpretation of question solve 80%  part of question",
                    "Be clear about your short goal and main goal",
                    "All of the above"},
            {"Revise syllabus completely",
                    "Focus on classroom notes",
                    "Solve previous years question papers",
                    "Above all"},
            {"Start with the subject you are good at",
                    "Depends on ones strength",
                    "Stay motivated ,Give your best and utilize your time",
                    "Be positive in all aspects"},
            {"Stay determined","Do not copy anyones strategy do what suits you",
                    "Try to achieve your short goal with in time",
                    "All of the above"},
    };

    private int mCorrectClick[][] ={
            {0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0},
            {0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0},
            {0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0},
            {0,0,0,0},{0,0,0,0}
    };
    private String mCorrectAnsers[] = {
            "Do as per your requirement",
            "6-7 hrs",
            "Do as per your requirement",
            "6 hrs (class) + (2-3 hrs) self study",
            "Before 3-4 months",
            "After every revision make your notes more precise",
            "All of the above",
            "140-150",
            "All of the above",
            "12 times or more",
            "All of the above",
            "Above all",
            "Be positive in all aspects",
            "All of the above"
//            "2005","Gradle","Spinner","least privileges",
//            "2005","Gradle","Spinner","least privileges"
    };

    public QuesionBacnk() {

    }

    public void quesionBacnk(){

    }

    public QuesionBacnk(int[][] mCorrectClick) {
        this.mCorrectClick = mCorrectClick;
    }

    public QuesionBacnk(String[] textQuestions, String[][] multipleChoice, int[][] mCorrectClick, String[] mCorrectAnsers,Map<String,Boolean> tags) {
        this.textQuestions = textQuestions;
        this.multipleChoice = multipleChoice;
        this.mCorrectClick = mCorrectClick;
        this.mCorrectAnsers = mCorrectAnsers;
    }


    public int getClick(int index, int num) {
        int click = mCorrectClick[index][num-1];
        return click;
    }

    public String[] getTextQuestions() {
        return textQuestions;
    }

    public void setTextQuestions(String[] textQuestions) {
        this.textQuestions = textQuestions;
    }

    public String[][] getMultipleChoice() {
        return multipleChoice;
    }

    public void setMultipleChoice(String[][] multipleChoice) {
        this.multipleChoice = multipleChoice;
    }

    public int[][] getmCorrectClick() {
        return mCorrectClick;
    }

    public void setmCorrectClick(int[][] mCorrectClick) {
        this.mCorrectClick = mCorrectClick;
    }

    public String[] getmCorrectAnsers() {
        return mCorrectAnsers;
    }

    public void setmCorrectAnsers(String[] mCorrectAnsers) {
        this.mCorrectAnsers = mCorrectAnsers;
    }

    public void setClick(int index, int num, int ans) {
        this.mCorrectClick[index][num-1]=ans;

    }

    public Map<String, Boolean> getTags() {
        return tags;
    }

    public void setTags(Map<String, Boolean> tags) {
        this.tags = tags;
    }

    public int getLength(){
        return textQuestions.length;
    }

    public String getQuestion(int a){
        String question = textQuestions[a];
        return question;
    }

    public String getChoice(int index, int num)
    {
        String choice = multipleChoice[index][num-1];
        return choice;
    }

    public String getCorrectAnswer(int a){
        String answer = mCorrectAnsers[a];
        return answer;
    }
}
