package Application.Login;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class LoginPage {
    private final WebDriver driver;
    public By usernameInput = By.id("user-name");
    public  By passwordInput = By.id("password");
    private final By loginButton = By.id("login-button");

    public LoginPage(WebDriver driver) {

        this.driver = driver;
    }
    public static void LoadWebsite(WebDriver driver, String url) {


    }
    public void username( String username) {
        //driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys(username);
        driver.findElement(By.xpath("//*[@id=\"user-name\"]")).sendKeys(username);
    }

    public void password(String password) {
        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(password);
    }

    public void clickLoginButton() {

        driver.findElement(loginButton).click();
    }



        // Other methods as needed for login functionality
}
