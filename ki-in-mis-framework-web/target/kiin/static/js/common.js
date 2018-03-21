$.fn.serializeObject = function() {
	var o = {};
	var a = this.serializeArray();
	$.each(a, function() {
		if (o[this.name]) {
			if (!o[this.name].push) {
				o[this.name] = [ o[this.name] ];
			}
			o[this.name].push(this.value || '');
		} else {
			o[this.name] = this.value || '';
		}
	});
	return o;
};

//日期
$.extend($.fn.validatebox.defaults.rules,
        {
            equaldDate: {
                validator: function(value, param) {
                    var d1 = $(param[0]).datetimebox('getValue'); //获取开始时间
                    return value >= d1; //有效范围为大于开始时间的日期
                },
                message: '结束日期不能早于开始日期!'
            }
        });
var buttons = $.extend([], $.fn.datebox.defaults.buttons);  
buttons.splice(1, 0, {  
text: '清空',  
handler : function(target) {  
    $(target).combo("setValue", "").combo("setText", ""); // 设置空值  
    $(target).combo("hidePanel"); // 点击清空按钮之后关闭日期选择面板  
}  
});  
$.fn.datebox.defaults.buttons = buttons;  