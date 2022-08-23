package Tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factory.ArticlePageObjectFactory;
import lib.ui.factory.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

@Epic ("Basic app checks")
public class ChangeAppConditionTests extends CoreTestCase {

    @Test
    @Features(value = {@Feature(value="Screen_Orientation"),@Feature(value="Article")})
    @DisplayName("Compare article title before and after rotation")
    @Description("Search '{search-line}' article, open it, rotate screen to landscape and check that title is correct.")
    @Step("Starting test testChangeScreenOrientationOnSearchResults")
    @Severity(value = SeverityLevel.NORMAL)
    public void testChangeScreenOrientationOnSearchResults() {
        if (Platform.getInstance().isMW()) {
            return;
        }

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        String title_before_rotation = ArticlePageObject.getArticleTitle();
        this.rotateScreenLandscape();
        String title_after_rotation = ArticlePageObject.getArticleTitle();

        Assert.assertEquals(
                "Article title have been changed after screen rotation",
                title_before_rotation,
                title_after_rotation
        );

        this.rotateScreenPortrait();
        String title_after_second_rotation = ArticlePageObject.getArticleTitle();

        Assert.assertEquals(
                "Article title have been changed after screen rotation",
                title_before_rotation,
                title_after_second_rotation
        );

    }

    @Test
    @Features(value = {@Feature(value="Screen_Orientation"),@Feature(value="Search")})
    @DisplayName("Compare article with substring '{substring}' before and after background mode")
    @Description("Search '{search-line}' article, get app to background mode for 2 seconds and then check that search results is still there")
    @Step("Starting test testCheckSearchArticleInBackground")
    @Severity(value = SeverityLevel.NORMAL)
    public void testCheckSearchArticleInBackground() {

        if (Platform.getInstance().isMW()) {
            return;
        }
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
        this.backgroudApp(2);
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }

}
