

class SurveyConnectionController {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

	def addEquate = {
		redirect(action:addSurveyStation, controller:"equateController", params:params)
	}

    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        [ surveyConnectionInstanceList: SurveyConnection.list( params ), surveyConnectionInstanceTotal: SurveyConnection.count() ]
    }

    def show = {
        def surveyConnectionInstance = SurveyConnection.get( params.id )

        if(!surveyConnectionInstance) {
            flash.message = "SurveyConnection not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ surveyConnectionInstance : surveyConnectionInstance ] }
    }

    def delete = {
        def surveyConnectionInstance = SurveyConnection.get( params.id )
        if(surveyConnectionInstance) {
            try {
                surveyConnectionInstance.delete(flush:true)
                flash.message = "SurveyConnection ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "SurveyConnection ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "SurveyConnection not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def surveyConnectionInstance = SurveyConnection.get( params.id )

        if(!surveyConnectionInstance) {
            flash.message = "SurveyConnection not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ surveyConnectionInstance : surveyConnectionInstance ]
        }
    }

    def update = {
        def surveyConnectionInstance = SurveyConnection.get( params.id )
        if(surveyConnectionInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(surveyConnectionInstance.version > version) {
                    
                    surveyConnectionInstance.errors.rejectValue("version", "surveyConnection.optimistic.locking.failure", "Another user has updated this SurveyConnection while you were editing.")
                    render(view:'edit',model:[surveyConnectionInstance:surveyConnectionInstance])
                    return
                }
            }
            surveyConnectionInstance.properties = params
            if(!surveyConnectionInstance.hasErrors() && surveyConnectionInstance.save()) {
                flash.message = "SurveyConnection ${params.id} updated"
                redirect(action:show,id:surveyConnectionInstance.id)
            }
            else {
                render(view:'edit',model:[surveyConnectionInstance:surveyConnectionInstance])
            }
        }
        else {
            flash.message = "SurveyConnection not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def create = {
        def surveyConnectionInstance = new SurveyConnection()
        surveyConnectionInstance.properties = params
        return ['surveyConnectionInstance':surveyConnectionInstance]
    }

    def save = {
        def surveyConnectionInstance = new SurveyConnection(params)
        if(!surveyConnectionInstance.hasErrors() && surveyConnectionInstance.save()) {
            flash.message = "SurveyConnection ${surveyConnectionInstance.id} created"
            redirect(action:show,id:surveyConnectionInstance.id)
        }
        else {
            render(view:'create',model:[surveyConnectionInstance:surveyConnectionInstance])
        }
    }
}
