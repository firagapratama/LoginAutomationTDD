package stepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import config.env_target;

import java.time.Duration;

public class Login extends env_target {
    @Given("User is on login page")
    public void userIsOnLoginPage() {
        ChromeOptions option = new ChromeOptions();
        option.addArguments("--remote-allow-origins=*");

        System.setProperty("webdriver.chrome.driver","src\\test\\resources\\drivers\\chromedriver.exe");
        driver = new ChromeDriver(option);
        driver.manage().window().maximize();
        driver.get(baseUrl);

        Duration duration = Duration.ofSeconds(10);
        WebDriverWait wait = new WebDriverWait(driver, duration);
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"btn_submit\"]"))
        );
    }

    @When("^User input (.*) and (.*)$")
    public void userInputUsernameAndPassword(String username, String password) {
        driver.findElement(By.name("user_name")).sendKeys(username);
        driver.findElement(By.name("user_password")).sendKeys(password);
    }

    @And("User click login button")
    public void userClickLoginButton() {
        driver.findElement(By.xpath("//*[@id=\"btn_submit\"]")).click();
    }

    @Then("^User get verify login (.*)$")
    public void userGetVerifyLoginResult(String result) {
        Duration duration = Duration.ofSeconds(10);
        WebDriverWait wait = new WebDriverWait(driver, duration);
        if (result == "Success") {
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"wrapper\"]/div[4]/div/div/div/div/div/h4"))
            ));
        } else if (result == "Failed") {
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/section/div/div/div/div/div/div[2]/div[1]"))
            ));
        }
        driver.quit();
    }
}
