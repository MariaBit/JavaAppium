package lib.ui.android;

import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidMyListsPageObject extends MyListsPageObject {
    static {
                FOLDER_BY_NAME_TPL = "xpath://*[@text='{FOLDER_NAME}']";
                ARTICLE_BY_TITLE_TPL = "xpath://*[@text='{TITLE}']";
                ARTICLE_BY_SUBSTRING_TPL = "xpath://*[@text='{SUBSTRING}']";
    }
    public AndroidMyListsPageObject (RemoteWebDriver driver)
    {
        super(driver);
    }
}
