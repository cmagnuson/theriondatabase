

class FixedPointController {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        [ fixedPointInstanceList: FixedPoint.list( params ), fixedPointInstanceTotal: FixedPoint.count() ]
    }

    def show = {
        def fixedPointInstance = FixedPoint.get( params.id )

        if(!fixedPointInstance) {
            flash.message = "FixedPoint not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ fixedPointInstance : fixedPointInstance ] }
    }

    def delete = {
        def fixedPointInstance = FixedPoint.get( params.id )
        if(fixedPointInstance) {
            try {
                fixedPointInstance.delete(flush:true)
                flash.message = "FixedPoint ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "FixedPoint ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "FixedPoint not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def fixedPointInstance = FixedPoint.get( params.id )

        if(!fixedPointInstance) {
            flash.message = "FixedPoint not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ fixedPointInstance : fixedPointInstance ]
        }
    }

    def update = {
        def fixedPointInstance = FixedPoint.get( params.id )
        if(fixedPointInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(fixedPointInstance.version > version) {
                    
                    fixedPointInstance.errors.rejectValue("version", "fixedPoint.optimistic.locking.failure", "Another user has updated this FixedPoint while you were editing.")
                    render(view:'edit',model:[fixedPointInstance:fixedPointInstance])
                    return
                }
            }
            fixedPointInstance.properties = params
            if(!fixedPointInstance.hasErrors() && fixedPointInstance.save()) {
                flash.message = "FixedPoint ${params.id} updated"
                redirect(action:show,id:fixedPointInstance.id)
            }
            else {
                render(view:'edit',model:[fixedPointInstance:fixedPointInstance])
            }
        }
        else {
            flash.message = "FixedPoint not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def create = {
        def fixedPointInstance = new FixedPoint()
        fixedPointInstance.properties = params
	
		def surveyInstance = SurveyStation.get(params.surveyStation.id)
		if(surveyInstance){
			fixedPointInstance.station = surveyInstance;
		}
        return ['fixedPointInstance':fixedPointInstance]
    }

    def save = {
        def fixedPointInstance = new FixedPoint(params)
        if(FixedPoint.findAllByStation(fixedPointInstance.station).size()>0){
        	flash.message = "Fixed point for this survey station already exists!"
        	redirect(action:show, id:FixedPoint.findByStation(fixedPointInstance.station).id)
        	return;
        }
        if(!fixedPointInstance.hasErrors() && fixedPointInstance.save()) {
            flash.message = "FixedPoint ${fixedPointInstance.id} created"
            redirect(action:show,id:fixedPointInstance.id)
        }
        else {
            render(view:'create',model:[fixedPointInstance:fixedPointInstance])
        }
    }
}
