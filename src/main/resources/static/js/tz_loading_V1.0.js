
	$.tzLoading = function(opts){
		var animate_r = tz_animateIn();
		var $loading = $('<div class="tz_loading '+animate_r+'"><img src="../img/load3.gif"/>'+opts.content+'</div>');
		$("body").append($loading);
		if(opts.width){$loading.width(opts.width);}
		if(opts.height){$loading.height(opts.height);}
		tz_center_loading($loading);
		initEvent($loading);
		
	}
	
	// 关闭
	function LoadingClose(){
		var animateOut = tz_animateOut();
		// 删除原有的动画，添加淡出的动画效果
		$(".tz_loading").removeClass("animated").addClass(animateOut);
		// 关闭层
		$(".tz_loading").fadeOut(500,function(){$(this).remove();});
	}
	
	
	
	//计算高度宽度居中
	function tz_center_loading($loading){
		var width = $loading.width();
		var height = $loading.height(); 
		var ww = $(window).width(); 
		var hh = $(window).height(); 
		var left = (ww - width) / 2;
		var top = (hh - height) / 2;
		$loading.css({top:top,left:left}); 
	}
	//根据浏览器居中 
	function initEvent($loading){
		$(window).resize(function(){
			tz_center_loading($loading);
		});
	}
	
	
	// 触发加载tz_dialog插件，随机动画
	function tz_animateIn(index){
		var animateIn = [];
		animateIn.push("animated bounce");//0
		animateIn.push("animated tada");//1
		animateIn.push("animated swing");//2
		animateIn.push("animated wobble");//3
		animateIn.push("animated flip");//4
		animateIn.push("animated flipInX");//5
		animateIn.push("animated flipInY");//6
		animateIn.push("animated fadeIn");//7
		animateIn.push("animated fadeInUp");//8
		animateIn.push("animated fadeInDown");//9
		animateIn.push("animated fadeInLeft");//10
		animateIn.push("animated fadeInRight");//11
		animateIn.push("animated fadeInUpBig");//12
		animateIn.push("animated fadeInDownBig");//13
		animateIn.push("animated fadeInLeftBig");//14
		animateIn.push("animated fadeInRightBig");//15
		animateIn.push("animated bounceIn");//16
		animateIn.push("animated bounceInUp");//17
		animateIn.push("animated bounceInDown");//18
		animateIn.push("animated bounceInLeft");//19
		animateIn.push("animated bounceInRight");//20
		animateIn.push("animated rotateIn");//21
		animateIn.push("animated rotateInUpLeft");//22
		animateIn.push("animated rotateInDownLeft");//23
		animateIn.push("animated rotateInUpRight");//24
		animateIn.push("animated rotateInDownRight");//25
		animateIn.push("animated rollIn");//26
		if(!index){
			var len = animateIn.length;
			var r = Math.floor(Math.random()*(len - 1)+1);
			return animateIn[r];
		} else {
			return animateIn[index];
		}
	}

	// 动画消失
	function tz_animateOut(index){
		var animateOut = [];
		animateOut.push("animated fadeOut");//2
		animateOut.push("animated fadeOutUp");//3
		animateOut.push("animated fadeOutDown");//4
		animateOut.push("animated fadeOutLeft");//5
		animateOut.push("animated fadeOutRight");//6
		animateOut.push("animated fadeOutUpBig");//7
		animateOut.push("animated fadeOutDownBig");//8
		animateOut.push("animated fadeOutLeftBig");//9
		animateOut.push("animated fadeOutRightBig");//10
		animateOut.push("animated bounceOut");//11
		animateOut.push("animated bounceOutUp");//12
		animateOut.push("animated bounceOutDown");//13
		animateOut.push("animated bounceOutLeft");//14
		animateOut.push("animated bounceOutRight");//15
		animateOut.push("animated rotateOut");//16
		animateOut.push("animated rotateOutUpLeft");//17
		animateOut.push("animated rotateOutDownLeft");//18
		animateOut.push("animated rotateOutDownRight");//19
		animateOut.push("animated rollOut");//21

		if(!index){
			var len = animateOut.length;
			var r = Math.floor(Math.random()*(len-1)+1);
			return animateOut[r];
		} else {
			return animateOut[index];
		}
	}