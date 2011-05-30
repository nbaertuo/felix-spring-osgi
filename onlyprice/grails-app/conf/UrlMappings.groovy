class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?"{ constraints {
                // apply constraints here
            } }

        "/"(controller:"bid",action:"onTime")
        "500"(view:'/error')
    }
}
