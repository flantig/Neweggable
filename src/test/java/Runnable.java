import Utilities.Selectinator;
import cases.Shuffleable;
import Utilities.Setupinator;
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
        List<WebElement> thing = utils.allElementsByClass("item-title");
        System.out.println(thing.get(0).getText());
        System.out.println(thing.size());
        System.out.println("debug break here");
    }
}
