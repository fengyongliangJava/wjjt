
var prefix = "/heat/heat";
var queryType = "1";

$(function() {
	loadType('userType','userType');
	loadType('heatType','heatType');
	load();
});


function load() {
	$('#heatTable')
			.bootstrapTable(
					{
						method : 'get', 
						url : prefix + "/list", 
						iconSize : 'outline',
						toolbar : '#exampleToolbar',
						striped : true, 
						dataType : "json", 
						pagination : true, 
						singleSheatt : false, 
						pageSize : 10, 
						pageNumber : 1, 
						showColumns : false, 
						sidePagination : "server",
						queryParams : function(params) {
							return {
								limit: params.limit,
								offset:params.offset,
								userType:$('#userType').val(),
								heatType:$('#heatType').val(),
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
													align : 'center',
													valign : "middle",
													width:60,
												},
																				{
													field : 'userId', 
													title : '用户编号',
													align : 'center',
													valign : "middle",
													width:80,
												},
																				{
													field : 'userName', 
													title : '用户姓名',
													align : 'center',
													valign : "middle",
													width:80,
													
												},
																				{
													field : 'userType', 
													title : '用户类型' ,
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
													title : '用户地区',
													align : 'center',
													valign : "middle",
													width:80,
												},
																				{
													field : 'userTell', 
													title : '联系方式',
													align : 'center',
													valign : "middle",
													width:80,
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
													align : 'center',
													valign : "middle",
													width:80,
												},
																				{
													field : 'heatType', 
													title : '暖费类型' ,
													align : 'center',
													valign : "middle",
													width:80,
													formatter : function(value, row, index) {
														if (value == '1') {
															return '<span class="label label-primary">民用暖</span>';
														} else if (value == '2') {
															return '<span class="label label-primary">商业暖</span>';
														} else if (value == '3') {
															return '<span class="label label-primary">工业民</span>';
														} else if (value == '4') {
															return '<span class="label label-primary">工业商</span>';
														} 
													}
												},
																				{
													field : 'heatPrice', 
													title : '取暖单价', 
													align : 'center',
													valign : "middle",
													width:80,
												},
																				{
													field : 'heatArea', 
													title : '取暖面积',
													align : 'center',
													valign : "middle",
													width:80,
												},
																				{
													field : 'heatCost', 
													title : '用户暖费',
													align : 'center',
													valign : "middle",
													width:60,
												},
																				{
													field : 'heatSum', 
													title : '暖费余额',
													align : 'center',
													valign : "middle",
													width:60,
												},
																				{
													field : 'createTime', 
													title : '创建时间', 
													align : 'center',
													valign : "middle",
													width:150,
												},
																				{
													field : 'createBy', 
													title : '创建人',
													align : 'center',
													valign : "middle",
													width:80,
												},
																				{
													field : 'updateTime', 
													title : '更新时间',
													align : 'center',
													valign : "middle",
													width:150,
												},
																				{
													field : 'updateBy', 
													title : '创建人',
													align : 'center',
													valign : "middle",
													width:80,
												},
																				{
													field : 'remark', 
													title : '备注', 
													align : 'center',
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
	$('#heatTable').bootstrapTable('refresh');
}

function add() {
	layer.open({
		type : 2,
		title : '增加',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/add' // iframe的url
	});
}
function edit(id) {
	layer.open({
		type : 2,
		title : '编辑',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/edit/' + id // iframe的url
	});
}

function heat(id){
	layer.open({
		type : 2,
		title : '编辑',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/heatEdit/' + id // iframe的url
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
					reLoad();
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
	var rows = $('#heatTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择要删除的数据");
		return;
	}
	layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?", {
		btn : [ '确定', '取消' ]
	// 按钮
	}, function() {
		var ids = new Array();
		// 遍历所有选择的行数据，取每条数据对应的ID
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
					reLoad();
				} else {
					layer.msg(r.msg);
				}
			}
		});
	}, function() {

	});
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
						type : params.sheatted,
					}
				}
				$('#heatTable').bootstrapTable('refresh', opt);
			});
		}
	});
}


function exprotHeat(){
	
	$.tzLoading({content:'正在导出暖费用户，请稍等...'});
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
    
    window.location.href = prefix + '/exprotHeat?createTime='+createTime+'&userOrg='+userOrg+'&userType='+userType+'&userId='+userId;
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
	debugger;
	$("#userOrg").val(deptId);
	$("#userOrgName").val(deptName);
}