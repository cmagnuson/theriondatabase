

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Show Survey</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">Survey List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New Survey</g:link></span>
            <span class="menuButton"><g:link class="create" action="importSurveyData" controller="surveyDataImport" id="${fieldValue(bean:surveyInstance, field:'id')}">Import Data</g:link></span>
        </div>
        <div class="body">
            <h1>Show Survey</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>

                    
                        <tr class="prop">
                            <td valign="top" class="name">Id:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:surveyInstance, field:'id')}</td>
                            
                        </tr>
                    
                       
                       <tr class="prop">
                            <td valign="top" class="name">Title:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:surveyInstance, field:'title')}</td>
                            
                        </tr>
                        
                        <tr class="prop">
                            <td valign="top" class="name">Dateentered:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:surveyInstance, field:'dateentered')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Datesurveyed:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:surveyInstance, field:'datesurveyed')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Note:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:surveyInstance, field:'note')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Survey Connections:</td>
                            
                            <td  valign="top" style="text-align:left;" class="value">
                                <ul>
                                <g:each var="s" in="${surveyInstance.surveyConnections}">
                                    <li><g:link controller="surveyConnection" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></li>
                                </g:each>
                                </ul>
                            </td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Survey Stations:</td>
                            
                            <td  valign="top" style="text-align:left;" class="value">
                                <ul>
                                <g:each var="s" in="${surveyInstance.surveyStations}">
                                    <li><g:link controller="surveyStation" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></li>
                                </g:each>
                                </ul>
                            </td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">System:</td>
                            
                            <td valign="top" class="value"><g:link controller="tunnelSystem" action="show" id="${surveyInstance?.system?.id}">${surveyInstance?.system?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Team:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:surveyInstance, field:'team')}</td>
                            
                        </tr>
                    
                    
                    <tr class="prop">
                    	<td valign="top" class="name">Video:</td>
                    	<td valign="top" class="value"><a href="${createLink(controller:'survey', action:'viewVideo', id:surveyInstance.id)}">Show</a></td>
                    </tr>
                    	
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <input type="hidden" name="id" value="${surveyInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" value="Edit" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
