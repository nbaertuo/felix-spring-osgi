import org.ertuo.linliqin.domain.User;
import org.ertuo.linliqin.service.UserService;
import org.slf4j.LoggerFactory;
import java.util.logging.Logger;

public class UserController {

    private final static Logger log = Logger.getLogger("UserController");

    private final static org.slf4j.Logger logslf = LoggerFactory.getLogger(UserController.class);

    private final static org.apache.log4j.Logger log4j=org.apache.log4j.Logger.getLogger(UserController.class)

    UserService userService

    groovy.sql.Sql sql
    
    def login={
        println("fuck 开始")
        //log.log(Level.INFO,"开始fuck日志")
        logslf.info("开始fuck logslf 日志")
        log4j.info("开始fuck log4j 日志")
        userService.getUser(1)       
        /*sql.eachRow("select * from test"){ row ->
	       log.log(Level.INFO, row.id + " " + row.name + " " + row.remark)
	    }*/
    }
    def register = {}
    
    def handleRegistration = {
                              def user = new User()
                              if(params.password != params.confirm) {
                                  flash.message = "The two passwords you entered dont match!"
                                  redirect(action:register)
                              }
                              else {
                                  
                                  //user.properties = params
                                  bindData(user,params)
                                  user.properties.each{
                                      println(it)
                                  }
                                  try {
                                      userService.save(user)
                                } catch (Exception e) {
                                	    e.printStackTrace()
                                        flash.user = user
                                       return redirect(action:register)
                                }
                                redirect(action:'query')  
                                   
                                           
                              }
                          }
    def query={
         def user=userService.findByLogin()
         return [user:user]
    }

}
