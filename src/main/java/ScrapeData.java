import java.io.File;
import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
public class ScrapeData {

    public static void main(String[] args) {
        // Create a new instance of the Firefox driver
        // Notice that the remainder of the code relies on the interface,
        // not the implementation.

        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");

        //WebDriver driver = new ChromeDriver();

        ChromeOptions options = new ChromeOptions();
        options.addExtensions(new File("C:\\extension_2_5_4.crx"));
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);

        //start driver in incognito mode
        capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
        capabilities.setCapability("chrome.switches", Arrays.asList("--incognito"));
        ChromeDriver driver = new ChromeDriver(capabilities);
        driver.manage().window().maximize();

        // And now use this to visit Google
        //driver.get("http://denvycom.com/blog/");
        driver.get("https://www.amazon.com/s/ref=sr_pg_2?rh=n%3A7141123011%2Cn%3A7147445011%2Cn%3A12035955011%2Cp_6%3AATVPDKIKX0DER&page=3&bbn=12035955011&hidden-keywords=ORCA&ie=UTF8&qid=1482475994");
        // Alternatively the same thing can be done like this
        // driver.navigate().to("http://www.google.com");

//        int current = 96;
//        JavascriptExecutor jse = (JavascriptExecutor)driver;
//        for (int i = 1; i < 5; i++ ) {
//            try {
//                Thread.sleep(2000);//1000 milliseconds is one second.
//                //jse.executeScript("window.scrollBy(0,1200)", "");
//                //move to the target item on the screen
//                int tmp = (i * 4) + 1;
//                current += tmp;
//                System.out.println( current ) ;
//                WebElement element = driver.findElement(By.xpath("//*[@id=\"result_" +current+ "\"]/div/div[2]/div/a/span"));
//                Actions actions = new Actions(driver);
//                actions.moveToElement(element);
//                // actions.click();
//                actions.perform();
//            } catch (InterruptedException ex) {
//                Thread.currentThread().interrupt();
//            }
//        }

//        for (int i = 96; i <= 143; i++ ) {
//            String rank = "";
//
//            String branch = driver.findElement(By.xpath("//*[@id=\"result_"+ i +"\"]/div/div[2]/div/a/span")).getText();
//            String title = driver.findElement(By.xpath("//*[@id=\"result_"+ i +"\"]/div/div[3]/div[1]/a/h2")).getText();
//            String price = driver.findElement(By.xpath("//*[@id=\"result_"+ i +"\"]/div/div[3]/div[2]/a/span")).getText();
//            if (driver.findElements(By.xpath("//*[@id=\"result_"+ i +"\"]/div/div[4]/div[1]/span")).size() > 0) {
//                rank = driver.findElement(By.xpath("//*[@id=\"result_" + i + "\"]/div/div[4]/div[1]/span")).getText();
//            }
//            String asin = driver.findElement(By.xpath("//*[@id=\"result_"+ i +"\"]/div/div[4]/div[2]/span")).getText();
//
//            System.out.println(branch);
//            System.out.println(title);
//            System.out.println(price);
//            System.out.println(rank);
//            System.out.println(asin);
//
//        }
        String branch =  "MILKYWAY TEE";
        String title = "Is My Bike Okay? T-Shirt Cycling, BMX, Mountain Cyclists";
        String price = "$15.99";
        String rank = "#370,561";
        String asin = "ASIN: B01MTWJSRT";

        System.out.println(branch);
        System.out.println(title);
        System.out.println(Float.parseFloat(price.substring(1, price.length())));
        String saleRank = rank.replace(',','.');
        System.out.println(Float.parseFloat(saleRank.substring(1, saleRank.length())));
        System.out.println(asin);

        //driver.get("https://www.amazon.com/s/ref=sr_pg_2?rh=n%3A7141123011%2Cn%3A7147445011%2Cn%3A12035955011%2Cp_6%3AATVPDKIKX0DER&page=13&bbn=12035955011&hidden-keywords=ORCA&ie=UTF8&qid=1482475994");

        // Find the Denvycom search input element by its name
        WebElement element = driver.findElement(By.id("s"));

        // Enter something to search for
        element.sendKeys("research");

        // Now submit the form. WebDriver will find the form for us from the element
        element.submit();

        // Check the title of the page
        System.out.println("Page title is: " + driver.getTitle());
        // Should see: "All Articles on Denvycom related to the Keyword "Research""

        //Get the title of all posts
        List<WebElement> titles = driver.findElements(By.cssSelector("h2.page-header"));
        List<WebElement> dates = driver.findElements(By.cssSelector("span.entry-date"));
        System.out.println(" =============== Denvycom Articles on Research ================= ");
        for (int j = 0; j < titles.size(); j++) {
            System.out.println( dates.get(j).getText() + "\t - " + titles.get(j).getText() ) ;
        }

        //Close the browser
        driver.quit();

    }

}
