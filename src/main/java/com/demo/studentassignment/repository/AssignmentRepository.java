package com.demo.studentassignment.repository;

import com.demo.studentassignment.model.AssignmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignmentRepository extends JpaRepository<AssignmentEntity,Long> {
}
