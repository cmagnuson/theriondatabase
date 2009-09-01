

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>SurveyConnection List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New SurveyConnection</g:link></span>
        </div>
        <div class="body">
            <h1>SurveyConnection List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="clino" title="Clino" />
                        
                   	        <g:sortableColumn property="compass" title="Compass" />
                        
                   	        <g:sortableColumn property="down" title="Down" />
                        
                   	        <th>From Station</th>
                   	    
                   	        <g:sortableColumn property="left" title="Left" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${surveyConnectionInstanceList}" status="i" var="surveyConnectionInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${surveyConnectionInstance.id}">${fieldValue(bean:surveyConnectionInstance, field:'id')}</g:link></td>
                        
                            <td>${fieldValue(bean:surveyConnectionInstance, field:'clino')}</td>
                        
                            <td>${fieldValue(bean:surveyConnectionInstance, field:'compass')}</td>
                        
                            <td>${fieldValue(bean:surveyConnectionInstance, field:'down')}</td>
                        
                            <td>${fieldValue(bean:surveyConnectionInstance, field:'fromStation')}</td>
                        
                            <td>${fieldValue(bean:surveyConnectionInstance, field:'left')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${surveyConnectionInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
