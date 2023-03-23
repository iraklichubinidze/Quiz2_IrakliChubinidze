import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class WebFormsTest {
//    ChromeOptions co = new ChromeOptions().addArguments("--remote-allow-origins=*");
//    WebDriver driver = new ChromeDriver(co);
    WebDriver driver;
    @BeforeMethod
    public void  open() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    @Test
    public static void main(String[] args) throws InterruptedException {
        ChromeOptions co = new ChromeOptions().addArguments("--remote-allow-origins=*");
        WebDriver driver = new ChromeDriver(co);
        driver.get("http://webdriveruniversity.com/Dropdown-Checkboxes-RadioButtons/index.html");

        // 1
        Select sl = new Select(driver.findElement(By.id("dropdowm-menu-1")));
        sl.selectByVisibleText("Python");
        assert sl.getFirstSelectedOption().getText().equals("Python");

        // 2
        java.util.List<WebElement> checkboxes = driver.findElements(By.cssSelector("input[type='checkbox']"));
        for (WebElement checkbox : checkboxes) {
            if (!checkbox.isSelected()) {
                checkbox.click();
                assert checkbox.isSelected();
            }
        }

        // 3
        WebElement radio_button = driver.findElement(By.cssSelector("input[value='yellow']"));
        radio_button.click();
        assert radio_button.isSelected();

        // 4
        WebElement disabled_option = driver.findElement(By.cssSelector("option[value='orange'][disabled='true']"));
        assert !disabled_option.isEnabled();

        // 5
        driver.get("https://demoqa.com/progress-bar");
        WebElement startButton = driver.findElement(By.id("startStopButton"));
        startButton.click();

        while (true) {
            WebElement progressBar = driver.findElement(By.id("progressBar"));
            if (progressBar.getAttribute("aria-valuenow").equals("50")) {
                System.out.println("50%");
                break;
            }
        }


    }
    @AfterMethod
    public void tearDown() {
        driver.close();
    }
}