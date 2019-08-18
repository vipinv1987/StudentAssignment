package com.demo.studentassignment.model;

import java.util.ArrayList;

public class AssignmentVoRequest {
    private Long assignmentId;
    private String assignmentName;
    private ArrayList<Student> students;
    private Integer marks;
    private Student upDateStudent;

    public Student getUpDateStudent() {
        return upDateStudent;
    }

    public void setUpDateStudent(Student upDateStudent) {
        this.upDateStudent = upDateStudent;
    }

    public Long getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(Long assignmentId) {
        this.assignmentId = assignmentId;
    }

    public String getAssignmentName() {
        return assignmentName;
    }

    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public Integer getMarks() {
        return marks;
    }

    public void setMarks(Integer marks) {
        this.marks = marks;
    }
}
