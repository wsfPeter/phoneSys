<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>角色列表</title>
<link href="${base}/template/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/template/js/jquery.js"></script>
<script type="text/javascript" src="${base}/template/js/common.js"></script>
<script type="text/javascript" src="${base}/template/js/list.js"></script>
</head>
<body>
	<div class="path">
		<a href="${base}/common/index">首页</a> &raquo; 角色列表 <span>(共<span id="pageTotal">${pager.totalCount}</span>条记录)</span>
	</div>
	<form id="listForm" action="${base}/role/list" method="get">
		<div class="bar">
			<a href="${base}/role/add" class="iconButton">
				<span class="addIcon">&nbsp;</span>添加
			</a>
			<div class="buttonWrap">
				<a href="javascript:;" id="deleteButton" class="iconButton disabled">
					<span class="deleteIcon">&nbsp;</span>删除
				</a>
				<a href="javascript:;" id="refreshButton" class="iconButton">
					<span class="refreshIcon">&nbsp;</span>刷新
				</a>
			</div>
			<div class="menuWrap">
				<span class="arrow">角色名称：</span>
				<input type="text" id="roleName" name="roleName" value="${roleName!}" maxlength="20" />
				<input type="submit" class="button" value="查询" />
			</div>
		</div>
		<table id="listTable" class="list">
			<tr>
				<th class="check">
					<input type="checkbox" id="selectAll" />
				</th>
				<th>
					<a href="javascript:;" class="sort" name="name">角色名</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="description">描述</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="isSystem">是否是系统内置</a>
				</th>
				<th>
					<span>操作</span>
				</th>
			</tr>
			<#list pager.result as role>
				<tr>
					<td>
					    <input type="checkbox"<#if role.isSystem> disabled title="系统内置角色不允许删除!"<#else> name="ids" value="${role.id}"</#if> />
					</td>
					<td>
						&nbsp;${role.name}
					</td>
					<td>
                        &nbsp;${role.description!}
					</td>
					<td>
						<#if role.isSystem>
							<span class="green">是</span>
						<#else>
							<span class="red"> 否 </span>
						</#if>
					</td>
					<td>
						<a href="${base}/role/edit?id=${role.id}" title="编辑">[编辑]</a>
					</td>
				</tr>
			</#list>
		</table>
		<#include "/pager.ftl" />
	</form>
</body>
</html>