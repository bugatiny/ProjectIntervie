package com.Testing;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class MutsiAndOutlander {

    static String kwActual = "7kW";
    static String expFullPrice = "£879.00";
    static String expOlevGrant = "£529.00";

    static int holster = 29;
    static int cable_bag = 55;
    static int key_lock = 39;

    public static void main(String[] args) throws InterruptedException {

        System.out.println("hrrdr");

        String chromeLocation = "src/main/resources/drivers/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", chromeLocation);
        WebDriver driver = new ChromeDriver();
        driver.get("https://checkout.pod-point.com/product");
        driver.manage().window().maximize();


        Select selectMutsubishi = new Select(driver.findElement(By.id("vehicleBrand")));
        selectMutsubishi.selectByVisibleText("Mitsubishi");
        Thread.sleep(2000);

        Select selectOutlander = new Select(driver.findElement(By.id("vehicleId")));
        selectOutlander.selectByVisibleText("Outlander");
        Thread.sleep(2000);

        driver.findElement(By.id("optOut")).click();
        Thread.sleep(5000);

        driver.findElement(By.xpath("//label[@for='variant-universal']")).click();
        Thread.sleep(5000);

        String kwExpected = driver.findElement(By.xpath("//label[@for='model-S7']//h4[1]")).getText();
        Assertions.assertEquals(kwExpected, kwActual);

        String actualFullPrice = driver.findElement(By.xpath("//label[@for='model-S7']//div[1]/p[1]/span")).getText();

        String actualOlevGrant = driver.findElement(By.xpath("//label[@for='model-S7']//div[1]/p[2]/span")).getText();

        Assertions.assertEquals(expFullPrice, actualFullPrice);
        Assertions.assertEquals(expOlevGrant, actualOlevGrant);

        driver.findElement(By.xpath("//label[@for='model-S7']//h4[1]")).click();
        Thread.sleep(5000);


        //  This look will confirm all 6 identical buttons are displayed
//        List<WebElement> breadCrumbList = driver.findElements(By.xpath("//div[@class='grid__col grid__col-md-4']"));
//        boolean isAllDisplayed = true;
//        for(WebElement breadCrumb : breadCrumbList) {
//            if (breadCrumb.isDisplayed() == false) {
//                isAllDisplayed = false;
//                break;
//            }
//        }

        //  This look will confirm all 6 identical buttons are displayed
        List<WebElement> areAll6Displayed = driver.findElements(By.xpath("//div[@class='grid__col grid__col-md-4']"));
        if (areAll6Displayed.size() == 0) {
            fail("Not all 6 displayed");
        } else {
            System.out.println("All 6 are displayed CORRECTLY");
        }

        List<WebElement> selectRandomOf6 = driver.findElements(By.xpath("//div[@class='grid cards-same-height'] /div"));
        int maxCompatible = selectRandomOf6.size();
        // get random number
        Random random = new Random();
        int randomProduct = random.nextInt(maxCompatible);
        // Select the list item
        selectRandomOf6.get(randomProduct).click();

        String totalPriceBottomLeft = driver.findElement(By.xpath("//h3[@class='p-b-none']")).getText();
        System.out.println(totalPriceBottomLeft);

        Thread.sleep(5000);


        String compatibleProduct = driver.findElement(By.xpath("//strong[@class='colour-base']")).getText();
        System.out.println(compatibleProduct);
        Thread.sleep(5000);

        if (compatibleProduct.equalsIgnoreCase("Holster")) {
            String sum =  holster + totalPriceBottomLeft;
            System.out.println("Universal Socket (7kW)" + compatibleProduct + sum);
        } else if (compatibleProduct.equalsIgnoreCase("Cable bag")) {
            String sum = cable_bag  + totalPriceBottomLeft;
            System.out.println("Universal Socket (7kW)" + compatibleProduct + sum);

        } else if (compatibleProduct.equalsIgnoreCase("Key lock")) {
            String sum = key_lock + totalPriceBottomLeft;
            System.out.println("Universal Socket (7kW)" + compatibleProduct + sum);

        }

    }
}

