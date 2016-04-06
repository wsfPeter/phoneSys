<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>分配手机号</title>
<link href="${base}/template/css/common.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/css/main.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css"/>

<script type="text/javascript" src="${base}/template/js/jquery.js"></script>
<script type="text/javascript" src="${base}/template/js/jquery.tools.js"></script>
<script type="text/javascript" src="${base}/template/js/jquery.ztree.all.js"></script>
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
    $(document.frm.adminId).val('${aid!}');
    getNode();
});

function getNode(){
   var aid = document.frm.adminId.options[document.frm.adminId.selectedIndex].value;
   $.post("${base}/adminPhoneNo/getUserPhoneNo",
              "id="+aid,
              function(data){
                $("#noFenPeiPhoneNo").text(data[0]);
                $("#allPhoneNo").text(data[1]);
                $("#doneDownload").text(data[2]);
                $("#downloadPhoneNo").text(parseInt(data[1])-parseInt(data[2]));
   },"json");
}

function sendPhoneNo(node){
   var username = document.frm.adminId.options[document.frm.adminId.selectedIndex].text;
   var aid = document.frm.adminId.options[document.frm.adminId.selectedIndex].value;
    $.dialog({
                    type: "warn",
                    content: "<b>你确定要为"+username+"业务员<br/>分配"+node.value+"个手机号吗？</b>",
                    ok: message("admin.dialog.ok"),
                    cancel: message("admin.dialog.cancel"),
                    onOk: function() {
                       $.post("${base}/adminPhoneNo/phoneNoCountFenPei",
					              "id="+aid+
					               "&limit="+node.value,
					              function(data){
					               if(data){
					                   $.message("warn","分配成功"); 
					               }else{
					                   $.message("warn","分配失败"); 
					               }
					                node.checked = false;
					                location.reload();
					   });
                       
                    },
                    onCancel:function(){
                      node.checked = false;
                    }
                });
}

</script>
</head>
<body>
	<div class="path">
		 分配手机号 
	</div>
	<form id="inputForm" name="frm" action="${base}/adminPhoneNo/saveFenPei" method="post">
		<div id="phoneNos"></div>
		<table class="input tabContent">
		    <tr>
				<th>
					<span class="requiredField">*</span>业务员:
				</th>
				<td>
				     <select  name="adminId" onChange="getNode()">
				      <#list admins as admin>
				         <option value="${admin[0]!}">${admin[1]!}</option>
				      </#list>
				     </select>
				</td>
			</tr>
			<tr>
			   <th>
			        当前业务员:
			   </th>
			   <td>
			       <span>资源总数：<label id="allPhoneNo"></label></span><span style="margin-left:20px;">已下载：<label id="doneDownload"></label></span><span style="margin-left:20px;">还剩：<label id="downloadPhoneNo"></label></span>
			   </td>
			</tr>
			<tr>
				<th>
					未分配手机号总数:
				</th>
				<td>
					<span id="noFenPeiPhoneNo"></span>
				</td>
			</tr>
			<tr>
				<th>
					分配个数: 
				</th>
				<td>
						<label>
							<input type="radio" name="phoneNoCount" onclick="sendPhoneNo(this)" value="50" />50
						</label>
						<label>
							<input type="radio" name="phoneNoCount" onclick="sendPhoneNo(this)" value="100" />100
						</label>
						<label>
							<input type="radio" name="phoneNoCount" onclick="sendPhoneNo(this)" value="150" />150
						</label>
						<label>
							<input type="radio" name="phoneNoCount" onclick="sendPhoneNo(this)" value="200" />200
						</label>
				</td>
			</tr>
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					<input type="button" class="button" value="返&nbsp;&nbsp;回" onclick="history.back();return false;" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>