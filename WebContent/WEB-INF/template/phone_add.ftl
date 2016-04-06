<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <title>添加手机号</title>
    <link href="${base}/template/css/common.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${base}/template/js/jquery.js"></script>
    <script type="text/javascript" src="${base}/template/js/jquery.tools.js"></script>
    <script type="text/javascript" src="${base}/template/js/jquery.validate.js"></script>
    <script type="text/javascript" src="${base}/template/js/common.js"></script>
    <script type="text/javascript" src="${base}/template/js/input.js"></script>
    <script type="text/javascript">
        $().ready(function () {
			 var $inputForm = $("#inputForm");
			  // 表单验证
			$inputForm.validate({
				rules: {
					"phoneNo.phoneNo": {
					   digits:true,
						required: true,
						remote: {
							url: "${base}/phoneNo/checkPhoneNo",
							cache: false,
							type: "post",
							dataType: "json",
							data: {
								oldValue: "${(phoneNo.phoneNo)!}",
								phoneNo: function(){return $("#phoneNo").val();}
							}
						}
					}
				},	
				messages: {
					"phoneNo.phoneNo": {
						remote: "手机号已存在"
					}
				}
			});
			
        });
    </script>
</head>
<body>
	<div class="path">
	     添加手机号
	</div>
	<form id="inputForm" action="${base}/phoneNo/save" method="post">
		<table class="input">
		    <tr>
				<th>
					<span class="requiredField">*</span>手机号:
				</th>
				<td>
					<input type="text"  id="phoneNo"  name="phoneNo.phoneNo" class="text" minlength="11" maxlength="11" />
				</td>
			</tr>
			 <tr>
				<th>
					姓名:
				</th>
				<td>
					<input type="text"  id="phoneName"  name="phoneNo.phoneName" class="text" maxlength="20" />
				</td>
			</tr>
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					<input type="submit" class="button" value="确&nbsp;&nbsp;定" />
					<input type="button" class="button" value="返&nbsp;&nbsp;回" onclick="location.href='${base}/phoneNo/list'"/>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>