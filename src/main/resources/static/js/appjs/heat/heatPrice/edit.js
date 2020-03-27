$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});
function update() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/heat/heatPrice/update",
		data : $('#heatPriceForm').serialize(),// 你的formid
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
	$("#heatPriceForm").validate({
		rules : {
			createTime : {
				required : true
			},
			mHeat : {
				required : true,
				number:true
			},
			sHeat : {
				required : true,
				number:true
			},
			gmHeat : {
				number:true
			},
			gsHeat : {
				number:true
			}
		},
		messages : {
			
			createTime : {
				required : icon + "请输入时间！"
			},
			mHeat : {
				required : icon + "请输入民用暖！"
			},
			sHeat : {
				required : icon + "请输入商业暖"
			},
			gmHeat : {
				required : icon + "请输入工业民"
			},
			gsHeat : {
				required : icon + "请输入工业商"
			}
		}
	})
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
	$("#heatOrg").val(deptId);
	$("#heatOrgName").val(deptName);
}