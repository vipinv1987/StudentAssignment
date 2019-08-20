package com.demo.studentassignment;

import com.demo.studentassignment.model.AssignmentEntity;
import com.demo.studentassignment.model.AssignmentStudentEntity;
import com.demo.studentassignment.model.AssignmentWebRequest;
import com.demo.studentassignment.model.Student;
import com.demo.studentassignment.repository.AssignmentRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentAssignmentAppIntegrationTest {

  @LocalServerPort private int port;

  @MockBean private AssignmentRepository assignmentRepository;

  private Optional<AssignmentEntity> assignment;

  private AssignmentEntity assignmentEntity;

  private TestRestTemplate restTemplate;

  private HttpHeaders headers;

  @Before
  public void init() {
    restTemplate = new TestRestTemplate();
    headers = new HttpHeaders();
  }

  @Test
  public void testgetAssignmentDetailsById() throws Exception {
    when(assignmentRepository.findById(1L)).thenReturn(createAssignmentDetailsEnity());
    ResponseEntity<String> response =
        restTemplate.exchange(
            createURLWithPort("/assignments/1"), HttpMethod.GET, null, String.class);
    Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  @Test
  public void testCreateAssignment() throws Exception {
    when(assignmentRepository.save(any(AssignmentEntity.class)))
        .thenReturn(createAssignmentEntity());
    HttpEntity<AssignmentWebRequest> entity =
        new HttpEntity<AssignmentWebRequest>(createAssignmentWebRequest(), headers);
    ResponseEntity<String> response =
        restTemplate.exchange(
            createURLWithPort("/assignments/create"), HttpMethod.POST, entity, String.class);
    Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  @Test
  public void testUpdateAssignment() throws Exception {
    when(assignmentRepository.findById(anyLong())).thenReturn(createAssignmentDetailsEnity());
    when(assignmentRepository.save(any(AssignmentEntity.class)))
        .thenReturn(createAssignmentEntity());
    HttpEntity<AssignmentWebRequest> entity =
        new HttpEntity<AssignmentWebRequest>(createAssignmentWebRequest(), headers);
    ResponseEntity<String> response =
        restTemplate.exchange(
            createURLWithPort("/assignments/update/1"), HttpMethod.PUT, entity, String.class);
    Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  @Test
  public void testDeleteAssignmentById() throws Exception {
    when(assignmentRepository.findById(anyLong())).thenReturn(createAssignmentDetailsEnity());
    doNothing().when(assignmentRepository).delete(any(AssignmentEntity.class));
    ResponseEntity<String> response =
        restTemplate.exchange(
            createURLWithPort("/assignments/1"), HttpMethod.DELETE, null, String.class);
    Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  private AssignmentWebRequest createAssignmentWebRequest() {
    AssignmentWebRequest assignmentWebRequest = new AssignmentWebRequest();
    assignmentWebRequest.setAssignmentName("TEST");
    ArrayList<Student> students = new ArrayList<Student>();
    students.add(new Student(123L, 80));
    assignmentWebRequest.setStudents(students);
    return assignmentWebRequest;
  }

  private String createURLWithPort(String uri) {
    return "http://localhost:" + port + uri;
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

  private AssignmentEntity createAssignmentEntity() {
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
    return assignmentEntity;
  }
}
