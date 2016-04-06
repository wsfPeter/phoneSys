<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>管理员列表</title>
<link href="${base}/template/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/template/js/jquery.js"></script>
<script type="text/javascript" src="${base}/template/js/common.js"></script>
<script type="text/javascript" src="${base}/template/js/list.js"></script>
</head>
<body>
	<div class="path">
		管理员列表 <span>(共<span id="pageTotal">${pager.totalCount}</span>条记录)</span>
	</div>
	<form id="listForm" action="${base}/admin/list" method="get">
		<div class="bar">
			<a href="${base}/admin/add" class="iconButton">
				<span class="addIcon">&nbsp;</span>添加
			</a>
			<div class="buttonWrap">
				<a href="javascript:;" id="deleteButton" class="iconButton disabled phoneNo">
					<span class="deleteIcon">&nbsp;</span>删除
				</a>
				<a href="javascript:;" id="refreshButton" class="iconButton">
					<span class="refreshIcon">&nbsp;</span>刷新
				</a>
			</div>
			<div class="menuWrap">
				<span class="arrow">用户名：</span>
				<input type="text" id="username" name="admin.username" value="${admin.username!}" maxlength="20" />
				
				<span class="arrow">email：</span>
				<input type="text" id="email" name="admin.email" value="${admin.email!}" maxlength="20" />
				
				<span class="arrow">姓名：</span>
				<input type="text" id="name" name="admin.name" value="${admin.name!}" maxlength="20" />
				
				<input type="submit" class="button" value="查询" />
			</div>
		</div>
		<table id="listTable" class="list">
			<tr>
				<th class="check">
					<input type="checkbox" id="selectAll" />
				</th>
				<th>
					<span>用户名<span>
				</th>
				<th>
					<span>E-mail<span>
				</th>
				<th>
					<span>姓名<span>
				</th>
				<th>
					<span>最后登录日期<span>
				</th>
				<th>
					<span>状态</span>
				</th>
				<th>
					<span>创建日期<span>
				</th>
				<th>
					<span>操作</span>
				</th>
			</tr>
			<#list pager.result as admin>
				<tr>
					<td>
					    <input type="checkbox"<#if admin.isSystem> disabled title="系统内置账户不允许删除!"<#else> name="ids" value="${admin.id}"</#if> />
					</td>
					<td>
						${admin.username}
					</td>
					<td>
						${admin.email!}
					</td>
					<td>
						${admin.name!}
					</td>
					<td>
						<#if admin.loginDate??>
							<span>${admin.loginDate!?string("yyyy-MM-dd HH:mm:ss")}</span>
						<#else>
							-
						</#if>
					</td>
					<td>
						<#if admin.isAccountEnabled>
							<span class="green">正常</span>
						<#elseif !admin.isAccountEnabled>
							<span class="red"> 未启用 </span>
						</#if>
					</td>
					<td>
						<span>${admin.createDate!}</span>
					</td>
					<td>
						<a href="${base}/admin/edit?id=${admin.id}" title="编辑">[编辑]</a>
						<a href="${base}/adminPhoneNo/detail?id=${admin.id}" title="详情">[详情]</a>
					</td>
				</tr>
			</#list>
		</table>
		<#include "/pager.ftl" />
	</form>
</body>
</html>