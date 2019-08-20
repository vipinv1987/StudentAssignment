package com.demo.studentassignment.service;

import com.demo.studentassignment.exception.ResourceNotFoundException;
import com.demo.studentassignment.model.AssignmentEntity;
import com.demo.studentassignment.model.AssignmentStudentEntity;
import com.demo.studentassignment.model.AssignmentVoRequest;
import com.demo.studentassignment.model.Student;
import com.demo.studentassignment.repository.AssignmentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AssignmentServiceImplTest {

  @Mock private AssignmentRepository assignmentRepository;

  @InjectMocks private AssignmentServiceImpl assignmentService;

  private Optional<AssignmentEntity> assignment;

  private AssignmentEntity assignmentEntity;

  @Test
  public void getAssignmentDetailsByIdTest() {
    when(assignmentRepository.findById(1L)).thenReturn(createAssignmentDetailsEnity());
    assignmentService.getAssignmentDetailsById(1L);
  }

  @Test
  public void createAssignmentTest(){
    when(assignmentRepository.save(any(AssignmentEntity.class))).thenReturn(new AssignmentEntity());
    assignmentService.createAssignment(createAssignmentVoRequest());
  }

  @Test
  public void updateAssignmentTest(){
    when(assignmentRepository.findById(anyLong())).thenReturn(createAssignmentDetailsEnity());
    when(assignmentRepository.save(any(AssignmentEntity.class))).thenReturn(new AssignmentEntity());
    assignmentService.updateAssignment(updateAssignmentVoRequest(),1L);
  }

  @Test(expected = ResourceNotFoundException.class)
  public void updateAssignmentWithExceptionTest(){
    when(assignmentRepository.findById(anyLong())).thenReturn(Optional.empty());
    assignmentService.updateAssignment(updateAssignmentVoRequest(),1L);
  }

  @Test
  public void deleteAssignmentByIdTest(){
    when(assignmentRepository.findById(anyLong())).thenReturn(createAssignmentDetailsEnity());
    doNothing().when(assignmentRepository).delete(any(AssignmentEntity.class));
    assignmentService.deleteAssignmentById(1L);

  }

  private AssignmentVoRequest createAssignmentVoRequest(){
    AssignmentVoRequest assignmentVoRequest =new AssignmentVoRequest();
    assignmentVoRequest.setAssignmentName("TEST");
    ArrayList<Student> students = new ArrayList<Student>();
    students.add(new Student(123L,80));
    assignmentVoRequest.setStudents(students);
    return assignmentVoRequest;
  }

  private AssignmentVoRequest updateAssignmentVoRequest(){
    AssignmentVoRequest assignmentVoRequest =new AssignmentVoRequest();
    assignmentVoRequest.setAssignmentName("TEST");
    ArrayList<Student> students = new ArrayList<Student>();
    students.add(new Student(123L,80));
    assignmentVoRequest.setStudents(students);
    return assignmentVoRequest;
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
