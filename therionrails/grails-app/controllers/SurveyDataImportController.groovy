
class SurveyDataImportController {

	def index = { redirect(action:importSurveyData,params:params) }

    def importSurveyData = {
    	def surveyInstance = Survey.get( params.id )

    		return [surveyInstance : surveyInstance]
    }

	def save = {
	    
	    def surveyInstance = Survey.get( params.id )
	    def rawData = params.data
    	
    	if(!rawData || !surveyInstance){
    		redirect(action:importSurveyData)
    	}
    	else{	
 			//try to import...
			String[] lines = rawData.split("\n")
			HashMap stations = new HashMap();
			for(String line: lines){
				line = line.trim();
				if(line.startsWith("#") || line.size()==0){
					continue;
				}
				String[] parts = line.split("\\s+")

				//add station to unique list of stations if not already in there
				if(!stations.containsKey(parts[0])){
					def ss = new SurveyStation(survey:surveyInstance, name:parts[0], note:"", timeMeasured:new Date())
					ss.save()
					stations.put(parts[0], ss)
				}
				if(!stations.containsKey(parts[1])){
					def ss = new SurveyStation(survey:surveyInstance, name:parts[1], note:"", timeMeasured:new Date())
					ss.save();
					stations.put(parts[1], ss)
				}
				
				def connection = new SurveyConnection(survey:surveyInstance, fromStation:stations.get(parts[0]), toStation:stations.get(parts[1]), length:parts[2], compass:parts[3], clino:parts[4], left:parts[5], right:parts[6], up:parts[7], down:parts[8])
				connection.save()
			}

			
			
			
			
 			//on error send back to importSurvey data... with way to write out error
 			
 			//also check for double import... would be bad
 			
 			//if works send here   	
 			redirect(controller:"survey", action:"show", params:[id:surveyInstance.id])
    	}
	}

}