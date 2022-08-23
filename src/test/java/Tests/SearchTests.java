package Tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factory.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

public class SearchTests extends CoreTestCase {

    @Test
    @Feature(value ="Search")
    @DisplayName("Search for '{search_line}'")
    @Description("We search for '{search_line}' and making sure '{substring}' is presented in search results")
    @Step("Starting test testSearch")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testSearch()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");

    }

    @Test
    @Feature(value ="Search")
    @DisplayName("Cancel search")
    @Description("We start search and then cancel search")
    @Step("Starting test testCancelSearch")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testCancelSearch()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    @Feature(value ="Search")
    @DisplayName("Amount of search results")
    @Description("We count amount of search results for '{search_line}' text to make sure results are presented")
    @Step("Starting test testAmountOfNotEmptySearch")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testAmountOfNotEmptySearch() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        String search_line = "Linkin Park discography";
        SearchPageObject.typeSearchLine(search_line);
        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();

        Assert.assertTrue(
                "We found too few results",
                amount_of_search_results > 0
        );
    }

    @Test
    @Feature(value ="Search")
    @DisplayName("Amount of search results")
    @Description("We count amount of search results for '{search_line}' text to make sure that NO results are presented")
    @Step("Starting test testAmountOfEmptySearch")
    @Severity(value = SeverityLevel.NORMAL)
    public void testAmountOfEmptySearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        String search_line = "zaxscdvfbg";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForEmptyResultsLabel();
        SearchPageObject.assertThereIsNoResultOfSearch();
    }


}
