import org.ertuo.linliqin.service.UserService;


public class UserController {

    UserService userService
    
    def login={
        println("fuck 登录")       
        userService.getUser(1)       
        
    }

}
