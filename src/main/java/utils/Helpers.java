package utils;

import com.google.common.collect.ImmutableList;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Helpers {
    public AndroidDriver driver;
    public Helpers(AndroidDriver driver){
        this.driver = driver;
        System.out.println("parent");
    }

    public  void waitForVisibility(By by) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
    }

    public  void elementClick(By by){
        driver.findElement(by).click();
    }

    public void waitAndClick(By by){
        waitForVisibility(by);
        elementClick(by);
    }
    public void enterDataForElement(By by,String text){
        driver.findElement(by).sendKeys(text);
    }

    public void swipe(Point start, Point end, Duration duration) {
        PointerInput input = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence swipe = new Sequence(input, 0);
        swipe.addAction(input.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), start.x, start.y));
        swipe.addAction(input.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(input.createPointerMove(duration, PointerInput.Origin.viewport(), end.x, end.y));
        swipe.addAction(input.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(ImmutableList.of(swipe));
    }

    private void touchScroll(String scrollDirection) {
        Duration SCROLL_DUR = Duration.ofMillis(300);
        Dimension dimension = driver.manage().window().getSize();
        System.out.println("size = " + dimension);
        Point midPoint = new Point((int) (dimension.width * 0.3), (int) (dimension.height * 0.3));
        int bottom = midPoint.y + (int) (midPoint.y * .9);
        int top = midPoint.y - (int) (midPoint.y * .3);
        int left = midPoint.x - (int) (midPoint.x * .1);
        int right = midPoint.x + (int) (midPoint.x * .3);

        switch (scrollDirection.toUpperCase()) {
            case "UP":
                swipe(new Point(midPoint.x, top), new Point(midPoint.x, bottom), SCROLL_DUR);
                break;
            case "DOWN":
                swipe(new Point(midPoint.x, bottom), new Point(midPoint.x, top), SCROLL_DUR);
                break;
            case "RIGHT":
                swipe(new Point(right, midPoint.y), new Point(left, midPoint.y), SCROLL_DUR);
                break;
            case "LEFT":
                swipe(new Point(left, midPoint.y), new Point(right, midPoint.y), SCROLL_DUR);
                break;
            default:
                throw new IllegalArgumentException(
                        "Incorrect scroll direction given: Direction must be [up], [down], [left], or [right]");
        }

    }

    public void androidScrollToElement(By by, String direction) {
        int count = 0;
        boolean isDisplayed = false;

        while (!isDisplayed && count++ < 30) {
            try {
                isDisplayed = driver.findElement(by).isDisplayed();
            } catch (NoSuchElementException | AssertionError | IndexOutOfBoundsException e) {
                touchScroll(direction);
            }
        }
    }
}
