

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Edit Equate</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">Equate List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New Equate</g:link></span>
        </div>
        <div class="body">
            <h1>Edit Equate</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${equateInstance}">
            <div class="errors">
                <g:renderErrors bean="${equateInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${equateInstance?.id}" />
                <input type="hidden" name="version" value="${equateInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="note">Note:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:equateInstance,field:'note','errors')}">
                                    <input type="text" id="note" name="note" value="${fieldValue(bean:equateInstance,field:'note')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="stations">Stations:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:equateInstance,field:'stations','errors')}">
                                    <g:select name="stations"
from="${SurveyStation.list()}"
size="5" multiple="yes" optionKey="id"
value="${equateInstance?.stations}" />

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
