package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class HomePage extends BasePage {

    @FindBy(css = "#redes_lg_tvgo > ul > li.session_nav_out > a")
    private WebElement btnGoPageLogin;

    @FindBy(css = "#redes_lg_tvgo > .user_m .subs-header")
    private WebElement btnCreateAccount;



    public HomePage(WebDriver driver){
        super(driver);
    }

    public void goPageLogin(){
        clickElement(btnGoPageLogin);
    }


    public void clickGoCreateAccount(){
        clickElement(btnCreateAccount);
    }
}
