

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Show SurveyConnection</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">SurveyConnection List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New SurveyConnection</g:link></span>
        </div>
        <div class="body">
            <h1>Show SurveyConnection</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>

                    
                        <tr class="prop">
                            <td valign="top" class="name">Id:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:surveyConnectionInstance, field:'id')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Clino:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:surveyConnectionInstance, field:'clino')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Compass:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:surveyConnectionInstance, field:'compass')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Down:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:surveyConnectionInstance, field:'down')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">From Station:</td>
                            
                            <td valign="top" class="value"><g:link controller="surveyStation" action="show" id="${surveyConnectionInstance?.fromStation?.id}">${surveyConnectionInstance?.fromStation?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Left:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:surveyConnectionInstance, field:'left')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Length:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:surveyConnectionInstance, field:'length')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Right:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:surveyConnectionInstance, field:'right')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">To Station:</td>
                            
                            <td valign="top" class="value"><g:link controller="surveyStation" action="show" id="${surveyConnectionInstance?.toStation?.id}">${surveyConnectionInstance?.toStation?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Up:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:surveyConnectionInstance, field:'up')}</td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <input type="hidden" name="id" value="${surveyConnectionInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" value="Edit" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
