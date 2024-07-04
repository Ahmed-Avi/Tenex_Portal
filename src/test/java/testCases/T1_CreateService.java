package testCases;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.P01CreateService;

import java.util.Properties;

import static utils.ReadPropertyFile.readPropertyFile;


public class T1_CreateService extends BaseTest {
    P01CreateService create;
    Properties TestData = readPropertyFile("config/TestData.properties");


    @Test (priority = 1)
    public void validateLogineSuccessfully(){
        create = new P01CreateService(driver);
        create.CheckLoginScreen(TestData.getProperty("Email"),TestData.getProperty("Password"));
    }
    @Test (priority = 2)
    public void CreateServiceAndFillData(){
        create.InitializeServiceCreation(TestData.getProperty("FullName"),TestData.getProperty("ServiceMail"),TestData.getProperty("CountryCode"),TestData.getProperty("Phone"));

    }
    @Test (priority = 3)
    public void ChooseServiceAndProvide() throws InterruptedException {
        create.ChooseService();
        create.SelectProvider();
    }
    @Test (priority = 4)
    public void CaptureAndValidatePhoto(){
        create.CapturePhoto();
    }
    @Test (priority = 5)
    public void CheckRecordAndCommentScreen() throws InterruptedException {
        create.WriteCommentorRecord();
    }
//    @Test (priority = 6)
//    public void
}
