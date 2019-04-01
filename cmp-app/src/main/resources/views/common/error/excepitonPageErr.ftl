<#include "/layout.ftl">
		<style type="text/css">
			body{ margin:0; padding:0; background:#efefef; font-family:Georgia, Times, Verdana, Geneva, Arial, Helvetica, sans-serif; }
			div#mother{ margin:0 auto; width:943px; height:572px; position:relative; }
			div#errorBox{ background: url(${base}/assets/i/404_bg.png) no-repeat top left; width:943px; height:572px; margin:auto; }
			div#errorText{ color:#39351e; padding:146px 0 0 446px }
			div#errorText p{ width:303px; font-size:14px; line-height:26px; }
			div.link{ /*background:#f90;*/ height:50px; width:145px; float:left; }
			div#home{ margin:20px 0 0 444px;}
			div#contact{ margin:20px 0 0 25px;}
			h1{ font-size:40px; margin-bottom:35px; }
		</style>
	<#--<@body>
		<@panel>
			<@panelBody style="padding:15px">
				<div class="div1" style="width: auto;height: 600px;position: relative;">
   					<div class="div2" style="position: absolute;left: 50%;top: 50%;margin-left: -600px;margin-top: -200px;width: 1200px;height: 400px;line-height: 60px;text-align: center;">
   						<font size="5" >抱歉，您当前访问的页面存在问题....</font><br/>
   						<font size="4" color="red">${(imageErrMessage)!}</font>
   						<br/><@backButton style="margin-left:5px;" name="返回上一页" />
   					</div>
				</div>
			</@panelBody>
		</@panel>
		
	</@body>-->
	<@body>
		<div id="mother">
			<div id="errorBox">
				<div id="errorText">
					<font size="5" color="blue">抱歉，您当前访问的页面存在问题....</font>
						<br/><br/>
   						<font size="4" color="brown" style="text-align: center;">${(imageErrMessage)!}</font>
   						<br/><br/><br/><br/><@backButton style="margin-left:5px;" name="返回上一页" />
				</div>
			</div>
		</div>
	</@body>