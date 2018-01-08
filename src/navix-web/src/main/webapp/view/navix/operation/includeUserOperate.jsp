<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/view/conf/farmtag.tld" prefix="PF"%>

<style>
.barspace {
	margin-top: -4px;  
    margin-bottom: 10px;
}

</style>

<div class="row">
<div class="col-md-12">
	<div class="btn-group barspace" role="group" aria-label="...">
		<c:if test="${type=='joy'}">
			<c:if test="${self}">
				<a href="webuser/PubHome.do?type=joy&userid=${userid}"
					class="btn btn-info">关注商品</a>
			</c:if>
		</c:if>
		<c:if test="${type!='joy'}">
			<c:if test="${self}">
				<a href="webuser/PubHome.do?type=joy&userid=${userid}"
					class="btn btn-default">关注商品</a>
			</c:if>
		</c:if>
		<c:if test="${type=='group'}">
			<a href="webuser/PubHome.do?type=group&userid=${userid}"
				class="btn btn-info">关注店铺</a>
		</c:if>
		<c:if test="${type!='group'}">
			<a href="webuser/PubHome.do?type=group&userid=${userid}"
				class="btn btn-default">关注店铺</a>
		</c:if>
	
	    <c:if test="${type=='usermessage'}">
			<c:if test="${self}">
				<a href="webuser/PubHome.do?type=usermessage&userid=${userid}"
					class="btn btn-info">用户消息</a>
			</c:if>
		</c:if>
		
		<c:if test="${type!='usermessage'}">
			<c:if test="${self}">
				<a href="webuser/PubHome.do?type=usermessage&userid=${userid}"
					class="btn btn-default">用户消息</a>
			</c:if>
		</c:if>
	</div>
</div>
</div>

<div class="btn-toolbar" role="toolbar">
<div class="row">
<div class="col-md-12">
	<div class="btn-group barspace" role="group" aria-label="...">
		<c:if test="${type=='know'}">
			<a href="webuser/PubHome.do?type=know&userid=${userid}"
				class="btn btn-info">发布商品</a>
		</c:if>
		<c:if test="${type!='know'}">
			<a href="webuser/PubHome.do?type=know&userid=${userid}"
				class="btn btn-default">发布商品</a>
		</c:if>
		<c:if test="${type=='file'}">
			<a href="webuser/PubHome.do?type=file&userid=${userid}"
				class="btn btn-info">发布资源</a>
		</c:if>
		<c:if test="${type!='file'}">
			<a href="webuser/PubHome.do?type=file&userid=${userid}"
				class="btn btn-default">发布资源</a>
		</c:if>
	</div>
</div>
</div>

<div class="row">
<div class="col-md-12">
	<div class="btn-group barspace" role="group" aria-label="...">
		<c:if test="${type=='audit'}">
			<c:if test="${self}">
				<a href="webuser/PubHome.do?type=audit&userid=${userid}"
					class="btn btn-info">审核中</a>
			</c:if>
		</c:if>
		<c:if test="${type!='audit'}">
			<c:if test="${self}">
				<a href="webuser/PubHome.do?type=audit&userid=${userid}"
					class="btn btn-default">审核中</a>
			</c:if>
		</c:if>
		<c:if test="${type=='audith'}">
			<c:if test="${self}">
				<a href="webuser/PubHome.do?type=audith&userid=${userid}"
					class="btn btn-info">审核历史</a>
			</c:if>
		</c:if>
		<c:if test="${type!='audith'}">
			<c:if test="${self}">
				<a href="webuser/PubHome.do?type=audith&userid=${userid}"
					class="btn btn-default">审核历史</a>
			</c:if>
		</c:if>
		<c:if test="${type=='audito'}">
			<c:if test="${self}">
				<a href="webuser/PubHome.do?type=audito&userid=${userid}"
					class="btn btn-info">审核任务</a>
			</c:if>
		</c:if>
		<c:if test="${type!='audito'}">
			<c:if test="${self}">
				<a href="webuser/PubHome.do?type=audito&userid=${userid}"
					class="btn btn-default">审核任务</a>
			</c:if>
		</c:if>
	</div>
</div>
</div>

</div>
