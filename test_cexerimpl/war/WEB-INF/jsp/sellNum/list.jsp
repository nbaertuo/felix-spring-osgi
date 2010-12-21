<%@ page contentType="text/html;charset=GBK" language="java" isELIgnored="false" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
	<head>
		<meta name="layout" content="edit" />
		<title>手机号码列表</title>
	</head>

	<body>
		<!-- end #header -->
		<!-- end #header-wrapper -->
							<form name="example" method="post" action="list">

							<h2 class="title">
								<label class="label" for="login">时间:</label>
								<input type="text" name="date" value="20100915" size="20" />
							</h2>
							
							<h2 class="title">
								<label class="label" for="login">城市:</label>
								<input type="text" name="city"
									value="武汉" size="20" />
							</h2>
								<br />
								<input type="submit" name="button" value="提交内容" />
							</div>
						</form>
		<!-- end #page -->

		<!-- end #footer -->
		<div align="center"><span style="font-family: 仿宋_gb2312; color: black; font-size: 16pt;"><span style="font-family: 华文细黑; font-size: 16pt;"><strong><font size="7" face="黑体" color="#ff0000" style="background-color: rgb(255, 255, 0);">${updateTime}更新</font></strong></span></span></div>
		<div align="center"><span style="font-family: 仿宋_gb2312; color: black; font-size: 16pt;"><span style="font-family: 华文细黑; font-size: 16pt;"><strong><font size="6" face="黑体" color="#ff00ff" style="background-color: rgb(255, 255, 0);">（定期更新，敬请关注）</font></strong></span></span></div>
		
		<c:forEach items="${sellNums}" var="num">
		<p style="margin-top: 0pt; margin-bottom: 0pt; margin-left: 54pt;">
			${num.display}
			<span style="font-family: times new roman; font-size: 22pt;">${num.display}</span>
			<span style="font-family: times new roman; font-size: 22pt;">&nbsp;预存款</span>
			<span style="font-family: times new roman; color: rgb(255, 0, 0); font-size: 22pt;">${num.phoneNum.prise}</span>
			<span style="font-family: times new roman; font-size: 22pt;"></span>
		</p>
		</c:forEach>	
		
		
		<div align="center"><span style="font-family: 仿宋_gb2312; color: black; font-size: 16pt;"><span style="font-family: 华文细黑; font-size: 16pt;"><font size="6" face="黑体" color="#ff0000">还在犹豫什么。。。</font></span></span></div>
		<div align="center"><span style="font-family: 仿宋_gb2312; color: black; font-size: 16pt;"><span style="font-family: 华文细黑; font-size: 16pt;"><font size="6" face="黑体" color="#ff0000"></font></span></span>&nbsp;</div>
		<div align="left"><span style="font-family: 仿宋_gb2312; color: black; font-size: 16pt;"><span style="font-family: 华文细黑; font-size: 16pt;"><font size="5"><font color="#ff0000"><font size="6"><font face="黑体">传统的3G号码挑选是自己进入网上营业厅上千页面中翻找，费时费力，看得眼花撩乱还不一定能挑选出满意的号段，本店所售3G号码均为联通网上营业厅中普号中的精品号段，现在本店采用“程序设计”为您精心挑选出普通中的精品，精品中的极品，让你能轻松即得满意的3G号码啦～～快来啊～～。</font></font></font></font></span></span></div>
	</div>
	</body>
