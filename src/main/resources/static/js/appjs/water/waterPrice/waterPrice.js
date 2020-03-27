
var prefix = "/water/waterPrice"
$(function() {
	load();
});

function load() {
	$('#waterPriceTable')
			.bootstrapTable(
					{
						method : 'get', 
						url : prefix + "/list", 
						iconSize : 'outline',
						toolbar : '#waterPriceToolbar',
						striped : true, 
						dataType : "json", 
						pagination : true, 
						singleSelect : false, 
						pageSize : 10, 
						pageNumber : 1, 
						showColumns : false, 
						sidePagination : "server", 
						queryParams : function(params) {
							return {
								limit: params.limit,
								offset:params.offset
					
							};
						},

						columns : [
								{
									checkbox : true
								},
																{
									field : 'id', 
									title : 'id' 
								},
																{
									field : 'waterOrgName', 
									title : '水价归属' 
								},
																{
									field : 'mWater', 
									title : '民用水' 
								},
																{
									field : 's1Water', 
									title : '商业水1' 
								},
																{
									field : 's2Water', 
									title : '商业水2' 
								},
																{
									field : 's3Water', 
									title : '商业水3' 
								},
																{
									field : 'createTime', 
									title : '创建时间' 
								},
																{
									field : 'createBy', 
									title : '创建人' 
								},
																{
									field : 'updateTime', 
									title : '更新时间' 
								},
																{
									field : 'updateBy', 
									title : '创建人' 
								},
																{
									field : 'remark', 
									title : '备注' 
								},
																{
									title : '操作',
									field : 'id',
									align : 'center',
									formatter : function(value, row, index) {
										var e = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" title="编辑" onclick="edit(\''
												+ row.id
												+ '\')"><i class="fa fa-edit"></i></a> ';
										var d = '<a class="btn btn-warning btn-sm '+s_remove_h+'" href="#" title="删除"  mce_href="#" onclick="remove(\''
												+ row.id
												+ '\')"><i class="fa fa-remove"></i></a> ';
										var f = '<a class="btn btn-success btn-sm" href="#" title="备用"  mce_href="#" onclick="resetPwd(\''
												+ row.id
												+ '\')"><i class="fa fa-key"></i></a> ';
										return e + d ;
									}
								} ]
					});
}
function reLoad() {
	$('#waterPriceTable').bootstrapTable('refresh');
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
	var rows = $('#waterPriceTable').bootstrapTable('getSelections'); 
	if (rows.length == 0) {
		layer.msg("请选择要删除的数据");
		return;
	}
	layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?", {
		btn : [ '确定', '取消' ]
	// 按钮
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
					reLoad();
				} else {
					layer.msg(r.msg);
				}
			}
		});
	}, function() {

	});
}