
var prefix = "/moneyUpLoad/HeatLogUpLoad";

$(function() {
	loadType('userType','userType');
	loadType('heatType','heatType');
});


function importHeatLogExcel(){
		
			$.tzLoading({content:'正在上传暖缴费记录，请稍等...'});
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
		
			var formData = new FormData($('#HeatLogForm')[0]);

			$.ajax({
				cache : false,
				type : "POST",
				url :prefix +'/importHeatLogExcel',
				data : formData,
				async : true,
				processData: false,
				contentType: false,
			    error : function() {
			    },
			    success:function(data){

			 
                    if('success' == data){
                        alert("上传暖缴费记录信息成功!");
                       	LoadingClose();
                    }else if("fail" == data) {
                        alert("上传暖缴费记录信息失败!");
                       	LoadingClose();
                    }else{
                        alert(data);
                    }

			    }
			});
			
}




function exprotHeatLogExcel(){
	
		$.tzLoading({content:'正在导出数据，请稍等...'});
	    var createTime = $('#createTime').val();
	    var userOrg  = $('#userOrg').val();
	    var userType  = $('#userType').val();
	    var heatType  = $('#heatType').val();
	    var userId  = $('#userId').val();
	    
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
	    
	    window.location.href = prefix + '/exprotHeatLogExcel?createTime='+createTime+'&userOrg='+userOrg+'&userType='+userType+'&heatType='+heatType+'&userId='+userId;
	    	LoadingClose();
}
		

function downloadExcel(){
	var opt = "heatLog";
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


function loadType(id, dicType){
	var html = "";
	$.ajax({
		url : '/common/dict/list/'+dicType,
		success : function(data) {
			//加载数据
			for (var i = 0; i < data.length; i++) {
				html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
			}
			$("#"+id).append(html);
			$("#"+id).chosen({
				maxHeight : 200
			});
			//点击事件
			$('#'+id).on('change', function(e, params) {

				var opt = {
					query : {
						type : params.selected,
					}
				}

			});
		}
	});
}
