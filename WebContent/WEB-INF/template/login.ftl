<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>管理登录</title>
<link href="${base}/template/css/common.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/css/login.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/template/js/jquery.js"></script>
<script type="text/javascript" src="${base}/template/js/common.js"></script>
<script type="text/javascript">
	$().ready( function() {
		var $loginForm = $("#loginForm");
		var $username = $("#username");
		var $password = $("#password");
		
		$username.focus();
		
		// 登录页面若在框架内，则跳出框架
		if (self != top) {
			top.location = self.location;
		};
	    <#if error??>
            $.message("warn", '${error}');
        </#if>
		// 提交表单验证,记住登录用户名
		$loginForm.submit( function() {
			if ($username.val() == "") {
				$.message("warn", "请输入您的用户名");
				return false;
			}
			if ($password.val() == "") {
				$.message("warn", "请输入您的密码");
				return false;
			}
		});
	});
</script>
</head>
<body>
	<div class="login">
		<form id="loginForm" action="${base}/j_spring_security_check" method="post">
			<table>
				<tr>
					<td width="190" rowspan="2" align="center" valign="bottom">
						<img src="${base}/template/images/logo.png"/>
					</td>
					<th>
						用户名:
					</th>
					<td>
						<input type="text" id="username" name="j_username" class="text" maxlength="20" />
					</td>
				</tr>
				<tr>
					<th>
						密&nbsp;&nbsp;&nbsp;码:
					</th>
					<td>
						<input type="password" id="password" name="j_password" class="text" maxlength="20" autocomplete="off"/>
					</td>
				</tr>
				<tr>
					<td>
						&nbsp;
					</td>
					<td>
						&nbsp;
					</td>
					<td>
						<input type="button" class="homeButton" value="" /><input type="submit" class="loginButton" value="登录" />
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>