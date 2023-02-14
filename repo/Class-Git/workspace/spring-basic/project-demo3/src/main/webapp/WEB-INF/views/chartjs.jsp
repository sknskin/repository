<%@page import="java.util.Date"%>
<%@page language="java" 
		contentType="text/html; charset=UTF-8"
    	pageEncoding="UTF-8"%>
    	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chart.js Demo</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet">


</head>
<body>

<div class="container">
	<button type="button" id="load-iris" class="btn btn-primary mt-3">load iris dataset</button>
	<button type="button" id="chart-demo-1" class="btn btn-primary mt-3">show chart</button>
	<hr>
	<canvas id="the-chart"></canvas>
</div>
<script src="https://code.jquery.com/jquery-3.6.1.js"></script>   
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script type="text/javascript">
$(function() {
	
	var dataset = null;
	
	$('#load-iris').on('click', function(event) {
		$.ajax({
			"url": "load-iris-dataset",
			"method": "get",
			"dataType": "json", // 수신하는 데이터 형식
			"success": function(data, status, xhr) {
				dataset = data;
				alert('데이터 준비 완료');
			},
			"error": function(xhr, status, err) {
				alert('fail to load dataset');
			}
		});
	});
	
	$('#chart-demo-1').on('click', function(event) {
		
		if (dataset == null || dataset.length == 0) {
			alert('데이터셋이 준비되지 않았습니다.');
			return;
		}
		const labels = Object.keys(dataset[0]);
		labels.pop();
		
		const data = [];
		$.each(labels, function(idx, label) {
			data.push(0);	
		});
		
		var count = 0;
		$.each(dataset, function(idx, row) {
			count++;
			$.each(labels, function(idx2, label) {
				data[idx2] += row[label];
			});
		});
		
		const meanData = data.map(x => x / count);
		
	  const ctx = document.getElementById('the-chart');

	  new Chart(ctx, {
	    type: 'line',
	    data: {
	      labels: labels, // x축 tick
	      datasets: [{
	        label: 'iris data means',			// data series name
	        data: meanData, 					// y축 data
	        backgroundColor: 'rgb(255,0,0)',
	        borderColor: 'rgb(0,0,255)',
	        borderWidth: 1
	      },
	      {
		        label: 'iris data means 2',			// data series name
		        data: [5.7, 3.4, 3.1, 2], 				// y축 data
		        backgroundColor: 'rgb(0,255,0)',
		        borderColor: 'rgb(0,0,255)',
		        borderWidth: 1
		      }]
	    },
	    options: {
	      scales: {
	        y: {
	          beginAtZero: true
	        }
	      }
	    }
	  });
	});
});
</script>

</body>
</html>












