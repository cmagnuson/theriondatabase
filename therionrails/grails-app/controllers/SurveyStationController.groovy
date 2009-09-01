

class SurveyStationController {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        [ surveyStationInstanceList: SurveyStation.list( params ), surveyStationInstanceTotal: SurveyStation.count() ]
    }

    def show = {
        def surveyStationInstance = SurveyStation.get( params.id )

        if(!surveyStationInstance) {
            flash.message = "SurveyStation not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ surveyStationInstance : surveyStationInstance ] }
    }

    def delete = {
        def surveyStationInstance = SurveyStation.get( params.id )
        if(surveyStationInstance) {
            try {
                surveyStationInstance.delete(flush:true)
                flash.message = "SurveyStation ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "SurveyStation ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "SurveyStation not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def surveyStationInstance = SurveyStation.get( params.id )

        if(!surveyStationInstance) {
            flash.message = "SurveyStation not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ surveyStationInstance : surveyStationInstance ]
        }
    }

    def update = {
        def surveyStationInstance = SurveyStation.get( params.id )
        if(surveyStationInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(surveyStationInstance.version > version) {
                    
                    surveyStationInstance.errors.rejectValue("version", "surveyStation.optimistic.locking.failure", "Another user has updated this SurveyStation while you were editing.")
                    render(view:'edit',model:[surveyStationInstance:surveyStationInstance])
                    return
                }
            }
            surveyStationInstance.properties = params
            if(!surveyStationInstance.hasErrors() && surveyStationInstance.save()) {
                flash.message = "SurveyStation ${params.id} updated"
                redirect(action:show,id:surveyStationInstance.id)
            }
            else {
                render(view:'edit',model:[surveyStationInstance:surveyStationInstance])
            }
        }
        else {
            flash.message = "SurveyStation not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def create = {
        def surveyStationInstance = new SurveyStation()
        surveyStationInstance.properties = params
        return ['surveyStationInstance':surveyStationInstance]
    }

    def save = {
        def surveyStationInstance = new SurveyStation(params)
        if(!surveyStationInstance.hasErrors() && surveyStationInstance.save()) {
            flash.message = "SurveyStation ${surveyStationInstance.id} created"
            redirect(action:show,id:surveyStationInstance.id)
        }
        else {
            render(view:'create',model:[surveyStationInstance:surveyStationInstance])
        }
    }
}
