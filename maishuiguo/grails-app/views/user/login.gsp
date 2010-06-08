<html>
      <head>
           <title>不登陆不让你玩！</title>
           <meta name="layout" content="main" />
		  <style type="text/css" media="screen">
				form { width: 300px; }
		  		input {
					position: absolute;
					left: 130px;
			    }          
				p {	margin-left: 30px; } 
				.button { margin-top: 30px;	}
		  </style>
      </head>
      <body>
             <g:if test="${flash.message}">
				<div class="message">${flash.message}</div>
			 </g:if>
             <p>
                  欢迎常来玩。先登录的说。 
                  <g:link action="register">注册点这儿</g:link>.
             </p>        
             <form action="${request.contextPath}/j_acegi_security_check">
                     <p>
                         <label for="login">外号:</label>
                         <input type="text" name="j_username" />
                     </p>
                     <p>
                         <label for="password">密号:</label>
                         <input type="password" name="j_password" />
                     </p>
                    <input class="button" type="submit" value="冲啊" />
             </form>
      </body>
</html>
