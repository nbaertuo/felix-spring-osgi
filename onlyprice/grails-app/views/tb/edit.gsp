 <head>
        <title>添加</title>
        <meta name="layout" content="main" />
 </head>
<form  method="post" action="${grailsApplication.config.grails.serverURL}/price/edit"  >

				
			<div>
				<div class="abc">
					<span class="gaia ops gsl">
					  系统信息	
 	 			</span>
					<p>
						<label class="label" for="login">标题:</label>
						<input type="text" name="title"
							value="${fieldValue(bean:p,field:'title')}" />
						<div class="errormsg">
							<p class="error">
								<g:renderErrors bean="${p}" field="title" />
							</p>
						</div>
					</p>
					
					<p>
						<label class="label" for="login">描述:</label>
						<input type="text" name="description"
							value="${fieldValue(bean:p,field:'description')}" />
						<div class="errormsg">
							<p class="error">
								<g:renderErrors bean="${p}" field="description" />
							</p>
						</div>
					</p>
				<div class="buttomDiv">
					<input type="SUBMIT" value="你确定？" name="submitbutton" id="submitbutton"
						style="width: 10em;">
				</div>
			</div>

		</form>