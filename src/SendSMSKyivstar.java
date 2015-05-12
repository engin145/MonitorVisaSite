import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class SendSMSKyivstar {
    public void sendSMS(String msg){

        WebDriver driver = new FirefoxDriver();
        //WebDriver driver = new HtmlUnitDriver(BrowserVersion.FIREFOX_24);
        driver.get("https://my.kyivstar.ua");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement elementLogin = driver.findElement(By.id("login"));
        elementLogin.sendKeys("+38067 your phone number");

        WebElement elementPass = driver.findElement(By.id("password"));
        elementPass.sendKeys("your password");

        WebElement elementSub = driver.findElement(By.id("login_submit"));
        elementSub.click();

        driver.get("https://my.kyivstar.ua/tbmb/sendSms/showSmsDialog.do");
        WebElement elementTextSMS = driver.findElement(By.id("textMessageArea"));
        elementTextSMS.sendKeys(msg);
        driver.close();
    }

    public static void main(String[] args){
        SendSMSKyivstar ks = new SendSMSKyivstar();
        ks.sendSMS("Hi");

    }
}
