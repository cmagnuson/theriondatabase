class SurveyExportController {

	 def therionService

	 def index = { redirect(action:exportSurvey,params:params) }

	 def exportSurvey = {
	 
	 	therionService.exportSurvey()
	 	redirect(uri:"/")
    }
}
