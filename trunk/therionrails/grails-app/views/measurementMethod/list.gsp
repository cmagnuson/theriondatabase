

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>MeasurementMethod List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New MeasurementMethod</g:link></span>
        </div>
        <div class="body">
            <h1>MeasurementMethod List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="name" title="Name" />
                        
                   	        <g:sortableColumn property="sd_x" title="Sdx" />
                        
                   	        <g:sortableColumn property="sd_y" title="Sdy" />
                        
                   	        <g:sortableColumn property="sd_z" title="Sdz" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${measurementMethodInstanceList}" status="i" var="measurementMethodInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${measurementMethodInstance.id}">${fieldValue(bean:measurementMethodInstance, field:'id')}</g:link></td>
                        
                            <td>${fieldValue(bean:measurementMethodInstance, field:'name')}</td>
                        
                            <td>${fieldValue(bean:measurementMethodInstance, field:'sd_x')}</td>
                        
                            <td>${fieldValue(bean:measurementMethodInstance, field:'sd_y')}</td>
                        
                            <td>${fieldValue(bean:measurementMethodInstance, field:'sd_z')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${measurementMethodInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
