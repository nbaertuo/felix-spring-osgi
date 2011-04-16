// Place your Spring DSL code here
import org.codehaus.groovy.grails.commons.ConfigurationHolder as CH
beans = {

    tbClient(
            com.taobao.api.DefaultTaobaoClient,
            CH.config.openVar.tbapi,
            CH.config.openVar.tbkey,
            CH.config.openVar.tbsecret
            )
}
