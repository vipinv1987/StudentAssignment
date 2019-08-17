package com.demo.studentassignment.repo;

import com.demo.studentassignment.model.AssignmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignmentRepo extends JpaRepository<AssignmentEntity,Long> {
}
