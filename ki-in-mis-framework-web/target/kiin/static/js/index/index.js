$(function() {
	InitLeftMenu();

	$("#smarty dl").click(function() {
		if (!$(this).attr("url")) {
			return
		}
		
		addTab($(this).find("dd").text(), $(this).attr("url"));
	})

})
//显示个人信息的窗口
function showPerson(){
	$("#showPerson").dialog({
		title:"显示个人信息",
		width:600,
		height:300,
		closed:false,
		cache: false,
		href: '../kiin/person/toShowPerson',
		modal:true,
		buttons:[{
			text:"确定",
			iconCls:"icon-ok",
			handler:function(){
				$("#showPerson").dialog('close');
			}
		}]
	});
	$("#showPerson").dialog('open');
}
//显示修改密码的窗口
function updatePwd(){
	$('#updatePassWord').dialog({
	    title: '修改密码',
	    width: 320,
	    height: 250,
	    closed: false,
	    cache: false,
	    href: '../kiin/toUpdatePwd',
	    modal: true,
	    buttons:[{
	    	text:"保存",
	    	iconCls:"icon-ok",
	    	handler:function(){
	    		if ($("#newPass").val() == "") {
					$.messager.alert('警告','密码不能为空'); 
					return false;
				}
	    		if ($("#newPass").val().length<6) {
					$.messager.alert("提示","密码长度最少要6位");
					return false;
				} 
	    		if ($("#newPass").val().length>8) {
					$.messager.alert("提示","密码长度最多只能是8位");
					return false;
				}
				if ($("#newPass").val() != $("#Pass").val()) {
					$.messager.alert('警告','两次密码输入不一致，请重新输入'); 
					return false;
				}
				if ($("#userPassWord").val() != $("#oldPass").val()) {
					$.messager.alert('警告','原密码输入错误，请重新输入'); 
					return false;
				}
				$.ajax({
					url:'../kiin/user/updatePassword', 
					data:'newPass='+$("#newPass").val(),
					type:'post',
					dataType:'json',
					beforeSend:function(){
						$.messager.progress({
							title:"提示",
							msg:"正在修改",
							interval:"300"
						});
					},
					success:function(data){
						$.messager.progress('close');
				    	if (data.code == "200") {
							$.messager.alert({
								title:"系统提示",
								msg:"密码修改成功",
								icon:"info"
							});
							$("#updatePassWord").dialog('close');
							$.messager.alert("系统提示","请重新登录!","info");
							location.href="../kiin/breakLogin";
							
						}else {
							$.messager.alert("系统提示","服务器遇到未知错误，请稍后再试","error");
						}
				    }
				});
	    	}
	    },{
	    	text:"取消",
	    	iconCls:"icon-no",
	    	handler:function(){
	    		$('#updatePassWord').dialog('close');
	    	}
	    }]
	});
	$('#updatePassWord').dialog('open');
}
//选中左边的列，根据条件判断，如果符合条件，就进相应的判断
function selectName(t){
	if (t == '2') {//选中的id值等于2时，弹出修改密码的窗口
		updatePwd();
	}else if(t == '1'){//选中的id值等于1时，弹出显示个人信息的窗口
		showPerson();
	}
}
//初始化左侧
function InitLeftMenu() {
	$("#nav").accordion({
		animate: false
	});

	$.each(_menus.menus, function(i, n) {
		var menulist = '';
		menulist += '<ul>';
		$.each(n.menus, function(j, o) {
			if (o.id == '1' || o.id == '2') {
				menulist += '<li style = "margin-top:10px;" class = "person"><div><span><span  class="icon ' + o.icon + '" >&nbsp;</span><span class="nav" onclick = "selectName('+o.id+')">' + o.menuname + '</span></span></div></li> ';
			} else {
				menulist += '<li><div><a ref="' + o.menuid + '" href="#" rel="' + o.url + '" ><span  class="icon ' + o.icon + '" >&nbsp;</span><span class="nav">' + o.menuname + '</span></a></div></li> ';
			}
			
		})
		menulist += '</ul>';

		$('#nav').accordion('add', {
			title: n.menuname,
			content: menulist,
			iconCls: '' + n.icon
		});

	});

	$('.easyui-accordion li a').click(function() {
		var tabTitle = $(this).children('.nav').text();
		var url = $(this).attr("rel");
		addTab(tabTitle, url);
		$('.easyui-accordion li div').removeClass("selected");
		$(this).parent().addClass("selected");
	}).hover(function() {
		$(this).parent().addClass("hover");
	}, function() {
		$(this).parent().removeClass("hover");
	});

	//选中第一个
	var panels = $('#nav').accordion('panels');
	var t = panels[0].panel('options').title;
	$('#nav').accordion('select', t);
}
//获取左侧导航的图标
function getIcon(menuid) {
	var icon = 'icon ';
	$.each(_menus.menus, function(i, n) {
		$.each(n.menus, function(j, o) {
			if(o.menuid == menuid) {
				icon += o.icon;
			}
		})
	})

	return icon;
}

function addTab(subtitle, url) {
	if(!$('#tabs').tabs('exists', subtitle)) {
		$('#tabs').tabs('add', {
			title: subtitle,
			content: createFrame(url),
			closable: true,
		});
	} else {
		$('#tabs').tabs('select', subtitle);
		$('#mm-tabupdate').click();
	}
	tabClose();
}

function createFrame(url) {
	var s = '<iframe scrolling="auto" frameborder="0"  src="' + url + '" style="width:100%;height:100%;"></iframe>';
	return s;
}

function tabClose() {
	/*双击关闭TAB选项卡*/
	$(".tabs-inner").dblclick(function() {
		var subtitle = $(this).children(".tabs-closable").text();
		$('#tabs').tabs('close', subtitle);
	})
}