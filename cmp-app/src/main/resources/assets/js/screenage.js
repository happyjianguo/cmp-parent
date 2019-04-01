var $ = jQuery;
var iv1;
var top_width;
var top_height;
console.log("进入screenage.js")
$(function s () {
	// 自定义事件
	function showCoords(c) {// 显示选择区域的坐标
		var rate = iv1.iviewer('info', 'zoom') / 100;//计算当前图片放大的比例，因为iv1.iviewer('info', 'zoom')得到的是以%为单位的值，所以除以100得到相对于原图片放大的倍数，假如缩小一倍（50%），rate就是0.5，放大为200%，rate就是2
		$('#x').val((c.x / rate).toFixed(2));
		$('#y').val((c.y / rate).toFixed(2));
		$('#x2').val((c.x2 / rate).toFixed(2));
		$('#y2').val((c.y2 / rate).toFixed(2));
		$('#w').val((c.w / rate).toFixed(2));
		$('#h').val((c.h / rate).toFixed(2));
	}
	
	function destorycrop(c) {// 销毁crop插件
		if (jcrop_api != undefined) {
			jcrop_api.destroy();
			$('.viewer > img').css('visibility', 'visible');
		}
	}
	
	iv1 = $("#viewer").iviewer( {
		src : $('#imagePath').val(),
		update_on_resize : false,
		zoom_min : 20,
		zoom_max : 400,
		zoom_animation : false,
		//onMouseMove : function(ev, coords) {
		//},
		onStartDrag : function(ev, coords) {
				//return false;
		}, // this image will not be dragged
		onDrag : function(ev, coords) {
			$('.flexdiv').remove();
		},
		ui_disabled : true,
		onZoom : function(ev, new_zoom) {
			$('.flexdiv').remove();
		}
	});
	
	// 取顶层图片大小(图片可视区域大小)
	top_width = $("#viewer").width();
	top_height = $("#viewer").height();

	$("#in").click(function() {
		iv1.iviewer('zoom_by', 1);
	});
	$("#out").click(function() {
		iv1.iviewer('zoom_by', -1);
	});
	$("#orig").click(function() {
		iv1.iviewer('set_zoom', 100);
	});
	
	var jcrop_api;// 保存jcrop插件对象
	// 为跟踪定位模式添加事件处理
	$('.traceposition').click(function() {
		$(".viewer > img").Jcrop( {
			onChange : showCoords,
			onSelect : destorycrop
		}, function() {
			jcrop_api = this;
		});
	});
		
	//处理浏览器滚动条滚动事件
	$(window).scroll(function() {
		var scrollTop = $(this).scrollTop();
		$("#right").css('margin-top', scrollTop + 50 + 'px');
		console.log("滚动1")
	})
});

	function adjustposition(coords) {
        console.log(coords);
        var left = Math.abs(parseInt($('.viewer >img').css('left')));
		var top = Math.abs(parseInt($('.viewer >img').css('top')));
		var beyondx = parseInt(coords.x) + parseInt(coords.w)
				- parseInt(top_width) - left;
		var beyondy = parseInt(coords.y) + parseInt(coords.h)
				- parseInt(top_height) - top;
		var inlinex = parseInt(coords.x)
				- Math.abs(parseInt($('.viewer >img').css('left')));
		var inliney = parseInt(coords.y)
				- Math.abs(parseInt($('.viewer >img').css('top')));
		if (beyondx > 0) {// 超出屏幕所能显示的宽度
			$('.viewer > img').css('left', -(beyondx + left));
		} else if (inlinex <= 0) {// 在偏移的区域内
			$('.viewer > img').css('left', -parseInt(coords.x));
		}
		if (beyondy > 0) {// 超出屏幕所能显示的高度
			$('.viewer > img').css('top', -(beyondy + top));
			$('body').scrollTop(beyondy + 200);   // 将图片向上偏移200px
			console.log("偏移1")
		} else if (inliney <= 0) {// 在偏移的区域内
			var scrolly = parseInt($('body').scrollTop())
					- parseInt(coords.y);
			if (scrolly > 0) {
				$('body').scrollTop(scrolly);
			}
			$('.viewer > img').css('top', -(parseInt(coords.y)));
		}
	}

	function getDisplayCoords(coords) {
		var rate = iv1.iviewer('info', 'zoom') / 100;
		return {
			x : (coords.x * rate),
			y : (coords.y * rate),
			w : (coords.w * rate),
			h : (coords.h * rate)
		}
	}

	function displayRect(coords) {
		if (coords == undefined) {
			return;
		}
		coords = getDisplayCoords(coords);
		adjustposition(coords);
		var left = $('.viewer > img').position().left;
		var top = $('.viewer > img').position().top;
		coords.x = coords.x + left;
		coords.y = coords.y + top;
		$('.flexdiv').remove();
		var flexdiv = $("<div class='flexdiv'></div>");
		$(flexdiv).css( {
			position : "absolute",
			top : coords.y + "px",
			left : coords.x + "px",
			cursor : "move",
			width : coords.w + "px",
			height : coords.h + "px"
		})
		$(".viewer").append(flexdiv);
	}