package com.chuwa.tutorial.t01_basic;

public enum Grades  {
    A("Excellent", 90, 100),
    B("Good", 80, 89),
    C("Average", 70, 79),
    D("Poor", 60, 69);

    private String description;
    private int minScore;
    private int maxScore;

    Grades(String description, int minScore, int maxScore) {
        this.description = description;
        this.minScore = minScore;
        this.maxScore = maxScore;
    }

    public String getDescription() {
        return description;
    }

    public int getMinScore() {
        return minScore;
    }

    public int getMaxScore() {
        return maxScore;
    }
    public static Grades fromScore(int score) {
        for (Grades grade : Grades.values()) {
            if (score >= grade.minScore && score <= grade.maxScore) {
                return grade;
            }
        }
        throw new IllegalArgumentException("Score out of range: " + score);
    }
}
