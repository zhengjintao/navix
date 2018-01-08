<%@page import="java.net.URLEncoder"%>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/view/conf/farmtag.tld" prefix="PF"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="row">
	<div class="col-sm-12 ">
		<form action="websearch/PubDo.do" id="websearchpubdoId" method="post">
		   
		   
			<div class="input-group">
				<input type="text" class="form-control" name="word" value="${word}"
					placeholder="请输入关键字..."> 
					<div class="input-group-btn">
					<button class="btn btn-info" type="submit">
						<span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;<span
							id="resultTypeNameId">检索</span>
					</button>
					<button type="button" class="btn btn-info dropdown-toggle"
						data-toggle="dropdown">
						<span class="caret"></span>
					</button>
					<ul class="dropdown-menu dropdown-menu-right">
						<li><a href="javascript:chooseResultTypeAll(0);">查找&nbsp;<b>全部</b></a></li>
						<li role="separator" class="divider"></li>
						<li><a href="javascript:chooseResultTypeKnow(0);">查找&nbsp;<b>商品</b></a></li>
						<li><a href="javascript:chooseResultTypeQuest(0);">查找&nbsp;<b>店铺</b></a></li>
					</ul>
					
				</div>
			</div>
			<script type="text/javascript">
				$('#searchPlusButtonId').bind(
						'click',
						function() {
							$('#websearchpubdoId').attr('action',
									"websearch/PubPlus.do");
							$('#websearchpubdoId').submit();
						});
				//选择查询商品
				function chooseResultTypeKnow() {
					$('#resultTypeNameId').text('商品');
					$('#resultTypeInputId').val('know');
					if($('#searchInput').val()){
						$('#websearchpubdoId').submit();
					}
				}
				//选择查询店铺
				function chooseResultTypeQuest() {
					$('#resultTypeNameId').text('店铺');
					$('#resultTypeInputId').val('fqa');
					if($('#searchInput').val()){
						$('#websearchpubdoId').submit();
					}
				}
				//选择查询检索
				function chooseResultTypeAll() {
					$('#resultTypeNameId').text('检索');
					$('#resultTypeInputId').val('all');
					if($('#searchInput').val()){
						$('#websearchpubdoId').submit();
					}
				}
				//初始化检索结果类型的组件
				$(function() {
					var resultType = $('#resultTypeInputId').val();
					switch (resultType) {
					case 'know':
						$('#resultTypeNameId').text('商品');
						break;
					case 'fqa':
						$('#resultTypeNameId').text('店铺');
						break;
					default:
						$('#resultTypeInputId').val('all');
						$('#resultTypeNameId').text('检索');
					}
				});
			</script>
			
		</form>
	</div>
</div>
<div class="row">
	<div class="col-sm-12 ">
		<c:forEach items="${hotCase}" var="node">
			<a href="websearch/PubDo.do?word=<PF:urlEncode val="${node}"/>">
				<span class="label label-danger" style="cursor: pointer;">${fn:length(node) <= 10 ? node : fn:substring(node,0,10) }</span>
			</a>
		</c:forEach>
	</div>
</div>
