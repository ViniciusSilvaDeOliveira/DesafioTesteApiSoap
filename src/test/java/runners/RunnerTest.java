package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "C:\\Users\\tivin\\Desktop\\Estudos\\EstagioBN\\DesafioTesteApiSoap\\src\\test\\resources\\features\\cucumberSoap.feature",
        glue = "",
        tags = "@CT006"
)
public class RunnerTest {
}
