import grails.test.*

class SurveyExportTests extends GrailsUnitTestCase {

	def therionService

	protected void setUp() {
		super.setUp();
		therionService = new TherionService();
		therionService.PATH = "/tmp/generated/source/";
		therionService.OUTPUT_PATH = "../output/";
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
