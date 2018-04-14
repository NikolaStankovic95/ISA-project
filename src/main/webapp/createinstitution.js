$(document).on('click', '#add', function(e) {
	var name = $("#name").val();
	var type = $("#type").val();
	var description = $("#description").val();
	var address = $("#address").val();
	
	var institution = JSON.stringify({
		"name" : name,
		"type" : type,
		"description" : description,
		"address" : address,
		"rating" : '0',

	})
	var fzlist = [];
	var ui = document.getElementById('addedAdmins');
	var elements = ui.getElementsByTagName('li');
	var temp = 'adedAdmin';
	for (i = 0; i < elements.length; i++) {
		var str= elements[i].id
	console.log()
		fzlist.push(str.substring(9,str.length));
	}
	
	var instlist = [];
	var ui = document.getElementById('addedInstdAdmins');
	var elements = ui.getElementsByTagName('li');
	var temp = 'adedInstAdmin';
	for (i = 0; i < elements.length; i++) {
		var str= elements[i].id
	console.log(str.substring(13,str.length))
		instlist.push(str.substring(13,str.length));
	}
	
	
	$.ajax({
		type : 'POST',
		url : '/institution/createInstitution',
		contentType : 'application/json',
		dataType : 'json',
		data : institution,
		success : function(data) {
			alert("Kreirana institucija");
			$.ajax({
				type : 'PATCH',
				url : '/institution/insertFanzoneAdmins/'+data.id,
				contentType : 'application/json',
				dataType : 'json',
				data : JSON.stringify(fzlist),
				success : function(data) {
					alert("Dodati Admini fanzone");
					$.ajax({
						type : 'PATCH',
						url : '/institution/insertInstitutionAdmins/'+data.id,
						contentType : 'application/json',
						dataType : 'json',
						data : JSON.stringify(instlist),
						success : function(data) {
							alert("Dodati Admini institucije");
							
						}
					});
				}
			});
		}
	});
})
function myFunction() {
	document.getElementById("myDropdown").classList.toggle("show");
}

function myFunction2() {
	document.getElementById("myDropdown2").classList.toggle("show");
}
function filterFunction() {
	var input, filter, ul, li, a, i;
	input = document.getElementById("myInput");
	filter = input.value.toUpperCase();
	div = document.getElementById("myDropdown");
	li = div.getElementsByTagName("li");
	for (i = 0; i < li.length; i++) {
		if (li[i].innerHTML.toUpperCase().indexOf(filter) > -1) {
			li[i].style.display = "";
		} else {
			li[i].style.display = "none";
		}
	}
}
function filterFunction2() {
	var input, filter, ul, li, a, i;
	input = document.getElementById("myInput2");
	filter = input.value.toUpperCase();
	div = document.getElementById("myDropdown2");
	li = div.getElementsByTagName("li");
	for (i = 0; i < li.length; i++) {
		if (li[i].innerHTML.toUpperCase().indexOf(filter) > -1) {
			li[i].style.display = "";
		} else {
			li[i].style.display = "none";
		}
	}
}
function moveToList(id){
	var li = document.getElementById(id);
	var name = li.getAttribute('value');
	$("#addedAdmins").append("<li id=adedAdmin"+id+">"+name+"<button  onclick=removeFromList("+id+",\"" +  name + "\")>Remove</button></li>");
	li.remove();
}
function removeFromList(id,name){
	console.log("pogodio");
	var li = document.getElementById("adedAdmin"+id);
	$("#lista").append("<li id="+id+" value="+name+">"+name+
					"<button  onclick=moveToList("+id+")>Choose</button></li>");
	li.remove()
}

function moveToInstList(id){
	var li = document.getElementById("inst"+id);
	var name = li.getAttribute('value');
	$("#addedInstdAdmins").append("<li id=adedInstAdmin"+id+">"+name+"<button  onclick=removeFromInstList("+id+",\"" +  name + "\")>Remove</button></li>");
	li.remove();
}
function removeFromInstList(id,name){
	console.log("pogodio");
	var li = document.getElementById("adedInstAdmin"+id);
	$("#instlista").append("<li id=inst"+id+" value="+name+">"+name+
					"<button  onclick=moveToInstList("+id+")>Choose</button></li>");
	li.remove()
}