<%@ page language="java" pageEncoding="utf-8"%>
<%@page import="com.navix.core.web.constant.FarmConstant"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/view/conf/farmtag.tld" prefix="PF"%>
<%@ taglib uri="/view/conf/farmdoc.tld" prefix="DOC"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<base href="<PF:basePath/>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>首页-<PF:ParameterValue key="config.sys.title" /></title>
<meta name="description"
	content='<PF:ParameterValue key="config.sys.mate.description"/>'>
<meta name="keywords"
	content='<PF:ParameterValue key="config.sys.mate.keywords"/>'>
<meta name="author"
	content='<PF:ParameterValue key="config.sys.mate.author"/>'>
<meta name="robots" content="index,follow">
<jsp:include page="atext/include-web.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="commons/head.jsp"></jsp:include>
	<jsp:include page="commons/superContent.jsp"></jsp:include>
	<!-- /.carousel -->
	<div class="containerbox">
		<div class="container">
			<div style="margin-top: 20px;"></div>
			<div class="widget-box shadow-box hidden-xs hidden-sm">
				<div class="stream-list p-stream">
					<div class="stream-item" id="loop-30">
						<PF:IfParameterEquals key="config.sys.news.topmax.show" val="true">
							<div class="row">
								<div class="col-sm-12">
								<!-- 滚动推荐 -->
									<jsp:include page="commons/includeMaxTopKnows.jsp"></jsp:include>
								</div>
							</div>
						</PF:IfParameterEquals>
					</div>
				</div>
			</div>
			<div style="margin-top: 20px;"></div>
			<div class="row">
				<div class="col-xs-12 col-md-3 side-right hidden-xs hidden-sm">
				    <!-- 热门商品 -->
					<jsp:include page="commons/includeHotKnowsMin.jsp"></jsp:include>
					<!-- 店铺 -->
					<jsp:include page="docgroup/commons/includeHomePubGroup.jsp"></jsp:include>
				</div>
				<div class="col-sm-9">
					<PF:IfParameterEquals key="config.sys.news.topmin.show" val="false">
						<jsp:include page="commons/includeTopKnows.jsp"></jsp:include>
					</PF:IfParameterEquals>
					<!-- 最新商品 -->
					<jsp:include page="commons/includeNewKnows.jsp"></jsp:include>
					<!-- 商品分类 -->
					<jsp:include page="commons/includePubType.jsp"></jsp:include>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="commons/footServer.jsp"></jsp:include>
	<jsp:include page="commons/foot.jsp"></jsp:include>
</body>
</html>