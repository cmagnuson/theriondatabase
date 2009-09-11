

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>FeatureInstance List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
        </div>
        <div class="body">
            <h1>FeatureInstance List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <th>Feature</th>
                   	    
                   	        <g:sortableColumn property="rotation" title="Rotation" />
                        
                   	        <g:sortableColumn property="centerOffset" title="Center Offset" />
                        
                   	        <th>Survey Connection</th>
                   	    
                   	        <th>Survey Station</th>
                   	    
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${featureInstanceInstanceList}" status="i" var="featureInstanceInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${featureInstanceInstance.id}">${fieldValue(bean:featureInstanceInstance, field:'id')}</g:link></td>
                        
                            <td>${fieldValue(bean:featureInstanceInstance, field:'feature')}</td>
                        
                            <td>${fieldValue(bean:featureInstanceInstance, field:'rotation')}</td>
                        
                            <td>${fieldValue(bean:featureInstanceInstance, field:'centerOffset')}</td>
                        
                            <td>${fieldValue(bean:featureInstanceInstance, field:'surveyConnection')}</td>
                        
                            <td>${fieldValue(bean:featureInstanceInstance, field:'surveyStation')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${featureInstanceInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
