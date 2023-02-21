<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@page import="com.Users.model.*"%>
<%@page import="com.article.model.*"%>
<%@page import="com.article_report.model.*"%>


<html>
<head>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>7Tour | 後台檢舉頁面</title>
<!-- 自己的bootstrap -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- datatable basic cdn  -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<link rel="stylesheet"
	href="//cdn.datatables.net/1.13.1/css/jquery.dataTables.min.css">
<script
	src="https://cdn.datatables.net/1.13.1/js/jquery.dataTables.min.js"></script>
<!-- 自己css -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/back-end/article/css/listallreport.css">
</head>

<body>
	<c:import url="/back-end/sidebar.jsp"></c:import>
	<div class="workplace h-100" style="top: 1px;]">
		<h4>文章檢舉管理</h4>
		<table id="table_id" class="display compact hover cell-border stripe"
			style="text-align: center;">
			<thead>
				<tr>
					<th class="showTd">文章檢舉編號</th>
					<th class="showTd">文章編號</th>
					<th class="showTd">文章標題</th>
					<th class="showTd">檢舉會員編號</th>
					<th class="showTd">處理狀態</th>
					<th class="showTd">檢舉事由</th>
					<th class="showTd">檢舉時間</th>
					<th class="showTd">檢舉處理時間</th>
					<th class="showTd"></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="articleReportVO" items="${allRepPost}">
					<tr>
						<td>${articleReportVO.artRpId}</td>
						<td>${articleReportVO.artId}</td>
						<td>${articleReportVO.articleVO.artTitle}</td>
						<td>${articleReportVO.userId}</td>
						<td class="rpStatus">${articleReportVO.rpStatus}</td>
						<td class="rpReason">${articleReportVO.rpReason}</td>
						<td><fmt:parseDate value="${articleReportVO.rpTime }"
								pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
							<fmt:formatDate pattern="yyyy-MM-dd HH:mm"
								value="${ parsedDateTime }" /></td>
						<td><fmt:parseDate value="${articleReportVO.rpDoneTime }"
								pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
							<fmt:formatDate pattern="yyyy-MM-dd HH:mm"
								value="${ parsedDateTime }" /></td>
						<td>
							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/articleReport"
								style="margin-bottom: 0px;">
								<input type="submit" value="修改狀態"> <input type="hidden"
									name="userId" value="${articleReportVO.userId}"> <input
									type="hidden" name="artRpId" value="${articleReportVO.artRpId}">
								<input type="hidden" name="action" value="allReportUpdate1">
							</FORM>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>


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

	<script>
		let rpStatus = document.getElementsByClassName("rpStatus");
		for (let i = 0; i < rpStatus.length; i++) {
			if (rpStatus[i].innerHTML === "0") {
				rpStatus[i].innerHTML = "未處理";
				rpStatus[i].style.color = "#5834f8";
			} else if (rpStatus[i].innerHTML === "1") {
				rpStatus[i].innerHTML = "檢舉下架";
				rpStatus[i].style.color = "#a9231c";
			} else if (rpStatus[i].innerHTML === "2") {
				rpStatus[i].innerHTML = "檢舉未成功";
				rpStatus[i].style.color = "#6a5655";
			}
		}
	</script>
	<script>
		let rpReason = document.getElementsByClassName("rpReason");
		for (let i = 0; i < rpReason.length; i++) {
			if (rpReason[i].innerHTML === "0") {
				rpReason[i].innerHTML = "惡意洗版、重複張貼";
			} else if (rpReason[i].innerHTML === "1") {
				rpReason[i].innerHTML = "包含未成年、裸露、色情內容";
			} else if (rpReason[i].innerHTML === "2") {
				rpReason[i].innerHTML = "仇恨言論";
			}else if (rpReason[i].innerHTML === "3") {
				rpReason[i].innerHTML = "廣告商業宣傳";
			}
		}
	</script>

	<!-- 隱藏datatable過多文字 -->
	<script>
		$(document).on('click', 'td', function() {
			$(this).toggleClass('showTd');
		});
	</script>
	
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