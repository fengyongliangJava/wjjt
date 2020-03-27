$().ready(function() {
	loadType('userType','userType');
	validateRule();
});

$.validator.setDefaults({
	ignore:":hidden:not(select)",
	submitHandler : function() {
		save();
	}
});



function save() {
	
	$.ajax({
		cache : true,
		type : "POST",
		url : "/print/print/save",
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
			
			userId : {
				required : true
			},
			userName : {
				required : true
			},
			userOrgName : {
				required : true
			},
			start : {
				digits:true
			},
			end : {
				digits:true
			},
			elecPrice : {
				number:true
			},
			elecAmount : {
				digits:true
			},
			elecCost : {
				number:true
			},
			elecSum : {
				number:true
			},
			elecMoney : {
				number:true
			},
			heatPrice : {
				number:true
			},
			heatArea : {
				number:true
			},
			heatCost : {
				number:true
			},
			heatSum : {
				number:true
			},
			heatMoney: {
				number:true
			},
			waterPrice: {
				number:true
			},
			waterCost: {
				number:true
			},
			waterSum: {
				number:true
			},
			waterMoney: {
				number:true
			},
			printDate: {
				required : true
			},
			costPoper: {
				required : true
			},
			createTime: {
				required : true
			}

		},
		messages : {

		userId : {
			required : icon + "请输入编号!"
		},
		userName : {
			required : icon + "请输入姓名!"
		},
		userOrgName : {
			required : icon + "请选择地区!"
		},
		start : {
			required : icon + "请输入上月电表数!"
		},
		end : {
			required : icon + "请输入本月电表数!"
		},
		hu : {
			required : icon + "请输入互感比!"
		},
		elecPrice : {
			required : icon + "请输入电价!"
		},
		elecAmount : {
			required : icon + "请输入电量!"
		},
		elecCost : {
			required : icon + "请输入电费!"
		},
		elecSum : {
			required : icon + "请输入电费余额!"
		},
		elecMoney : {
			required : icon + "请输入电缴费金额!"
		},
		heatPrice : {
			required : icon + "请输入暖价!"
		},
		heatArea : {
			required : icon + "请输入取暖面积!"
		},
		heatCost : {
			required : icon + "请输入暖费!"
		},
		heatSum : {
			required : icon + "请输入暖费余额!"
		},
		heatMoney: {
			required : icon + "请输入暖缴费金额!"
		},
		waterPrice: {
			required : icon + "请输入水价!"
		},
		waterCost: {
			required : icon + "请输入水费!"
		},
		waterSum: {
			required : icon + "请输入水费余额!"
		},
		waterMoney: {
			required : icon + "请输入水缴费金额!"
		},
		printState: {
			required : icon + "请输入打印状态!"
		},
		printDate: {
			required : icon + "请输入打印时间!"
		},
		costPoper: {
			required : icon + "请输入收费人!"
		},
		createTime: {
			required : icon + "请输入创建日期!"
		}
		
		
		}
	})
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
				$('#exampleTable').bootstrapTable('refresh', opt);
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