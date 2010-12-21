<%@ page contentType="text/html;charset=GBK" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
<head>
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
							    <li> <font color='red' size='5'>总条数</font> </li>
								<c:forEach items="${phoneNums}" var="phoneNum">
								<div>
								   ${phoneNum.num} ${phoneNum.prise}元  ${phoneNum.city} ${phoneNum.date}
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
</hTml>	
