
var prefix = "/elec/elecInto";
$().ready(function() {
	validateRule();
});

function validateRule() {
	$("#elecIntoForm").validate({
		
	})
}

$.validator.setDefaults({
	submitHandler : function() {
		importElecIntoExcel();
	}
});


//上传
function importElecIntoExcel(){
		
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
			
		
			var formData = new FormData($('#elecIntoForm')[0]);


			$.ajax({
				cache : false,
				type : "POST",
				url :prefix +'/importElecIntoExcel',
				data : formData,
				async : true,
				processData: false,
				contentType: false,
			    error : function(xhr,type) {
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




function exprotElecIntoExcel(){
	
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
	    
	    window.location.href = prefix + '/exprotElecIntoExcel?createTime='+createTime+'&userOrg='+userOrg;
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
