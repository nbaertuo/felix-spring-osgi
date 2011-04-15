// Place your Spring DSL code here
beans = {
    
    tbClient(com.taobao.api.DefaultTaobaoClient(grailsApplication.config.openVar.tbapi,grailsApplication.config.openVar.tbkey,grailsApplication.config.openVar.tbsecret)){
        
    }
}
