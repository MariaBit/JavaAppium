package suites;

import Tests.ArticleTests;
import Tests.ChangeAppConditionTests;
import Tests.MyListsTest;
import Tests.SearchTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        ArticleTests.class,
        ChangeAppConditionTests.class,
        MyListsTest.class,
        SearchTests.class
})

public class TestSuites {}
