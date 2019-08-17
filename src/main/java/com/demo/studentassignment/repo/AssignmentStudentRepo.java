package com.demo.studentassignment.repo;

import com.demo.studentassignment.model.AssignmentStudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignmentStudentRepo extends JpaRepository<AssignmentStudentEntity,Long> {
}
