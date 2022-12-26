import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src\\test\\resources\\features",
        glue = "stepdef",
        monochrome = true,
        plugin = {"pretty","html:target/site/cucumber-report-default","json:target/site/cucumber.json"},
        snippets = SnippetType.CAMELCASE,
        tags = {"@debug"}
)
public class TestRunner {
}
