<html>
    <head>
        <title>Welcome to PMaps</title>
		<meta name="layout" content="main" />
    </head>
    <body>
    <center><g:link url="${createLinkTo(dir:'generated/output', file:'cave.pdf')}">
    <img src="${createLinkTo(dir:'generated/output', file:'cave.jpg')}" class="frontimg">
	</g:link>
	</center>
	<br>
	<center>
	<div>
	<h2>Last Compile Stats:</h2> 
	<br><b>Status:</b> ${LastCompile.status}<br>
	<b>Avg. Loop Error:</b> ${LastCompile.loopClosure}
	<br><b>Compilation Time:</b> ${LastCompile.compileTime}
	<br><b>Compile Date:</b> ${LastCompile.compileDate}
	<br>
		<g:link url="${createLinkTo(dir:'generated/source', file:'therion.log')}">See Log</g:link>
	</div>
	</center>
			
    </body>
</html>