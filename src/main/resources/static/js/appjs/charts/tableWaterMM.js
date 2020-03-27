var prefix = "/charts/tableWaterMM";

$(function () {
	loadType('userType','userType');
	loadType('waterType','waterType');
});


function tableWaterMM() {
	
	    $.tzLoading({content: '正在统计，请稍等...'});
	    
	    var title = document.getElementById("title");
	    var Mwater = document.getElementById("Mwater");
	    var S1water = document.getElementById("S1water");
	    var S2water = document.getElementById("S2water");
	    var S3water = document.getElementById("S3water");
	    var Count  = document.getElementById("Count");
	    var footer = document.getElementById("footer");
	    
	    var box1 = document.getElementById("box1");
	    var box2 = document.getElementById("box2");

	    var createTime = $('#createTime').val();
	    var userOrg = $('#userOrg').val();
	    var userType = $('#userType').val();
	    var userId = $('#userId').val();

	    
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

	    var formData = new FormData($('#tableWaterMMForm')[0]);

	    $.ajax({
	        cache: false,
	        type: "POST",
	        url: prefix + '/tableWaterMM',
	        data: formData,
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

	            str1 += "<td colspan='12' align='center'>" + createTime+userOrg+userType+userId+ "水月统计报表</td>";

	            
/*	            
	            str2 += "<td>民用水</td>" +
	            	"<td>" + data.MPreWaterCountMMOld + "户" + data.MPreWaterSumMMOld + "元</td>" +
	                "<td>" + data.MOweWaterCountMMOld + "户" + data.MOweWaterSumMMOld + "元</td>" +
	                "<td>" + data.MTotalhuOld+ "户" + data.MTotalyuOld + "元</td>" +
	                "<td>" + data.MWaterPrice + "</td>" +
	                "<td>" + data.MWaterCostMMCount+ "户" + data.MWaterCostMMSum + "元</td>" +
	                "<td>" + data.MWaterLogMMCount+ "户" + data.MWaterLogMMSum+ "元</td>" +
	              	"<td>" + data.MPreWaterCountMMNew + "户" + data.MPreWaterSumMMNew + "元</td>" +
	                "<td>" + data.MOweWaterCountMMNew + "户" + data.MOweWaterSumMMNew + "元</td>" +
	                "<td>" + data.MTotalhuNew+ "户" + data.MTotalyuNew + "元</td>";
*/
	                
	    
	            str2 += "<td>民用水</td>" +
            	"<td>" + data.MPreWaterSumMMOld + "元</td>" +
                "<td>" + data.MOweWaterSumMMOld + "元</td>" +
                "<td>" + data.MTotalyuOld + "元</td>" +
                "<td>" + data.MWaterPrice + "</td>" +
                "<td>" + data.MWaterCostMMSum + "元</td>" +
                "<td>" + data.MWaterLogMMSum+ "元</td>" +
              	"<td>" + data.MPreWaterSumMMNew + "元</td>" +
                "<td>" + data.MOweWaterSumMMNew + "元</td>" +
                "<td>" + data.MTotalyuNew + "元</td>";

	            
	            

	            
	            
/*		        str3 += "<td>商业水1</td>" +
	            	"<td>" + data.S1PreWaterCountMMOld + "户" + data.S1PreWaterSumMMOld + "元</td>" +
	                "<td>" + data.S1OweWaterCountMMOld + "户" + data.S1OweWaterSumMMOld + "元</td>" +
	                "<td>" + data.S1TotalhuOld+ "户" + data.S1TotalyuOld + "元</td>" +
	                "<td>" + data.S1WaterPrice + "</td>" +
	                "<td>" + data.S1WaterCostMMCount+ "户" + data.S1WaterCostMMSum + "元</td>" +
	                "<td>" + data.S1WaterLogMMCount+ "户" + data.S1WaterLogMMSum+ "元</td>" +
	              	"<td>" + data.S1PreWaterCountMMNew + "户" + data.S1PreWaterSumMMNew + "元</td>" +
	                "<td>" + data.S1OweWaterCountMMNew + "户" + data.S1OweWaterSumMMNew + "元</td>" +
	                "<td>" + data.S1TotalhuNew+ "户" + data.S1TotalyuNew + "元</td>";*/
	             
	            
		        str3 += "<td>商业水1</td>" +
            	"<td>" + data.S1PreWaterSumMMOld + "元</td>" +
                "<td>" + data.S1OweWaterSumMMOld + "元</td>" +
                "<td>" + data.S1TotalyuOld + "元</td>" +
                "<td>" + data.S1WaterPrice + "</td>" +
                "<td>" + data.S1WaterCostMMSum + "元</td>" +
                "<td>" + data.S1WaterLogMMSum+ "元</td>" +
              	"<td>" + data.S1PreWaterSumMMNew + "元</td>" +
                "<td>" + data.S1OweWaterSumMMNew + "元</td>" +
                "<td>" + data.S1TotalyuNew + "元</td>";
	            
	            
	            
	            
	            
	            

/*		        str6 += "<td>商业水2</td>" +
	            	"<td>" + data.S2PreWaterCountMMOld + "户" + data.S2PreWaterSumMMOld + "元</td>" +
	                "<td>" + data.S2OweWaterCountMMOld + "户" + data.S2OweWaterSumMMOld + "元</td>" +
	                "<td>" + data.S2TotalhuOld+ "户" + data.S2TotalyuOld + "元</td>" +
	                "<td>" + data.S2WaterPrice + "</td>" +
	                "<td>" + data.S2WaterCostMMCount+ "户" + data.S2WaterCostMMSum + "元</td>" +
	                "<td>" + data.S2WaterLogMMCount+ "户" + data.S2WaterLogMMSum+ "元</td>" +
	              	"<td>" + data.S2PreWaterCountMMNew + "户" + data.S2PreWaterSumMMNew + "元</td>" +
	                "<td>" + data.S2OweWaterCountMMNew + "户" + data.S2OweWaterSumMMNew + "元</td>" +
	                "<td>" + data.S2TotalhuNew+ "户" + data.S2TotalyuNew + "元</td>";
*/
		        
		        
		        str6 += "<td>商业水2</td>" +
            	"<td>" + data.S2PreWaterSumMMOld + "元</td>" +
                "<td>" + data.S2OweWaterSumMMOld + "元</td>" +
                "<td>" + data.S2TotalyuOld + "元</td>" +
                "<td>" + data.S2WaterPrice + "</td>" +
                "<td>" + data.S2WaterCostMMSum + "元</td>" +
                "<td>" + data.S2WaterLogMMSum+ "元</td>" +
              	"<td>" + data.S2PreWaterSumMMNew + "元</td>" +
                "<td>" + data.S2OweWaterSumMMNew + "元</td>" +
                "<td>" + data.S2TotalyuNew + "元</td>";
		        
		        
		        
		        
		        
/*		        
	            str7 += "<td>商业水3</td>" +
		        	"<td>" + data.S3PreWaterCountMMOld + "户" + data.S3PreWaterSumMMOld + "元</td>" +
		            "<td>" + data.S3OweWaterCountMMOld + "户" + data.S3OweWaterSumMMOld + "元</td>" +
		            "<td>" + data.S3TotalhuOld+ "户" + data.S3TotalyuOld + "元</td>" +
		            "<td>" + data.S3WaterPrice + "</td>" +
		            "<td>" + data.S3WaterCostMMCount+ "户" + data.S3WaterCostMMSum + "元</td>" +
		            "<td>" + data.S3WaterLogMMCount+ "户" + data.S3WaterLogMMSum+ "元</td>" +
		          	"<td>" + data.S3PreWaterCountMMNew + "户" + data.S3PreWaterSumMMNew + "元</td>" +
		            "<td>" + data.S3OweWaterCountMMNew + "户" + data.S3OweWaterSumMMNew + "元</td>" +
		            "<td>" + data.S3TotalhuNew+ "户" + data.S3TotalyuNew + "元</td>";*/

	            
	            str7 += "<td>商业水3</td>" +
	        	"<td>" + data.S3PreWaterSumMMOld + "元</td>" +
	            "<td>" + data.S3OweWaterSumMMOld + "元</td>" +
	            "<td>" + data.S3TotalyuOld + "元</td>" +
	            "<td>" + data.S3WaterPrice + "</td>" +
	            "<td>" + data.S3WaterCostMMSum + "元</td>" +
	            "<td>" + data.S3WaterLogMMSum+ "元</td>" +
	          	"<td>" + data.S3PreWaterSumMMNew + "元</td>" +
	            "<td>" + data.S3OweWaterSumMMNew + "元</td>" +
	            "<td>" + data.S3TotalyuNew + "元</td>";
	            
	            
/*	            str4 += "<td>合计</td>" +
		        	"<td>" + data.TotalPrehuOld +"户" + data.TotalPreyuOld+ "元</td>" +
		            "<td>" + data.TotalOwehuOld +"户" + data.TotalOweyuOld+ "元</td>" +
		            "<td>" + data.TotalAllhuOld+ "户" + data.TotalAllyuOld+ "元</td>" +
		            "<td>" + data.PriceTotal + "</td>" +
		            "<td>" + data.TotalCosthu+ "户" + data.TotalCostyu+ "元</td>" +
		            "<td>" + data.TotalLoghu+ "户" + data.TotalLogyu+ "元</td>" +
		        	"<td>" + data.TotalPrehuNew+ "户" + data.TotalPreyuNew+ "元</td>" +
		            "<td>" + data.TotalOwehuNew+ "户" + data.TotalOweyuNew+ "元</td>" +
		            "<td>" + data.TotalAllhuNew+ "户" + data.TotalAllyuNew+ "元</td>";*/
	            
	            str4 += "<td>合计</td>" +
	        	"<td>" + data.TotalPreyuOld+ "元</td>" +
	            "<td>" + data.TotalOweyuOld+ "元</td>" +
	            "<td>" + data.TotalAllyuOld+ "元</td>" +
	            "<td>"  + "</td>" +
	            "<td>" + data.TotalCostyu+ "元</td>" +
	            "<td>" + data.TotalLogyu+ "元</td>" +
	        	"<td>" + data.TotalPreyuNew+ "元</td>" +
	            "<td>" + data.TotalOweyuNew+ "元</td>" +
	            "<td>" + data.TotalAllyuNew+ "元</td>";

	            str5 += "<td colspan='12' align='center'><div align='center'>本月实收总额:" + data.TotalLogyu+ "元</div></td>";

	            if(data.type == 2){
                    title.innerHTML = str1;
                    Mwater.innerHTML = str2;
                    S1water.innerHTML = str3;
                    Count.innerHTML = str4;
                    footer.innerHTML = str5;
                    S2water.innerHTML = "";
                    S3water.innerHTML = "";
				}
				if(data.type == 3){
                    title.innerHTML = str1;
                    Mwater.innerHTML = str2;
                    S1water.innerHTML = str3;
                    Count.innerHTML = str4;
                    footer.innerHTML = str5;
                    S2water.innerHTML = str6;
                    S3water.innerHTML = str7;
				}

	        	box2.style.display = 'block';
	        	
	            box1.style.display = 'none';
	            
	            LoadingClose();
	        }
	    });
	}




function _printWater(data){
    var d = $.extend({
        style : null,
        title : '',
        orgName : '五九集团收费系统',
        divId : 'box2',// 打印的DIV的ID
        ids : 'LODOP_OB',
        fontSize :16,
        bold : 10,
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
            LODOP.SET_PRINT_PAGESIZE(2,2400,3000,"水月统计表格");
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

$('#printWater').on('click', function(){
    _printWater({
        title:'水月统计表格',
        fontSize : 18,
        type:'1',
        htm1 : 88,
        htm2 : 50,
        htm3 : 2400,
        htm4 : 3000,
        htm5:null
    });
});





function exportWaterMM(){
	
	$.tzLoading({content:'正在下载，请稍等...'});
    var createTime = $('#createTime').val();
    var userOrg  = $('#userOrg').val();
    var userType  = $('#userType').val();
    var waterType  = $('#waterType').val();
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
    
    window.location.href = prefix + '/exportWaterMM?createTime='+createTime+'&userOrg='+userOrg+'&userType='+userType+'&waterType='+waterType+'&userId='+userId;
    	LoadingClose();
}




function dowloadWaterMM() {
	$.tzLoading({content:'正在下载，请稍等...'});
    $("#tableBox2").table2excel({
        exclude: ".noExl",
        filename: "水费月统计表格",
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