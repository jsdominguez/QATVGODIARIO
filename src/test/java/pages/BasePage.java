package pages;


import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.ArrayList;
import java.util.List;

public class BasePage {

    public Logger log = Logger.getLogger(BasePage.class);
    protected static WebDriver driver;
    protected static WebDriverWait wait;
    private Select select;
    private Actions accion;
    private ArrayList tabs;

    public BasePage(WebDriver pDriver){
        PageFactory.initElements(pDriver,this);
        wait = new WebDriverWait(pDriver,15);
        driver = pDriver;
    }

    public void clickElement(WebElement element){
        getWait().until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public void clickElement(String selector){
        getDriver().findElement(By.cssSelector(selector)).click();
    }

    public int numberRandom(){
        return (int)(Math.random()*100+1);
    }

    public void submitForm(WebElement element){
        element.submit();
    }

    public void setText(WebElement element, String text){
        element.clear();
        element.sendKeys(text);
    }

    public void selectOption(WebElement element, String text){
        select = new Select(element);
        select.selectByVisibleText(text);
    }

    public void selectOption(WebElement element, int index){
        select = new Select(element);
        select.selectByIndex(index);
    }

    public void mouseHoverElementClick(WebElement...elements){
        accion = new Actions(getDriver());
        accion.moveToElement(elements[0]).build().perform();
        accion.moveToElement(elements[1]).build().perform();
        clickElement(elements[1]);
    }

    public boolean waitElementVisibility(WebElement element) {
        boolean encontrado = true;
        try{
            getWait().until(ExpectedConditions.visibilityOf(element));
        }catch(Exception e){
            encontrado = false;
        }
        return encontrado;
    }

    public void waitFor(By Locator){
        getWait().until(ExpectedConditions.visibilityOfElementLocated(Locator));
    }

    public String getElementText(WebElement element) {
        waitElementVisibility(element);
        return element.getText();
    }

    public void returnHomeTVGo(){
        WebElement btnHomeTvGo = getDriver().findElement(By.cssSelector("#panel > nav > div > div.navbar-header.nav_tvgo > h1 > a"));
        clickElement(btnHomeTvGo);
    }

    public void keyPressEnter(WebElement element){
        element.sendKeys(Keys.ENTER);
    }

    public void setCheckBox(WebElement element){
        element.sendKeys(Keys.SPACE);
    }

    public void getCurrentUrl(){
        System.out.println(getDriver().getCurrentUrl());
    }

    public void getTitleWeb(){
        getDriver().getTitle();
    }

    public void goNewPage(String url){
        try{
            getDriver().navigate().to(url);
        }catch(Exception e){e.printStackTrace();}
    }

    public void returnPage(){
        getDriver().navigate().back();
    }

    public void changeTab(int PositionTab){
        tabs = new ArrayList<>(getDriver().getWindowHandles());
        getDriver().switchTo().window(tabs.get(PositionTab).toString());
    }

    public void closeDriver() {
        if (driver != null) {
            log.info("Close Driver");
            driver.quit();
        }
    }

    public void openNewTab(WebElement element){
        String combineKeys = Keys.chord(Keys.CONTROL,Keys.RETURN);
        element.sendKeys(combineKeys);
    }

    public void closeCurrentTab(){
        getDriver().close();
    }

    public WebDriver getDriver() {
        return driver;
    }

    public WebDriverWait getWait() {
        return wait;
    }

    public List<WebElement> getAllElementsProgram(){
        return getDriver().findElements(By.cssSelector(".title > a"));
    }

    public List<WebElement> getAllElements(String selector){
        return getDriver().findElements(By.cssSelector(selector));
    }

    public void changeIframe(WebElement element){
        getDriver().switchTo().frame(0);
    }

    public void returnIframeDefault(){
        getDriver().switchTo().defaultContent();
    }

}
