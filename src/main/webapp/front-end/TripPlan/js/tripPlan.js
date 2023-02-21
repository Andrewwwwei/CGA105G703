// 讓以自訂的景點打開資訊
$('.custom-loc').click((e) => {
  $('#locInfo-tab').addClass('active');  $('#locInfo').addClass('show active');
  $('#cusLoc-tab').removeClass('active');
  $('#cusLoc').removeClass('show active');
});

//在活動名稱後方的自訂景點開啟
$('#custom-Loc').click((e) => {
  let locInfo = $('#loc-info');
  //如果地點資訊卡片沒有打開 那就把它開啟
  if (locInfo.css('display') === 'none') {
    locInfo.css('display','block');
    //如果資訊卡都開啟，在看是不再在自訂景點內，如果是就把資訊卡整個關掉
  } else if ($('#cusLoc-tab').hasClass('active') || $('#cusLoc').hasClass('show active')) {
    ;
    locInfo.css('display','none');
  }
  $('#cusLoc-tab').addClass('active');
  $('#cusLoc').addClass('show active');
  $('#locInfo-tab').removeClass('active');
  $('#locInfo').removeClass('show active');
});


//聊天室的開啟
$('#mbrMsg-icon').click((e) => {
  $('#mbr-chat').css('display', 'block');
  $('#mbrMsg-icon').css('display', 'none');
  //當聊天室打開將scroll固定在最下方
  let scrollHeight = $('#msg-content').prop('scrollHeight');
  $('#msg-content').animate({ scrollTop: scrollHeight }, 400);
});

//聊天室關閉
$('#close-msg').click((e) => {
$('#mbrMsg-icon').css('display', 'block');
$('#mbr-chat').css('display', 'none');
});

// 聊天室內開啟筆記本
$('#notes-btn').click((e) => {
  let memNotes = $('#mem-notes');
  if (memNotes.css('display') === 'none') {
    memNotes.css('display', 'block');
  } else if ($('#note-btn').hasClass('active') || $('#notes').hasClass('show active')) {
    memNotes.css('display', 'none');
  }
  $('#note-btn').addClass('active');
  $('#notes').addClass('show active');
  $('#mbr-btn').removeClass('active');
  $('#mbr').removeClass('show active');
});

//聊天室內開啟活動成員
$('#mbrs-btn').click((e) => {
  let memNotes = $('#mem-notes');
  if (memNotes.css('display') === 'none') {
    memNotes.css('display', 'block');
  } else if ($('#mbr-btn').hasClass('active') || $('#mbr').hasClass('show active')) {
    memNotes.css('display', 'none');
  }
  $('#mbr-btn').addClass('active');
  $('#mbr').addClass('show active');
  $('#note-btn').removeClass('active');
  $('#notes').removeClass('show active');
});

$('#invite').click((e) => {
  let memNotes = $('#mem-notes');
  if (memNotes.css('display') === 'none') {
    memNotes.css('display', 'block');
  } else if ($('#mbr-btn').hasClass('active') || $('#mbr').hasClass('show active')) {
    memNotes.css('display', 'none');
  }
  $('#mbr-btn').addClass('active');
  $('#mbr').addClass('show active');
  $('#note-btn').removeClass('active');
  $('#notes').removeClass('show active');
});

//close #loc-info
function closeLocInfo(){
	$('#loc-info').css('display','none');
};

//date control
$("#dateRight").click(function() {
  $("#scroll-content").animate({
  scrollLeft: "+=75px"
  }, "slow");
  });

$("#dateLeft").click(function() {
  $("#scroll-content").animate({
  scrollLeft: "-=75px"
  }, "slow");
  });

  //close-search
  $('#close-search').click(function(){
    $('#search-content').css('display','none');
  });
  
  //dateTimePicker update tripDate
$.datetimepicker.setLocale('zh'); // kr ko ja en
$(function(){
	let today = new Date();
	 $('#updateStartDate').datetimepicker({
	  format:'Y-m-d',
	  onShow:function(){
	   this.setOptions({
		minDate: today,
	    maxDate:$('#updateEndDate').val()?$('#updateEndDate').val():false
	   })
	  },
	  timepicker:false
	 });
	 
	 $('#updateEndDate').datetimepicker({
	  format:'Y-m-d',
	  onShow:function(){
	   this.setOptions({
	    minDate:$('#updateStartDate').val()?$('#updateStartDate').val():today
	   })
	  },
	  timepicker:false
	 });
});

// dateTimePicker insert LocTime
$.datetimepicker.setLocale('zh'); // kr ko ja en
$(document).on('focus', '#arrivalTime', function(){
  $(this).datetimepicker({
   format:'Y-m-d H:i',
   onShow:function(){
    this.setOptions({
     minDate: startDate,
     maxDate:$('#leaveTime').val()?$('#leaveTime').val():endDate
    })
   },
   timepicker:true,
   step:10
  });
});

$(document).on('focus', '#leaveTime', function(){
  $(this).datetimepicker({
   format:'Y-m-d H:i',
   onShow:function(){
    this.setOptions({
     minDate:$('#arrivalTime').val()?$('#arrivalTime').val():startDate,
     maxDate:endDate
    })
   },
   timepicker:true,
   step:10
  });
});

//tripDate active
$('#tripDate-1').addClass('active');

function focusDate(e){
	$('.Date-form button:first-child.active').removeClass('active');
	$(e).addClass('active');
}

//Delete dateLocation
function deleteDateLoc(e){
  swal({
    title: "確定刪除當天行程地點?",
    text: "刪除後將無法復原!!",
    icon: "warning",
    buttons: {
      cancel: {
        text: "取消",
        visible: true
      },
      confirm: {
        text: "確定",
        value: true,
        visible: true,
        className: "",
        closeModal: false
      },
    },
    dangerMode: true,
  }).then((willDelete) => {
    if (willDelete) {
      swal("成功刪除", {
        icon: "success",
      }).then(()=>{
        e.closest('form').submit();
      });
    }
  });
}

//Delete One TripLocation
function deletOneLoc(e,tripDetailId){
	  swal({
    title: "確定刪除行程景點?",
    text: "刪除後將無法復原!!",
    icon: "warning",
    buttons: {
      cancel: {
        text: "取消",
        visible: true
      },
      confirm: {
        text: "確定",
        value: true,
        visible: true,
        className: "",
        closeModal: false
      },
    },
    dangerMode: true,
  }).then((willDelete) => {
    if (willDelete) {
      swal("成功刪除", {
        icon: "success",
      }).then(()=>{
         deleteTripLoc(tripDetailId);
      });
    }
  });
}

//Delete customized Location 
function deleteCusLoc(e,locId){
	  swal({
    title: "確定刪除自訂景點?",
    text: "刪除後將無法復原!!",
    icon: "warning",
    buttons: {
      cancel: {
        text: "取消",
        visible: true
      },
      confirm: {
        text: "確定",
        value: true,
        visible: true,
        className: "",
        closeModal: false
      },
    },
    dangerMode: true,
  }).then((willDelete) => {
    if (willDelete) {
     deleteUserLoc(locId);
    }
  });
}

//changeTripDate
function changeTripDate(e){
	console.log($('#updateStartDate').val());
	if($('#updateStartDate').val()){console.log('haha')}
	if(!$('#updateStartDate').val() || !$('#updateEndDate').val()){
		swal ( "Oops" ,  "請輸入日期!!" ,  "error" )
	}else{
		  swal({
	    title: "確定更改日期?",
	    text: "刪除後將無法復原!!",
	    icon: "warning",
	    buttons: {
	      cancel: {
	        text: "取消",
	        visible: true
	      },
	      confirm: {
	        text: "確定",
	        value: true,
	        visible: true,
	        className: "",
	        closeModal: false
	      },
	    },
	    dangerMode: true,
	  }).then((willDelete) => {
	    if (willDelete) {
	      swal("更改成功!!", {
	        icon: "success",
	      }).then(()=>{
	        e.closest('form').submit();
	      });
	    }
	  });		
	}
}












