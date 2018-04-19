function convertDate(time){
	var item=new Date(time);
	var date=item.getDate()
	var month=item.getMonth()+1
	var year=item.getYear()+1900
	var h=item.getHours()
	var m=item.getMinutes()
	var s=item.getSeconds()
	var mili=item.getMilliseconds()
	var formatted=year;
	if(month<10){
		formatted+="-0"+month+"-"+date+" "+h+":"+m;
	}else{
		formatted=+"-"+month+"-"+date+" "+h+":"+m;
	}
	if(s<10){
		formatted+=":0"+s+"."+mili;
	}else
		formatted+=":"+s+"."+mili
	return formatted;
}
$(document).on('click','.delete',function(e){
	e.preventDefault();
	  var url=$(this).attr("href");
	  var id=$(this).attr("id");
	toastr.info("<br /><br /><button type='button' id='confirmationRevertYes' class='btn clear'>Yes</button>&nbsp&nbsp&nbsp" +
			"<button type='button' id='confirmationRevertNo' class='btn clear'>No</button>",'Are you sure you want to decline reservation?',
			{
      closeButton: false,
      allowHtml: true,
      onShown: function (toast) {
          $("#confirmationRevertYes").click(function(){
        	
      		$.ajax({
      			url:url,
      			type:"DELETE",
      			success:function(data){
      				if(data.length!=0){
      					
      						$("#row"+id).remove()
      				
      					
      				}else if(data.length==0){
      					$("tr:not('.header')").remove();
      					
      				}
      				else{
      					toastr.error("You can decline your reservation only 30 minutes before projection starts!")
      				}
      			},error:function(data){
      				toastr.error("You can decline your reservation only 30 minutes before projection starts!")
      				
      			}
      		})
          });
        }
  });
	
})