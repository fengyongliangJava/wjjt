$().ready(function() {
	loadType('userType','userType');
	loadType('userState','userState');
	loadType('waterType','waterType');
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
		url : "/water/water/save",
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


jQuery.validator.addMethod("isPhone", function(value, element) {
    var length = value.length;
    var mobile = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1}))+\d{8})$/;
    return this.optional(element) || (length == 11 && mobile.test(value));
   }, "请填写正确的手机号码");


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
			userTell : {
				 isPhone:true
			},
			wagesId : {
				digits : true
			},
			userState  : {
				required : true
			},
			waterType : {
				required : true
			},			
			waterPrice : {
				required : true,
				number:true
			},
			waterCost : {
				required : true,
				number:true
			},
			waterSum: {
				required : true,
				number:true
			},
			createTime : {
				required : true
			}
			
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
			userTell: {
				isPhone : icon + "请填写正确的手机号码!"
		    },	
			wagesId : {
				digits : icon + "请填写正确数字!"
			},
			userState : {
				required : icon + "请选择用户状态！"
			},
			waterType : {
				required : icon + "请选择水费类型！"
			},
			waterPrice : {
				required : icon + "请输入水费单价！"
			},
			waterCost : {
				required : icon + "请输入水费！"
			},
			waterSum: {
				required : icon + "请输水费余额！"
			},
			createTime : {
				required : icon + "请选择创建时间！"
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