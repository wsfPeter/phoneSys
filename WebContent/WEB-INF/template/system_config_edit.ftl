<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>系统设置</title>
<link href="${base}/template/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/template/js/jquery.js"></script>
<script type="text/javascript" src="${base}/template/js/jquery.tools.js"></script>
<script type="text/javascript" src="${base}/template/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/template/js/common.js"></script>
<script type="text/javascript" src="${base}/template/js/input.js"></script>
<script type="text/javascript">
$().ready( function() {

	var $inputForm = $("#inputForm");
	var $intervalTr = $("#intervalTr");
	var $isAutoSave = $("#isAutoSave");
	
	if($isAutoSave.attr("checked")){
			$intervalTr.show();
	}else{
			$intervalTr.hide();
	}
	
	$isAutoSave.click(function(){
		if($(this).attr("checked")){
			$intervalTr.show();
		}else{
			$intervalTr.hide();
			$("#interval").val(${systemConfig.interval});
		}

	});
	
	// 表单验证
	$inputForm.validate({
		rules: {
			"systemConfig.interval": {	
				required:true ,
				digits:true
			}
		}
	});
});
</script>
</head>
<body>
	<div class="path">
		系统设置
	</div>
	<form id="inputForm" action="${base}/systemConfig/update" method="post">
		<ul id="tab" class="tab">
			<li>
				<input type="button" value="基本信息" />
			</li>
		</ul>
		<table class="input tabContent">
			<tr>
				<th>
					自动保存信息:
				</th>
				<td>
					<input type="checkbox" id="isAutoSave" name="systemConfig.isAutoSave"  value="true" <#if systemConfig.isAutoSave> checked="checked"</#if> />启用
				</td>
			</tr>
			<tr id="intervalTr">
				<th>
					自动保存间隔时间（秒）: 
				</th>
				<td>
					<input type="text" id="interval" name="systemConfig.interval" class="text" value="${systemConfig.interval}" maxlength="20"/>
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
					<input type="button" class="button" value="返&nbsp;&nbsp;回" onclick="location.href='#'" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>