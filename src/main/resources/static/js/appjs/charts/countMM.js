var prefix = "/charts/countMM";

$(function() {
	loadType('userType','userType');
	var myChart = echarts.init(document.getElementById('box'));
	option = {
	    title: {
		        text:'水电暖当月收费报表',
		        subtext:'水电暖收费总数 ',
		        left: 'center'
		    },	
	    tooltip: {
	        trigger: 'item',
	        formatter: "{a} <br/>{b}: {c} ({d}%)"
	    },
	    legend: {
	        orient: 'vertical',
	        x: 'left',
	        data:['民用水','商业水1','商业水2','商业水3','民用暖','商业暖','工业民','工业商','矿民用','矿商用','矿工业1','矿工业2','矿工业3','乌民用','乌商业','乌工业','煤田民用','煤田商业','政府部门']
	    },
	    series: [
	        {
	            name:'收费明细',
	            type:'pie',
	            selectedMode: 'single',
	            radius: [0, '30%'],

	            label: {
	                normal: {
	                    position: 'inner'
	                }
	            },
	            labelLine: {
	                normal: {
	                    show: false
	                }
	            },
	            data:[
	                {value:335, name:'水费'},
	                {value:679, name:'暖费'},
	                {value:1548, name:'电费'}
	                
	            ]
	        },
	        {
	            name:'访问来源',
	            type:'pie',
	            radius: ['40%', '55%'],
	            label: {
	                normal: {
	                    formatter: '{a|{a}}{abg|}\n{hr|}\n  {b|{b}：}{c}  {per|{d}%}  ',
	                    backgroundColor: '#eee',
	                    borderColor: '#aaa',
	                    borderWidth: 1,
	                    borderRadius: 4,
	     
	                    rich: {
	                        a: {
	                            color: '#999',
	                            lineHeight: 22,
	                            align: 'center'
	                        },
	       
	                        hr: {
	                            borderColor: '#aaa',
	                            width: '100%',
	                            borderWidth: 0.5,
	                            height: 0
	                        },
	                        b: {
	                            fontSize: 16,
	                            lineHeight: 33
	                        },
	                        per: {
	                            color: '#eee',
	                            backgroundColor: '#334455',
	                            padding: [2, 4],
	                            borderRadius: 2
	                        }
	                    }
	                }
	            },
	            data:[
	                {value:335, name:'民用水'},
	                {value:315, name:'商业水1'},
	                {value:335, name:'商业水2'},
	                {value:315, name:'商业水3'},
	                {value:310, name:'民用暖'},
	                {value:234, name:'商业暖'},
	                {value:448, name:'工业民'},
	                {value:251, name:'工业商'},
	                {value:147, name:'矿民用'},
	                {value:102, name:'矿商业'},
	                {value:335, name:'矿工业1'},
	                {value:315, name:'矿工业2'},
	                {value:310, name:'矿工业3'},
	                {value:234, name:'乌民用'},
	                {value:448, name:'乌商业'},
	                {value:251, name:'乌工业'},
	                {value:147, name:'煤田民用'},
	                {value:102, name:'煤田商业'},
	                {value:102, name:'政府部门'}
	            ]
	        }
	    ]
	};
	myChart.setOption(option);
	
});





function countMM() {
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


    var formData = new FormData($('#countMMForm')[0]);

    $.ajax({
        cache: false,
        type: "POST",
        url: prefix + '/getCountMM',
        data : formData,
        async: false,
        processData: false,
        contentType: false,
        error: function () {
        },
        success: function (data) {

        	var myChart = echarts.init(document.getElementById('box'));


        	option = {
        	    title: {
    		            text: createTime+'水电暖当月收费报表',
    		            subtext:'水电暖收费总数 ' + data.totalCount + '户 ' + data.totalSum + '元'+'电费 ' + data.totalCountElec + '户 ' + data.totalSumElec + '元'+'暖费 ' + data.totalCountHeat + '户 ' + data.totalSumHeat + '元'+'水费 ' + data.totalCountWater + '户 ' + data.totalSumWater + '元',
        		        left: 'center'
        		    },	
        	    tooltip: {
        	        trigger: 'item',
        	        formatter: "{a} <br/>{b}: {c} ({d}%)"
        	    },
        	    legend: {
        	        orient: 'vertical',
        	        x: 'left',
        	        data:['民用水','商业水1','商业水2','商业水3','民用暖','商业暖','工业民','工业商','矿民用','矿商用','矿工业1','矿工业2','矿工业3','乌民用','乌商业','乌工业','煤田民用','煤田商业','政府部门']
        	    },
        	    series: [
        	        {
        	            name:'收费明细',
        	            type:'pie',
        	            selectedMode: 'single',
        	            radius: [0, '30%'],

        	            label: {
        	                normal: {
        	                    position: 'inner'
        	                }
        	            },
        	            labelLine: {
        	                normal: {
        	                    show: false
        	                }
        	            },
        	            data:[
        	                {value:data.totalSumWater, name:'水费'},
        	                {value:data.totalSumHeat, name:'暖费'},
        	                {value:data.totalSumElec, name:'电费'}
        	                
        	            ]
        	        },
        	        {
        	            name:'访问来源',
        	            type:'pie',
        	            radius: ['40%', '55%'],
        	            label: {
        	                normal: {
        	                    formatter: '{a|{a}}{abg|}\n{hr|}\n  {b|{b}：}{c}  {per|{d}%}  ',
        	                    backgroundColor: '#eee',
        	                    borderColor: '#aaa',
        	                    borderWidth: 1,
        	                    borderRadius: 4,
        	     
        	                    rich: {
        	                        a: {
        	                            color: '#999',
        	                            lineHeight: 22,
        	                            align: 'center'
        	                        },
        	       
        	                        hr: {
        	                            borderColor: '#aaa',
        	                            width: '100%',
        	                            borderWidth: 0.5,
        	                            height: 0
        	                        },
        	                        b: {
        	                            fontSize: 16,
        	                            lineHeight: 33
        	                        },
        	                        per: {
        	                            color: '#eee',
        	                            backgroundColor: '#334455',
        	                            padding: [2, 4],
        	                            borderRadius: 2
        	                        }
        	                    }
        	                }
        	            },
        	            data:[
        	            	
        	                {value:data.SumMWaterLog, name:'民用水'},
        	                {value:data.SumS1WaterLog, name:'商业水1'},
        	                {value:data.SumS2WaterLog, name:'商业水2'},
        	                {value:data.SumS3WaterLog, name:'商业水3'},
        	                {value:data.SumMHeatLog, name:'民用暖'},
        	                {value:data.SumSHeatLog, name:'商业暖'},
        	                {value:data.SumGMHeatLog, name:'工业民'},
        	                {value:data.SumGSHeatLog, name:'工业商'},
        	                {value:data.SumMElecLog, name:'矿民用'},
        	                {value:data.SumSElecLog, name:'矿商业'},
        	                {value:data.SumG1ElecLog, name:'矿工业1'},
        	                {value:data.SumG2ElecLog, name:'矿工业2'},
        	                {value:data.SumG3ElecLog, name:'矿工业3'},
        	                {value:data.SumWMElecLog, name:'乌民用'},
        	                {value:data.SumWSElecLog, name:'乌商业'},
        	                {value:data.SumWGElecLog, name:'乌工业'},
        	                {value:data.SumMMElecLog, name:'煤田民用'},
        	                {value:data.SumMSElecLog, name:'煤田商业'},
        	                {value:data.SumZElecLog, name:'政府部门'}
        	            ]
        	        }
        	    ]
        	};
        	
        	myChart.setOption(option);
        	LoadingClose();
        }
    });

}





function exportMM(){
	
	$.tzLoading({content:'正在下载，请稍等...'});
    var createTime = $('#createTime').val();
    var userType =  $('#userType').val();
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
    
    window.location.href = prefix + '/exportMM?createTime='+createTime+'&userOrg='+userOrg+'&userType='+userType+'&userId='+userId;
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
                $('#box').bootstrapTable('refresh', opt);
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

