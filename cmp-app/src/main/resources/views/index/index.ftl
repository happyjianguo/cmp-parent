<#include "/layout.ftl">
<@body>
    <@gotop/>
    <@panelBody>
        <@form id="imageQueryForm" cols="4">
            <@row>
                <@field label="客户姓名" label_ar="8">
                    <@input name="query.name" />
                </@field>
                <@field label="客户证件号" label_ar="8">
                    <@input name="query.idNo" />
                </@field>
                <@field label="内容批次号" label_ar="8">
                    <@input name="query.batchNo" />
                </@field>
                <@field label="维护人" label_ar="8">
                    <@input name="query.updateUser" />
                </@field>
                <@toolbar align="right" style="margin-right:80px">
                    <@button id="todoQueryButton" name="查询" style="margin-right:15px" fa="search"/>
                </@toolbar>
            </@row>
        </@form>
        <@table id="home" url="cmp_/queryImageList" form_id="imageQueryForm" button_id="todoQueryButton" page_size="10" load_auto="true">
            <@th checkbox="false"/>
            <@th title="客户姓名" field="name" />
            <@th title="客户证件号" field="idNo" />
            <@th title="内容批次号" field="batchNo" sortable="true"/>
            <@th title="维护人" field="updateUser" sortable="true"/>
            <@th title="维护时间" field="updateDate" render="true" >
                <@thDate value="{{row.updateDate}}" datetype="datetime"/>
            </@th>
            <@th title="<div style='width:85px'>操作</div>" render="true">
                <@href name="查看" href="/cmp_/showContent?batchNo={{row.batchNo}}"/>
            </@th>
        </@table>
    </@panelBody>
<script type="text/javascript">

</script>
</@body>