<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <title>导入手机号</title>
    <link href="${base}/template/css/common.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${base}/template/js/jquery.js"></script>
    <script type="text/javascript" src="${base}/template/js/jquery.tools.js"></script>
    <script type="text/javascript" src="${base}/template/js/jquery.validate.js"></script>
    <script type="text/javascript" src="${base}/template/js/common.js"></script>
    <script type="text/javascript" src="${base}/template/js/jquery.lSelect.organization.js"></script>
    <script type="text/javascript" src="${base}/template/js/input.js"></script>
    <script type="text/javascript">
         $().ready(function () {
            var $inputForm = $("#inputForm");
            
            $.validator.addMethod("checkFile", 
				function(value, element) {
					var reg=/^.*?\.(xls|xlsx)$/;
					if(reg.test(value)){return true;}
					return false;
				},
				"文件格式不正确"
			);
            
		      // 表单验证
			$inputForm.validate({
				rules: {
					mubileFile: {
						required: true,
						checkFile: true
					}
				}
			});
        });
    </script>
</head>
<body>
	<div class="path">
	     导入手机号
	</div>
	<form id="inputForm" action="${base}/adminPhoneNo/saveImport" method="post" enctype="multipart/form-data">
		<table class="input">
				<th>
					<span class="requiredField">*</span>导入文件:
				</th>
				<td>
					<input type="file" name="mubileFile" class="in"/>
					<input type="button" onClick="location.href='${base}/adminPhoneNo/download';" class="button" value="模板下载">
				</td>
			</tr>
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					<input  type="submit"  class="button" value="确&nbsp;&nbsp;定" />
					<input  type="button"  class="button" onClick="history.back(); return false;" value="返&nbsp;&nbsp;回" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>