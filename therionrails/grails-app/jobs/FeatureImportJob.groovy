
class FeatureImportJob {
	def featureImportService
	def concurrent = false

	def startDelay = 60000
	def timeout = 30000 
  
    def group = "MyGroup"
  
    def execute() {
    	log.info "Importing Features From Disk"
    	try{
			featureImportService.importFromFiles()
		}
		catch(Exception e){
			log.error("Error feature import", e)
		}
		log.info "Feature Import Completed"
    }
}
