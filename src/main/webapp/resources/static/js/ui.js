
var mySwiperMenu;

function uiGnb(){
    var body = 'body',
        openBtn = '.gnb_comm .btn_menu',
        closeBtn = '.gnb_comm .btn_close',
        wrapGnb = '.gnb_comm',
        MenuDimmed = '.menu_dimmed',
        bgDimmed = '.bg_dimmed',
        depth1 = '.is_submenu.link_menu',
        depth2 = '.list_navigation .list_depth2',
        depth3 = '.list_depth3';
    // Menu bar Open
    $(openBtn).click(function(){
        if(!$(body).hasClass('is_menu_open')){
            $(body).addClass('is_menu_open');
            $(this).parents(wrapGnb).append('<div class="menu_dimmed"></div>');
            $(bgDimmed).stop().fadeIn(350);
            uiGnbClose();
            $('.wrap_gnb .inner_gnb').scrollTop(0);
        } 
        // mySwiperMenu = new Swiper('.swiper_menu', {
        //     speed: 200,
        //     loop: false,
        //     autoplayStopOnLast: true,
        //     autoHeight: true
        // });

        $('.wrap_gnb .bt_cate').each(function(){
            if($(this).hasClass('is_active')){
                var $dataTab = $(this).attr('data-target');
                //mySwiperMenu.slideTo($dataTab, 200);
            }
        });
        uiGnbCategory();

        return false;
    });
    // Menu bar Close
    $(closeBtn).click(function(){
        if($(body).hasClass('is_menu_open')){
            $(body).removeClass('is_menu_open');
            $(bgDimmed).fadeOut('fast');
            $(MenuDimmed).stop().remove();
            //mySwiperMenu.destroy(false, true);
        } 
        return false;
    });

    $(depth2).each(function(){
        var _this = $(this);
        $(this).prev('.link_menu').addClass('is_submenu');
    });

    $(depth3).each(function(){
        var _this = $(this);
        $(this).prev('.link_depth2').addClass('is_submenu');
    });

    $(depth1).click(function(){
        if($(this).parent('li').hasClass('is_active')){
            $(this).parent('li').removeClass('is_active');
            $(this).next(depth2).stop().slideUp('fast');
        } else {
            $(this).parent('li').addClass('is_active');
            $(this).next(depth2).stop().slideDown('fast');
        }
        return false;
    });

    $(depth2+' .link_depth2.is_submenu').click(function(){
        if($(this).hasClass('is_active')){
            $(this).removeClass('is_active');
            $(this).next(depth3).stop().slideUp('fast');
        } else {
            $(this).addClass('is_active');
            $(this).next(depth3).stop().slideDown('fast');
        }
        return false;
    });
}
function uiGnbClose(){
    var body = 'body',
        MenuDimmed = '.menu_dimmed',
        bgDimmed = '.bg_dimmed';
    $(MenuDimmed).click(function(){
        if($(body).hasClass('is_menu_open')){
            $(body).removeClass('is_menu_open');
            $(bgDimmed).fadeOut('fast');
            $(MenuDimmed).stop().remove();
           // mySwiperMenu.destroy(false, true);
        } 
        return false;
    });
}

function uiGnbDeskTop(){
     if ($(window).width() > 1024){ 
        $('.list_navigation li').mouseover(function(){
            $(this).find('.link_menu').addClass('is_active');
            $(this).find('.list_depth2').addClass('is_active');
            //$(this).find('.list_depth2').stop().slideDown('fast');
        });

        $('.list_navigation li').mouseleave(function(){
            $(this).find('.link_menu').removeClass('is_active');
            $(this).find('.list_depth2').removeClass('is_active');
            //$(this).find('.list_depth2').stop().slideUp('fast');
        });
     }

}
function uiGnbResize(){
    if ($(window).width() < 1024){ 
      
     } else {
        $('.menu_container .list_depth2, .menu_container .list_depth3').removeAttr('style');
        $('.page_aside .list_aside, .page_aside .aside_filter_wrap').removeAttr('style');


        $('.list_navigation .is_active').removeClass('is_active');
        $(".is_submenu.link_menu").unbind('click');
        $(".list_navigation .link_depth2.is_submenu").unbind('click');

     }


    
}

function uiGnbCategory(){
    var body = 'body',
        bt = '.gnb_comm .bt_cate',
        MenuWrap = '.wrap_menu',
        MenuWrapOn = '.wrap_menu.on';

    $(bt).click(function(){
        var $dataTab = $(this).attr('data-target');
            $(bt).removeClass('is_active');
            $(this).addClass('is_active');
            //mySwiperMenu.slideTo($dataTab, 200);
    });

    // mySwiperMenu.on('slideChangeStart', function(e) {
    //     $(bt).removeClass('is_active');
    //     $(bt+'[data-target='+e.activeIndex+']').addClass('is_active');
    //     $('.wrap_gnb .inner_gnb').scrollTop(0);
    // });
}

function uiHeaderSearch(){
    var btn = '.wrap_top_util .list_util .bt_search',
        srhWrap = '.wrap_top_util .wrap_search_box',
        srhInp = '.wrap_top_util .inner_search .tf_text',
        body = 'body';

    $(btn).click(function(){
        if($(this).parents('.wrap_top_util').hasClass('is_active')){
            $(this).parents('.wrap_top_util').removeClass('is_active');
            $(srhWrap).stop().slideUp(100);
            $(srhInp).stop().focusout();
        } else {
            $(this).parents('.wrap_top_util').addClass('is_active');
            $(srhWrap).stop().slideDown(100);
            $(srhInp).stop().focus();
        }
        return false;
    });

     if ($(window).width() < 1024){ 
        $(srhWrap).removeAttr('style');
     }
}

function uiSelectBoxCtrl(){
    var $select = $(".opt_item .opt_select");
    var $selectDis = $(".opt_item .opt_select:disabled");

    $select.change(function(){
        var select_name = $(this).children("option:selected").text();
        $(this).siblings(".lab_select").text(select_name);
    });
    $select.focus(function(){
        $(this).parent().addClass("on");
    });
    $select.focusout(function(){
        $(this).parent().removeClass("on");
    });
    $selectDis.parent().addClass("opt_disabled");
    $select.each(function(){
        if($(this).children("option:selected")){
            var default_name = $(this).children("option:selected").text();
            $(this).siblings(".lab_select").text(default_name);
        }
    });
}

function  uiCheckCtrl(){
    $('input:checked').prev().addClass('on');   // 최초 체크된 서식 디자인 적용
    $('.radio:disabled').prev().addClass('is_disabled');   
    $('.check:disabled').prev().addClass('is_disabled');  

    $(document).on('click', 'input.radio, input.check', function(){
        var $that = $(this);
        if ($that.is('.check')){
            if($that.is(":checked")) $that.prev().addClass('on');
            else  $that.prev().removeClass('on');
        } else if ($that.is('.radio')) {
            $('input[name="'+ $that.attr('name') +'"]').each(function() {
                if (this == $that[0]) $(this).prev().addClass('on');
                else $(this).prev().removeClass('on');
            });
        }
    })
    .on('focus', 'input.radio, input.check', function() {
            if($(this).is(":checked")) $(this).prev().addClass('on');
            else  $(this).prev().removeClass('on');
    })
    .on('blur', 'input.radio, input.check', function() {
            if($(this).is(":checked")) $(this).prev().addClass('on');
            else  $(this).prev().removeClass('on');
    });

    $that = null;
}

function listTypeChangeBtn(){
    var bt = '.wrap_sort .bt_list_type',
        wrapList = '.js__list_type';

    $(bt).click(function(){
        var $dataType = $(this).attr('data-type');

        $(bt).stop().removeClass('on');
        $(this).stop().addClass('on');
        $(wrapList)
            .removeClass('type_list')
            .removeClass('type_thumb')
            .addClass('type_'+$dataType);

        return false;
    });
}

function uiLayerBoxHandle(){
    var bt = '.js_bt_layer';

    $(bt).click(function(){
        var $dataTarget = $(this).attr('data-target');
        $(bt).toggleClass('on');
        $('#'+$dataTarget).slideToggle('fast');
        return false;
    });
}

function categoryScrollX(){
    var $scrollWrap = $(".wrap_scr_category .inner_cate"),
        $scrollMenu = $(".wrap_scr_category .link_cate"),
        $scrollMenuOn = $(".wrap_scr_category .link_cate.on");

    $scrollMenu.each(function(){
        if($(this).hasClass("on")){
            if($scrollMenuOn.offset().left > 200) {
                $scrollWrap.scrollLeft($scrollMenuOn.offset().left);
            }
        }
    });
}

function stickyHead(){
    var $body = $('body');
    if ($(window).scrollTop() > 5){
        $body.addClass("is_head_sticky");
    } else{
        $body.removeClass("is_head_sticky");
    }
}

function pageTrackerSticky(){
    var $cView = $('#cView'),
        $cHead = $('#g_header'),
        $cSub = $('#cSub'),
        $cTracker = $('#cTracker'),
        c_offset = $cView.offset(),
        c_height = $cView.outerHeight(),
        s_height = $cSub.outerHeight(),
        t_height = $cTracker.outerHeight(),
        h_height = $cHead.outerHeight();

    if(c_offset){
        var c_heightTop = c_offset.top + c_height;
        if ($(window).scrollTop() > c_heightTop - h_height - t_height){
            $('body').addClass("trackersticky_on");
        } else if ($(window).scrollTop() < c_heightTop - h_height + t_height){
            $('body').removeClass("trackersticky_on");
        } else {
            $('body').removeClass("trackersticky_on");
        }
    }
    
}

function uiSubCateHandle(){
    $('.page_aside .bt_extend.type1').click(function(){
        $('.page_aside .aside_filter_wrap').slideUp(0);
        $('.page_aside .bt_extend.type2').removeClass('on');
        $(this).toggleClass('on');
        $(this).parents('.inner_aside').find('.list_aside').slideToggle('fast');
        return false;
    });
    $('.page_aside .bt_extend.type2').click(function(){
        $('.page_aside .list_aside').slideUp(0);
        $('.page_aside .bt_extend.type1').removeClass('on');
        $(this).toggleClass('on');
        $(this).parents('.inner_aside').find('.aside_filter_wrap').slideToggle('fast');
        return false;
    });
}

function uiBreadcrumb(){
    var bt = '.list_breadcrumb .link_depth1';

    $(bt).click(function(){
        $(this).toggleClass('on')
        $(this).next('.list_depth2').slideToggle('fast');
        return false;
    });
}

function uiSideMenu(){
    var bt = '.page_aside .list_aside .link_depth1';

    $(bt).click(function(){
        if($(this).hasClass('is_active')){
            $(this).removeClass('is_active');
            $(this).next('.sub_menu_block').slideUp('fast');
        } else {
            $(this).addClass('is_active');
            $(this).next('.sub_menu_block').slideDown('fast');
        }
        return false;
    });
}


$(function(){
    uiSelectBoxCtrl();
    uiCheckCtrl();
    uiHeaderSearch();
    uiGnb();
    listTypeChangeBtn();
    categoryScrollX();
    stickyHead();
    pageTrackerSticky();
    uiLayerBoxHandle();
    uiBreadcrumb();
    uiSideMenu();
    uiGnbResize();
    uiGnbDeskTop();
    uiSubCateHandle();


    $(window).on("scroll",function(){
        stickyHead();
        pageTrackerSticky();
    });
    $(window).on("resize",function(){
        uiGnbResize();
        uiHeaderSearch();
       
    });
    $(window).load(function() {
        uiGnbResize();
    });
    
});