<%@ page contentType="text/html;charset=GBK" language="java" isELIgnored="false" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
	<head>
		<meta name="layout" content="edit" />
		<title>刷淘宝流量，宝贝流量，网店流量</title>
	</head>
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.3.1.min.js"></script> 
    <script type="text/javascript" src="/js/jquery.timers-1.1.2.js"></script> 
	<script>
	var urls='${url}'
	function xishuashua(){
		$("title").everyTime(20000,function(i) {
		    var temp = new Array(); 
		    temp = $('#ids').attr('value').split(',');
		    var n = Math.floor(Math.random() * temp.length + 1)-1;  
      		urls="http://item.taobao.com/item.htm?id="+temp[n];
			$.get("viewUrl",{url:urls}, function(data){
            	//var id=ip.replace(/\./g,'_');
  				$("#rs").append(i+data);
  				$("#rs").append('<br />');
    		});  
		});
	}
	
	
	
	
    function viewUrl(ip,url){
    	if(urls==null){
    		return;
   		 }
        $.get("viewUrl",{url:urls,ip:ip}, function(data){
            var id=ip.replace(/\./g,'_');
  			$("#"+id).attr('innerHTML',data);
    }); 
    }
</script>

	<body>
		<!-- end #header -->
		<!-- end #header-wrapper -->
		<div><a href="http://t.sina.com.cn/1663655120?s=6uyXnP" target="_blank"><img border="0" src="http://service.t.sina.com.cn/widget/qmd/1663655120/75d27faf/1.png"/></a></div>
							<form name="example" method="get" action="list">

							<h2 class="title">
								<label class="label" for="login">需要刷的宝贝的id:</label>
								<label class="label" for="login">例如http://item.taobao.com/item.htm?id=8168953761中的8168953761</label>
								<input type="text" id="ids" name="url" value="8548510277,8168953761,8548766647" size="90" />
							</h2>
								<br />
								<input type="button" onclick="xishuashua()" name="button" value="喜唰唰喜唰唰" />
							</div>
						</form>
		<div id="page">
			<div id="page-bgtop">
			<div id="rs">
			 
			</div>

				<div>
					<ul>
						<li>
							<ul>
								<li> <font color='red' size='5'>代理服务器列表 </font> </li>
								<c:forEach items="${ips}" var="ip">
								<div>
								   <a href="#" onclick="viewUrl('${ip.ip}','http://item.taobao.com/item.htm?id=8457703455')">${ip.ip}</a>
								   <script>
								   		//viewUrl('${ip.ip}','http://item.taobao.com/item.htm?id=8457703455')
								   </script>  
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
