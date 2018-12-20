package com.dantas.jscrapper.driverAction;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class ScreenshotDriverAction extends DriverAction {

    private String filePath;
    public String getFilePath() { return this.filePath; }



    public ScreenshotDriverAction(String filePath, String name) {
        super(name);
        this.filePath = filePath;
    }

    public ScreenshotDriverAction(String filePath) {
        this(filePath, "");
    }



    @Override
    public void internalAct(WebDriver driver) throws DriverActionException {
        if(!(driver instanceof TakesScreenshot))
            throw new DriverActionException("Driver of type " + driver.getClass().getName() + " doesn't implement TakeScreenshot!");

        File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(srcFile, new File(this.filePath));
        } catch (IOException e) {
            throw new DriverActionException("Error copying screenshot!", e);
        }
    }

}
