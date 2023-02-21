let geocoder;

function initMap(){
	geocoder =  new google.maps.Geocoder();
}

function codeAddress(e){
	let address = $('#cusLocAddress').val();
	console.log(address);
	geocoder.geocode({
		'address':address
	},function(results,status){
		if(status == google.maps.GeocoderStatus.OK){
			 swal ( "新增成功!!" , "",  "success" );
			$('#longitude').val(results[0].geometry.location.lng());
			$('#latitude').val(results[0].geometry.location.lat());
			addCusLoc();
		}else {
             swal ( "Oops" ,  "地址錯誤，請輸入有效地址!!" ,  "error" );
		}
	});
}