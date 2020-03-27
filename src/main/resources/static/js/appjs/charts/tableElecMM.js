var prefix = "/charts/tableElecMM";

$(function () {
	loadType('userType','userType');
	loadType('elecType','elecType');
});


function tableElecMM() {

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


    var formData = new FormData($('#tableElecMMForm')[0]);

    $.ajax({
        cache: false,
        type: "POST",
        url: prefix + '/tableElecMM',
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
        	
            
            
            
            str1 += "<td colspan='11' align='center'>" + createTime+userOrg+userType+userId+ "电月统计报表</td>";

            
            
            
           str2 += "<td align='center'>民用电</td>" +
        	"<td align='center'>" + data.MPreElecCountMMOld + "户" + data.MPreElecSumMMOld + "元</td>" +
            "<td align='center'>" + data.MOweElecCountMMOld + "户" + data.MOweElecSumMMOld + "元</td>" +
            "<td align='center'>" + data.MTotalhuOld+ "户" + data.MTotalyuOld + "元</td>" +
            "<td align='center'>" + data.MElecPrice + "</td>" +
            "<td align='center'>" + data.MElecAmountMM + "</td>" +
            "<td align='center'>" + data.MElecCostMMCount+ "户" + data.MElecCostMMSum + "元</td>" +
            "<td align='center'>" + data.MElecLogMMCount+ "户" + data.MElecLogMMSum+ "元</td>" +
          	"<td align='center'>" + data.MPreElecCountMMNew + "户" + data.MPreElecSumMMNew + "元</td>" +
            "<td align='center'>" + data.MOweElecCountMMNew + "户" + data.MOweElecSumMMNew + "元</td>" +
            "<td align='center'>" + data.MTotalhuNew+ "户" + data.MTotalyuNew + "元</td>";
            
       
            
           
           
	        str3 += "<td align='center'>商业电</td>" +
        	"<td align='center'>" + data.SPreElecCountMMOld + "户" + data.SPreElecSumMMOld + "元</td>" +
            "<td align='center'>" + data.SOweElecCountMMOld + "户" + data.SOweElecSumMMOld + "元</td>" +
            "<td align='center'>" + data.STotalhuOld+ "户" + data.STotalyuOld + "元</td>" +
            "<td align='center'>" + data.SElecPrice + "</td>" +
            "<td align='center'>" + data.SElecAmountMM + "</td>" +
            "<td align='center'>" + data.SElecCostMMCount+ "户" + data.SElecCostMMSum + "元</td>" +
            "<td align='center'>" + data.SElecLogMMCount+ "户" + data.SElecLogMMSum+ "元</td>" +
          	"<td align='center'>" + data.SPreElecCountMMNew + "户" + data.SPreElecSumMMNew + "元</td>" +
            "<td align='center'>" + data.SOweElecCountMMNew + "户" + data.SOweElecSumMMNew + "元</td>" +
            "<td align='center'>" + data.STotalhuNew+ "户" + data.STotalyuNew + "元</td>";   
                


	        
       str4 += "<td align='center'>工业电1</td>" +
        	"<td align='center'>" + data.G1PreElecCountMMOld + "户" + data.G1PreElecSumMMOld + "元</td>" +
            "<td align='center'>" + data.G1OweElecCountMMOld + "户" + data.G1OweElecSumMMOld + "元</td>" +
            "<td align='center'>" + data.G1TotalhuOld+ "户" + data.G1TotalyuOld + "元</td>" +
            "<td align='center'>" + data.G1ElecPrice + "</td>" +
            "<td align='center'>" + data.G1ElecAmountMM + "</td>" +
            "<td align='center'>" + data.G1ElecCostMMCount+ "户" + data.G1ElecCostMMSum + "元</td>" +
            "<td align='center'>" + data.G1ElecLogMMCount+ "户" + data.G1ElecLogMMSum+ "元</td>" +
          	"<td align='center'>" + data.G1PreElecCountMMNew + "户" + data.G1PreElecSumMMNew + "元</td>" +
            "<td align='center'>" + data.G1OweElecCountMMNew + "户" + data.G1OweElecSumMMNew + "元</td>" +
            "<td align='center'>" + data.G1TotalhuNew+ "户" + data.G1TotalyuNew + "元</td>";
	        
	        
	        

	        
	        
            str7 += "<td align='center'>工业电2</td>" +
        	"<td align='center'>" + data.G2PreElecCountMMOld + "户" + data.G2PreElecSumMMOld + "元</td>" +
            "<td align='center'>" + data.G2OweElecCountMMOld + "户" + data.G2OweElecSumMMOld + "元</td>" +
            "<td align='center'>" + data.G2TotalhuOld+ "户" + data.G2TotalyuOld + "元</td>" +
            "<td align='center'>" + data.G2ElecPrice + "</td>" +
            "<td align='center'>" + data.G2ElecAmountMM + "</td>" +
            "<td align='center'>" + data.G2ElecCostMMCount+ "户" + data.G2ElecCostMMSum + "元</td>" +
            "<td align='center'>" + data.G2ElecLogMMCount+ "户" + data.G2ElecLogMMSum+ "元</td>" +
          	"<td align='center'>" + data.G2PreElecCountMMNew + "户" + data.G2PreElecSumMMNew + "元</td>" +
            "<td align='center'>" + data.G2OweElecCountMMNew + "户" + data.G2OweElecSumMMNew + "元</td>" +
            "<td align='center'>" + data.G2TotalhuNew+ "户" + data.G2TotalyuNew + "元</td>";
	        
	        

            
            
            
          str8 += "<td align='center'>工业电3</td>" +
			"<td align='center'>" + data.G3PreElecCountMMOld + "户" + data.G3PreElecSumMMOld + "元</td>" +
			"<td align='center'>" + data.G3OweElecCountMMOld + "户" + data.G3OweElecSumMMOld + "元</td>" +
			"<td align='center'>" + data.G3TotalhuOld+ "户" + data.G3TotalyuOld + "元</td>" +
			"<td align='center'>" + data.G3ElecPrice + "</td>" +
			"<td align='center'>" + data.G3ElecAmountMM + "</td>" +
			"<td align='center'>" + data.G3ElecCostMMCount+ "户" + data.G3ElecCostMMSum + "元</td>" +
			"<td align='center'>" + data.G3ElecLogMMCount+ "户" + data.G3ElecLogMMSum+ "元</td>" +
			"<td align='center'>" + data.G3PreElecCountMMNew + "户" + data.G3PreElecSumMMNew + "元</td>" +
			"<td align='center'>" + data.G3OweElecCountMMNew + "户" + data.G3OweElecSumMMNew + "元</td>" +
			"<td align='center'>" + data.G3TotalhuNew+ "户" + data.G3TotalyuNew + "元</td>";
            
            

            
            
            
           str9 += "<td align='center'>乌民用</td>" +
			"<td align='center'>" + data.WMPreElecCountMMOld + "户" + data.WMPreElecSumMMOld + "元</td>" +
			"<td align='center'>" + data.WMOweElecCountMMOld + "户" + data.WMOweElecSumMMOld + "元</td>" +
			"<td align='center'>" + data.WMTotalhuOld+ "户" + data.WMTotalyuOld + "元</td>" +
			"<td align='center'>" + data.WMElecPrice + "</td>" +
			"<td align='center'>" + data.WMElecAmountMM + "</td>" +
			"<td align='center'>" + data.WMElecCostMMCount+ "户" + data.WMElecCostMMSum + "元</td>" +
			"<td align='center'>" + data.WMElecLogMMCount+ "户" + data.WMElecLogMMSum+ "元</td>" +
			"<td align='center'>" + data.WMPreElecCountMMNew + "户" + data.WMPreElecSumMMNew + "元</td>" +
			"<td align='center'>" + data.WMOweElecCountMMNew + "户" + data.WMOweElecSumMMNew + "元</td>" +
			"<td align='center'>" + data.WMTotalhuNew+ "户" + data.WMTotalyuNew + "元</td>";
            
           
            
            
            
          str10 += "<td align='center'>乌商业</td>" +
			"<td align='center'>" + data.WSPreElecCountMMOld + "户" + data.WSPreElecSumMMOld + "元</td>" +
			"<td align='center'>" + data.WSOweElecCountMMOld + "户" + data.WSOweElecSumMMOld + "元</td>" +
			"<td align='center'>" + data.WSTotalhuOld+ "户" + data.WSTotalyuOld + "元</td>" +
			"<td align='center'>" + data.WSElecPrice + "</td>" +
			"<td align='center'>" + data.WSElecAmountMM + "</td>" +
			"<td align='center'>" + data.WSElecCostMMCount+ "户" + data.WSElecCostMMSum + "元</td>" +
			"<td align='center'>" + data.WSElecLogMMCount+ "户" + data.WSElecLogMMSum+ "元</td>" +
			"<td align='center'>" + data.WSPreElecCountMMNew + "户" + data.WSPreElecSumMMNew + "元</td>" +
			"<td align='center'>" + data.WSOweElecCountMMNew + "户" + data.WSOweElecSumMMNew + "元</td>" +
			"<td align='center'>" + data.WSTotalhuNew+ "户" + data.WSTotalyuNew + "元</td>";
            
            

            
            str11 += "<td align='center'>乌工业</td>" +
			"<td align='center'>" + data.WGPreElecCountMMOld + "户" + data.WGPreElecSumMMOld + "元</td>" +
			"<td align='center'>" + data.WGOweElecCountMMOld + "户" + data.WGOweElecSumMMOld + "元</td>" +
			"<td align='center'>" + data.WGTotalhuOld+ "户" + data.WGTotalyuOld + "元</td>" +
			"<td align='center'>" + data.WGElecPrice + "</td>" +
			"<td align='center'>" + data.WGElecAmountMM + "</td>" +
			"<td align='center'>" + data.WGElecCostMMCount+ "户" + data.WGElecCostMMSum + "元</td>" +
			"<td align='center'>" + data.WGElecLogMMCount+ "户" + data.WGElecLogMMSum+ "元</td>" +
			"<td align='center'>" + data.WGPreElecCountMMNew + "户" + data.WGPreElecSumMMNew + "元</td>" +
			"<td align='center'>" + data.WGOweElecCountMMNew + "户" + data.WGOweElecSumMMNew + "元</td>" +
			"<td align='center'>" + data.WGTotalhuNew+ "户" + data.WGTotalyuNew + "元</td>";
            
            
            

            
            
            
           str12 += "<td align='center'>煤田民用</td>" +
			"<td align='center'>" + data.MMPreElecCountMMOld + "户" + data.MMPreElecSumMMOld + "元</td>" +
			"<td align='center'>" + data.MMOweElecCountMMOld + "户" + data.MMOweElecSumMMOld + "元</td>" +
			"<td align='center'>" + data.MMTotalhuOld+ "户" + data.MMTotalyuOld + "元</td>" +
			"<td align='center'>" + data.MMElecPrice + "</td>" +
			"<td align='center'>" + data.MMElecAmountMM + "</td>" +
			"<td align='center'>" + data.MMElecCostMMCount+ "户" + data.MMElecCostMMSum + "元</td>" +
			"<td align='center'>" + data.MMElecLogMMCount+ "户" + data.MMElecLogMMSum+ "元</td>" +
			"<td align='center'>" + data.MMPreElecCountMMNew + "户" + data.MMPreElecSumMMNew + "元</td>" +
			"<td align='center'>" + data.MMOweElecCountMMNew + "户" + data.MMOweElecSumMMNew + "元</td>" +
			"<td align='center'>" + data.MMTotalhuNew+ "户" + data.MMTotalyuNew + "元</td>";
            
            

            
            
           str13 += "<td align='center'>煤田商业</td>" +
			"<td align='center'>" + data.MSPreElecCountMMOld + "户" + data.MSPreElecSumMMOld + "元</td>" +
			"<td align='center'>" + data.MSOweElecCountMMOld + "户" + data.MSOweElecSumMMOld + "元</td>" +
			"<td align='center'>" + data.MSTotalhuOld+ "户" + data.MSTotalyuOld + "元</td>" +
			"<td align='center'>" + data.MSElecPrice + "</td>" +
			"<td align='center'>" + data.MSElecAmountMM + "</td>" +
			"<td align='center'>" + data.MSElecCostMMCount+ "户" + data.MSElecCostMMSum + "元</td>" +
			"<td align='center'>" + data.MSElecLogMMCount+ "户" + data.MSElecLogMMSum+ "元</td>" +
			"<td align='center'>" + data.MSPreElecCountMMNew + "户" + data.MSPreElecSumMMNew + "元</td>" +
			"<td align='center'>" + data.MSOweElecCountMMNew + "户" + data.MSOweElecSumMMNew + "元</td>" +
			"<td align='center'>" + data.MSTotalhuNew+ "户" + data.MSTotalyuNew + "元</td>";
            
            

            
            

            
            
            
          str14 += "<td align='center'>政府部门</td>" +
			"<td align='center'>" + data.ZPreElecCountMMOld + "户" + data.ZPreElecSumMMOld + "元</td>" +
			"<td align='center'>" + data.ZOweElecCountMMOld + "户" + data.ZOweElecSumMMOld + "元</td>" +
			"<td align='center'>" + data.ZTotalhuOld+ "户" + data.ZTotalyuOld + "元</td>" +
			"<td align='center'>" + data.ZElecPrice + "</td>" +
			"<td align='center'>" + data.ZElecAmountMM + "</td>" +
			"<td align='center'>" + data.ZElecCostMMCount+ "户" + data.ZElecCostMMSum + "元</td>" +
			"<td align='center'>" + data.ZElecLogMMCount+ "户" + data.ZElecLogMMSum+ "元</td>" +
			"<td align='center'>" + data.ZPreElecCountMMNew + "户" + data.ZPreElecSumMMNew + "元</td>" +
			"<td align='center'>" + data.ZOweElecCountMMNew + "户" + data.ZOweElecSumMMNew + "元</td>" +
			"<td align='center'>" + data.ZTotalhuNew+ "户" + data.ZTotalyuNew + "元</td>";
            
            
            
            

            
            
            str5 += "<td align='center'>合计</td>" +
        	"<td align='center'>" + data.TotalPrehuOld +"户" + data.TotalPreyuOld+ "元</td>" +
            "<td align='center'>" + data.TotalOwehuOld +"户" + data.TotalOweyuOld+ "元</td>" +
            "<td align='center'>" + data.TotalAllhuOld+ "户" + data.TotalAllyuOld+ "元</td>" +
            "<td align='center'>" + data.PriceTotal + "</td>" +
            "<td align='center'>" + data.AmountTotalMM + "</td>" +
            "<td align='center'>" + data.TotalCosthu+ "户" + data.TotalCostyu+ "元</td>" +
            "<td align='center'>" + data.TotalLoghu+ "户" + data.TotalLogyu+ "元</td>" +
        	"<td align='center'>" + data.TotalPrehuNew+ "户" + data.TotalPreyuNew+ "元</td>" +
            "<td align='center'>" + data.TotalOwehuNew+ "户" + data.TotalOweyuNew+ "元</td>" +
            "<td align='center'>" + data.TotalAllhuNew+ "户" + data.TotalAllyuNew+ "元</td>";
            
            
            


            str6 += "<td colspan='11' align='center'><div align='center'>本月实收总额:" + data.TotalLogyu+ "元</div></td>";

            
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
            LODOP.SET_PRINT_PAGESIZE(2,2400,3000,"电月统计表格");
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
        title:'电月统计表格',
        fontSize : 18,
        type:'1',
        htm1 : 0,
        htm2 : 1,
        htm3 : 2400,
        htm4 : 3000,
        htm5:null
    });
});








function exportElecMM(){

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
    
    window.location.href = prefix + '/exportElecMM?createTime='+createTime+'&userOrg='+userOrg+'&userType='+userType+'&elecType='+elecType+'&userId='+userId;
    	LoadingClose();
}

function dowloadElecMM() {
	$.tzLoading({content:'正在下载，请稍等...'});
    $("#tableBox2").table2excel({
        exclude: ".noExl",
        filename: "电费月统计表格",
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