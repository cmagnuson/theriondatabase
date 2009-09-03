

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Edit SurveyConnection</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">SurveyConnection List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New SurveyConnection</g:link></span>
        </div>
        <div class="body">
            <h1>Edit SurveyConnection</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${surveyConnectionInstance}">
            <div class="errors">
                <g:renderErrors bean="${surveyConnectionInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${surveyConnectionInstance?.id}" />
                <input type="hidden" name="version" value="${surveyConnectionInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="clino">Clino:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:surveyConnectionInstance,field:'clino','errors')}">
                                    <input type="text" id="clino" name="clino" value="${fieldValue(bean:surveyConnectionInstance,field:'clino')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="compass">Compass:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:surveyConnectionInstance,field:'compass','errors')}">
                                    <input type="text" id="compass" name="compass" value="${fieldValue(bean:surveyConnectionInstance,field:'compass')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="down">Down:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:surveyConnectionInstance,field:'down','errors')}">
                                    <input type="text" id="down" name="down" value="${fieldValue(bean:surveyConnectionInstance,field:'down')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="fromStation">From Station:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:surveyConnectionInstance,field:'fromStation','errors')}">
                                    <g:select optionKey="id" from="${SurveyStation.list()}" name="fromStation.id" value="${surveyConnectionInstance?.fromStation?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="left">Left:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:surveyConnectionInstance,field:'left','errors')}">
                                    <input type="text" id="left" name="left" value="${fieldValue(bean:surveyConnectionInstance,field:'left')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="length">Length:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:surveyConnectionInstance,field:'length','errors')}">
                                    <input type="text" id="length" name="length" value="${fieldValue(bean:surveyConnectionInstance,field:'length')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="right">Right:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:surveyConnectionInstance,field:'right','errors')}">
                                    <input type="text" id="right" name="right" value="${fieldValue(bean:surveyConnectionInstance,field:'right')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="survey">Survey:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:surveyConnectionInstance,field:'survey','errors')}">
                                    <g:select optionKey="id" from="${Survey.list()}" name="survey.id" value="${surveyConnectionInstance?.survey?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="toStation">To Station:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:surveyConnectionInstance,field:'toStation','errors')}">
                                    <g:select optionKey="id" from="${SurveyStation.list()}" name="toStation.id" value="${surveyConnectionInstance?.toStation?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="up">Up:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:surveyConnectionInstance,field:'up','errors')}">
                                    <input type="text" id="up" name="up" value="${fieldValue(bean:surveyConnectionInstance,field:'up')}" />
                                </td>
                            </tr> 
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" value="Update" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
