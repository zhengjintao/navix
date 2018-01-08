<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/view/conf/farmtag.tld" prefix="PF"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<base href="<PF:basePath/>" />
<title>注册-<PF:ParameterValue key="config.sys.title" />
</title>
<meta name="description"
	content='<PF:ParameterValue key="config.sys.mate.description"/>'>
<meta name="keywords"
	content='<PF:ParameterValue key="config.sys.mate.keywords"/>'>
<meta name="author"
	content='<PF:ParameterValue key="config.sys.mate.author"/>'>
<meta name="robots" content="noindex,nofllow">
<jsp:include page="../atext/include-web.jsp"></jsp:include>
<script charset="utf-8"
	src="<PF:basePath/>/text/lib/super-validate/validate.js"></script>
<script charset="utf-8"
	src="<PF:basePath/>/text/lib/kindeditor/kindeditor-all-min.js"></script>
<link rel="stylesheet"
	href="<PF:basePath/>/text/lib/kindeditor/themes/default/default.css" />
</head>
<body>
	<jsp:include page="../commons/head.jsp"></jsp:include>
	<jsp:include page="../commons/superContent.jsp"></jsp:include>
	<div class="containerbox" style="background-color: #fff;">
		<div class="container ">
			<div class="row">
			
				<div class="col-sm-12">
				<div class="panel panel-default userbox"
						style="margin: auto; width: 300px; margin-top: 100px; margin-bottom: 10px; background-color: #fcfcfc;">
						<div class="panel-body">
					<div class="row">
						<div class="col-sm-12" style="margin-bottom: 5px;">
							<span style="color: #D9534F;" class="glyphicon glyphicon-user">用户注册</span>
							<hr />
						</div>
					</div>
					<div class="row">
						<div class="col-sm-12">
							<c:if test="${pageset.commitType=='1'}">
								<div class="alert alert-danger">${pageset.message}</div>
							</c:if>
							<form role="form" action="webuser/PubRegistCommit.do"
								id="registSubmitFormId" method="post">
								<div class="row">
									<div class="col-md-12">
										<div class="form-group">
											<label for="exampleInputEmail1"> 用户名 <span
												class="alertMsgClass">*</span>
											</label>
											<div class="row">
												<div class="col-md-12">
													<input type="text" class="form-control" name="loginname"
														id="loginnameId" placeholder="输入用户名" value="${loginname}" />
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-12">
										<div class="form-group">
											<label for="exampleInputEmail1"> 登录密码 <span
												class="alertMsgClass">*</span>
											</label>
											<div class="row">
												<div class="col-md-12">
													<input type="password" id="passwordId" class="form-control"
														name="password" placeholder="输入登录密码" value="${password}" />
											    </div>
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-12">
										<div class="form-group">
											<label for="exampleInputEmail1"> 重复密码 <span
												class="alertMsgClass">*</span>
											</label>
											<div class="row">
												<div class="col-md-12">
													<input type="password" id="passwordId2"
														class="form-control" placeholder="重新输入登录密码" />
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-2">
										<button type="button" id="registSubmitButtonId"
											class="btn btn-primary">提交</button>
									</div>
									<div class="col-md-10">
										<span class="alertMsgClass" id="errormessageShowboxId">${errorMessage }</span>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
				</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="../commons/foot.jsp"></jsp:include>
	<script type="text/javascript">
		$(function() {
			$('#registSubmitButtonId').bind('click', function() {
				if (!validate('registSubmitFormId')) {
					$('#errormessageShowboxId').text('信息输入有误，请检查！');
				} else {
					$('#registSubmitFormId').submit();
				}
			});
			KindEditor
					.ready(function(K) {
						var uploadbutton = K
								.uploadbutton({
									button : K('#uploadButton'),
									fieldName : 'imgFile',
									url : basePath + 'actionImg/PubFPuploadImg.do',
									afterUpload : function(data) {
										if (data.error === 0) {
											$('#imgShowBoxId').attr('src',
													data.url);
											$('#photoid').val(data.id);
											$('#photoid').parent().find(
													".errorMsgClass").empty();//清空验证消息(临时实现方法)
										} else {
											if (data.message == '') {
												alert("请检查上传文件类型(png、gif、jpg、bmp)以及文件大小不能超过2M");
											} else {
												alert(data.message);
											}
										}
									},
									afterError : function(str) {
										alert('自定义错误信息: ' + str);
									}
								});
						uploadbutton.fileBox.change(function(e) {
							uploadbutton.submit();
						});
					});
			validateInput('loginnameId', function(id, val, obj) {
				// 登录名
				if (valid_isNull(val)) {
					return {
						valid : false,
						msg : '不能为空'
					};
				}
				if (!valid_maxLength(val, 4 - 1)) {
					return {
						valid : false,
						msg : '不能小于4个字符'
					};
				}
				if (valid_maxLength(val, 16)) {
					return {
						valid : false,
						msg : '不能大于16个字符'
					};
				}
				return {
					valid : true,
					msg : '正确'
				};
			});
			validateInput('photoid', function(id, val, obj) {
				// 用户名
				if (valid_isNull(val)) {
					return {
						valid : false,
						msg : '不能为空'
					};
				}
				return {
					valid : true,
					msg : '正确'
				};
			});

			validateInput('passwordId', function(id, val, obj) {
				// 密码
				if (valid_isNull(val)) {
					return {
						valid : false,
						msg : '不能为空'
					};
				}
				if (!valid_maxLength(val, 6 - 1)) {
					return {
						valid : false,
						msg : '不能小于6个字符'
					};
				}
				if (valid_maxLength(val, 32)) {
					return {
						valid : false,
						msg : '不能大于32个字符'
					};
				}
				return {
					valid : true,
					msg : '正确'
				};
			});
			validateInput('passwordId2', function(id, val, obj) {
				// 重录密码
				if (valid_isNull(val)) {
					return {
						valid : false,
						msg : '不能为空'
					};
				}
				if ($('#passwordId').val() != $('#passwordId2').val()) {
					return {
						valid : false,
						msg : '两次密码输入不一样'
					};
				}
				return {
					valid : true,
					msg : '正确'
				};
			});

		});

		$('#openChooseTypeButtonId').bind('click', function() {
			$('#myModal').modal({
				keyboard : false
			})
		});

		function clickDocType(id, text) {
			$('#orgnameId').val(text);
			$('#orgid').val(id);
			$('#myModal').modal('hide');
		}
	</script>
</body>
</html>