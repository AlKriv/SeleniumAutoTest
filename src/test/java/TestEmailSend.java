import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

/*
1.  логинимся в почту
2.  заполняем поля
3.  отправляем письмо
4.  переходим в отправленные проверяем что письмо отправилось
 */
public class TestEmailSend
{
    private WebDriver driver;
    private String mail;
    private String pass;
    private String subj;


    @Before
    public void Before()
    {
        driver=new ChromeDriver();
        mail="SelForTest@gmail.com";
        pass="10#@E(3oMU)y";
        subj="Test Selenium Web driver";
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        driver.get("https://mail.google.com/mail/?tab=wm");
    }

    @Test
    public void Test()
    {
        //login
        driver.findElement(By.cssSelector("input#identifierId")).sendKeys(mail);
        driver.findElement(By.cssSelector("div#identifierNext")).click();

      //  try {
        //    Thread.sleep(5000);
      //  } catch (InterruptedException e) {
      //      e.printStackTrace();
      //  }
        //password
        driver.findElement(By.xpath(".//*[@id=\"password\"]/div/div/div/input")).sendKeys(pass);
        driver.findElement(By.cssSelector("div#passwordNext")).click();
        //новое письмо
        driver.findElement(By.cssSelector("div.T-I.J-J5-Ji.T-I-KE.L3")).click();
        driver.findElement(By.cssSelector(".vO[name='to']")).sendKeys(mail);//кому
        driver.findElement(By.xpath("//input[@name='subjectbox']")).sendKeys(subj);//subject
        driver.findElement(By.xpath("//div[@aria-label='Тело письма']")).sendKeys(subj);//list
        driver.findElement(By.xpath("//table[@role='group']/tbody/tr/td/div/div[2]")).click();//button send
        //check send
        driver.findElement(By.xpath("//a[@title=\"Отправленные\"]")).click();
        List<WebElement> tempList = driver.findElements(By.xpath("//table[@id=':mm']/tbody"));
        for(WebElement el:tempList)
        {
            System.out.println(el);
        }

    }
    @After
    public void After()
    {

    }
}
