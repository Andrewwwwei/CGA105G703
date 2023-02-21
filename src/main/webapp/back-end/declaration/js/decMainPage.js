

//	 Get the values from the target row & put all of these values on the update-page 
function updateView(id, title, content) {
	$('#update-ID').text('Declaration ID : ' + id);
	$('#update-title').attr("value", title)
	$('#update-content').text(content);
	$('#setUpdateID').attr("value", id);
}

//  td onclick
$(document).ready(function() {
	$(".clickable").click(function() {
		window.location = $(this).data("href");
	});
})

