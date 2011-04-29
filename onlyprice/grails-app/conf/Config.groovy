// locations to search for config files that get merged into the main config
// config files can either be Java properties files or ConfigSlurper scripts

// grails.config.locations = [ "classpath:${appName}-config.properties",
//                             "classpath:${appName}-config.groovy",
//                             "file:${userHome}/.grails/${appName}-config.properties",
//                             "file:${userHome}/.grails/${appName}-config.groovy"]

// if(System.properties["${appName}.config.location"]) {
//    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
// }

grails.project.groupId = appName // change this to alter the default package name and Maven publishing destination
grails.mime.file.extensions = true // enables the parsing of file extensions from URLs into the request format
grails.mime.use.accept.header = false
grails.mime.types = [ html: [
        'text/html',
        'application/xhtml+xml'
    ],
    xml: [
        'text/xml',
        'application/xml'
    ],
    text: 'text/plain',
    js: 'text/javascript',
    rss: 'application/rss+xml',
    atom: 'application/atom+xml',
    css: 'text/css',
    csv: 'text/csv',
    all: '*/*',
    json: [
        'application/json',
        'text/json'
    ],
    form: 'application/x-www-form-urlencoded',
    multipartForm: 'multipart/form-data'
]

// URL Mapping Cache Max Size, defaults to 5000
//grails.urlmapping.cache.maxsize = 1000

// The default codec used to encode data with ${}
grails.views.default.codec = "none" // none, html, base64
grails.views.gsp.encoding = "utf-8"
grails.converters.encoding = "utf-8"
// enable Sitemesh preprocessing of GSP pages
grails.views.gsp.sitemesh.preprocess = true
// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// whether to install the java.util.logging bridge for sl4j. Disable for AppEngine!
grails.logging.jul.usebridge = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []

// request parameters to mask when logging exceptions
grails.exceptionresolver.params.exclude = ['password']

// set per-environment serverURL stem for creating absolute links
environments {
    prod { grails.serverURL = "http://www.changeme.com" }
    dev { grails.serverURL = "http://localhost" }
    test { grails.serverURL = "http://localhost:8080/${appName}" }
}
environments {
    prod {
        openVar = [tbkey:"12239297", tbSecret:"c296d6c6a6fe83edd6e4bfbc4614b045",tbapi:"http://gw.api.tbsandbox.com/router/rest"]
    }
    dev {
        openVar = [
                    tbkey:"12239297",
                    tbsecret:"sandbox6a6fe83edd6e4bfbc4614b045",
                    tbapi:"http://gw.api.tbsandbox.com/router/rest",
                    tbContainer:"http://container.api.tbsandbox.com/container",
                    tbdetail:"http://mini.tbsandbox.com/minisandbox/buyer/item_detail.htm?numIid="
                ]
    }
    test {
        openVar = [tbkey:"12239297", tbSecret:"c296d6c6a6fe83edd6e4bfbc4614b045",tbapi:"http://gw.api.tbsandbox.com/router/rest"]
    }
}

// log4j configuration
log4j = {
    // Example of changing the log pattern for the default console
    // appender:
    //
    //appenders {
    //    console name:'stdout', layout:pattern(conversionPattern: '%c{2} %m%n')
    //}

    error  'org.codehaus.groovy.grails.web.servlet',  //  controllers
            'org.codehaus.groovy.grails.web.pages', //  GSP
            'org.codehaus.groovy.grails.web.sitemesh', //  layouts
            'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
            'org.codehaus.groovy.grails.web.mapping', // URL mapping
            'org.codehaus.groovy.grails.commons', // core / classloading
            'org.codehaus.groovy.grails.plugins', // plugins
            // hibernate integration
            'org.springframework',
            'net.sf.ehcache.hibernate'

    warn   'org.mortbay.log'

    info   'org.ertuo.onlyprice',
            'org.codehaus.groovy.grails.orm.hibernate',
            'org.hibernate'
}
