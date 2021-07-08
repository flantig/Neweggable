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

public class Runnable {
    public static Setupinator setup;
    public Selectinator utils;
    public static WebDriverWait wait;

    @BeforeClass
    public static void init() throws IOException {
        setup = new Setupinator();
    }

    /**
     * General purpose test for running odd functions, more specific tests should be placed below this one.
     *
     * @throws IOException
     */
    @Test
    public void run() throws IOException {
        wait = new WebDriverWait(setup.driver, Duration.ofSeconds(2));
        Shuffleable shuffle = new Shuffleable(setup.driver, wait);
        utils = new Selectinator(setup.driver, wait);
        List<WebElement> thing = utils.allElementsByClass("lottery-item-cell");
        List<WebElement> singleThing = thing.get(1).findElements(By.className("item-title"));
        //System.out.println(thing.get(1).getText());
        //System.out.println(thing.size());
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
        wait = new WebDriverWait(setup.driver, Duration.ofSeconds(2));
        Shuffleable shuffle = new Shuffleable(setup.driver, wait);
        utils = new Selectinator(setup.driver, wait);
        utils.retrieveAllProducts("lottery-item-cell", "item-title", "lottery-price");
        setup.driver.close();
    }
}
