var prefix = "/charts/countYY";

$(function () {
    loadType('userType', 'userType');

    var chart = Highcharts.chart('container',{
		
		chart: {
	        type: 'column'
	    },
	    title: {
	        text: '2019水电暖收费报表'
	    },
	    subtitle: {
	        text: '数据来源: www.59jt.com'
	    },
	    xAxis: {
	        categories: [
	            '一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'
	        ],
	        crosshair: true
	    },
	    yAxis: {
	        min:0,
	        title: {
	            text: '金额(元)'
	        }
	    },
	    tooltip: {
	        // head + 每个 point + footer 拼接成完整的 table
	        headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
	        pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
	        '<td style="padding:0"><b>{point.y:.1f}万元</b></td></tr>',
	        footerFormat: '</table>',
	        shared: true,
	        useHTML: true
	    },
	    plotOptions: {
	        column: {
	            borderWidth: 0
	        }
	    },
	    series: [{
	        name: '水费',
	        data: [49.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6, 148.5, 216.4, 194.1, 95.6, 54.4]
	    }, {
	        name: '暖费',
	        data: [83.6, 78.8, 98.5, 93.4, 106.0, 84.5, 105.0, 104.3, 91.2, 83.5, 106.6, 92.3]
	    }, {
	        name: '电费',
	        data: [48.9, 38.8, 39.3, 41.4, 47.0, 48.3, 59.0, 59.6, 52.4, 65.2, 59.3, 51.2]
	    }]
	});

});



function countYY() {
    $.tzLoading({content: '正在统计，请稍等...'});

    var createTime = $('#createTime').val();
    var userType = $('#userType').val();
    var userOrg  = $('#userOrg').val();
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


    var formData = new FormData($('#countYYForm')[0]);

    $.ajax({
        cache: false,
        type: "POST",
        url: prefix + '/getCountYY',
        data : formData,
        async: false,
        processData: false,
        contentType: false,
        error: function () {
        },
        success: function (data) {
            var chart = Highcharts.chart('container', {

                chart: {
                    type: 'column'
                },
                title: {
                    text: createTime + '水电暖全年收费报表',
                },
                subtitle: {
                    text: '水电暖收费总数 ' + data.totalCount + '户 ' + data.totalSum + '元'
                },
                xAxis: {
                    categories: [
                        '一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'
                    ],
                    crosshair: true
                },
                yAxis: {
                    min: 0,
                    title: {
                        text: '金额(元)'
                    }
                },
                tooltip: {
                    // head + 每个 point + footer 拼接成完整的 table
                    headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                    pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                    '<td style="padding:0"><b>{point.y:.1f}元</b></td></tr>',
                    footerFormat: '</table>',
                    shared: true,
                    useHTML: true
                },
                plotOptions: {
                    column: {
                        borderWidth: 0
                    }
                },
                series: [{
                    name: '电费',
                    data: [data.elecLogSum1,data.elecLogSum2,data.elecLogSum3,data.elecLogSum4,data.elecLogSum5,data.elecLogSum6,data.elecLogSum7,data.elecLogSum8,data.elecLogSum9,data.elecLogSum10,data.elecLogSum11,data.elecLogSum12]
                }, {
                    name: '暖费',
                    data: [data.heatLogSum1,data.heatLogSum2,data.heatLogSum3,data.heatLogSum4,data.heatLogSum5,data.heatLogSum6,data.heatLogSum7,data.heatLogSum8,data.heatLogSum9,data.heatLogSum10,data.heatLogSum11,data.heatLogSum12]
                }, {
                    name: '水费',
                    data: [data.waterLogSum1,data.waterLogSum2,data.waterLogSum3,data.waterLogSum4,data.waterLogSum5,data.waterLogSum6,data.waterLogSum7,data.waterLogSum8,data.waterLogSum9,data.waterLogSum10,data.waterLogSum11,data.waterLogSum12]
                }]
            });
            LoadingClose();
        }
    });

}



function exportYY(){
	
	$.tzLoading({content:'正在下载，请稍等...'});
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
    
    window.location.href = prefix + '/exportYY?createTime='+createTime+'&userOrg='+userOrg+'&userType='+userType+'&userId='+userId;
    	LoadingClose();
}



function loadType(id, dicType) {
    var html = "";
    $.ajax({
        url: '/common/dict/list/' + dicType,
        success: function (data) {
            //加载数据
            for (var i = 0; i < data.length; i++) {
                html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
            }
            $("#" + id).append(html);
            $("#" + id).chosen({
                maxHeight: 200
            });
            //点击事件
            $('#' + id).on('change', function (e, params) {

                var opt = {
                    query: {
                        type: params.selected,
                    }
                }
                $('#container').bootstrapTable('refresh', opt);
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