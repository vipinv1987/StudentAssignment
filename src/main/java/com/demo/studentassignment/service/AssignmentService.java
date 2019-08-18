package com.demo.studentassignment.service;

import com.demo.studentassignment.model.AssignmentServiceRequest;
import com.demo.studentassignment.model.AssignmentServiceResponse;
import com.demo.studentassignment.model.AssignmentWebRequest;
import org.springframework.stereotype.Service;

public interface AssignmentService {
    public AssignmentServiceResponse getAssignmentDetailsById(Long assignmentId);
    public void createAssignment(AssignmentServiceRequest assignmentServiceRequest);
    public void updateAssignment(AssignmentServiceRequest assignmentServiceRequest, Long assignmentId);
    public void deleteAssignmentById(Long assignmntId);
}
