

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Create MeasurementMethod</title>         
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">MeasurementMethod List</g:link></span>
        </div>
        <div class="body">
            <h1>Create MeasurementMethod</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${measurementMethodInstance}">
            <div class="errors">
                <g:renderErrors bean="${measurementMethodInstance}" as="list" />
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
                                <td valign="top" class="value ${hasErrors(bean:measurementMethodInstance,field:'name','errors')}">
                                    <input type="text" id="name" name="name" value="${fieldValue(bean:measurementMethodInstance,field:'name')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="sd_x">Sdx:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:measurementMethodInstance,field:'sd_x','errors')}">
                                    <input type="text" id="sd_x" name="sd_x" value="${fieldValue(bean:measurementMethodInstance,field:'sd_x')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="sd_y">Sdy:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:measurementMethodInstance,field:'sd_y','errors')}">
                                    <input type="text" id="sd_y" name="sd_y" value="${fieldValue(bean:measurementMethodInstance,field:'sd_y')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="sd_z">Sdz:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:measurementMethodInstance,field:'sd_z','errors')}">
                                    <input type="text" id="sd_z" name="sd_z" value="${fieldValue(bean:measurementMethodInstance,field:'sd_z')}" />
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
