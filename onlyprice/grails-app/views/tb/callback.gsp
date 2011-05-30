<html>
    <head>
        <title>商品列表</title>
        <meta name="layout" content="main" />
    </head>
    ${session.top_session}
		    
	<form  method="post" action="${grailsApplication.config.grails.serverURL}/tb/get"  >

				
			<div>
				<div class="abc">
					<span class="gaia ops gsl">
					 选择出售中的商品	
 	 			</span>
					<p>
						<label class="label" for="login">商品名称:</label>
						<input type="text" name="q"
							value="${q}" />
						<div class="errormsg">
							<p class="error">
								${msg}
							</p>
						</div>
					</p>
					 
				<div class="buttomDiv">
					<input type="SUBMIT" value="查询" name="submitbutton" id="submitbutton"
						style="width: 10em;">
				</div>
			</div>

		</form>	  
		
		<g:each in="${items}">
						<div class="one-goods" style="width: 150px;">
						    <g:set var="title" value="${it.title}" />
							<p><img src="${it.picUrl}" style="height: 124px; width: 124px;"/></p>
							<p><g:remoteLink action="ajaxAdd" id="1" controller="goods" update="msg" params="[numId:it.numIid]" >${title}</g:remoteLink></p>
							<p><span class="one-price">一口价&nbsp;￥</span><span class="shop-price">${it.price}</span></p>
						</div>
		</g:each>  	
		<div id="msg">
		</div>
			
</html>        