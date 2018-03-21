
		//修改ajax
		$(function(){
			//绑定修改职位的事件
			$('#updatePositionBtn').click(function(){
				$('#updatePositionForm').form({
					onLoadError:function(){
				    	$.messager.alert("系统提示","服务器遇到未知错误，请稍后再试","error");
				    } 
				});
				$('#updatePositionForm').form('submit',{
					url:'../position/updatePosition',    
				    onSubmit: function(){    
				    	var isValid = $(this).form('validate');
						if (!isValid){
							return isValid;
						}
						$.messager.progress({
							title:"提示",
							msg:"正在修改",
							interval:"100"
						});
				    },    
				    success:function(data){   
				    	$.messager.progress('close');
				    	 var data = eval('(' + data + ')');  // change the JSON string to javascript object    
				    	 if (data.success=="200"){
					            $("#position_table").datagrid('load',{});
					            $('#position_table').datagrid('clearChecked');
								$('#position_table').datagrid('clearSelections');
								$('#position_table').datagrid('unselectAll');
								$('#position_table').datagrid('uncheckAll');
					            //清空查询内容
					            $("[name=positionId]").val("");
					            console.info($("[name=positionId]").val());
								$("[name=positionName]").val("");
								$("[name=positionCreateName]").val("");
								$('#positionCreateDateStrat').datetimebox('clear');
								$('#positionCreateDateEnd').datetimebox('clear');
								$('#positionStatus').combobox('select',-1);
								/* $($("[name=positionCreateDateStrat]")[0]).datetimebox('setValue','');
								$($("[name=positionCreateDateEnd]")[0]).datetimebox('setValue','');
								$($("[name=positionStatus]")[0]).combobox('select',0);  */
								$.messager.alert("系统提示",data.message,"info");
								$("#addPosition").window('close');
				    	 }else if(data.success=="500"){
					        	 $.messager.alert("系统提示",data.message,"info");
					        }else{
					        	$.messager.alert("系统提示","服务器遇到未知错误，请稍后再试","error");
					        }       
				    }  
				});
			});
		})
	
		$.extend($.fn.validatebox.defaults.rules, {    
		    selectStatus: {    
		        validator: function(value, param){
		        	if(value=="--请选择--"){
		        		return false;
		        	}
		        	return true;
		        },    
		        message: '请选择状态!'   
		    }    
		}); 
		$("#position_back").click(function(){
			$("#addPosition").window({closed:true});
		});