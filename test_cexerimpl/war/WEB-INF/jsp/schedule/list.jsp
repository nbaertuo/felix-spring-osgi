<%@ page contentType="text/html;charset=GBK" language="java" isELIgnored="false" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
	<head>
		<meta name="layout" content="edit" />
		<title>任务列表</title>
	</head>
   

	<body>
		<!-- end #header -->
		<!-- end #header-wrapper -->
		<div>
			<a href="http://t.sina.com.cn/1663655120?s=6uyXnP" target="_blank"><img border="0" src="http://service.t.sina.com.cn/widget/qmd/1663655120/75d27faf/1.png"/></a>
		</div>
		<div id="page">
			<div id="page-bgtop">
			<div id="rs">
			 
			</div>

				<div>
					<ul>
						<li>
							<ul>
								<li> <font color='red' size='5'>任务执行情况列表 </font> </li>
								<c:forEach items="${pns}" var="currSp">
								<div>
								    <div>${currSp.id}&nbsp&nbsp ${currSp.currPage} &nbsp&nbsp  ${currSp.totalPage} &nbsp&nbsp  ${currSp.date} &nbsp&nbsp ${currSp.updateTOP}</div>
								</div> 
								<div id="${fn:replace(ip.ip, '.', '_')}">
								</div>  
								</c:forEach>
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
	
	
	</body>
