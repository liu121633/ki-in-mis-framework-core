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
			$("#addPositionSaveBtn").click(function(){
				$('#addPositionForm').form('submit',{
					url:'../position/addPosition',
					onSubmit: function(){   
						var isValid = $(this).form('validate');
						if (!isValid){
							return isValid;
						}
						var dept = $('[name=addPositionStatus]').val();
						if(dept==-1){
							alert('请选择状态!');
							return false;
						}
						$.messager.progress({
							title:"提示",
							msg:"正在修改",
							interval:"100"
						});
				    },
				    success: function(data){  
				    	$.messager.progress('close');
				        var data = eval('(' + data + ')');  // change the JSON string to javascript object    
				        if (data.success=="200"){
				        	$.messager.alert("系统提示",data.message,"info");
				            $("#addPosition").window({closed:true});
				            $("#position_table").datagrid('load',{});
				            //清空查询内容
				            $("[name=positionId]").val("");
							$("[name=positionName]").val("");
							$("[name=positionCreateName]").val("");
							$('#positionCreateDateStrat').datetimebox('clear');
							$('#positionCreateDateEnd').datetimebox('clear');
							$('#positionStatus').combobox('select',-1);
							/* $($("[name=positionCreateDateStrat]")[0]).datetimebox('setValue','');
							$($("[name=positionCreateDateEnd]")[0]).datetimebox('setValue','');
							$($("[name=positionStatus]")[0]).combobox('select',0);  */
				        }else if(data.success=="500"){
				        	 $.messager.alert("系统提示",data.message,"info");
				        }else{
				        	$.messager.alert("系统提示","服务器遇到未知错误，请稍后再试","error");
				        }
				    },onLoadError:function(){
				    	$.messager.alert("系统提示","服务器遇到未知错误，请稍后再试","error");
				    }
				});
			});