 /* Success & Fail */     
 const Toast = Swal.mixin({
  toast: true,
  position: 'top-end',
  showConfirmButton: false,
  timer: 3000,
  timerProgressBar: true,
  didOpen: (toast) => {
   toast.addEventListener('mouseenter', Swal.stopTimer)
   toast.addEventListener('mouseleave', Swal.resumeTimer)
    }
 })
 
 function success(info,id){
  Toast.fire({
   icon:'success',
   title: info +' Sucessfully'+'<br> Declaration ID : '+id,
  });
 }
 function AnnounceSuccess(info , id){
  Toast.fire({
   icon:'success',
   title: info +'成功'+'<br> 公告編號 : '+id,
  });
 }
 function fail(info){
  Toast.fire({
   icon:'error',
   title: info + ' Fail',
  });
 }
 function AnnounceFail(info){
  Toast.fire({
   icon:'error',
   title: info + '失敗',
  });
 }
 
  function AnnounceFail2(info){
  Toast.fire({
   icon:'error',
   title: info + '失敗 , 請選擇發佈對象',
  });
 }