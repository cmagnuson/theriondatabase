

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Show SurveyStation</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">SurveyStation List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New SurveyStation</g:link></span>
        </div>
        <div class="body">
            <h1>Show SurveyStation</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>

                    
                        <tr class="prop">
                            <td valign="top" class="name">Id:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:surveyStationInstance, field:'id')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Name:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:surveyStationInstance, field:'name')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Note:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:surveyStationInstance, field:'note')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Survey:</td>
                            
                            <td valign="top" class="value"><g:link controller="survey" action="show" id="${surveyStationInstance?.survey?.id}">${surveyStationInstance?.survey?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Time Measured:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:surveyStationInstance, field:'timeMeasured')}</td>
                            
                        </tr>
                        
                       <tr class="prop">
                            <td valign="top" class="name">Label:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:surveyStationInstance, field:'label')}</td>
                            
                        </tr>
                   
                          <tr class="prop">
                            <td valign="top" class="name">Features:</td>
                            
                            <td  valign="top" style="text-align:left;" class="value">
                                <ul>
                                <g:each var="f" in="${FeatureInstance.findAllBySurveyStation(surveyStationInstance)}">
                                    <li><g:link controller="featureInstance" action="show" id="${f.id}">${f?.feature?.encodeAsHTML()}</g:link></li>
                                </g:each>
                                </ul>
                            </td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <input type="hidden" name="id" value="${surveyStationInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" value="Edit" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                    </g:form>
                    <g:form controller="equate">
                    <input type="hidden" name="surveyStation.id" value="${surveyStationInstance?.id}" />
                    <g:select name="equate.id" from="${Equate.list()}" optionKey="id"/>
                    <span class="button"><g:actionSubmit class="edit" action="addSurveyStation" value="Add Equate" /></span>
                </g:form>
                <g:form controller="fixedPoint">
                    <input type="hidden" name="surveyStation.id" value="${surveyStationInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" action="create" value="Add Fixed Point" /></span>
                </g:form>
                <g:form controller="featureInstance">
                    <input type="hidden" name="surveyStation.id" value="${surveyStationInstance?.id}" />
                    <g:select name="featureInstance.id" from="${Feature.list()}" optionKey="id"/>
                    <span class="button"><g:actionSubmit class="edit" action="create" value="Add Feature" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
