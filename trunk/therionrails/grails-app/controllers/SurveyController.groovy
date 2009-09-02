

class SurveyController {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        [ surveyInstanceList: Survey.list( params ), surveyInstanceTotal: Survey.count() ]
    }

    def show = {
        def surveyInstance = Survey.get( params.id )

        if(!surveyInstance) {
            flash.message = "Survey not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ surveyInstance : surveyInstance ] }
    }

    def delete = {
        def surveyInstance = Survey.get( params.id )
        if(surveyInstance) {
            try {
                surveyInstance.delete(flush:true)
                flash.message = "Survey ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "Survey ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "Survey not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def surveyInstance = Survey.get( params.id )

        if(!surveyInstance) {
            flash.message = "Survey not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ surveyInstance : surveyInstance ]
        }
    }

    def update = {
        def surveyInstance = Survey.get( params.id )
        if(surveyInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(surveyInstance.version > version) {
                    
                    surveyInstance.errors.rejectValue("version", "survey.optimistic.locking.failure", "Another user has updated this Survey while you were editing.")
                    render(view:'edit',model:[surveyInstance:surveyInstance])
                    return
                }
            }
            surveyInstance.properties = params
            if(!surveyInstance.hasErrors() && surveyInstance.save()) {
                flash.message = "Survey ${params.id} updated"
                redirect(action:show,id:surveyInstance.id)
            }
            else {
                render(view:'edit',model:[surveyInstance:surveyInstance])
            }
        }
        else {
            flash.message = "Survey not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def create = {
        def surveyInstance = new Survey()
        surveyInstance.properties = params
        return ['surveyInstance':surveyInstance]
    }

    def save = {
        def surveyInstance = new Survey(params)
        if(!surveyInstance.hasErrors() && surveyInstance.save()) {
            flash.message = "Survey ${surveyInstance.id} created"
            redirect(action:show,id:surveyInstance.id)
        }
        else {
            render(view:'create',model:[surveyInstance:surveyInstance])
        }
    }
}
