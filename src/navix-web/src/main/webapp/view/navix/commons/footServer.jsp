<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/view/conf/farmtag.tld" prefix="PF"%>
<div class="super_content  hidden-xs" style="margin: 0px;">
	<div class="container ">
		<div class="row">
			<div class="col-sm-2">
				<jsp:include page="../commons/includeTowDCode.jsp"></jsp:include></div>
			<div class="col-sm-2">
				<a href="<PF:basePath/>"> <img title="扫描二维码访问公众号" alt="扫描二维码访问公众号"
					class="img-thumbnail" style="text-align: center; max-width: 110px;"
					src="text/img/wxgzh.png" /></a>
			</div>
			<div class="col-sm-8 putServerBox">
				<ul id="recommendServiceList" style="float: left;">
					<c:if test="${USEROBJ!=null}">
						<li style='width: 100px; height: 100px;'><a
							href="frame/index.do"><img src="text/img/consoleIcon.png"
								alt="navix商品库" class="img-thumbnail"
								style="width: 64px; height: 64px;" /><br />管理控制台</a></li>
					</c:if>
				</ul>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	//页面加载完毕，执行如下方法。
	$(function() {
		$
				.post(
						"home/PubrecommendServiceList.do",
						{},
						function(data) {
							for (i in data) {
								var url = '<li style="width: 100px;height: 100px;" ><a href="'+data[i].URL+'" target="_blank"><img alt="" src="'+data[i].IMGURL+'" class="img-thumbnail" style="width: 64px;height: 64px;"/><br/>'
										+ data[i].WEBNAME + '</a></li>';
								$("#recommendServiceList").append(url);
							}
						}, "json");
	});
</script>