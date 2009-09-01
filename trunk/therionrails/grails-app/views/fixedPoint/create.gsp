

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Create FixedPoint</title>         
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">FixedPoint List</g:link></span>
        </div>
        <div class="body">
            <h1>Create FixedPoint</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${fixedPointInstance}">
            <div class="errors">
                <g:renderErrors bean="${fixedPointInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="measurementMethod">Measurement Method:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:fixedPointInstance,field:'measurementMethod','errors')}">
                                    <g:select optionKey="id" from="${MeasurementMethod.list()}" name="measurementMethod.id" value="${fixedPointInstance?.measurementMethod?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="note">Note:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:fixedPointInstance,field:'note','errors')}">
                                    <input type="text" id="note" name="note" value="${fieldValue(bean:fixedPointInstance,field:'note')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="station">Station:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:fixedPointInstance,field:'station','errors')}">
                                    <g:select optionKey="id" from="${SurveyStation.list()}" name="station.id" value="${fixedPointInstance?.station?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="x">X:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:fixedPointInstance,field:'x','errors')}">
                                    <input type="text" id="x" name="x" value="${fieldValue(bean:fixedPointInstance,field:'x')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="y">Y:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:fixedPointInstance,field:'y','errors')}">
                                    <input type="text" id="y" name="y" value="${fieldValue(bean:fixedPointInstance,field:'y')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="z">Z:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:fixedPointInstance,field:'z','errors')}">
                                    <input type="text" id="z" name="z" value="${fieldValue(bean:fixedPointInstance,field:'z')}" />
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
