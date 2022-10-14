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