package Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.List;

public class Selectinator {
    static ChromeDriver driver;
    static WebDriverWait wait;

    public Selectinator(ChromeDriver driver, WebDriverWait wait) throws IOException {
        Selectinator.driver = driver;
        Selectinator.wait = wait;
    }

    /**
     * A general purpose function to check if a clickable element exists, it likely doesn't exist if it runs past the timeout passed.
     *
     * @param selector A CSS selector that may be clickable
     * @return True if the clickable element exists, false if it doesn't
     */
    public boolean checkIfClickable(String selector) {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(selector)));
        } catch(TimeoutException e){
            return false;
        }
        return true;
    }

    /**
     * The primary intent for this function is to find all of the items/products within a div. Some pages have expandable divs listing out different bundles and this
     * should be able to catch that after a clickable event.
     *
     * @param className We're collecting elements with this class name.
     * @return A list of WebElements retrieved by className.
     */
    public List<WebElement> allElementsByClass(String className){
        List<WebElement> elements;
        try{
            elements = driver.findElements(By.className(className));
        } catch (TimeoutException e){
            return elements = null;
        }
        return elements;
    }

    /**
     * <!!!>Work in Progress<!!!/>
     *
     * The intent is to load in the gmail account used for Newegg to sign in without any browser profile with a saved login at all.
     *
     * @param driver ChromeDriver passed in from the BeforeClass function
     */
    public void signIn() throws IOException {
        WebElement signInBox = driver.findElement(By.cssSelector("#labeled-input-signEmail"));
        WebElement signInButton = driver.findElement(By.cssSelector("#signInSubmit"));
        signInBox.sendKeys(Setupinator.getProperties().getProperty("email"));
        signInButton.click();
    }

    public void getURL(String url) {
        driver.get(url);
    }

    /**
     * TODO: Create a Model for storing product information
     * TODO: Using allElementsByClass, construct a function that retrieves product data depending on whether it's a toggleable dropdown like the shuffle page during the live shuffle or a listview of items like regular listings.
     */

}
