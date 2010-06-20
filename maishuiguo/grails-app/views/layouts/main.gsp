<html>
    <head>
        <title><g:layoutTitle default="Grails" /></title>
        <link rel="stylesheet" href="${resource(dir:'css',file:'main.css')}" />
        <link rel="shortcut icon" href="${resource(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
        <g:layoutHead />
        <g:javascript library="application" />
    </head>
    <body>
        <div id="spinner" class="spinner" style="display:none;">
            <img src="${resource(dir:'images',file:'spinner.gif')}" alt="Spinner" />
        </div>
        <div id="grailsLogo" class="logo"><a href="http://www.hbhgzy.com.cn" target="_blank"><img src="${resource(dir:'images',file:'logo.jpg')}" alt="Grails" border="0" /></a></div>
        <div class="nav">
            <span class="menuButton"><g:link controller="hr" action="list">人员列表</g:link></span>
            <span class="menuButton"><g:link action="hr">New Tag</g:link></span>
        </div>
        <g:layoutBody />
    </body>
</html>