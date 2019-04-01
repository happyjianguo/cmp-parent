<#include "/.ftl"/>
<@body>
	<#--审批历史信息弹出框-->
	<@panel>
	    <@panelBody style="padding-left:10px;">
	        <@pureTable>
	 			<tr>
					<th>审批状态</th>
					<th>操作员ID</th>
					<th>创建日期</th>
					<@buttonAuth code="CAS_APPLY_REMARK">
						<th>备注</th>
						<th>拒绝原因码</th>
					</@buttonAuth>
	 			</tr>
	 			<#if tmAppHistoryList??>
		 			<#list tmAppHistoryList as tmAppHistory>
		 				<tr>
		 					<td width="175">${(tmAppHistory.rtfState)!}</td>
		 					<td width="100">${(tmAppHistory.operatorId)!}</td>
		 					<td width="145">${(tmAppHistory.createDate?datetime)!}</td>
		 					<@buttonAuth code="CAS_APPLY_REMARK">
			 					<td width="200">${(tmAppHistory.remark)!}</td>
			 					<td width="200">${(tmAppHistory.refuseCode)!}</td>
		 					</@buttonAuth>
		 				</tr>
		 			</#list>
		 		</#if>
		   	 </@pureTable>
	    </@panelBody>
	</@panel> 
</@body>