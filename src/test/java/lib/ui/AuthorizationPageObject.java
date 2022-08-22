package lib.ui;

import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AuthorizationPageObject extends MainPageObject {
    private static final String
    LOGIN_BUTTON = "xpath://body/div/div/a[text()='Log in']",
    LOGIN_INPUT ="css:input[name='wpName']",
    PASSWORD_INPUT="css:input[name='wpPassword']",
    SUBMIT_BUTTON ="css:button#wpLoginAttempt",
    RETURN_TO_PREVIOUS_PAGE_BUTTON = "xpath://body//a[text()='Return to the previous page.']";

    public AuthorizationPageObject(RemoteWebDriver driver){
        super(driver);
    }

    public void clickAuthButton(){
        this.waitForElementToRender(LOGIN_BUTTON,"Cannot render auth button", 5);
        this.waitForElementToRender(LOGIN_BUTTON,"Cannot render auth button", 5);
        this.waitForElementToRender(LOGIN_BUTTON,"Cannot render auth button", 5);
        this.waitForElementToRender(LOGIN_BUTTON,"Cannot render auth button", 5);
        this.waitForElementToRender(LOGIN_BUTTON,"Cannot render auth button", 5);
        this.waitForElementToRender(LOGIN_BUTTON,"Cannot render auth button", 5);
        this.waitForElementToRender(LOGIN_BUTTON,"Cannot render auth button", 5);
        this.waitForElementToRender(LOGIN_BUTTON,"Cannot render auth button", 5);
        this.waitForElementToRender(LOGIN_BUTTON,"Cannot render auth button", 5);
        this.waitForElementToRender(LOGIN_BUTTON,"Cannot render auth button", 5);
        this.waitForElementPresent(LOGIN_BUTTON,"Cannot find auth button", 5);
        this.waitForElementAndClick(LOGIN_BUTTON,"Cannot find and click auth button", 5);


    }

    public void enterLoginData(String login, String password) {
        this.waitForElementAndSendKeys(LOGIN_INPUT, login, "Cannot find and put a login to the login input", 5);
        this.waitForElementAndSendKeys(PASSWORD_INPUT, password, "Cannot find and put a password to the password input", 5);
    }

    public void submitForm() {
        this.waitForElementAndClick(SUBMIT_BUTTON, "Cannot find and click submit auth button", 5);
        this.waitForElementAndClick(RETURN_TO_PREVIOUS_PAGE_BUTTON,"Cannot find and click Return to the previous page button", 5);
    }

    public void refreshArticlePageAfterLogIn()
    {
        if (Platform.getInstance().isMW()) {
            driver.navigate().refresh();
        }
    }

}
