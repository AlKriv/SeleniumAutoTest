import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


import java.util.List;
import java.util.concurrent.TimeUnit;


import static org.junit.Assert.assertEquals;

public class TestGoogle
{
    private WebDriver driver;
    private String findSentance;
    //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    @Before
    public void before()
    {
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        driver.get("http://www.google.com/");
        findSentance="Байк";
    }
    @Test
    public void test() throws InterruptedException {

        //вводим в строку поиска запрос
        WebElement findField = driver.findElement(By.cssSelector("div#gs_lc0 input#lst-ib.gsfi"));
        findField.sendKeys(findSentance);
        //убираем выпадающий список
        findField.sendKeys(Keys.TAB);

        //поиск кнопки поиска и нажатие
         driver.findElement(By.cssSelector("div.jsb > center > input[type=\"submit\"]:nth-child(1)")).click();

        //открывается страница с выполненным запросом
        // получаем список элементов
        List<WebElement> findList=driver.findElements(By.cssSelector("div.rc"));

        // перебираем и проверяем на совпадение с поисковым словом
        String temp;
        for(int i=0;i<findList.size();i++)
        {
            temp= findList.get(i).getText().toLowerCase();
            assertEquals(true,temp.contains(findSentance.toLowerCase()));

        }

    }
    @After
    public void after()
    {
        driver.quit();
    }
}
