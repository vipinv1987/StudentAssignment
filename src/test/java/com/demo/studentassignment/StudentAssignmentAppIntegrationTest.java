package com.demo.studentassignment;

import com.demo.studentassignment.model.AssignmentEntity;
import com.demo.studentassignment.model.AssignmentStudentEntity;
import com.demo.studentassignment.repository.AssignmentRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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
  public void testStudentDetails() throws Exception {
    when(assignmentRepository.findById(1L)).thenReturn(createAssignmentDetailsEnity());
    ResponseEntity<String> response =
        restTemplate.exchange(
            createURLWithPort("/assignments/1"), HttpMethod.GET, null, String.class);
    Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
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
}
