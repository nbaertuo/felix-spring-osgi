<%@ page contentType="text/html;charset=GBK" language="java" isELIgnored="false" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
	<head>
		<meta name="layout" content="edit" />
		<title>城市列表</title>
	</head>
	<body>
		<!-- end #header -->
		<!-- end #header-wrapper -->
		<div><a href="http://t.sina.com.cn/1663655120?s=6uyXnP" target="_blank"><img border="0" src="http://service.t.sina.com.cn/widget/qmd/1663655120/75d27faf/1.png"/></a></div>
		<div id="page">
							 <c:forEach items="${cys}" var="cy">
								<div>
								   ${cy.chsName} &nbsp&nbsp ${cy.number} &nbsp&nbsp  ${cy.cookies} &nbsp&nbsp ${cy.itemId} &nbsp&nbsp&nbsp&nbsp  ${cy.title} &nbsp&nbsp&nbsp&nbsp <a href="del?id=${cy.number}" >删除城市和任务</a>
								</div>   
							</c:forEach>
		</div>
	
	
	</body>
