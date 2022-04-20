<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
<h3>계좌리스트</h3>
<div id="list">
	<!-- <div class="acc" data-usenum="12323232"><span>대구은행</span><span>1111222***</span></div> -->
</div>
<div id="result"></div>

<script>
		var url = "accountList";
		$.ajax(url).done(function(datas){
			for(data of datas){
				//console.log(data);
			//은행명, 계좌번호, 계좌일련변호 divacc에 출력
	$("<div>").addClass("acc")
			  .data("usenum", data.fintech_use_num)
			  .append($("<span>").html(data.bank_name))
			  .append($("<span>").html(data.account_num_masked))
			  //.append($("<input>").val(data.별칭))
			  .append($("<button>").html("거래내역").addClass("btnTrans"))
			  .appendTo("#list")}

		})

		//잔액을 가져오기.
		 $("#list").on("click", "span", function(){
			var num = $(this).closest(".acc").data("usenum");
			
			var url = "balanceList";
			$.ajax({
				url:url,
				data : {fintechUseNum : num},	
			}).done(function(datas){
					$("#result").html("잔액 : " + datas);
			}) 
		})
		 
		
		//거래내역
		$("#list").on("click", ".btnTrans", function(){
			
			var url = "transactionList";
			
			$.ajax({
				url:url
				
			}).done(function(datas){
				console.log(datas);
				for(var i=0; i<10; i++){
					var div = $("<div>").html("일시 : " + datas.res_list[i].tran_date + " " +
							  				datas.res_list[i].inout_type + " : "  + datas.res_list[i].tran_amt +
										 	  " 잔액 : " + datas.res_list[i].after_balance_amt );
					$("#result").append(div);
				}
								
			}) 
			
		})

		
</script>
</body>
</html>