/**
 * 写字楼输入提示
 * 
 * 暂无延迟功能
 *  author:陈墨
 */
(function($) {
	var autocomplete = {
		elm : null,
		curObj : null,
		sectionId : "autoC_panel",
		result : "autoC_result",
		footer : "autoC_footer",
		content : "autoC_content",
		name_class : "autoC_name",
		count_class : "autoC_count",
		options : {
			basePath : "",
			action:"",
			param:"",
			top:0,
			left:0
		},
		_click_bind : function() {
			var _autocomplete = autocomplete;
			var elm = _autocomplete.elm;
			if(_autocomplete.hasBind("keyup")==true){
				elm.unbind("keyup");
				elm.unbind("keydown");
				$("body").unbind("click");
			}
			var timer = 0;
			elm.keyup(function(event) {
				var e = event.target;
				var keyCode = event.keyCode;
				var showFlag=false;
				if((keyCode>=48 && keyCode<=57)||
	                (keyCode>=65 && keyCode<=90)||
	                (keyCode>=96 && keyCode<=105)|| 
	                keyCode==8 || keyCode==32){
					showFlag=true;
				}
				if(showFlag==false){
					return;
				}
				_autocomplete.elm = $(e);
				
				
				 clearTimeout (timer);
				 timer = setTimeout(function(){
					 _autocomplete.show();
				 }, 400);
				//setTimeout(function(){_autocomplete.show();},1000);
			});
			elm.keydown(function(event) {
				var keyCode = event.keyCode;
				switch(keyCode){
					case 38:
						_autocomplete._select_item(true);
						break;
					case 40:
						_autocomplete._select_item(false);
						break;
					case 13:
						_autocomplete._replace_inputVal(true);
						break;
					case 9:
						_autocomplete.close();
						break;
					case 27:
						_autocomplete.close();
						break;
					default:
						return;
				}
			});
			$("body").click( function(event) {
				var e = event.target;		
				
				//如果点击处往上返回能遇到组件ID,那么取选中值
				if($(e).closest("#"+_autocomplete.content).length>0){
					_autocomplete._replace_inputVal(true);
				} 
				var p=$(e).parents("#"+_autocomplete.sectionId);
				if (e.id != _autocomplete.sectionId && e.id != _autocomplete.elm[0].id && p.length<=0) {
					_autocomplete.close();					
				}
			});
			_autocomplete._mouse_sel_item();
		},
		hasBind:function(type){
		  var elm = autocomplete.elm;
		  var events = elm.data("events");
		  if( events && events[type] ) return true;
		  return false;
		},
		_select_item:function(isUp){
			var _ac=autocomplete;
			var sec=$("#"+_ac.sectionId);
			var ul=$("#"+_ac.content);
			var items=ul.children("li");
			var sel_item=items.filter(".sel");
			if(sec.css("display") == "none"){
				return;
			}
			//true等于上,false 等于下
			if(isUp==true){
				if(!items.hasClass("sel")){
					items.last().addClass("sel");
				}else{
					sel_item.prev().addClass("sel");
					sel_item.removeClass("sel");
				}
			}else{
				if(!items.hasClass("sel")){
					items.first().addClass("sel");
				}else{
					sel_item.next().addClass("sel");
					sel_item.removeClass("sel");
				}
			}
			_ac._replace_inputVal(false);
		},
		_replace_inputVal:function(isClosePanel){
			var _ac=autocomplete;
			var sel_item=$("#"+_ac.content).children("li").filter(".sel").children("span");
			if(sel_item.html()!=null && sel_item.html()!=""){
				_ac.elm.val(sel_item.html());
			}
			_ac.elm.focus();
			if(isClosePanel==true){
				_ac.close();		
			}
		},
		_mouse_sel_item:function(){
			var _ac=autocomplete;
			var _content=$("#"+_ac.content);
			_content.mouseover(function(event){
				var e=event.target;
				if($(e).closest("#"+_ac.content).length>0){
					_content.children("li").removeClass("sel");
					$(e).closest("li").addClass("sel");
				}
			});
		},
		_reset_position : function() {
			var _autocomplete = autocomplete;
			var _elm = _autocomplete.elm;
			var _sectionId = $("#" + _autocomplete.sectionId);
			
			_sectionId.width(_autocomplete._getWidth());
			_sectionId.css("left", _autocomplete._getLeft());
			_sectionId.css("top", _autocomplete._getTop());
		},
		_getWidth : function() {
			return this.elm.width();
		},
		_getTop : function() {
			var _elm = autocomplete.elm;
			return _elm.position().top+_elm.outerHeight()+autocomplete.options.top;
		},
		_getLeft : function() {
			return this.elm.position().left+autocomplete.options.left;
		},
		_loadContent : function() {
			var _ac=this;
			var old_sec=$("#"+_ac.sectionId);
			if(old_sec.length>0){
				old_sec.remove();
			}
			var div_panel=$("<div id='"+_ac.sectionId+"'></div>");
			ul=$("<ul id='"+_ac.content+"'><ul>");
			var div_foot=$("<div id='"+_ac.footer+"'></div>");
			div_panel.append(ul);
			div_panel.append(div_foot);
			$("body").append(div_panel);
		},
		_load_item:function(){
			var _ac=this;
			var sec=$("#"+_ac.sectionId);
			var c_ul=sec.children("ul");
			var val= $.trim(_ac.elm.val());
			var param=_ac.options.param;
			if(param!=""){
				if(_ac.options.action.indexOf("?")!=-1){
					param="&"+param+"=";
				}else{
					param="?"+param+"=";
				}
			}
			if(val!=""){
				$.getJSON(_ac.options.basePath+_ac.options.action+param+val,function(rs){
					c_ul.empty();
					var list=rs.list;
					if(list.length>0){
						for(var i=0,j=list.length;i<j;i++){
							var name=list[i].itemname;
							var count=list[i].itemcount;
							var li_tmp=$("<li><span class='"+_ac.name_class+"'>"+name+"</span></li>");
							if(count!=null && count!=""){
								li_tmp.append($("<span class='"+_ac.count_class+"'>约"+count+"个结果</span>"));
							}
							c_ul.append(li_tmp);
						}
					}else{
						_ac.close();
					}
				});
			}else{
				_ac.close();
			}
		}
	};

	$.extend(autocomplete, {
		init : function(name) {
			var _autocomplete = autocomplete;
			_autocomplete._loadContent();
			_autocomplete._click_bind();
		},
		show : function() {
			var _ac=this;
			var _section = $("#" + _ac.sectionId);
			_ac._reset_position();
			
			if (_section.css("display") == "none") {
				_section.fadeIn();
			}
			_ac._load_item();
		},
		close : function() {
			var _ac=autocomplete;
			var _section = $("#" + _ac.sectionId);
			if (_section.css("display") == "block") {
				_section.hide();
			}
			$("#" + _ac.content).empty();
		}
	});

	var AC = {
		show : function() {
			return autocomplete.show();
		},
		close : function() {
			return autocomplete.close();
		},
		init : function(name) {
			return autocomplete.init(name);
		}
	};

	$.fn.autocomplete = function(options) {
		var ac = AC;
		var autocompleteObj = autocomplete;
		autocompleteObj.elm = this;
		$.extend(autocompleteObj.options, options);
		ac.init();
	};

})(jQuery);