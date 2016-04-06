<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>用户手机详情</title>
<link href="${base}/template/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/template/js/jquery.js"></script>
<script type="text/javascript" src="${base}/template/js/common.js"></script>
<script type="text/javascript" src="${base}/template/js/list.js"></script>
<script type="text/javascript">
  $().ready(function() {
      $("#statu").val('${statu!}');
  });
</script>
</head>
<body>
	<div class="path">
		 用户手机列表 <span>(共<span id="pageTotal">${pager.totalCount}</span>条记录)</span>
	</div>
	<form id="listForm" action="${base}/adminPhoneNo/detail" method="get">
		<div class="bar">
			<div class="buttonWrap">
				<a href="javascript:;" id="refreshButton" class="iconButton">
					<span class="refreshIcon">&nbsp;</span>刷新
				</a>
			</div>
			<div class="menuWrap">
				<span class="arrow">手机号：</span>
				<input type="hidden" name="id" value="${id!}" />
				<input type="text" id="phoneNo" name="phoneNo" value="${phoneNo!}" maxlength="11" />
				<input type="submit" class="button" value="查询" />
			</div>
		</div>
		<table id="listTable" class="list">
			<tr>
				<th>
					<span>用户名</span>
				</th>
				<th>
					<span>姓名</span>
				</th>
				<th>
					<span>机主姓名</span>
				</th>
				<th>
					<span>手机号</span>
				</th>
			</tr>
			<#list pager.result as papers>
				<tr>
					<td>
						${papers.admin.username!}
					</td>
					<td>
						${papers.admin.name!}
					</td>
					<td>
						${papers.phoneName!}
					</td>
					<td>
						${papers.phoneNo!}
					</td>
				</tr>
			</#list>
		</table>
		<#include "/pager.ftl" />
	</form>
</body>
</html>