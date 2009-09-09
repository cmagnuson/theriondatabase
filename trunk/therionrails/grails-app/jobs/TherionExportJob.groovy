
class TherionExportJob {

	def therionService
	def concurrent = false

	def startDelay = 60000
	def timeout = 300000  
  
    def group = "MyGroup"
  
    def execute() {
    	log.info "Running map compilation"
    	try{
			therionService.exportSurvey()
		}
		catch(Exception e){
			log.error("Error in compilation", e)
		}
		log.info "Map compilation completed"
    }
}
