

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Show FixedPoint</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">FixedPoint List</g:link></span>
        </div>
        <div class="body">
            <h1>Show FixedPoint</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>

                    
                    	<tr class="prop">
                    		<td valign="top" class="name">Map:</td>
                    		<td valign="top" class="value">
	                    		<% ref = new uk.me.jstott.jcoord.UTMRef(fixedPointInstance.x, fixedPointInstance.y, 'T' as char, 15);%>
	                    		<center>
	                    		<img src="http://maps.google.com/maps/api/staticmap?key=ABQIAAAAiuPVg850W_6nz_Xq0YZbBhSrESv4-HCD7-CxgDZyxXuj1_HzYhQUlbzFYM8NU4bu5HoPhWQPtQCYLQ&sensor=false&size=300x300&markers=${(ref.toLatLng().getLat()+"0000000").substring(0,9)+","+(ref.toLatLng().getLng()+"000000").substring(0,9)}">
								</center>
                    		</td>
                    	</tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Id:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:fixedPointInstance, field:'id')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Measurement Method:</td>
                            
                            <td valign="top" class="value"><g:link controller="measurementMethod" action="show" id="${fixedPointInstance?.measurementMethod?.id}">${fixedPointInstance?.measurementMethod?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Note:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:fixedPointInstance, field:'note')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Station:</td>
                            
                            <td valign="top" class="value"><g:link controller="surveyStation" action="show" id="${fixedPointInstance?.station?.id}">${fixedPointInstance?.station?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">X:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:fixedPointInstance, field:'x')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Y:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:fixedPointInstance, field:'y')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Z:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:fixedPointInstance, field:'z')}</td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <input type="hidden" name="id" value="${fixedPointInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" value="Edit" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
