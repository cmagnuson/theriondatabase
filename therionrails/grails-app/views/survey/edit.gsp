

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Edit Survey</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">Survey List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New Survey</g:link></span>
        </div>
        <div class="body">
            <h1>Edit Survey</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${surveyInstance}">
            <div class="errors">
                <g:renderErrors bean="${surveyInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${surveyInstance?.id}" />
                <input type="hidden" name="version" value="${surveyInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="dateentered">Dateentered:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:surveyInstance,field:'dateentered','errors')}">
                                    <g:datePicker name="dateentered" value="${surveyInstance?.dateentered}" precision="minute" ></g:datePicker>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="datesurveyed">Datesurveyed:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:surveyInstance,field:'datesurveyed','errors')}">
                                    <g:datePicker name="datesurveyed" value="${surveyInstance?.datesurveyed}" precision="minute" ></g:datePicker>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="note">Note:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:surveyInstance,field:'note','errors')}">
                                    <input type="text" id="note" name="note" value="${fieldValue(bean:surveyInstance,field:'note')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="surveyConnection">Survey Connection:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:surveyInstance,field:'surveyConnection','errors')}">
                                    
<ul>
<g:each var="s" in="${surveyInstance?.surveyConnection?}">
    <li><g:link controller="surveyConnection" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></li>
</g:each>
</ul>
<g:link controller="surveyConnection" params="['survey.id':surveyInstance?.id]" action="create">Add SurveyConnection</g:link>

                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="surveyStation">Survey Station:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:surveyInstance,field:'surveyStation','errors')}">
                                    
<ul>
<g:each var="s" in="${surveyInstance?.surveyStation?}">
    <li><g:link controller="surveyStation" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></li>
</g:each>
</ul>
<g:link controller="surveyStation" params="['survey.id':surveyInstance?.id]" action="create">Add SurveyStation</g:link>

                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="system">System:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:surveyInstance,field:'system','errors')}">
                                    <g:select optionKey="id" from="${TunnelSystem.list()}" name="system.id" value="${surveyInstance?.system?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="team">Team:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:surveyInstance,field:'team','errors')}">
                                    <input type="text" id="team" name="team" value="${fieldValue(bean:surveyInstance,field:'team')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="title">Title:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:surveyInstance,field:'title','errors')}">
                                    <input type="text" id="title" name="title" value="${fieldValue(bean:surveyInstance,field:'title')}"/>
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
