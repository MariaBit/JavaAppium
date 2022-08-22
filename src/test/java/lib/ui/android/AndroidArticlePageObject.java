package lib.ui.android;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidArticlePageObject extends ArticlePageObject {
     static {

         TITLE = "id:org.wikipedia:id/view_page_title_text";
         FOOTER_ELEMENT = "xpath://*[@text='View page in browser']";
         OPTIONS_BUTTON = "xpath://android.widget.ImageView[@content-desc='More options']";
         OPTIONS_ADD_TO_MY_LIST_BUTTON = "xpath://android.widget.TextView[@text='Add to reading list']";
         ADD_TO_MY_LIST_OVERLAY = "id:org.wikipedia:id/onboarding_button";
         MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input";
         MY_LIST_OK_BUTTON = "xpath://*[@text='OK']";
         MY_LIST_TITLE = "id:org.wikipedia:id/item_title";
         CLOSE_ARTICLE_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";
         FIRST_ARTICLE_TITLE = "id:org.wikipedia:id/page_list_item_title";
         THIRD_ARTICLE_TITLE = "xpath://android.widget.LinearLayout[3]/android.widget.LinearLayout/android.widget.TextView[1]";
         SUBSTRING_TPL = "xpath://*[@text='{SUBSTRING}']";
         ARTICLE_TITLE = "id:org.wikipedia:id/view_page_title_text";
     }

    public AndroidArticlePageObject(RemoteWebDriver driver)
    {
        super(driver);
    }

}
