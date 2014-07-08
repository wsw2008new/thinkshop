
//验证字节长度
jQuery.validator.addMethod("byteRangeLength", function(value, element,
		param) {
	var length = value.length;
	for ( var i = 0; i < value.length; i++) {
		if (value.charCodeAt(i) > 127) {
			length++;
		}
	}
	return this.optional(element)
			|| (length >= param[0] && length <= param[1]);
}, $.validator.format("请确保输入的值在{0}-{1}个字符之间"));


//身份证验证
jQuery.validator.addMethod("cardid", function(value, element) {
	var chrnum =  /[\d]{6}(19|20)*[\d]{2}((0[1-9])|(11|12))([012][\d]|(30|31))[\d]{3}[xX\d]*/; 
	return this.optional(element)
			|| (chrnum.test(value));
}, $.validator.format("请输入正确的身份证号码"));
//姓名验证
jQuery.validator.addMethod("namex", function(value, element) {
	var name = /^[赵钱孙李周吴郑王冯陈褚卫蒋沈韩杨朱秦尤许何吕施张孔曹严华金魏陶姜戚谢邹喻柏水窦章云苏潘葛奚范彭郎鲁韦昌马苗凤花方俞任袁柳酆鲍史唐费廉岑薛雷贺倪汤滕殷罗毕郝邬安常乐于时傅皮卞齐康伍余元卜顾孟平黄和穆萧尹姚邵湛汪祁毛禹狄米贝明臧计伏成戴谈宋茅庞熊纪舒屈项祝董梁杜阮蓝闵席季麻强贾路娄危江童颜郭梅盛林刁钟徐邱骆高夏蔡田樊胡凌霍虞万支柯昝管卢莫柯房裘缪干解应宗丁宣贲邓郁单杭洪包诸左石崔吉钮龚程嵇邢滑裴陆荣翁荀羊于惠甄曲家封芮羿储靳汲邴糜松井段富巫乌焦巴弓牧隗山谷车侯宓蓬全郗班仰秋仲伊宫宁仇栾暴甘钭历戎祖武符刘景詹束龙叶幸司韶郜黎蓟溥印宿白怀蒲邰从鄂索咸籍赖卓蔺屠蒙池乔阳郁胥能苍双闻莘党翟谭贡劳逄姬申扶堵冉宰郦雍却璩桑桂濮牛寿通边扈燕冀浦尚农温别庄晏柴瞿阎充慕连茹习宦艾鱼容向古易慎戈廖庾终暨居衡步都耿满弘匡国文寇广禄阙东欧殳沃利蔚越夔隆师巩厍聂晁勾敖融冷訾辛阚那简饶空曾毋沙乜养鞠须丰巢关蒯相查后荆红游竺权逮盍益桓公上赫皇澹淳太轩令宇长盖况闫].{1,5}$/; 
	return this.optional(element)
			|| (name.test(value));
}, $.validator.format("请输入真实姓名"));

//验证中文,字母,数字,下划线
jQuery.validator.addMethod("nobadchar", function(value, element) {
	var chrnum = /^([a-zA-Z0-9_\u4e00-\u9fa5]+)$/; 
	return this.optional(element)
			|| (chrnum.test(value));
}, $.validator.format("只能包含中文,字母,数字,下划线"));

//验证字母
jQuery.validator.addMethod("zimu", function(value, element) {
	var zimu = /^([a-zA-Z]+)$/; 
	return this.optional(element)
			|| (zimu.test(value));
}, $.validator.format("请输入英文"));

//验证中文,字母,数字,下划线  横杠,斜杠,括号
jQuery.validator.addMethod("nobad", function(value, element) {
	var chrnum = /^([a-zA-Z0-9_\-\/\(\)\u4e00-\u9fa5]+)$/; 
	return this.optional(element)
			|| (chrnum.test(value));
}, $.validator.format("只能包含中文,字母,数字,下划线  横杠,斜杠,括号"));
//验证金额
jQuery.validator.addMethod("amount", function(value, element) { 
	var chrnum = /^(?!0(\.00?)?$)\d+(\.\d\d?)?$/; 
	return this.optional(element) || (chrnum.test(value)); 
	}, $.validator.format("请输入正确的金额")); 
//验证小数或者为0
jQuery.validator.addMethod("number", function(value, element) { 
	var chrnum = /^\d+(\.\d+)?$/g; 
	return this.optional(element) || (chrnum.test(value)); 
	}, $.validator.format("请输入正确的金额")); 


//验证字母,数字,下划线
jQuery.validator.addMethod("chrnum", function(value, element) { 
	var chrnum = /^([a-zA-Z0-9_]+)$/; 
	return this.optional(element) || (chrnum.test(value)); 
	}, $.validator.format("只能输入数字和字母或下划线")); 

//验证手机号码
jQuery.validator.addMethod("mobile", function(value, element) { 
	var length = value.length; 
	var mobile = /^(((13[0-9]{1})|(15[0-9]{1})|18[0-9]{1})+\d{8})$/ ;
	return this.optional(element) || (length == 11 && mobile.test(value)); 
	}, "手机号码格式错误"); 

//电话号码验证 
jQuery.validator.addMethod("phone", function(value, element) { 
var tel = /^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$/; 
return this.optional(element) || (tel.test(value)); 
}, "电话号码格式错误"); 

//邮政编码验证 
jQuery.validator.addMethod("zipCode", function(value, element) { 
var tel = /^[0-9]{6}$/; 
return this.optional(element) ||3 (tel.test(value)); 
}, "邮政编码格式错误"); 

//IP地址验证 
jQuery.validator.addMethod("ip", function(value, element) { 
var ip = /^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/; 
return this.optional(element) || (ip.test(value) && (RegExp.$1 < 256 && RegExp.$2 < 256 && RegExp.$3 < 256 && RegExp.$4 < 256)); 
}, "Ip地址格式错误"); 
//网址
jQuery.validator.addMethod("comurl", function(value, element) {
	return this.optional(element) || /^[http:\/\/A-Za-z0-9]+\.[A-Za-z0-9]+[\/=\?%\-&_~`@[\]\:+!]*([^<>])*$/.test(value);
}, "网址格式错误");
//整数
jQuery.validator.addMethod("integer", function(value, element) {
			var tel =  /^[0-9]?\d+$/;
			return this.optional(element) || (tel.test(value));
		}, "请填写整数");