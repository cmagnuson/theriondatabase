

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>SurveyStation List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New SurveyStation</g:link></span>
        </div>
        <div class="body">
            <h1>SurveyStation List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="name" title="Name" />
                        
                   	        <g:sortableColumn property="note" title="Note" />
                        
                   	        <th>Survey</th>
                   	    
                   	        <g:sortableColumn property="timeMeasured" title="Time Measured" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${surveyStationInstanceList}" status="i" var="surveyStationInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${surveyStationInstance.id}">${fieldValue(bean:surveyStationInstance, field:'id')}</g:link></td>
                        
                            <td>${fieldValue(bean:surveyStationInstance, field:'name')}</td>
                        
                            <td>${fieldValue(bean:surveyStationInstance, field:'note')}</td>
                        
                            <td>${fieldValue(bean:surveyStationInstance, field:'survey')}</td>
                        
                            <td>${fieldValue(bean:surveyStationInstance, field:'timeMeasured')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${surveyStationInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
