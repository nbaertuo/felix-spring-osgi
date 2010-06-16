import org.ertuo.linliqin.service.UserService;


public class UserController {

    UserService userService

    groovy.sql.Sql sql
    
    def login={
        //println("fuck 登录")       
        //userService.getUser(1)       
        sql.eachRow("select * from test"){ row ->
	       println row.id + " " + row.name + " " + row.remark
	    }
    }

}
