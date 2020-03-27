var prefix = "/water/waterUpLoad";

//水费上传
function importWaterExcel(){
		
	$.tzLoading({content:'正在上传水费用户信息，请稍等...'});
	
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

		var formData = new FormData($('#waterUpLoadForm')[0]);
		$.ajax({
			cache : false,
			type : "POST",
			url :prefix +'/importWaterExcel',
			data : formData,
			async : true,
			processData: false,
			contentType: false,
		    error : function() {
		    },
			success:function(data){
				if('success' == data){
					alert("上传水费用户信息成功!");
					LoadingClose();
				}else{
                    alert("上传水费用户信息失败");
                	LoadingClose();
                }
			}
		});
  }
			
	
//水费生成
function createWaterExcel(){

	$.tzLoading({content:'正在生成水费用户信息，请稍等...'});
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
		
		 var formData = new FormData($('#waterUpLoadForm')[0]);
		$.ajax({

			cache : false,
			type : "POST",
			url :prefix +'/createWaterExcel',
			data : formData,
			async : true,
			processData: false,
			contentType: false,
		    error : function() {
		    },
			success:function(data){
			
				if("success" == data){
					alert("生成水费用户信息成功!");
					LoadingClose();
				}else if("fail" == data) {
					alert("该月水费用户信息已生成!请点击更换日期");
					LoadingClose();
				}else{
                    alert("生成水费用户信息失败");
                	LoadingClose();
				}
			}
		});
  }



function exprotWaterExcel(){
	
	$.tzLoading({content:'正在导出水费用户数据，请稍等...'});
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
	
	
	window.location.href = prefix +'/exprotWaterExcel?createTime='+createTime+'&userOrg='+userOrg;
	LoadingClose();
}
			



function downloadExcel(){
	var opt = "waterUser";
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


