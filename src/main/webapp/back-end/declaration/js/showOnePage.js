// checkboxes
$(document).ready(function() {
	$("#north").change(function() {
		let check = $(this).is(':checked');
		$(".north-input").prop('checked', check);
	});
	$("#midd").change(function() {
		let check = $(this).is(':checked');
		$(".midd-input").prop('checked', check);
	});
	$("#south").change(function() {
		let check = $(this).is(':checked');
		$(".south-input").prop('checked', check);
	});
	$("#east").change(function() {
		let check = $(this).is(':checked');
		$(".east-input").prop('checked', check);
	});
	$("#island").change(function() {
		let check = $(this).is(':checked');
		$(".island-input").prop('checked', check);
	});
});

// card add button
	var list =[] ;
function addMem(id){
	if (list.includes(id)){
		return console.log("this member already exists");
	}
	
	let btnName = '#addBtn'+id;
	let $clickedBtn = $(btnName);

	let $clickedDiv = $clickedBtn.closest('div.card');
	let $clonedDiv = $clickedDiv.clone();
	let $conedButton = $clonedDiv.find("button");
	let deleteIcon = '<i class="fas fa-times"></i>';
	
	$conedButton.empty();
	$conedButton.append(deleteIcon);
	$conedButton.attr("onclick","deleteMen("+id+")");
	$conedButton.attr("class","btn btn-primary btn-floating");
	$conedButton.attr("id","deleteBtn"+id);
	
	$clonedDiv.append("<input type='text' class='addedcard' name='memID' value='"+id+"' style='display:none;'>");
	$clonedDiv.appendTo("#list-contents");
	
	list.push(id);
}

function deleteMen(id){
	let btnName = '#deleteBtn'+id;
	let $clickedBtn = $(btnName);
	let $clickedDiv = $clickedBtn.closest('div.card');
	$clickedDiv.remove();
	list = arrayRemove(list,id);
}

function arrayRemove(arr, value) { 
        return arr.filter(function(ele){ 
            return ele != value; 
        });
    }


