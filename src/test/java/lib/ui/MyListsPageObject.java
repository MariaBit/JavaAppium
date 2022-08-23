package lib.ui;

import io.qameta.allure.Step;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class MyListsPageObject extends MainPageObject{
    protected static String
            FOLDER_BY_NAME_TPL,
            ARTICLE_BY_TITLE_TPL,
            ARTICLE_BY_SUBSTRING_TPL,
            ARTICLE_IN_MY_LIST_XPATH,
            REMOVE_FROM_SAVED_BUTTON;
    private static String getFolderXpathByName(String name_of_folder)
    {
        return FOLDER_BY_NAME_TPL.replace( "{FOLDER_NAME}", name_of_folder);
    }

    private static String getSavedArticleXpathByTitle(String article_title)
    {
        return ARTICLE_BY_TITLE_TPL.replace( "{TITLE}", article_title);
    }

    private static String getSavedArticleXpathBySubstring (String article_substring)
    {
        return ARTICLE_BY_SUBSTRING_TPL.replace( "{SUBSTRING}", article_substring);
    }

    private static String getRemoveButtonByTitle (String article_title)
    {
        return REMOVE_FROM_SAVED_BUTTON.replace( "{TITLE}", article_title);
    }

    public MyListsPageObject(RemoteWebDriver driver){
            super(driver);
        }

    @Step("Opening folder")
    public void openFolderByName(String name_of_folder) {
            if (Platform.getInstance().isAndroid()){
            String folder_name_xpath = getFolderXpathByName(name_of_folder);

            this.waitForElementToRender(folder_name_xpath,
                    "Cannot find folder by name " + name_of_folder,
                    5);

            this.waitForElementAndClick(
                    folder_name_xpath,
                    "Cannot find folder by name " + name_of_folder,
                    5
            );} else {
                System.out.println("Method openFolderByName() does nothing for platform" + Platform.getInstance().getPlatformVar());
            }
    }

    @Step("Waiting until article is presented on the screen")
    public void waitForArticleToAppearByTitle(String article_title)
    {
        String article_xpath = getFolderXpathByName(article_title);
        this.waitForElementPresent(article_xpath, "Cannot find saved article by title", 15);

    }

    @Step("Waiting until article is disappeared")
    public void waitForArticleToDisappearByTitle(String article_title)
    {
        String article_xpath = getFolderXpathByName(article_title);
        this.waitForElementNotPresent(article_xpath, "Saved article is still presented", 15);

    }

    @Step("Swiping article in order to delete it from My list")
        public void swipeByArticleToDelete(String article_title)
        {


            if (Platform.getInstance().isAndroid()) {
                this.waitForArticleToAppearByTitle(article_title);

                String article_xpath = getFolderXpathByName(article_title);
                this.swipeElementToLeft(
                        article_xpath,
                        "Cannot find saved article"
                );
                this.waitForArticleToDisappearByTitle(article_title);
            } else {
                this.waitForElementAndClick(
                        REMOVE_FROM_SAVED_BUTTON,
                        "Cannot click button to remove article from saved.",
                        10
                );
            }
            if (Platform.getInstance().isMW()) {
                driver.navigate().refresh();
            }
            this.waitForArticleToDisappearByTitle(article_title);
        }

    @Step("Opening article in My list")
        public void openArticleInMyList(String article_substring)
    {
        String article_substring_xpath = getSavedArticleXpathBySubstring(article_substring);
        this.waitForElementAndClick(article_substring_xpath, "Cannot find article with title " + article_substring, 5);
    }

    @Step("Clicking on remaining article in My list")
    public void clickRemainingArticle()
    {
        this.waitForElementAndClick(ARTICLE_IN_MY_LIST_XPATH, "Cannot find and click any article in My list ", 5);
    }
}
