package lib.ui;

import lib.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class ArticlePageObject extends MainPageObject {

    protected static String

        TITLE,
        FOOTER_ELEMENT,
        OPTIONS_BUTTON,
        OPTIONS_ADD_TO_MY_LIST_BUTTON,
        OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
        ADD_TO_MY_LIST_OVERLAY,
        MY_LIST_NAME_INPUT,
        MY_LIST_OK_BUTTON,
        MY_LIST_TITLE,
        CLOSE_ARTICLE_BUTTON,
        FIRST_ARTICLE_TITLE,
        THIRD_ARTICLE_TITLE,
        SUBSTRING_TPL,
        ARTICLE_TITLE;

    /*TEMPLATE METHODS*/
    private static String getResultSearchElement(String substring)
    {
        return SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    /*TEMPLATE METHODS*/

    public ArticlePageObject(RemoteWebDriver driver)
    {
        super(driver);
    }

    public WebElement waitForTitleElement()
    {
        return this.waitForElementPresent(TITLE, "Cannot find article title on page", 15);
    }

    public String getArticleTitle()
    {
        WebElement title_element = waitForTitleElement();
        if (Platform.getInstance().isAndroid()){
            return title_element.getAttribute("text");
        } else {
            return title_element.getText();
        }

    }

    public void swipeToFooter()
    {
        if (Platform.getInstance().isAndroid()) {
            this.swipeUpToFindElement(
                    FOOTER_ELEMENT,
                    "Cannot find the end of article",
                    40
            );
        } else {
            this.scrollWebPageTillElementNotVisible(
                    FOOTER_ELEMENT,
                    "Cannot find the end of article",
                    40
            );
        }

    }

    public void addArticleToMyList(String name_of_folder) {

        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Cannot find 'More options' button",
                10
        );

        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find 'Add to reading list' option",
                10
        );

        this.waitForElementAndClick(
                ADD_TO_MY_LIST_OVERLAY,
                "Cannot find 'GOT IT' tip overlay",
                5
        );

        this.waitForElementAndClear(
                MY_LIST_NAME_INPUT,
                "Cannot find input to set name articles folder",
                5
        );


        this.waitForElementAndSendKeys(
                MY_LIST_NAME_INPUT,
                name_of_folder,
                "Cannot enter new name for article folder",
                5
        );

        this.waitForElementAndClick(
                MY_LIST_OK_BUTTON,
                "Cannot press 'OK' button to save name of this list ",
                5
        );
    }

    public void addNextArticleToMyList(String name_of_folder) {

        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Cannot find 'More options' button",
                10
        );

        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find 'Add to reading list' option",
                10
        );


        this.waitForElementAndClick(
                MY_LIST_TITLE,
                "Cannot find My list - '" + name_of_folder + "' folder",
                5
        );

    }


    public void closeArticle()
    {
        if (Platform.getInstance().isAndroid()) {
            this.waitForElementAndClick(
                    CLOSE_ARTICLE_BUTTON,
                    "Cannot close article, cannot find X button",
                    5 );
        } else{
            System.out.println("Method closeArticle() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }



        this.waitForElementNotPresent(
                CLOSE_ARTICLE_BUTTON,
                "Cannot close article, X button is still there",
                5
        );
    }

    public void assertArticleTitleHasTextById (String expected_article_title)
    {
        this.assertElementHasText(
                FIRST_ARTICLE_TITLE,
                expected_article_title,
                "Article title does not equal to expected title"
        );
    }

    public void assertArticleTitleHasTextByXpath (String expected_article_title)
    {
        this.assertElementHasText(
                THIRD_ARTICLE_TITLE,
                expected_article_title,
                "Third article title does not equal to expected title"
        );
    }

    public void waitForTitleToDisappear()
    {
        this.waitForElementNotPresent(
                FIRST_ARTICLE_TITLE,
                "Title supposed to be not presented",
                5
                );

    }

    public void waitForArticleWithSubstringPresent (String article_substring)
    {
        String article_substring_xpath = getResultSearchElement(article_substring);
        this.waitForElementPresent(article_substring_xpath, "Cannot find article by requested substring" + article_substring, 5);
    }

    public void waitForArticleWithSubstringNotPresent (String article_substring)
    {
        String article_substring_xpath = getResultSearchElement(article_substring);
        this.waitForElementNotPresent(article_substring_xpath, "Article with requested substring " +article_substring + "supposed to be not presented ", 5);
    }

    public void assertArticleTitlePresent()
    {
        this.assertElementPresent(
                ARTICLE_TITLE,
                "Page do not have article title"
        );
    }

    public void addArticlesToMySaved() {

        if (Platform.getInstance().isMW()) {
            this.removeFromSavedIfItAdded();
        }

        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find option to add article to reading list",
                5
        );
    }

    public void removeFromSavedIfItAdded (){
        if (this.isElementPresent(OPTIONS_REMOVE_FROM_MY_LIST_BUTTON)) {
            this.waitForElementAndClick(
                    OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
                    "Cannot click button to remove an article from My saved list",
                    1);
            this.waitForElementAndClick(
                    OPTIONS_ADD_TO_MY_LIST_BUTTON,
                    "Cannot find button to add an article to My saved list after removing it from this before",
                    5
                    );
        }
    }





}
