

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Edit TunnelSystem</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">TunnelSystem List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New TunnelSystem</g:link></span>
        </div>
        <div class="body">
            <h1>Edit TunnelSystem</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${tunnelSystemInstance}">
            <div class="errors">
                <g:renderErrors bean="${tunnelSystemInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${tunnelSystemInstance?.id}" />
                <input type="hidden" name="version" value="${tunnelSystemInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="color">Color:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:tunnelSystemInstance,field:'color','errors')}">
                                    <input type="text" id="color" name="color" value="${fieldValue(bean:tunnelSystemInstance,field:'color')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name">Name:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:tunnelSystemInstance,field:'name','errors')}">
                                    <input type="text" id="name" name="name" value="${fieldValue(bean:tunnelSystemInstance,field:'name')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="surveys">Surveys:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:tunnelSystemInstance,field:'surveys','errors')}">
                                    
<ul>
<g:each var="s" in="${tunnelSystemInstance?.surveys?}">
    <li><g:link controller="survey" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></li>
</g:each>
</ul>
<g:link controller="survey" params="['tunnelSystem.id':tunnelSystemInstance?.id]" action="create">Add Survey</g:link>

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
