package api;

import model.Status;
import model.Ticket;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UpdateTicketTest extends BaseTest {

    @Test
    public void updateTicketTest() {
        Ticket newCreateTicket = buildNewTicket(Status.CLOSED, 2);
        Ticket newClosedTicket = createTicket(newCreateTicket);

        Assert.assertEquals(newCreateTicket, newClosedTicket);

        updateTicketNegative(newClosedTicket);
    }

    private void updateTicketNegative(Ticket ticket) {
        given()
                .header("Authorization", "token " + login().getToken())
                .body(ticket)
                .when()
                .put("/api/tickets/" + ticket.getId() )
                .then()
                .assertThat()
                .statusCode(422);
    }
}