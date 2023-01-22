package api;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import model.AuthToken;
import model.Status;
import model.Ticket;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.util.UUID;

import static io.restassured.RestAssured.given;

public abstract class BaseTest {
    @BeforeClass
    public void prepare() {
        try {
            System.getProperties().load(ClassLoader.getSystemResourceAsStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String baseUri = System.getProperty("base.uri");
        if (baseUri == null || baseUri.isEmpty()) {
            throw new RuntimeException("В файле \"config.properties\" отсутствует значение \"base.uri\"");
        }

        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();

        RestAssured.filters(new ResponseLoggingFilter());
    }

    protected AuthToken login() {
        AuthToken authToken = new AuthToken();
        authToken.setUsername(System.getProperty("base.username"));
        authToken.setPassword(System.getProperty("base.password"));

        return given()
                .body(authToken)
                .when()
                .post("/api/login")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .body()
                .as(AuthToken.class);
    }

    protected Ticket buildNewTicket(Status status, int priority) {
        Ticket ticket = new Ticket();
        ticket.setTitle("newTextTitle " + UUID.randomUUID());
        ticket.setQueue(2);
        ticket.setStatus(status.getCode());
        ticket.setPriority(priority);
        ticket.setResolution("Resolution");
        return ticket;
    }

    protected Ticket createTicket(Ticket ticket) {
        return given()
                .body(ticket)
                .when()
                .post("/api/tickets")
                .then()
                .assertThat()
                .statusCode(201)
                .extract()
                .body()
                .as(Ticket.class);
    }
}