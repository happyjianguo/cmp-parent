<#include "/layout_img.ftl"/>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=IE11">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script type="text/javascript" src="${base}/assets/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="${base}/assets/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${base}/assets/js/jqueryui.js"></script>
    <script type="text/javascript" src="${base}/assets/js/jquery.cookie.js"></script>
    <script type="text/javascript" src="${base}/assets/js/mousewheel.js"></script>
    <script type="text/javascript" src="${base}/assets/js/iviewer.js"></script>
    <script type="text/javascript" src="${base}/assets/js/jquery.Jcrop.min.js"></script>
    <script type="text/javascript" src="${base}/assets/js/screenage.js"></script>
    <script type="text/javascript" src="${base}/assets/js/ui.tooltip.js"></script>
    <script type="text/javascript" src="${base}/assets/js/jquery.jcarousellite.min.js"></script>
    <link type="text/css" rel="stylesheet" href="${base}/assets/css/custom.css"/>
    <link type="text/css" rel="stylesheet" href="${base}/assets/css/iviewer.css"/>
    <link type="text/css" rel="stylesheet" href="${base}/assets/css/jcrop.css"/>
    <script>
        window.onload = function () {
            var current = 0;
            document.getElementById('ivImg').ondblclick = function () {
                current = (current + 90) % 360;
                this.style.transform = 'rotate(' + current + 'deg)';
            }
        }

        //改变类型时显示的图片
        function changeImg(url, imageInfo) {
            console.log(url);
            document.getElementById('ivImg').src = url;
            var spanInfo = document.getElementById('contDetail');
            spanInfo.innerHTML = imageInfo;
        }
        function uploadButton(batchNo,userNo,systemId,branchCode){
            window.location.href = "/cmp-app/assets/cmp_/uploadPage?batchNo="+batchNo+"&userNo="+userNo+"&systemId="+systemId+"&branchCode="+branchCode;
        }
        function uploadButtons(batchNo){
            window.location.href = "/cmp-app/cmp_/uploadPage?batchNo="+batchNo;
        }
        $(function () {
            //菜单隐藏展开
            var tabs_i = 0
            $('.vtitle').click(function () {
                var _self = $(this);
                var j = $('.vtitle').index(_self);
                if (tabs_i == j) return false;
                tabs_i = j;
                $('.vtitle em').each(function (e) {
                    if (e == tabs_i) {
                        $('em', _self).removeClass('v01').addClass('v02');
                    } else {
                        $(this).removeClass('v02').addClass('v01');
                    }
                });
                $('.vcon').slideUp().eq(tabs_i).slideDown();
            });
        })
    </script>
    <style>
        li {
            display: inline;
        }

        .ThumbPicBorder {
            width: 100%;
            text-align: center;
            height: 52px;
        }

        * {
            margin: 0;
            padding: 0;
            list-style-type: none;
        }

        a, img {
            border: 0;
        }

        body {
            font: 12px/180% Arial, Helvetica, sans-serif, "新宋体";
        }

        a, a:hover {
            text-decoration: none;
        }

        /*收缩菜单*/
        .v {
            float: right;
            width: 14px;
            height: 14px;
            overflow: hidden;
            background: url(${base}/assets/i/jj/vicon.png) no-repeat;
            display: inline-block;
            margin-top: -5px;
            margin-bottom: -5px;
        }

        .v01 {
            background-position: 0 0;
        }

        .v02 {
            background-position: 0 -16px;;
        }

        .vtitle {
            height: 35px;
            background: #ffffff;
            line-height: 35px;
            border: 1px solid #ccb6a9;
            margin-top: -1px;
            padding-left: 20px;
            font-size: 15px;
            color: #4d4d4d;
            font-family: "\5FAE\8F6F\96C5\9ED1";
            cursor: pointer;
        }

        .vtitle em {
            margin: 10px 10px 0 0;
        }

        .vconlist {
            background: #f9ffef;
        }

        .vconlist li a {
            height: 30px;
            line-height: 30px;
            padding-left: 30px;
            display: block;
            font-size: 14px;
            color: #0906bf;
            font-family: "\5FAE\8F6F\96C5\9ED1";
            cursor: pointer;
        }

        .vconlist li.select a, .vconlist li a:hover {
            color: #ed4948;
            text-decoration: none;
        }
    </style>

</head>
<body>
<table border="0" sytle="solid #eaeaea;" width="100%;">
    <tr>
    <#assign val="" />
        <td style="font-size:14px;face:'宋体';text-align:center; width: 100px;" valign="top"">
        <div style="text-align:left;">
            <span style="font-size:12.5px;face:'宋体';color:black;">姓名:${(tmCmpMain.name)!}</span>
        </div>
        <div style="text-align:left;">
            <span style="font-size:12.5px;face:'宋体';color:black;">证件号:${(tmCmpMain.idNo)!}</span>
        </div>
        <div style="text-align:left;">
            <span style="font-size:12.5px;face:'宋体';color:black;">批次号:${(tmCmpMain.batchNo)!}</span>
        </div>
<#--        <div style="text-align:left;">
            <span style="font-size:12.5px;face:'宋体';color:black;">备注:${(remark)!}</span>
        </div>-->
        <hr/>
        <div style="text-align:left;word-wrap: break-word;word-break: break-all;">
            <span style="font-size:14px;face:'宋体';color:#000000;">影像详情:</span>
            <span id="contDetail" style="font-size:12.5px;face:'宋体';color:#0000FF;"></span>
        </div>
        <div style="text-align:left;">
        <#if (userNo)??&&(systemId)??&&(branchCode)??>
            <input type="button" value="上传"
                   onclick="uploadButton('${(tmCmpMain.batchNo)!}','${(userNo)!}','${(systemId)}','${(branchCode)}')"/>
        <#else>
            <input type="button" value="上传" onclick="uploadButtons('${(tmCmpMain.batchNo)!}')">
        </#if>
        </div>
        <hr/>
        <br/>
        </div>
        <#if allContextMap?? >
            <#if (allContextMap?size>1)>
            <#else>
                <#list allContextMap?keys as k1><#--批次号-->
                    <#list allContextMap[k1]?keys as k2><#--小类型-->
                        <div class="vtitle"><em class="v"></em>${k2}</div>
                        <div class="vcon" style="display: none;">
                            <ul class="vconlist clearfix">
                                <#list allContextMap[k1][k2] as tmCmpContent><#--实体类集合<List>-->
                                    <li>
                                        <a onclick="changeImg('${tmCmpContent.contRelPath!}${tmCmpContent.contAbsPath!}',
                                                '类型:${tmCmpContent.subTypeDesc!}; 由${tmCmpContent.updateUser!}; 于  ${tmCmpContent.formatDate!} 上传')"
                                           style="width:100%;text-align:left;">${tmCmpContent.formatDate}</a>
                                        <input type="hidden" id="imagePath"
                                               value="${tmCmpContent.contRelPath!''}${tmCmpContent.contAbsPath!''}"/>
                                    </li>
                                </#list>
                            </ul>
                        </div>
                    <div style="text-align:center;clear:both">



                    <#--                    <#list allContextMap[k1][k2] as tmCmpContent>&lt;#&ndash;实体类集合<List>&ndash;&gt;
                                                <button type="button" onclick="changeImg('${tmCmpContent.contRelPath!}${tmCmpContent.contAbsPath!}',
                                                        '类型:${tmCmpContent.subTypeDesc!}; 由${tmCmpContent.updateUser!}; 于  ${tmCmpContent.formatDate!} 上传')"
                                                        style="width:100%;text-align:left;">${tmCmpContent.subTypeDesc}</button>
                                                <input type="hidden" id="imagePath" value="${tmCmpContent.contRelPath!''}${tmCmpContent.contAbsPath!''}"/>
                                        </#list>-->
                    </#list>
                </#list>
            </#if>
        </#if>
        </div>
            </td>
            <td style="width:85%;height:670px;">
                <div id="divone"  style="width:100%;height:100%;display: block" >

                <div id="viewer" style="width:100%;height:100%;" class="viewer">
                    <span style="font-size:12.5px;face:'宋体';color:#0000FF;">
                    &nbsp;&nbsp;滑动鼠标滚轮即放大缩小图片，90 度旋转请双击图片；
                   </span>
                </div>

                <div class="SpaceLine"></div>
                <div class="HS15"></div>
                <div class="main">
                    <div class="ThumbPicBorder">
                        <div class="jCarouselLite FlLeft">
                            <img src="${base}/assets/i/jj/ArrowL.jpg" id="btnPrev" class="FlLeft"
                                 style="cursor:pointer;height: 50px;"/>
                        <#if allContextList?? >
                            <#list allContextList as tmCmpContent>
                                <li rel=${tmCmpContent_index+1}><a
                                        onclick="changeImg('${tmCmpContent.contRelPath!}${tmCmpContent.contAbsPath!}'
                                                ,'类型:${tmCmpContent.subTypeDesc!}; 由 ${tmCmpContent.updateUser!}; 于  ${tmCmpContent.formatDate!} 上传')">
                                    <img src=${tmCmpContent.contRelPath!}${tmCmpContent.contAbsPath!} style="width:50px;height:50px"></a>
                                </li>
                            </#list>
                        </#if>
                            <img src="${base}/assets/i/jj/ArrowR.jpg" id="btnNext" class="FlLeft"
                                 style="cursor:pointer;height: 50px;"/>
                        </div>
                    </div>
                </div>
                <div class="HS15"></div>
                </div>
            </td>
    </tr>
</table>
<script type="text/javascript">
    /*    var  list =document.getElementById("list");
        //获取ul对象
        list=list.getElementsByTagName("li")
        //获取ul下的li对象,是数组集合
        for (var i=0,l=list.length;i<l;i++){
            list[i].rel=i
        }*/
    //缩略图滚动事件
    $(".jCarouselLite").jCarouselLite({
        btnNext: "#btnNext",
        btnPrev: "#btnPrev",
        scroll: 1,
        speed: 240,
        circular: false,
        visible: 6
    });
</script>
</body>
</html>

