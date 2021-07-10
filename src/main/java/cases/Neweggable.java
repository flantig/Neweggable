package cases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.Selectinator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Neweggable {
    String newEgg = "https://www.newegg.com";
    static Selectinator utils;

    public Neweggable(ChromeDriver driver, WebDriverWait wait) throws IOException {
        utils = new Selectinator(driver, wait);
        utils.getURL(newEgg);
    }

    /**
     * Opens up the hamburger icon and returns back WebElements describing the main newegg store categories.
     *
     * @return List<WebElement>
     */
    public List<WebElement> hamburgerHelper(){
        WebElement hamburger;

        List<WebElement> mainCategories = new ArrayList<>();
        if(utils.checkIfClickable(By.cssSelector("div[class='header2020-hamburger menu-box is-gray-menu']"))){
            hamburger = utils.driver.findElement(By.cssSelector("div[class='header2020-hamburger menu-box is-gray-menu']"));
            hamburger.click();
            hamburger = hamburger.findElement(By.cssSelector("div[class='menu-box-menu']"));
        } else {
            return mainCategories;
        }

        WebElement productContainer = hamburger.findElement(By.cssSelector("div[class='nav-list nav-level-2']"));

        mainCategories = productContainer.findElements(By.className("nav-level-3"));

        return mainCategories;
    }

    public void unwrapHamburger(){
        if(utils.checkIfClickable(By.className("header2020-hamburger")))
            utils.driver.findElement(By.className("header2020-hamburger")).click();
    }

    /**
     * This should make it easy to navigate to a product page based on category. It has a conditional as a fallback in case the item that leads to the link doesn't exist but it should always target the href tag
     *
     * @param mainCategory One of the main categories in the initial hamburger icon list
     * @param subCatergories However many number of subcategories necessary to get to the set of products most described by the parameters passed
     */
    public void openCategory(WebElement mainCategory, String... subCatergories){
        WebElement selector = mainCategory;
        selector.click();

        for(String sub: subCatergories){
            if(utils.checkIfClickable(By.xpath(String.format("//a[text() = '%s']", sub)))){
                selector = selector.findElement(By.xpath(String.format("//a[text() = '%s']", sub)));
                selector.click();
            } else if(utils.checkIfClickable(By.xpath(String.format("//div[text() = '%s']", sub)))) {
                selector = selector.findElement(By.xpath(String.format("//div[text() = '%s']", sub)));
                selector.click();
            }
        }

    }
}
