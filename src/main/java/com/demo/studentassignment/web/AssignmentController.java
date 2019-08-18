package com.demo.studentassignment.web;

import com.demo.studentassignment.mapper.AssignmentRequestMapper;
import com.demo.studentassignment.model.AssignmentServiceResponse;
import com.demo.studentassignment.model.AssignmentWebRequest;
import com.demo.studentassignment.service.AssignmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/assignments")
public class AssignmentController {

  private final Logger LOGGER = LoggerFactory.getLogger(AssignmentController.class);

  @Autowired private AssignmentService assignmentService;

  @GetMapping("/{id}")
  public ResponseEntity<AssignmentServiceResponse> getAssignmentDetailsById(
      @PathVariable("id") Long id) {
    LOGGER.debug("Inside getStudent Method");
    return new ResponseEntity<AssignmentServiceResponse>(
        assignmentService.getAssignmentDetailsById(id), HttpStatus.OK);
  }

  @PostMapping("/create")
  public HttpStatus createAssignment(@RequestBody AssignmentWebRequest assignmentWebRequest) {
    LOGGER.debug("Inside createAssignment Method");
    assignmentService.createAssignment(
        AssignmentRequestMapper.createServiceRequest.apply(assignmentWebRequest));
    return HttpStatus.OK;
  }

  @PutMapping("/update/{id}")
  public HttpStatus updateAssignment(@PathVariable (value = "id") Long assignmentId, @RequestBody AssignmentWebRequest assignmentWebRequest) {
    LOGGER.debug("Inside createAssignment Method");
    assignmentService.updateAssignment(AssignmentRequestMapper.updateServiceRequest.apply(assignmentWebRequest), assignmentId);
    return HttpStatus.OK;
  }

  @DeleteMapping("/{id}")
  public HttpStatus deleteAssignment(@PathVariable (value = "id") Long assignmentId) {
    LOGGER.debug("Inside createAssignment Method");
    assignmentService.deleteAssignmentById(assignmentId);
    return HttpStatus.OK;
  }
}
