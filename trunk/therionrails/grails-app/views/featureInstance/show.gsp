

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Show FeatureInstance</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">FeatureInstance List</g:link></span>
        </div>
        <div class="body">
            <h1>Show FeatureInstance</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>

                    
                        <tr class="prop">
                            <td valign="top" class="name">Id:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:featureInstanceInstance, field:'id')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Feature:</td>
                            
                            <td valign="top" class="value"><g:link controller="feature" action="show" id="${featureInstanceInstance?.feature?.id}">${featureInstanceInstance?.feature?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Rotation:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:featureInstanceInstance, field:'rotation')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Center Offset:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:featureInstanceInstance, field:'centerOffset')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Survey Connection:</td>
                            
                            <td valign="top" class="value"><g:link controller="surveyConnection" action="show" id="${featureInstanceInstance?.surveyConnection?.id}">${featureInstanceInstance?.surveyConnection?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Survey Station:</td>
                            
                            <td valign="top" class="value"><g:link controller="surveyStation" action="show" id="${featureInstanceInstance?.surveyStation?.id}">${featureInstanceInstance?.surveyStation?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Attributes:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:featureInstanceInstance, field:'attributes')}</td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <input type="hidden" name="id" value="${featureInstanceInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" value="Edit" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
