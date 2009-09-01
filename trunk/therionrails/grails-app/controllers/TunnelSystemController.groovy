

class TunnelSystemController {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        [ tunnelSystemInstanceList: TunnelSystem.list( params ), tunnelSystemInstanceTotal: TunnelSystem.count() ]
    }

    def show = {
        def tunnelSystemInstance = TunnelSystem.get( params.id )

        if(!tunnelSystemInstance) {
            flash.message = "TunnelSystem not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ tunnelSystemInstance : tunnelSystemInstance ] }
    }

    def delete = {
        def tunnelSystemInstance = TunnelSystem.get( params.id )
        if(tunnelSystemInstance) {
            try {
                tunnelSystemInstance.delete(flush:true)
                flash.message = "TunnelSystem ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "TunnelSystem ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "TunnelSystem not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def tunnelSystemInstance = TunnelSystem.get( params.id )

        if(!tunnelSystemInstance) {
            flash.message = "TunnelSystem not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ tunnelSystemInstance : tunnelSystemInstance ]
        }
    }

    def update = {
        def tunnelSystemInstance = TunnelSystem.get( params.id )
        if(tunnelSystemInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(tunnelSystemInstance.version > version) {
                    
                    tunnelSystemInstance.errors.rejectValue("version", "tunnelSystem.optimistic.locking.failure", "Another user has updated this TunnelSystem while you were editing.")
                    render(view:'edit',model:[tunnelSystemInstance:tunnelSystemInstance])
                    return
                }
            }
            tunnelSystemInstance.properties = params
            if(!tunnelSystemInstance.hasErrors() && tunnelSystemInstance.save()) {
                flash.message = "TunnelSystem ${params.id} updated"
                redirect(action:show,id:tunnelSystemInstance.id)
            }
            else {
                render(view:'edit',model:[tunnelSystemInstance:tunnelSystemInstance])
            }
        }
        else {
            flash.message = "TunnelSystem not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def create = {
        def tunnelSystemInstance = new TunnelSystem()
        tunnelSystemInstance.properties = params
        return ['tunnelSystemInstance':tunnelSystemInstance]
    }

    def save = {
        def tunnelSystemInstance = new TunnelSystem(params)
        if(!tunnelSystemInstance.hasErrors() && tunnelSystemInstance.save()) {
            flash.message = "TunnelSystem ${tunnelSystemInstance.id} created"
            redirect(action:show,id:tunnelSystemInstance.id)
        }
        else {
            render(view:'create',model:[tunnelSystemInstance:tunnelSystemInstance])
        }
    }
}
