

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Create FeatureInstance</title>         
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">FeatureInstance List</g:link></span>
        </div>
        <div class="body">
            <h1>Create FeatureInstance</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${featureInstanceInstance}">
            <div class="errors">
                <g:renderErrors bean="${featureInstanceInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="feature">Feature:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:featureInstanceInstance,field:'feature','errors')}">
                                    <g:select optionKey="id" from="${Feature.list()}" name="feature.id" value="${featureInstanceInstance?.feature?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="rotation">Rotation:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:featureInstanceInstance,field:'rotation','errors')}">
                                    <input type="text" id="rotation" name="rotation" value="${fieldValue(bean:featureInstanceInstance,field:'rotation')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="centerOffset">Center Offset:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:featureInstanceInstance,field:'centerOffset','errors')}">
                                    <input type="text" id="centerOffset" name="centerOffset" value="${fieldValue(bean:featureInstanceInstance,field:'centerOffset')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="surveyConnection">Survey Connection:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:featureInstanceInstance,field:'surveyConnection','errors')}">
                                    <g:select optionKey="id" from="${SurveyConnection.list()}" name="surveyConnection.id" value="${featureInstanceInstance?.surveyConnection?.id}" noSelection="['null':'']"></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="surveyStation">Survey Station:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:featureInstanceInstance,field:'surveyStation','errors')}">
                                    <g:select optionKey="id" from="${SurveyStation.list()}" name="surveyStation.id" value="${featureInstanceInstance?.surveyStation?.id}" noSelection="['null':'']"></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="attributes">Attributes:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:featureInstanceInstance,field:'attributes','errors')}">
                                    
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
