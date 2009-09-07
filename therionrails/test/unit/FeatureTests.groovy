import grails.test.*

class FeatureTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testEvalString() {
		Feature f = new Feature(name:"Test", metapostCode:"test mp code", evalScrapString: "def evalScrap(Object o){return \"hello\";}");
		assertEquals(f.generateScrapString(null),"hello")
    }
}
