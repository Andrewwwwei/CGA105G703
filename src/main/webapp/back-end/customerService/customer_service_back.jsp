<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en" >
<head>
    <meta charset="UTF-8">
    <title>客服聊天室-後台</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/customerService/css/style.css">
    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    <!-- font-awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css">
    <!-- bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<style>
		.send-box button:hover{
			filter: drop-shadow(2px 2px 2px #060C3C);
		}
		a:hover{
			border: 1px solid #CCCCCC;
			border-radius: 0.25rem;
		}
		.message-area{
			margin-left: 350px;
		}
	</style>
</head>
<body onload="connect();" onunload="disconnect();">
<c:import url="/back-end/sidebar.jsp"></c:import>
    <!-- char-area -->
    <section class="message-area">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <div class="chat-area border rounded-3" style="border: #F1F1F1;">
                        <!-- chatlist -->
                        <div class="chatlist">
                            <div class="modal-dialog-scrollable">
                                <div class="modal-content">
                                    <div class="chat-header">
                                        <ul class="nav nav-tabs" id="myTab" role="tablist">
                                            <li class="nav-item" role="presentation">
                                                <button class="nav-link active" id="Open-tab" data-bs-toggle="tab" data-bs-target="#Open" type="button" role="tab">上線</button>
                                            </li>
                                            <li class="nav-item" role="presentation">
                                                <button class="nav-link" id="Closed-tab" data-bs-toggle="tab" data-bs-target="#Closed" type="button" role="tab">離線</button>
                                            </li>
                                        </ul>
                                    </div>

                                    <div class="modal-body">
                                        <!-- users -->
                                        <div class="chat-lists">
                                            <div class="tab-content" id="myTabContent">
                                                <!-- online-users -->
                                                <div class="tab-pane fade show active" id="Open" role="tabpanel" aria-labelledby="Open-tab">
                                                    <div class="chat-list" id="online-list"></div>
                                                </div>

                                                <!-- offline-users -->
                                                <div class="tab-pane fade" id="Closed" role="tabpanel" aria-labelledby="Closed-tab">
                                                    <div class="chat-list" id="offline-list"></div>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- users -->
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- chatlist -->



                        <!-- chatbox -->
                        <div class="chatbox">
                            <div class="modal-dialog-scrollable">
                                <div class="modal-content" id="chatbox" style="display: none;">
                                    <div class="msg-head">
                                        <div class="row">
                                            <div class="col-8">
                                                <div class="d-flex align-items-center">
                                                    <div class="flex-shrink-0" id="userImg"></div>
                                                    <div class="flex-grow-1 ms-3">
                                                        <h3 id="userName">會員姓名</h3>
                                                        <div id="userId" style="display: none;"></div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-4"></div>
                                        </div>
                                    </div>


                                    <div class="modal-body" id="modal-body">
                                        <div class="msg-body" id="msg-body"></div>
                                    </div>

                                    <div class="send-box">
                                        <form action="" autocomplete="off">
                                            <input type="text" class="form-control" id="btn-input">

                                            <button type="button" onclick="sendMessage();"><i class="fa fa-paper-plane"></i> 送出</button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- chatbox -->


                </div>
            </div>
        </div>
        </div>
    </section>
    <!-- char-area -->
    <script>
        const ServerPoint = "/CustomerService/employee";
        const host = window.location.host;  //localhost:8081
        const path = window.location.pathname; // '/CGA105G7'
        const webCtx = path.substring(0, path.indexOf('/', 1));
        const endPointURL = "ws://" + host + webCtx + ServerPoint;

        const msgContainer = document.querySelector('#msg-body');
        const msgBody = document.querySelector('#modal-body');
        const self = 'employee';
        let webSocket;


        function connect() {
            // create a websocket
            webSocket = new WebSocket(endPointURL);
            webSocket.onopen = function(event) {
                console.log("Connect Success!");
                trigderRefreshUserList();
            };

            webSocket.onmessage = function(event) {
                let jsonObj = JSON.parse(event.data);
                if ("open" === jsonObj.type) {
    				refreshUserList(jsonObj);
    			}else if ("history" === jsonObj.type) {
    				document.querySelector('#userName').innerText = jsonObj.userName;
    				document.querySelector('#userImg').innerHTML = '<img style="width: 50px;" src="<%=request.getContextPath()%>/usersServlet?action=getUserPic&userId=' + JSON.parse(jsonObj.msgObj).receiver + '">';
    				document.querySelector('#userId').innerText = 'u' + JSON.parse(jsonObj.msgObj).receiver;
                    msgContainer.innerHTML = '';
                    // 這行的jsonObj.message是從redis撈出跟客服的歷史訊息，再parse成JSON格式處理
                    let messages = JSON.parse(JSON.parse(jsonObj.msgObj).message);
                    let ul = document.createElement('ul');
                    ul.id = 'msgBodyContent';
                    msgContainer.appendChild(ul);
                    for (let i = 0; i < messages.length; i++) {
                        let historyData = JSON.parse(messages[i]);
                        let showMsg = '<p>' + historyData.message + '</p>'
                                    + '<span class="time">' + historyData.time + '</span>';
                        let li = document.createElement('li');
                        // 根據發送者是自己還是對方來給予不同的class名, 以達到訊息左右區分
                        historyData.sender === self ? li.className += 'repaly' : li.className += 'sender';
                        li.innerHTML = showMsg;
                        ul.appendChild(li);
                    }
                    document.querySelector('#chatbox').style.display = "flex";
                    msgBody.scrollTop = msgBody.scrollHeight;
                    
                }else if ("chat" === jsonObj.type) {
                		let userIds = document.querySelector('#userId').innerText.substring(1);
                		if(userIds === jsonObj.sender || userIds === jsonObj.receiver){
	                        let ul = document.querySelector('#msgBodyContent');
	                        let li = document.createElement('li');
	                        let showMsg = '<p>' + jsonObj.message + '</p>'
	                                    + '<span class="time">' + jsonObj.time + '</span>';
	                        // 根據發送者是自己還是對方來給予不同的class名
	                        jsonObj.sender === self ? li.className += 'repaly' : li.className += 'sender';
	                        li.innerHTML = showMsg;
	                        ul.appendChild(li);
	                        msgBody.scrollTop = msgBody.scrollHeight;
                		}
                		trigderRefreshUserList();
                		if(userIds == jsonObj.sender){
                			let jsonObj = {
                                "type" : "read",
                                "sender" : self,
                                "receiver" : userIds
                            };
                            webSocket.send(JSON.stringify(jsonObj));
                		}
                }else if("onAndOff" === jsonObj.type){
                	trigderRefreshUserList();
                }
            };
        };

        function sendMessage() {
            let inputMessage = document.getElementById("btn-input");
            let message = inputMessage.value.trim();

            if (message === "") {
                alert("您未輸入訊息");
                inputMessage.focus();
            } else {
        		let userIds = document.querySelector('#userId').innerText.substring(1);
                let now = new Date();
                let nowStr = now.getFullYear() + '-' + (now.getMonth() + 1) + '-'
                            + now.getDate() + ' ' + now.getHours() + ':' + now.getMinutes();
                let jsonObj = {
                    "type" : "chat",
                    "sender" : self,
                    "receiver" : userIds,
                    "message" : message,
                    "time" : nowStr
                };
                webSocket.send(JSON.stringify(jsonObj));
                inputMessage.value = "";
                inputMessage.focus();
            };
        };
		
        function trigderRefreshUserList() {
        	let jsonObj = {
					"type" : "userlist",
					"sender" : self,
				};
			webSocket.send(JSON.stringify(jsonObj));
        }
        
        // 會員上線或離線就更新列表
    	function refreshUserList(jsonObj) {
    		let userList = jsonObj.userList;
    		let onLineList = document.getElementById("online-list");
    		let offLineList = document.getElementById("offline-list");
    		onLineList.innerHTML = '';
    		offLineList.innerHTML = '';
    		let userIds = document.querySelector('#userId').innerText.substring(1);
    		for (let user of userList) {
    			let userRow = '';
    			if(user.status === 'unread' && userIds != user.userId){
    				userRow = '<a href="#" class="d-flex align-items-center a" id="user' + user.userId + '" onclick="showUserChatBox();">'
							+ '<div class="flex-shrink-0">'
							+ '<img class="img-fluid ps-1" src="<%=request.getContextPath()%>/back-end/customerService/images/user.png">'
							+ '<img src="<%=request.getContextPath()%>/back-end/customerService/images/alert.png" style="position: absolute; bottom: 0px; left: 34px; width: 15px;">'
							+ '</div><div class="flex-grow-1 ms-3">'
							+ '<h3>' + user.userName + '</h3>'
							+ '<p>' + JSON.parse(user.lastmsg).message + '</p></div></a>';
    			}else{
	    			userRow = '<a href="#" class="d-flex align-items-center a" id="user' + user.userId + '" onclick="showUserChatBox();">'
    						+ '<div class="flex-shrink-0">'
    						+ '<img class="img-fluid ps-1" src="<%=request.getContextPath()%>/back-end/customerService/images/user.png">'
    						+ '</div><div class="flex-grow-1 ms-3">'
    						+ '<h3>' + user.userName + '</h3>'
    						+ '<p>' + JSON.parse(user.lastmsg).message + '</p></div></a>';
    			}
    			if(user.onoff === 'online'){
    				onLineList.innerHTML += userRow;
    			}else{
    				offLineList.innerHTML += userRow;
    			}
    		}
    	}
    	
    	function showUserChatBox() {
	    		let atags = document.querySelectorAll(".a");
	    		for (var i = 0; i < atags.length; i++) {
	    			atags[i].style.backgroundColor = "#FFF";
	    		}
    			let userIds = event.target.closest("a").id.substring(4);
    			event.target.closest("a").style.backgroundColor = '#D0DEFB';
    			event.target.closest("a").style.borderRadius = '0.25rem';
    			let img = event.target.closest("a").getElementsByTagName("img");
    			let toRead = '';
    			if(img.length > 1){
    				img[1].remove();
    				toRead = 'toRead';
    			}
    			let jsonObj = {
    					"type" : "history",
    					"sender" : self,
    					"receiver" : userIds,
    					"message" : toRead
    				};
    			webSocket.send(JSON.stringify(jsonObj));
    	}
        
        function disconnect() {
		    webSocket.close();
	    }
    </script>
</body>
</html>
