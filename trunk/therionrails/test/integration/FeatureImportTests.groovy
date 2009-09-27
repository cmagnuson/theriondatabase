import grails.test.*

class FeatureImportTests extends GrailsUnitTestCase {

	def featureImportService

	protected void setUp() {
		super.setUp();
		featureImportService = new FeatureImportService();
		featureImportService.PATH = "web-app/WEB-INF/features/";
	}
	

    protected void tearDown() {
        super.tearDown()
    }

    void testImport() {
    	featureImportService.importFromFiles();
    }
}
