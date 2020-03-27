
var prefix = "/water/water";
var queryType = "1";
$(function() {
	loadType('userType','userType');
	loadType('waterType','waterType');
	load();
});

function load() {
	$('#waterTable')
			.bootstrapTable(
					{
						method : 'get', 
						url : prefix + "/list", 
						iconSize : 'outline',
						toolbar : '#exampleToolbar',
						striped : true, 
						dataType : "json", 
						pagination : true, 
						singleSwatert : false, 
						pageSize : 10,
						pageNumber : 1, 
						showColumns : false, 
						sidePagination : "server",
						queryParams : function(params) {
							return {
								limit: params.limit,
								offset:params.offset,
								userType:$('#userType').val(),
								waterType:$('#waterType').val(),
								userOrg:$('#userOrg').val(),
								userId:$('#userId').val(),
								createTime:$('#createTime').val(),
								queryType:queryType
								
							};
						},
						columns : [
							{
								checkbox : true
							},
															{
								field : 'id', 
								title : 'id',
								align : "center",
								valign : "middle",
								width:50,
							},
															{
								field : 'userId', 
								title : '用户编号',
								align : "center",
								valign : "middle",
								width:80,
									
							},
															{
								field : 'userName', 
								title : '用户名', 
								align : "center",
								valign : "middle",
								width:80,
										
							},
															{
								field : 'userType', 
								title : '用户类型',
								align : 'center',
								valign : "middle",
								width:80,
									
								formatter : function(value, row, index) {
									if (value == 'A') {
										return '<span class="label label-primary">现金缴费</span>';
									} else if (value == 'B') {
										return '<span class="label label-primary">工资代扣</span>';
									}
								}
							},
															{
								field : 'userOrgName', 
								title : '用户归属', 
								align : "center",
								valign : "middle",
								width:80,
										
							},
															{
								field : 'userTell', 
								title : '联系方式', 
								align : "center",
								valign : "middle",
								width:120,
										
							},
															{
								field : 'userState', 
								title : '用户状态' ,
								align : 'center',
								valign : "middle",
								width:80,
									
								formatter : function(value, row, index) {
									if (value == '0') {
										return '<span class="label label-danger">未使用</span>';
									} else if (value == '1') {
										return '<span class="label label-primary">使用中</span>';
									}
								}
							},
															{
								field : 'wagesId', 
								title : '工资编号', 
								align : "center",
								valign : "middle",
								width:80,
										
							},
															{
								field : 'waterType', 
								title : '水费类型',
								align : 'center',
								valign : "middle",
								width:80,
									
								formatter : function(value, row, index) {
									if (value == '1') {
										return '<span class="label label-primary">商业水</span>';
									} else if (value == '2') {
										return '<span class="label label-primary">民用水</span>';
									} 
								}
							},
															{
								field : 'waterPrice', 
								title : '水费单价', 
								align : "center",
								valign : "middle",
								width:80,
										
							},	
															{
								field : 'waterCost', 
								title : '用户水费', 
								align : "center",
								valign : "middle",
								width:80,
										
							},	
							
															{
								field : 'waterSum', 
								title : '水费余额',
								align : "center",
								valign : "middle",
								width:80,
									
							},
															{
								field : 'createTime', 
								title : '创建时间',
								align : "center",
								valign : "middle",
								width:150,
							},
															{
								field : 'createBy', 
								title : '创建人',
								align : "center",
								valign : "middle",
								width:80,
										
							},
															{
								field : 'updateTime', 
								title : '更新时间',
								align : "center",
								valign : "middle",
								width:150,
							},
															{
								field : 'updateBy', 
								title : '更新人', 
								align : "center",
								valign : "middle",
								width:80,
										
							},
															{
								field : 'remark', 
								title : '备注',
								align : "center",
								valign : "middle",
								width:120,
									
							},
															{
								title : '操作',
								field : 'id',
								align : 'center',
								valign : "middle",
								width:150,
								formatter : function(value, row, index) {
									var e = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" title="编辑" onclick="edit(\''
											+ row.id
											+ '\')"><i class="fa fa-edit"></i></a> ';
									var d = '<a class="btn btn-warning btn-sm '+s_remove_h+'" href="#" title="删除"  mce_href="#" onclick="remove(\''
											+ row.id
											+ '\')"><i class="fa fa-remove"></i></a> ';
									var f = '<a class="btn btn-success btn-sm" href="#" title="添加"  mce_href="#" onclick="add(\''
											+ row.id
											+ '\')"><i class="fa fa-plus"></i></a> ';
									var g = '<a class="btn btn-success btn-sm" href="#" title="批量删除"  mce_href="#" onclick="batchRemove(\''
										+ row.id
										+ '\')"><i class="fa fa-file-excel-o"></i></a> ';
									return e + d + f + g;
								}
							} ]
				});
}

function reLoad(type) {
	if(type == 1){
		queryType = "1";
	}else{
		queryType = "2";
	}
	$('#waterTable').bootstrapTable('refresh');
}
function add() {
	layer.open({
		type : 2,
		title : '增加',
		maxmin : true,
		shadeClose : false, 
		area : [ '800px', '520px' ],
		content : prefix + '/add' 
	});
}
function edit(id) {
	layer.open({
		type : 2,
		title : '编辑',
		maxmin : true,
		shadeClose : false, 
		area : [ '800px', '520px' ],
		content : prefix + '/edit/' + id 
	});
}

function water(id){
	layer.open({
		type : 2,
		title : '编辑',
		maxmin : true,
		shadeClose : false, 
		area : [ '800px', '520px' ],
		content : prefix + '/waterEdit/' + id 
	});
}
function remove(id) {
	layer.confirm('确定要删除选中的记录？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : prefix+"/remove",
			type : "post",
			data : {
				'id' : id
			},
			success : function(r) {
				if (r.code==0) {
					layer.msg(r.msg);
					load();
				}else{
					layer.msg(r.msg);
				}
			}
		});
	})
}

function resetPwd(id) {
}
function batchRemove() {
	var rows = $('#waterTable').bootstrapTable('getSelections'); 
	if (rows.length == 0) {
		layer.msg("请选择要删除的数据");
		return;
	}
	layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?", {
		btn : [ '确定', '取消' ]
	
	}, function() {
		var ids = new Array();

		$.each(rows, function(i, row) {
			ids[i] = row['id'];
		});
		$.ajax({
			type : 'POST',
			data : {
				"ids" : ids
			},
			url : prefix + '/batchRemove',
			success : function(r) {
				if (r.code == 0) {
					layer.msg(r.msg);
					load();
				} else {
					layer.msg(r.msg);
				}
			}
		});
	}, function() {

	});
}





function exprotWater(){
	
	$.tzLoading({content:'正在导出水费用户，请稍等...'});
    var createTime = $('#createTime').val();
    var userType =   $('#userType').val();
    var userOrg  = $('#userOrg').val();
    var userId   = $('#userId').val();
    
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
    
    window.location.href = prefix + '/exprotWater?createTime='+createTime+'&userOrg='+userOrg+'&userType='+userType+'&userId='+userId;
    	LoadingClose();
}



function loadType(id, dicType){
	var html = "";
	$.ajax({
		url : '/common/dict/list/'+dicType,
		success : function(data) {

			for (var i = 0; i < data.length; i++) {
				html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
			}
			$("#"+id).append(html);
			$("#"+id).chosen({
				maxHeight : 200
			});
	
			$('#'+id).on('change', function(e, params) {
				
				var opt = {
					query : {
						type : params.swaterted,
					}
				}
				$('#waterTable').bootstrapTable('refresh', opt);
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