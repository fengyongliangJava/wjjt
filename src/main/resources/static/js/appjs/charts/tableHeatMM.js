var prefix = "/charts/tableHeatMM";

$(function () {
	loadType('userType','userType');
	loadType('heatType','heatType');
});



function tableHeatMM() {

    $.tzLoading({content: '正在统计，请稍等...'});
    
    var title = document.getElementById("title");
    var Mheat = document.getElementById("Mheat");
    var Sheat = document.getElementById("Sheat");
    var GMheat = document.getElementById("GMheat");
    var GSheat = document.getElementById("GSheat");
    var Count  = document.getElementById("Count");
    var footer = document.getElementById("footer");
    
    var box1 = document.getElementById("box1");
    var box2 = document.getElementById("box2");
    
    
    var createTime = $('#createTime').val();
    var userOrg  = $('#userOrg').val();
    var userType  = $('#userType').val();
    var userId   = $('#userId').val();

    if (isEmpty(createTime)) {
        alert("请选择统计时间！");
        LoadingClose();
        return;
    }

    if (isEmpty(userOrg)) {
        alert("请选择用户地区！");
        LoadingClose();
        return;
    }


    var formData = new FormData($('#tableHeatMMForm')[0]);

    $.ajax({
        cache: false,
        type: "POST",
        url: prefix + '/tableHeatMM',
        data : formData,
        async: false,
        processData: false,
        contentType: false,
        error: function () {
        },
        success: function (data) {
        	
			if (userOrg == "2") {
				userOrg = "五九地区";
			} else if (userOrg == "3") {
				userOrg = "牙星地区";
			}
        	
			if (userType == "A") {
				userType = "现金缴费";
			} else if (userType == "B") {
				userType = "工资代扣";
			} else if (userType == "C") {
				userType = "微信支付";
			} else if (userType == "D") {
				userType = "银行转账";
			}
			
            var str1 = "";
            var str2 = "";
            var str3 = "";
            var str4 = "";
            var str5 = "";
            var str6 = "";
            var str7 = "";
        	
            
            
            str1 += "<td colspan='12' align='center'>" + createTime+userOrg+userType+userId+ "暖月统计报表</td>";

            
            
            str2 += "<td>民用暖</td>" +
            	"<td>" + data.MPreHeatCountMMOld + "户" + data.MPreHeatSumMMOld + "元</td>" +
                "<td>" + data.MOweHeatCountMMOld + "户" + data.MOweHeatSumMMOld + "元</td>" +
                "<td>" + data.MTotalhuOld+ "户" + data.MTotalyuOld + "元</td>" +
                "<td>" + data.MHeatPrice + "</td>" +
                "<td>" + data.MHeatAreaMM + "</td>" +
                "<td>" + data.MHeatCostMMCount+ "户" + data.MHeatCostMMSum + "元</td>" +
                "<td>" + data.MHeatLogMMCount+ "户" + data.MHeatLogMMSum+ "元</td>" +
              	"<td>" + data.MPreHeatCountMMNew + "户" + data.MPreHeatSumMMNew + "元</td>" +
                "<td>" + data.MOweHeatCountMMNew + "户" + data.MOweHeatSumMMNew + "元</td>" +
                "<td>" + data.MTotalhuNew+ "户" + data.MTotalyuNew + "元</td>";
            
            
            
            
/*            str2 += "<td>民用暖</td>" +
        	"<td>" + data.MPreHeatSumMMOld + "元</td>" +
            "<td>" + data.MOweHeatSumMMOld + "元</td>" +
            "<td>" + data.MTotalyuOld + "元</td>" +
            "<td>" + data.MHeatPrice + "</td>" +
            "<td>" + data.MHeatAreaMM + "</td>" +
            "<td>" + data.MHeatCostMMSum + "元</td>" +
            "<td>" + data.MHeatLogMMSum+ "元</td>" +
          	"<td>" + data.MPreHeatSumMMNew + "元</td>" +
            "<td>" + data.MOweHeatSumMMNew + "元</td>" +
            "<td>" + data.MTotalyuNew + "元</td>";*/

                
                
	        str3 += "<td>商业暖</td>" +
            	"<td>" + data.SPreHeatCountMMOld + "户" + data.SPreHeatSumMMOld + "元</td>" +
                "<td>" + data.SOweHeatCountMMOld + "户" + data.SOweHeatSumMMOld + "元</td>" +
                "<td>" + data.STotalhuOld+ "户" + data.STotalyuOld + "元</td>" +
                "<td>" + data.SHeatPrice + "</td>" +
                "<td>" + data.SHeatAreaMM + "</td>" +
                "<td>" + data.SHeatCostMMCount+ "户" + data.SHeatCostMMSum + "元</td>" +
                "<td>" + data.SHeatLogMMCount+ "户" + data.SHeatLogMMSum+ "元</td>" +
              	"<td>" + data.SPreHeatCountMMNew + "户" + data.SPreHeatSumMMNew + "元</td>" +
                "<td>" + data.SOweHeatCountMMNew + "户" + data.SOweHeatSumMMNew + "元</td>" +
                "<td>" + data.STotalhuNew+ "户" + data.STotalyuNew + "元</td>";
             
	        
	        
/*        str3 += "<td>商业暖</td>" +
        	"<td>" + data.SPreHeatSumMMOld + "元</td>" +
            "<td>" + data.SOweHeatSumMMOld + "元</td>" +
            "<td>" + data.STotalyuOld + "元</td>" +
            "<td>" + data.SHeatPrice + "</td>" +
            "<td>" + data.SHeatAreaMM + "</td>" +
            "<td>" + data.SHeatCostMMSum + "元</td>" +
            "<td>" + data.SHeatLogMMSum+ "元</td>" +
          	"<td>" + data.SPreHeatSumMMNew + "元</td>" +
            "<td>" + data.SOweHeatSumMMNew + "元</td>" +
            "<td>" + data.STotalyuNew + "元</td>";*/
         
	        
	        

	        str6 += "<td>工业民</td>" +
            	"<td>" + data.GMPreHeatCountMMOld + "户" + data.GMPreHeatSumMMOld + "元</td>" +
                "<td>" + data.GMOweHeatCountMMOld + "户" + data.GMOweHeatSumMMOld + "元</td>" +
                "<td>" + data.GMTotalhuOld+ "户" + data.GMTotalyuOld + "元</td>" +
                "<td>" + data.GMHeatPrice + "</td>" +
                "<td>" + data.GMHeatAreaMM + "</td>" +
                "<td>" + data.GMHeatCostMMCount+ "户" + data.GMHeatCostMMSum + "元</td>" +
                "<td>" + data.GMHeatLogMMCount+ "户" + data.GMHeatLogMMSum+ "元</td>" +
              	"<td>" + data.GMPreHeatCountMMNew + "户" + data.GMPreHeatSumMMNew + "元</td>" +
                "<td>" + data.GMOweHeatCountMMNew + "户" + data.GMOweHeatSumMMNew + "元</td>" +
                "<td>" + data.GMTotalhuNew+ "户" + data.GMTotalyuNew + "元</td>";

	        
        
/*        str6 += "<td>工业民</td>" +
    	"<td>" + data.GMPreHeatSumMMOld + "元</td>" +
        "<td>" + data.GMOweHeatSumMMOld + "元</td>" +
        "<td>" + data.GMTotalyuOld + "元</td>" +
        "<td>" + data.GMHeatPrice + "</td>" +
        "<td>" + data.GMHeatAreaMM + "</td>" +
        "<td>" + data.GMHeatCostMMSum + "元</td>" +
        "<td>" + data.GMHeatLogMMSum+ "元</td>" +
      	"<td>" + data.GMPreHeatSumMMNew + "元</td>" +
        "<td>" + data.GMOweHeatSumMMNew + "元</td>" +
        "<td>" + data.GMTotalyuNew + "元</td>";*/
        
        
        str7 += "<td>工业商</td>" +
    	"<td>" + data.GSPreHeatCountMMOld + "户" + data.GSPreHeatSumMMOld + "元</td>" +
        "<td>" + data.GSOweHeatCountMMOld + "户" + data.GSOweHeatSumMMOld + "元</td>" +
        "<td>" + data.GSTotalhuOld+ "户" + data.GSTotalyuOld + "元</td>" +
        "<td>" + data.GSHeatPrice + "</td>" +
        "<td>" + data.GSHeatAreaMM + "</td>" +
        "<td>" + data.GSHeatCostMMCount+ "户" + data.GSHeatCostMMSum + "元</td>" +
        "<td>" + data.GSHeatLogMMCount+ "户" + data.GSHeatLogMMSum+ "元</td>" +
      	"<td>" + data.GSPreHeatCountMMNew + "户" + data.GSPreHeatSumMMNew + "元</td>" +
        "<td>" + data.GSOweHeatCountMMNew + "户" + data.GSOweHeatSumMMNew + "元</td>" +
        "<td>" + data.GSTotalhuNew+ "户" + data.GSTotalyuNew + "元</td>";

        
/*            str7 += "<td>工业商</td>" +
	        	"<td>" + data.GSPreHeatSumMMOld + "元</td>" +
	            "<td>" + data.GSOweHeatSumMMOld + "元</td>" +
	            "<td>" + data.GSTotalyuOld + "元</td>" +
	            "<td>" + data.GSHeatPrice + "</td>" +
	            "<td>" + data.GSHeatAreaMM + "</td>" +
	            "<td>" + data.GSHeatCostMMSum + "元</td>" +
	            "<td>" + data.GSHeatLogMMSum+ "元</td>" +
	          	"<td>" + data.GSPreHeatSumMMNew + "元</td>" +
	            "<td>" + data.GSOweHeatSumMMNew + "元</td>" +
	            "<td>" + data.GSTotalyuNew + "元</td>";*/

            
            
           str4 += "<td>合计</td>" +
        	"<td>" + data.TotalPrehuOld +"户" + data.TotalPreyuOld+ "元</td>" +
            "<td>" + data.TotalOwehuOld +"户" + data.TotalOweyuOld+ "元</td>" +
            "<td>" + data.TotalAllhuOld+ "户" + data.TotalAllyuOld+ "元</td>" +
            "<td>" + data.PriceTotal + "</td>" +
            "<td>" + data.AreaTotalMM + "</td>" +
            "<td>" + data.TotalCosthu+ "户" + data.TotalCostyu+ "元</td>" +
            "<td>" + data.TotalLoghu+ "户" + data.TotalLogyu+ "元</td>" +
        	"<td>" + data.TotalPrehuNew+ "户" + data.TotalPreyuNew+ "元</td>" +
            "<td>" + data.TotalOwehuNew+ "户" + data.TotalOweyuNew+ "元</td>" +
            "<td>" + data.TotalAllhuNew+ "户" + data.TotalAllyuNew+ "元</td>";
            
            
/*            str4 += "<td>合计</td>" +
	        	"<td>" + data.TotalPreyuOld+ "元</td>" +
	            "<td>" + data.TotalOweyuOld+ "元</td>" +
	            "<td>" + data.TotalAllyuOld+ "元</td>" +
	            "<td>" + "</td>" +
	            "<td>" + data.AreaTotalMM + "</td>" +
	            "<td>" + data.TotalCostyu+ "元</td>" +
	            "<td>" + data.TotalLogyu+ "元</td>" +
	        	"<td>" + data.TotalPreyuNew+ "元</td>" +
	            "<td>" + data.TotalOweyuNew+ "元</td>" +
	            "<td>" + data.TotalAllyuNew+ "元</td>";*/


            str5 += "<td colspan='12' align='center'><div align='center'>本月实收总额:" + data.TotalLogyu+ "元</div></td>";

            
            if(data.type == 2){
                title.innerHTML = str1;
                Mheat.innerHTML = str2;
                Sheat.innerHTML = str3;
                Count.innerHTML = str4;
                footer.innerHTML = str5;
                GMheat.innerHTML = str6;
                GSheat.innerHTML = str7;
			}
			if(data.type == 3){
                title.innerHTML = str1;
                Mheat.innerHTML = str2;
                Sheat.innerHTML = str3;
                Count.innerHTML = str4;
                footer.innerHTML = str5;
                GMheat.innerHTML = "";
                GSheat.innerHTML = "";
			}
            
            
        	box2.style.display = 'block';
        	
            box1.style.display = 'none';
            
        	LoadingClose();

        }
    });

}


function _printHeat(data){
    var d = $.extend({
        style : null,
        title : '',
        orgName : '五九集团收费系统',
        divId : 'box2',// 打印的DIV的ID
        ids : 'LODOP_OB',
        fontSize :14,
        bold : 1,
        htm1 : '0',
        htm2 : '0',
        htm3 : '100%',
        htm4 : '100%',
        htm5 : null,
        type : '0'
    }, data || {});
    d.title = d.title && d.title != '' ? '--'+d.title : ''
    var LODOP=getLodop(document.getElementById(d.ids),document.getElementById('LODOP_EM'));
    LODOP.PRINT_INIT(d.orgName+d.title);

    LODOP.ADD_PRINT_HTML(30,'0%','100%',50,"<div align='center'><strong>"+d.orgName+d.title+"</strong></div>");
    if(!d.htm5){
        var strStyleCss = '<link href="/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">'+
            '<link rel="stylesheet" href="/js/plugins/layer/theme/default/layer.css?v=3.1.0" id="layuicss-layer">'+
            '<link href="/css/style.css?v=4.1.0" rel="stylesheet">'+
		    '<style type="text/css">'+
		     '	#tableBox1 tr td{width:100px;height:50px;ling-height:50px;text-align:center;}'+
		     '	#tableBox2 tr td{width:100px;height:50px;ling-height:50px;text-align:center;}'+
			'</style>';
        var printHTML = "<body>" + document.getElementById(d.divId).innerHTML + "</body>";
        d.htm5 = "<head>" + strStyleCss + "</head>" + printHTML;
    }
    
    LODOP.ADD_PRINT_HTM(d.htm1,d.htm2,d.htm3,d.htm4,d.htm5);

    //*****如下空白位置适合调用统一功能:******************
    LODOP.SET_LICENSES("","A3470E243232414B2A4DDCDE440798FF","C94CEE276DB2187AE6B65D56B3FC2848","");
    //*******************************************

    switch(d.type){
        case '0':
            LODOP.PREVIEW();
            break;
        case '1':
            LODOP.SET_PRINT_PAGESIZE(2,2400,3000,"暖月统计表格");
            LODOP.PRINT();
            break;
        case '2':
            LODOP.PRINTA();
            break;
        default:
            LODOP.PREVIEW();
            break;
    }

}

$('#printHeat').on('click', function(){
    _printHeat({
        title:'暖月统计表格',
        fontSize : 18,
        type:'1',
        htm1 : 88,
        htm2 : 1,
        htm3 : 2400,
        htm4 : 3000,
        htm5:null
    });
});





function exportHeatMM(){
	
	$.tzLoading({content:'正在下载，请稍等...'});
    var createTime = $('#createTime').val();
    var userOrg  = $('#userOrg').val();
    var userType  = $('#userType').val();
    var heatType  = $('#heatType').val();
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
    
    window.location.href = prefix + '/exportHeatMM?createTime='+createTime+'&userOrg='+userOrg+'&userType='+userType+'&heatType='+heatType+'&userId='+userId;
    	LoadingClose();
}

function dowloadHeatMM() {
	$.tzLoading({content:'正在下载，请稍等...'});
    $("#tableBox2").table2excel({
        exclude: ".noExl",
        filename: "暖费月统计表格",
    });
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
				$('#tableBox1').bootstrapTable('refresh', opt);
			});
		}
	});
}


function openDept() {
    layer.open({
        type: 2,
        title: "选择组织",
        area: ['300px', '450px'],
        content: "/system/sysDept/treeView"
    })
}

function loadDept(deptId, deptName) {
    $("#userOrg").val(deptId);
    $("#userOrgName").val(deptName);
}