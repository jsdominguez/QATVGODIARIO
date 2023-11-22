package tests;

import driver.DriverBrowser;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.ContentsPage;
import pages.HomePage;
import pages.LoginPage;

import java.util.ArrayList;
import java.util.List;

public class ContentTets{

   ContentsPage contentsPage;
   LoginPage loginPage;
   static DriverBrowser driverBrowser = null;
   HomePage homePage = null;
    Logger log = Logger.getLogger(ContentTets.class);

    @Test
    @Parameters({"urlXML","paramUser","paramPass"})
    public void contentDayly(String urlXML,String paramUser, String paramPass){

        try{

            //configuracion
            BasicConfigurator.resetConfiguration();
            BasicConfigurator.configure();
            driverBrowser = new DriverBrowser("chrome");

            //Abrir tvgo
            homePage = new HomePage(driverBrowser.getDriver());
            driverBrowser.getDriver().manage().window().maximize();
            driverBrowser.getDriver().get("https://tvgo.americatv.com.pe/usuario/login");

            Thread.sleep(2000);

            //ir a la pagina de login
            //homePage = new HomePage(driverBrowser.getDriver());
            //homePage.goPageLogin();

            //setear credenciales
            loginPage =  new LoginPage(driverBrowser.getDriver());
            loginPage.doLogin(paramUser,paramPass);

            Thread.sleep(3000);

            //valida si logro el login
            Assert.assertEquals(loginPage.is_login_failed(), false , "Fail Login");

            Thread.sleep(1000);

            //ir a la pagina sitemap de tvGo
            contentsPage = new ContentsPage(driverBrowser.getDriver());
            contentsPage.goNewPage(urlXML);

            List<WebElement> listCategories = contentsPage.getCaterogies();
            ArrayList<String> listCategoriaToString = new ArrayList<>();
            listCategories.forEach(element -> listCategoriaToString.add(element.getText()));

            int quantity_categories = listCategories.size()-5;

            for(int i = 0; i < quantity_categories;i++){
                String url = listCategoriaToString.get(i);
                log.info("");
                log.info("*********************************************");
                log.info("Caterogia => " + url);
                log.info("*********************************************");
                contentsPage.goNewPage(url);
                contentsPage.readPrograms();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            driverBrowser.getDriver().quit();
            driverBrowser = null;
        }
    }
}
