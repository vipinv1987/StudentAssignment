package com.demo.studentassignment.mapper;

import com.demo.studentassignment.model.AssignmentServiceRequest;
import com.demo.studentassignment.model.AssignmentWebRequest;

import java.util.function.Function;

public class AssignmentRequestMapper {

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

    public static Function<AssignmentWebRequest, AssignmentServiceRequest> updateServiceRequest =
            new Function<AssignmentWebRequest, AssignmentServiceRequest>() {

                public AssignmentServiceRequest apply(AssignmentWebRequest t) {
                    AssignmentServiceRequest assignmentServiceRequest = new AssignmentServiceRequest();
                    assignmentServiceRequest.setAssignmentName(t.getAssignmentName());
                    return assignmentServiceRequest;
                }
            };
}
