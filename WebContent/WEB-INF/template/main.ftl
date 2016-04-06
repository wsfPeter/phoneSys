<#assign sec=JspTaglibs["/WEB-INF/tld/security.tld"] />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>管理中心</title>
<link href="${base}/template/css/common.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/template/js/jquery.js"></script>
<style type="text/css">
*{
	font: 12px tahoma, Arial, Verdana, sans-serif;
}
html, body {
	width: 100%;
	height: 100%;
	overflow: hidden;
}
</style>
<script type="text/javascript">
$().ready(function() {

	var $nav = $("#nav a");
	var $menu = $("#menu dl");
	var $menuItem = $("#menu a");
	
	$nav.click(function() {
		var $this = $(this);
		$nav.removeClass("current");
		$this.addClass("current");
		var $currentMenu = $($this.attr("href"));
		$menu.hide();
		$currentMenu.show();
		return false;
	});
	
	$menuItem.click(function() {
		var $this = $(this);
		$menuItem.removeClass("current");
		$this.addClass("current");
	});

});
</script>
</head>
<body>
	<script type="text/javascript">
		if (self != top) {
			top.location = self.location;
		};
	</script>
	<table class="main">
		<tr>
			<th class="logo">
				<img src="${base}/template/images/exam.png" />
			</th>
			<th>
				<div id="nav" class="nav">
					<ul>
						<@sec.authorize ifAnyGranted="ROLE_ADMIN">
							<li>
								<a href="#admin">管理员</a>
							</li>
						</@sec.authorize>
						<@sec.authorize ifAnyGranted="ROLE_MAINTION">
							<li>
								<a href="#maintion">维护员</a>
							</li>
						</@sec.authorize>
						<@sec.authorize ifAnyGranted="ROLE_OPERATION">
							<li>
								<a href="#operation">业务员</a>
							</li>
						</@sec.authorize>
					</ul>
				</div>
				<div class="link">
				</div>
				<div class="link">
					<strong><@sec.authentication property="name" /></strong>
					您好!
					<a href="${base}/profile/edit" target="iframe">[账号设置]</a>
					<a href="${base}/admin/logout" target="_top">[注销]</a>
				</div>
			</th>
		</tr>
		<tr>
			<td id="menu" class="menu">
				<dl id="admin">
					<dt>管理员</dt>
					<@sec.authorize ifAnyGranted="ROLE_ADMIN">
						<dd>
							<a href="${base}/admin/list" target="iframe">用户管理</a>
						</dd>
						<dd>
							<a href="${base}/phoneNo/list" target="iframe">手机号管理</a>
						</dd>
						<dd>
							<a href="${base}/adminPhoneNo/fenPeiPhoneNo" target="iframe">分配手机号</a>
						</dd>
					</@sec.authorize>
				</dl>
				<dl id="maintion">
					<dt>维护管理</dt>
					<@sec.authorize ifAnyGranted="ROLE_MAINTION">
						<dd>
							<a href="${base}/phoneNo/list" target="iframe">手机号管理</a>
						</dd>
						<dd>
							<a href="${base}/adminPhoneNo/fenPeiPhoneNo" target="iframe">分配手机号</a>
						</dd>
					</@sec.authorize>
				</dl>
				<dl id="operation">
					<dt>业务管理</dt>
					<@sec.authorize ifAnyGranted="ROLE_OPERATION">
						<dd>
							<a href="${base}/adminPhoneNo/detail" target="iframe">手机号列表</a>
						</dd>
					</@sec.authorize>
				</dl>
			</td>
			<td>
				<iframe id="iframe" name="iframe" src="${base}/${page}" frameborder="0"></iframe>
			</td>
		</tr>
	</table>
<body>
</html>