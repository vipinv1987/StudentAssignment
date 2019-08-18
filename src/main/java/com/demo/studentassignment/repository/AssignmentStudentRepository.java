package com.demo.studentassignment.repository;

import com.demo.studentassignment.model.AssignmentStudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignmentStudentRepository extends JpaRepository<AssignmentStudentEntity,Long> {
}

