
var prefix = "/moneyUpLoad/ElecLogUpLoad";

$(function() {
	loadType('userType','userType');
	loadType('elecType','elecType');
});

//上传
function importElecLogExcel(){
		
			$.tzLoading({content:'正在上传电缴费记录，请稍等...'});
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
			
			
		
			var formData = new FormData($('#ElecLogForm')[0]);


			$.ajax({
				cache : false,
				type : "POST",
				url :prefix +'/importElecLogExcel',
				data : formData,
				async : true,
				processData: false,
				contentType: false,
			    error : function() {
			    },
			    success:function(data){

			 
                    if('success' == data){
                        alert("上传电缴费记录信息成功!");
                       	LoadingClose();
                    }else if("fail" == data) {
                        alert("上传电缴费记录信息失败!");
                       	LoadingClose();
                    }else{
                        alert(data);
                    }

			    }
			});
			
}




function exprotElecLogExcel(){
	
		$.tzLoading({content:'正在导出数据，请稍等...'});
	    var createTime = $('#createTime').val();
	    var userOrg  = $('#userOrg').val();
	    var userType  = $('#userType').val();
	    var elecType  = $('#elecType').val();
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
	    
	    window.location.href = prefix + '/exprotElecLogExcel?createTime='+createTime+'&userOrg='+userOrg+'&userType='+userType+'&elecType='+elecType+'&userId='+userId;
	    	LoadingClose();
}
		

function downloadExcel(){
	var opt = "elecLog";
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









