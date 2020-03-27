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
		url : "/water/waterPrice/update",
		data : $('#waterPriceForm').serialize(),// 你的formid
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
	$("#waterPriceForm").validate({
		rules : {
			
			createTime : {
				required : true
			},
			mWater : {
				required : true,
				digits:true
			},
			s1Water : {
				required : true,
				digits:true
			},
			s2Water : {
				digits:true
			},
			s3Water : {
				digits:true
			}
		},
		messages : {
			
			createTime : {
				required : icon + "请选择时间！"
			},
			mWater : {
				required : icon + "请输入民用水！"
			},
			s1Water : {
				required : icon + "请输入商业水1"
			},
			s2Water : {
				required : icon + "请输入商业水2"
			},
			s3Water : {
				required : icon + "请输入商业水3"
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
	$("#waterOrg").val(deptId);
	$("#waterOrgName").val(deptName);
}