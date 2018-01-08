<%@ page language="java" pageEncoding="utf-8"%>
<%@page import="com.navix.core.web.constant.FarmConstant"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/view/conf/farmtag.tld" prefix="PF"%>
<%@ taglib uri="/view/conf/farmdoc.tld" prefix="DOC"%>
<style>
.onselect:hover {
	box-shadow: 0 15px 30px rgba(0, 0, 0, .1);
	-webkit-transform: translate3d(0, -2px, 0);
	transform: translate3d(0, -2px, 0);
}

.round:hover img {
    box-shadow: 0 15px 30px rgba(0, 0, 0, .2);
	-webkit-transform: translate3d(-2px, -2px, -2px);
	transform:scale(1.1);//设置缩放比例
    -ms-transform:scale(1.1);
    -webkit-transform:scale(1.1);
    -o-transform:scale(1.1);
    -moz-transform:scale(1.1);
}

</style>

<!-- 模态框（Modal） -->
<div class="modal fade" id="logOffModal" tabindex="-1" role="dialog" aria-labelledby="logOffModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h5 class="modal-title" id="logOffModalLabel">
					退出
				</h5>
			</div>
			<div class="modal-body">
				确定退出登录吗？
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭
				</button>
				<button id="okBtn" type="button" class="btn btn-primary">
					确认
				</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>
<div class="navbar navbar-inverse navbar-fixed-top" role="navigation"
	style="margin: 0px;">
	<div class ="container">
	<!-- Brand and toggle get grouped for better mobile display -->
	<div class="navbar-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse"
			data-target="#bs-example-navbar-collapse-1">
			<span class="sr-only">Toggle navigation</span> 
			<span class="icon-bar"></span>
			<span class="icon-bar"></span> 
			<span class="icon-bar"></span>
		</button>
		<a class="navbar-brand round"
			style="color: #ffffff; font-weight: bold; padding: 5px;"
			href="<DOC:defaultIndexPage/>"> <img
			src="<PF:basePath/>/text/img/logo.png" height="40" alt="navix"
			title="Goto navix HP." align="middle" />
		</a>
	</div>
	<!-- Collect the nav links, forms, and other content for toggling -->
	<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1" style="height:1px" >
		<ul class="nav navbar-nav">
		    <li class="active onselect"><a href="home/Pubindex.html"><span
						class="glyphicon glyphicon-home"></span>&nbsp;首页</a></li>
	    	<li class="active onselect"><a href="webtype/view/Pub1.html"><span
					class="glyphicon glyphicon-th"></span>&nbsp;商品</a></li>
					
			<li class="active onselect"><a href="webgroup/PubHome.html"><span
					class="glyphicon glyphicon-tree-conifer"></span>&nbsp;店铺</a></li>
					
			<li class="active onselect"><a href="webstat/PubHome.html"><span
					class="glyphicon glyphicon-stats"></span>&nbsp;排行榜</a></li>
			<!--
			<li class="active"><a href="home/PubAbout.html"><span
						class="glyphicon glyphicon-phone-alt"></span>&nbsp;联系方式</a></li>
			-->
		</ul>
		
		<ul class="nav navbar-nav navbar-right" style="margin-right: 10px;">
		    <li class="active onselect"><a href="websearch/PubCodeDo.do"><span
					class="glyphicon glyphicon-camera"></span>&nbsp;</a></li>
		    <li class="active onselect"><a href="websearch/PubDo.do"><span
					class="glyphicon glyphicon-search"></span>&nbsp;</a></li>
			<c:if test="${USEROBJ==null}">
				<li class="active onselect"><a href="login/webPage.html"><span
						class="glyphicon glyphicon glyphicon-user"></span>&nbsp;登录</a></li>
			</c:if>
			<c:if test="${USEROBJ!=null}">
				<li class="active onselect"><a href="webuser/PubHome.do"><span
						class="glyphicon glyphicon-user"></span>&nbsp;${USEROBJ.name}</a></li>
				<li class="active btn-primary onselect" data-toggle="modal" data-target="#logOffModal"><a href="login/webout.html"><span
						class="glyphicon glyphicon-off"></span>&nbsp;注销</a></li>
			</c:if>
		</ul>
		
		<PF:IfParameterEquals key="config.about" val="false">
		<form action="websearch/PubDo.do" method="post"
			id="lucenesearchFormId"
			class="navbar-form navbar-left hidden-xs hidden-sm" role="search">
			<div class="form-group">
				<input type="text" name="word" id="wordId" value="${word}"
					class="form-control input-sm" placeholder="输入查询内容"> <input
					type="hidden" id="pageNumId" name="pagenum">
			</div>
			<button type="submit" class="btn btn-default btn-sm">检索</button>
			<jsp:include page="../operation/includeCreatOperate.jsp"></jsp:include>
		</form>
		</PF:IfParameterEquals>
	</div>
	<!-- /.navbar-collapse -->
    </div>
</div>
<script type="text/javascript">
	function luceneSearch(key) {
		$('#wordId').val(key);
		$('#lucenesearchFormId').submit();
	}
	function luceneSearchGo(page) {
		$('#pageNumId').val(page);
		$('#lucenesearchFormId').submit();
	}
	
	$("#okBtn").click(function (){
		$("#myModal").modal('hide');
		location.href ="login/webout.html";
	});
//-->
</script>