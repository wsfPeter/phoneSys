
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 

"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<link href="${base}/template/css/common.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="${base}/template/js/jquery.js"></script>
<script type="text/javascript" src="${base}/template/js/jquery.tools.js"></script>
<script type="text/javascript" src="${base}/template/js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="${base}/template/js/jquery.lSelect.organization.js"></script>
<script type="text/javascript" src="${base}/template/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/template/js/common.js"></script>
<script type="text/javascript" src="${base}/template/js/input.js"></script>
<style type="text/css">
.roles label {
	width: 150px;
	display: block;
	float: left;
	padding-right: 6px;
}
 
</style>
<script type="text/javascript">
  
$().ready( function() {
  var $inputForm = $("#inputForm");

	// 表单验证
	$inputForm.validate({
		rules: {
			"admin.username": {
				required: true,
				pattern: /^[0-9a-z_A-Z\u4e00-\u9fa5]+$/,
				minlength: 2,
				maxlength: 20,
				remote: {
					url: "${base}/admin/checkUsername",
					cache: false,
                    type: "post",
                    dataType: "json"
				}
			},
			"admin.password": {
				required: true,
				pattern: /^[^\s&\"<>]+$/,
				minlength: 4,
				maxlength: 20
			},
			"rePassword": {
				required: true,
				equalTo: "#password"
			},
			"admin.email": {
				email: true
			},
			"admin.name": "required",
			"roleIds": "required"
		},
		messages: {
			"admin.username": {
				pattern: "非法字符",
				remote: "用户名已存在"
			},
			"admin.password": {
				pattern: "非法字符"
			}
		}
	});
});
</script>
</head>
<body>
	<div class="path">
		 添加管理员
	</div>
	<form id="inputForm" action="${base}/admin/save" method="post">
		<ul id="tab" class="tab">
			<li>
				<input type="button" value="基本信息" />
			</li>
		</ul>
		<table class="input tabContent">
			<tr>
				<th>
					<span class="requiredField">*</span>用户名:
				</th>
				<td>
					<input type="text" name="admin.username" class="text" maxlength="20"/>
				</td>
			</tr>
		    <tr>
				<th>
					<span class="requiredField">*</span>姓名:
				</th>
				<td>
					<input type="text" name="admin.name" class="text" maxlength="20" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>密 码: 
				</th>
				<td>
					<input type="password" id="password" name="admin.password" class="text" maxlength="20"/>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>确认密码: 
				</th>
				<td>
					<input type="password" name="rePassword" class="text" maxlength="20" />
				</td>
			</tr>
			<tr class="roles">
				<th>
					<span class="requiredField">*</span>管理角色: 
				</th>
				<td>
					<span class="fieldSet">
						<#assign roleSet = (admin.roleSet)! />
						<#list allRoleList as role>
							<label>
								<input type="radio" name="roleIds" value="${role.id}" />${role.name}
							</label>
						</#list>
					</span>	
				</td>
			</tr>
			<tr>
				<th>
					Email:
				</th>
				<td>
					<input type="text" name="admin.email" class="text" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					设置: 
				</th>
				<td>
					<label>
						<input type="checkbox" name="admin.isAccountEnabled" value="true" checked="checked" />启用
					</label>
				</td>
			</tr>
		</table>
		<table class="input">
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					<input type="submit" class="button" value="确&nbsp;&nbsp;定" />
					<input type="button" class="button" value="返&nbsp;&nbsp;回" onclick="location.href='${base}/admin/list'" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>