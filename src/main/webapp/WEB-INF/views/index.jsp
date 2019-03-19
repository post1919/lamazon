<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<script type="text/javascript" src="/js/jquery.cycle2.min.js"></script>
<script type="text/javascript" src="/js/jquery.cycle2.carousel.min.js"></script>
 <script type="text/javascript">
        $(function(){
            $(".visual_a").cycle({
                fx:'scrollHorz',
                slides:".visual_area_img",
                next: "#cycle_next_btn",
                prev: "#cycle_prev_btn"
            });
        });
        
        $(".user_a").cycle({
            fx:'scrollHorz',
            slides:".user_wrap",
            next: "#cycle01_next_btn",
            prev: "#cycle01_prev_btn"
        });
</script> 
<style>
.visual_star1 {display:inline-block;margin-top:20px;width:116px!important;height:20px;background-image:url("/images/main/star_off_l.png");background-size:116px 20px}
.visual_star1 span {display:inline-block;width:116px;height:20px;background-image:url("/images/main/star_on_l.png");background-size:116px 20px}
.kyobo {
	background:none;
	border-radius: 50px;
	-moz-border-radius: 50px;
	-webkit-border-radius: 50px;
	border:0px solid red;
	overflow:hidden;
}
.user_wrap h1 {
	display:block;
	border-radius: 55px;
	-moz-border-radius: 55px;
	-webkit-border-radius: 55px;
}

.user_table h1 {
	display:block;
	border-radius: 55px;
	-moz-border-radius: 55px;
	-webkit-border-radius: 55px;
}


.pros_cons p {width: 450px; text-overflow: ellipsis; white-space: nowrap; overflow: hidden; }
.visual_txt a {color:white}
.user_top  a {color:white}

.user_bottom div.txt_box > span {display:inline-block;width: 780px;height:24px; text-overflow: ellipsis; white-space: nowrap; overflow: hidden;margin-bottom:20px;}
.c_img span {display:inline-block;width: 160px; text-overflow: ellipsis; white-space: nowrap; overflow: hidden;}

.request_wrap .sum {height:57px;overflow: hidden;display:block;}

.c_btn01, .c_btn02 {font-size:12px;}

.bottom_txt p {dispay:block;height:70px;overflow:hidden}

.blue_txt div {cursor:pointer}
</style>
    <div class="contents_a">
        <div class="visual_a">
            <div class="img_a">
                <div class="visual_area_img ver01"></div>
                
                <c:forEach var="item" items="${MAIN_TOP_ROLL}" varStatus="status">
                <c:if test="${ status.index eq 0 }">
                <div class="visual_area_img ver02">
                    <div class="visual_wrap">
                         <ul>
                            <li><span class="kyobo"><img src="<mono:img_url_logo><c:out value="${item['C_LOGO'] }"/></mono:img_url_logo>" alt="기업로고" width=90 height=90></span></li>
                            <li>
                                <span class="visual_star1"><span style="width:<c:out value="${ item['PT_TOTAL'] * 20}" />%;"></span></span>
                                <p class="visual_txt"><a href="/partner/<c:out value="${item['C_ID']}"/>"><c:out value="${item['C_NAME']}"/></a></p>
                            </li>
                            <li class="pros_cons">
                                <p><span>장점</span> <c:out value="${item['CE_PROS']}"/></p>
                                <p><span>단점</span> <c:out value="${item['CE_CONS']}"/></p>
                            </li>
                        </ul>
                    </div>
                </div>
                </c:if>
                
                <c:if test="${ status.index eq 1 }">
                <div class="visual_area_img ver03 relative">
                    <div class="visual_wrap">
                         <ul>
                            <li><span class="kyobo"><img src="<mono:img_url_logo><c:out value="${item['C_LOGO'] }"/></mono:img_url_logo>" alt="기업로고" width=90 height=90></span></li>
                            <li>
                                <span class="visual_star1"><span style="width:<c:out value="${ item['PT_TOTAL'] * 20}" />%;"></span></span>
                                <p class="visual_txt"><a href="/partner/<c:out value="${item['C_ID']}"/>"><c:out value="${item['C_NAME']}"/></a></p>
                            </li>
                            <li style="margin-top:60px;">
                                <mono:category1><c:out value="${item['C_CATEGORY']}"/></mono:category1>
                            </li>
                        </ul>
                        <p>"<c:out value="${item['CE_SUMMARY']}"/>"</p>
                    </div>
                </div>
                </c:if>
                </c:forEach>
            </div><!--// img_a -->
            <a href="#" class="btn_pre prev" id="cycle_prev_btn"><img src="../images/main/btn_pre.png" alt="이전"></a>
            <a href="#" class="btn_next next" id="cycle_next_btn"><img src="../images/main/btn_next.png" alt="다음"></a>
        </div><!--// visual_a -->
        <div class="main_content">
			<div class="user_a">
				<c:forEach var="item" items="${MAIN_PARTNERS}" varStatus="status">
                <div class="user_wrap">
                    <div class="user_txt">
                        <h4>사용자 <span class="ast">이용 후기</span></h4>
                        <span>파트너와 업무를 함께 진행해 본 실제 고객들의 생생한 후기를 확인하세요</span>
                    </div>
                    <div class="user_table">
                        <p class="more"><a href="/review">더보기</a> <span class="ast">▶</span></p>
                        <h1><img src="<mono:img_url_logo><c:out value="${item['LOGO'] }"/></mono:img_url_logo>" alt="기업로고" width=110 height=110></h1>
                        <div class="user_top">
                            <div class="top_left">
                                <p><a href="/partner/<c:out value="${item['C_ID']}"/>"><c:out value="${item['CNAME']}"/></a></p>
                        		<span><mono:category1><c:out value="${item['CATEGORY']}"/></mono:category1></span>
                            </div>
                            <div class="top_right">
                                <span class="mt10 relative"><span class="star_bar_l"><span style="width:<c:out value="${item['PT_TOTAL']*20}"/>%;"></span></span></span><br>
                                <p>평점 <c:out value="${item['PT_TOTAL']}"/></p>
                            </div>
                        </div>
                        <div class="user_bottom">
							<c:forEach var="item2" items="${item['REVIEWS']}" varStatus="status">
                            <div class="bottom_txt">
                                <span class="noimage01"></span>
                                <p><c:out value="${item2['SUMMARY'] }"/></p>
                                <div class="txt_box">
                                    <span>
                                        <span class="red_box">장점</span>
                                        <c:out value="${item2['PROS'] }"/>
                                    </span>
                                    <span>
                                        <span class="blue_box">단점</span>
                                        <c:out value="${item2['CONS'] }"/>
                                    </span>
                                </div>
                            </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
                </c:forEach>
          </div>
                <a href="#" class="btn_pre prev" id="cycle01_prev_btn"><img src="/images/main/btn_pre.png" alt="이전"></a>
                <a href="#" class="btn_next next" id="cycle01_next_btn"><img src="/images/main/btn_next.png" alt="다음"></a>
            
            
            <div class="blue_wrap">
                <div class="blue_txt">
                    <div class="blue_left" onclick="document.location.href='/partner'">
                        <h1><span><fmt:formatNumber value="${ TOTAL_PARTNER_COUNT }" pattern="#,#00"/></span> 개 업체</h1>
                        <p>검증된 파트너가 당신의 캐스팅을 기다리고 있습니다.</p>
                    </div>
                    <div class="blue_center" onclick="document.location.href='/review'">
                        <h1><span><fmt:formatNumber value="${ TOTAL_REVIEW_COUNT }" pattern="#,#00"/></span> 건</h1>
                        <p>파트너와 업무를 함께 진행해 본실제 고객들의 생생한 후기.</p>
                    </div>
                    <div class="blue_right"  onclick="document.location.href='/projectFind'">
                        <h1><span><fmt:formatNumber value="${ TOTAL_PROJECT_COUNT }" pattern="#,#00"/></span> 건</h1>
                        <p>의뢰 내용을 등록 하시면 최적의 파트너의 제안을 받으실 수 있습니다.</p>
                    </div>
                </div>
            </div>
            <div class="request_wrap">
                <p class="more"><a href="/partner">더보기</a> <span class="ast">▶</span></p>
                <h2>추천 <span class="ast">협력사</span></h2>
                <p><fmt:formatNumber value="${ TOTAL_PARTNER_COUNT }" pattern="#,#00"/>개의 검증된 파트너가 당신의 캐스팅을 기다리고 있습니다.</p>
                <ul class="request_list mt10">
                	<c:forEach var="item" items="${MAIN_RECOMMEND_PARTNER}" varStatus="status">
                	<c:if test="${status.index lt 3 }">
                    <li>
                        <div class="c_info">
                            <p class="c_img">
                            	<a href="/partner/<c:out value="${item['C_ID'] }"/>"><img src="<mono:img_url_logo><c:out value="${item['C_LOGO'] }"/></mono:img_url_logo>" alt="기업로고" width=100 height=100></a>
                                <span><a href="/partner/<c:out value="${item['C_ID'] }"/>"><c:out value="${item['C_NAME'] }"/></a></span>
                            </p>
                            <p class="mt10 relative" style="margin-left:125px;">
                                <span class="star_bar">
                                    <span style="width:<c:out value="${item['PT_TOTAL']*20 }"/>%;"></span>
                                </span>
                                <span class="c_poll"><strong><fmt:formatNumber value="${ item['PT_TOTAL']}" pattern="0.0#"/></strong> / <c:out value="${item['C_PT_NUMBER'] }"/>개</span>
                            </p>
                            <p class="c_btn">
                            	<c:if test="${item['C_LOGO'] eq '' }">
	                       		 <a href="#" onclick="return false"><span class="c_btn01">기업인증</span></a>
	                       		</c:if>
	                       		<c:if test="${item['C_LOGO'] ne '' }">
	                       		 <a href="#" onclick="return false"><span class="c_btn01">기업인증</span></a>
	                       		</c:if>
	                       		<c:if test="${item['PT_PRICE'] ge 3.5 }">
	                       		<a href="#" onclick="return false"><span class="c_btn01">가격경쟁력</span></a>
	                       		</c:if>
	                       		<c:if test="${item['PT_PRICE'] lt 3.5 }">
	                       		 <a href="#" onclick="return false"><span class="c_btn02">가격경쟁력</span></a>
	                       		</c:if>
	                       		<c:if test="${item['PT_SPECIAL'] ge 3.5 }">
	                       		 <a href="#" onclick="return false"><span class="c_btn01">전문성</span></a>
	                       		</c:if>
	                       		<c:if test="${item['PT_SPECIAL'] lt 3.5 }">
	                       		 <a href="#" onclick="return false"><span class="c_btn02">전문성</span></a>
	                       		</c:if>
	                           <c:if test="${item['PT_QUALITY'] ge 3.5 }">
	                       		 <a href="#" onclick="return false"><span class="c_btn01">만족도</span></a>
	                       		</c:if>
	                       		<c:if test="${item['PT_QUALITY'] lt 3.5 }">
	                       		 <a href="#" onclick="return false"><span class="c_btn02">만족도</span></a>
                       			</c:if>
                            </p>
                        </div>
                        <dl>
                            <dt><span class="sum"><c:out value="${item['C_SUMMARY'] }"/></span></dt>
                        </dl>
                    </li>
                    </c:if>
                    </c:forEach>
                </ul>
                <ul class="request_list_1 mt10">
                	<c:forEach var="item" items="${MAIN_RECOMMEND_PARTNER}" varStatus="status">
                    <c:if test="${status.index ge 3 }">
                    <li>
                        <div class="c_info">
                            <p class="c_img">
                            	<a href="/partner/<c:out value="${item['C_ID'] }"/>"><img src="<mono:img_url_logo><c:out value="${item['C_LOGO'] }"/></mono:img_url_logo>" alt="기업로고" width=100 height=100></a>
                                <span><a href="/partner/<c:out value="${item['C_ID'] }"/>"><c:out value="${item['C_NAME'] }"/></a></span>
                            </p>
                            <p class="mt10 relative" style="margin-left:125px;">
                                <span class="star_bar">
                                    <span style="width:<c:out value="${item['PT_TOTAL']*20 }"/>%;"></span>
                                </span>
                                <span class="c_poll"><strong><fmt:formatNumber value="${ item['PT_TOTAL']}" pattern="0.0#"/></strong> / <c:out value="${item['C_PT_NUMBER'] }"/>개</span>
                            </p>
                            <p class="c_btn">
                            	<c:if test="${item['R_U_PK'] ne 0 }">
	                       		 <a href="#" onclick="return false"><span class="c_btn01">기업인증</span></a>
	                       		</c:if>
	                       		<c:if test="${item['R_U_PK'] eq 0 }">
	                       		 <a href="#" onclick="return false"><span class="c_btn02">기업인증</span></a>
	                       		</c:if>
	                       		<c:if test="${item['PT_PRICE'] ge 3.5 }">
	                       		<a href="#" onclick="return false"><span class="c_btn01">가격경쟁력</span></a>
	                       		</c:if>
	                       		<c:if test="${item['PT_PRICE'] lt 3.5 }">
	                       		 <a href="#" onclick="return false"><span class="c_btn02">가격경쟁력</span></a>
	                       		</c:if>
	                       		<c:if test="${item['PT_SPECIAL'] ge 3.5 }">
	                       		 <a href="#" onclick="return false"><span class="c_btn01">전문성</span></a>
	                       		</c:if>
	                       		<c:if test="${item['PT_SPECIAL'] lt 3.5 }">
	                       		 <a href="#" onclick="return false"><span class="c_btn02">전문성</span></a>
	                       		</c:if>
	                           <c:if test="${item['PT_QUALITY'] ge 3.5 }">
	                       		 <a href="#" onclick="return false"><span class="c_btn01">만족도</span></a>
	                       		</c:if>
	                       		<c:if test="${item['PT_QUALITY'] lt 3.5 }">
	                       		 <a href="#" onclick="return false"><span class="c_btn02">만족도</span></a>
                       			</c:if>
                            </p>
                        </div>
                        <dl>
                            <dt><span class="sum"><c:out value="${item['C_SUMMARY'] }"/></span></dt>
                        </dl>
                    </li>
                    </c:if>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>