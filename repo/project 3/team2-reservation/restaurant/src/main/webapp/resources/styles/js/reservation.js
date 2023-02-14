$(document).ready(function(){
	$(".seat").on('click',function(e){
		console.log(e.target.id)
	});
});
$('.seat').on('click',function(e){
	if(e.target.id === 'btn_1'){
		alert(e.target.id);
	}else if(e.target.id === 'btn_2'){
		alert('1번테이블');
	}

});
