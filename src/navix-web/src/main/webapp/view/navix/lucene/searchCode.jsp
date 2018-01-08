<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/view/conf/farmtag.tld" prefix="PF"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<base href="<PF:basePath/>" />
<title>检索-<PF:ParameterValue key="config.sys.title" /></title>
<meta name="description"
	content='<PF:ParameterValue key="config.sys.mate.description"/>'>
<meta name="keywords"
	content='<PF:ParameterValue key="config.sys.mate.keywords"/>'>
<meta name="author"
	content='<PF:ParameterValue key="config.sys.mate.author"/>'>
<meta name="robots" content="index,follow">
<jsp:include page="../atext/include-web.jsp"></jsp:include>
<style>
.deqr_a {
	cursor: pointer;
	display: block;
	height: 200px;
	width: 250px;
	text-align:center;
	background-color: #fff;
	margin-top: -5px;  
    margin-bottom: 20px;
	transition: box-shadow 300ms linear, -webkit-transform 300ms linear,
		-moz-transform 300ms linear, -o-transform 300ms linear, transform
		300ms linear, background-color 300ms linear
}

.deqr_a:hover {
	box-shadow: 0 15px 30px rgba(0, 0, 0, .1);
	-webkit-transform: translate3d(0, -2px, 0);
	transform: translate3d(0, -2px, 0);
	background-color: #ffe
}

</style>
</head>
<body>
	<jsp:include page="../commons/head.jsp"></jsp:include>
	<jsp:include page="../commons/superContent.jsp"></jsp:include>
	
	<div class="containerbox">
		<div class="container">
			<div style="margin-top: 20px;"></div>
			<div class="row column_box">
				<ul>
					<li>
						<div class="col-lg-4">
							<div id="deqr_camera" class="deqr_a">
								<img class="img-thumbnail" src="text/img/camera.png" alt="navix"
									style="margin: 20px;" />
								<div class="deqr_logo_title">电脑摄像头扫描二维码</div>
								<input type="hidden" name="deqr" value="deqr" />
							</div>
						</div>
					</li>
					
					<li>
						<div class="col-lg-4">
							<div id="deqr_upload" class="deqr_a">
								<img class="img-thumbnail" src="text/img/upload.png" alt="navix"
									style="margin: 20px;" />
								<div class="deqr_logo_title">上传二维码图片</div>
								<input type="file" id="filedatacode" name="Filedata" hidden=true/>
							</div>
						</div>
					</li>
					<li>
						<div class="col-lg-4">
								<div id="deqr_weburl" class="deqr_a">
									<img class="img-thumbnail" src="text/img/weburl.png"
										alt="navix" style="margin: 20px;" />
									<div class="deqr_logo_title" id="qr-url">输入二维码图片网址</div>
									<input type="hidden" name="deqr" value="deqr"/>
									<div class="input-group input-group-sm m-a-md none"
										id="deqr_url_show">
										<input id="deqr_urls" class="form-control" hidden=true
											name="deqr_url_input" type="text" placeholder="http://"
											ondragenter="return false" value=""> <span
											class="input-group-addon light" id="deqr_url_btn">扫描</span>
									</div>
								</div>
						</div>
					</li>
				</ul>
			</div>
		</div>
	</div>
	<jsp:include page="../commons/footServer.jsp"></jsp:include>
	<jsp:include page="../commons/foot.jsp"></jsp:include>
</body>
</html>