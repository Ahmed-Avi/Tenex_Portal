package testCases;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.P02ValidateService;

import java.util.Properties;

import static utils.ReadPropertyFile.readPropertyFile;

public class T2_ValidateService extends BaseTest {
    P02ValidateService create ;
    Properties TestData = readPropertyFile("config/TestData.properties");

    @Test (priority = 1)
    public void CheckMyCard (){
        create = new P02ValidateService(driver);
        create.CheckMyCard(TestData.getProperty("FullName"));
    }

    @Test (priority = 2)
    public void CommentInTheService () throws InterruptedException {
        create = new P02ValidateService(driver);
//        create.SearchForService();
//        create.ClientCommentInService(TestData.getProperty("ClientComment"));
//        create.TeamCommentInService(TestData.getProperty("TeamComment"));
        create.ChangeStatus();
    }
}
