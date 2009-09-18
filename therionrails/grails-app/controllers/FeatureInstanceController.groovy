

class FeatureInstanceController {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        [ featureInstanceInstanceList: FeatureInstance.list( params ), featureInstanceInstanceTotal: FeatureInstance.count() ]
    }

    def show = {
        def featureInstanceInstance = FeatureInstance.get( params.id )

        if(!featureInstanceInstance) {
            flash.message = "FeatureInstance not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ featureInstanceInstance : featureInstanceInstance ] }
    }

    def delete = {
        def featureInstanceInstance = FeatureInstance.get( params.id )
        if(featureInstanceInstance) {
            try {
                featureInstanceInstance.delete(flush:true)
                flash.message = "FeatureInstance ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "FeatureInstance ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "FeatureInstance not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def featureInstanceInstance = FeatureInstance.get( params.id )

        if(!featureInstanceInstance) {
            flash.message = "FeatureInstance not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ featureInstanceInstance : featureInstanceInstance ]
        }
    }

    def update = {
        def featureInstanceInstance = FeatureInstance.get( params.id )
        if(featureInstanceInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(featureInstanceInstance.version > version) {
                    
                    featureInstanceInstance.errors.rejectValue("version", "featureInstance.optimistic.locking.failure", "Another user has updated this FeatureInstance while you were editing.")
                    render(view:'edit',model:[featureInstanceInstance:featureInstanceInstance])
                    return
                }
            }
            featureInstanceInstance.properties = params
            if(!featureInstanceInstance.hasErrors() && featureInstanceInstance.save()) {
                flash.message = "FeatureInstance ${params.id} updated"
                redirect(action:show,id:featureInstanceInstance.id)
            }
            else {
                render(view:'edit',model:[featureInstanceInstance:featureInstanceInstance])
            }
        }
        else {
            flash.message = "FeatureInstance not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def create = {
    	def stationId = params['surveyStation']?.id
    	def connectionId = params['surveyConnection']?.id
    	def surveyStation;
    	def surveyConnection;
    	if(stationId!=null){
			surveyStation = SurveyStation.get(Long.valueOf(stationId))
		}
		if(connectionId!=null){
			surveyConnection = SurveyConnection.get(Long.valueOf(connectionId))
		}
		def feature = Feature.get(Long.valueOf(params['featureInstance'].id))
		
        def featureInstanceInstance = new FeatureInstance()
        featureInstanceInstance.properties = params
        featureInstanceInstance.surveyStation = surveyStation
        featureInstanceInstance.surveyConnection = surveyConnection
        featureInstanceInstance.feature = feature
        return ['featureInstanceInstance':featureInstanceInstance]
    }

    def save = {
        def featureInstanceInstance = new FeatureInstance(params)
        if(!featureInstanceInstance.hasErrors() && featureInstanceInstance.save()) {
            flash.message = "FeatureInstance ${featureInstanceInstance.id} created"
            redirect(action:show,id:featureInstanceInstance.id)
        }
        else {
            render(view:'create',model:[featureInstanceInstance:featureInstanceInstance])
        }
    }
}
