package com.demo.studentassignment.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="TBL_ASSIGNMENT")
public class AssignmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="assignment_id")
    private Long assignmentId;

    @Column(name="assignment_name")
    private String assignmentName;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "assignmentEntity")
    private Set<AssignmentStudentEntity> assignments = new HashSet<>();

    public Set<AssignmentStudentEntity> getAssignments() {
        return assignments;
    }

    public void setAssignments(Set<AssignmentStudentEntity> assignments) {
        this.assignments = assignments;
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
}
