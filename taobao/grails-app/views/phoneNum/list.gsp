s	<head>
		<meta name="layout" content="edit" />
		<title>手机号码列表</title>
	</head>

	<body>
		<!-- end #header -->
		<!-- end #header-wrapper -->
		
		<div id="page">
			<div id="page-bgtop">

				<div>
					<ul>
						<li>
							<ul>
							     <li> <font color='red' size='5'>总条数${counts}</font> </li>
								<g:each in="${phoneNums}" var="phoneNum">
									<li>
										${phoneNum.num} ${phoneNum.prise}元  ${phoneNum.city}
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
