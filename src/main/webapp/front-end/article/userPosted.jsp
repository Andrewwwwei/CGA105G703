<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.Users.model.*"%>
<%@page import="com.article.model.*"%>
<%@ page import="com.ColArt.model.*"%>

<%
UsersVO usersVO = (UsersVO) session.getAttribute("usersVO");
Integer user = usersVO.getUserId();
String userAcount = usersVO.getUserAccount();
ColArtService ColArtSvc = new ColArtService();
List<ColArtVO> list = ColArtSvc.getAll(user);
pageContext.setAttribute("list", list);
byte UserShopLevel = usersVO.getUserShopLevel();
String str = "";
if (UserShopLevel == 1) {
	str = "VIP";
}
if (UserShopLevel == 0) {
	str = "一般";
}
%>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>7Tour | 前台會員頁面</title>


<!-- 自己css -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front-end/article/css/user-posted.css">
<!-- bootStrap -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.2/font/bootstrap-icons.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"></script>
<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<!--俊賢的 -->
<script src="https://kit.fontawesome.com/616f19a0b0.js"></script>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front-end/collection/css/sidebar.css">
	
	<!-- datatable basic cdn  -->
<link rel="stylesheet"
	href="//cdn.datatables.net/1.13.1/css/jquery.dataTables.min.css">
<script
	src="https://cdn.datatables.net/1.13.1/js/jquery.dataTables.min.js"></script>

<style>
			* {
			    margin: 0;
			    padding: 0;
			}
			
			body {
			     overflow: scroll;
    			 font-family: "微軟正黑體";
			}
			
			ul {
			    list-style: none;
			}
			#wrapper {
				background-color:#DEDEDE;
			}
			#banner{
				position: absolute;
				left:100px;
				top:30px;
			}
			#hotelPic{
				position: absolute;
				left:300px;
				width:230px;
				height:135px;
			}
		    #position{  
			    position: absolute;
			    top:100px;
			    right:400px;
		    }   
		    #position>p{  
			    width:150px;
			    position: absolute;
			    top:30px;
			    right:450px;
		    }
			#position #vendor{
				float: middle;
				width:600px;
				height: 480px;
				background-color: white;
				border-radius: 10px;
				margin: 65px 0 0 0px;
			}
			#position #vendor #font{
				position: relative;
				margin: 10px 0 0 10px;
				padding: 10px 0 0 0;
			}
			#position #vendor #heartPic{
				position: absolute;
				bottom:-5px;
				width: 30px;
				margin: 0px 0 0 540px;
			}
</style>
</head>

<body>
	<c:import url="/front-end/header.jsp"></c:import>
	<div id="wrapper" style="left:0;">

             <div class="sidebar">
                <div class="headSculpture">
                   <img src="<%=request.getContextPath()%>/front-end/member/getUserPic.jsp?UserAccount='<%=usersVO.getUserAccount()%>'"  alt="">
                   <p><%=usersVO.getUserName()%></p>
                </div>
                <p id="vip">論壇等級：達人</p>
                <div class="option">
                   <ul>
                      <li><i class="bi bi-award"></i>
                         <p>購買等級：</p><p id="point"><%=str%></p>
                      </li>
                      <li><i class="bi bi-gem"></i>
                         <p>紅利積點</p><p id="point"><%=usersVO.getBonusPoints()%></p>
                      </li>
                      <li><i class="bi bi-people-fill"></i>
                         <p>揪團評分</p><p id="point"><%=usersVO.getAlltogetherScore()%></p>
                      </li>
                      <li><i class="bi bi-bag"></i>
                        <p>購買累計金額</p><p id="point"><%=usersVO.getPurchaseTotal()%></p>
                     </li>
                     <li><i class="bi bi-book"></i>
                      <p><a href="<%=request.getContextPath()%>/front-end/collection/myArt.jsp" class="text-decoration-none link-dark">我的文章</a></p>
                   </li>
                      <li><i class="bi bi-calendar2"></i>
                         <p><a href="<%=request.getContextPath()%>/front-end/collection/myTrip.jsp" class="text-decoration-none link-dark">我的行程</a></p>
                      </li>
                      <li><i class="bi bi-file-text"></i>
                         <p><a href="<%=request.getContextPath()%>/front-end/collection/collection.jsp" class="text-decoration-none link-dark">店家收藏</a></p>
                      </li>
                   </ul>
                </div>
             </div>
             <!-- 侧边栏按钮 -->
             <button></button>
             <!-- 内容区域 -->
            <div id="banner">
               <h2>我的收藏</h2>
             </div>
          </div>
	<!-- ---------------------main body區 --------------------->

	<div class="workplace">
		<h3>會員發文紀錄</h3>
		<table id="table_id" class="display compact hover cell-border stripe"
			style="text-align: center;">
			<thead>
				<tr>
					<th>文章主題</th>
					<th>文章標題</th>
					<th>觀看次數</th>
					<th>文章發表時間</th>
					<th>文章最後編輯時間</th>
					<th class="showTd"></th>
					<th class="showTd"></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="articleVO" items="${mypost}">
					<tr>
						<td class="artTypeId">${articleVO.artTypeId}</td>
						<td><a
								href="<%=request.getContextPath() %>/article?action=SelectOnePost_Display&artId=${articleVO.artId}&userId=${sessionScope.usersVO.getUserId()}"
								style="text-decoration: none; color: #5c49dc;">${articleVO.artTitle} </a></td>
						<td>${articleVO.artShowCount}</td>
						<td><fmt:parseDate value="${articleVO.artTime }"
								pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
							<fmt:formatDate pattern="yyyy-MM-dd HH:mm"
								value="${ parsedDateTime }" /></td>
						<td><fmt:parseDate value="${articleVO.artEditTime }"
								pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
							<fmt:formatDate pattern="yyyy-MM-dd HH:mm"
								value="${ parsedDateTime }" /></td>
						<td>
							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/article"
								style="margin-bottom: 0px;">
								<input type="submit" class="btn btn-outline-info" value="修改"> <input type="hidden"
									name="userId" value="${articleVO.userId}"> <input
									type="hidden" name="artId" value="${articleVO.artId}">
								<input type="hidden" name="action" value="UserPost1_Update">
							</FORM>
						</td>
						<td>
							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/article"
								style="margin-bottom: 0px;">
								<input type="submit" class="btn btn-outline-danger" value="刪除"> <input type="hidden"
									name="userId" value="${articleVO.userId}"> <input
									type="hidden" name="artId" value="${articleVO.artId}">
								<input type="hidden" name="action" value="UserPost_delete">
							</FORM>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>


	<!-- 俊賢的 -->
	<script
		src="<%=request.getContextPath()%>/front-end/member/js/sidebar.js"></script>
	<script>
//         <!-- 控制delete 表單發送-->
         document.getElementById("heartPic").addEventListener("click", function () {
        	document.getElementById("Delete").submit();
        	});
//         <!-- 控制登出發送  -->
   		loginout.addEventListener("click", function () {
        	document.login_out.submit();
        });
//    		<!-- 控制userInfo 發送   -->
        document.getElementById("info").addEventListener("click", function () {
        	window.open("<%=request.getContextPath()%> /front-end/member/userInfo.jsp ", "userInfo", config = "height=540,width=445, top = 100, left = 700");
						});
	<%UsersVO usersVO1 = (UsersVO) session.getAttribute("usersVO");
if (usersVO1 != null) {
	out.println("document.getElementById('userPic').src=" + "'" + request.getContextPath()
			+ "/front-end/member/getUserPic.jsp?UserAccount=" + "\"" + usersVO1.getUserAccount() + "\"" + "'" + ";");
} ;%>
	</script>
	<!-- 愷威的 -->
	<script>
		var artTypeIds = document.getElementsByClassName("artTypeId");
		for (let i = 0; i < artTypeIds.length; i++) {
			if (artTypeIds[i].innerHTML === "1") {
				artTypeIds[i].innerHTML = "遊記";
				artTypeIds[i].style.color = "#68c6ea";
			} else if (artTypeIds[i].innerHTML === "2") {
				artTypeIds[i].innerHTML = "美食";
				artTypeIds[i].style.color = "#eac368";
			} else if (artTypeIds[i].innerHTML === "3") {
				artTypeIds[i].innerHTML = "住宿";
				artTypeIds[i].style.color = "#45e9b8";
			} else if (artTypeIds[i].innerHTML === "4") {
				artTypeIds[i].innerHTML = "景點";
				artTypeIds[i].style.color = "#9898f8";
			}
		}
	</script>

	<!-- datatable js -->
	<script>
		$(document)
				.ready(
						function() {
							$('#table_id')
									.DataTable(
											{
												//中文化
												"language" : {
													"processing" : "處理中...",
													"loadingRecords" : "載入中...",
													"paginate" : {
														"first" : "第一頁",
														"previous" : "上一頁",
														"next" : "下一頁",
														"last" : "最後一頁"
													},
													"emptyTable" : "目前沒有資料",
													"datetime" : {
														"previous" : "上一頁",
														"next" : "下一頁",
														"hours" : "時",
														"minutes" : "分",
														"seconds" : "秒",
														"amPm" : [ "上午", "下午" ],
														"unknown" : "未知",
														"weekdays" : [ "週日",
																"週一", "週二",
																"週三", "週四",
																"週五", "週六" ],
														"months" : [ "一月",
																"二月", "三月",
																"四月", "五月",
																"六月", "七月",
																"八月", "九月",
																"十月", "十一月",
																"十二月" ]
													},
													"searchBuilder" : {
														"add" : "新增條件",
														"condition" : "條件",
														"deleteTitle" : "刪除過濾條件",
														"button" : {
															"_" : "複合查詢 (%d)",
															"0" : "複合查詢"
														},
														"clearAll" : "清空",
														"conditions" : {
															"array" : {
																"contains" : "含有",
																"equals" : "等於",
																"empty" : "空值",
																"not" : "不等於",
																"notEmpty" : "非空值",
																"without" : "不含"
															},
															"date" : {
																"after" : "大於",
																"before" : "小於",
																"between" : "在其中",
																"empty" : "為空",
																"equals" : "等於",
																"not" : "不為",
																"notBetween" : "不在其中",
																"notEmpty" : "不為空"
															},
															"number" : {
																"between" : "在其中",
																"empty" : "為空",
																"equals" : "等於",
																"gt" : "大於",
																"gte" : "大於等於",
																"lt" : "小於",
																"lte" : "小於等於",
																"not" : "不為",
																"notBetween" : "不在其中",
																"notEmpty" : "不為空"
															},
															"string" : {
																"contains" : "含有",
																"empty" : "為空",
																"endsWith" : "字尾為",
																"equals" : "等於",
																"not" : "不為",
																"notEmpty" : "不為空",
																"startsWith" : "字首為",
																"notContains" : "不含",
																"notStartsWith" : "開頭不是",
																"notEndsWith" : "結尾不是"
															}
														},
														"data" : "欄位",
														"leftTitle" : "群組條件",
														"logicAnd" : "且",
														"logicOr" : "或",
														"rightTitle" : "取消群組",
														"title" : {
															"_" : "複合查詢 (%d)",
															"0" : "複合查詢"
														},
														"value" : "內容"
													},
													"editor" : {
														"close" : "關閉",
														"create" : {
															"button" : "新增",
															"title" : "新增資料",
															"submit" : "送出新增"
														},
														"remove" : {
															"button" : "刪除",
															"title" : "刪除資料",
															"submit" : "送出刪除",
															"confirm" : {
																"_" : "您確定要刪除您所選取的 %d 筆資料嗎？",
																"1" : "您確定要刪除您所選取的 1 筆資料嗎？"
															}
														},
														"error" : {
															"system" : "系統發生錯誤(更多資訊)"
														},
														"edit" : {
															"button" : "修改",
															"title" : "修改資料",
															"submit" : "送出修改"
														},
														"multi" : {
															"title" : "多重值",
															"info" : "您所選擇的多筆資料中，此欄位包含了不同的值。若您想要將它們都改為同一個值，可以在此輸入，要不然它們會保留各自原本的值。",
															"restore" : "復原",
															"noMulti" : "此輸入欄需單獨輸入，不容許多筆資料一起修改"
														}
													},
													"autoFill" : {
														"cancel" : "取消"
													},
													"buttons" : {
														"copySuccess" : {
															"_" : "複製了 %d 筆資料",
															"1" : "複製了 1 筆資料"
														},
														"copyTitle" : "已經複製到剪貼簿",
														"excel" : "Excel",
														"pdf" : "PDF",
														"print" : "列印",
														"copy" : "複製",
														"colvis" : "欄位顯示",
														"colvisRestore" : "重置欄位顯示",
														"csv" : "CSV",
														"pageLength" : {
															"-1" : "顯示全部",
															"_" : "顯示 %d 筆"
														},
														"createState" : "建立狀態",
														"removeAllStates" : "移除所有狀態",
														"removeState" : "移除",
														"renameState" : "重新命名",
														"savedStates" : "儲存狀態",
														"stateRestore" : "狀態 %d",
														"updateState" : "更新"
													},
													"searchPanes" : {
														"collapse" : {
															"_" : "搜尋面版 (%d)",
															"0" : "搜尋面版"
														},
														"emptyPanes" : "沒搜尋面版",
														"loadMessage" : "載入搜尋面版中...",
														"clearMessage" : "清空",
														"count" : "{total}",
														"countFiltered" : "{shown} ({total})",
														"title" : "過濾條件 - %d",
														"showMessage" : "顯示全部",
														"collapseMessage" : "摺疊全部"
													},
													"stateRestore" : {
														"emptyError" : "名稱不能空白。",
														"creationModal" : {
															"button" : "建立",
															"columns" : {
																"search" : "欄位搜尋",
																"visible" : "欄位顯示"
															},
															"name" : "名稱：",
															"order" : "排序",
															"paging" : "分頁",
															"scroller" : "卷軸位置",
															"search" : "搜尋",
															"searchBuilder" : "複合查詢",
															"select" : "選擇",
															"title" : "建立新狀態",
															"toggleLabel" : "包含："
														},
														"duplicateError" : "此狀態名稱已經存在。",
														"emptyStates" : "名稱不可空白。",
														"removeConfirm" : "確定要移除 %s 嗎？",
														"removeError" : "移除狀態失敗。",
														"removeJoiner" : "和",
														"removeSubmit" : "移除",
														"removeTitle" : "移除狀態",
														"renameButton" : "重新命名",
														"renameLabel" : "%s 的新名稱：",
														"renameTitle" : "重新命名狀態"
													},
													"select" : {
														"columns" : {
															"_" : "選擇了 %d 欄資料",
															"1" : "選擇了 1 欄資料"
														},
														"rows" : {
															"1" : "選擇了 1 筆資料",
															"_" : "選擇了 %d 筆資料"
														},
														"cells" : {
															"1" : "選擇了 1 格資料",
															"_" : "選擇了 %d 格資料"
														}
													},
													"zeroRecords" : "沒有符合的資料",
													"aria" : {
														"sortAscending" : "：升冪排列",
														"sortDescending" : "：降冪排列"
													},
													"info" : "顯示第 _START_ 至 _END_ 筆結果，共 _TOTAL_ 筆",
													"infoEmpty" : "顯示第 0 至 0 筆結果，共 0 筆",
													"infoFiltered" : "(從 _MAX_ 筆結果中過濾)",
													"infoThousands" : ",",
													"lengthMenu" : "顯示 _MENU_ 筆結果",
													"search" : "查詢：",
													"searchPlaceholder" : "請輸入關鍵字",
													"thousands" : ","
												}

											});
						});
	</script>

	<!-- 隱藏datatable過多文字 -->
	<script>
		$(document).on('click', 'td', function() {
			$(this).toggleClass('showTd');
		});
	</script>
	<!-- 置中datatable -->
	<script>
		$(document).ready(function() {
			var table = $('#table_id').DataTable();
			var thead = table.table().header();
			$('th', thead).css({
				'text-align' : 'center'
			});
		});
	</script>


</body>
</html>