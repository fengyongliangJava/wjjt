var prefix = "/charts/tableHeatYY";

$(function () {
	loadType('userType','userType');
	loadType('heatType','heatType');
});


function tableHeatYY() {

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


    var formData = new FormData($('#tableHeatYYForm')[0]);

    $.ajax({
        cache: false,
        type: "POST",
        url: prefix + '/tableHeatYY',
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
        	
            
            
            str1 += "<td colspan='12' align='center'>" + createTime+userOrg+userType+userId+ "暖年统计报表</td>";

            
   str2 += "<td>民用暖</td>" +
            	"<td>" + data.MPreHeatCountYYOld + "户" + data.MPreHeatSumYYOld + "元</td>" +
                "<td>" + data.MOweHeatCountYYOld + "户" + data.MOweHeatSumYYOld + "元</td>" +
                "<td>" + data.MTotalhuOld+ "户" + data.MTotalyuOld + "元</td>" +
                "<td>" + data.MHeatPrice + "</td>" +
                "<td>" + data.MHeatAreaYY + "</td>" +
                "<td>" + data.MHeatCostYYCount+ "户" + data.MHeatCostYYSum + "元</td>" +
                "<td>" + data.MHeatLogYYCount+ "户" + data.MHeatLogYYSum+ "元</td>" +
              	"<td>" + data.MPreHeatCountYYNew + "户" + data.MPreHeatSumYYNew + "元</td>" +
                "<td>" + data.MOweHeatCountYYNew + "户" + data.MOweHeatSumYYNew + "元</td>" +
                "<td>" + data.MTotalhuNew+ "户" + data.MTotalyuNew + "元</td>";
            
/*            str2 += "<td>民用暖</td>" +
        	"<td>" + data.MPreHeatSumYYOld + "元</td>" +
            "<td>" + data.MOweHeatSumYYOld + "元</td>" +
            "<td>" + data.MTotalyuOld + "元</td>" +
            "<td>" + data.MHeatPrice + "</td>" +
            "<td>" + data.MHeatAreaYY + "</td>" +
            "<td>" + data.MHeatCostYYSum + "元</td>" +
            "<td>" + data.MHeatLogYYSum+ "元</td>" +
          	"<td>" + data.MPreHeatSumYYNew + "元</td>" +
            "<td>" + data.MOweHeatSumYYNew + "元</td>" +
            "<td>" + data.MTotalyuNew + "元</td>";*/

                
                
	        str3 += "<td>商业暖</td>" +
            	"<td>" + data.SPreHeatCountYYOld + "户" + data.SPreHeatSumYYOld + "元</td>" +
                "<td>" + data.SOweHeatCountYYOld + "户" + data.SOweHeatSumYYOld + "元</td>" +
                "<td>" + data.STotalhuOld+ "户" + data.STotalyuOld + "元</td>" +
                "<td>" + data.SHeatPrice + "</td>" +
                "<td>" + data.SHeatAreaYY + "</td>" +
                "<td>" + data.SHeatCostYYCount+ "户" + data.SHeatCostYYSum + "元</td>" +
                "<td>" + data.SHeatLogYYCount+ "户" + data.SHeatLogYYSum+ "元</td>" +
              	"<td>" + data.SPreHeatCountYYNew + "户" + data.SPreHeatSumYYNew + "元</td>" +
                "<td>" + data.SOweHeatCountYYNew + "户" + data.SOweHeatSumYYNew + "元</td>" +
                "<td>" + data.STotalhuNew+ "户" + data.STotalyuNew + "元</td>";
             
            
/*	        str3 += "<td>商业暖</td>" +
        	"<td>" + data.SPreHeatSumYYOld + "元</td>" +
            "<td>" + data.SOweHeatSumYYOld + "元</td>" +
            "<td>" + data.STotalyuOld + "元</td>" +
            "<td>" + data.SHeatPrice + "</td>" +
            "<td>" + data.SHeatAreaYY + "</td>" +
            "<td>" + data.SHeatCostYYSum + "元</td>" +
            "<td>" + data.SHeatLogYYSum+ "元</td>" +
          	"<td>" + data.SPreHeatSumYYNew + "元</td>" +
            "<td>" + data.SOweHeatSumYYNew + "元</td>" +
            "<td>" + data.STotalyuNew + "元</td>";

	        */
	        
        str6 += "<td>工业民</td>" +
            	"<td>" + data.GMPreHeatCountYYOld + "户" + data.GMPreHeatSumYYOld + "元</td>" +
                "<td>" + data.GMOweHeatCountYYOld + "户" + data.GMOweHeatSumYYOld + "元</td>" +
                "<td>" + data.GMTotalhuOld+ "户" + data.GMTotalyuOld + "元</td>" +
                "<td>" + data.GMHeatPrice + "</td>" +
                "<td>" + data.GMHeatAreaYY + "</td>" +
                "<td>" + data.GMHeatCostYYCount+ "户" + data.GMHeatCostYYSum + "元</td>" +
                "<td>" + data.GMHeatLogYYCount+ "户" + data.GMHeatLogYYSum+ "元</td>" +
              	"<td>" + data.GMPreHeatCountYYNew + "户" + data.GMPreHeatSumYYNew + "元</td>" +
                "<td>" + data.GMOweHeatCountYYNew + "户" + data.GMOweHeatSumYYNew + "元</td>" +
                "<td>" + data.GMTotalhuNew+ "户" + data.GMTotalyuNew + "元</td>";

	        
/*	        str6 += "<td>工业民</td>" +
        	"<td>" + data.GMPreHeatSumYYOld + "元</td>" +
            "<td>" + data.GMOweHeatSumYYOld + "元</td>" +
            "<td>" + data.GMTotalyuOld + "元</td>" +
            "<td>" + data.GMHeatPrice + "</td>" +
            "<td>" + data.GMHeatAreaYY + "</td>" +
            "<td>" + data.GMHeatCostYYSum + "元</td>" +
            "<td>" + data.GMHeatLogYYSum+ "元</td>" +
          	"<td>" + data.GMPreHeatSumYYNew + "元</td>" +
            "<td>" + data.GMOweHeatSumYYNew + "元</td>" +
            "<td>" + data.GMTotalyuNew + "元</td>";*/
	        
	        
	        
	        
	        
            str7 += "<td>工业商</td>" +
	        	"<td>" + data.GSPreHeatCountYYOld + "户" + data.GSPreHeatSumYYOld + "元</td>" +
	            "<td>" + data.GSOweHeatCountYYOld + "户" + data.GSOweHeatSumYYOld + "元</td>" +
	            "<td>" + data.GSTotalhuOld+ "户" + data.GSTotalyuOld + "元</td>" +
	            "<td>" + data.GSHeatPrice + "</td>" +
	            "<td>" + data.GSHeatAreaYY + "</td>" +
	            "<td>" + data.GSHeatCostYYCount+ "户" + data.GSHeatCostYYSum + "元</td>" +
	            "<td>" + data.GSHeatLogYYCount+ "户" + data.GSHeatLogYYSum+ "元</td>" +
	          	"<td>" + data.GSPreHeatCountYYNew + "户" + data.GSPreHeatSumYYNew + "元</td>" +
	            "<td>" + data.GSOweHeatCountYYNew + "户" + data.GSOweHeatSumYYNew + "元</td>" +
	            "<td>" + data.GSTotalhuNew+ "户" + data.GSTotalyuNew + "元</td>";

/*            str7 += "<td>工业商</td>" +
        	"<td>" + data.GSPreHeatSumYYOld + "元</td>" +
            "<td>" + data.GSOweHeatSumYYOld + "元</td>" +
            "<td>" + data.GSTotalyuOld + "元</td>" +
            "<td>" + data.GSHeatPrice + "</td>" +
            "<td>" + data.GSHeatAreaYY + "</td>" +
            "<td>" + data.GSHeatCostYYSum + "元</td>" +
            "<td>" + data.GSHeatLogYYSum+ "元</td>" +
          	"<td>" + data.GSPreHeatSumYYNew + "元</td>" +
            "<td>" + data.GSOweHeatSumYYNew + "元</td>" +
            "<td>" + data.GSTotalyuNew + "元</td>";*/
            
            
            
            
            str4 += "<td>合计</td>" +
	        	"<td>" + data.TotalPrehuOld +"户" + data.TotalPreyuOld+ "元</td>" +
	            "<td>" + data.TotalOwehuOld +"户" + data.TotalOweyuOld+ "元</td>" +
	            "<td>" + data.TotalAllhuOld+ "户" + data.TotalAllyuOld+ "元</td>" +
	            "<td>" + data.PriceTotal + "</td>" +
	            "<td>" + data.AreaTotalYY + "</td>" +
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
            "<td>" + data.AreaTotalYY + "</td>" +
            "<td>" + data.TotalCostyu+ "元</td>" +
            "<td>" + data.TotalLogyu+ "元</td>" +
        	"<td>" + data.TotalPreyuNew+ "元</td>" +
            "<td>" + data.TotalOweyuNew+ "元</td>" +
            "<td>" + data.TotalAllyuNew+ "元</td>";*/


            str5 += "<td colspan='12' align='center'><div align='center'>全年实收总额:" + data.TotalLogyu+ "元</div></td>";

            
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






function _printView(data){
    var d = $.extend({
        style : null,
        title : '',
        orgName : '五九集团收费系统',
        divId : 'box2',// 打印的DIV的ID
        ids : 'LODOP_OB',
        fontSize :16,
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
            LODOP.SET_PRINT_PAGESIZE(2,2400,3000,"暖年统计表格");
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
    _printView({
        title:'暖年统计表格',
        fontSize : 18,
        type:'1',
        htm1 : 88,
        htm2 : 1,
        htm3 : 2400,
        htm4 : 3000,
        htm5:null
    });
});





function dowloadHeatYY() {
	$.tzLoading({content:'正在下载，请稍等...'});
    $("#tableBox2").table2excel({
        exclude: ".noExl",
        filename: "暖费年统计表格",
    });
	LoadingClose();
}


function exportHeatYY(){

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
    
    window.location.href = prefix + '/exportHeatYY?createTime='+createTime+'&userOrg='+userOrg+'&userType='+userType+'&heatType='+heatType+'&userId='+userId;
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