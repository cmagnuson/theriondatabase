
class SurveyDataImportController {

	def therionService
	
	def index = { redirect(action:importSurveyData,params:params) }

	def importSurveyData = {
			def surveyInstance = Survey.get( params.id )

			return [surveyInstance : surveyInstance]
	}

	def save = {

			def surveyInstance = Survey.get( params.id )
			def rawData = params.data
			def video = params.video
			
			if(!rawData || !surveyInstance){
				redirect(action:importSurveyData)
			}
			else{	
				//try to import...
				therionService.importSurvey(surveyInstance, rawData)
			}

			//on error send back to importSurvey data... with way to write out error

			//also check for double import... would be bad

			surveyInstance.video = video.getBytes();
			surveyInstance.save()
			//if works send here   	
			redirect(controller:"survey", action:"show", params:[id:surveyInstance.id])
	}

}