var prefix = "/charts/tableElecYY";

$(function () {
	loadType('userType','userType');
	loadType('elecType','elecType');
});


function tableElecYY() {

    $.tzLoading({content: '正在统计，请稍等...'});
    
    var title = document.getElementById("title");
    var Melec = document.getElementById("M");
    var Selec = document.getElementById("S");
    var G1elec = document.getElementById("G1");
    var G2elec = document.getElementById("G2");
    var G3elec = document.getElementById("G3");
    var WMelec = document.getElementById("WM");
    var WSelec = document.getElementById("WS");
    var WGelec = document.getElementById("WG");
    var MMelec = document.getElementById("MM");
    var MSelec = document.getElementById("MS");
    var Zelec = document.getElementById("Z");
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


    var formData = new FormData($('#tableElecYYForm')[0]);

    $.ajax({
        cache: false,
        type: "POST",
        url: prefix + '/tableElecYY',
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
            var str8 = "";
            var str9 = "";
            var str10 = "";
            var str11 = "";
            var str12 = "";
            var str13 = "";
            var str14 = "";
        	
            
            
            
            str1 += "<td colspan='12' align='center'>" + createTime+userOrg+userType+userId+ "电年统计报表</td>";

            
            
            str2 += "<td>民用电</td>" +
            	"<td>" + data.MPreElecCountYYOld + "户" + data.MPreElecSumYYOld + "元</td>" +
                "<td>" + data.MOweElecCountYYOld + "户" + data.MOweElecSumYYOld + "元</td>" +
                "<td>" + data.MTotalhuOld+ "户" + data.MTotalyuOld + "元</td>" +
                "<td>" + data.MElecPrice + "</td>" +
                "<td>" + data.MElecAmountYY + "</td>" +
                "<td>" + data.MElecCostYYCount+ "户" + data.MElecCostYYSum + "元</td>" +
                "<td>" + data.MElecLogYYCount+ "户" + data.MElecLogYYSum+ "元</td>" +
              	"<td>" + data.MPreElecCountYYNew + "户" + data.MPreElecSumYYNew + "元</td>" +
                "<td>" + data.MOweElecCountYYNew + "户" + data.MOweElecSumYYNew + "元</td>" +
                "<td>" + data.MTotalhuNew+ "户" + data.MTotalyuNew + "元</td>";

                

        
            
                
	        str3 += "<td>商业电</td>" +
            	"<td>" + data.SPreElecCountYYOld + "户" + data.SPreElecSumYYOld + "元</td>" +
                "<td>" + data.SOweElecCountYYOld + "户" + data.SOweElecSumYYOld + "元</td>" +
                "<td>" + data.STotalhuOld+ "户" + data.STotalyuOld + "元</td>" +
                "<td>" + data.SElecPrice + "</td>" +
                "<td>" + data.SElecAmountYY + "</td>" +
                "<td>" + data.SElecCostYYCount+ "户" + data.SElecCostYYSum + "元</td>" +
                "<td>" + data.SElecLogYYCount+ "户" + data.SElecLogYYSum+ "元</td>" +
              	"<td>" + data.SPreElecCountYYNew + "户" + data.SPreElecSumYYNew + "元</td>" +
                "<td>" + data.SOweElecCountYYNew + "户" + data.SOweElecSumYYNew + "元</td>" +
                "<td>" + data.STotalhuNew+ "户" + data.STotalyuNew + "元</td>";
            
      
            

             

	        str4 += "<td>工业电1</td>" +
            	"<td>" + data.G1PreElecCountYYOld + "户" + data.G1PreElecSumYYOld + "元</td>" +
                "<td>" + data.G1OweElecCountYYOld + "户" + data.G1OweElecSumYYOld + "元</td>" +
                "<td>" + data.G1TotalhuOld+ "户" + data.G1TotalyuOld + "元</td>" +
                "<td>" + data.G1ElecPrice + "</td>" +
                "<td>" + data.G1ElecAmountYY + "</td>" +
                "<td>" + data.G1ElecCostYYCount+ "户" + data.G1ElecCostYYSum + "元</td>" +
                "<td>" + data.G1ElecLogYYCount+ "户" + data.G1ElecLogYYSum+ "元</td>" +
              	"<td>" + data.G1PreElecCountYYNew + "户" + data.G1PreElecSumYYNew + "元</td>" +
                "<td>" + data.G1OweElecCountYYNew + "户" + data.G1OweElecSumYYNew + "元</td>" +
                "<td>" + data.G1TotalhuNew+ "户" + data.G1TotalyuNew + "元</td>";
	        


	        
            str7 += "<td>工业电2</td>" +
	        	"<td>" + data.G2PreElecCountYYOld + "户" + data.G2PreElecSumYYOld + "元</td>" +
	            "<td>" + data.G2OweElecCountYYOld + "户" + data.G2OweElecSumYYOld + "元</td>" +
	            "<td>" + data.G2TotalhuOld+ "户" + data.G2TotalyuOld + "元</td>" +
	            "<td>" + data.G2ElecPrice + "</td>" +
	            "<td>" + data.G2ElecAmountYY + "</td>" +
	            "<td>" + data.G2ElecCostYYCount+ "户" + data.G2ElecCostYYSum + "元</td>" +
	            "<td>" + data.G2ElecLogYYCount+ "户" + data.G2ElecLogYYSum+ "元</td>" +
	          	"<td>" + data.G2PreElecCountYYNew + "户" + data.G2PreElecSumYYNew + "元</td>" +
	            "<td>" + data.G2OweElecCountYYNew + "户" + data.G2OweElecSumYYNew + "元</td>" +
	            "<td>" + data.G2TotalhuNew+ "户" + data.G2TotalyuNew + "元</td>";
	        
	        
            
            
           str8 += "<td>工业电3</td>" +
				"<td>" + data.G3PreElecCountYYOld + "户" + data.G3PreElecSumYYOld + "元</td>" +
				"<td>" + data.G3OweElecCountYYOld + "户" + data.G3OweElecSumYYOld + "元</td>" +
				"<td>" + data.G3TotalhuOld+ "户" + data.G3TotalyuOld + "元</td>" +
				"<td>" + data.G3ElecPrice + "</td>" +
				"<td>" + data.G3ElecAmountYY + "</td>" +
				"<td>" + data.G3ElecCostYYCount+ "户" + data.G3ElecCostYYSum + "元</td>" +
				"<td>" + data.G3ElecLogYYCount+ "户" + data.G3ElecLogYYSum+ "元</td>" +
				"<td>" + data.G3PreElecCountYYNew + "户" + data.G3PreElecSumYYNew + "元</td>" +
				"<td>" + data.G3OweElecCountYYNew + "户" + data.G3OweElecSumYYNew + "元</td>" +
				"<td>" + data.G3TotalhuNew+ "户" + data.G3TotalyuNew + "元</td>";
            
            

            
           str9 += "<td>乌民用</td>" +
				"<td>" + data.WMPreElecCountYYOld + "户" + data.WMPreElecSumYYOld + "元</td>" +
				"<td>" + data.WMOweElecCountYYOld + "户" + data.WMOweElecSumYYOld + "元</td>" +
				"<td>" + data.WMTotalhuOld+ "户" + data.WMTotalyuOld + "元</td>" +
				"<td>" + data.WMElecPrice + "</td>" +
				"<td>" + data.WMElecAmountYY + "</td>" +
				"<td>" + data.WMElecCostYYCount+ "户" + data.WMElecCostYYSum + "元</td>" +
				"<td>" + data.WMElecLogYYCount+ "户" + data.WMElecLogYYSum+ "元</td>" +
				"<td>" + data.WMPreElecCountYYNew + "户" + data.WMPreElecSumYYNew + "元</td>" +
				"<td>" + data.WMOweElecCountYYNew + "户" + data.WMOweElecSumYYNew + "元</td>" +
				"<td>" + data.WMTotalhuNew+ "户" + data.WMTotalyuNew + "元</td>";
            
            

            
            
            
            
            
           str10 += "<td>乌商业</td>" +
				"<td>" + data.WSPreElecCountYYOld + "户" + data.WSPreElecSumYYOld + "元</td>" +
				"<td>" + data.WSOweElecCountYYOld + "户" + data.WSOweElecSumYYOld + "元</td>" +
				"<td>" + data.WSTotalhuOld+ "户" + data.WSTotalyuOld + "元</td>" +
				"<td>" + data.WSElecPrice + "</td>" +
				"<td>" + data.WSElecAmountYY + "</td>" +
				"<td>" + data.WSElecCostYYCount+ "户" + data.WSElecCostYYSum + "元</td>" +
				"<td>" + data.WSElecLogYYCount+ "户" + data.WSElecLogYYSum+ "元</td>" +
				"<td>" + data.WSPreElecCountYYNew + "户" + data.WSPreElecSumYYNew + "元</td>" +
				"<td>" + data.WSOweElecCountYYNew + "户" + data.WSOweElecSumYYNew + "元</td>" +
				"<td>" + data.WSTotalhuNew+ "户" + data.WSTotalyuNew + "元</td>";
            

            
            
            
           str11 += "<td>乌工业</td>" +
				"<td>" + data.WGPreElecCountYYOld + "户" + data.WGPreElecSumYYOld + "元</td>" +
				"<td>" + data.WGOweElecCountYYOld + "户" + data.WGOweElecSumYYOld + "元</td>" +
				"<td>" + data.WGTotalhuOld+ "户" + data.WGTotalyuOld + "元</td>" +
				"<td>" + data.WGElecPrice + "</td>" +
				"<td>" + data.WGElecAmountYY + "</td>" +
				"<td>" + data.WGElecCostYYCount+ "户" + data.WGElecCostYYSum + "元</td>" +
				"<td>" + data.WGElecLogYYCount+ "户" + data.WGElecLogYYSum+ "元</td>" +
				"<td>" + data.WGPreElecCountYYNew + "户" + data.WGPreElecSumYYNew + "元</td>" +
				"<td>" + data.WGOweElecCountYYNew + "户" + data.WGOweElecSumYYNew + "元</td>" +
				"<td>" + data.WGTotalhuNew+ "户" + data.WGTotalyuNew + "元</td>";
            

            
            str12 += "<td>煤田民用</td>" +
				"<td>" + data.MMPreElecCountYYOld + "户" + data.MMPreElecSumYYOld + "元</td>" +
				"<td>" + data.MMOweElecCountYYOld + "户" + data.MMOweElecSumYYOld + "元</td>" +
				"<td>" + data.MMTotalhuOld+ "户" + data.MMTotalyuOld + "元</td>" +
				"<td>" + data.MMElecPrice + "</td>" +
				"<td>" + data.MMElecAmountYY + "</td>" +
				"<td>" + data.MMElecCostYYCount+ "户" + data.MMElecCostYYSum + "元</td>" +
				"<td>" + data.MMElecLogYYCount+ "户" + data.MMElecLogYYSum+ "元</td>" +
				"<td>" + data.MMPreElecCountYYNew + "户" + data.MMPreElecSumYYNew + "元</td>" +
				"<td>" + data.MMOweElecCountYYNew + "户" + data.MMOweElecSumYYNew + "元</td>" +
				"<td>" + data.MMTotalhuNew+ "户" + data.MMTotalyuNew + "元</td>";
            
       
            
            
           str13 += "<td>煤田商业</td>" +
				"<td>" + data.MSPreElecCountYYOld + "户" + data.MSPreElecSumYYOld + "元</td>" +
				"<td>" + data.MSOweElecCountYYOld + "户" + data.MSOweElecSumYYOld + "元</td>" +
				"<td>" + data.MSTotalhuOld+ "户" + data.MSTotalyuOld + "元</td>" +
				"<td>" + data.MSElecPrice + "</td>" +
				"<td>" + data.MSElecAmountYY + "</td>" +
				"<td>" + data.MSElecCostYYCount+ "户" + data.MSElecCostYYSum + "元</td>" +
				"<td>" + data.MSElecLogYYCount+ "户" + data.MSElecLogYYSum+ "元</td>" +
				"<td>" + data.MSPreElecCountYYNew + "户" + data.MSPreElecSumYYNew + "元</td>" +
				"<td>" + data.MSOweElecCountYYNew + "户" + data.MSOweElecSumYYNew + "元</td>" +
				"<td>" + data.MSTotalhuNew+ "户" + data.MSTotalyuNew + "元</td>";
            
            

          str14 += "<td>政府部门</td>" +
				"<td>" + data.ZPreElecCountYYOld + "户" + data.ZPreElecSumYYOld + "元</td>" +
				"<td>" + data.ZOweElecCountYYOld + "户" + data.ZOweElecSumYYOld + "元</td>" +
				"<td>" + data.ZTotalhuOld+ "户" + data.ZTotalyuOld + "元</td>" +
				"<td>" + data.ZElecPrice + "</td>" +
				"<td>" + data.ZElecAmountYY + "</td>" +
				"<td>" + data.ZElecCostYYCount+ "户" + data.ZElecCostYYSum + "元</td>" +
				"<td>" + data.ZElecLogYYCount+ "户" + data.ZElecLogYYSum+ "元</td>" +
				"<td>" + data.ZPreElecCountYYNew + "户" + data.ZPreElecSumYYNew + "元</td>" +
				"<td>" + data.ZOweElecCountYYNew + "户" + data.ZOweElecSumYYNew + "元</td>" +
				"<td>" + data.ZTotalhuNew+ "户" + data.ZTotalyuNew + "元</td>";
            
            
            

            
            
          str5 += "<td>合计</td>" +
	        	"<td>" + data.TotalPrehuOld +"户" + data.TotalPreyuOld+ "元</td>" +
	            "<td>" + data.TotalOwehuOld +"户" + data.TotalOweyuOld+ "元</td>" +
	            "<td>" + data.TotalAllhuOld+ "户" + data.TotalAllyuOld+ "元</td>" +
	            "<td>" + data.PriceTotal + "</td>" +
	            "<td>" + data.AmountTotalYY + "</td>" +
	            "<td>" + data.TotalCosthu+ "户" + data.TotalCostyu+ "元</td>" +
	            "<td>" + data.TotalLoghu+ "户" + data.TotalLogyu+ "元</td>" +
	        	"<td>" + data.TotalPrehuNew+ "户" + data.TotalPreyuNew+ "元</td>" +
	            "<td>" + data.TotalOwehuNew+ "户" + data.TotalOweyuNew+ "元</td>" +
	            "<td>" + data.TotalAllhuNew+ "户" + data.TotalAllyuNew+ "元</td>";
            
            



            str6 += "<td colspan='12' align='center'><div align='center'>全年实收总额:" + data.TotalLogyu+ "元</div></td>";

            
            if(data.type == 2){
                title.innerHTML = str1;
                Melec.innerHTML = str2;
                Selec.innerHTML = str3;
                G1elec.innerHTML = str4;
                G2elec.innerHTML = str7;
                G3elec.innerHTML = str8;
                WMelec.innerHTML = str9;
                WSelec.innerHTML = str10;
                WGelec.innerHTML = str11;
                MMelec.innerHTML = str12;
                MSelec.innerHTML = str13;
                Zelec.innerHTML = str14;
                Count.innerHTML = str5;
                footer.innerHTML = str6;
			}
			if(data.type == 3){
                title.innerHTML = str1;
                Melec.innerHTML = str2;
                Selec.innerHTML = str3;
                G1elec.innerHTML =str4;
                Count.innerHTML = str5;
                footer.innerHTML = str6;
                G2elec.innerHTML = "";
                G3elec.innerHTML = "";
                WMelec.innerHTML = "";
                WSelec.innerHTML = "";
                WGelec.innerHTML = "";
                MMelec.innerHTML = "";
                MSelec.innerHTML = "";
                Zelec.innerHTML = "";
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
        fontSize :14,
        bold : 1,
        htm1 : '0',
        htm2 : '0',
        htm3 : '100%',
        htm4 : '100%',
        htm5 : null,
        type : '0'
    }, data || {});

    if(!d.htm5){
        var strStyleCss = '<link href="/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">'+
            '<link rel="stylesheet" href="/js/plugins/layer/theme/default/layer.css?v=3.1.0" id="layuicss-layer">'+
            '<link href="/css/style.css?v=4.1.0" rel="stylesheet">'+
		    '<style type="text/css">'+
		     '	#tableBox1 tr td{width:100px;height:46px;ling-height:46px;text-align:center;}'+
		     '	#tableBox2 tr td{width:100px;height:46px;ling-height:46px;text-align:center;}'+
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
            LODOP.SET_PRINT_PAGESIZE(2,2400,3000,"电年统计表格");
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

$('#print').on('click', function(){
    _printView({
        title:'电年统计表格',
        fontSize : 18,
        type:'1',
        htm1 : 0,
        htm2 : 1,
        htm3 : 2400,
        htm4 : 30000,
        htm5:null
    });
});











function dowloadElecYY() {
	$.tzLoading({content:'正在下载，请稍等...'});
    $("#tableBox2").table2excel({
        exclude: ".noExl",
        filename: "电费年统计表格",
    });
	LoadingClose();
}


function exportElecYY(){

	$.tzLoading({content:'正在下载，请稍等...'});
    var createTime = $('#createTime').val();
    var userOrg  = $('#userOrg').val();
    var userType  = $('#userType').val();
    var elecType  = $('#elecType').val();
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
    
    window.location.href = prefix + '/exportElecYY?createTime='+createTime+'&userOrg='+userOrg+'&userType='+userType+'&elecType='+elecType+'&userId='+userId;
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