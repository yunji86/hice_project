window.onload = function() {

	if (document.getElementById("regist")) {

		const $regist = document.getElementById("regist");

		$regist.onclick = function() {

			/*
			
			var memPhone1 = document.getElementById('memPhone1'); 
			var memPhone2 = document.getElementById('memPhone2'); 
			var memPhone3 = document.getElementById('memPhone3');
	    
			var temp = memPhone2.concat(memPhone3);
	    
			var memPhone = parseInt(memPhone1.concat(temp));
			
			*/

			location.href = "/member/regist";

		}

	}

	if (document.getElementById("terms")) {

		var terms = document.getElementById("terms");

		var terms_data = {
			"c1": false,
			"c2": false
		};

		const c1 = document.getElementById('c1');
		const c2 = document.getElementById('c2');

		function checkboxListener() {
			terms_data[this.name] = this.checked;
		}

		c1.onclick = c2.onclick = checkboxListener;

		terms.onsubmit = function(e) {

			e.preventDefault();

			if (!terms_data['c1']) {
				alert('이용동의 약관에 동의하지 않았습니다.');
				return location.href = "/member/terms";
			}

			if (!terms_data['c2']) {
				alert('개인정보 수집 및 이용에 대한 안내를 동의하지 않았습니다.');
				return location.href = "/member/terms";
			}

			if (terms_data['c1'] && terms_data['c2']) {
				return location.href = "/member/regist";
			}

		};

	}

	if (document.getElementById("idRedupCheck")) {

		const $duplication = document.getElementById("idRedupCheck");

		$duplication.onclick = function() {

			let memId = document.getElementById("memId").value.trim();

			fetch("/member/idRedupCheck", {
				method: "POST",
				headers: {
					'Content-Type': 'application/json;charset=UTF-8'
				},
				body: JSON.stringify({ memId: memId })
			})
				.then(result => result.text())
				.then(result => alert(result))
				.catch((error) => error.text().then((res) => alert(res)));
		}

	}
	
	if(document.getElementById("memEmailSend")) {
		
		const $memEmailSend = document.getElementById("memEmailSend");
		
		$memEmailSend.onclick = function() {
			
			let memEmail = document.getElementById("memEmail").value.trim();
			
			fetch("/member/memEmailSend", {
				method: "POST",
				headers: {
					'Content-Type': 'application/json;charset=UTF-8'	
				},
				body: JSON.stringify({ memEmail: memEmail })
			})
			.then(result => result.text())
			.then(result => alert(result))
			.catch((error) => error.text().then((res) => alert(res)));
			
		}
		
	}

	if (document.getElementById("welcome")) {
		const $regist = document.getElementById("welcome");
		$regist.onclick = function() {
			location.href = "/member/welcome";
		}
	}


	if (document.getElementById("login")) {
		const $login = document.getElementById("login");
		$login.onclick = function() {
			location.href = "/member/login";
		}
	}

	if (document.getElementById("logout")) {
		const $logout = document.getElementById("logout");
		$logout.onclick = function() {
			location.href = "/member/logout";
		}
	}

}
