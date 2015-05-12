import com.gargoylesoftware.htmlunit.BrowserVersion;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MonitorWithoutBrowser {
    public static void main(String[] args) throws Exception {

        HtmlUnitDriver driver = new HtmlUnitDriver(BrowserVersion.FIREFOX_24);

        driver.setJavascriptEnabled(true);

        WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
        driver.get("https://www.vfsvisaonline.com/poland-ukraine-appointment/%28S%28sqplzw55ccghqx45ipq2cymg%29%29/AppScheduling/AppWelcome.aspx?P=s2x6znRcBRv7WQQK7h4MTjZiPRbOsXKqJzddYBh3qCA=");

        WebElement element = driver.findElement(By.xpath(".//*[@id='ctl00_plhMain_lnkChkAppmntAvailability']"));
        element.click();

        WebElement selectElemCity = driver.findElement(By.id("ctl00_plhMain_cboVAC"));
        Select selectCity = new Select(selectElemCity);
        selectCity.selectByValue("17");

        webDriverWait.until(new ExpectedCondition<Boolean>(){
            public Boolean apply(WebDriver driver)
            {
                WebElement selectElemTypeVisa = driver.findElement(By.id("ctl00_plhMain_cboVisaCategory"));
                Select selectTypeVisa = new Select(selectElemTypeVisa);
                selectTypeVisa.selectByValue("235");
                return selectTypeVisa.getOptions().size()>1;
            }
        });

        WebElement elementSub;
        WebElement elementMsg;
        String massage;

        do {
            Thread.sleep(10000);
            elementSub = driver.findElement(By.id("ctl00_plhMain_btnSubmit"));
            elementSub.click();
            elementMsg = driver.findElement(By.id("ctl00_plhMain_lblAvailableDateMsg"));
            massage=elementMsg.getText();
            System.out.println(massage);
        } while(massage.equals("Немає доступних дат"));

        //KyivstarTest ks = new KyivstarTest();
        //ks.sendSMS(massage);
    }
}
