var prefix = "/charts/tableDD";

$(function () {
	loadType('userType','userType');
});


function tableDD() {


    $.tzLoading({content: '正在统计，请稍等...'});
    
    var title = document.getElementById("title");
    var one = document.getElementById("one");
    var two = document.getElementById("two");
    var three = document.getElementById("three");
    var footer = document.getElementById("footer");
    
    
    var box1 = document.getElementById("box1");
    var box2 = document.getElementById("box2");

    var createTime = $('#createTime').val();
    var userOrg = $('#userOrg').val();
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


    var formData = new FormData($('#tableDDForm')[0]);

    $.ajax({
        cache: false,
        type: "POST",
        url: prefix + '/getTableDD',
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
        	
        	
        	
            var str1 = "";
            var str2 = "";
            var str3 = "";
            var str4 = "";
            var str5 = "";
            var str6 = "";
            var str7 = "";
            var str8 = "";
            
            
            
            str1 += "<td colspan='8' align='center'>" + createTime+userOrg+userId+ "当日收费报表</td>";
            title.innerHTML = str1;
            
            
            str2 += "<td align='center'>" + data.MelecLogCountA + "户" + data.MelecLogSumA + "元</td>" +
            "<td align='center'>" + data.MelecLogCountC + "户" + data.MelecLogSumC + "元</td>" +
            "<td align='center'>" + data.SelecLogCountA + "户" + data.SelecLogSumA + "元</td>" +
            "<td align='center'>" + data.SelecLogCountC + "户" + data.SelecLogSumC + "元</td>" +
            "<td align='center'>" + data.MheatLogCountA + "户" + data.MheatLogSumA + "元</td>" +
            "<td align='center'>" + data.MheatLogCountC + "户" + data.MheatLogSumC + "元</td>" +
            "<td align='center'>" + data.SheatLogCountA + "户" + data.SheatLogSumA + "元</td>" +
            "<td align='center'>" + data.SheatLogCountC + "户" + data.SheatLogSumC + "元</td>";
            one.innerHTML = str2;
            
            
            
            var userOrg1 = $('#userOrg').val();
            
			if (userOrg1 == "2") {
			    str3 += "<td colspan='2' align='center'>" + "乌镇电费</td>" + "<td colspan='2' align='center'>" + "煤田电费</td>" + "<td colspan='2' align='center'>" + "工业电费</td>" + "<td colspan='2' align='center'>" + "水费</td>" ;
	    	    two.innerHTML = str3;
			} else if (userOrg1 == "3") {
    	        str4 += "<td colspan='2' align='center'>" + "民用水费</td>" + "<td colspan='2' align='center'>" + "商业水费</td>" + "<td colspan='2' align='center'>" + "工业电费</td>" + "<td colspan='2' align='center'>" + "合计</td>" ;
 	            two.innerHTML = str4;
			}
            
			
            
			if (userOrg1 == "2") {
    		    str5 += "<td align='center'>" + data.WelecLogCountA + "户" + data.WelecLogSumA + "元</td>" +
                "<td align='center'>" + data.WelecLogCountC + "户" + data.WelecLogSumC + "元</td>" +
                "<td align='center'>" + data.TelecLogCountA + "户" + data.TelecLogSumA + "元</td>" +
                "<td align='center'>" + data.TelecLogCountC + "户" + data.TelecLogSumC + "元</td>" +
                "<td align='center'>" + data.GelecLogCountA + "户" + data.GelecLogSumA + "元</td>" +
                "<td align='center'>" + data.GelecLogCountC + "户" + data.GelecLogSumC + "元</td>" +
                "<td align='center'>" + data.waterLogCountA + "户" + data.waterLogSumA + "元</td>" +
                "<td align='center'>" + data.waterLogCountC + "户" + data.waterLogSumC + "元</td>";
    	        three.innerHTML = str5;
			} else if (userOrg1 == "3") {
    		    str6 += "<td align='center'>" + data.MwaterLogCountA + "户" + data.MwaterLogSumA + "元</td>" +
                "<td align='center'>" + data.MwaterLogCountC + "户" + data.MwaterLogSumC + "元</td>" +
                "<td align='center'>" + data.SwaterLogCountA + "户" + data.SwaterLogSumA + "元</td>" +
                "<td align='center'>" + data.SwaterLogCountC + "户" + data.SwaterLogSumC + "元</td>" +
                "<td align='center'>" + data.YXGelecLogCountA + "户" + data.YXGelecLogSumA + "元</td>" +
                "<td align='center'>" + data.YXGelecLogCountC + "户" + data.YXGelecLogSumC + "元</td>" +
                "<td align='center'>" + data.Xtotal + "户" + data.Xsum + "元</td>" +
    	        "<td align='center'>" + data.Wtotal + "户" + data.Wsum + "元</td>";
 	            three.innerHTML = str6;
			}

			
			
			if (userOrg1 == "2") {
	           str7 += "<td colspan='8' align='center'>当日实收总额:" + data.WJTotal + "户" + data.WJSum + "元</td>";
	            footer.innerHTML = str7;
			} else if (userOrg1 == "3") {
		       str8 += "<td colspan='8' align='center'>当日实收总额:" + data.YXTotal + "户" + data.YXSum  + "元</td>";
		        footer.innerHTML = str8;
			}

            
        	box2.style.display = 'block';
        	
            box1.style.display = 'none';
            
            LoadingClose();
        }
    });

}



function dowloadDD(){
	$.tzLoading({content:'正在下载，请稍等...'});
    $("#tableBox2").table2excel({
        exclude: ".noExl",
        filename: "日统计表格",
    });
	LoadingClose();
}




function exportDD(){
	
	$.tzLoading({content:'正在下载，请稍等...'});
    var createTime = $('#createTime').val();
    var userOrg  = $('#userOrg').val();
    var userType  = $('#userType').val();
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
    
    window.location.href = prefix + '/exportDD?createTime='+createTime+'&userOrg='+userOrg+'&userType='+userType+'&userId='+userId;
    	LoadingClose();
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
    d.title = d.title && d.title != '' ? '--'+d.title : ''
    var LODOP=getLodop(document.getElementById(d.ids),document.getElementById('LODOP_EM'));
    LODOP.PRINT_INIT(d.orgName+d.title);

    LODOP.ADD_PRINT_HTML(30,'0%','100%',50,"<div align='center'><strong>"+d.orgName+d.title+"</strong></div>");
    if(!d.htm5){
        var strStyleCss = '<link href="/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">'+
            '<link rel="stylesheet" href="/js/plugins/layer/theme/default/layer.css?v=3.1.0" id="layuicss-layer">'+
            '<link href="/css/style.css?v=4.1.0" rel="stylesheet">';
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
            LODOP.SET_PRINT_PAGESIZE(2,2400,3000,"日统计表格");
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
        title:'日统计表格',
        fontSize : 18,
        type:'1',
        htm1 : 88,
        htm2 : 100,
        htm3 : 2400,
        htm4 : 3000,
        htm5:null
    });
});






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
			});
		}
	});
}



