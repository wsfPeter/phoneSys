<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8"/>
<title>编辑角色</title>
<link href="${base}/template/css/common.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${base}/template/js/jquery.js"></script>
<script type="text/javascript" src="${base}/template/js/jquery.tools.js"></script>
<script type="text/javascript" src="${base}/template/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/template/js/common.js"></script>
<script type="text/javascript" src="${base}/template/js/input.js"></script>
<style type="text/css">
.authorities label {
	min-width: 120px;
	_width: 120px;
	display: block;
	float: left;
	padding-right: 4px;
	_white-space: nowrap;
}
</style>
<script type="text/javascript">
    $().ready(function () {
        var $inputForm = $("#inputForm");
        var $selectAll = $("#inputForm .selectAll");
        
        $selectAll.click(function() {
			var $this = $(this);
			var $thisCheckbox = $this.closest("tr").find(":checkbox");
			if ($thisCheckbox.filter(":checked").size() > 0) {
				$thisCheckbox.prop("checked", false);
			} else {
				$thisCheckbox.prop("checked", true);
			}
			return false;
		});
		
        $inputForm.validate({
            rules: {
                "role.name": {
                    required: true,
                    remote: {
                        url: "${base}/role/checkName",
                        cache: false,
                        type: "post",
                        dataType: "json",
                        data: {
                            oldValue: "${(role.name)!}"
                        }
                    }
                },
                "authority":"required"
            },
            messages:{
                "role.name":{
                    remote:"角色已存在"
                }
            }
        })
    });
</script>
</head>
<body>
	<div class="path">
	    <a href="${base}/common/index">首页</a> &raquo; 编辑角色
	</div>
	<form id="inputForm" action="${base}/role/update" method="post">
	    <input type="hidden" name="role.id" value="${id}"/>
	    <table class="input">
	        <tr>
	            <th>
	              	 <span class="requiredField">*</span> 角色名称:
	            </th>
	            <td>
	                <input type="text" id="name" name="role.name" value="${role.name}" class="text" maxlength="20"/>
	
	            </td>
	        </tr>
	        <tr>
	            <th>
	                                                 描 述:
	            </th>
	            <td>
	                <input type="text" name="role.description" class="text" value="${role.description}" maxlength="200" />
	            </td>
	        </tr>
			<tr>
				<td colspan="2">
					&nbsp;
				</td>
			</tr>
			<input type="hidden" name="authority" value="ROLE_BASE" />
	        <tr class="authorities">
	            <th>
	                <a href="javascript:;" class="selectAll" title="选择此组权限">系统设置: </a>
	            </th>
	            <td>
	                <label>
	                    <input type="checkbox" name="authority" value="ROLE_ADMIN"  <#if  role.authorityList?contains("ROLE_ADMIN")!>checked</#if> />管理员管理
	                </label>
	                <label>
	                    <input type="checkbox" name="authority" value="ROLE_ROLE" <#if (!id?? || role.authorityList?contains("ROLE_ROLE"))!> checked</#if> />角色管理
	                </label>
	                <label>
	                    <input type="checkbox" name="authority"  value="ROLE_ORGANIZATION" <#if (!id?? || role.authorityList?contains("ROLE_ORGANIZATION"))!> checked</#if> />组织管理
	                </label>
	                <label>
	                    <input type="checkbox" name="authority" value="ROLE_LOG" <#if (!id?? || role.authorityList?contains("ROLE_LOG"))!> checked</#if> />日志管理
	                </label>
	               	<label>
	                    <input type="checkbox" name="authority" value="ROLE_LICENSE" <#if (!id?? || role.authorityList?contains("ROLE_LICENSE"))!> checked</#if> />License管理
	                </label>
	            </td>
	        </tr>
	        <tr class="authorities">
	            <th>
	                <a href="javascript:;" class="selectAll" title="选择此组权限">题库管理: </a>
	            </th>
	            <td>
	            	<label>
	                    <input type="checkbox" name="authority" value="ROLE_QUESTION_BANG" <#if (!id?? || role.authorityList?contains("ROLE_QUESTION_BANG"))!> checked</#if> />题库管理
	                </label>
	                <label>
	                    <input type="checkbox" name="authority"  value="ROLE_QUESTION_TEST" <#if (!id?? || role.authorityList?contains("ROLE_QUESTION_TEST"))!> checked</#if> />试题管理
	                </label>
	            </td>
	        </tr>
	        <tr class="authorities">
	            <th>
	                <a href="javascript:;" class="selectAll" title="选择此组权限">试卷管理: </a>
	            </th>
	            <td>
	                <label>
	                    <input type="checkbox" name="authority" value="ROLE_PAPERS" <#if (!id?? || role.authorityList?contains("ROLE_PAPERS"))!> checked</#if> />试卷管理
	                </label>
	            </td>
	        </tr>
		    <#if (role.isSystem)!false>
		        <tr>
		            <th>
		                &nbsp;
		            </th>
		            <td>
		                <span class="tips">系统提示:</b> 系统内置角色不允许修改!</span>
		            </td>
		        </tr>
		    </#if>
	    </table>
	    <table class="input">
	        <tr>
	            <th>
	                &nbsp;
	            </th>
	            <td>
	                <input type="submit" class="button" value="确&nbsp;&nbsp;定" <#if (role.isSystem)!false> disabled="true" </#if> />
	                <input type="button" class="button" value="返&nbsp;&nbsp;回" onclick="location.href='${base}/role/list'"/>
	            </td>
	        </tr>
	    </table>
	</form>
</body>
</html>