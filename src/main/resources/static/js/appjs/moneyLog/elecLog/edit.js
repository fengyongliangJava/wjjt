$().ready(function() {
	loadType('userType','userType');
	loadType('elecType','elecType');
	validateRule();
});

$.validator.setDefaults({
	ignore:":hidden:not(select)",
	submitHandler : function() {
		update();
	}
});

function update() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/moneyLog/elecLog/update",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				parent.reLoad();
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);

			} else {
				parent.layer.alert(data.msg)
			}

		}
	});

}


function validateRule() {
	
	var icon = "<i class='fa fa-times-circle'></i> ";
	
	$("#signupForm").validate({

		rules : {
			userName : {
				required : true
			},
			userId : {
				required : true
			},
			userType : {
				required : true
			},	
			userOrgName : {
				required : true
			},
			elecType : {
				required : true
			},	
		    elecMoney : {
				required : true,
				number:true
			},	

			createTime : {
				required : true
			},
			moneyDate : {
				required : true
			},
		},
		
		messages : {
			
			userName : {
				required : icon + "请输入姓名！"
			},
			userId : {
				required : icon + "请输入编号！"
			},
			userType : {
				required : icon + "请选择用户类型！"
			},
			userOrgName : {
				required : icon + "请选择用户地区！"
			},
			elecType : {
				required : icon + "请选择电费类型！"
			},
			elecMoney : {
				required : icon + "请输入电缴费金额！"
			},

			createTime : {
				required : icon + "请输入创建时间！"
			},
			moenyDate : {
				required : icon + "请输入缴费时间！"
			},
			
		}
	})
}





function loadType(id, dicType){
	var html = "";
	$.ajax({
		url : '/common/dict/list/'+dicType,
		success : function(data) {
			// 加载数据
			for (var i = 0; i < data.length; i++) {
				html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
			}
			$("#"+id).append(html);
			$("#"+id).chosen({
				maxHeight : 200
			});
			$("#"+id).val($("#T"+id).val());
			$("#"+id).trigger("chosen:updated");
			// 点击事件
			$("#"+id).on('change', function(e, params) {
				
			});
		}
	});
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

