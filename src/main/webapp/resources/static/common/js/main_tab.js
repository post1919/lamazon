// JavaScript Document

function tabcon(str){
	function hideall(){
		for(i=1;i<=2;i++){

				document.getElementById('tab'+i).getElementsByTagName('a')[0].className='';
			    document.getElementById('tabscon'+i).style.visibility='hidden';
		}
	}

	for(i=1;i<=2;i++){		
		document.getElementById('tab'+i).getElementsByTagName('a')[0].onclick=function (e){
			 hideall();
			 document.getElementById('tabscon'+this.parentNode.id.substring(3)).style.visibility='visible';
			 this.className='onhover';
			 
			 return false;
		}
		document.getElementById('tab'+i).getElementsByTagName('a')[0].onmouseover=function (e){
			 hideall();
			 document.getElementById('tabscon'+this.parentNode.id.substring(3)).style.visibility='visible';
			this.className='onhover';
		}
		document.getElementById('tab'+i).getElementsByTagName('a')[0].onfocus=function (e){
			 hideall();
			 document.getElementById('tabscon'+this.parentNode.id.substring(3)).style.visibility='visible';
			this.className='onhover';
		}
	}


	
    if(str){
		hideall();
		document.getElementById('tab'+str).getElementsByTagName('a')[0].className='onhover';
		document.getElementById('tabscon'+str).style.visibility='visible';
		
		
	}
} 




$(document).ready( function() {
		tabcon("1");
});
