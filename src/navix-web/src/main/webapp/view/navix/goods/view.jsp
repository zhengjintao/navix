<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/view/conf/farmtag.tld" prefix="PF"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<base href="<PF:basePath/>" />
<title>商品一览首页-<PF:ParameterValue key="config.sys.title" /></title>
<meta name="description"
	content='<PF:ParameterValue key="config.sys.mate.description"/>'>
<meta name="keywords"
	content='<PF:ParameterValue key="config.sys.mate.keywords"/>'>
<meta name="author"
	content='<PF:ParameterValue key="config.sys.mate.author"/>'>
<meta name="robots" content="index,follow">
<jsp:include page="../atext/include-web.jsp"></jsp:include>
<script src="<PF:basePath/>text/lib/syntax-highlighter/brush.js"></script>
<link href="<PF:basePath/>text/lib/syntax-highlighter/shCore.css"
	rel="stylesheet" />
<link
	href="<PF:basePath/>text/lib/syntax-highlighter/shThemeDefault.css"
	rel="stylesheet" />
</head>
<body>
	<jsp:include page="../commons/head.jsp"></jsp:include>
	<div class="containerbox">
		<div class="container ">
			<div class="row" style="margin-top: 70px;">
            ${INFO.count()}
			</div>
			<div class="row column_box">
				<div class="col-md-12"></div>
			</div>
		</div>
	</div>
	<jsp:include page="../commons/footServer.jsp"></jsp:include>
	<jsp:include page="../commons/foot.jsp"></jsp:include>
	<script type="text/javascript">

	</script>
</body>
</html>