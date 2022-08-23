package Tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factory.ArticlePageObjectFactory;
import lib.ui.factory.MyListsPageObjectFactory;
import lib.ui.factory.NavigationUIFactory;
import lib.ui.factory.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

@Epic("Account settings")
public class MyListsTest extends CoreTestCase {
    private static final String name_of_folder = "Learning programming";
    private static final String
            login = "DummyTest2022",
            password = "Wikidummy951";

    @Test
    @Features(value = {@Feature(value="My list"),@Feature(value="Article")})
    @DisplayName("Add article to My list and delete it")
    @Description("We add article to My list, checking that it was added. Then we delete this article and check that it was deleted.")
    @Step("Starting test testSaveFirstArticleToMyList")
    @Severity(value = SeverityLevel.NORMAL)
    public void testSaveFirstArticleToMyList(){

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title= ArticlePageObject.getArticleTitle();

        if(Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyList(name_of_folder);
        } else {
            ArticlePageObject.addArticlesToMySaved();
        }

        if (Platform.getInstance().isMW()) {
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginData(login,password);
            Auth.submitForm();
            Auth.refreshArticlePageAfterLogIn();

            ArticlePageObject.waitForTitleElement();

            Assert.assertEquals("We are not on the same page after login.",
                    article_title,
                    ArticlePageObject.getArticleTitle()
            );

        }
        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.openNavigation();
        NavigationUI.clickMyLists();

        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);
        MyListsPageObject.openFolderByName(name_of_folder);
        MyListsPageObject.swipeByArticleToDelete(article_title);
    }

    @Test
    @Features(value = {@Feature(value="My list"),@Feature(value="Article")})
    @DisplayName("Add two articles to My list and delete one")
    @Description("We add two articles to My list, checking that they were added. Then we delete one article and check that it was delete successfully.")
    @Step("Starting test testSaveTwoArticlesToMyListAndDeleteOne")
    @Severity(value = SeverityLevel.NORMAL)
    public void testSaveTwoArticlesToMyListAndDeleteOne() {

        // Adding first article
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title= ArticlePageObject.getArticleTitle();

        if(Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyList(name_of_folder);
        } else {
            ArticlePageObject.addArticlesToMySaved();
        }

        if (Platform.getInstance().isMW()) {
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginData(login,password);
            Auth.submitForm();
            Auth.refreshArticlePageAfterLogIn();

            ArticlePageObject.waitForTitleElement();

            Assert.assertEquals("We are not on the same page after login.",
                    article_title,
                    ArticlePageObject.getArticleTitle()
            );

        }
        ArticlePageObject.closeArticle();

        // Adding second article


        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Appium");
        SearchPageObject.clickByArticleWithSubstring("Automation for Apps");

        String article_title2= ArticlePageObject.getArticleTitle();

        ArticlePageObject.waitForTitleElement();

        if(Platform.getInstance().isAndroid()) {
            ArticlePageObject.addNextArticleToMyList(name_of_folder);
        } else {
            ArticlePageObject.addArticlesToMySaved();
        }
        ArticlePageObject.closeArticle();

        // Deleting first article from My list

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.openNavigation();
        NavigationUI.clickMyLists();

        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);
        MyListsPageObject.openFolderByName(name_of_folder);
        MyListsPageObject.swipeByArticleToDelete(article_title);


        // Checking that one article has been deleted and another is still presented and can be opened
        if(Platform.getInstance().isAndroid()) {
            ArticlePageObject.waitForArticleWithSubstringNotPresent("Object-oriented programming language");
            MyListsPageObject.openArticleInMyList("Automation for Apps");
            ArticlePageObject.waitForArticleWithSubstringPresent("Automation for Apps");
        } else {
            MyListsPageObject.clickRemainingArticle();
            ArticlePageObject.waitForTitleElement();

            Assert.assertEquals("Remaining page is not " + article_title2 + "article.",
                    article_title2,
                    ArticlePageObject.getArticleTitle()
            );
        }
    }

}
