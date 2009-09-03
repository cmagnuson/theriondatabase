class SurveyExportController {

	 def therionService

	 def index = { redirect(action:exportSurvey,params:params) }

	 def exportSurvey = {
	 
	 	therionService.exportSurvey()
	 
    	def surveyInstance = Survey.get( params.id )
    		return [surveyInstance : surveyInstance]
    }
}
