<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="PF" uri="/view/conf/farmtag.tld"%>
<div class="foot">
	<div class="container">
		<div class="row">
			<div class="col-sm-6 col-lg-6">
				<h3 class="round">
					<img src="text/img/logo.png" height="80" alt="navix"
						title="Goto navix HP." align="left" />
				</h3>
				<p>
					本网为客户提供最新药妆热门商品信息，提供商品比价，店铺导航等诸多功能，欢迎使用。<br>
				</p>
			</div>
			<div class="col-sm-6 col-lg-5">
				<div class="row about">
					<div class="col-xs-4">
						<h4>关于</h4>
						<ul class="list-unstyled">
							<li><a href="/about/">关于我们</a></li>
							<li><a href="/links/">友情链接</a></li>
						</ul>
					</div>
					<div class="col-xs-4">
						<h4>联系方式</h4>
						<ul class="list-unstyled">
							<li><a href="" onmouseout="hiddenPic();" onmouseover="showPic(event,'text/img/wxgzh.png');">微信公众号</a></li>
							<li><a href="mailto:xiaonei0912@hotmail.com">电子邮件</a></li>
						</ul>
					</div>
					<div class="col-xs-4">
						<h4>赞助商</h4>
						<ul class="list-unstyled">
							<li><a href="https://www.baidu.com" target="_blank">广告位</a></li>
							<li><a href="/ad/">广告合作</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<div id="Layer1" style="display: none; position: absolute; z-index: 100;">
</div>
<script type="text/javascript">
	$(function() {
		$(window).resize(function() {
			$('.containerbox').css('min-height', $(window).height() - 70);
		});
		$('.containerbox').css('min-height', $(window).height() - 70);
	});
	
	 function showPic(e,sUrl){ 
         var x,y; 
         x = e.clientX + 5; 
         y = e.clientY - 5; 
         document.getElementById("Layer1").style.left = x+'px'; 
         document.getElementById("Layer1").style.top = y+'px'; 
         document.getElementById("Layer1").innerHTML = "<img border='0' src=\"" + sUrl + "\">"; 
         document.getElementById("Layer1").style.display = ""; 
         } 
         function hiddenPic(){ 
         document.getElementById("Layer1").innerHTML = ""; 
         document.getElementById("Layer1").style.display = "none"; 
     } 
</script>