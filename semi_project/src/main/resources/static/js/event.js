window.onload = function() {
	
	if(document.getElementById("eventRegist")) {
        const $regist = document.getElementById("eventRegist");
        $regist.onclick = function() {
            location.href = "/event/eventRegist";
        }
    }
    
	
}