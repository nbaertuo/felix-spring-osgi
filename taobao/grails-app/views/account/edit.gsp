	<head>
		<meta name="layout" content="edit" />
		<title>账户编辑</title>
	</head>
	<body>
		<!-- end #header -->
		<!-- end #header-wrapper -->
		<div id="page">
			<div id="page-bgtop">
				<div id="content">
					<div class="post">
						<form name="example" method="post" action="edit">
							<g:hiddenField name="accountId"
								value="${fieldValue(bean:creditAccount,field:'accountId')}" />

							<h2 class="title">
								<label class="label" for="login">邮件:</label>
								<input type="text" name="email"
									value="${fieldValue(bean:creditAccount,field:'email')}" size="20" />
							</h2>
							<div class="errormsg">
								<p class="error">
									<g:renderErrors bean="${creditAccount}" field="email" />
								</p>
							</div>
							
							
							
							<h2 class="title">
								<label class="label" for="login">淘宝密码:</label>
								<input type="text" name="taobaoPW"
									value="${fieldValue(bean:creditAccount,field:'taobaoPW')}" size="20" />
							</h2>
							<div class="errormsg">
								<p class="error">
									<g:renderErrors bean="${creditAccount}" field="taobaoPW" />
								</p>
							</div>
							
							
							<h2 class="title">
								<label class="label" for="login">淘宝用户名:</label>
								<input type="text" name="taobaoUS"
									value="${fieldValue(bean:creditAccount,field:'taobaoUS')}" size="20" />
							</h2>
							<div class="errormsg">
								<p class="error">
									<g:renderErrors bean="${creditAccount}" field="taobaoUS" />
								</p>
							</div>
							
							<h2 class="title">
								<label class="label" for="login">别名:</label>
								<input type="text" name="alias"
									value="${fieldValue(bean:creditAccount,field:'alias')}" size="20" />
							</h2>
							<div class="errormsg">
								<p class="error">
									<g:renderErrors bean="${creditAccount}" field="alias" />
								</p>
							</div>
							
							
								<br />
								<input type="submit" name="button" value="提交内容" />
							</div>
						</form>


					</div>
				</div>
				<!-- end #content -->
				<div id="sidebar">
				</div>
				<!-- end #sidebar -->
				<div style="clear: both;">&nbsp;
				</div>
			</div>
		</div>
	</div>
