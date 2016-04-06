<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>错误提示</title>
<link href="${base}/template/css/common.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/css/error.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">     
function countDown(secs,surl){     
 //alert(surl);     
 var jumpTo = document.getElementById('jumpTo');
 jumpTo.innerHTML=secs;  
 if(--secs>0){     
     setTimeout("countDown("+secs+",'"+surl+"')",1000);     
     }     
 else{       
     location.href=surl;     
     }     
 }     
</script> 
</head>
<body>
	<div class="wrap">
		<div class="error">
			<dl>
				<dt>对不起，您的账号已在其他地方登陆！</dt>
				<dd><span id="jumpTo">5</span>秒后自动跳转到登陆页面</dd>
				<script type="text/javascript">countDown(5,'${base}/admin/login');</script>  
			</dl>
		</div>
	</div>
</body>
</html>