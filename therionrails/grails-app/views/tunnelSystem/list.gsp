

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>TunnelSystem List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New TunnelSystem</g:link></span>
        </div>
        <div class="body">
            <h1>TunnelSystem List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="color" title="Color" />
                        
                   	        <g:sortableColumn property="name" title="Name" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${tunnelSystemInstanceList}" status="i" var="tunnelSystemInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${tunnelSystemInstance.id}">${fieldValue(bean:tunnelSystemInstance, field:'id')}</g:link></td>
                        
                            <td>${fieldValue(bean:tunnelSystemInstance, field:'color')}</td>
                        
                            <td>${fieldValue(bean:tunnelSystemInstance, field:'name')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${tunnelSystemInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
