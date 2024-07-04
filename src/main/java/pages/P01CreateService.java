package pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import utils.Helpers;

import java.util.Properties;

public class P01CreateService extends Helpers {
    public P01CreateService(AndroidDriver driver){
        super(driver);
        System.out.println("child");
    }

    //Login Page
    By MailField = By.xpath("//*[@text='Enter your email']");
    By PassField = By.xpath("//*[@text='Password']");
    By LoginBtn = By.xpath("//*[@text='Login']");
    By AllCommunitiesTitle = By.xpath("//*[@text='All Communities']");
    By PlusIcon = By.xpath("(//*[@class='android.widget.ImageView'])[4]");
    By CreateServiceRequest = By.xpath("//*[@text='Create Service Request']");
    By Community = By.xpath("//*[@text='Select community']");
    By TTResidential = By.xpath("//*[@text='TT Residential']");
    By Unit = By.xpath("//*[@text='Select unit']");
    By UnitExample = By.xpath("//*[@text='01010']");
    By Tenant = By.xpath("//*[@text='Select tenant']");
    By AnotherTenant =By.xpath("//*[@text='Another tenant']");
    By FullNameField = By.xpath("//*[@text='Full name']");
    By EmailField = By.xpath("//*[@text='Email']");
    By CountryCodeField = By.xpath("//*[@text='Country Code']");
    By PhoneField = By.xpath("//*[@text='Phone #']");
    By NextBtn = By.xpath("//*[@text='NEXT']");
    By ServiceTitle = By.xpath("//*[@text='Access'");
    By HomeApplicances =By.xpath("//*[@text='Home Appliances']");
    By Provider = By.xpath("//*[@text='TenexFM']");
    By TVitem =By.xpath("//*[@text='Television']");
    By CameraIcon =By.xpath("(//*[@class='android.widget.ImageView'])[3]");
    By ImageFromCamera = By.xpath("//*[@text='Image from Camera']");
    By CameraBtn = By.id("com.sec.android.app.camera:id/normal_center_button");
    By OkBtn = By.xpath("//*[@text='OK']");
    By Image = By.xpath("(//*[@class='android.widget.ImageView'])[3]");
    By MicIcon = By.xpath("(//*[@class='android.widget.ImageView'])[3]");
    By RecordText = By.xpath("//*[@text='Recording... Tap Again to Stop']");
    By TextField = By.xpath("//*[@class='android.widget.EditText']");
    By XcloseRecord = By.xpath("(//*[@class='android.widget.ImageView'])[5]");
    By CreateServiceRequestBtn = By.xpath("//*[@text='CREATE SERVICE REQUEST']");

    public void CheckLoginScreen(String Mail, String Password){
        try{
            waitForVisibility(MailField);
            System.out.println("User need to login");
            Login(Mail,Password);
        }catch (Exception e){
            System.out.println("user already logged in");
        }
    }
    public void Login(String mail , String password){
        waitForVisibility(MailField);
        enterDataForElement(MailField,mail);
        enterDataForElement(PassField,password);
        waitAndClick(LoginBtn);
    }

    public void InitializeServiceCreation (String FullName, String Email, String CountryCode, String Phone){
        waitAndClick(PlusIcon);
        waitAndClick(CreateServiceRequest);
        waitForVisibility(Community);
        elementClick(Community);
        waitAndClick(TTResidential);
        waitAndClick(Unit);
        waitAndClick(UnitExample);
        waitAndClick(Tenant);
        waitAndClick(AnotherTenant);
        waitForVisibility(FullNameField);
        enterDataForElement(FullNameField,FullName);
        enterDataForElement(EmailField,Email);
        enterDataForElement(CountryCodeField,CountryCode);
        enterDataForElement(PhoneField,Phone);
        waitAndClick(NextBtn);
    }

    public void ChooseService () throws InterruptedException {
        Thread.sleep(5000);
        androidScrollToElement(HomeApplicances,"DOWN");
        waitAndClick(HomeApplicances);
    }

    public void SelectProvider (){
        waitAndClick(Provider);
        waitAndClick(TVitem);
        elementClick(NextBtn);
    }

    public void CapturePhoto (){
        waitAndClick(CameraIcon);
        waitAndClick(ImageFromCamera);
        waitAndClick(CameraBtn);
        waitAndClick(OkBtn);
        waitForVisibility(Image);
        elementClick(NextBtn);
    }

    public void WriteCommentorRecord() throws InterruptedException {
        Thread.sleep(3000);
        waitAndClick(MicIcon);
        waitForVisibility(RecordText);
        waitAndClick(MicIcon);
        waitAndClick(XcloseRecord);
        waitForVisibility(TextField);
        enterDataForElement(TextField,"My Comment");
        elementClick(NextBtn);
        waitAndClick(CreateServiceRequestBtn);
    }

}
