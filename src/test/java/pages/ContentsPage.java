package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ContentsPage extends  BasePage{


    @FindBy(css = "#wrapper > #message")
    private WebElement errorVideo;


    @FindBy(css = "#playerMediaStream > iframe")
    private WebElement iframe;

    String urlCategories = "div:nth-child(2) > span:nth-child(2)";

    String urlPrograms = "div:nth-child(2) > span:nth-child(2)";

    String urlContent = "div:nth-child(2) > span:nth-child(2)";

    ArrayList<String> listUrlPrograms;
    ArrayList<String> listUrlContent = new ArrayList<>();

    public ContentsPage(WebDriver driver){
        super(driver);
    }

    public void scroll(JavascriptExecutor js){
        try{
            for(int j=1;j<=5;j++){
                js.executeScript("window.scrollBy(0,3000)");
                Thread.sleep(1000);
            }
        }catch(Exception e){}
    }

    public void runElements(JavascriptExecutor js){
        try{

            int contador = 1;
            scroll(js);
            List<WebElement> videos = getAllElements("a.image-link");
            ArrayList<String> contenidos = new ArrayList<>();

            log.info(videos.size());

            for(WebElement video : videos){
                contenidos.add(video.getAttribute("href"));
            }

            for(String contenido : contenidos){
                goNewPage(contenido);
                Thread.sleep(2000);
                changeIframe(iframe);
                Thread.sleep(3000);
                if(waitElementVisibility(errorVideo)){
                    log.info("ERROR => " + getDriver().getCurrentUrl());
                }else{
                    log.info((contador++) + "- OK");
                }

                Thread.sleep(3000);
                returnIframeDefault();
                Thread.sleep(2000);
            }
        }catch(Exception e){

        }
/*
        try{
            Thread.sleep(3000);
            scroll(js);
            List<WebElement> videos = getAllElements("a.image-link");
            int cantidad_videos = videos.size();

            log.info(cantidad_videos);

            if(cantidad_videos == 30){
                videos.clear();
                scroll(js);
                Thread.sleep(3000);
                videos = getAllElements("a.image-link");
                cantidad_videos = videos.size();
            }

            log.info(cantidad_videos);

            for(int i = 0 ;i < cantidad_videos;i++){
                goNewPage(videos.get(i).getAttribute("href"));
                Thread.sleep(2000);
                changeIframe(iframe);
                Thread.sleep(2000);
                if(waitElementVisibility(errorVideo)){
                    log.info("ERROR => " + getDriver().getCurrentUrl());
                }else{
                    log.info((i+1) + "- OK");
                }

                Thread.sleep(2000);
                returnIframeDefault();
                getDriver().navigate().back();
                Thread.sleep(1000);
                scroll(js);
            }
        }catch(Exception e){
            e.printStackTrace();
        }*/
    }

    public List<WebElement> getCaterogies(){
        List<WebElement>  listCategories = null;
        try{
            Thread.sleep(2000);
            listCategories = getAllElements(urlCategories);
        }catch (Exception e){e.printStackTrace();}
        finally {
            return listCategories;
        }
    }

    public void getUrlPrograms(){
        listUrlPrograms = new ArrayList<>();
        getAllElements(urlPrograms).forEach(element -> listUrlPrograms.add(element.getText()));
    }

    public void readPrograms(){
        getUrlPrograms();
        for(String program : listUrlPrograms){
            log.info("");
            log.info("Programa => " + program);
            goNewPage(program);
            readContent();
        }
    }

    public void getUrlContent(){
        listUrlContent = new ArrayList<>();
        getAllElements(urlContent).forEach(element -> listUrlContent.add(element.getText()));
    }

    public void readContent(){
        try{
            int contadorVideos = 1;
            getUrlContent();
            log.info("Cantidad de videos => " + listUrlContent.size());
            log.info("----------------------------------------------------");
            log.info("");
            for(String content : listUrlContent){
                goNewPage(content);
                Thread.sleep(2000);
                changeIframe(iframe);
                Thread.sleep(3000);
                if(waitElementVisibility(errorVideo)){
                    log.info("ERROR => " + getDriver().getCurrentUrl());
                }else{
                    log.info("Video " + contadorVideos + ": OK");
                }
                ++contadorVideos;
                Thread.sleep(3000);
                returnIframeDefault();
                Thread.sleep(2000);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
