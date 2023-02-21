function delTrip(e) {
  swal({
    title: "確定刪除行程?",
    text: "刪除後將無法復原",
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


//dateTimePicker

$.datetimepicker.setLocale('zh'); // kr ko ja en
$(function(){
	let today = new Date();
	 $('#tripStart').datetimepicker({
	  format:'Y-m-d',
	  onShow:function(){
	   this.setOptions({
		minDate: today,
	    maxDate:$('#tripEnd').val()?$('#tripEnd').val():false
	   })
	  },
	  timepicker:false
	 });
	 
	 $('#tripEnd').datetimepicker({
	  format:'Y-m-d',
	  onShow:function(){
	   this.setOptions({
	    minDate:$('#tripStart').val()?$('#tripStart').val():today
	   })
	  },
	  timepicker:false
	 });
});

function addTripForm(event) {
  if (!$('#cover-pic').val()) {
    swal ( "Oops" ,  "請選擇封面圖片!!" ,  "error" )
    event.preventDefault();
  }
}

function updateTripPicForm(event) {
  if (!$('#updatePic').val()) {
    swal ( "Oops" ,  "請選擇封面圖片!!" ,  "error" )
    event.preventDefault();
  }
}