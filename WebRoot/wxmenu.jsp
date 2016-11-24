<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>菜单管理</title>
    
  
   <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css"></link>
   <script type="text/javascript" src="js/jquery-1.10.2.js"></script>
   <script type="text/javascript" src="js/bootstrap.min.js"></script>
   </head>
  <body>
  <div style="width: 500px;height:500px; border-color: green; border-style: solid;float: left;">
  		<div class="row" style="margin-top: 70%;" align="center">
  		<!-- menu  begin -->
  		<c:forEach items="${menu.button}" var="m">
	  		<!-- 一个菜单================================================ -->
			<div class="btn-group dropup">
			   <!-- 主菜单名 -->
			      <button type="button" id="${m.name}_show" style="width: 120px;" class="click_menu_1 btn btn-success dropdown-toggle" data-toggle="dropdown">
			      	 ${m.name}
			       		   <c:if test="${!empty m.sub_button}">
						      	<!-- 是否有子集按钮 -->
						       <span class="caret"></span>
					       </c:if>
			      </button>
			      
			      <!--////// ==================这是数据配置=========================////////// -->
			      <button id="${m.name}" 
			       name="${m.name}" 
			       isTrue="${empty m.sub_button}" 
			       url="${m.url}" 
			       style="display: none;"></button>
			      
			      <!-- 子菜单  ::如果不为空。就显示 -->
			      <c:if test="${!empty m.sub_button}">
				      <ul class="dropdown-menu" role="menu">
				      	<c:forEach items="${m.sub_button}" var="m_sub">
				 	 			 <li><a href="javascript:;" class="click_menu_1"  id="${m_sub.name}_show">${m_sub.name}</a>
				 	 			 	 <button id="${m_sub.name}" 
								       name="${m_sub.name}" 
								       isTrue="true"
								       url="${m_sub.url}" 
								       style="display: none;"></button>
				 	 			 </li>
				 	 				 <!-- 路径：${m_sub.url} -->
				 	 	</c:forEach>
			 	 	  </ul>
			      </c:if>
			      
			   </div>
			  </c:forEach> 
			  <!-- menu end！ -->
		</div>	
   </div>
   <div style="width: 500px;height:500px; border-color: green;  border-style: solid; float: left; margin-left:  50px;">
		<div id="showDiv" style="display: none;">
			<!-- 功能区开始 -->
			<br/><br/><br/><br/>
				 <div class="form-group">
				      <label for="lastname"  class="col-sm-3 control-label">功能名称</label>
				      <div class="col-sm-9">
				         <input type="text" class="form-control" id="gnName_1" 
				            placeholder="">
				      </div>
				   </div>
				   <br/><br/><br/><br/>
				    <div id="url_div" class="form-group">
				      <label for="lastname" class="col-sm-3 control-label">链接地址</label>
				      <div class="col-sm-9">
				         <input type="text" class="form-control" id="gnUrl_1" 
				            placeholder="">
				      </div>
				   </div>
				   
			<div align="center" style="margin-top: 200px;">
				<!-- 保存操作 -->
				<button type="button" okThereId="" isCh="false" class="btn btn-info okEd" style="font-family: sans-serif">确认编辑</button>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<!-- 取消操作 -->
				<button type="button" id="closeEdu" class="btn btn-success">取消此编辑</button>
			</div>
		</div>
  </div>
  
 <script type="text/javascript">
 		$(function(){
 			//点击确认编辑的时候触发
 			$(".btn.btn-info.okEd").click(function(){
 				//修改显示区域数据
 				var val=$(this).attr("okThereId");
 				//是否有子集
 				var isCh=$(this).attr("isCh");
 				//更改显示的内容
 				if(isCh=="true"){
 					$("#"+val+"_show").html($("#gnName_1").val()+" <span class=\"caret\"></span>");
 				}
 				else{
 					$("#"+val+"_show").html($("#gnName_1").val());
 				}
 				//更改实际内容
 				$("#"+val).attr("name",$("#gnName_1").val());//名字
 				
 				$("#"+val).attr("url",$("#gnUrl_1").val());//路径
 				$("#closeEdu").click();
 			});
 			//获取菜单内容 元素
 			$(".click_menu_1").click(function(){
 				var $sib=$(this).siblings("button");
 				//判断是否有子菜单 有就执行动作
 				if($sib.attr("isTrue")=="true")
 				{
 					$("#gnName_1").val($sib.attr("name"));
 					$("#gnUrl_1").val($sib.attr("url"));
 					$(".btn.btn-info.okEd").attr("okThereId",$sib.attr("id"));
 					$(".btn.btn-info.okEd").attr("isCh","false");
 					$("#url_div").show();//显示路径编写盒子
 					$("#showDiv").show(200);//显示编辑盒子
 				}
 				else{
 					//主菜单，有子菜单
 					$("#gnName_1").val($sib.attr("name"));
 					$(".btn.btn-info.okEd").attr("okThereId",$sib.attr("id"));
 					$(".btn.btn-info.okEd").attr("isCh","true");
 					$("#url_div").hide();
 					$("#showDiv").show(200);
 				}
 				
 			});
 			//取消单个元素的编辑
 			$("#closeEdu").click(function(){
 				$("#showDiv").hide(200);
 				$("#gnName_1").val("");
 				$("#gnUrl_1").val("");
 			});
 		});
 </script>
   <div style="width: 1050px;;height:100px; border-color: green; border-style: solid; float: left; padding-top: 20px;" align="center">
  			 <!-- 保存操作 -->
			<button type="button" class="btn btn-info" style="height: 50px;width: 200px;">保存菜单</button>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<!-- 取消操作 -->
			<button type="button" onclick="window.location.href='weixin/menu.action'" class="btn btn-success" style="height: 50px;width: 200px;">取消操作</button>
  </div>

  </body>
</html>
