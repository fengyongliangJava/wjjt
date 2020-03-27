
var prefix = "/heat/heatInto";

//上传
function importHeatIntoExcel(){
		
	$.tzLoading({content:'正在工资代扣，请稍等...'});
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
	
	var formData = new FormData($('#heatIntoForm')[0]);


	$.ajax({
		cache : false,
		type : "POST",
		url :prefix +'/importHeatIntoExcel',
		data : formData,
		async : true,
		processData: false,
		contentType: false,
	    error : function() {
	    },
	    success:function(data){

           if('success' == data){
               alert("上传工资代扣信息成功!");
           }else if("fail" == data) {
               alert("上传工资代扣信息失败!");
           }else{
               alert(data);
           }
         	LoadingClose();
	    }
	});
	
}




 //导出
function exprotHeatIntoExcel(){
	
		$.tzLoading({content:'正在导出数据，请稍等...'});
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
	    
	    window.location.href = prefix + '/exprotHeatIntoExcel?createTime='+createTime+'&userOrg='+userOrg;
	    	LoadingClose();
}
		



function downloadExcel(){
	var opt = "userInto";
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

