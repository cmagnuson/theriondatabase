

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Edit Survey</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list" controller="survey">Survey List</g:link></span>
            <span class="menuButton"><g:link class="list" action="show" controller="survey" id="${surveyInstance.id}">Return To Survey</g:link></span>
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
                                    <label for="data">Data:</label>
                                </td>
                                <td valign="top">
                                    <textarea rows="120" cols="100" id="data" name="data"></textarea>
                                </td>
                            </tr> 
                                                                                                       
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" value="Save" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
