import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;

/*
1.  логинимся в почту
2.  заполняем поля
3.  отправляем письмо
4.  переходим в отправленные проверяем что письмо отправилось
 */
public class TestEmailSend
{


    private WebDriver driver;
    private  WebDriverWait wait;
    private String mail;
    private String pass;
    private String subj;
    private String bodyMail;


    @Before
    public void Before()
    {
        driver=new ChromeDriver();
        mail="selfortest@gmail.com";
        pass="10#@E(3oMU)y";
        subj="Test Selenium Web driver";
        bodyMail="Test Selenium Web driver Body";
        //driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
        driver.get("https://mail.google.com/mail/?tab=wm");
    }

    @Test
    public void Test()
    {
        //login
        WebElement fieldMail=(new WebDriverWait(driver,30)).until(ExpectedConditions.elementToBeClickable(By.cssSelector("input#identifierId")));
        fieldMail.sendKeys(mail);
        driver.findElement(By.cssSelector("div#identifierNext")).click();

        //password
        WebElement fieldPass=(new WebDriverWait(driver,30)).until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id=\"password\"]/div/div/div/input")));
        fieldPass.sendKeys(pass);
        driver.findElement(By.cssSelector("div#passwordNext")).click();

        //new letter
        WebElement newLetter=(new WebDriverWait(driver,30)).until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.T-I.J-J5-Ji.T-I-KE.L3")));
        newLetter.click();

        //fill fields
        WebElement fieldWhomSend=(new WebDriverWait(driver,30)).until(ExpectedConditions.elementToBeClickable(By.cssSelector(".vO[name='to']")));
        fieldWhomSend.sendKeys(mail);
        driver.findElement(By.xpath("//input[@name='subjectbox']")).sendKeys(subj);//subject
        driver.findElement(By.xpath("//table[@id='undefined']//div[2]/div")).sendKeys(bodyMail);//list
        driver.findElement(By.xpath("//table[@role='group']/tbody/tr/td/div/div[2]")).click();//button send
        //check send


        //click send button
        WebElement sendButton= (new WebDriverWait(driver,30)).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@role='navigation']/div/div/div/div/div[4]/div")));
        sendButton.click();

        try {
            sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement letterFieldSummary=(new WebDriverWait(driver,30)).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@role='main']//tbody/tr")));
        letterFieldSummary.click();


        //wait for load page
        //get data field
        //subject
        String tempSubj=(driver.findElement(By.xpath("//table[@role='presentation']//h2"))).getText();
        //mail
         String tempMail =(driver.findElement(By.xpath("//*[@role='listitem']//span/span"))).getAttribute("email");
       //body letter
        String tempLetter = (driver.findElement(By.xpath("//div[@role='gridcell']/div"))).getText();


        assertEquals(true,tempMail.contains(mail.toLowerCase())&&tempSubj.contains(subj)&&tempLetter.contains(bodyMail));



    }



    @After
    public void After()
    {

        driver.findElement(By.xpath("//*[@id=\":5\"]/div[3]/div[1]/div/div[2]/div[3]/div")).click();

    }


}
