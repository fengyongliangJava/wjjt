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
		url : "/elec/elecPrice/update",
		data : $('#elecPriceForm').serialize(),// 你的formid
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
	$("#elecPriceForm").validate({
		rules : {
			createTime : {
				required : true
			},
			
			mElec : {
				required : true,
				number:true
			},
			sElec : {
				required : true,
				number:true
			},
			g1Elec : {
				number:true
			},
			g2Elec : {
				number:true
			},
			g3Elec : {
				number:true
			},
			wmElec : {
				number:true
			},
			wsElec : {
				number:true
			},
			wgElec : {
				number:true
			},
			mmElec : {
				number:true
			},
			msElec : {
				number:true
			},
			zElec : {
				number:true
			}
		},
		messages : {
			
			createTime : {
				required : icon + "请输入时间！"
			},
			
			mElec : {
				required : icon + "请输入民用电！"
			},
			sElec : {
				required : icon + "请输入商业电"
			},
			g1Elec : {
				required : icon + "请输入工业电1"
			},
			g2Elec : {
				required : icon + "请输入工业电2"
			},
			g3Elec : {
				required : icon + "请输入工业电3"
			},
			wmElec : {
				required : icon + "请输入乌民用"
			},
			wsElec : {
				required : icon + "请输入乌商业"
			},
			wgElec : {
				required : icon + "请输入乌工业"
			},
			mmElec : {
				required : icon + "请输入煤田民用"
			},		
			msElec : {
				required : icon + "请输入煤田商业"
			},
			zElec : {
				required : icon + "请输入政府部门"
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
	$("#elecOrg").val(deptId);
	$("#elecOrgName").val(deptName);
}