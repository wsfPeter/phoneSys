<input type="hidden" id="pageSize" name="pager.pageSize" value="${pager.pageSize}" />
<input type="hidden" id="searchProperty" name="pager.searchProperty" value="${pager.searchProperty}" />
<input type="hidden" id="orderProperty" name="pager.orderProperty" value="${pager.orderProperty}" />
<input type="hidden" id="orderDirection" name="pager.orderType" value="${pager.orderType}" />
<#if (pager.totalPages > 1)>
	<div class="pagination">
		<#if (pager.pageNumber==1)>
			<span  class="firstPage">&nbsp;</span>
		<#else>
			<a   title="首页" class="firstPage" id="onePage"  href="javascript: $.pageSkip(1);">&nbsp;</a>
		</#if>
		<#if (pager.pageNumber > 1)>
			<a   title="上一页" class="previousPage" href="javascript: $.pageSkip(${pager.pageNumber-1});">&nbsp;</a>
		<#else>
			<span class="previousPage">&nbsp;</span>
		</#if>
		<#if (pager.pageNumber < pager.totalPages)>
			<a    title="下一页" class="nextPage" href="javascript: $.pageSkip(${pager.pageNumber+1});">&nbsp;</a>
		<#else>
			<span class="nextPage">&nbsp;</span>
		</#if>
		<#if (pager.pageNumber == pager.totalPages)>
			<span class="lastPage">&nbsp;</span>
		<#else>
			<a     title="末尾" class="lastPage" href="javascript: $.pageSkip(${pager.totalPages});">&nbsp;</a>
		</#if>
		<span class="pageSkip">
			共${pager.totalPages}页 到第<input id="pageNumber" name="pageNumber" value="${pager.pageNumber}" maxlength="9" onpaste="return false;" /><button type="submit">&nbsp;</button>
		</span>
	</div>
</#if>
