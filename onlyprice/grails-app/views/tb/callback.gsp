<html>
    <head>
        <title>商品列表</title>
        <meta name="layout" content="main" />
    </head>
     
     
     <style type="text/css">
		.desc {
			border: 1px dashed #0099CC;
			margin: 3px;
			padding: 0px;
			float: left;
			height: 300px;
			width: 192px;
}
	</style>
	
	<script>
		function updateMe(response){
		 	var g = eval('(' + response.responseText + ')')
			if(!g.su){
				alert(g.msg.errors[0].message)
			}else{
				$(g.id).style.display="none"
				alert("保存成功"+g.title)
			}
		}
	</script>
		    
	<form  method="post" action="${grailsApplication.config.grails.serverURL}/tb/get"  >

				
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
								${flash.message}
							</p>
						</div>
					</p>
				</div>	
					 
				<div class="buttomDiv">
					<input type="SUBMIT" value="查询" name="submitbutton" id="submitbutton" />
				</div>

		</form>	  
		
		<g:each in="${items}">
		        <div id="${it.numIid}">
				<form action="${grailsApplication.config.grails.serverURL}/goods/ajaxAdd" id="${it.numIid}-form">
						<input type="hidden" name="fId" value="${it.numIid}"/>
						<div style="float: left;">
						    <g:set var="title" value="${it.title}" />
							<p><img src="${it.picUrl}" style="height: 124px; width: 124px;"/></p>
							<p><g:remoteLink action="ajaxAdd" id="1" controller="goods" update="msg" params="[numId:it.numIid]" >${title}</g:remoteLink></p>
							<p><span class="one-price">一口价&nbsp;￥</span><span class="shop-price">${it.price}</span></p>
							<g:textArea name="descs"  rows="5" cols="40" />
							<g:submitToRemote  update="updateYou" onSuccess="updateMe(e)" value="申请" url="[controller: 'goods', action: 'ajaxAdd']"/>
							<input onclick="new Ajax.Updater('updateYou','${grailsApplication.config.grails.serverURL}/goods/ajaxAdd',{asynchronous:true,evalScripts:true,onSuccess:function(e){updateMe(e)},parameters:Form.serialize($('${it.numIid}-form'))});return false" type="button" value="申请">
						</div>
				</form>	
				</div>	
		</g:each>  	
		<div id="msg">
		</div>
			
</html>        