//bStrength.js
jQuery(function(){
	var n = $('.beanBody .bean .beanDetail1').length;
	var cols = 4;	//칸 수
	var rows = Math.ceil / cols;	//줄 수
	var winW = $(window).innerWidth();	
	var imgW = winW / cols;
	var imgH = imgW;
	var z = 0;		//버튼0번  -  눌른번호
	var hide = []; //사라질 이미지 배열번호
	var show = [0,1,2,3,4,5,6,7]; //보여질 이미지 배열번호
	
		//탭메뉴 버튼클릭 이벤트
		$('.sec4Bt').eq(0).on({		//all
			click: function(){
				z = 0;	//버튼번호(인덱스)
				hide = [];	//감춰질(hide) 배열
				show = [0,1,2,3,4,5,6,7];	//보여질(show) 배열
				beanBodyFn();
			}
		});
		
		$('.sec4Bt').eq(1).on({		//5개 이미지
			click: function(){
				z = 1;
				hide = [1,5,7];
				show = [0,2,3,4,6];
				n = show.length;		//이미지의 움직임을 빠르게 하기위해서 쓰는 것!!! 불러오는게 빠름.
				beanBodyFn();
			}
		});	
	
		$('.sec4Bt').eq(2).on({		//5개 이미지
			click: function(){
				z = 2;
				hide = [2,5,6];
				show = [0,1,3,4,7];
				n = show.length;
				beanBodyFn();
			}
		});	
		
		$('.sec4Bt').eq(3).on({		//5개 이미지
			click: function(){
				z = 3;
				hide = [0,3,6];
				show = [1,2,4,5,7];
				n = show.length;
				beanBodyFn();
			}
		});				
	
        beanBodyFn();
	setTimeout(beanBodyFn, 100);
	
	$(window).resize(function(){
		beanBodyFn();
	});
	
	function beanBodyFn(){
		
		var winW = $(window).innerWidth();
		
			if( winW > 1200 ){
			 	cols = 4;
			 }
			else if( winW > 900 ){
			 	cols = 3;
			 }			
			else if( winW > 600 ){
			 	cols = 2;
			 }	
			else{
			 	cols = 1;
			 }
			
			rows = Math.ceil(n/cols);
			imgW = winW/cols;
			imgH = imgW;
			
			console.log( rows );
			$('.beanBody .bean').stop().animate({ height: imgH*rows });
			
			$('.beanBody .beanDetail1').removeClass('addZoom');

			
			
			if( cols==4 ){
				
				if( z==0 ){
					hide = [];	
					show = [0,1,2,3,4,5,6,7];
					n = 8;
					rows = Math.ceil(n/cols);
					
					$('.beanBody .beanDetail1').eq(0).show().stop().animate({ top:(imgH*0), left:(imgW*0), width:imgW, height:imgH },300);
					$('.beanBody .beanDetail1').eq(1).show().stop().animate({ top:(imgH*0), left:(imgW*1), width:imgW, height:imgH },300);
					$('.beanBody .beanDetail1').eq(2).show().stop().animate({ top:(imgH*0), left:(imgW*2), width:imgW, height:imgH },300);
					$('.beanBody .beanDetail1').eq(3).show().stop().animate({ top:(imgH*0), left:(imgW*3), width:imgW, height:imgH },300);
					
					$('.beanBody .beanDetail1').eq(4).show().stop().animate({ top:(imgH*1), left:(imgW*0), width:imgW, height:imgH },300);
					$('.beanBody .beanDetail1').eq(5).show().stop().animate({ top:(imgH*1), left:(imgW*1), width:imgW, height:imgH },300);
					$('.beanBody .beanDetail1').eq(6).show().stop().animate({ top:(imgH*1), left:(imgW*2), width:imgW, height:imgH },300);
					$('.beanBody .beanDetail1').eq(7).show().stop().animate({ top:(imgH*1), left:(imgW*3), width:imgW, height:imgH },300);					
				}
				
				else if( z==1 ){
					hide = [1,5,7];
					show = [0,2,3,4,6];	
					n = 5;
					rows = Math.ceil(n/cols);
					
					$('.beanBody .beanDetail1').eq(1).hide();
					$('.beanBody .beanDetail1').eq(5).hide();
					$('.beanBody .beanDetail1').eq(7).hide();	
					
					$('.beanBody .beanDetail1').eq(0).show().stop().animate({ top:(imgH*0), left:(imgW*0), width:imgW, height:imgH },300);
					$('.beanBody .beanDetail1').eq(2).show().stop().animate({ top:(imgH*0), left:(imgW*1), width:imgW, height:imgH },300);
					$('.beanBody .beanDetail1').eq(3).show().stop().animate({ top:(imgH*0), left:(imgW*2), width:imgW, height:imgH },300);
					$('.beanBody .beanDetail1').eq(4).show().stop().animate({ top:(imgH*0), left:(imgW*3), width:imgW, height:imgH },300);
					$('.beanBody .beanDetail1').eq(6).show().stop().animate({ top:(imgH*1), left:(imgW*0), width:imgW, height:imgH },300);				
				}
				
				else if( z==2 ){
					hide = [2,5,6];
					show = [0,1,3,4,7];	
					n = 5;
					rows = Math.ceil(n/cols);
					
					$('.beanBody .beanDetail1').eq(2).hide();
					$('.beanBody .beanDetail1').eq(5).hide();
					$('.beanBody .beanDetail1').eq(6).hide();	
					
					$('.beanBody .beanDetail1').eq(0).show().stop().animate({ top:(imgH*0), left:(imgW*0), width:imgW, height:imgH },300);
					$('.beanBody .beanDetail1').eq(1).show().stop().animate({ top:(imgH*0), left:(imgW*1), width:imgW, height:imgH },300);
					$('.beanBody .beanDetail1').eq(3).show().stop().animate({ top:(imgH*0), left:(imgW*2), width:imgW, height:imgH },300);
					$('.beanBody .beanDetail1').eq(4).show().stop().animate({ top:(imgH*0), left:(imgW*3), width:imgW, height:imgH },300);
					$('.beanBody .beanDetail1').eq(7).show().stop().animate({ top:(imgH*1), left:(imgW*0), width:imgW, height:imgH },300);					
				}	
				
				else if( z==3 ){
					hide = [0,3,6];
					show = [1,2,4,5,7];	
					n = 5;
					rows = Math.ceil(n/cols);					
					
					$('.beanBody .beanDetail1').eq(0).hide();
					$('.beanBody .beanDetail1').eq(3).hide();
					$('.beanBody .beanDetail1').eq(6).hide();	
					
					$('.beanBody .beanDetail1').eq(1).show().stop().animate({ top:(imgH*0), left:(imgW*0), width:imgW, height:imgH },300);
					$('.beanBody .beanDetail1').eq(2).show().stop().animate({ top:(imgH*0), left:(imgW*1), width:imgW, height:imgH },300);
					$('.beanBody .beanDetail1').eq(4).show().stop().animate({ top:(imgH*0), left:(imgW*2), width:imgW, height:imgH },300);
					$('.beanBody .beanDetail1').eq(5).show().stop().animate({ top:(imgH*0), left:(imgW*3), width:imgW, height:imgH },300);
					$('.beanBody .beanDetail1').eq(7).show().stop().animate({ top:(imgH*1), left:(imgW*0), width:imgW, height:imgH },300);						
				}	
				
				else if( z==4 ){
					hide = [0,2,7];
					show = [1,3,4,5,6];
					n = 5;
					rows = Math.ceil(n/cols);					
					
					$('.beanBody .beanDetail1').eq(0).hide();
					$('.beanBody .beanDetail1').eq(2).hide();
					$('.beanBody .beanDetail1').eq(7).hide();	
					
					$('.beanBody .beanDetail1').eq(1).show().stop().animate({ top:(imgH*0), left:(imgW*0), width:imgW, height:imgH },300);
					$('.beanBody .beanDetail1').eq(3).show().stop().animate({ top:(imgH*0), left:(imgW*1), width:imgW, height:imgH },300);
					$('.beanBody .beanDetail1').eq(4).show().stop().animate({ top:(imgH*0), left:(imgW*2), width:imgW, height:imgH },300);
					$('.beanBody .beanDetail1').eq(5).show().stop().animate({ top:(imgH*0), left:(imgW*3), width:imgW, height:imgH },300);
					$('.beanBody .beanDetail1').eq(6).show().stop().animate({ top:(imgH*1), left:(imgW*0), width:imgW, height:imgH },300);						
				}				

			}
			else if( cols==3 ){
				
				if( z==0 ){
					hide = [];	
					show = [0,1,2,3,4,5,6,7];		
					n = 8;
					rows = Math.ceil(n/cols);					
					
					$('.beanBody .beanDetail1').eq(0).show().stop().animate({ top:(imgH*0), left:(imgW*0), width:imgW, height:imgH },300);
					$('.beanBody .beanDetail1').eq(1).show().stop().animate({ top:(imgH*0), left:(imgW*1), width:imgW, height:imgH },300);
					$('.beanBody .beanDetail1').eq(2).show().stop().animate({ top:(imgH*0), left:(imgW*2), width:imgW, height:imgH },300);
					
					$('.beanBody .beanDetail1').eq(3).show().stop().animate({ top:(imgH*1), left:(imgW*0), width:imgW, height:imgH },300);
					$('.beanBody .beanDetail1').eq(4).show().stop().animate({ top:(imgH*1), left:(imgW*1), width:imgW, height:imgH },300);
					$('.beanBody .beanDetail1').eq(5).show().stop().animate({ top:(imgH*1), left:(imgW*2), width:imgW, height:imgH },300);
					
					$('.beanBody .beanDetail1').eq(6).show().stop().animate({ top:(imgH*2), left:(imgW*0), width:imgW, height:imgH },300);
					$('.beanBody .beanDetail1').eq(7).show().stop().animate({ top:(imgH*2), left:(imgW*1), width:imgW, height:imgH },300);				
						
				}
				if( z==1 ){
					hide = [1,5,7];
					show = [0,2,3,4,6];	
					n = 5;
					rows = Math.ceil(n/cols);					
					
					$('.beanBody .beanDetail1').eq(1).hide()
					$('.beanBody .beanDetail1').eq(5).hide()
					$('.beanBody .beanDetail1').eq(7).hide()
					
					$('.beanBody .beanDetail1').eq(0).show().stop().animate({ top:(imgH*0), left:(imgW*0), width:imgW, height:imgH },300);
					$('.beanBody .beanDetail1').eq(2).show().stop().animate({ top:(imgH*0), left:(imgW*1), width:imgW, height:imgH },300);
					$('.beanBody .beanDetail1').eq(3).show().stop().animate({ top:(imgH*0), left:(imgW*2), width:imgW, height:imgH },300);
					$('.beanBody .beanDetail1').eq(4).show().stop().animate({ top:(imgH*1), left:(imgW*0), width:imgW, height:imgH },300);
					$('.beanBody .beanDetail1').eq(6).show().stop().animate({ top:(imgH*1), left:(imgW*1), width:imgW, height:imgH },300);					
					
				}				
				if( z==2 ){
					hide = [2,5,6];
					show = [0,1,3,4,7];		
					n = 5;
					rows = Math.ceil(n/cols);					
					
					$('.beanBody .beanDetail1').eq(2).hide()
					$('.beanBody .beanDetail1').eq(5).hide()
					$('.beanBody .beanDetail1').eq(6).hide()
					
					$('.beanBody .beanDetail1').eq(0).show().stop().animate({ top:(imgH*0), left:(imgW*0), width:imgW, height:imgH },300);
					$('.beanBody .beanDetail1').eq(1).show().stop().animate({ top:(imgH*0), left:(imgW*1), width:imgW, height:imgH },300);
					$('.beanBody .beanDetail1').eq(3).show().stop().animate({ top:(imgH*0), left:(imgW*2), width:imgW, height:imgH },300);
					$('.beanBody .beanDetail1').eq(4).show().stop().animate({ top:(imgH*1), left:(imgW*0), width:imgW, height:imgH },300);
					$('.beanBody .beanDetail1').eq(7).show().stop().animate({ top:(imgH*1), left:(imgW*1), width:imgW, height:imgH },300);					
					
				}
				if( z==3 ){
					hide = [0,3,6];
					show = [1,2,4,5,7];	
					n = 5;
					rows = Math.ceil(n/cols);					
					
					$('.beanBody .beanDetail1').eq(0).hide()
					$('.beanBody .beanDetail1').eq(3).hide()
					$('.beanBody .beanDetail1').eq(6).hide()
					
					$('.beanBody .beanDetail1').eq(1).show().stop().animate({ top:(imgH*0), left:(imgW*0), width:imgW, height:imgH },300);
					$('.beanBody .beanDetail1').eq(2).show().stop().animate({ top:(imgH*0), left:(imgW*1), width:imgW, height:imgH },300);
					$('.beanBody .beanDetail1').eq(4).show().stop().animate({ top:(imgH*0), left:(imgW*2), width:imgW, height:imgH },300);
					$('.beanBody .beanDetail1').eq(5).show().stop().animate({ top:(imgH*1), left:(imgW*0), width:imgW, height:imgH },300);
					$('.beanBody .beanDetail1').eq(7).show().stop().animate({ top:(imgH*1), left:(imgW*1), width:imgW, height:imgH },300);					
					
				}
				if( z==4 ){
					hide = [0,2,7];
					show = [1,3,4,5,6];	
					n = 5;
					rows = Math.ceil(n/cols);					
					
					$('.beanBody .beanDetail1').eq(0).hide()
					$('.beanBody .beanDetail1').eq(2).hide()
					$('.beanBody .beanDetail1').eq(7).hide()
					
					$('.beanBody .beanDetail1').eq(1).show().stop().animate({ top:(imgH*0), left:(imgW*0), width:imgW, height:imgH },300);
					$('.beanBody .beanDetail1').eq(3).show().stop().animate({ top:(imgH*0), left:(imgW*1), width:imgW, height:imgH },300);
					$('.beanBody .beanDetail1').eq(4).show().stop().animate({ top:(imgH*0), left:(imgW*2), width:imgW, height:imgH },300);
					$('.beanBody .beanDetail1').eq(5).show().stop().animate({ top:(imgH*1), left:(imgW*0), width:imgW, height:imgH },300);
					$('.beanBody .beanDetail1').eq(6).show().stop().animate({ top:(imgH*1), left:(imgW*1), width:imgW, height:imgH },300);					
					
				}				
				

			}			
			if( cols==2 ){
				$('.beanBody .beanDetail1').eq(0).stop().animate({ top:(imgH*0), left:(imgW*0), width:imgW, height:imgH },300);
				$('.beanBody .beanDetail1').eq(1).stop().animate({ top:(imgH*0), left:(imgW*1), width:imgW, height:imgH },300);
				
				$('.beanBody .beanDetail1').eq(2).stop().animate({ top:(imgH*1), left:(imgW*0), width:imgW, height:imgH },300);
				$('.beanBody .beanDetail1').eq(3).stop().animate({ top:(imgH*1), left:(imgW*1), width:imgW, height:imgH },300);
				
				$('.beanBody .beanDetail1').eq(4).stop().animate({ top:(imgH*2), left:(imgW*0), width:imgW, height:imgH },300);
				$('.beanBody .beanDetail1').eq(5).stop().animate({ top:(imgH*2), left:(imgW*1), width:imgW, height:imgH },300);
				
				$('.beanBody .beanDetail1').eq(6).stop().animate({ top:(imgH*3), left:(imgW*0), width:imgW, height:imgH },300);
				$('.beanBody .beanDetail1').eq(7).stop().animate({ top:(imgH*3), left:(imgW*1), width:imgW, height:imgH },300);
			}
			if( cols==1 ){
				$('.beanBody .beanDetail1').eq(0).stop().animate({ top:(imgH*0), left:(imgW*0), width:imgW, height:imgH },300);
				$('.beanBody .beanDetail1').eq(1).stop().animate({ top:(imgH*1), left:(imgW*0), width:imgW, height:imgH },300);
				$('.beanBody .beanDetail1').eq(2).stop().animate({ top:(imgH*2), left:(imgW*0), width:imgW, height:imgH },300);
				$('.beanBody .beanDetail1').eq(3).stop().animate({ top:(imgH*3), left:(imgW*0), width:imgW, height:imgH },300);
				$('.beanBody .beanDetail1').eq(4).stop().animate({ top:(imgH*4), left:(imgW*0), width:imgW, height:imgH },300);
				$('.beanBody .beanDetail1').eq(5).stop().animate({ top:(imgH*5), left:(imgW*0), width:imgW, height:imgH },300);
				$('.beanBody .beanDetail1').eq(6).stop().animate({ top:(imgH*6), left:(imgW*0), width:imgW, height:imgH },300);
				$('.beanBody .beanDetail1').eq(7).stop().animate({ top:(imgH*7), left:(imgW*0), width:imgW, height:imgH },300);
			}	
			
			$('.beanBody .beanDetail1').addClass('addZoom');


			
	}  
	
	
});

