package com.demo.studentmanagement.service;

import com.demo.studentassignment.model.AssignmentEntity;
import com.demo.studentassignment.model.AssignmentStudentEntity;
import com.demo.studentassignment.repository.AssignmentRepository;
import com.demo.studentassignment.service.AssignmentServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AssignmentServiceImplTest {

  @Mock private AssignmentRepository assignmentRepository;

  @InjectMocks private AssignmentServiceImpl assignmentService;

  private Optional<AssignmentEntity> assignment;

  private AssignmentEntity assignmentEntity;

  @Before
  public void init() {}

  @Test
  public void getAssignmentDetailsByIdTest() {
    when(assignmentRepository.findById(1L)).thenReturn(createAssignmentDetailsEnity());
    assignmentService.getAssignmentDetailsById(1L);
  }

  private Optional<AssignmentEntity> createAssignmentDetailsEnity() {
    assignmentEntity = new AssignmentEntity();
    assignmentEntity.setAssignmentId(1L);
    assignmentEntity.setAssignmentName("Test");
    AssignmentStudentEntity assignmentStudentEntity;
    Set<AssignmentStudentEntity> assignmentStudentEntities = new HashSet<AssignmentStudentEntity>();
    assignmentStudentEntity = new AssignmentStudentEntity();
    assignmentStudentEntity.setStudentId(306L);
    assignmentStudentEntity.setMarks(90);
    assignmentStudentEntities.add(assignmentStudentEntity);
    assignmentEntity.setAssignments(assignmentStudentEntities);
    assignment = Optional.of(assignmentEntity);
    return assignment;
  }
}
