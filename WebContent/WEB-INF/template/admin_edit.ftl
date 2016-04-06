<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>编辑管理员</title>
<link href="${base}/template/css/common.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/css/main.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="${base}/template/js/jquery.js"></script>
<script type="text/javascript" src="${base}/template/js/jquery.tools.js"></script>
<script type="text/javascript" src="${base}/template/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/template/js/common.js"></script>
<script type="text/javascript" src="${base}/template/js/input.js"></script>
<style type="text/css">
.roles label {
	width: 100px;
	display: block;
	float: left;
	padding-right: 6px;
}
</style>
<script type="text/javascript">
$().ready( function() {
	 var $inputForm = $("#inputForm");
	 
    $inputForm.validate({
        rules: {
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
            },
            "admin.name":"required",
            "roleIds": "required"
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
		  编辑管理员 
	</div>
	<form id="inputForm" action="${base}/admin/update" method="post">
		<input type="hidden" name="admin.id" value="${id}" />
		<div id="phoneNos"></div>
		<ul id="tab" class="tab">
			<li>
				<input type="button" value="基本信息" />
			</li>
		</ul>
		<table class="input tabContent">
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
					<span class="requiredField">*</span>姓名:
				</th>
				<td>
					<input type="text" name="admin.name" value="${admin.name!}" class="text" maxlength="20" />
				</td>
			</tr>
			<tr>
				<th>
					密 码: 
				</th>
				<td>
					<input type="password" id="password" name="admin.password" class="text" maxlength="20"/>
				</td>
			</tr>
			<tr>
				<th>
					确认密码: 
				</th>
				<td>
					<input type="password" id="rePassword" name="rePassword" class="text" maxlength="20"/>
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
								<input type="radio" name="roleIds" value="${role.id}"<#if roleSet?seq_contains(role)> checked="checked"</#if> />${role.name}
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
					<input type="text" name="admin.email" value="${admin.email!}" class="text" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					设置: 
				</th>
				<td>
					<label>
						<input type="checkbox" name="admin.isAccountEnabled"  value="true" <#if admin.isAccountEnabled> checked="checked"</#if> />启用
					</label>
				</td>
			</tr>
			<tr>
				<th>&nbsp;</th>
				<td>
					<span class="warnInfo"><span class="icon">&nbsp;</span><span class="tips">如果要修改密码,请填写密码,若留空,密码将保持不变!</span></span>
				</td>
			</tr>
		</table>
		<table class="input">
			<tr>
            <#if (admin.isSystem)!false>
                <tr>
                    <th>
                        &nbsp;
                    </th>
                    <td>
                        <span class="warnInfo"><span class="icon">&nbsp;</span><span style="color:red;" class="tips">系统提示:</b> 系统内置管理员不允许修改!</span></span>
                    </td>
                </tr>
            </#if>
				<th>
					&nbsp;
				</th>
				<td>
					<input type="submit"  class="button" value="确&nbsp;&nbsp;定" <#if (admin.isSystem)!false> disabled="true"</#if> />
					<input type="button" class="button" value="返&nbsp;&nbsp;回" onclick="location.href='${base}/admin/list'" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>