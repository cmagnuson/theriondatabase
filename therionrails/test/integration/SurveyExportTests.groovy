import grails.test.*

class SurveyExportTests extends GrailsUnitTestCase {

	def therionService

	protected void setUp() {
		super.setUp();
		therionService = new TherionService();
		therionService.PATH = "web-app/generated/source/";
		therionService.OUTPUT_PATH = "../output/";

		def featureImportService = new FeatureImportService();
		featureImportService.PATH = "web-app/WEB-INF/features/";
		therionService.featureImportService = featureImportService
	}

	protected void tearDown() {
		super.tearDown();
	}

	void testExport() {
		therionService.exportSurvey()
	}
	
//	void testSingleSurveyExport(){
//		for(survey in Survey.findAll()){
//			assert therionService.getSurveyXvi(survey)
//			therionService.assignSurveyCords(survey)
//		}
//	}

}
