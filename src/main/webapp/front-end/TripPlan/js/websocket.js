
//Websocket
	var tripPoint = `/tripChat.do/${tripId}`;
	var tripHost = window.location.host;
	var wsPath = window.location.pathname;
	var tripWebCtx = wsPath.substring(0, wsPath.indexOf('/', 1));
	var tripEndPointURL = "ws://" + tripHost + tripWebCtx + tripPoint;

	var statusOutput = document.getElementById("statusOutput");
	var messagesArea = document.getElementById("messagesArea");
	var webSocket;
$(function(){
	ws_connect();
});

function ws_connect() {
		// create a websocket
		webSocket = new WebSocket(tripEndPointURL);

		webSocket.onopen = function(event) {
			console.log("Connect Success!");
		};

		webSocket.onmessage = function(msg) {
		let jsonStr = JSON.parse(msg.data);
			if(jsonStr.length === undefined){
				updateDialog(jsonStr)
			}
		
			for(let count =0; count<jsonStr.length; count++){
				let jsonObj = JSON.parse(jsonStr[count]);
				updateDialog(jsonObj);
			};
		}
		webSocket.onclose = function(event) {
			console.log("Disconnected!");
		};
	}

//送出訊息
function sendMsg(){
	let msg = $('#msg-input');
	if(msg.val() === ""){
		swal ( "Oops" ,  "請輸入文字!" ,  "error" );
		return;
	}
	console.log("you is send message ? " + userId);
	console.log("YOUR MSG" + msg.val());
	let currentTime = new Date();
	let year = currentTime.getFullYear();
	let mon = currentTime.getMonth()+1;
	let date = currentTime.getDate();
	let hour = currentTime.getHours();
	let min = currentTime.getMinutes();
	let sec = currentTime.getSeconds();
	let jsonObj = {
		userId: userId,
		tripId: tripId,
		message: msg.val(),
		sendTime:[year, mon, date, hour, min, sec]
	};
	
	console.log(jsonObj);
	webSocket.send(JSON.stringify(jsonObj));
	msg.val("");
	msg.focus();
}

let date=0;
function updateDialog(json){
	let msgContent = $('#msg-content');
	let htmlStr = "";
	if(date < json.sendTime[2]){
		 date = json.sendTime[2];
		 let dateHtml = `<div class="text-center"><p class="d-inline p-2 bg-cblue" style="border-radius: 30px;">${json.sendTime[1]}/${date}日</p></div>`;
		 msgContent.append(dateHtml);
	}
		let sendHour = json.sendTime[3] < 10?"0"+json.sendTime[3] : json.sendTime[3];
		let sendMin = json.sendTime[4] < 10?"0"+json.sendTime[4] : json.sendTime[4];
	if(json.userId === userId){
		htmlStr = `
		<div class="m-2 text-end d-flex flex-row-reverse">
            <span class="trip-msg text-start">${json.message}</span>
            <p class="fs-6 mb-0 mt-auto">${sendHour}:${sendMin}</p> 
         </div>`;			
		}else{
			htmlStr = `
				<div class="m-2 text-end d-flex">
		    	<img src="${tripPath}/front-end/TripPlan/tripMbr.do?action=getUserPic&UserID=${json.userId}"  class="user-pic d-inline align-top">
		        <span class="trip-msg text-start">${json.message}</span>
		        <p class="fs-6 mb-0 mt-auto">${sendHour}:${sendMin}</p> 
		        </div>`;
		}
   	msgContent.append(htmlStr);
   	let scrollHeight = $('#msg-content').prop("scrollHeight");
   	msgContent.scrollTop(scrollHeight,200);
}
          
