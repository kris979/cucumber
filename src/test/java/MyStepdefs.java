import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static com.agisoft.cucumber.model.IsItFriday.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyStepdefs {

    private String today;
    private String actualAnswer;

    @Given("today is {string}")
    public void todayIs(String day) {
        today = day;
    }

    @When("I ask whether it's Friday yet")
    public void i_ask_whether_it_s_Friday_yet() {
        actualAnswer = isItFriday(today);
    }

    @Then("I should be told {string}")
    public void iShouldBeTold(String expectedAnswer) {
        assertEquals(expectedAnswer, actualAnswer);
    }

}
