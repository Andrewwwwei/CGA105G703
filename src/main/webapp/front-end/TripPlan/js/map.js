//取消body scroll
$('body').css('overflow','hidden');
//當網頁載入完畢時
$(document).ready(()=>{
	$('#search-loc').html(`
		<input type="search" name="word" class="form-control p-0 border-0 fs-5">
		<input type="hidden" name="action" value="search">
		<input type="hidden" name="forward" value="front-end">
    	<button type="submit" class="btn btn-primary">
      	<i class="fas fa-search"></i>
    	</button>`);	
    	
//search Location
updateMap();
});

/********* 取得user定位 *********/
let userLocMarker = [24.958682752351766, 121.22511946454931];
if(navigator.geolocation){
	navigator.geolocation.getCurrentPosition(function(position){
		let lat = position.coords.latitude;
		let lng = position.coords.longitude;
		userLocMarker = L.latLng(lat,lng);
		this.map.flyTo(userLocMarker,13);
		L.marker(userLocMarker,{icon : userIcon}).addTo(map).bindPopup('您的位置').openPopup();
	});
}else{
	alert('很抱歉，您的瀏覽器版本，不支援此網頁');
}

/********* 放置地圖 *********/
var map = L.map('map',{
	center:userLocMarker,
	zoom:10
});

L.tileLayer('https://{s}.tile.openstreetmap.fr/hot/{z}/{x}/{y}.png', {
  attribution: '© OpenStreetMap', // 商用時必須要有版權出處
  zoomControl: true,
}).addTo(map);

var userIcon = L.ExtraMarkers.icon({
	icon:'fa-igloo',
	markerColor:'yellow',
	shape:'circle',
	prefix:'fa',
});

const focusIcon = L.ExtraMarkers.icon({
	shape: 'circle',
	markerColor: 'green',
	prefix: 'fa',
	icon: 'fa-spinner',
	iconColor: '#fff',
	iconRotate: 0,
	extraClasses: 'fa-spin',
	number: '',
	svg: false
});

const cusLocIcon = L.ExtraMarkers.icon({
	shape: 'circle',
	markerColor: 'white',
	prefix: 'fa',
	icon: 'fa-cog',
	iconColor: 'pink',
	iconRotate: 0,
	extraClasses: '',
	number: '',
	svg: false
});

	let focusLoc;
//select location focus to map fa-spinner  L.marker(center,{icon:focusIcon}).addTo(map)
function focusToMap(lat, lng, locName,isCus){
	if(focusLoc){
	map.removeLayer(focusLoc);		
	}
	if(isCus === true){
		focusLoc =  L.marker([lat,lng],{icon:cusLocIcon}).addTo(map).bindPopup(locName).openPopup();
	}else{
		focusLoc =  L.marker([lat,lng],{icon:focusIcon}).addTo(map).bindPopup(locName).openPopup();
	}
	this.map.flyTo([lat,lng],16);
}

function focusTripLoc(lat,lng){
	this.map.flyTo([lat,lng],17);
}


 	
 





