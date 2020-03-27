var prefix = "/elec/elecUpLoad";

function validate(){
	
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

	var excelFile = $('#excelFile').val();
	if(isEmpty(excelFile)){
		alert("请选择上传Excel文件！");
		LoadingClose();
		return;
	}
	
}



function importElecExcel(){
		
	$.tzLoading({content:'正在上传电字信息，请稍等...'});
	
		validate();

		var formData = new FormData($('#elecUpLoadForm')[0]);
		$.ajax({
			cache : false,
			type : "POST",
			url :prefix +'/importElecExcel',
			data : formData,
			async : true,
			processData: false,
			contentType: false,
		    error : function() {
		    },
			success:function(data){
				
				if('success'== data.result){
					alert("电字上传成功!");
					LoadingClose();
				}else if('fail' == data.result){
                    alert("电字上传失败!");
                	LoadingClose();
                }else if('notExcel' == data.result){
                    alert("不是Excel文件!");
                	LoadingClose();
                }else{
                    alert("第"+data.result+"行数据不匹配!");
                	LoadingClose();
                }
			}
		});
  }




function importElecUser(){
		
	$.tzLoading({content:'正在上传电费用户信息，请稍等...'});
	
		validate();

		var formData = new FormData($('#elecUpLoadForm')[0]);
		$.ajax({
			cache : false,
			type : "POST",
			url :prefix +'/importElecUser',
			data : formData,
			async : true,
			processData: false,
			contentType: false,
		    error : function() {
		    },
			success:function(data){
				
				if('success' == data){
					alert("电费用户信息上传成功!");
					LoadingClose();
				}else if("fail" == data) {
                    alert("电费用户信息上传失败!");
                    LoadingClose();
                }else{
                    alert(data);
                }
			}
		});
  }




function exprotElecExcel(){
	
	$.tzLoading({content:'正在导出电费用户数据，请稍等...'});
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
	
	window.location.href = prefix +'/exprotElecExcel?createTime='+createTime+'&userOrg='+userOrg;
	LoadingClose();
}



function downloadElec(){
	var opt = "elecUpload";
	$.tzLoading({content:'正在下载模板，请稍等...'});
	window.open('/file/downImport/'+ opt,'_blank'); 
	LoadingClose();
	
}

function downloadUser(){
	var opt = "elecUser";
	$.tzLoading({content:'正在下载模板，请稍等...'});
	window.open('/file/downImport/'+ opt,'_blank'); 
	LoadingClose();
	
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
