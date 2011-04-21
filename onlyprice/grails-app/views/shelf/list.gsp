 <html>
 <head>
        <title>出价吧</title>
        <meta name="layout" content="main" />
 </head>
		<g:each in="${shelfs}">
						<div class="one-goods" style="width: 150px;">
							<p><img src="${it.goods.picUrl}" style="height: 124px; width: 124px;"/></p>
							<p><a href="${grailsApplication.config.grails.serverURL}/goods/ajaxAdd?numId=${it.goods.numIid}">${it.goods.tit.goodsle}</a></p>
							<p><span class="one-price">一口价&nbsp;￥</span><span class="shop-price">${it.goods.price}</span></p>
						</div>
		</g:each>
</html>		  