<html>
    <head>
        <title>货架商品列表</title>
        <meta name="layout" content="main" />
    </head>
    ${session.top_session}
		    
		
		<g:each in="${list}" status="i" var="sf">
			<p>
			<div class="one-goods" style="width: 300px;">
			 ${i} <a href="<g:createLink  action="mod" params="[fId:sf.goods.fId,owner:sf.owner]" absolute="true"/>" > ${sf.goods.title}  <g:formatDate format="yyyy-MM-dd HH:MM" date="${sf.onTime}"/> </a>
			</div>
			</p>
		</g:each> 
		
		 
			
</html>        