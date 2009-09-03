

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Equate List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New Equate</g:link></span>
        </div>
        <div class="body">
            <h1>Equate List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="note" title="Note" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${equateInstanceList}" status="i" var="equateInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${equateInstance.id}">${fieldValue(bean:equateInstance, field:'id')}</g:link></td>
                        
                            <td>${fieldValue(bean:equateInstance, field:'note')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${equateInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
