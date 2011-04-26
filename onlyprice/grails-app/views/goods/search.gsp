<html>
    <head>
        <title>待上架商品列表</title>
        <meta name="layout" content="main" />
    </head>
    ${session.top_session}
		    
	<form  method="post" action="${grailsApplication.config.grails.serverURL}/goods/search"  >

				
			<div>
				<div class="abc">
					<span class="gaia ops gsl">
					 选择出售中的商品	
 	 			</span>
					<p>
						<label class="label" for="login">价格区间:</label>
						<input type="text" name="bPrice" id="bPrice" value="${bPrice}" />
							到
						<input type="text" name="ePrice" value="${ePrice}" id="ePrice"/>	
						<div class="errormsg">
							<g:if test="${flash.message}">
								<strong class="h">INFO</strong>
	            				<div class="box">${flash.message}</div>
            				</g:if>
						</div>
					</p>
					 
				<div class="buttomDiv">
					<input type="SUBMIT" value="查询" name="submitbutton" id="submitbutton"
						style="width: 10em;" onclick="fill()">
				</div>
			</div>

		</form>	  
		
		<g:each in="${list}">
						<div class="one-goods" style="width: 150px;">
							<p><g:remoteLink action="ajaxAdd" id="1" controller="goods" update="msg" params="[numId:it.numIid]" > <img src="${it?.picUrl}" style="height: 124px; width: 124px;"/></g:remoteLink></p>
							<p><a href="${grailsApplication.config.grails.serverURL}/goods/ajaxAdd?numId=${it.numIid}">${it.title}</a></p>
							<p><span class="one-price">一口价&nbsp;￥</span><span class="shop-price">${it.price}</span></p>
						</div>
		</g:each> 
		<g:if test="${count}"> 	
			<g:paginate total="${count}" params="${params}" />
		</g:if>
		<div id="msg">
		</div>
		
		<script>
			function fill(){
				alert(present($('ePrice'))) 
				if(present($('ePrice'))){
					alert("请填写金额")
				}
			}
		</script>
			
</html>        