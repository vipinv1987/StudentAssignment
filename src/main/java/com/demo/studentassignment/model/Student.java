package com.demo.studentassignment.model;

public class Student {
    private Long studentId;
    private Integer marks;

    public Student(Long studentId, Integer marks) {
        this.studentId = studentId;
        this.marks = marks;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Integer getMarks() {
        return marks;
    }

    public void setMarks(Integer marks) {
        this.marks = marks;
    }
}
