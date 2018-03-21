$(function(){

	$.extend($.fn.validatebox.defaults.rules, {  
        phoneRex: {  
            validator: function(value){  
            var rex=/^1[3-8]+\d{9}$/;  
            if(rex.test(value)){  
              return true;  
            }else{  
               return false;  
            }  
            },  
            message: '请输入正确的手机号码格式'  
        },idcard: {// 验证身份证
            validator: function (value) {
                return /^\d{15}(\d{2}[A-Za-z0-9])?$/i.test(value);
            },
            message: '身份证号码格式不正确'
        
	    },equaldDate: {
                        validator: function(value, param) {
                            var d1 = $(param[0]).datetimebox('getValue'); //获取开始时间
                            return value >= d1; //有效范围为大于开始时间的日期
                        },
                        message: '结束日期不能早于开始日期!'
                    }
                


    });  
	
	coachData();
})


function coachData(){
	$("#theCoachIsKi2").combotree({
		url:"../kiin/findUserTreeKinn",
		checkbox:true,
		panelHeight : 'auto'
		
	});
	/*alert("SD");
	alert($('#knumber').val());
	$('#theCoachIsKi2').combotree('setValue',$('#knumber').val());
	$('#theCoachIsKi2').combotree('setText', $('#kname').val());*/

	}
  
  

 