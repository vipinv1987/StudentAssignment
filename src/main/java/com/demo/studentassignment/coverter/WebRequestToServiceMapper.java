package com.demo.studentassignment.coverter;

import com.demo.studentassignment.model.AssignmentServiceRequest;
import com.demo.studentassignment.model.AssignmentWebRequest;

import java.util.function.Function;

public class WebRequestToServiceMapper {

  public static Function<AssignmentWebRequest, AssignmentServiceRequest> createServiceRequest =
      new Function<AssignmentWebRequest, AssignmentServiceRequest>() {

        public AssignmentServiceRequest apply(AssignmentWebRequest t) {
          AssignmentServiceRequest assignmentServiceRequest = new AssignmentServiceRequest();
          assignmentServiceRequest.setAssignmentName(t.getAssignmentName());
          assignmentServiceRequest.setStudents(t.getStudents());
          assignmentServiceRequest.setMarks(t.getMarks());
          return assignmentServiceRequest;
        }
      };
}
