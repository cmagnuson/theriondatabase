

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>FixedPoint List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New FixedPoint</g:link></span>
        </div>
        <div class="body">
            <h1>FixedPoint List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <th>Measurement Method</th>
                   	    
                   	        <g:sortableColumn property="note" title="Note" />
                        
                   	        <th>Station</th>
                   	    
                   	        <g:sortableColumn property="x" title="X" />
                        
                   	        <g:sortableColumn property="y" title="Y" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${fixedPointInstanceList}" status="i" var="fixedPointInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${fixedPointInstance.id}">${fieldValue(bean:fixedPointInstance, field:'id')}</g:link></td>
                        
                            <td>${fieldValue(bean:fixedPointInstance, field:'measurementMethod')}</td>
                        
                            <td>${fieldValue(bean:fixedPointInstance, field:'note')}</td>
                        
                            <td>${fieldValue(bean:fixedPointInstance, field:'station')}</td>
                        
                            <td>${fieldValue(bean:fixedPointInstance, field:'x')}</td>
                        
                            <td>${fieldValue(bean:fixedPointInstance, field:'y')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${fixedPointInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
