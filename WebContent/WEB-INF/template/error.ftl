<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>错误提示</title>
<link href="${base}/template/css/common.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/css/error.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="wrap">
		<div class="error">
			<dl>
				<dt>对不起，您的操作出现错误！</dt>
				<#if content??>
					<dd>${content}</dd>
					
				</#if>
				<#if  url??>
					<dd>
						<a href="${base}${url}">返回上一页</a>
					</dd>
				<#else>
				
					<dd>
						<a href="javascript:;" onclick="window.history.back(); return false;">返回上一页</a>
					</dd>
				</#if>
				
			</dl>
		</div>
	</div>
</body>
</html>