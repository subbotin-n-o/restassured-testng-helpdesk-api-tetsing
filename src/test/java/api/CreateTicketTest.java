package api;

import model.Status;
import model.Ticket;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class CreateTicketTest extends BaseTest {

    @Test
    public void createTicketTest() {
        Ticket newTicket = createTicket(buildNewTicket(Status.OPEN, 2));
        Ticket newTicketGet = getTicket(newTicket.getId());

        Assert.assertEquals(newTicket, newTicketGet);
    }

    protected Ticket getTicket(int id) {
        return given()
                .header("Authorization", "token " + login().getToken())
                .when()
                .get("/api/tickets/" + id)
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .body()
                .as(Ticket.class);
    }
}