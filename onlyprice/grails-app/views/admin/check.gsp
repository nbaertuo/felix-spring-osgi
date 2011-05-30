
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>竞价商品发布</title>
    </head>
    <body>
		<div id="center-column">
			<div class="top-bar">
				<h1>竞价商品发布</h1>
				<div class="breadcrumbs">
					<a class="home" href="${createLinkTo(dir:'')}">Home</a> / 
					<g:link class="list" action="list">User List</g:link>
				</div>
			</div><br />
			<div class="select-bar-border-no"></div>
	        <div class="body">
	            <g:form action="check" method="post" >
	            	<input type="hidden" name="goods.fId" value="${sf?.goods?.fId}">
	            	<input type="hidden" name="owner" value="${sf?.owner}">
	                <div class="dialog">
	                	<div class="subdialog">
	                		<p>
                                 <label>商品名称:</label>
                                 ${sf?.goods?.title}
                            </p>
                            <p>
                                 <label>商品价格:</label>
                                 ${sf?.goods?.price}
                            </p>
                            <p class='value ${hasErrors(bean:sf,field:'onTime','errors')}'>
                                 <label for='onTime'>货架中商品最近下架时间:</label>
                                 <g:formatDate format="yyyy-MM-dd HH:MM" date="${sf.onTime}"/>
                            </p>
                            <p class='value ${hasErrors(bean:sf,field:'onTime','errors')}'>
                                 <label for='offTime'>上架时间:</label>
                                 <g:datePicker name="onTime" value="${sf?.onTime?:new Date()}" precision="minute" years="${2011..2015}"/>
                                 
                            </p>
                            <p class='value ${hasErrors(bean:sf,field:'waitTime','errors')}'>
                                 <label for='waitTime'>停滞间隔时间:（单位秒）</label>
                                 <input type="text" id='waitTime' name='waitTime' value="${sf?.waitTime?.encodeAsHTML()}"/>
                            </p>
                            <p class='value ${hasErrors(bean:sf,field:'utils','errors')}'>
                                 <label for='utils'>出价单位:</label>
                                 <input type="text" id='utils' name='utils' value="${sf?.utils?.encodeAsHTML()}"/>
                            </p>
                            
	                	</div>                        
	                </div>
	                <div class="buttons">
	                    <span class="button"><input class="save" type="submit" value="发布"></input></span>
	                </div>
	            </g:form>
	    	</div>
        </div>
		<div id="right-column">
            <g:if test="${flash.message}">
				<strong class="h">INFO</strong>
	            <div class="box">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${sf}">
				<strong class="h">ERROR</strong>
                <div class="box"><g:renderErrors bean="${sf}" as="list" /></div>
            </g:hasErrors>
		</div>
    </body>
</html>
