package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{

    @FindBy(css = "#iniciarSesionPeru > div:nth-child(3) > input")
    private WebElement txtUser;

    @FindBy(css = "#iniciarSesionPeru > div:nth-child(4) > input")
    private WebElement txtPass;

    @FindBy(css = "#iniciarSesionPeru > div.form-group.center > button")
    private WebElement clickDoLogin;

    @FindBy(id = "msg-registro")
    private WebElement loginFallido;


    public LoginPage(WebDriver driver){
        super(driver);
    }

    public void doLogin(String user, String pass){
        setText(txtUser,user);
        setText(txtPass,pass);
        clickElement(clickDoLogin);
    }

    public boolean is_login_failed() {
       return waitElementVisibility(loginFallido);
    }

}
