package Application.Products;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class ProductsPage {
    private final WebDriver driver;
    public ProductsPage(WebDriver driver) {

        this.driver = driver;
    }
    private final By LabsOnesie = By.xpath("//*[@id=\"item_2_title_link\"]/div");
    public void SelectLabsOnesie() {
        driver.findElement(LabsOnesie).click();
    }

    private final By AddToCart = By.xpath("//*[@id=\"add-to-cart\"]");
    public void ClickAddToCart()  {
        driver.findElement(AddToCart).click();
    }



}