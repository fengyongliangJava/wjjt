var prefix = "/charts/countDD";

$(function () {
    loadType('userType', 'userType');

    var myChart = echarts.init(document.getElementById('box'));

    var weatherIcons = {
        'Sunny': '/img/ziyuan.png',
        'Cloudy': '/img/shandian.png',
        'Showers': '/img/huo.png'

    };

    option = {
        title: {
            text: '2019水电暖当日收费报表',
            subtext: '水电暖收费总数  9999户 9999元',
            left: 'center'
        },
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            // orient: 'vertical',
            // top: 'middle',
            bottom: 10,
            left: 'center',
            data: ['电费', '水费', '暖费']
        },
        series: [
            {
                type: 'pie',
                radius: '65%',
                center: ['50%', '50%'],
                selectedMode: 'single',
                data: [
                    {
                        value: 50,
                        name: '电费',
                        label: {
                            normal: {
                                formatter: [
                                    '{title|{b}}{abg|}',
                                    '  {weatherHead|类型}{valueHead|户数}{rateHead|金额}',
                                    '{hr|}',
                                    '  {Cloudy|}{value|3}{rate|200}',
                                    '  {Sunny|}{value|4}{rate|300}',
                                    '  {Showers|}{value|5}{rate|400}'
                                ].join('\n'),
                                backgroundColor: '#eee',
                                borderColor: '#777',
                                borderWidth: 1,
                                borderRadius: 4,
                                rich: {
                                    title: {
                                        color: '#eee',
                                        align: 'center'
                                    },
                                    abg: {
                                        backgroundColor: '#333',
                                        width: '100%',
                                        align: 'right',
                                        height: 25,
                                        borderRadius: [4, 4, 0, 0]
                                    },
                                    Sunny: {
                                        height: 30,
                                        align: 'left',
                                        backgroundColor: {
                                            image: weatherIcons.Sunny
                                        }
                                    },
                                    Cloudy: {
                                        height: 30,
                                        align: 'left',
                                        backgroundColor: {
                                            image: weatherIcons.Cloudy
                                        }
                                    },
                                    Showers: {
                                        height: 30,
                                        align: 'left',
                                        backgroundColor: {
                                            image: weatherIcons.Showers
                                        }
                                    },
                                    weatherHead: {
                                        color: '#333',
                                        height: 24,
                                        align: 'left'
                                    },
                                    hr: {
                                        borderColor: '#777',
                                        width: '100%',
                                        borderWidth: 0.5,
                                        height: 0
                                    },
                                    value: {
                                        width: 20,
                                        padding: [0, 20, 0, 30],
                                        align: 'left'
                                    },
                                    valueHead: {
                                        color: '#333',
                                        width: 20,
                                        padding: [0, 20, 0, 30],
                                        align: 'center'
                                    },
                                    rate: {
                                        width: 40,
                                        align: 'right',
                                        padding: [0, 10, 0, 0]
                                    },
                                    rateHead: {
                                        color: '#333',
                                        width: 40,
                                        align: 'center',
                                        padding: [0, 10, 0, 0]
                                    }
                                }
                            }
                        }
                    },
                    {value: 50, name: '水费'},
                    {value: 50, name: '暖费'}
                ],
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };


    myChart.setOption(option);


});


function countDD() {

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


    var formData = new FormData($('#countDDForm')[0]);

    $.ajax({
        cache: false,
        type: "POST",
        url: prefix + '/getCountDD',
        data : formData,
        async: false,
        processData: false,
        contentType: false,
        error: function () {
        },
        success: function (data) {
            LoadingClose();
            var myChart = echarts.init(document.getElementById('box'));

            var weatherIcons = {
                'Sunny': '/img/ziyuan.png',
                'Cloudy': '/img/shandian.png',
                'Showers': '/img/huo.png'

            };

            if(data == null){
            	alert("当日没有缴费记录!");
            	return;
            }
            

            option = {
                title: {
                    text: createTime + '水电暖当日收费报表',
                    subtext: '水电暖收费总数 ' + data.totalCount + '户 ' + data.totalSum + '元',
                    left: 'center'
                },
                tooltip: {
                    trigger: 'item',
                    formatter: "{a} <br/>{b} : {c} ({d}%)"
                },
                legend: {
                    bottom: 10,
                    left: 'center',
                    data: ['电费', '水费', '暖费']
                },
                series: [
                    {
                        type: 'pie',
                        radius: '65%',
                        center: ['50%', '50%'],
                        selectedMode: 'single',
                        data:[
                            {
                                value:data.elecLogSum,
                                name: '电费',
                                label: {
                                    normal: {
                                        formatter: [
                                            '{title|{b}}{abg|}',
                                            '  {weatherHead|类型}{valueHead|户数}{rateHead|金额}',
                                            '{hr|}',
                                            '  {Cloudy|}{value|'+data.elecLogCount+'}{rate|'+data.elecLogSum+'}',
                                            '  {Sunny|}{value|'+data.waterLogCount+'}{rate|'+data.waterLogSum+'}',
                                            '  {Showers|}{value|'+data.heatLogCount+'}{rate|'+data.heatLogSum+'}'
                                        ].join('\n'),
                                        backgroundColor: '#eee',
                                        borderColor: '#777',
                                        borderWidth: 1,
                                        borderRadius: 4,
                                        rich: {
                                            title: {
                                                color: '#eee',
                                                align: 'center'
                                            },
                                            abg: {
                                                backgroundColor: '#333',
                                                width: '100%',
                                                align: 'right',
                                                height: 25,
                                                borderRadius: [4, 4, 0, 0]
                                            },
                                            Sunny: {
                                                height: 30,
                                                align: 'left',
                                                backgroundColor: {
                                                    image: weatherIcons.Sunny
                                                }
                                            },
                                            Cloudy: {
                                                height: 30,
                                                align: 'left',
                                                backgroundColor: {
                                                    image: weatherIcons.Cloudy
                                                }
                                            },
                                            Showers: {
                                                height: 30,
                                                align: 'left',
                                                backgroundColor: {
                                                    image: weatherIcons.Showers
                                                }
                                            },
                                            weatherHead: {
                                                color: '#333',
                                                height: 24,
                                                align: 'left'
                                            },
                                            hr: {
                                                borderColor: '#777',
                                                width: '100%',
                                                borderWidth: 0.5,
                                                height: 0
                                            },
                                            value: {
                                                width: 20,
                                                padding: [0, 20, 0, 30],
                                                align: 'left'
                                            },
                                            valueHead: {
                                                color: '#333',
                                                width: 20,
                                                padding: [0, 20, 0, 30],
                                                align: 'center'
                                            },
                                            rate: {
                                                width: 40,
                                                align: 'right',
                                                padding: [0, 10, 0, 0]
                                            },
                                            rateHead: {
                                                color: '#333',
                                                width: 40,
                                                align: 'center',
                                                padding: [0, 10, 0, 0]
                                            }
                                        }
                                    }
                                }
                            },
                            {value:data.heatLogSum, name:'暖费'},
                            {value:data.waterLogSum, name:'水费'}
                        ],
                        itemStyle: {
                            emphasis: {
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                            }
                        }
                    }
                ]
            };
            myChart.setOption(option);

        }
    });

}











function exportDD(){
	
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
    
    window.location.href = prefix + '/exportDD?createTime='+createTime+'&userOrg='+userOrg+'&userType='+userType+'&userId='+userId;
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