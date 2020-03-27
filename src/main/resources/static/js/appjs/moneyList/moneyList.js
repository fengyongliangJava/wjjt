var prefix = "/moneyList/moneyList";

	// 点击回到顶部
	$(function(){
		
		// 点击回到顶部
		$("#top").click(function(){
			$("#likeResult").animate({
				scrollTop:0
			},1000);
		});
  		// 滑动滚动条淡入淡出
		$("#likeResult").scroll(function(){
			// 滚动条距离顶部的距离大于200px的时候
			if($("#likeResult").scrollTop() >=0){
				$("#top").fadeIn(1000); // 开始淡入
				$("#close").fadeIn(1000); // 开始淡入
			} else{
				$("#top").stop(true,true).fadeOut(1000); // 当距离小于200px时 淡出
				$("#close").stop(true,true).fadeOut(1000); // 当距离小于200px时 淡出
			}
		}); 
		
		
		
		$("#close").click(function(){
			$("#likeResult").fadeOut(1000);
			$("#close").fadeOut(1000);
			$("#top").fadeOut(1000);
		});
 
	});
	
	
	

	//查询用户  水电暖
	function queryAllUser(val){

				$.tzLoading({content:'正在查询用户，请稍等...'});
				var tbody = window.document.getElementById("content");
				//添加校验
				if(isEmpty(val)){
                    if(isNull()){
                        tbody.innerHTML = "";
                        LoadingClose();
                        return;
                    }
				}else{
                    var createTime = $('#createTime').val();
                    if(isEmpty(createTime)){
                        alert("请选择查询时间！"); 
                        LoadingClose();
                        return true;
                    }
                }
				
				
				var formData = new FormData($('#moneyForm')[0]);
				
				
			    formData.userId = $(this).data('userId');
				  
				$.ajax({
					cache : false,
					type : "POST",
					url :prefix +'/queryAllUser',
					data : formData,
					async : false,
					processData: false,
					contentType: false,
				    error : function() {
				    },
                    success:function(data){
                    	                 
                    	
                        var str = "";
                        var value ="";
                        for (i in data){
                        	if(i==0){
                                value="电费";
							}else if(i==1){
                                value="暖费";
							}else if(i==2){
                                value="水费";
							}
                            str += "<tr>" +
                                "<td class='btnmoneybox'>" +
								"<input class='btnmoney btn btn-primary' type='submit'   onclick='money("+i+",)' value='"+value+"'	 /></td>";
								//没有值的时候添加为空字符串
                            	str += "<input type='hidden' id='temp"+i+"' value="+data[i].userId+">"

                                if(data[i].userId==null){
                                    str +="<td align='center'></td>" ;
                                }
                                else{
                                    str +="<td align='center'>" + data[i].userId + "</td>" ;
                                }
                                if(data[i].userName==null){
                                    str +="<td align='center'></td>" ;
                                }
                                else{
                                    str +="<td align='center'>" + data[i].userName + "</td>" ;
                                }
                                if(data[i].userType==null){
                                    str +="<td align='center'></td>" ;
                                }
                                else{
                                    str +="<td align='center'>" + data[i].userType + "</td>" ;
                                }
                                if(data[i].userOrgName==null){
                                    str +="<td align='center'></td>" ;
                                }
                                else{
                                    str +="<td align='center'>" + data[i].userOrgName + "</td>" ;
                                }
                                if(data[i].userTell==null){
                                    str +="<td align='center'></td>" ;
                                }
                                else{
                                    str +="<td align='center'>" + data[i].userTell + "</td>" ;
                                }
                                if(data[i].userState==null){
                                	
                                	
                                    str +="<td align='center'></td>" ;
                                }
                                else{
                                    str +="<td align='center'>" + data[i].userState + "</td>" ;
                                }
                                if(data[i].moneyType==null){
                                    str +="<td align='center'></td>" ;
                                }
                                else{
                                    str +="<td align='center'>" + data[i].moneyType + "</td>" ;
                                }
                                if(data[i].userPrice==null){
                                    str +="<td align='center'></td>" ;
                                }
                                else{
                                    str +="<td align='center'>" + data[i].userPrice + "</td>" ;
                                }
                                if(data[i].userArea==null){
                                    str +="<td align='center'></td>" ;
                                }
                                else{
                                    str +="<td align='center'>" + data[i].userArea + "</td>" ;
                                }
                                if(data[i].userCost==null){
                                    str +="<td align='center'></td>" ;
                                }
                                else{
                                    str +="<td align='center'>" + data[i].userCost + "</td>" ;
                                }
                                if(data[i].userSum==null){
                                    str +="<td align='center'></td>" ;
                                }
                                else{
                                    str +="<td align='center'>" + data[i].userSum + "</td>" ;
                                }


                        }
                        
                        
                      
                        
                        tbody.innerHTML = "";
                        tbody.innerHTML = str;
                        LoadingClose();
                    }
				})
		}

	//输入框触发事件
	function money(val){
		var userNo = $("#temp" + val).val();
        $.tzDialog({title:"请输入缴费金额！",content:"<input id='money' name = 'money'  type='text' value='' maxlength='10' placeholder='请输入数字！' />",callback:function(ok){
            if(ok){
                var obj ={};
                var money  = document.getElementById('money').value;
                var createTime = document.getElementById('createTime').value;
                var id = val;
                var userOrg = document.getElementById('userOrg').value;
                var userId = document.getElementById('userId').value;
                var userName = document.getElementById('userName').value;
                obj.userType = "A";
                if($('#wx').is(':checked')) {
                    var userType="C";
                    obj.userType = userType;
                }
                if($('#yh').is(':checked')) {
                    var userType="D";
                    obj.userType = userType;
                }
                if(isEmpty(money)){
                    alert("请输入缴费金额！");
                    return ;
                }
				if(!isNumber(money)){
                    alert("请输入正确数字");
                    return ;
				}
                obj.money = money;
                obj.id = id;
                obj.createTime = createTime;
                obj.userOrg = userOrg;
                obj.userId = userNo;
                obj.userName = userName;
			//提交都后台,更新数据
                $.ajax({
                    type:'post',
                    url:prefix +'/updateMoney',
                    data: obj,
                    async:true,
                    success:function(data){
                        if('0' == id){
                            alert("电费缴费成功!");
                            queryAllUser();
                            moneyMsg(data,userNo);
                        }else if('1' == id){
                            alert("暖费缴费成功!");
                            queryAllUser();
                            moneyMsg(data,userNo);
                        }else if('2' == id){
                            alert("水费缴费成功!");
                            queryAllUser();
                            moneyMsg(data,userNo);
                        }else{
                            alert("缴费失败!");
                        }
                    }
                });

            }

        }});

    };

    
    
    
    function moneyMsg(val,userNo){
        var id = val;
 
        layer.msg('请选择流程!', {
            time: 0 
            ,btn: ['票据打印', '继续缴费']
            ,yes: function(index){
              layer.close(index);
                layer.open({
                    type : 2,
                    title : '编辑',
                    maxmin : true,
                    shadeClose : false, // 点击遮罩关闭层
                    area : [ '800px', '520px' ],
                    content : prefix + '/moneyPrint/'+id + '/' +userNo
                });


            }
          });
    }
    
    
    
	//校验只允许输入数字
	function isNumber(val){
		/*var regPos = /^\d+(\.\d+)?$/; //非负浮点数 */
		
		var regPos = /^(-)?\d+(\.\d+)?$/;
        var regNeg = /^(-(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*)))$/; //负浮点数
        
        if(regPos.test(val)) {
            return true;
        } else {
            return false;
        }
}

	//用户模糊查询
	function queryUserName(){
        var formData = new FormData($('#moneyForm')[0]);
        var ul = document.getElementById("likeResult");
        
        
        
        $.ajax({
            cache : false,
            type : "POST",
            url :prefix +'/queryAllUserName',
            data : formData,
            async : false,
            processData: false,
            contentType: false,
            error : function() {
            },
            success:function(data){
                var str = "";
            	//成功
                for (i in data){
                    str += "<li>" + data[i].userName + "</li>";
				}
                ul.innerHTML = "";
                ul.innerHTML = str;
                //获取所有li的节点
                var list = document.getElementsByTagName("li");
				//给每个li绑定事件
                for(var i = 0;i<list.length;i++){

                    list[i].onclick = function(){
					//弹出对应的li节点里面的内容
                        var username =this.innerHTML;
                        $("#userName").val(username);
                        ul.innerHTML = "";
						//通过名字获取相应数据
                        queryAllUser(username);

                    }
                }
            }
        });

	}
	
	
	
	
	//不为空的校验
	function isNull(){
        var createTime = $('#createTime').val();
        var userOrg  = $('#userOrg').val();
        
        if(isEmpty(createTime)){
            alert("请选择查询时间！");
            return true;
        }
        if(isEmpty(userOrg)){
            alert("请选择用户地区！");
            return true;
        }

	}


	function openDept(){
		layer.open({
			type:2,
			title:"选择组织",
			area : [ '300px', '450px' ],
			content:"/system/sysDept/treeView"
		})
	}
	
	function loadDept( deptId,deptName){
		$("#userOrg").val(deptId);
		$("#userOrgName").val(deptName);
	}

