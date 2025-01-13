package pl.edu.pjatk.MPR_Spring_PRJ.integrtionTests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;
import pl.edu.pjatk.MPR_Spring_PRJ.model.School;

import static io.restassured.RestAssured.basePath;
import static org.hamcrest.Matchers.is;

@Sql(scripts = "/insert_school.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) //używamy
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MyIntergrationTest {

    @LocalServerPort
    int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    @Order(1)
    public void getAllReturnsSchools() {
        RestAssured.get(basePath + "/school/all")
                .then()
                .statusCode(200)
                .body("$.size()",is(3)); //dollar - root element
    }

    @Test
    @Order(2)
    public void postSchools() {
        RestAssured.with()
                .body(new School("pop",123))
                .header("Content-Type","application/json")
                .post(basePath + "/school")
                .then()
                .statusCode(201);
    }
}
