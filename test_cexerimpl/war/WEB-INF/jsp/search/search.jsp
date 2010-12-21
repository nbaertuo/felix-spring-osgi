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
	
    function xpaths(){
   		var url=$('#url').attr('value');
    	var xpath=$('#xpath').attr('value');
        $.get("search/xpath",{url:url,xpath:xpath}, function(data){
  			//$("#"+id).attr('innerHTML',data);
  			if(data!='没有找到商品'){
  					//$('#rs').append('找到商品-'+data);
  					$('#rs').append('</br>开始大规模模拟')
  					xishuashua(data);
  			}else{
  					$('#rs').append('</br>没有找到商品，请更换url或者关键字')
  			}
    	}); 
   	 }
   	 
   	 
   function xishuashua(key){
   		var url=$('#url').attr('value');
    	var xpath=$('#xpath').attr('value');
		$("title").everyTime(25000,function(i) {
		    $.get("search/click",{url:url,xpath:xpath,key:key}, function(data){
  			if(data!='没有找到商品'){
  				$('#rs').append('</br>'+i+data)
  			}
    	});   
		});
	}
	</script>

	<body>
		<!-- end #header -->
		<!-- end #header-wrapper -->
		<div><a href="http://t.sina.com.cn/1663655120?s=6uyXnP" target="_blank"><img border="0" src="http://service.t.sina.com.cn/widget/qmd/1663655120/75d27faf/1.png"/></a></div>
							<form name="example" method="get" action="xpath">

							<h2 class="title">
								<label class="label" for="login">搜索地址:</label>
								<input type="text" id="url" name="url" value="${url}" size="90" />
							</h2>
							<h2 class="title">
								<label class="label" for="login">关键字:</label>
								<input type="text" id="xpath" name="xpath" value="${xpath}" size="90" />
							</h2>
								<br />
								<input type="button" name="button" value="喜唰唰喜唰唰" onclick="xpaths()"/>
							</div>
						</form>
			<div id="rs">
			 
		</div>
	
	
	</body>
