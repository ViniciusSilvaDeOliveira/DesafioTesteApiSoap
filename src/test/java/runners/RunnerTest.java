package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "features/cucumberSoap.feature",
        glue = "",
        tags = "@CT001"
)
public class RunnerTest {
}
