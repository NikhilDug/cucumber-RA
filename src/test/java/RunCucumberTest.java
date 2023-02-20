
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Test;
@CucumberOptions(
        features = "src/test/features",
        glue = {"cukeSteps"}
)
@Test
public class RunCucumberTest extends AbstractTestNGCucumberTests {
}
