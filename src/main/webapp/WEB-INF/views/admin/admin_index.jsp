<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<section class="content">
	<div class="row">        
		<div class="col-md-12">
			<!-- BAR CHART -->
			<div class="box box-success">
				<div class="box-header with-border">
					<h3 class="box-title">LAMAZON 현황1</h3>

					<div class="box-tools pull-right">
						<button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
						</button>
						<button type="button" class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
					</div>
				</div>
				<div class="box-body">
					<div class="row">
						<div class="col-md-6">
							<div class="chart">
								<canvas id="barChartM" style="height:255px"></canvas>
							</div>
						</div>
						<div class="col-md-6">
							<div class="table-responsive">
								<table class="table table-center m_table">
									<colgroup>
										<col class="col-md-2">
										<col class="col-md-2">
										<col class="col-md-2">
										<col class="col-md-2">
										<col class="col-md-2">
										<col class="col-md-2">
									</colgroup>
									<thead>
										<tr>
											<th>구분</th>
											<th>목표</th>
											<th class="point_bg1">현황1</th>
											<th class="point_bg1">현황2</th>
											<th class="point_bg2">현황3</th>
											<th class="point_bg2">현황4</th>
										</tr>
									</thead>
									<tbody>
										<%--
										<c:forEach var="item" items="${MM_GOAL_REPORT}" varStatus="status">
											<tr class="hover_tr" onclick="javascript:location.href='/admin/project/list/all';">
												<th><c:out value="${item['TITLE'] }"/></th>
												<td><fmt:formatNumber value="${item['GOAL'] }" pattern="#,###"/></td>
												<td class="point_bg1_1"><fmt:formatNumber value="${item['CUR_CNT'] }" pattern="#,###"/></td>
												<td class="point_bg1_1"><c:out value="${item['CURPER'] }"/> %</td>
												<td class="point_bg2_1"><fmt:formatNumber value="${item['PRE_GOAL'] }" pattern="#,###"/></td>
												<td class="point_bg2_1"><c:out value="${item['PRE_CNT'] }"/> %</td>
											</tr>
										</c:forEach>
										--%>
										
										<tr class="hover_tr" onclick="javascript:alert('준비중');">
											<th>등록건수</th>
											<td><fmt:formatNumber value="200" pattern="#,###"/></td>
											<td class="point_bg1_1"><fmt:formatNumber value="300" pattern="#,###"/></td>
											<td class="point_bg1_1"><c:out value="15"/> %</td>
											<td class="point_bg2_1"><fmt:formatNumber value="200" pattern="#,###"/></td>
											<td class="point_bg2_1"><c:out value="20"/> %</td>
										</tr>
	                  			</tbody>
				              </table>
				          </div>
				          <!--// table-responsive -->
				          <div class="box">
				          	<div class="box-header with-border">
				          		<i class="fa fa-text-width"></i>
								<fmt:formatDate value="<%=new java.util.Date()%>" pattern="yyyy년MM월" var="thisYm"/>
			
				          		<h3 class="box-title">${thisYm} 공지사항</h3>
				          	</div>
				          	<!-- /.box-header -->
				          	<div class="box-body">
				          		<ol class="box_list">
				          			<li>LAMAZON 사이트 오픈!</li>
				          			<!-- <li>수주 40건
				          				<ul class="box_list_2depth">
				          					<li>수주건 등록시 시스템 수시 등록( 수주액 必 )</li>
				          				</ul>
				          			</li>
				          			<li>파트너 견적서 획득 500건</li>
				          			<li>시스템 이용료 캠페인</li> -->
				          		</ol>
				          	</div>
				          	<!-- /.box-body -->
				          </div>
				      </div>
				      <!--// item -->
				  </div>
				  <!-- two_bottom_td -->
				</div>
				<!-- /.box-body -->
			</div>
			<!-- box -->
		</div>          
		<!-- /.col (RIGHT) -->
	</div>
</section>

<!-- Main content -->
<section class="content">
<%--
	<!-- row -->
	<div class="row">        
		<div class="col-md-12">
			<!-- BAR CHART -->
			<div class="box box-success">
				<div class="box-header with-border">
					<h3 class="box-title">LAMAZON 현황2</h3>

					<div class="box-tools pull-right">
						<button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
						</button>
						<button type="button" class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
					</div>
				</div>
				<div class="box-body">
					<div class="row">
						<div class="col-md-6">
							<div class="chart">
								<canvas id="barChartM" style="height:255px"></canvas>
							</div>
						</div>
						<div class="col-md-6">
							<div class="table-responsive">
								<table class="table table-center m_table">
									<colgroup>
										<col class="col-md-2">
										<col class="col-md-2">
										<col class="col-md-2">
										<col class="col-md-2">
										<col class="col-md-2">
										<col class="col-md-2">
									</colgroup>
									<thead>
										<tr>
											<th>구분</th>
											<th>목표</th>
											<th class="point_bg1">현황1</th>
											<th class="point_bg1">현황2</th>
											<th class="point_bg2">현황3</th>
											<th class="point_bg2">현황4</th>
										</tr>
									</thead>
									<tbody>

										<c:forEach var="item" items="${MM_GOAL_REPORT}" varStatus="status">
											<tr class="hover_tr" onclick="javascript:location.href='/admin/project/list/all';">
												<th><c:out value="${item['TITLE'] }"/></th>
												<td><fmt:formatNumber value="${item['GOAL'] }" pattern="#,###"/></td>
												<td class="point_bg1_1"><fmt:formatNumber value="${item['CUR_CNT'] }" pattern="#,###"/></td>
												<td class="point_bg1_1"><c:out value="${item['CURPER'] }"/> %</td>
												<td class="point_bg2_1"><fmt:formatNumber value="${item['PRE_GOAL'] }" pattern="#,###"/></td>
												<td class="point_bg2_1"><c:out value="${item['PRE_CNT'] }"/> %</td>
											</tr>
										</c:forEach>
										
										<tr class="hover_tr" onclick="javascript:alert('준비중');">
											<th><c:out value="지표"/></th>
											<td><fmt:formatNumber value="30" pattern="#,###"/></td>
											<td class="point_bg1_1"><fmt:formatNumber value="6" pattern="#,###"/></td>
											<td class="point_bg1_1"><c:out value="27"/> %</td>
											<td class="point_bg2_1"><fmt:formatNumber value="29" pattern="#,###"/></td>
											<td class="point_bg2_1"><c:out value="15"/> %</td>
										</tr>
	                  </tbody>
	              </table>
	          </div>
	          <!--// table-responsive -->
	          <div class="box">
	          	<div class="box-header with-border">
	          		<i class="fa fa-text-width"></i>
				<fmt:formatDate value="<%=new java.util.Date()%>" pattern="yyyy.MM" var="thisYm"/>

	          		<h3 class="box-title">${ thisYm }월 주요 지표</h3>
	          	</div>
	          	<!-- /.box-header -->
	          	<div class="box-body">
	          		<ol class="box_list">
	          			<li>준비중</li>
	          			<!--
	          			<li>수주 40건
	          				<ul class="box_list_2depth">
	          					<li>수주건 등록시 시스템 수시 등록( 수주액 必 )</li>
	          				</ul>
	          			</li>
	          			<li>파트너 견적서 획득 500건</li>
	          			<li>시스템 이용료 캠페인</li>
	          			 <li>의뢰 300건</li>
	          			<li>Nulla volutpat aliquam velit
	          				<ul class="box_list_2depth">
	          					<li>Phasellus iaculis neque</li>
	          					<li>Ac tristique libero volutpat at</li>
	          				</ul>
	          			</li>
	          			<li>Faucibus porta lacus fringilla vel</li>
	          			<li>Eget porttitor lorem</li> -->
	          		</ol>
	          	</div>
	          	<!-- /.box-body -->

	          </div>
	      </div>
	      <!--// item -->
	  </div>
	  <!-- two_bottom_td -->
	</div>
	<!-- /.box-body -->
</div>
<!-- box -->
</div>          
<!-- /.col (RIGHT) -->
</div>
<!-- /.row -->
--%>
 
<div class="row">        
	<div class="col-md-12">
		<!-- BAR CHART -->
		<div class="box box-success">
			<div class="box-header with-border">
				<h3 class="box-title">LAMAZON 현황2</h3>

				<div class="box-tools pull-right">
					<button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
					</button>
					<button type="button" class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
				</div>
			</div>
			<div class="box-body">
				<div class="row">
					<div class="col-md-6">
						<div class="chart" style="position: relative;">
							<canvas id="barChartDay" ></canvas>
						</div>
					</div>
					<div class="col-md-6">
						<div class="table-responsive">
							<table class="table table-center m_table">
								<colgroup>
									<col class="col-md-1">
									<col class="col-md-2">
									<col class="col-md-2">
									<col class="col-md-2">
									<col class="col-md-2">
									<col class="col-md-3">
								</colgroup>
								<tr>
									<th>구분</th>
									<th class="point_bg3">당일</th>
									<th class="point_bg4">전일</th>
									<th>금월평균(일)</th>
									<th>전월평균(일)</th>
								</tr>

								<%--
								<c:forEach var="item" items="${DD_GOAL_REPORT}" varStatus="status">

								<tr class="hover_tr" onclick="javascript:location.href='/admin/project/list/all';">
									<td><c:out value="${item['TITLE'] }"/></td>
									<td class="point_bg3_1"><c:out value="${item['TODAY'] }"/></td>
									<td class="point_bg4_1"><c:out value="${item['YESTERDAY'] }"/></td>
									<td><c:out value="${item['CUR_GAIN'] }"/></td>
									<td><c:out value="${item['PRE_GAIN'] }"/></td>
									<td><c:out value="${item['PRE_PER'] }"/></td>
								</tr>
								</c:forEach>
								--%>
								
								<tr class="hover_tr" onclick="javascript:alert('준비중');">
									<td><c:out value="샘플"/></td>
									<td class="point_bg3_1"><c:out value="20"/></td>
									<td class="point_bg4_1"><c:out value="14"/></td>
									<td><c:out value="16"/></td>
									<td><c:out value="17"/></td>
								</tr>


	                      <!-- <tr>
	                        <td>의뢰건</td>
	                        <td class="point_bg3_1">7</td>
	                        <td class="point_bg4_1">5</td>
	                        <td>12</td>
	                        <td>12</td>
	                        <td>8</td>
	                      </tr>
	                      <tr>
	                        <td>수주건</td>
	                        <td class="point_bg3_1">6</td>
	                        <td class="point_bg4_1">11</td>
	                        <td>12</td>
	                        <td>15</td>
	                        <td>9</td>
	                    </tr> -->                  
	                      <!-- <tr>
	                        <td>멤버십</td>
	                        <td class="point_bg3_1">7</td>
	                        <td class="point_bg4_1">13</td>
	                        <td>13</td>
	                        <td>5</td>
	                        <td>14</td>
	                    </tr> -->
	                </table>
	            </div>
	            <div class="box">
	            	<div class="box-header with-border">
	            		<i class="fa fa-text-width"></i>

	            		<h3 class="box-title">항목설명</h3>
	            	</div>
	            	<!-- /.box-header -->
	            	<div class="box-body">
	            		<ol class="box_list">
	            			<li>당월 목표
	            				<ul class="box_list_2depth">
	            					<li>샘플입니다.</li>
	            					<!-- <li>수주건 : 최근 6개월 이내건중 확정대기 이상</li>
	            					<li>달성예상 : 영업일 기준</li> -->
	            				</ul>
	            			</li>
	            			<!-- <li>Lorem ipsum dolor sit amet</li>
	            			<li>Nulla volutpat aliquam velit
	            				<ul class="box_list_2depth">
	            					<li>Phasellus iaculis neque</li>
	            					<li>Ac tristique libero volutpat at</li>
	            				</ul>
	            			</li>
	            			<li>Faucibus porta lacus fringilla vel</li>
	            			<li>Eget porttitor lorem</li>
	            			<li>Eget porttitor lorem</li>
	            			<li>Eget porttitor lorem</li> -->
	            		</ol>
	            	</div>
	            </div>
	        </div>                
	    </div>
	    <!-- /.box-body -->
	</div>
	<!-- /.box -->
</div>          
<!-- /.col (RIGHT) -->
</div>
<!-- /.row -->
</div>
</section>
<!-- /.content -->

<!-- page script -->
<script>
	$(function () {
	    // Return with commas in between
	    var numberWithCommas = function(x) {
	    	return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
	    };

	    var dataPack1 = [57.7, 25.2, 33.8];
	    var dataPack2 = [76.9, 66.4, 70.1];

	    //var dataPack1 = [${MM_GOAL_REPORT[0].CURPER}, ${MM_GOAL_REPORT[1].CURPER}, ${MM_GOAL_REPORT[2].CURPER}];
	    //var dataPack2 = [${MM_GOAL_REPORT[0].PRE_CNT}, ${MM_GOAL_REPORT[1].PRE_CNT}, ${MM_GOAL_REPORT[2].PRE_CNT}];
	    
	    
	    //var dates = ["의뢰건", "수주건", "수주금액", "멤버십"];
	    var dates = ["샘플1", "샘플2", "샘플3"];

	  // Chart.defaults.global.elements.rectangle.backgroundColor = '#FF0000';

	  var bar_ctx = document.getElementById('barChartM');
	  var bar_chart = new Chart(bar_ctx, {
	  	animationEnabled: true,
	  	type: 'bar',
	  	data: {
	  		labels: dates,
	  		datasets: [
	  		{
	  			label: '등록율',
	  			data: dataPack1,
	  			backgroundColor: "rgba(255, 159, 64, 0.2)",
	  			hoverBackgroundColor: "rgba(255, 159, 64, 0.5)",
	  			borderWidth : 2,
	  			hoverBorderWidth: 2,
	  			borderColor:"rgba(255, 159, 64, 1)"        
	  		},
	  		{
	  			label: '처리율',
	  			data: dataPack2,
	  			backgroundColor: "rgba(255, 99, 132, 0.2)",
	  			hoverBackgroundColor: "rgba(255, 99, 132, 0.5)",
	  			borderWidth : 2,
	  			hoverBorderWidth: 2,
	  			borderColor:"rgba(255, 99, 132, 1)"        
	  		},
	  		]
	  	},
	  	options: {
	  		tooltips: {
	  			mode: 'label',
	  			callbacks: {
	  				title: function(tooltipItems, data) {

	  					return data.labels[tooltipItems[0].index] + ' ';
	  				},
	  				label: function(tooltipItem, data) { 
	  					return data.datasets[tooltipItem.datasetIndex].label + ": " + numberWithCommas(tooltipItem.yLabel) + "%";
	  				},
	  			}
	  		},
	  		scales: {
	  			xAxes: [{ 
	  				stacked: false, 
	  				ticks: {
	            // callback: function(value) { return value.substring(5, value.length); },
	        },
	        gridLines: { display: false },
	    }],
	    yAxes: [{ 
	    	stacked: false, 
	    	ticks: {
	    		max: 100,
	    		callback: function(value) { return value +"%"; },
	    	}, 
	    }],
	        }, // scales
	        legend: {display: true}
	    } // options
	}
	);

	  //var dayPack1 = [${DD_GOAL_REPORT[0].TODAY}, ${DD_GOAL_REPORT[1].TODAY}];
	  //var dayPack2 = [${DD_GOAL_REPORT[0].YESTERDAY}, ${DD_GOAL_REPORT[1].YESTERDAY}];
	  //var dayPack3 = [${DD_GOAL_REPORT[0].CUR_GAIN}, ${DD_GOAL_REPORT[1].CUR_GAIN}];
	  //var dayPack4 = [${DD_GOAL_REPORT[0].PRE_GAIN}, ${DD_GOAL_REPORT[1].PRE_GAIN}];
	  
	  	var dayPack1 = [12, 13];
		var dayPack2 = [6, 16];
		var dayPack3 = [13, 7];
		var dayPack4 = [8, 11];
		
	  //var dates_x2 = ["의뢰건", "수주건", "수주금액", "멤버십"];
	  var dates_x2 = ["등록건", "처리건"];

	  // Chart.defaults.global.elements.rectangle.backgroundColor = '#FF0000';

	  var bar_ctx2 = document.getElementById('barChartDay');
	  var bar_chart2 = new Chart(bar_ctx2, {
	  	type: 'bar',
	  	data: {
	  		animationEnabled: true,
	  		labels: dates_x2,
	  		datasets: [
	  		{
	  			label: '당일',
	  			data: dayPack1,
	  			backgroundColor:"rgba(255, 205, 86, 0.2)",
	  			hoverBackgroundColor:"rgba(255, 205, 86, 0.5)",
	  			borderWidth : 2,
	  			hoverBorderWidth: 2,
	  			borderColor:"rgba(255, 205, 86, 1)"        
	  		},
	  		{
	  			label: '전일',
	  			data: dayPack2,
	  			backgroundColor: "rgba(75, 192, 192, 0.2)",
	  			hoverBackgroundColor: "rgba(75, 192, 192, 0.5)",
	  			borderWidth : 2,
	  			hoverBorderWidth: 2,
	  			borderColor:"rgba(75, 192, 192, 1)"        
	  		},
	  		{
	  			label: '금월평균(일)',
	  			data: dayPack3,
	  			backgroundColor: "rgba(54, 162, 235, 0.2)",
	  			hoverBackgroundColor: "rgba(54, 162, 235, 0.5)",
	  			borderWidth : 2,
	  			hoverBorderWidth: 2,
	  			borderColor:"rgba(54, 162, 235, 1)"   
	  		},
	  		{
	  			label: '전월평균(일)',
	  			data: dayPack4,
	  			backgroundColor: "rgba(153, 102, 255, 0.2)",
	  			hoverBackgroundColor: "rgba(153, 102, 255, 0.5)",
	  			borderWidth : 2,
	  			hoverBorderWidth: 2,
	  			borderColor:"rgba(153, 102, 255, 1)",
	  		},
	  		]
	  	},
	  	options: {
	  		tooltips: {
	  			mode: 'label',
	  			callbacks: {
	  				title: function(tooltipItems, data) {

	  					return data.labels[tooltipItems[0].index] + ' ';
	  				},
	  				label: function(tooltipItem, data) { 
	  					return data.datasets[tooltipItem.datasetIndex].label + ": " + numberWithCommas(tooltipItem.yLabel) + "건";
	  				},
	  			}
	  		},
	  		scales: {
	  			xAxes: [{ 
	  				stacked: false, 
	  				ticks: {
	            // callback: function(value) { return value +"%"; },
	        },
	        gridLines: { display: false },
	    }],
	    yAxes: [{ 
	    	stacked: false,
	    	ticks: {
	    		max: 20,
	    		callback: function(value) { return value +"건"; },
	    	}, 
	    }],
	        }, // scales
	        legend: {display: true}
	    } // options
	}
	);
	})
</script>