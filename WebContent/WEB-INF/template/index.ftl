<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<link href="${base}/template/css/common.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.input .powered {
	font-size: 11px;
	text-align: right;
	color: #cccccc;
}
</style>
</head>
<body>
	<div class="path">
		管理中心首页 
	</div>
	<table class="input">
		<tr>
			<th>
				JAVA版本:
			</th>
			<td>
				${javaVersion}
			</td>
			<th>
				JAVA路径:
			</th>
			<td>
				${javaHome}
			</td>
		</tr>
		<tr>
			<th>
				操作系统名称: 
			</th>
			<td>
				${osName}
			</td>
			<th>
				操作系统构架: 
			</th>
			<td>
				${osArch}
			</td>
		</tr>
		<tr>
			<th>
				Servlet信息:
			</th>
			<td>
				${serverInfo}
			</td>
			<th>
				Servlet版本: 
			</th>
			<td>
				${servletVersion}
			</td>
		</tr>
		<tr>
			<td colspan="4">
				&nbsp;
			</td>
		</tr>
	</table>
</body>
</html>