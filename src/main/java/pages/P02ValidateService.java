package pages;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import utils.Helpers;
import org.testng.Assert;


public class P02ValidateService extends Helpers {
    public P02ValidateService (AndroidDriver driver){
        super(driver);
    }

    By FilterBtn = By.xpath("//*[@text='Filters']");
    By ActiveStatus = By.xpath("//*[@text='ACTIVE']");
    By AcceptedBox = By.xpath("//*[@text='Accepted']");
    By HomeAppliance = By.xpath("//*[@text='Home Appliances']");
    By ApplyFiltersBtn = By.xpath("//*[@text='APPLY FILTERS']");
    By SearchBar = By.xpath("//*[@text='Search']");
    By JohnCage = By.xpath("//*[@text='John cage']");
    By CommentBar = By.xpath("//*[@text='Type your comment here...']");
    By SendIcon = By.xpath("(//*[@class='android.widget.ImageView'])[8]");
    By SendToClient = By.xpath("//*[@text='SEND TO CLIENT']");
    By AddStandardComment = By.xpath("//*[@text='Add Standard Response...']");
    By FirstStandardComment = By.xpath("(//*[@class='android.widget.TextView'])[1]");
    By SendCommentIcon = By.xpath("(//*[@class='android.widget.ImageView'])[8]");
    By InternalTeam = By.xpath("//*[@text='Internal Team']");
    By TeamSendIcon = By.xpath("(//*[@class='android.widget.ImageView'])[9]");
    By SendToInternalTeam = By.xpath("//*[@text='SEND TO INTERNAL TEAM']");
    By StatusPage = By.xpath("(//*[@class='android.widget.TextView'])[4]");
    By ApprovedStatus = By.xpath("//*[@text='Approved']");
    By SaveBtn = By.xpath("//*[@text='Save']");


    public void CheckMyCard (String FullName){
        androidScrollToElement(By.xpath("//*[@text='"+FullName+" ']"),"DOWN");
        waitForVisibility(By.xpath("//*[@text='"+FullName+" ']"));
        Assert.assertTrue(driver.findElement(By.xpath("//*[@text='"+FullName+" ']")).isDisplayed());
        elementClick(FilterBtn);
        waitAndClick(ActiveStatus);
        waitAndClick(AcceptedBox);
        androidScrollToElement(HomeAppliance,"DOWN");
        waitAndClick(HomeAppliance);
        waitAndClick(ApplyFiltersBtn);
    }

    public void SearchForService (){
        waitAndClick(SearchBar);
        enterDataForElement(SearchBar,"John");
        driver.executeScript("mobile:performEditorAction", ImmutableMap.of("action", "done"));
        androidScrollToElement(JohnCage,"DOWN");
        waitAndClick(JohnCage);
    }

    public void ClientCommentInService (String ClientComment){
        waitForVisibility(JohnCage);
        androidScrollToElement(CommentBar,"DOWN");
        enterDataForElement(CommentBar,ClientComment);
        waitAndClick(SendIcon);
        waitAndClick(SendToClient);
        Assert.assertTrue(driver.findElement(By.xpath("//*[@text='"+ClientComment+" ']")).isDisplayed());
        androidScrollToElement(AddStandardComment,"DOWN");
        elementClick(AddStandardComment);
        waitForVisibility(FirstStandardComment);
        String StandardCommentText = driver.findElement(FirstStandardComment).getText();
        waitAndClick(FirstStandardComment);
        driver.executeScript("mobile:performEditorAction", ImmutableMap.of("action", "done"));
        waitAndClick(SendCommentIcon);
        waitAndClick(SendToClient);
        Assert.assertTrue(driver.findElement(By.xpath("//*[@text='"+StandardCommentText+" ']")).isDisplayed());

    }

    public void TeamCommentInService (String TeamComment) throws InterruptedException {
        Thread.sleep(5000);
        androidScrollToElement(InternalTeam,"UP");
        elementClick(InternalTeam);
        androidScrollToElement(CommentBar,"DOWN");
        enterDataForElement(CommentBar,TeamComment);
        waitAndClick(TeamSendIcon);
        waitAndClick(SendToInternalTeam);
        Assert.assertTrue(driver.findElement(By.xpath("//*[@text='"+TeamComment+" ']")).isDisplayed());
    }

    public void ChangeStatus (){
        waitAndClick(StatusPage);
        waitAndClick(ApprovedStatus);
        waitAndClick(SaveBtn);
    }
}
