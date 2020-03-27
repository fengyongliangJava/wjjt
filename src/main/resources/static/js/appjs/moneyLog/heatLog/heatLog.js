
var prefix = "/moneyLog/heatLog"
	
$(function() {
	loadType('userType','userType');
	loadType('heatType','heatType');
	load();
});


function load() {
	$('#heatLogTable')
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
								moneyDate:$('#moneyDate').val()
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
									width:30,
								},
																{
									
									title : '暖交费记录明细',
									field : '', 
									align : "center",
									valign : "middle",
									width:500,
									formatter : function(value, row, index) {
										var e = row.moneyDate;
										var d = row.userId;
										var k = row.userName;
										return e + " " + d + " " + k + "缴费记录明细";
									}
								},													
								
								{
									title : '操作',
									field : 'id',
									align : 'center',
									valign : "middle",
									width:120,
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
function reLoad() {
	$('#heatLogTable').bootstrapTable('refresh');
}



function exprotHeatLog(){
	
	$.tzLoading({content:'正在导出暖缴费记录，请稍等...'});
    var moneyDate = $('#moneyDate').val();
    var userType = $('#userType').val();
    var heatType = $('#heatType').val();
    var userOrg  = $('#userOrg').val();
    var userId   = $('#userId').val();
    
	if(isEmpty(moneyDate)){
		alert("请选择导出时间！");
		LoadingClose();
		return;
	} 
	
	if(isEmpty(userOrg)){
		alert("请选择用户地区！");
		LoadingClose();
		return;
	} 
    
    window.location.href = prefix + '/exprotHeatLog?moneyDate='+moneyDate+'&userOrg='+userOrg+'&userType='+userType+'&heatType='+heatType+'&userId='+userId;
    	LoadingClose();
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
	var rows = $('#heatLogTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
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
						type : params.selected,
					}
				}
				$('#heatLogTable').bootstrapTable('refresh', opt);
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




