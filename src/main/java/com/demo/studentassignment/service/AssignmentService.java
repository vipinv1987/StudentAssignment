package com.demo.studentassignment.service;

import com.demo.studentassignment.model.AssignmentVoRequest;
import com.demo.studentassignment.model.AssignmentServiceResponse;

public interface AssignmentService {
    public AssignmentServiceResponse getAssignmentDetailsById(Long assignmentId);
    public void createAssignment(AssignmentVoRequest assignmentServiceRequest);
    public void updateAssignment(AssignmentVoRequest assignmentServiceRequest, Long assignmentId);
    public void deleteAssignmentById(Long assignmntId);
}
