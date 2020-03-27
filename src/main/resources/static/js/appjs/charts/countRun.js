 var prefix = "/charts/countRun";
 
 $(function() {
		loadType('userType','userType');
	    var myChart = echarts.init(document.getElementById('box'));
	    setTimeout(function () {
	    	
	        option = {
	            legend: {},
	            tooltip: {
	                trigger: 'axis',
	                showContent: false
	            },
	            dataset: {
	                source: [
	                    ['product', '一月份', '二月份', '三月份', '四月份', '五月份', '六月份','七月份', '八月份', '九月份', '十月份', '十一月份', '十二月份'],
	                    ['电费', 41.1, 30.4, 65.1, 53.3, 83.8, 98.7,41.1, 30.4, 65.1, 53.3, 83.8, 98.7],
	                    ['暖费', 86.5, 92.1, 85.7, 83.1, 73.4, 55.1,46.1, 34.4, 66.1, 58.3, 86.8, 88.7],
	                    ['水费', 24.1, 67.2, 79.5, 86.4, 65.2, 82.5,44.1, 36.4, 67.1, 59.3, 82.8, 78.7]
	       
	                ]
	            },
	            xAxis: {type: 'category'},
	            yAxis: {gridIndex: 0},
	            grid: {top: '55%'},
	            series: [
	                {type: 'line', smooth: true, seriesLayoutBy: 'row'},
	                {type: 'line', smooth: true, seriesLayoutBy: 'row'},
	                {type: 'line', smooth: true, seriesLayoutBy: 'row'},
	           
	                {
	                    type: 'pie',
	                    id: 'pie',
	                    radius: '30%',
	                    center: ['50%', '25%'],
	                    label: {
	                        formatter: '{b}: {@2012} ({d}%)'
	                    },
	                    encode: {
	                        itemName: 'product',
	                        value: '2012',
	                        tooltip: '2012'
	                    }
	                }
	            ]
	        };

	        myChart.on('updateAxisPointer', function (event) {
	            var xAxisInfo = event.axesInfo[0];
	            if (xAxisInfo) {
	                var dimension = xAxisInfo.value + 1;
	                myChart.setOption({
	                    series: {
	                        id: 'pie',
	                        label: {
	                            formatter: '{b}: {@[' + dimension + ']} ({d}%)'
	                        },
	                        encode: {
	                            value: dimension,
	                            tooltip: dimension
	                        }
	                    }
	                });
	            }
	        });

	        myChart.setOption(option);

	    });
	   		
	});





 function countRun() {
	 
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


     var formData = new FormData($('#countRunForm')[0]);

     $.ajax({
         cache: false,
         type: "POST",
         url: prefix + '/getCountRun',
         data : formData,
         async: false,
         processData: false,
         contentType: false,
         error: function () {
         },
         success: function (data) {
        	 
        		
        	 var myChart = echarts.init(document.getElementById('box'));
        	 

        	 setTimeout(function () {

        	     option = {
        	    		 
        	         legend: {},
        	         tooltip: {
        	             trigger: 'axis',
        	             showContent: false
        	         },
        	         dataset: {
        	             source: [
        	                 ['product', '一月份', '二月份', '三月份', '四月份', '五月份', '六月份','七月份', '八月份', '九月份', '十月份', '十一月份', '十二月份'],
        	                 ['电费', data.elecLogSum1,data.elecLogSum2,data.elecLogSum3,data.elecLogSum4,data.elecLogSum5,data.elecLogSum6,data.elecLogSum7,data.elecLogSum8,data.elecLogSum9,data.elecLogSum10,data.elecLogSum11,data.elecLogSum12],
        	                 ['暖费', data.heatLogSum1,data.heatLogSum2,data.heatLogSum3,data.heatLogSum4,data.heatLogSum5,data.heatLogSum6,data.heatLogSum7,data.heatLogSum8,data.heatLogSum9,data.heatLogSum10,data.heatLogSum11,data.heatLogSum12],
        	                 ['水费', data.waterLogSum1,data.waterLogSum2,data.waterLogSum3,data.waterLogSum4,data.waterLogSum5,data.waterLogSum6,data.waterLogSum7,data.waterLogSum8,data.waterLogSum9,data.waterLogSum10,data.waterLogSum11,data.waterLogSum12]
        	    
        	             ]
        	         },
        	         xAxis: {type: 'category'},
        	         yAxis: {gridIndex: 0},
        	         grid: {top: '55%'},
        	         series: [
        	             {type: 'line', smooth: true, seriesLayoutBy: 'row'},
        	             {type: 'line', smooth: true, seriesLayoutBy: 'row'},
        	             {type: 'line', smooth: true, seriesLayoutBy: 'row'},
        	        
        	             {
        	                 type: 'pie',
        	                 id: 'pie',
        	                 radius: '30%',
        	                 center: ['50%', '25%'],
        	                 label: {
        	                     formatter: '{b}: {@2012} ({d}%)'
        	                 },
        	                 encode: {
        	                     itemName: 'product',
        	                     value: '2012',
        	                     tooltip: '2012'
        	                 }
        	             }
        	         ]
        	     };

        	     myChart.on('updateAxisPointer', function (event) {
        	         var xAxisInfo = event.axesInfo[0];
        	         if (xAxisInfo) {
        	             var dimension = xAxisInfo.value + 1;
        	             myChart.setOption({
        	                 series: {
        	                     id: 'pie',
        	                     label: {
        	                         formatter: '{b}: {@[' + dimension + ']} ({d}%)'
        	                     },
        	                     encode: {
        	                         value: dimension,
        	                         tooltip: dimension
        	                     }
        	                 }
        	             });
        	         }
        	     });

        	     myChart.setOption(option);

        	 }); 
        	 
        	LoadingClose();
         }
           
     });

 }

 

function exportRun(){
 	
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
     
     window.location.href = prefix + '/exportRun?createTime='+createTime+'&userOrg='+userOrg+'&userType='+userType+'&userId='+userId;
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
				$('#box').bootstrapTable('refresh', opt);
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