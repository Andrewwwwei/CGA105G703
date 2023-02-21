// *** 放置地圖 24.958259814942263, 121.22486263762384
let zoom = 17; // 0 - 18
//let center = [24.958259814942263, 121.22486263762384]; // 中心點座標
let center = [24.428920, 120.868150]; // 中心點座標
let map = L.map('map').setView(center, zoom);
L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
  attribution: '© OpenStreetMap', // 商用時必須要有版權出處
  zoomControl: true, // 是否秀出 - + 按鈕
}).addTo(map);

// 我要加上一個maker，並設定他的座標，顯示在map上
L.marker(center).addTo(map)
  .bindPopup("大湖文化館")//我要針對這個maker加上html內容進去
  .openPopup();//預設為開啟
//更改icon需要載入別人的套件

const popup = L.popup();
let lat;
let lng;
function onMapClick(e) {
  lat = e.latlng.lat; // 緯度
  lng = e.latlng.lng; // 經度
  popup.setLatLng(e.latlng).setContent(`緯度：${lat}<br/>經度：${lng}`).openOn(map);
  // .setLatLng(e.latlng)
  // .setContent(`緯度：${lat}<br/>經度：${lng}`)
  // .openOn(map);
}
