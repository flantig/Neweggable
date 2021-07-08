package utilities;

import models.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

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
        } catch (TimeoutException e) {
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
    public List<WebElement> allElementsByClass(String className) {
        List<WebElement> elements;
        try {
            elements = driver.findElements(By.className(className));
        } catch (TimeoutException e) {
            return elements = null;
        }
        return elements;
    }

    /**
     * <!!!>Work in Progress<!!!/>
     * <p>
     * The intent is to load in the gmail account used for Newegg to sign in without any browser profile with a saved login at all.
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
     * Using allElementsByClass, construct a function that retrieves product data depending on whether it's a toggleable dropdown like the shuffle page during the live shuffle or a listview of items like regular listings.
     *
     * @param starterClassName The initial ClassName we start off with, this is supposed to make it easier to segment off elements into the individual listing.
     *                         If we got item-title to start it would grab every product and not listing so you'd get repeats making it harder to wrangle.
     * @param innerClassNames The innerclassnames would be individual parts of an item like the product names and prices.
     * @return A matryoshka of lists, the third list exists due to the possibility of bundles. This unfortunately means prices will be stored into a list.
     */
    public List<List<List<String>>> retrieveAllProducts(String starterClassName, String... innerClassNames) {
        List<List<String>> products = new ArrayList<>();
        List<WebElement> starter = allElementsByClass(starterClassName);
        List<List<String>> productTitles;
        List<List<List<String>>> allInfo = new ArrayList<>();

        for(String classname : innerClassNames){
            productTitles = starter
                    .stream()
                    .map(item -> {
                        List<String> possiblyBundled = new ArrayList<>();
                        for (WebElement elem : item.findElements(By.className(classname))) {
                            possiblyBundled.add(elem.getText().replace("COMBO PRICE:", ""));
                        }
                        return possiblyBundled;
                    })
                    .collect(toList());
            allInfo.add(productTitles);
        }


        System.out.println("Break point");

        return allInfo;

    }

}
