$(function(){
	wu();
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
	        }, idcared: {     
		        validator: function(value,param){    
		            var flag= isCardID(value);  
		            return flag==true?true:false;    
		        },     
		        message: '不是有效的身份证号码'    
		    },equaldDate: {
	                        validator: function(value, param) {
	                            var d1 = $(param[0]).datetimebox('getValue'); //获取开始时间
	                            return value >= d1; //有效范围为大于开始时间的日期
	                        },
	                        message: '结束日期不能早于开始日期!'
	                    }
	                


	    });  
	 
	schooldata2();
	coachData();
	gradedate();
})
//加载学校2
	function schooldata2(){
		$.ajax({
			url : '../stu/gotoSchool',
			dataType : 'json',
			success : function(jsonstr) {
				jsonstr.unshift({
					'schoolNumber' : '0',
					'schoolName' : '请选择'
				});
		$('#schoolNumber1')
		.combobox(
				{
					url : '../stu/gotoSchool',
					
					valueField : 'schoolNumber',
					textField : 'schoolName',
					editable :false,
					panelHeight : 'auto'

				});
			}
		});

	}




//棋院与教练
function coachData(){
	$("#theStudentsAreKiin").combotree({
		url:"../kiin/findUserTreeKinn",
		checkbox:true,
		panelHeight : 'auto',
		onSelect:function(record){//单击某一个节点时
			var t = $('#theStudentsAreKiin').combotree('tree');	// 获取树对象
			var n = t.tree('getSelected');		// 获取选择的节点
			//加载学校
			$.ajax({
				url : '../stu/findCoach?id='+n.id,
				dataType : 'json',
				success : function(jsonstr) {
					jsonstr.unshift({
						'coachnumber' : '0',
						'coachname' : '请选择'
					});
					$('#studentCoach')
							.combobox(
									{
										data : jsonstr,
										required:true,
										valueField : 'coachnumber',
										textField : 'coachname',
										panelHeight : 'auto',
										onLoadSuccess : function() { // 加载完成后,设置选中第一项
											var val = $(
													this)
													.combobox(
															"getData");

											for ( var item in val[0]) {
												if (item == "coachnumber") {
													$(
															this)
															.combobox(
																	"select",
																	val[0][item]);
												}
											}
										}

									});
							}
						});
		}
	});
}
function isCardID(sId){   
    var iSum=0 ;  
    var info="" ;  
    if(!/^\d{17}(\d|x)$/i.test(sId)) return "你输入的身份证长度或格式错误";   
    sId=sId.replace(/x$/i,"a");   
    if(aCity[parseInt(sId.substr(0,2))]==null) return "你的身份证地区非法";   
    sBirthday=sId.substr(6,4)+"-"+Number(sId.substr(10,2))+"-"+Number(sId.substr(12,2));   
    var d=new Date(sBirthday.replace(/-/g,"/")) ;  
    if(sBirthday!=(d.getFullYear()+"-"+ (d.getMonth()+1) + "-" + d.getDate()))return "身份证上的出生日期非法";   
    for(var i = 17;i>=0;i --) iSum += (Math.pow(2,i) % 11) * parseInt(sId.charAt(17 - i),11) ;  
    if(iSum%11!=1) return "你输入的身份证号非法";   
    return true;//aCity[parseInt(sId.substr(0,2))]+","+sBirthday+","+(sId.substr(16,1)%2?"男":"女")   
}   
  
  
var gindex=0;
function wu(){
	$('#bg').datagrid({
        rownumbers : true,
        fitColumns:true,
        singleSelect:true,
        idField : 'week',
        columns : [ [ {
            field : 'week',
            title : '选择星期',
            width : 40,
            align : 'center',

            editor : {
                type: 'combobox',
                options: {
                    valueField: 'label',
                    textField: 'value',
                    editable:false,
                    required:true,
                    missingMessage:'该项为必填项',
                	panelHeight : 'auto',
                    data: [{
            			label: '星期一',
            			value: '星期一'
            		},{
            			label: '星期二',
            			value: '星期二'
            		},{
            			label: '星期三',
            			value: '星期三'
            		},{
            			label: '星期四',
            			value: '星期四'
            		},{
            			label: '星期五',
            			value: '星期五'
            		},{
            			label: '星期六',
            			value: '星期六'
            		},{
            			label: '星期日',
            			value: '星期日'
            		}]
                    
                }
            }
        }, {
            field : 'timeduan',
            title : '时间段',
            width : 40,
            align : 'center',
            editor : {
                type: 'combobox',
                options: {
                	 valueField: 'label',
                     textField: 'value',
                     editable:false,
                     required:true,
                     missingMessage:'该项为必填项',
                 	panelHeight : 'auto',
                     data: [{
             			label: '上午',
             			value: '上午'
             		},{
             			label: '下午',
             			value: '下午'
             		},{
             			label: '晚上',
             			value: '晚上'
             		}]
                    
                }
            }
        }] ],
        loadFilter : function(data) {
            var data_ = {
                "rows" : data.attributes.rows,
                "total" : data.obj.totalSize
            };
            return data_;

        }
    });
    var editBoxing = undefined;
    $('#bg').datagrid({
        toolbar : [ {
            text : '新增',
            iconCls : 'icon-edit',
            handler : function() {
                if (editBoxing == undefined) {
                    editBoxing = 0;
                    $('#bg').datagrid("insertRow", {
                        index : editBoxing, // 索引从0开始
                        row : {
                            time : '',
                            week : '',
                        }
                    });
                    $('#bg').datagrid('beginEdit', editBoxing);
                } else {
                    $.messager.alert('警告', '尚有未编辑完成单元，请继续编辑', 'info');
                }

            }
        },{
            text : '保存',
            iconCls : 'icon-save',
            handler : function() {
            	if ($('#bgfrom').form('validate')) {
            	 var rows = $("#bg").datagrid("getRows"); //这段代码是获取当前页的所有行
          
            	
            	 //获取最后一行的属性
            	 var timeduan = $('#bg').datagrid('getEditor', {index:0,field:'timeduan'});
            	 var week = $('#bg').datagrid('getEditor', {index:0,field:'week'});
            	 var timeduan1=$(timeduan.target).datebox('getValue');
            	 var week1=$(week.target).datebox('getValue');
				 for(var i=0;i<rows.length;i++)
				 {
					if(i!=gindex){
						
					
					if(rows[i].timeduan==timeduan1&&rows[i].week==week1){
						 $.messager.alert('警告', '不能有重复数据!');
						 return;
					}
					}
				 }
                $('#bg').datagrid('endEdit', editBoxing);
                editBoxing = undefined;
            	}else{
            		 $.messager.alert('警告', '请填写必填数据再提交!');
            	}
            }
        },{
            text : '删除',
            iconCls : 'icon-remove',
            handler : function() {
            	
                var array = $('#bg').datagrid('getSelections');
                if (array.length >= 1) {
                	$('#bg').datagrid('deleteRow',0);

                } else {
                    $.messager.alert('警告', '请选择一条记录');
                }
            }
        },{
            text : '取消编辑',
            iconCls : 'icon-redo',
            handler : function() {
                if (editBoxing == 0) {
                    $('#bg').datagrid('deleteRow', editBoxing);
                    editBoxing = undefined;
                    $('#bg').datagrid('reload');
                } else {
                    editBoxing = undefined;
                    $('#bg').datagrid('reload');

                }

            }
        }
        ],
        onDblClickCell : function(index, field, value) {
            if (editBoxing == undefined) {
                editBoxing = index;
                $('#bg').datagrid('beginEdit', index);
                var ed = $(this).datagrid('getEditor', {
                    index : index,
                    field : field
                });
               
                $(ed.target).focus();
                
            } else {
                $('#bg').datagrid('endEdit', editBoxing);
                editBoxing = index;
                $('#bg').datagrid('beginEdit', index);
                gindex=$('#bg').datagrid('beginEdit', index);
                var ed = $(this).datagrid('getEditor', {
                    index : index,
                    field : field
                });
              
                $(ed.target).focus();
            }
        }
    });
}


function gradedate(){

			$('#studentsInTheClass1')
					.combobox(
							{
								url : '../stu/findClass',
								editable : false,
								valueField : 'gradenumber',
								textField : 'gradename',
								panelHeight : 'auto'
							});  

}


