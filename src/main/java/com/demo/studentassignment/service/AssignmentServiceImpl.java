package com.demo.studentassignment.service;

import com.demo.studentassignment.exception.ResourceNotFoundException;
import com.demo.studentassignment.model.*;
import com.demo.studentassignment.repo.AssignmentRepo;
import com.demo.studentassignment.repo.AssignmentStudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class AssignmentServiceImpl implements AssignmentService {

  @Autowired private AssignmentRepo assignmentRepo;

  @Autowired private AssignmentStudentRepo assignmentStudentRepo;

  @Override
  public AssignmentServiceResponse getAssignmentDetailsById(Long assignmentId) {

    Optional<AssignmentEntity> assignmentEntity = assignmentRepo.findById(assignmentId);
    AssignmentServiceResponse assignmentServiceResponse = null;
    ArrayList<Student> students;
    Student student;
    if (assignmentEntity.isPresent()) {
      assignmentServiceResponse = new AssignmentServiceResponse();
      assignmentServiceResponse.setAssignmentName(assignmentEntity.get().getAssignmentName());
      students = new ArrayList<Student>();
      assignmentEntity
          .get()
          .getAssignments()
          .forEach(
              assignmentStudentEntity ->
                  students.add(
                      new Student(
                          assignmentStudentEntity.getStudentId(),
                          assignmentStudentEntity.getMarks())));
      assignmentServiceResponse.setStudents(students);
    }
    return assignmentServiceResponse;
  }

  @Override
  public void createAssignment(AssignmentServiceRequest assignmentServiceRequest) {
    AssignmentEntity assignmentEntity = new AssignmentEntity();
    assignmentEntity.setAssignmentName(assignmentServiceRequest.getAssignmentName());
    assignmentServiceRequest
        .getStudents()
        .forEach(
            student -> {
              AssignmentStudentEntity assignmentStudentEntity = new AssignmentStudentEntity();
              assignmentStudentEntity.setStudentId(student.getStudentId());
              assignmentStudentEntity.setMarks(student.getMarks());
              assignmentStudentEntity.setAssignmentEntity(assignmentEntity);
              assignmentEntity.getAssignments().add(assignmentStudentEntity);
            });
    assignmentRepo.save(assignmentEntity);
  }

  @Override
  public void updateAssignment(AssignmentServiceRequest assignmentServiceRequest, Long assignmntId) {
    assignmentRepo.findById(assignmntId).map(assignmentEntity -> {
      assignmentEntity.setAssignmentName(assignmentServiceRequest.getAssignmentName());
      return assignmentRepo.save(assignmentEntity);
    }).orElseThrow(()->new ResourceNotFoundException("Assignment " + assignmntId + " Not Found"));
  }

  @Override
  public void deleteAssignmentById(Long assignmntId) {
     assignmentRepo.findById(assignmntId).map(assignmentEntity -> {
      assignmentRepo.delete(assignmentEntity);
      return assignmentEntity;
    }).orElseThrow(() -> new ResourceNotFoundException("Assignment " + assignmntId + " Not Found" ));

  }
}
