<#include "/layout.ftl" />
<@body>
    <@panel head="影像上传界面【${(tmCmpMain.batchNo)!}】">
        <@panelBody>
            <@tab id="tab">
                <@tabContent>
                <div style="padding-bottom:0px;"></div>
                    <@form id="uploadForm" enctype="multipart/form-data" action="/cmp_/uploadImage?sgin=upload" success_url="cmp_/showContent?batchNo=${(tmCmpMain.batchNo)!}">
                        <@hidden name="query.batchNo" value="${(tmCmpMain.batchNo)!}" />
                        <@hidden name="query.userNo" value="${(userNo)!}"/>
                        <@hidden name="query.systemId" value="${(systemId)!}"/>
                        <@hidden name="query.branchCode" value="${(branchCode)!}"/>
                        <@pureTable id="telSurveyInfo">
                        <thead>
                        <tr>
                            <th width="30%">文件大类型</th>
                            <th width="30%">文件小类型</th>
                            <th width="30%">文件选择</th>
                            <th width="10%">操作</th>
                        </tr>
                        <tbody id="choiceTable">
                        <tr id="tr0">
                            <td> <@select id="bigType0" name="query.supType0"  change="setSecond(this)"  showcode="true"
                            options=dict_("tableMap","com.jjb.acl.infrastructure.TmAclDict",{"type":"fileBigType"},"code","codeName") /></td>
                            <td> <@select id="smallType0" name="query.subType0"  showcode="true" /></td>
                            <td><@input type="file" id="file0" name="fileName0" /></td>
                            <td><@button name="新建" fa="plus" click="addUploadInfo()"/></td>
                        </tr>
                        </tbody>
                        </@pureTable>
                        <@submitButton name="上传提交"/>
                            <@href name="返回查看" href="/cmp_/showContent?batchNo=${(tmCmpMain.batchNo)!}"/>
                    </@form>
                <div style="padding-bottom:50px;"></div>
                </@tabContent>
            </@tab>
        </@panelBody>
    </@panel>
</@body>
<script type="text/javascript">
    var num = 1;
    var NUM = 0;
    <#--上传新建按钮-->
    var addUploadInfo = function () {
        var flag = validChoice(0, num);//非空判断
        if (flag == false) {
            return;
        }
        var trHtml = $("#tr0").html();
        $("#choiceTable").append('<tr id="tr' + num + '">' + trHtml + '</tr>');
        $("#tr" + num).children("td:eq(0)").children("select").val('')
                .attr({
                    "id": "bigType" + num,
                    "name": "query.supType" + num
                });
        $("#tr" + num).children("td:eq(1)").children("select").val('')
                .attr({"id": "smallType" + num, "name": "query.subType" + num, "value": ""});
        $("#tr" + num).children("td:eq(2)").children("input").val('')
                .attr({"id": "file" + num, "name": "fileName" + num});
        $("#tr" + num).children("td:eq(3)")
                .html('<button type="button" id="" name="删除" value="" class="btn btn-sm btn-danger" style=""><i class="fa fa-close"></i>删除</button>')
                .children("button").attr("onclick", "delectChoiceInfo('tr" + num + "')");
        num++;
        NUM++;
    }
    <#--上传新建非空校验-->
    function validChoice(Size, num) {
        for (var i = Size; i < num; i++) {
            var bigType = $('#bigType' + i).val();
            var smallType = $('#smallType' + i).val();
            var file = $('#file' + i);
                if (bigType == '') {
                    alert("[文件大类型]不能为空!");
                    return false;
                }
                if (smallType == '') {
                    alert("[文件小类型]不能为空!");
                    return false;
                }
                if (file == undefined) {
                    alert("上传的文件不能为空!")
                    return
                }
        }
        return true;
    }
    <#--删除按钮-->
    var delectChoiceInfo = function (trId) {
        $('#' + trId).remove();
        NUM--;
        num--;
    }

    var upload = function () {
        if (!confirm("确认上传?")) {
            return false;
        }
        $.ajax({
            url: "${base}/cmp_/uploadImage?sgin=upload",
            type: "post",
            dataType: "json",
            data: $("#uploadForm").serialize(),
            success: function (ref) {
                if (ref.s) {<#--如果成功-->
                    alert(ref.msg);
                    } else {
                    alert(ref.msg);<#--如果失败，则显示失败原因-->
                    location.reload(true)
                }
            }
        });
    };

    function setSecond(obj) {
        var val = obj.value;
        var url = "/cmp-app/assets/cmp_/getTableByBigType?type=fileSmallType";
        var data = {_PARENT_KEY: "value3", _PARENT_VALUE: val};
        $('#smallType' + NUM).empty();
        $('#smallType' + NUM).val("");
        $.post(ar_.randomUrl(url), data,
                function (res) {
                    if (res.s) {
                        var options = res.obj;
                        if (options != null) {
                            var filter = [];
                            for (var i = 0; i < options.length; i++) {
                                var item = options[i];
                                var inFilter = false;
                                for (var j = 0; j < filter.length; j++) {
                                    if (item['codeName'] == filter[j]) {
                                        inFilter = true;
                                        break;
                                    }
                                }
                                if (inFilter == false) {
                                    $('#smallType' + NUM).append("<option value='" + item['code'] + "'>" + item['codeName'] + "</option>");
                                }
                            }
                        }
                    }
                    $('#smallType' + NUM).trigger("change");
                }, 'json');
    }
</script>
