s	<head>
		<meta name="layout" content="edit" />
		<title>账户列表</title>
	</head>

	<body>
		<!-- end #header -->
		<!-- end #header-wrapper -->

		<div>
			<a href="/account/edit.gsp" >添加淘宝账号 </a>
		</div>
		
		<div>
			<form name="example" method="post" action="fastAdd">
			  <input type="text" name="email"/><input type="submit" value="快速添加"/>
			</form>
		</div>
		
		<div id="page">
			<div id="page-bgtop">

				<div>
					<ul>
						<li>
							<ul>
								<g:each in="${accounts}" var="account">
									<li>
									<div id="${account.email}">
										${account.alias}-${account.email}<input  type="button" value="登录淘宝" onclick="taobao_login('${account.taobaoUS}','${account.taobaoPW}','${account.email}')"/>
										<g:if test="${user?.email == 'nbaertuo@gmail.com'}">
											<g:link action="del" id="${account.accountId}">[del]</g:link>
											<g:link action="edit" id="${account.accountId}">[edit]</g:link>
											
										</g:if>
									<div>	

										
									</li>
								</g:each>
							</ul>
						</li>
					</ul>
				</div>
				<!-- end #sidebar -->

				<!-- end #content -->
				<div style="clear: both;">&nbsp;
				</div>
			</div>
		</div>
		<!-- end #page -->

		<!-- end #footer -->
	</div>
	<g:include view="/account/taobao_login.gsp" />
	</body>
