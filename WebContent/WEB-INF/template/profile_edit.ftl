<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>账号设置</title>
<link href="${base}/template/css/common.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${base}/template/js/jquery.js"></script>
<script type="text/javascript" src="${base}/template/js/jquery.tools.js"></script>
<script type="text/javascript" src="${base}/template/js/jquery.lSelect.organization.js"></script>
<script type="text/javascript" src="${base}/template/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/template/js/common.js"></script>
<script type="text/javascript" src="${base}/template/js/input.js"></script>
<script type="text/javascript">
$().ready(function() {

	var $inputForm = $("#inputForm");
	
	$.validator.addMethod("requiredTo", 
		function(value, element, param) {
			var parameterValue = $(param).val();
			if ($.trim(parameterValue) == "" || ($.trim(parameterValue) != "" && $.trim(value) != "")) {
				return true;
			} else {
				return false;
			}
		},
		"必填"
	);
	
	// 表单验证
	$inputForm.validate({
		rules: {
			"currentPassword": {
				requiredTo: "#password",
				remote: {
					url: "${base}/profile/checkCurrentPassword",
					cache: false
				}
			},
			"admin.password": {
				pattern: /^[^\s&\"<>]+$/,
				minlength: 4,
				maxlength: 20
			},
			"rePassword": {
				equalTo: "#password"
			},
			"admin.email": {
				email: true
			}
		},
		messages: {
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
		 账户设置
	</div>
	<form id="inputForm" action="${base}/profile/update" method="post">
		<table class="input">
			<tr>
				<th>
					用户名: 
				</th>
				<td>
					${admin.username}
				</td>
			</tr>
			<tr>
				<th>
					姓名: 
				</th>
				<td>
					<input type="text" name="admin.name" value="${admin.name!}" class="text" maxlength="50" />
				</td>
			</tr>
			<tr>
				<th>
					当前密码:  
				</th>
				<td>
					<input type="password" name="currentPassword" class="text" maxlength="20" />
				</td>
			</tr>
			<tr>
				<th>
					新密码: 
				</th>
				<td>
					<input type="password" id="password" name="admin.password" class="text" maxlength="20" />
				</td>
			</tr>
			<tr>
				<th>
					确认新密码: 
				</th>
				<td>
					<input type="password" id="rePassword" name="rePassword" class="text" maxlength="20"/>
				</td>
			</tr>
			<tr>
				<th>
					Email:
				</th>
				<td>
					<input type="text" name="admin.email" value="${admin.email!}" class="text" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					<span class="tips">如需修改密码请先填写当前密码，若留空则密码保持不变!</span>
				</td>
			</tr>
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					<input type="submit" class="button" value="确&nbsp;&nbsp;定" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>