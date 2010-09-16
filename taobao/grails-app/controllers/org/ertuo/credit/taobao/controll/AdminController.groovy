package org.ertuo.credit.taobao.controll

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;


public class AdminController {
	
	final UserService userService = UserServiceFactory.getUserService();
	
	static defaultAction = "login"

	def login={
		
		redirect(url:userService.createLoginURL("/account/list"))

	}
}
