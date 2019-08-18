package com.demo.studentassignment.service;

import com.demo.studentassignment.exception.ResourceNotFoundException;
import com.demo.studentassignment.model.*;
import com.demo.studentassignment.repository.AssignmentRepository;
import com.demo.studentassignment.repository.AssignmentStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AssignmentServiceImpl implements AssignmentService {

  @Autowired private AssignmentRepository assignmentRepo;

  @Autowired private AssignmentStudentRepository assignmentStudentRepo;

  @Override
  public AssignmentServiceResponse getAssignmentDetailsById(Long assignmentId) {
    return assignmentRepo
        .findById(assignmentId)
        .map(
            assignmentEntity -> {
              AssignmentServiceResponse assignmentServiceResponse = new AssignmentServiceResponse();
              assignmentServiceResponse.setAssignmentName(assignmentEntity.getAssignmentName());
              ArrayList<Student> students = new ArrayList<Student>();
              assignmentEntity
                  .getAssignments()
                  .forEach(
                      assignmentStudentEntity ->
                          students.add(
                              new Student(
                                  assignmentStudentEntity.getStudentId(),
                                  assignmentStudentEntity.getMarks())));
              assignmentServiceResponse.setStudents(students);
              return assignmentServiceResponse;
            })
        .orElseThrow(
            () -> new ResourceNotFoundException("Assignment " + assignmentId + " Not Found"));
  }

  @Override
  public void createAssignment(AssignmentVoRequest assignmentServiceRequest) {
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
  public void updateAssignment(AssignmentVoRequest assignmentServiceRequest, Long assignmntId) {
    assignmentRepo
        .findById(assignmntId)
        .map(
            assignmentEntity -> {
              assignmentEntity.setAssignmentName(assignmentServiceRequest.getAssignmentName());
              return assignmentRepo.save(assignmentEntity);
            })
        .orElseThrow(
            () -> new ResourceNotFoundException("Assignment " + assignmntId + " Not Found"));
  }

  @Override
  public void deleteAssignmentById(Long assignmntId) {
    assignmentRepo
        .findById(assignmntId)
        .map(
            assignmentEntity -> {
              assignmentRepo.delete(assignmentEntity);
              return assignmentEntity;
            })
        .orElseThrow(
            () -> new ResourceNotFoundException("Assignment " + assignmntId + " Not Found"));
  }
}
