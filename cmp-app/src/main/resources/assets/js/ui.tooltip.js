/*
 * jQuery UI Tooltip @VERSION
 *
 * Copyright (c) 2009 AUTHORS.txt (http://jqueryui.com/about)
 * Dual licensed under the MIT (MIT-LICENSE.txt)
 * and GPL (GPL-LICENSE.txt) licenses.
 *
 * http://docs.jquery.com/UI/Tooltip
 *
 * Depends:
 *	jquery.ui.core.js
 *	jquery.ui.widget.js
 *  jquery.ui.position.js
 */
(function($) {

var increments = 0;

$.widget("ui.tooltip", {
	_init: function() {
		var self = this;
		var tooltipId = "ui-tooltip-" + increments++;
		this.tooltip = $("<div/>").attr("id", tooltipId).attr("role", "tooltip").attr("aria-hidden", "true").addClass("ui-tooltip ui-widget ui-corner-all").addClass(this.options.tooltipClass).appendTo(document.body).hide();
		this.tooltipContent = $("<div/>").addClass("ui-tooltip-content").appendTo(this.tooltip);
		this.opacity = this.tooltip.css("opacity");
		this.element
			.bind("focus.tooltip mouseover.tooltip", function(event) {
				self.show($(event.target));
			})
			.bind("blur.tooltip mouseout.tooltip", function(event) {
				self.close();
			});
	},
	
	enable: function() {
		this.options.disabled = false;
	},
	
	disable: function() {
		this.options.disabled = true;
	},
	
	destroy: function() {
		this.element.unbind(".tooltip");
		this.tooltip.remove();
		$.widget.prototype.destroy.apply(this, arguments);
	},
	
	widget: function() {
		return this.tooltip;
	},
	
	show: function(target) {
		var self = this;
		this.current = target;
		this.currentTitle = target.attr("title");
		var content = this.options.content.call(target[0], function(response) {
			if (self.current == target)
				self.open(target, response);
		});
		if (content) {
			self.open(target, content);
		}
	},
	
	open: function(target, content) {
		if (!content)
			return;
		
		target.attr("title", "");
		
		if (this.options.disabled)
			return;
			
		this.tooltipContent.html(content);
		this.tooltip.position($.extend(this.options.position, {
			of: target
		}));
		
		this.tooltip.css("z-index", 9999);
		this.tooltip.attr("aria-hidden", "false");
		target.attr("aria-describedby", this.tooltip.attr("id"));

		if (this.tooltip.is(":animated"))
			this.tooltip.stop().show().fadeTo("normal", this.opacity);
		else
			this.tooltip.is(':visible') ? this.tooltip.fadeTo("normal", this.opacity) : this.tooltip.fadeIn();

		this._trigger("show", null, { target: target });
	},
	
	close: function() {
		if (!this.current)
			return;
		
		var current = this.current.attr("title", this.currentTitle);
		this.current = null;
		
		if (this.options.disabled)
			return;
		
		current.removeAttr("aria-describedby");
		this.tooltip.attr("aria-hidden", "true");
		
		var tooltip = this.tooltip;
		if (this.tooltip.is(':animated'))
			this.tooltip.stop().fadeTo("normal", 0, function() {$(this).css("z-index", -9999);});
		else
			this.tooltip.stop().fadeOut(function() {$(this).css("z-index", -9999);});

	}
	
});

$.extend($.ui.tooltip, {
    defaults: {
		tooltipClass: 'ui-tooltip-info',
		content: function() {
			return $(this).attr("tooltip");
		},
		position: {
			my: "left center",
			at: "right center",
			offset: "25 0",
			collision: "fit none"
		}
	}
});

})(jQuery);

jQuery(document).ready(function($) {
	jQuery.ajaxSetup({
		error: function(response, status, errorThrown) {
			if (status == 'timeout') {
				alert('\u9875\u9762\u54CD\u5E94\u8D85\u65F6\!\u8BF7\u91CD\u65B0\u64CD\u4F5C.');
			} else if (status == 'parsererror') {
				alert('\u89E3\u6790\u8FD4\u56DE\u6570\u636E\u65F6\u53D1\u751F\u4E86\u9519\u8BEF\:' + errorThrown);
			} else if (status == 'error') {
				switch (response.status) {
					case 404:
						alert('404:\u8BE5\u9875\u9762\u4E0D\u5B58\u5728\!');break;
					case 503:
						alert('503:\u670D\u52A1\u4E0D\u53EF\u7528\!');break;
					case 510:
						eval(response.responseText);break;
					case 0:
						alert('\u65E0\u6CD5\u8BBF\u95EE\u670D\u52A1\u5668,\u670D\u52A1\u5668\u5DF2\u7ECF\u505C\u6B62\u6216\u8005\u7F51\u7EDC\u4E0D\u53EF\u7528\!');
					default:
						alert('\u670D\u52A1\u5668\u6CA1\u6709\u6B63\u5E38\u54CD\u5E94,\u8FD4\u56DE\u4E86\u4EE3\u7801\:'+response.status+'!');
				}
			} else {
				alert('\u51FA\u73B0\u4E86\u4E0D\u80FD\u5904\u7406\u7684\u9519\u8BEF,\u9519\u8BEF\u4EE3\u7801\u662F' + status + ',\u539F\u56E0\u662F\:' + errorThrown);
			}
		},
		beforeSend: function(req) {
			var d = this.data;
			if (d) {
				if (typeof(d) == 'string') d = d.split('&');
				$.each(d, function(i, p) {
					$('*[name="'+p.split('=')[0]+'"]').removeClass('ui-error').tooltip('destroy');
				});
			}
		}
	});
	jQuery('*[tooltip]').tooltip();
});

/*
 * jQuery UI Position @VERSION
 *
 * Copyright (c) 2009 AUTHORS.txt (http://jqueryui.com/about)
 * Dual licensed under the MIT (MIT-LICENSE.txt)
 * and GPL (GPL-LICENSE.txt) licenses.
 *
 * http://docs.jquery.com/UI/Position
 */
(function($) {

$.ui = $.ui || {};

var horizontalPositions = /left|center|right/,
	horizontalDefault = 'center',
	verticalPositions = /top|center|bottom/,
	verticalDefault = 'center',
	_position = $.fn.position;

$.fn.position = function(options) {
	if (!options || !options.of) {
		return _position.apply(this, arguments);
	}
	
	// make a copy, we don't want to modify arguments
	options = $.extend({}, options);

	var target = $(options.of),
		collision = (options.collision || 'flip').split(' '),
		offset = options.offset ? options.offset.split(' ') : [0, 0],
		targetWidth,
		targetHeight,
		basePosition;

	if (options.of.nodeType === 9) {
		targetWidth = target.width();
		targetHeight = target.height();
		basePosition = { top: 0, left: 0 };
	} else if ('scrollTo' in options.of && options.of.document) {
		targetWidth = target.width();
		targetHeight = target.height();
		basePosition = { top: target.scrollTop(), left: target.scrollLeft() };
	} else if (options.of.preventDefault) {
		// force left top to allow flipping
		options.at = 'left top';
		targetWidth = targetHeight = 0;
		basePosition = { top: options.of.pageY, left: options.of.pageX };
	} else {
		targetWidth = target.outerWidth();
		targetHeight = target.outerHeight();
		basePosition = target.offset();
	}

	// force my and at to have valid horizontal and veritcal positions
	// if a value is missing or invalid, it will be converted to center 
	$.each(['my', 'at'], function() {
		var pos = (options[this] || '').split(' ');
		pos = pos.length == 1
			? horizontalPositions.test(pos[0])
				? pos.concat([verticalDefault])
				: verticalPositions.test(pos[0])
					? [horizontalDefault].concat(pos)
					: [horizontalDefault, verticalDefault]
			: pos;
		pos[0] = horizontalPositions.test(pos[0]) ? pos[0] : horizontalDefault;
		pos[1] = verticalPositions.test(pos[1]) ? pos[1] : verticalDefault;
		options[this] = pos;
	});

	// normalize collision option
	if (collision.length == 1) {
		collision[1] = collision[0];
	}

	// normalize offset option
	offset[0] = parseInt(offset[0], 10) || 0;
	if (offset.length == 1) {
		offset[1] = offset[0];
	}
	offset[1] = parseInt(offset[1], 10) || 0;

	switch (options.at[0]) {
		case 'right':
			basePosition.left += targetWidth;
			break;
		case horizontalDefault:
			basePosition.left += targetWidth / 2;
			break;
	}

	switch (options.at[1]) {
		case 'bottom':
			basePosition.top += targetHeight;
			break;
		case verticalDefault:
			basePosition.top += targetHeight / 2;
			break;
	}

	basePosition.left += offset[0];
	basePosition.top += offset[1];

	return this.each(function() {
		var elem = $(this),
			elemWidth = elem.outerWidth(),
			elemHeight = elem.outerHeight(),
			position = $.extend({}, basePosition),
			over,
			myOffset,
			atOffset;

		switch (options.my[0]) {
			case 'right':
				position.left -= elemWidth;
				break;
			case horizontalDefault:
				position.left -= elemWidth / 2;
				break;
		}

		switch (options.my[1]) {
			case 'bottom':
				position.top -= elemHeight;
				break;
			case verticalDefault:
				position.top -= elemHeight / 2;
				break;
		}

		$.each(['left', 'top'], function(i, dir) {
			($.ui.position[collision[i]] &&
				$.ui.position[collision[i]][dir](position, {
					targetWidth: targetWidth,
					targetHeight: targetHeight,
					elemWidth: elemWidth,
					elemHeight: elemHeight,
					offset: offset,
					my: options.my,
					at: options.at
				}));
		});

		(options.stackfix !== false && $.fn.stackfix && elem.stackfix());
		// the by function is passed the offset values, not the position values
		// we'll need the logic from the .offset() setter to be accessible for
		// us to calculate the position values to make the by option more useful
		($.isFunction(options.by) ? options.by.call(this, position) : elem.offset(position));
	});
};

$.ui.position = {
	fit: {
		left: function(position, data) {
			var over = position.left + data.elemWidth - $(window).width() - $(window).scrollLeft();
			position.left = over > 0 ? position.left - over : Math.max(0, position.left);
		},
		top: function(position, data) {
			var over = position.top + data.elemHeight - $(window).height() - $(window).scrollTop();
			position.top = over > 0 ? position.top - over : Math.max(0, position.top);
		}
	},

	flip: {
		left: function(position, data) {
			if (data.at[0] == 'center')
				return;
			var over = position.left + data.elemWidth - $(window).width() - $(window).scrollLeft(),
				myOffset = data.my[0] == 'left' ? -data.elemWidth : data.my[0] == 'right' ? data.elemWidth : 0,
				offset = -2 * data.offset[0];
			position.left += position.left < 0 ? myOffset + data.targetWidth + offset : over > 0 ? myOffset - data.targetWidth + offset : 0;
		},
		top: function(position, data) {
			if (data.at[1] == 'center')
				return;
			var over = position.top + data.elemHeight - $(window).height() - $(window).scrollTop(),
				myOffset = data.my[1] == 'top' ? -data.elemHeight : data.my[1] == 'bottom' ? data.elemHeight : 0,
				atOffset = data.at[1] == 'top' ? data.targetHeight : -data.targetHeight,
				offset = -2 * data.offset[1];
			position.top += position.top < 0 ? myOffset + data.targetHeight + offset : over > 0 ? myOffset + atOffset + offset : 0;
		}
	}
};


// the following functionality is planned for jQuery 1.4
// based on http://plugins.jquery.com/files/offset.js.txt
$.fn.extend({
	_offset: $.fn.offset,
	offset: function(newOffset) {
	    return !newOffset ? this._offset() : this.each(function() {
			var elem = $(this),
				// we need to convert static positioning to relative positioning
				isRelative = /relative|static/.test(elem.css('position')),
				hide = elem.css('display') == 'none';

			(isRelative && elem.css('position', 'relative'));
			(hide && elem.show());

			var offset = elem.offset(),
				delta = {
					left : parseInt(elem.css('left'), 10),
					top: parseInt(elem.css('top'), 10)
				};

			// in case of 'auto'
			delta.left = !isNaN(delta.left)
				? delta.left
				: isRelative
					? 0
					: this.offsetLeft;
			delta.top = !isNaN(delta.top)
				? delta.top
				: isRelative
					? 0
					: this.offsetTop;

			// allow setting only left or only top
			if (newOffset.left || newOffset.left === 0) {
				elem.css('left', newOffset.left - offset.left + delta.left);
			}
			if (newOffset.top || newOffset.top === 0) {
				elem.css('top', newOffset.top - offset.top + delta.top);
			}

			(hide && elem.hide());
		});
	}
});

})(jQuery);