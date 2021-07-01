package cases;

import Utilities.Selectinator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;

public class Shuffleable {
    String newEggShuffle = "https://www.newegg.com/product-shuffle";
    static Selectinator utils;


    /**
     * Loads up the page for the Newegg Shuffle. Handles before/after shuffle event pop-ups and product listings.
     * Before/After listings do not have a dropdown but when the event is live the dropdown functionality is active and have clickable events.
     *
     * Sometimes the page redirects you to the signin page. It uses the functions in SignInAble to try signing into
     * the account stored within the config file. It doesn't completely sign in as of writing this email but I plan to integrate GMAIL api to fetch the 2-factor authentication
     * pin that is sent to the account upon attempting to sign-in.
     *
     * @param driver ChromeDriver passed in from the BeforeClass function
     * @param wait   A wait timer for webpages that load in dynamically. It may not always be necessary as Selenium attempts to fully load a page before accessing elements.
     *               Use if and only if a selector isn't initially available after loading a page otherwise everything slows down.
     * @throws IOException
     */
    public Shuffleable(ChromeDriver driver, WebDriverWait wait) throws IOException {
        utils = new Selectinator(driver, wait);
        utils.getURL(newEggShuffle);

        if (utils.checkIfClickable("#labeled-input-signEmail"))
            utils.signIn();

        //If we arrive early or late in the shuffle we get a pop-up. We get two possible selectors that can determine this. If it's neither we simply set the element to null and check it later.
        WebElement element = utils.checkIfClickable("#Popup_Later_Visit > div > div > div.modal-header > button") ? driver.findElement(By.cssSelector("#Popup_Later_Visit > div > div > div.modal-header > button")) :
                (utils.checkIfClickable("#Popup_Early_Visit > div > div > div.modal-header > button") ? driver.findElement(By.cssSelector("#Popup_Early_Visit > div > div > div.modal-header > button")) : null);

        if (element != null)
            element.click();
    }


}
