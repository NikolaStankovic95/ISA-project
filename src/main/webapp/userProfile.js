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
		getUsers();
		checkFriendRequest();
}

function getUsers(){
	$.ajax({
		url:'../findUserNotFriendWith',
		type:"GET",
		contentType :'application/json',
		dataType :'json',
		async:false,
		success:function(data){
			var list=$("#users").empty();
			$.each(data,function(index,user){
				drawUsers(index,user);
			})
		}
	})
}

function drawUsers(index,user){
	var list=$("#users");
	list.append("<li><a id=\"link"+index+"\" href='../user/"+user.id+"\'>"+user.name+" "+user.surname
			+"</a>&nbsp&nbsp<input type=\"button\" onclick=\"addUser(\'"+user.id+"\')\" value=\"Send request\">");
	
	
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
			if(data.friend==null)
				$("#friendRequest").append("<input type=\"button\" id=\"addFriend\" value=\"Send request\">") 
			else if(data.accepted==true)
				$("#friendRequest").append("<input type=\"button\" id=\"removeFriend\" onclick=\"deleteFriend(\'"+data.friend.email+"\')\"  value=\"Remove friend\">") 
			else if(data.accepted==false)
					$("#friendRequest").append("<input type=\"button\" disabled  value=\"Request sent\">") 
			
		}
	})
}
function drawFriendsList(index,data){
	var list=$("#friendList");
	if(list!=null){
		list.append("<li><a id=\"link"+index+"\" href='../user/"+data.user.id+"\'>"+data.user.name+" "+data.user.surname+"</a>");
		if(data.accepted==false ){
			list.append("&nbsp<input type=\"button\" onclick=\"addFriend(\'"+data.user.email+"\')\" value=\"Accept request\"></li>")
	
		}else{
			list.append("&nbsp<input type=\"button\" onclick=\"deleteFriend(\'"+data.user.email+"\')\" value=\"Delete friend\"></li>")

		}
	}
}
function drawFriendsRequestList(index,data){
	var list=$("#friendRequestList");
	if(list!=null){
		list.append("<li><a id=\"link"+index+"\" href='../user/"+data.friend.id+"\'>"+data.friend.name+" "+data.friend.surname+"</a>");
		if(data.accepted==false ){
			list.append("&nbsp<input type=\"button\" onclick=\"addFriend(\'"+data.friend.email+"\')\" value=\"Accept request\"></li>")
			list.append("&nbsp<input type=\"button\" onclick=\"rejectRequest(\'"+data.friend.email+"\')\" value=\"Reject request\"></li>")

		}else{
			list.append("&nbsp<input type=\"button\" onclick=\"deleteFriend(\'"+data.friend.email+"\')\" value=\"Delete friend\"></li>")

		}
	}
}

function rejectRequest(email){
	$.ajax({
		url:'../rejectRequest',
		type:'POST',
		contentType :'application/json',
		dataType :'json',
		data:email,
		success:function(data){
			var list=$("#friendRequestList").empty();
			$.each(data,function(index,friend){
				drawFriendsRequestList(index,friend);
			})
		}
		
	})
	checkFriendRequest();
}


function deleteFriend(email){
	$.ajax({
		url:'../deleteFriend',
		type:'POST',
		contentType :'application/json',
		dataType :'json',
		data:email,
		success:function(data){
			var list=$("#friendList").empty();
			$.each(data,function(index,friend){
				drawFriendsList(index,friend);
			})
		}
		
	})
	checkFriendRequest();
}

function addUser(id){
	$.ajax({
		url:'../sendFriendRequest/'+id,
		contentType : 'application/json',
		dataType : 'json',
		type:'POST',
		async:false,
		success:function(data){
			if(data!=null){
				alert("Request has been sent");
			}else
				alert("You already sent friend request")
		}
	})
}
function addFriend(email){
	$.ajax({
		url:'../addFriend',
		type:'POST',
		contentType :'application/json',
		dataType :'json',
		data:email,
		success:function(data){
			var list=$("#friendRequestList").empty();
			var list=$("#friendList").empty();
			
			$.each(data,function(index,friend){
				drawFriendsList(index,friend);
			})
		}
		
	})
	checkFriendRequest();
}
$(document).on('click',"#addFriend",function(e){
	var id=$("#userId").val();
	
	$.ajax({
		url:'../sendFriendRequest/'+id,
		contentType : 'application/json',
		dataType : 'json',
		type:'POST',
		async:false,
		success:function(data){
			if(data!=null){
				alert("Request has been sent");
			}else
				alert("Reqeust has not been sent")
		}
	})
	getUsers();
	$("#friendRequest").empty();
	$("#friendRequest").append("<input type=\"button\" disabled  value=\"Request sent\">") 
})

$(document).on('click',"#searchUsers",function(e){
	var name=$("#searchName").val();
	var surname=$("#searchSurname").val();
	$.ajax({
		url:'../searchUser/'+name+"/"+surname,
		contentType : 'application/json',
		dataType : 'json',
		type:'GET',
		async:false,
		success:function(data){
			alert(data);
		}
	})
})
