import grails.test.*

class FeatureTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSomething() {
		Feature f = new Feature(name:"Test", metapostCode:"test mp code", evalScrapString: "def evalScrap(Object fi){return \"hello\"+fi.rotation;}");
		FeatureInstance fi = new FeatureInstance(rotation:139);
		assertEquals(f.generateScrapString(fi),"hello139")
    }
}
