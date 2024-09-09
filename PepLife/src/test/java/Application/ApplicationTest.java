package Application;


import Application.Login.LoginPage;
import Application.Products.ProductsPage;
import Application.Utilities.Credential;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.poiji.bind.Poiji;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class ApplicationTest {
    private WebDriver driver;
    ExtentReports reports = new ExtentReports();
    ExtentTest test ;
    ExtentSparkReporter spark;

    @BeforeTest
    @Parameters({"browser", "url"})
    public void setUp(String browser, String url) {


        switch(browser) {

            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            case "chrome":
                //System.setProperty("webdriver.chrome.driver", "C:\\Users\\tumelog\\Desktop\\chromedriver.exe");
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "firefox":

                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
        }
        if(url != null && !url.isEmpty()){
            driver.get(url);
        }
        spark = new ExtentSparkReporter("src/main/java/Reporting/reports.html");
        reports.attachReporter(spark);

    }




    @Test
        @Parameters({"url"})
    public void execute( String url){
        ClassLoader classLoader = ApplicationTest.class.getClassLoader();
        URL resource = classLoader.getResource("Credentials.xlsx");
        assert resource != null;

        List<Credential> credentials = Poiji.fromExcel(new File(resource.getPath()), Credential.class);
        try {

            ProductsPage productsPage = new ProductsPage(driver);

            Thread.sleep(4000);
            driver.manage().window().maximize();

            String pageTitle = driver.getTitle();




            test = reports.createTest("Homepage", "Verify Homepage");


            WebElement SwayName = driver.findElement(By.className("form_group"));
            WebElement credit = driver.findElement(RelativeLocator.with(By.className("login_logo")).above(SwayName));

            String Webname = "Swag Labhhs";
            if(Webname.matches(credit.getText())) {
                test.log(Status.PASS, "Welcome to the home page");
            }else {
                test.log(Status.FAIL, "it FAILED to locate the home page");
            }
                LoginPage loginPage = new LoginPage(driver);
           loginPage.username(credentials.get(0).getUserName());

            loginPage.password(credentials.get(0).getPassword());

            loginPage.clickLoginButton();
            test = reports.createTest("Products", "Verify Products");
            WebElement PR = driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/span"));
            if(PR.getText().equals("Produczzts")){
                test.log(Status.PASS,"it is on the Product page");
            }else{
                test.log(Status.FAIL,"it is not in the Product page");
            }






            String screenshotPath =  ("C:\\Users\\tumelog\\Desktop\\UITestAutomationAssessmentsrc\\main\\java\\Reporting\\Screenshorts");
            Thread.sleep(2000);





        }catch (Exception e) {
            System.out.println(e.getMessage());

        }}


    @AfterMethod
    public void tearDwn() throws IOException {

        reports.flush();
        File html = new File("src/main/java/Reporting/reports.html");
        if(html.exists()&& Desktop.isDesktopSupported()){
            Desktop.getDesktop().browse(html.toURI());
        }else {
            System.out.println("Desktop is not supported");
        }
        driver.quit();
    }
}
