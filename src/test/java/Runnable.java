import cases.Neweggable;
import org.junit.AfterClass;
import org.openqa.selenium.By;
import utilities.Selectinator;
import cases.Shuffleable;
import utilities.Setupinator;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class Runnable {
    public static Setupinator setup;
    public static Selectinator utils;
    public static WebDriverWait wait;

    @BeforeClass
    public static void init() throws IOException {
        setup = new Setupinator();
        wait = new WebDriverWait(setup.driver, Duration.ofSeconds(2));
        utils = new Selectinator(setup.driver, wait);
    }

    /**
     * General purpose test for running odd functions, more specific tests should be placed below this one.
     *
     * @throws IOException
     */
    @Test
    public void run() throws IOException {
        Shuffleable shuffle = new Shuffleable(setup.driver, wait);
        List<WebElement> thing = utils.allElementsByClass("lottery-item-cell");
        List<WebElement> singleThing = thing.get(1).findElements(By.className("item-title"));
        System.out.println(singleThing.get(0).getText());
        System.out.println(singleThing.size());
        System.out.println("debug break here");
    }

    /**
     * Grabs all of the products from the Newegg shuffle before or after the event has opened/closed.
     *
     * @throws IOException
     */
    @Test
    public void testRetrieveAllProducts() throws IOException {
        Shuffleable shuffle = new Shuffleable(setup.driver, wait);
        List<List<List<String>>> products = utils.retrieveAllProducts("lottery-item-cell", "item-title", "lottery-price");
        System.out.println("Beans");
    }

    @Test
    public void testNeweggMain() throws IOException {
        Neweggable eggy = new Neweggable(setup.driver, wait);
        List<WebElement> menus = eggy.hamburgerHelper();

        eggy.openCategory(menus.get(2), "Home Appliances", "Home Appliances", "Refrigerators");
        System.out.println("stop here please");
    }

    @Test
    public void testNeweggAndRetrieveAllProducts() throws IOException {
        Neweggable eggy = new Neweggable(setup.driver, wait);
        List<WebElement> menus = eggy.hamburgerHelper();

        eggy.openCategory(menus.get(2), "TV & Home Theater", "TV & Video", "LED TV");
        List<List<List<String>>> products = utils.retrieveAllProducts("item-cell", "item-title", "price-current", "price-was", "price-ship");
        System.out.println("stop here please");
    }



    @AfterClass
    public static void cleanUp(){
        Setupinator.driver.close();
    }
}
