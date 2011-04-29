<!DOCTYPE html>
<html>
    <head>
        <title><g:layoutTitle default="Grails" /></title>
        <link rel="stylesheet" href="${resource(dir:'css',file:'main.css')}" />
        <link rel="stylesheet" href="${createLinkTo(dir:'css',file:'admin.css')}" />
        <link rel="stylesheet" href="${resource(dir:'css',file:'tb.css')}" />
        <link rel="shortcut icon" href="${resource(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <g:layoutHead />
        
        <g:javascript library="application" />
        <g:javascript library="prototype" />
    </head>
    <body>
        <div id="spinner" class="spinner" style="display:none;">
            <img src="${resource(dir:'images',file:'spinner.gif')}" alt="${message(code:'spinner.alt',default:'Loading...')}" />
        </div>
        <div id="grailsLogo"><a href="${grailsApplication.config.grails.serverURL}><img src="${resource(dir:'images',file:'grails_logo.png')}" alt="Grails" border="0" /></a></div>
        <g:layoutBody />
    </body>
</html>