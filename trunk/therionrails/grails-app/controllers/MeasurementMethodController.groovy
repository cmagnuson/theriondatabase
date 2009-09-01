

class MeasurementMethodController {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        [ measurementMethodInstanceList: MeasurementMethod.list( params ), measurementMethodInstanceTotal: MeasurementMethod.count() ]
    }

    def show = {
        def measurementMethodInstance = MeasurementMethod.get( params.id )

        if(!measurementMethodInstance) {
            flash.message = "MeasurementMethod not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ measurementMethodInstance : measurementMethodInstance ] }
    }

    def delete = {
        def measurementMethodInstance = MeasurementMethod.get( params.id )
        if(measurementMethodInstance) {
            try {
                measurementMethodInstance.delete(flush:true)
                flash.message = "MeasurementMethod ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "MeasurementMethod ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "MeasurementMethod not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def measurementMethodInstance = MeasurementMethod.get( params.id )

        if(!measurementMethodInstance) {
            flash.message = "MeasurementMethod not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ measurementMethodInstance : measurementMethodInstance ]
        }
    }

    def update = {
        def measurementMethodInstance = MeasurementMethod.get( params.id )
        if(measurementMethodInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(measurementMethodInstance.version > version) {
                    
                    measurementMethodInstance.errors.rejectValue("version", "measurementMethod.optimistic.locking.failure", "Another user has updated this MeasurementMethod while you were editing.")
                    render(view:'edit',model:[measurementMethodInstance:measurementMethodInstance])
                    return
                }
            }
            measurementMethodInstance.properties = params
            if(!measurementMethodInstance.hasErrors() && measurementMethodInstance.save()) {
                flash.message = "MeasurementMethod ${params.id} updated"
                redirect(action:show,id:measurementMethodInstance.id)
            }
            else {
                render(view:'edit',model:[measurementMethodInstance:measurementMethodInstance])
            }
        }
        else {
            flash.message = "MeasurementMethod not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def create = {
        def measurementMethodInstance = new MeasurementMethod()
        measurementMethodInstance.properties = params
        return ['measurementMethodInstance':measurementMethodInstance]
    }

    def save = {
        def measurementMethodInstance = new MeasurementMethod(params)
        if(!measurementMethodInstance.hasErrors() && measurementMethodInstance.save()) {
            flash.message = "MeasurementMethod ${measurementMethodInstance.id} created"
            redirect(action:show,id:measurementMethodInstance.id)
        }
        else {
            render(view:'create',model:[measurementMethodInstance:measurementMethodInstance])
        }
    }
}
