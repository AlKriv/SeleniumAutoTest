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
    private String mail;
    private String pass;
    private String subj;
    private String bodyMail;


    @Before
    public void Before()
    {
        driver=new ChromeDriver();
        mail="SelForTest@gmail.com";
        pass="10#@E(3oMU)y";
        subj="Test Selenium Web driver";
        bodyMail="Test Selenium Web driver Body";
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
        driver.findElement(By.xpath("//div[@aria-label='Тело письма']")).sendKeys(bodyMail);//list
        driver.findElement(By.xpath("//table[@role='group']/tbody/tr/td/div/div[2]")).click();//button send
        //check send
        //driver.findElement(By.xpath("//div[@role='navigation']/div/div/div/div/div[4]/div/div/div[2]/span/a")).click();
        driver.get("https://mail.google.com/mail/u/0/?tab=wm#sent");
          try {
            Thread.sleep(5000);
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
        driver.findElement(By.xpath("//div[@role='main']//tbody/tr")).click();
          //subject
        String tempSubj=(driver.findElement(By.xpath("//table[@role='presentation']//h2"))).getText();
        //mail
        String tempMail =(driver.findElement(By.xpath("//span[@role='gridcell']/span[2]"))).getText();
        //body letter
        String tempLetter = (driver.findElement(By.xpath("//div[@role='gridcell']/div"))).getText();


        assertEquals(true,tempMail.contains(mail.toLowerCase())&&tempSubj.contains(subj)&&tempLetter.contains(bodyMail));



    }
    @After
    public void After()
    {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.xpath("//*[@id=\":5\"]/div[3]/div[1]/div/div[2]/div[3]/div")).click();
//  //*[@id=":5"]/div[3]/div[1]/div/div[2]/div[3]/div/div
    }
}
