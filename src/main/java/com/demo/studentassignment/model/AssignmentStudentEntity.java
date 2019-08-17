package com.demo.studentassignment.model;

import javax.persistence.*;

@Entity
@Table(name="TBL_ASSIGNMENT_STUDENT")
public class AssignmentStudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="map_id")
    private Long mapId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignment_id", nullable = false)
    private AssignmentEntity assignmentEntity;

    @Column(name="student_id")
    private Long studentId;

    @Column(name="marks")
    private Integer marks;

    public AssignmentEntity getAssignmentEntity() {
        return assignmentEntity;
    }

    public void setAssignmentEntity(AssignmentEntity assignmentEntity) {
        this.assignmentEntity = assignmentEntity;
    }

    public Long getMapId() {
        return mapId;
    }

    public void setMapId(Long mapId) {
        this.mapId = mapId;
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
