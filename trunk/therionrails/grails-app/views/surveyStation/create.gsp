

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Create SurveyStation</title>         
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">SurveyStation List</g:link></span>
        </div>
        <div class="body">
            <h1>Create SurveyStation</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${surveyStationInstance}">
            <div class="errors">
                <g:renderErrors bean="${surveyStationInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name">Name:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:surveyStationInstance,field:'name','errors')}">
                                    <input type="text" id="name" name="name" value="${fieldValue(bean:surveyStationInstance,field:'name')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="note">Note:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:surveyStationInstance,field:'note','errors')}">
                                    <input type="text" id="note" name="note" value="${fieldValue(bean:surveyStationInstance,field:'note')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="survey">Survey:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:surveyStationInstance,field:'survey','errors')}">
                                    <g:select optionKey="id" from="${Survey.list()}" name="survey.id" value="${surveyStationInstance?.survey?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="timeMeasured">Time Measured:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:surveyStationInstance,field:'timeMeasured','errors')}">
                                    <g:datePicker name="timeMeasured" value="${surveyStationInstance?.timeMeasured}" precision="minute" ></g:datePicker>
                                </td>
                            </tr> 
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><input class="save" type="submit" value="Create" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
