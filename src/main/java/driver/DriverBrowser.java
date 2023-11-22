package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;


public class DriverBrowser {

    private WebDriver driver;
    private ChromeOptions chromeOptions;
    private String incognito = "--incognito";
    private String disableGpu = "--disable-gpu";
    private String enableAutomation = "enable-automation";

    private String disabledev = "--disable-dev-shm-usage";
    private String sandbox = "--no-sandbox";
    public DriverBrowser(String cadena) {

        switch (cadena) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                //System.setProperty("webdriver.chrome.driver", "/home/jhosep/SiteMaps/src/main/java/driver/chromedriver");
                driver = new ChromeDriver(this.getOptionsChromeDrivers());
                break;
            default:
                break;
        }
    }

    public ChromeOptions getOptionsChromeDrivers(){
        chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        chromeOptions.addArguments("--allowed-ips");
        chromeOptions.addArguments(this.incognito);
        chromeOptions.addArguments(this.enableAutomation);
        chromeOptions.addArguments(this.disabledev);
        chromeOptions.addArguments(this.sandbox);
        chromeOptions.addArguments("--allow-no-sandbox-job");
        chromeOptions.addArguments("--allow-sandbox-debugging");
        chromeOptions.addArguments("--disable-gpu-sandbox");
        chromeOptions.addArguments("--disable-namespace-sandbox");
        chromeOptions.addArguments("--disable-seccomp-filter-sandbox");
        chromeOptions.addArguments("--disable-setuid-sandbox");
        chromeOptions.addArguments("--disable-win32k-lockdown");
        chromeOptions.addArguments("--enable-audio-service-sandbox");
        chromeOptions.addArguments("--no-sandbox-and-elevated");
        chromeOptions.addArguments("--gpu-sandbox-failures-fatal");
        chromeOptions.addArguments("--gpu-sandbox-allow-sysv-shm");
        chromeOptions.setHeadless(true);
        return chromeOptions;
    }



    public WebDriver getDriver() {
        return this.driver;
    }

}
