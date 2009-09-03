

class EquateController {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        [ equateInstanceList: Equate.list( params ), equateInstanceTotal: Equate.count() ]
    }

    def show = {
        def equateInstance = Equate.get( params.id )

        if(!equateInstance) {
            flash.message = "Equate not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ equateInstance : equateInstance ] }
    }

    def delete = {
        def equateInstance = Equate.get( params.id )
        if(equateInstance) {
            try {
                equateInstance.delete(flush:true)
                flash.message = "Equate ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "Equate ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "Equate not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def equateInstance = Equate.get( params.id )

        if(!equateInstance) {
            flash.message = "Equate not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ equateInstance : equateInstance ]
        }
    }

    def update = {
        def equateInstance = Equate.get( params.id )
        if(equateInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(equateInstance.version > version) {
                    
                    equateInstance.errors.rejectValue("version", "equate.optimistic.locking.failure", "Another user has updated this Equate while you were editing.")
                    render(view:'edit',model:[equateInstance:equateInstance])
                    return
                }
            }
            equateInstance.properties = params
            if(!equateInstance.hasErrors() && equateInstance.save()) {
                flash.message = "Equate ${params.id} updated"
                redirect(action:show,id:equateInstance.id)
            }
            else {
                render(view:'edit',model:[equateInstance:equateInstance])
            }
        }
        else {
            flash.message = "Equate not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def create = {
        def equateInstance = new Equate()
        equateInstance.properties = params
        return ['equateInstance':equateInstance]
    }

    def save = {
        def equateInstance = new Equate(params)
        if(!equateInstance.hasErrors() && equateInstance.save()) {
            flash.message = "Equate ${equateInstance.id} created"
            redirect(action:show,id:equateInstance.id)
        }
        else {
            render(view:'create',model:[equateInstance:equateInstance])
        }
    }
}
