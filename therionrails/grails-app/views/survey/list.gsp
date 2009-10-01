

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Survey List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New Survey</g:link></span>
        </div>
        <div class="body">
            <h1>Survey List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="title" title="Title" />
                                                
                   	        <g:sortableColumn property="datesurveyed" title="Date Surveyed" />
                        
                   	        <g:sortableColumn property="note" title="Note" />
                        
                   	        <th>System</th>
                   	                            
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${surveyInstanceList}" status="i" var="surveyInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${surveyInstance.id}">${fieldValue(bean:surveyInstance, field:'title')}</g:link></td>
                                                
                            <td>${fieldValue(bean:surveyInstance, field:'datesurveyed')}</td>
                        
                            <td>${fieldValue(bean:surveyInstance, field:'note')}</td>
                        
                            <td>${fieldValue(bean:surveyInstance, field:'system')}</td>
                                                
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${surveyInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
