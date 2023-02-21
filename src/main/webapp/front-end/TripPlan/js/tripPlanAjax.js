$(document).ready(()=>{
	getTripDetailAjax(startDate);
	getCusLoc();
	console.log(startDate);
});


function addTripLoc(){
	$.post(`${tripPath}/front-end/TripPlan/tripDetail.do`,
	{ action : 'addTripLoc',
	  TRIP_ID : tripId,
	  LOC_ID : $('#addTripDetailLocId').val(),
	  arrivalTime : $('#arrivalTime').val(),
	  leaveTime : $('#leaveTime').val()
	},
	function(data){
		console.log("datetime " + data.arrivalTime);
		$('#addtripLocModal').modal('hide');
		updateMap();
		getTripDetailAjax(data.arrivalTime);
	},"json");
}

function getOneLocAjax(locId){
	$.get(`${tripPath}/front-end/TripPlan/tripLoc.do`,
	{  action:'getOneLoc',
	   LOC_ID:locId
	},
	function(data){
		console.log(data);
	let bsSlide = '';
	let images = '';
			if(data[1] !== undefined){		
              for(let count = 1, num = 0; count < data.length; count++,num++){
				  bsSlide += `<button type="button" data-bs-target="#loc-pic" id="picControl-${num}" data-bs-slide-to="${num}"></button>`;
				  images += `<div class="carousel-item" id="infoPic-${num}" style="height: 250px;">
				  <img src="data:image/png;base64,${data[count].LocPic}" class="d-block w-100 h-100">
				 </div> `;					  
			  }
			  }else{
				  images +=`<div class="carousel-item" id="infoPic-0" style="height: 250px;">
                  				<p class="text-center bg-secondary h-100 m-0 fs-3 text-white" style="line-height: 250px">查無圖片</p>
                  			</div>`;
			  }
	let htmlStr = `<div id="loc-pic" class="carousel slide" data-bs-ride="carousel">
                <div class="carousel-indicators">
                  ${bsSlide}
                </div>
                <div class="carousel-inner">
                    ${images}
                </div>
                <button class="carousel-control-prev" type="button" data-bs-target="#loc-pic" data-bs-slide="prev">
                  <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                </button>
                <button class="carousel-control-next" type="button" data-bs-target="#loc-pic" data-bs-slide="next">
                  <span class="carousel-control-next-icon" aria-hidden="true"></span>
                </button>
              </div>

              <div class="row my-3">
                <p class="col-12 fs-5 text-center text-truncate">${data[0].locName}</p>
                <p class="col-12 m-0 px-5 text-start">Address:</p>
                <p class="col-12 m-0 fs-5 text-center text-truncate">${data[0].locAddress}</p>
                <p class="col-12 m-0 px-5 text-start">Phone:</p>
                <p class="col-12 m-0 fs-5 text-center text-truncate">${data[0].locPhone === undefined? '---' : data[0].locPhone}</p>
              </div>

              <div class="row justify-content-center">
                <button class="col-8 btn trip-btn fw-bold border-dark" data-bs-toggle="modal"
                  data-bs-target="#addtripLocModal">加入行程</button>
                <div class="modal fade" id="addtripLocModal" tabindex="-1" aria-labelledby="addtripLocModalLabel"
                  aria-hidden="true">
                  <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">
                      <div class="modal-header">
                        <h5 class="modal-title fw-bold" id="addtripLocModalLabel">加入行程</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                      </div>
                        <div class="modal-body">
                          <div class="text-center m-2">
                            <label for="">預計到達時間 : </label>
                            <input type="text" name="arrivalTime" id="arrivalTime" onkeypress="$(this).val('')" autocomplete="off">
                          </div>
                          <div class="text-center m-2">
                            <label for="">預計離開時間 : </label>
                            <input type="text" name="leaveTime" id="leaveTime" onkeypress="$(this).val('')" autocomplete="off">
                          </div>
                        </div>
                        <input type="hidden" name="LOC_ID" id="addTripDetailLocId" value="${data[0].locId}">
                        <div class="modal-footer">
                          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
                          <button type="button" class="btn btn-primary" onclick="addTripLoc()">確定</button>
                        </div>
                    </div>
                  </div>
                </div>
              </div>`
			  $('#loc-info').css('display','block');
			  $('#locInfo-tab').addClass('active');
			  $('#cusLoc-tab').removeClass('active');
			  $('#locInfo').html(htmlStr);
			  $('#locInfo').addClass('active');
			  $('#locInfo').addClass('show');
			  $('#infoPic-0').addClass('active');
			  $('#picControl-0').addClass('active');
	},"json");	
}


function getTripDetailAjax(date){
	$.get(`${tripPath}/front-end/TripPlan/tripDetail.do`,
	{  
	   action : 'getTrip_TripDetail',
	   TRIP_ID : tripId,
	   DATE : date
	},
	function(data){
		console.log("this is tripDetail")
		console.log(data);
		let tripLocPic = ``;
		let htmlStr='';
		if(data.length !== 0){
			htmlStr+=`<div class="col-3 btn mx-3 text-white" style="background-color:pink;">${data[0].DATE}</div>`
		}
		for(let count = 0; count < data.length; count++){
			if(data[count].locPic != ""){
				tripLocPic = `<img src="data:image/png;base64,${data[count].locPic}" class="w-100 h-100" >`;
			}else{
				tripLocPic =`<p class="text-center m-auto text-white bg-secondary h-100"><i class="bi bi-card-image fa-3x "></i></p>`; 
			}
			htmlStr+=
            `<div class="col-12 d-flex mt-2" style="height:90px">
            <a class="col-10 d-flex align-items-center bg-cblue custom-loc" onclick="getOneLocAjax(${data[count].LocId}); focusTripLoc(${data[count].Lat},${data[count].Long})">
              <div class="col-4 p-2 h-100" >
                ${tripLocPic}
              </div>
              <div class="col-8 px-2 text-start">
                <p class="m-1">${data[count].ArrivalTime} - ${data[count].LeaveTime}</p>
                <p class="m-1 text-truncate">${data[count].LocName}</p>
              </div>
            </a>
            <button class="col-2 delete-tripLoc" onclick="deletOneLoc(this,${data[count].TripDId})" title="刪除地點"><i class="bi bi-trash3-fill fa-2x"></i></button>
          </div>`;
		}
		$('#tripLocArea').html(htmlStr);
	},"json");
}

let markers = [];
let polylines = [];
function updateMap(){
	$.get(`${tripPath}/front-end/TripPlan/tripLoc.do`,
	{  action: 'ajaxGetLocInfo',
	   startDate:startDate,
	   endDate:endDate,
	   tripId : tripId
	},
	function(data){
		markers.forEach((marker)=>marker.remove());
		polylines.forEach((polyline)=>polyline.remove());
		console.log("this is updateMap");
		console.log(data);
	for(let count = 0; count < data.length; count++){
		var tripIcon = L.ExtraMarkers.icon({
			icon:'fa-number',
			markerColor:'cyan',
			shape:'circle',
			prefix:'fa',
			number:count+1
		});
		let lat =data[count].latitude;
		let lng = data[count].longitude;
	let tripMarker=L.marker([lat,lng],{icon : tripIcon}).addTo(map).bindPopup(data[count].locName);
	markers.push(tripMarker);
	}
	/********* marker的連線 *********/
	if(data.length >= 2){
		for(let first = 0,sec=1; sec < data.length; first ++, sec++){
			let firstLat = data[first].latitude;
			let firstLng = data[first].longitude;
			let secLat = data[sec].latitude;
			let secLng = data[sec].longitude;
			let tripPolyline =L.polyline([
				[firstLat, firstLng],
				[secLat, secLng],
				],{color:'lightBlue',weight:10,opacity:0.8}).addTo(map)
			polylines.push(tripPolyline);	
		}
	}
},"json");
}

function deleteTripLoc(tripDetailId){
	$.post(`${tripPath}/front-end/TripPlan/tripDetail.do`,
	{
		action : 'deleteTripLoc',
		TRIP_DETAIL_ID :tripDetailId
	},function(data){
		updateMap();
		getTripDetailAjax(startDate);
	});
}

function addCusLoc(){
	console.log(userId);
	$.post(`${tripPath}/front-end/TripPlan/tripLoc.do`,
	{
		action : 'insert',
		userId : userId,
		loc_name : $('#cusLocName').val(),
		longitude :$('#longitude').val(),
		latitude :$('#latitude').val(),
		address : $('#cusLocAddress').val(),
		phone : $('#cusLocPhone').val(),
		locStatus : 2,
		forwardWhere :'front-end'
	}, 
	function(data){
		console.log(data);
        getCusLoc();
		
	},"json");
}

//updateTripName
function updateTripName(){
	$.post(`${tripPath}/front-end/TripPlan/trip.do`,
	{
		action : 'update',
		tripId : tripId,
		startDate : startDate,
		endDate : endDate,
		tripName :$('#upDateTripName').val(),
		coverPic:$('#updateTNPic').val(),
		note:$('#updateTNNote').val(),
		method:'ajax'
	},function(data){
		console.log("this is ajax update");
		console.log(data);
		swal("更改成功!!", `活動名稱 : ${data.tripName}`, "success");
	},"json");
}


function deleteUserLoc(locId){
	$.post(`${tripPath}/front-end/TripPlan/tripLoc.do`,
	{
		action : 'deleteUserLoc',
		LOC_ID : locId
		
	},
	function(data){
		console.log(data);
		swal ( "成功刪除自訂景點" ,  "" ,  "success" );
		getCusLoc();
	});
}

function getCusLoc(){
	$.get(`${tripPath}/front-end/TripPlan/tripLoc.do`,
	{
		action :'ajaxGetCusLoc',
		USER_ID : userId
	},function(data){
		let htmlStr = '';
		for(let count = 0; count < data.length; count++){
			htmlStr+=`<div class="custom-loc trip-btn col-10 d-flex align-items-center bg-cblue my-2 p-0" onclick="getOneLocAjax(${data[count].locId});focusToMap(${data[count].latitude},${data[count].longitude},'${data[count].locName}',true)">
                  <div class="col-3 text-center">
                    <i class="bi bi-geo-alt-fill fa-2x"></i>
                  </div>
                  <div class="col-7 px-2">
                    <p class="text-start text-truncate m-1">${data[count].locName}</p>
                    <p class="text-start text-truncate m-1">${data[count].locAddress}</p>
                  </div>
                </div>
                <div class="col-2 p-0 my-2 text-center">
                  <button type="button" class="h-100 w-100 delete-cusLoc" title="刪除我的地點" onclick="deleteCusLoc(this,${data[count].locId})"><i class="bi bi-trash3-fill fa-2x"></i></button>
                </div>`;
		}
		
		$('#couLocContainer').html(htmlStr);
		
	},"json");
}

function updateTripNote(){
	$.post(`${tripPath}/front-end/TripPlan/trip.do`,
	{
		action : 'update',
		startDate : startDate,
		endDate : endDate,
		tripId : tripId,
		tripName :$('#note-tripName').val(),
		coverPic :$('#note-coverPic').val(),
		note :$('#note-input').val(),
		method : 'ajax'
	},
	function(data){
		console.log(data);
		$('#note-tripName').val(`${data.note}`);
		swal ( "修改成功" ,  `${data.note}` , "success");
	
	},"json");
}

function addUserToTrip(){
	$.post(`${tripPath}/front-end/TripPlan/tripMbr.do`,
	{
		action : 'insert',
		TRIP_ID : tripId,
		USER_ID :'',
		USER_ACCOUNT : $('#addUserAccount').val(),
		USER_NAME:userName,
		TRIP_NAME:tripName
	},
	function(data){
		console.log(data);
		let htmlStr = `<div class="col-12 my-2">
                  		<img src="${tripPath}/front-end/member/getUserPic.jsp?UserAccount='${data.userAccount}'" class="mbr-pic">
                  		<h5 class="d-inline align-middle">${data.userName}</h5>
                  	  </div>`;
           $('#invitingUser').append(htmlStr);       	  
       		 swal ( "邀請成功" ,  `` , "success");
	},"json").fail(function(da){
		console.log(da);
		swal ( "Oops" ,  "此帳號不存在，或會員已在群組內!!" ,  "error" );
	});
}







