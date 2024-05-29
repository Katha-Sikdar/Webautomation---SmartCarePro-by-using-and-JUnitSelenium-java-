import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

import static org.openqa.selenium.By.cssSelector;

public class AutomateSmartCarePro {
    static WebDriver driver;

    @BeforeAll
    //pre-requisite
    public static void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    public void visitSmartCarePro() throws InterruptedException {
        driver.get("https://staging-scweb.arcapps.org/");
        driver.findElement(By.name("username")).sendKeys("tester");
        driver.findElement(By.name("password")).sendKeys("tester2023!");
        driver.findElements(cssSelector("[type='submit']")).get(0).click();


        List<WebElement> dropdown = driver.findElements(By.tagName("select"));
        dropdown.get(0).sendKeys("Lusaka"); //province name
        dropdown.get(1).sendKeys("Lusaka");//district
        driver.findElements(By.cssSelector("[type='text']")).get(0).click();
        WebElement facility = driver.findElement(By.xpath("//div[contains(text(),\"Dr Watson\")]"));
        driver.findElement(By.xpath("//div[contains(text(),\"Dr Watson\")]")).click();
        driver.findElements(cssSelector("[type='submit']")).get(0).click();

        //Click on NRC and enter 11111111
        WebElement NRC = driver.findElement(By.xpath("//button[contains(text(),\"NRC\")]"));
        driver.findElement(By.xpath("//button[contains(text(),\"NRC\")]")).click();
        driver.findElement(By.tagName("input")).sendKeys("111111111");
        driver.findElements(By.tagName("button")).get(17).click();

        //Click Attend to patient

        WebElement Attend_To_Patient = driver.findElement(By.xpath("//button[contains(text(),'Attend to Patient')]"));
        driver.findElement(By.xpath("//button[contains(text(),'Attend to Patient')]")).click();

        //click Vital

        WebElement Vital = driver.findElement(By.xpath("//body/div[@id='root']/div[2]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/a[1]/div[1]"));
        driver.findElement(By.xpath("//body/div[@id='root']/div[2]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/a[1]/div[1]")).click();

        //Add Vital
        driver.findElements(cssSelector("[type='button']")).get(0).click();

        driver.findElements(cssSelector("[type='submit']")).get(0).click();

        Thread.sleep(2000);

        List<String[]> csvData = CSVReader.readCSV("src/test/resources/Sample Dataset.csv");

        // Read data from CSV file
        // Assuming the CSV file has columns: height, weight etc
        for (String[] record : csvData) {
            // Open the modal
            //WebElement openModalButton = driver.findElement(By.id("openModalButtonId"));
            //openModalButton.click();

            // Wait for the modal to be visible (you may need to adjust the waiting time)
            try {
                Thread.sleep(2000); // 2 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Locate modal form elements

            WebElement WeightField = driver.findElement(By.cssSelector("[type='text']"));
            WebElement LengthField = driver.findElement(By.cssSelector("[type='text']"));
            WebElement TempField = driver.findElement(By.cssSelector("[type='text']"));
            WebElement SystolicField = driver.findElement(By.cssSelector("[type='text']"));
            WebElement diastolicField = driver.findElement(By.name("DiastolicIfUnrecordable"));

            // Populate the form
            WeightField.clear();
            WeightField.sendKeys(record[1]);

            LengthField.clear();
            LengthField.sendKeys(record[2]);

            TempField.clear();
            TempField.sendKeys(record[8]);

            SystolicField.clear();
            SystolicField.sendKeys(record[4]);

            diastolicField.clear();
            diastolicField.sendKeys(record[7]);


            // Submit the form if needed
            WebElement submitButton = driver.findElement(By.id("modalSubmitButton"));
            submitButton.click();

            // Add a delay if necessary to handle form submission
            try {
                Thread.sleep(2000); // 2 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Optionally, handle form reset or navigate back to the form page
            driver.get("https://staging-scweb.arcapps.org/vitals");
        }


            // Close the browser
            driver.quit();
        }
    }



