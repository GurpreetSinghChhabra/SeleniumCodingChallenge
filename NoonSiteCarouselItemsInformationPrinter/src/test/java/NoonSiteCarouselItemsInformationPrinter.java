import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;
import java.util.stream.Collectors;

public class NoonSiteCarouselItemsInformationPrinter {

    static WebDriver driver;

    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        String url = "https://www.noon.com/uae-en/";
        driver.get(url);
        //driver.manage().window().maximize();
        printCarouselItemNames("Recommended For You");
        //printCarouselItemsName("Save big on mobiles & tablets");
        driver.quit();
    }

    public static void printCarouselItemNames(String carouselCategory) {

        WebElement nextButton = driver.findElement(By.xpath("//h3[text()='"+ carouselCategory + "']/../../following-sibling::div//div[contains(@class,'swiper-button-next')]"));
        String carouselItems = "//h3[text()='" + carouselCategory + "']/parent::div/parent::div/following-sibling::div//a[contains(@id,'productBox')]//div[@data-qa='product-name']/div";
        boolean isNextPresent= nextButton.isDisplayed();

        do{
            List<WebElement> listCarouselItems = driver.findElements(By.xpath(carouselItems));
            List<WebElement> displayedCarouselItems = listCarouselItems.stream().filter(e -> e.isDisplayed()).collect(Collectors.toList());
            displayedCarouselItems.forEach(itemName -> System.out.println(itemName.getText().replace("\\n","")));

            if (nextButton.isDisplayed()){
                nextButton.click();
            }
            else{
                isNextPresent=false;
            }

        }while(isNextPresent);
    }
}