Number.prototype.to2 = function(){return this<10?'0'+this:this;} 
Date.prototype.getYMD = function(s){ 
       s=s||'-'; 
       
       var hour = this.getHours();
       var weekday = this.getDay();
       var n = "";
       var strWeekday = "";
       switch( weekday ) {
       case 0:
    	   	strWeekday = "일요일";
    	   	break;
       case 1:
	   	   	strWeekday = "월요일";
	   	   	break;
       case 2:
	   	   	strWeekday = "화요일";
	   	   	break;
       case 3:
	   	   	strWeekday = "수요일";
	   	   	break;
       case 4:
	   	   	strWeekday = "목요일";
	   	   	break;
       case 5:
	   	   	strWeekday = "금요일";
	   	   	break;
       case 6:
	   	   	strWeekday = "토요일";
	   	   	break;   	   	
       }
       
       if ( hour > 12 ) 
   	   {
		   hour -= 12;
    	   n = "PM";
   	   } else {
   		   n = "AM";
   	   }
       

       return this.getFullYear() + "년 " 
            + (this.getMonth()-1).to2() + "월 " 
            + this.getDate().to2() + "일 " 
            + strWeekday + " "
            + "<strong>"
            + n + " "
       		+ hour.to2() + ":"
       		+ this.getMinutes().to2() + ":"
       		+ this.getSeconds().to2() 
       		+ "</strong>";

}

var timer;
$(document).ready(function() {
	
	if ( $("#currentTime").length == 0 ) return false;
	
	var timer = {
			showTime: function() {
				var now = new Date();
				$("#currentTime").html ( now.getYMD() );				
			}
	}
	
	
	setInterval( timer.showTime, 1000 );
});
