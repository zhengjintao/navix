<%@ page language="java" pageEncoding="utf-8"%>
<%@page import="java.net.InetAddress"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/view/conf/farmtag.tld" prefix="PF"%>
<div class="panel panel-default" style="background-color: #FCFCFA;">
	<div class="panel-body">
		<p>
			<span class="glyphicon glyphicon-asterisk  ">勤劳店家排名</span>
		</p>
		<table class="table" style="font-size: 12px;">
			<tr>
				<th>
					排名
				</th>
				<th>
					名称
				</th>
				<th>
					商品数量
				</th>
			</tr>
			<c:forEach items="${manyUsers.resultList}" varStatus="status" var="node">
				<tr>
					<td>
						${status.index+1}
					</td>
					<td>
						${node.NAME}
					</td>
					<td>
						${node.NUM}
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</div>