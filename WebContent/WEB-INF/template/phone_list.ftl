<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>部门管理</title>
<link href="${base}/template/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/template/js/jquery.js"></script>
<script type="text/javascript" src="${base}/template/js/common.js"></script>
<script type="text/javascript" src="${base}/template/js/list.js"></script>
</head>
<script type="text/javascript">
$().ready(function() {
   $("#phoneNoFenPei").val('${phoneNoStatu!}');
   $("#statu").val('${statu!}');
	var $delete = $("#listTable a.delete");
	var $listTable = $("#listTable");
	var $pageTotal = $("#pageTotal");
	// 删除
	$delete.click(function() {
		var $this = $(this);
		$.dialog({
			type: "warn",
			content: "您确定要删除吗？",
			onOk: function() {
				$.ajax({
					url: "${base}/organization/delete",
					type: "POST",
					data: {id: $this.attr("val")},
					dataType: "json",
					cache: false,
					success: function(message) {
						$.message(message);
						if (message.type == "success") {
							 $("#refreshButton").attr("onclick",'');   
    						 ($("#refreshButton")[0]).click("return true");
						}
					}
				});
			}
		});
		return false;
	});
	
});
</script>
<body>
	<div class="path">
		手机号管理 <span>(共<span id="pageTotal">${pager.totalCount}</span>条记录)</span>
	</div>
	<form id="listForm" action="${base}/phoneNo/list" method="get">
		<div class="bar">
			<a href="${base}/adminPhoneNo/toImport" id="exportin" class="iconButton">
						<span class="upIcon">&nbsp;</span>导入</a>
			<a href="${base}/phoneNo/add" class="iconButton">
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
				<span class="arrow">手机号：</span>
				<input type="text" id="phoneNo" name="phoneNo" value="${phoneNo!}" maxlength="11" />
				<span class="arrow">是否分配：</span>
				<select id="phoneNoFenPei" name="phoneNoStatu">
				    <option value="">--请选择--</option>
				    <option value="1">是</option>
				    <option value="0">否</option>
				</select>
				<span class="arrow">是否导出：</span>
				<select id="statu" name="statu">
				    <option value="">--请选择--</option>
				    <option value="1">是</option>
				    <option value="0">否</option>
				</select>
				<input type="submit" class="button" value="查询" />
			</div>
		</div>
		<table id="listTable" class="list">
			<tr>
			   <th class="check">
					<input type="checkbox" id="selectAll" />
				</th>
				<th>
					<span>机主姓名<span>
				</th>
				<th>
					<span>手机号<span>
				</th>
				<th>
					<span>是否分配<span>
				</th>
				<th>
					<span>是否导出<span>
				</th>
				<th>
					<span>操作</span>
				</th>
			</tr>
			<#list pager.result as papers>
				<tr>
				   <td>
					    <input type="checkbox" name="ids" value="${papers.id}" />
					</td>
					<td>
						${papers.phoneName!}
					</td>
					<td>
						${papers.phoneNo!}
					</td>
					<td>
					   <#if papers.phoneNoStatu =='0'>
					       否
					   <#else>
					       是
					   </#if>
					</td>
					<td>
					   <#if papers.statu??>
					      <#if papers.statu == '0'>
						       否
						   <#else>
						       是
						   </#if>
						<#else>
						      无
						</#if>
					</td>
					<td>
						<a href="${base}/phoneNo/edit?id=${papers.id!}" title="编辑">[编辑]</a>
					</td>
				</tr>
			</#list>
		</table>
		<#include "/pager.ftl" />
	</form>
</body>
</html>