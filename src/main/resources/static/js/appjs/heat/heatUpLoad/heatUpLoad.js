var prefix = "/heat/heatUpLoad";

//暖费上传
function importHeatExcel(){
		
	$.tzLoading({content:'正在上传暖费用户信息，请稍等...'});
	
		 var createTime = $('#createTime').val();
		 var userOrg  = $('#userOrg').val();

		if(isEmpty(createTime)){
			alert("请选择上传时间！");
			LoadingClose();
			return;
		} 
		if(isEmpty(userOrg)){
			alert("请选择用户地区！");
			LoadingClose();
			return;
		} 
		
	    var file = $('#excelFile').val();
		if(isEmpty(file)){
			alert("请选择上传Excel文件！");
			LoadingClose();
			return;
		}

		var formData = new FormData($('#heatUpLoadForm')[0]);
		$.ajax({
			cache : false,
			type : "POST",
			url :prefix +'/importHeatExcel',
			data : formData,
			async : true,
			processData: false,
			contentType: false,
		    error : function() {
		    },
			success:function(data){

				if('success' == data){
					alert("上传暖费用户信息成功!");
					LoadingClose();
				}else{
                    alert("上传暖费用户信息失败");
    				LoadingClose();
                }
			}
		});
  }
			
	
//暖费生成
function createHeatExcel(){

	$.tzLoading({content:'正在生成暖费用户信息，请稍等...'});
		 var createTime = $('#createTime').val();
		 var userOrg  = $('#userOrg').val();
		 
		if(isEmpty(createTime)){
			alert("请选择生成时间！");
			LoadingClose();
			return;
		}
		if(isEmpty(userOrg)){
			alert("请选择用户地区！");
			LoadingClose();
			return;
		} 
		
		 var formData = new FormData($('#heatUpLoadForm')[0]);
		$.ajax({

			cache : false,
			type : "POST",
			url :prefix +'/createHeatExcel',
			data : formData,
			async : true,
			processData: false,
			contentType: false,
		    error : function() {
		    },
			success:function(data){
				
				if("success" == data){
					alert("生成暖费用户信息成功!");
					LoadingClose();
				}else if("fail" == data) {
					alert("该月暖费用户信息已生成!请点击更换日期");
					LoadingClose();
				}else{
                    alert("生成暖费用户信息失败");
                    LoadingClose();
				}
			}
		});
  }





//下月暖费
function nextHeatExcel(){

	$.tzLoading({content:'正在生成下月暖费用户信息，请稍等...'});
		 var createTime = $('#createTime').val();
		 var userOrg  = $('#userOrg').val();
		 
		if(isEmpty(createTime)){
			alert("请选择生成时间！");
			LoadingClose();
			return;
		}
		if(isEmpty(userOrg)){
			alert("请选择用户地区！");
			LoadingClose();
			return;
		} 
		
		 var formData = new FormData($('#heatUpLoadForm')[0]);
		$.ajax({

			cache : false,
			type : "POST",
			url :prefix +'/nextHeatExcel',
			data : formData,
			async : true,
			processData: false,
			contentType: false,
		    error : function() {
		    },
			success:function(data){
				
				if("success" == data){
					alert("生成暖费用户信息成功!");
					LoadingClose();
				}else if("fail" == data) {
					alert("该月暖费用户信息已生成!请点击更换日期");
					LoadingClose();
				}else{
                  alert("生成暖费用户信息失败");
                  LoadingClose();
				}
			}
		});
}


//半月暖费
function halfHeatExcel(){

	$.tzLoading({content:'正在生成半月暖费用户信息，请稍等...'});
		 var createTime = $('#createTime').val();
		 var userOrg  = $('#userOrg').val();
		 
		if(isEmpty(createTime)){
			alert("请选择生成时间！");
			LoadingClose();
			return;
		}
		if(isEmpty(userOrg)){
			alert("请选择用户地区！");
			LoadingClose();
			return;
		} 
		
		 var formData = new FormData($('#heatUpLoadForm')[0]);
		$.ajax({

			cache : false,
			type : "POST",
			url :prefix +'/halfHeatExcel',
			data : formData,
			async : true,
			processData: false,
			contentType: false,
		    error : function() {
		    },
			success:function(data){
				
				if("success" == data){
					alert("生成暖费用户信息成功!");
					LoadingClose();
				}else if("fail" == data) {
					alert("该月暖费用户信息已生成!请点击更换日期");
					LoadingClose();
				}else{
                alert("生成暖费用户信息失败");
                LoadingClose();
				}
			}
		});
}



function exprotHeatExcel(){
	
	$.tzLoading({content:'正在导出暖费用户数据，请稍等...'});
	var createTime = $('#createTime').val();
    var userOrg  = $('#userOrg').val();
	
	if(isEmpty(createTime)){
		alert("请选择导出时间！");
		LoadingClose();
		return;
	} 
	if(isEmpty(userOrg)){
		alert("请选择用户地区！");
		LoadingClose();
		return;
	} 
	
	window.location.href = prefix +'/exprotHeatExcel?createTime='+createTime+'&userOrg='+userOrg;
	LoadingClose();
}
			

function downloadExcel(){
	var opt = "heatUser";
	$.tzLoading({content:'正在下载模板，请稍等...'});
	window.open('/file/downImport/'+ opt,'_blank'); 
	LoadingClose();
}


function openDept(){
	layer.open({
		type:2,
		title:"选择地区",
		area : [ '300px', '450px' ],
		content:"/system/sysDept/treeView"
	})
}
function loadDept( deptId,deptName){
	$("#userOrg").val(deptId);
	$("#userOrgName").val(deptName);
}


