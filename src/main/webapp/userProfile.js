window.onload=function(){

		$.ajax({
		url:'../findUserFriends',
		type:"GET",
		contentType :'application/json',
		dataType :'json',
		async:false,
		success:function(data){
			var list=$("#friendList").empty();
			$.each(data,function(index,friend){
				drawFriendsList(index,friend);
			})
		}
		})
		findUserFriendRequests()
		notFriends()
		checkFriendRequest();
}
function findUserFriendRequests(){
	$.ajax({
		url:'../findUserFriendRequests',
		type:"GET",
		contentType :'application/json',
		dataType :'json',
		async:false,
		success:function(data){
			var list=$("#friendRequestList").empty();
			$.each(data,function(index,friend){
				drawFriendsRequestList(index,friend);
			})
		}
		})
}
function checkFriendRequest(){
	$.ajax({
		url:'../checkFriendRequest',
		type:"POST",
		contentType :'application/json',
		dataType :'json',
		data:$("#userId").val(),
		async:false,
		success:function(data){
			$("#friendRequest").empty();
			if(data.user==null)
				$("#friendRequest").append("<input type=\"button\" class=\"btn btn-info\" id=\"addFriend\" value=\"Send request\">") 
			else if(data.accepted==true)
				$("#friendRequest").append("<input type=\"button\" class=\"btn btn-danger\" id=\"removeFriend\" onclick=\"deleteFriend(\'"+data.user.email+"\')\"  value=\"Remove friend\">") 
			else if(data.accepted==false)
					$("#friendRequest").append("<input type=\"button\" disabled class=\"btn btn-secondary\" value=\"Request sent\">") 
			
		}
	})
}
function notFriends(){
	$.ajax({
		url:'../notFriends',
		type:'GET',
		async:false,
		success:function(data){
			$("tr:not('.header')").remove();
			
			$.each(data,function(index,friend){
				$("#notFriends").append(
						"<tr id=\'friend.id\'><td>"+friend.name+"</td><td> "+friend.surname+
						"</td><td><input type=\'button\' class=\"btn btn-info\"  value=\'Send request\'onclick=\"sendFriendRequest(\'"+friend.id+"\')\"></td></tr>");
			})
		}
	})
}
function drawFriendsList(index,data){
	var list=$("#friendList");
	if(list!=null){
		list.append("<li><a id=\"linkF"+index+"\" href='../user/"+data.friend.id+"\'>"+data.friend.name+" "+data.friend.surname+"</a>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp");
		if(data.accepted==false ){
			$("#linkF"+index).after("&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<input type=\"button\" class=\"btn btn-success\" onclick=\"addFriend(\'"+data.friend.email+"\')\" value=\"Accept request\"></li>")
	
		}else{
			$("#linkF"+index).after("&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<input type=\"button\" class=\"btn btn-danger\" onclick=\"deleteFriend(\'"+data.friend.email+"\')\" value=\"Delete friend\"></li>")

		}
	}
}
function drawFriendsRequestList(index,data){
	var list=$("#friendRequestList");
	if(list!=null){
		list.append("<li><a id=\"linkR"+index+"\" href='../user/"+data.friend.id+"\'>"+data.friend.name+" "+data.friend.surname+"</a>");
		if(data.accepted==false ){
			$("#linkR"+index).after("&nbsp<input type=\"button\" class=\"btn btn-success\" onclick=\"addFriend(\'"+data.friend.email+"\')\" value=\"Accept request\"></li>")
	
		}else{
			$("#linkR"+index).after("&nbsp<input type=\"button\" class=\"btn btn-danger\" onclick=\"deleteFriend(\'"+data.friend.email+"\')\" value=\"Reuest sent\"></li>")

		}
	}
}

function deleteFriend(email){
	$.ajax({
		url:'../deleteFriend',
		type:'POST',
		contentType :'application/json',
		dataType :'json',
		data:email,
		async:false,
		success:function(data){
			var list=$("#friendList").empty();
			$.each(data,function(index,friend){
				drawFriendsList(index,friend);
			})
			
		findUserFriendRequests()
		}
		
	})
	notFriends();
	checkFriendRequest();
}


function addFriend(email){
	$.ajax({
		url:'../addFriend',
		type:'POST',
		contentType :'application/json',
		dataType :'json',
		data:email,
		async:false,
		success:function(data){
			var list=$("#friendList").empty();
			$.each(data,function(index,friend){
				drawFriendsList(index,friend);
			})
			
		findUserFriendRequests()
		}
		
	})
	notFriends();

	checkFriendRequest();
}
function sendFriendRequest(id){

	$.ajax({
		url:'../sendFriendRequest/'+id,
		contentType : 'application/json',
		dataType : 'json',
		type:'POST',
		async:false,
		success:function(data){
			if(data!=null){
				alert("Request has been sent");
				$("#"+id+"").remove();
			}else
				alert("Reqeust has not been sent")
		}
	})
	notFriends();
	
}
$(document).on('click',"#addFriend",function(e){
	var id=$("#userId").val();
	
	$.ajax({
		url:'../sendFriendRequest/'+id,
		contentType : 'application/json',
		dataType : 'json',
		type:'POST',
		success:function(data){
			if(data!=null){
				alert("Request has been sent");
			}else
				alert("Reqeust has not been sent")
		}
	})	
	notFriends();

})
$(document).on('click',"#reset",function(e){
	notFriends()
})
$(document).on('click',"#search",function(e){
	var user=JSON.stringify({
		"name":$("#nameSearch").val(),
		"surname":$("#surnameSearch").val(),
	})
	$.ajax({
		url:"../../userController/searchUser",
		type:"POST",
		data:user,
		contentType:'application/json',
		dataType:'json',
		success:function(data){
			
			$("tr:not('.header')").remove();
			$.each(data,function(index,friend){
				$("#notFriends").append(
						"<tr id=\'friend.id\'><td>"+friend.name+"</td><td> "+friend.surname+
						"</td><td><input type=\'button\' class=\"btn btn-info\"  value=\'Send request\'onclick=\"sendFriendRequest(\'"+friend.id+"\')\"></td></tr>");
			})
		}
	})
})
