<%@ page contentType="text/html;charset=GBK" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
	<head>
		<meta name="layout" content="edit" />
		<title>result</title>
	</head>
	<script>
	if('${city}'!=null&&'${city}'!=""){
		window.location.href='/app/schedule/check?city='+'${city}'
	}
	</script>
	<body>
	    <form name="example" method="post" action="add">

							<h2 class="title">
								<label class="label" for="login">城市名称:</label>
								<input type="text" name="chsName"  size="20" />
							</h2>
							
							<h2 class="title">
								<label class="label" for="login">城市代码:</label>
								<input type="text" name="city" size="20" />
							</h2>
							<h2 class="title">
								<label class="label" for="login">淘宝商品id:</label>
								<input type="text" name="itemId" size="20" />
							</h2>
							<h2 class="title">
								<label class="label" for="login">淘宝商品标题:</label>
								<input type="text" name="title" size="20" />
							</h2>
								<br />
								<input type="submit" name="button" value="提交内容" />
							</div>
						</form>
	</body>
