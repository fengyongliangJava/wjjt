
var resultDom = document.getElementById("result");

// 操作符锁
var operate = true; 
var xop = true;

// 点击计算器键盘执行操作
function command(num){
	var str = resultDom.value;
	// 如果是0 就用“”填充
	str = (str=="0"?"":str);
	// 拼接点击的数字
	str += num;
	// 赋给文本框
	resultDom.value = str;
	// 锁住操作符
	operate = true;

	// 播放音效
	play(num);
}

// 点击操作符
function tools(op,m){
	if(operate){
		var num = resultDom.value;
		num = (num == "0"?"":num);
		// 拼接操作符
		resultDom.value = num + op;
		operate = false;
		xop = true;
	}
	// 播放音效
	play(m);

}

// 点击小数点
function dot(m){
	if(xop){
		var num = resultDom.value;
		num  = num + ".";
		resultDom.value = num;
		xop = false;
	}
	// 播放音效
	play(m);
}


// 计算等于
function equal(m){
	play(m);
	var result = resultDom.value;
	//
	var r = eval(result);
	resultDom.value = r;
	operate = true;
	var r = resultDom.value + "";
	xop = r.indexOf(".") == -1 ? true:false;
}

// 清空
function clearzero(m){
	resultDom.value = 0;
	operate = true;
	xop = true;
	play(m);
}


// 播放键盘声音
function play(num){
	var boxDom = document.getElementById("audioBox");
	boxDom.innerHTML = "<embed src='/wav/"+num+".wav' width='0' height='0'></embed>";
}



  function bofang(){
	  
	  var boxDom = document.getElementById("audioBox");
		if(boxDom.paused){
			boxDom.play();
		} else {
			return null;
		}
  }
