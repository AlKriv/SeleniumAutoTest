import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;
import static java.lang.Thread.sleep;


public class TestDelMessage {

    private WebDriver driver;
    private WebDriverWait wait;
    private String mail;
    private String pass;
    private String filter;

    @Before
    public void Before()
    {
        driver=new ChromeDriver();
        mail="selfortest@gmail.com";
        pass="10#@E(3oMU)y";
        filter="before: 2019/01/01";

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }

    @Test
    public void Test() throws InterruptedException {
        driver.get("https://mail.google.com/mail/?tab=wm");
        //login

        WebElement fieldMail  = driver.findElement(By.cssSelector("input#identifierId"));
        //WebElement fieldMail=(new WebDriverWait(driver,30)).until(ExpectedConditions.elementToBeClickable(By.cssSelector("input#identifierId")));
        fieldMail.sendKeys(mail);
        driver.findElement(By.cssSelector("div#identifierNext")).click();

        //password

        WebElement fieldPass=driver.findElement(By.xpath(".//*[@id=\"password\"]/div/div/div/input"));
        //WebElement fieldPass=(new WebDriverWait(driver,30)).until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id=\"password\"]/div/div/div/input")));
        fieldPass.sendKeys(pass);
        driver.findElement(By.cssSelector("div#passwordNext")).click();

        // find field and enter filter message
        WebElement searchField = driver.findElement(By.cssSelector("input.gb_xf"));
        searchField.sendKeys(filter);
        searchField.sendKeys(Keys.ENTER);

        // чтоб фильтр успел отработать
        sleep(5000);


        for(int i=0;i<2;i++) {
            //выделяем письма
            WebElement checkBox = driver.findElement(By.xpath("//div[@id=':7m']//span"));
            checkBox.click();
            sleep(3000);
            WebElement deleteButton = driver.findElement(By.xpath("//div[@data-tooltip='Удалить']"));
            deleteButton.click();
            sleep(3000);
        }

    }



    @After
    public void After()
    {
        //del letter
       // driver.findElement(By.xpath("//*[@id=\":5\"]/div[3]/div[1]/div/div[2]/div[3]/div")).click();
       // driver.quit();
    }

}
