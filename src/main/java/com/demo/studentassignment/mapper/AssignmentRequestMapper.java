package com.demo.studentassignment.mapper;

import com.demo.studentassignment.model.AssignmentVoRequest;
import com.demo.studentassignment.model.AssignmentWebRequest;

import java.util.function.Function;

public class AssignmentRequestMapper {

  public static Function<AssignmentWebRequest, AssignmentVoRequest> createServiceRequest =
      new Function<AssignmentWebRequest, AssignmentVoRequest>() {

        public AssignmentVoRequest apply(AssignmentWebRequest t) {
          AssignmentVoRequest assignmentServiceRequest = new AssignmentVoRequest();
          assignmentServiceRequest.setAssignmentName(t.getAssignmentName());
          assignmentServiceRequest.setStudents(t.getStudents());
          assignmentServiceRequest.setMarks(t.getMarks());
          return assignmentServiceRequest;
        }
      };

    public static Function<AssignmentWebRequest, AssignmentVoRequest> updateServiceRequest =
            new Function<AssignmentWebRequest, AssignmentVoRequest>() {

                public AssignmentVoRequest apply(AssignmentWebRequest t) {
                    AssignmentVoRequest assignmentServiceRequest = new AssignmentVoRequest();
                    assignmentServiceRequest.setAssignmentName(t.getAssignmentName());
                    return assignmentServiceRequest;
                }
            };
}
