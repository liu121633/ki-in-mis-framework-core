<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="http://localhost:8080/kiin/static/js/importExcel.js"
	type="text/javascript" charset="utf-8"></script>
  <form id="uploadExcel"  method="post" enctype="multipart/form-data">    
            选择文件：　<input id = "excel" name = "excel" class="easyui-filebox" style="width:200px" data-options="prompt:'请选择文件...'">    
        </form>    
        <div style="text-align: center; padding: 5px 0;">  
            <a id = "booten" href="javascript:void(0)" class="easyui-linkbutton"  
                onclick="uploadExcel()" style="width: 80px" id="tt">导入</a>  
        </div>   