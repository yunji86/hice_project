window.onload = function() {   
   if(document.getElementById("writeBoard")) {
        const $writeBoard = document.getElementById("writeBoard");
        $writeBoard.onclick = function() {
            location.href = "/product/new";
        }
    }
   
     if(document.getElementById("deletePro")) {
        const $update = document.getElementById("deletePro");
        $update.onclick = function() {
            location.href = "/product/delete";
        }
    }


//사진 업로드 
(function(){
			
			const fileElements = document.querySelectorAll("[type=file]");
			const imageArea = document.querySelectorAll(".image-area");
			imageArea.forEach(item => item.addEventListener('click', open));
			fileElements.forEach(item=> item.addEventListener('change', preview));
			
			function open(){
				const index = Array.from(imageArea).indexOf(this);
				fileElements[index].click();
			}
			
			function preview(){
				const index = Array.from(fileElements).indexOf(this);
				if(this.files && this.files[0]){
					const reader = new FileReader();
					reader.readAsDataURL(this.files[0]);
					reader.onload = function(){
							imageArea[index].innerHTML = `<img src ='${reader.result}' style='width: 100px; height: 100px;'>`;
						}
					}
				}
			}
		)();
}    

//카카오 결제 API
//문서가 준비되면 제일 먼저 실행
$(document).ready(function(){ 
	$("#iamportPayment").click(function(){ 
    	payment(); //버튼 클릭하면 호출 
    }); 
})


//버튼 클릭하면 실행
function payment(data) {
    IMP.init('imp86422148');//아임포트 관리자 콘솔에서 확인한 '가맹점 식별코드' 입력
    IMP.request_pay({// param
    	url: "",
        pg: "kakaopay.TC0ONETIME", //pg사명 or pg사명.CID (잘못 입력할 경우, 기본 PG사가 띄워짐)
        pay_method: "card", //지불 방법
        merchant_uid: "merchant_hiceCoffee", //가맹점 주문번호 (아임포트를 사용하는 가맹점에서 중복되지 않은 임의의 문자열을 입력)
        name: "과자", //결제창에 노출될 상품명
        amount: 10000, //금액
        buyer_name : "박천웅",
    }, function (rsp) { // callback
        if (rsp.success) {
            alert("완료 -> imp_uid : "+rsp.imp_uid+" / merchant_uid(orderKey) : " +rsp.merchant_uid);
        } else {
            alert("실패 : 코드("+rsp.error_code+") / 메세지(" + rsp.error_msg + ")");
        }
    });
}