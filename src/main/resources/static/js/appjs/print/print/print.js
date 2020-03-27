
var prefix = "/print/print"
$(function() {
	loadType('userType','userType');
	load();
});

function load() {
	$('#printTable')
			.bootstrapTable(
					{
						method : 'get', 
						url : prefix + "/list", 
						iconSize : 'outline',
						toolbar : '#exampleToolbar',
						striped : true, 
						dataType : "json", 
						pagination : true, 
						singleSelect : false, 
						pageSize : 100, 
						pageNumber : 1, 
						showColumns : false, 
						sidePagination : "server",
						queryParams : function(params) {
							return {
								limit: params.limit,
								offset:params.offset,
								createTime:$('#createTime').val(),
								userType:$('#userType').val(),
								userOrg:$('#userOrg').val(),
								userId:$('#userId').val()
								
								
							};
						},

						columns : [
								{
									checkbox : true
								},{
									field : 'id', 
									title : '编号', 
									align : "center",
									valign : "middle",
									width:30,
								},{
									title : '票据打印记录明细',
									field : '', 
									align : "center",
									valign : "middle",
									width:500,
									formatter : function(value, row, index) {
										var e = row.printDate;
										var d = row.userId;
										var k = row.userName;
										return e + " " + d + " " + k + "票据打印记录明细";
									}
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
										var f = '<a class="btn btn-success btn-sm" href="#" title="添加"  mce_href="#" onclick="add(\''
												+ row.id
												+ '\')"><i class="fa fa-plus"></i></a> ';
										var d = '<a class="btn btn-warning btn-sm '+s_remove_h+'" href="#" title="删除"  mce_href="#" onclick="remove(\''
												+ row.id
												+ '\')"><i class="fa fa-remove"></i></a> ';
										var k = '<a class="btn btn-success btn-sm" href="#" title="打印"  mce_href="#" onclick="print(\''
												+ row.id
												+ '\')"><i class="fa fa-legal"></i></a> ';
										return e + f + d + k;
									}
								} ]
					});
}

function reLoad() {
	$('#printTable').bootstrapTable('refresh');
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

function print(id){
	layer.open({
		type : 2,
		title : '编辑',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/printEdit/' + id // iframe的url
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
	var rows = $('#printTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
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



function batchPrint() {
	
	var rows = $('#printTable').bootstrapTable('getSelections'); 
	if (rows.length == 0) {
		layer.msg("请选择要打印的数据");
		return;
	}
	
	layer.confirm("确认要打印选中的'" + rows.length + "'条数据吗?", {
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
			url : prefix + '/batchPrint',
			success : function(r) {

				var LODOP = getLodop();
				for (var i = 0; i < r.length; i++) {
					
				   alert(r[i].printMoney);
					
					LODOP.NewPage();
					
					if(r[i].start == null){ r[i].start = ""}
					if(r[i].end   == null){ r[i].end   = ""}
					if(r[i].elecAmount == null){ r[i].elecAmount  = ""}
					if(r[i].elecPrice == null){ r[i].elecPrice  = ""}
					if(r[i].elecCost == null){ r[i].elecCost  = ""}
					if(r[i].elecMoney == null){ r[i].elecMoney  = 0}
					if(r[i].elecSum == null){ r[i].elecSum  = ""}
					if(r[i].heatPrice == null){ r[i].heatPrice  = ""}
					if(r[i].heatCost == null){ r[i].heatCost  = ""}
					if(r[i].heatMoney == null){ r[i].heatMoney  = 0}
					if(r[i].heatSum == null){ r[i].heatSum  = ""}
					if(r[i].waterPrice == null){ r[i].waterPrice  = ""}
					if(r[i].waterCost == null){ r[i].waterCost  = ""}
					if(r[i].waterMoney == null){ r[i].waterMoney  = 0}
					if(r[i].waterSum == null){ r[i].waterSum  = ""}
					
					
					
					
					var html = 		
						
						'<link href="/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">'+
						'<link rel="stylesheet" href="/js/plugins/layer/theme/default/layer.css?v=3.1.0" id="layuicss-layer">'+
			            '<link href="/css/style.css?v=4.1.0" rel="stylesheet">'+
			            '<h2 align="center">'+"五九集团收费系统---缴费通知单"+'</h2>'+
						'	<div class="wrapper wrapper-content"  id="printId">'+
						'		<div class="col-sm-12">'+
						'			<div class="ibox float-e-margins">'+
						'				<div class="ibox-content">'+
						'					<form class="form-horizontal" id="signupForm" style="font-size:18px;">'+
						'						<div class="form-group" style="margin-bottom:5px;">	'+
						'							<label class="col-sm-2 control-label">用户编号：</label>'+ r[i].userId +
						'								<span class="col-sm-2" style="padding:0px;" ></span>'+
						'							<label class="col-sm-2 control-label">业主姓名：</label>'+
						'								<span class="col-sm-2" style="padding:0px;" > </span>' + r[i].userName +
						'							<label class="col-sm-2 control-label">地区：</label>'+
						'								<span class="col-sm-2" style="padding:0px;"></span>'+ r[i].userOrgName +
						'						</div>'+
						'						<div class="form-group" style="margin-bottom:5px;">	'+
						'							<label class="col-sm-2 control-label">上月电表：</label>'+
						'								<span class="col-sm-2"  style="padding:0px;" ></span>'+ r[i].start +
						'							<label class="col-sm-2 control-label">本月电表：</label>'+
						'								<span class="col-sm-2"  style="padding:0px;"></span>'+ r[i].end +
						'							<label class="col-sm-2 control-label">电量：</label>'+
						'								<span class="col-sm-2"  style="padding:0px;" ></span>'+ r[i].elecAmount +
						'						</div>'+
						'						<div class="form-group" style="margin-bottom:5px;">'+
						'							<label class="col-sm-3 control-label" style="text-align:left;padding-left:10px;">费用名称</label>'+
						'							<label class="col-sm-3 control-label" style="text-align:left;padding-left:10px;">收费标准</label>'+
						'							<label class="col-sm-2 control-label" style="text-align:left;padding-left:10px;">本月费用</label>'+
						'							<label class="col-sm-2 control-label" style="text-align:left;padding-left:10px;">本次缴费</label>'+
						'							<label class="col-sm-2 control-label" style="text-align:left;padding-left:10px;">用户余额</label>'+
						'						</div>'+
						'						<div class="form-group" style="margin-bottom:5px;">'+
						'							<label class="col-sm-3 control-label" style="text-align:left">电费</label>'+
						'								<span class="col-sm-3"  style="padding-top:5px;padding-left:50px;"></span>'+ r[i].elecPrice +
						'								<span class="col-sm-2"  style="padding-top:5px;padding-left:50px;"></span>'+  r[i].elecCost +
						'								<span class="col-sm-2"  style="padding-top:5px;padding-left:50px;"></span>'+  r[i].elecMoney +
						'								<span class="col-sm-2"  style="padding-top:5px;padding-left:40px;"></span>'+  r[i].elecSum +
						'						</div>'+
						'						<div class="form-group" style="margin-bottom:5px;">'+
						'							<label class="col-sm-3 control-label" style="text-align:left">暖费</label>'+
						'								<span class="col-sm-3"  style="padding-top:5px;padding-left:50px;"></span>'+ r[i].heatPrice +
						'								<span class="col-sm-2"  style="padding-top:5px;padding-left:50px;"></span>'+ r[i].heatCost +
						'								<span class="col-sm-2"  style="padding-top:5px;padding-left:50px;"></span>'+ r[i].heatMoney +
						'								<span class="col-sm-2"  style="padding-top:5px;padding-left:40px;"></span>'+ r[i].heatSum +
						'						</div>'+
						'						<div class="form-group" style="margin-bottom:5px;">'+
						'							<label class="col-sm-3 control-label" style="text-align:left">水费</label>'+
						'								<span class="col-sm-3"  style="padding-top:5px;padding-left:50px;"></span>'+  r[i].waterPrice +
						'								<span class="col-sm-2"  style="padding-top:5px;padding-left:50px;"></span>'+  r[i].waterCost +
						'								<span class="col-sm-2"  style="padding-top:5px;padding-left:50px;"></span>'+  r[i].waterMoney +
						'								<span class="col-sm-2"  style="padding-top:5px;padding-left:40px;"></span>'+  r[i].waterSum +
						'						</div>'+
						'						<div class="form-group" style="margin-bottom:5px;">'+
						'							<label class="col-sm-3 control-label" style="text-align:left">大写金额</label>'+
						'								<span class="col-sm-3"  style="padding-top:5px;" id="big">  ' +bigNum(r[i].printMoney)+'</span>' +
						'							<label class="col-sm-3 control-label" style="text-align:left">小写金额</label>' +   
						'								<span class="col-sm-3"  style="padding-top:5px;"></span>'+  (r[i].printMoney) +
						'						</div>'+
						'						'+
						'						<div class="form-group" style="margin-bottom:5px;">'+
						'							<label class="col-sm-3 control-label" style="text-align:left">收取 '+ r[i].createTime +'费用</label>'+
						'						</div>'+
						'						'+
						'						<div class="form-group" style="margin-bottom:5px;">'+
						'							<label class="col-sm-2 control-label" style="text-align:left">收款人</label>'+
						'								<span class="col-sm-4"  style="padding-top:5px;"></span>'+  r[i].createBy +
						'							<label class="col-sm-2 control-label" style="text-align:left">收款日期</label>'+
						'								<span class="col-sm-4"  style="padding-top:5px;"></span>'+ r[i].updateTime +
						'						</div>'+
						'					</form>'+
						'					'+
						'				</div>'+
						'			</div>'+
						'		</div>'+
						'	</div>'+
						'	';
					
					LODOP.ADD_PRINT_HTM(0,0,"100%","100%",html);
					
					LODOP.SET_PRINT_PAGESIZE(1, 1900, 1270, "");
					
					
				}
				LODOP.PRINT();
				//LODOP.PREVIEW();
				//LODOP.PRINT_SETUP();
				
			
			}
		});
	});

}





function bigNum(num) {
	
	
    if (!/^(0|[1-9]\d*)(\.\d+)?$/.test(num)) return "数据非法";
    var unit = "京亿万仟佰拾兆万仟佰拾亿仟佰拾万仟佰拾元角分", str = "";
    num += "00";
    var p = num.indexOf('.');
    if (p >= 0)
        num = num.substring(0, p) + num.substr(p+1, 2);
    unit = unit.substr(unit.length - num.length);
    for (var i=0; i < num.length; i++) str += '零壹贰叁肆伍陆柒捌玖'.charAt(num.charAt(i)) + unit.charAt(i);
    var a = str.replace(/零(仟|佰|拾|角)/g, "零").replace(/(零)+/g, "零").replace(/零(兆|万|亿|元)/g, "$1").replace(/(兆|亿)万/g, "$1").replace(/(京|兆)亿/g, "$1").replace(/(京)兆/g, "$1").replace(/(京|兆|亿|仟|佰|拾)(万?)(.)仟/g, "$1$2零$3仟").replace(/^元零?|零分/g, "").replace(/(元|角)$/g, "$1整");

    return a;
    
}


	

function exprotPrint(){
	
	$.tzLoading({content:'正在导出电费用户，请稍等...'});
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
    
    window.location.href = prefix + '/exprotPrint?createTime='+createTime+'&userOrg='+userOrg+'&userType='+userType+'&userId='+userId;
    	LoadingClose();
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
				$('#printTable').bootstrapTable('refresh', opt);
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