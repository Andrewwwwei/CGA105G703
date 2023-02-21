let geocoder;

function initMap(){
	geocoder =  new google.maps.Geocoder();
}

function codeAddress(e){
	let address = $('#addAddress').val();
	geocoder.geocode({
		'address':address
	},function(results,status){
		if(status == google.maps.GeocoderStatus.OK){
			 swal ( "新增成功!!" , "",  "success" );
			$('#addLocalongitude').val(results[0].geometry.location.lng());
			$('#addLocalatitude').val(results[0].geometry.location.lat());
			e.closest('form').submit();
		}else {
             swal ( "Oops" ,  "地址錯誤，請輸入有效地址!!" ,  "error" );
		}
	});
}

