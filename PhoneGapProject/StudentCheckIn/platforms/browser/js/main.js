
(function ($) {
    "use strict";


    /*==================================================================
    [ Focus input ]*/
    $('.input100').each(function(){
        $(this).on('blur', function(){
            if($(this).val().trim() != "") {
                $(this).addClass('has-val');
            }
            else {
                $(this).removeClass('has-val');
            }
        })    
    })
  
  
    /*==================================================================
    [ Validate ]*/
    var input = $('.validate-input .input100');

    $('.validate-form').on('submit',function(){
        var check = true;

        for(var i=0; i<input.length; i++) {
            if(validate(input[i]) == false){
                showValidate(input[i]);
                check=false;
            }
        }
		//alert('Hi');
		
		var data = '[{"attendance_id": 1,"course": "ACCOV","date": "01/01/2019","period": "5:00PM=>6:45PM","classroom": "class 305"},'+
            '{"attendance_id": 2,"course": "Clients Serveurs","date": "03/01/2019","period": "7:00PM=>8:45PM","classroom": "class 307"}]';
		//alert('data='+data);
			
		//filters = jQuery.param( data );
		//alert("attendancelist.html?Email="+document.getElementById("Email").value+"&Password="+document.getElementById("Password").value + "&attList="+data);

		
		//window.open("attendancelist.html?Email="+document.getElementById("Email").value+"&Password="+document.getElementById("Password").value + "&attList="+data,"_self");
		
		CheckInByAttendance();
        return check;
    });


    $('.validate-form .input100').each(function(){
        $(this).focus(function(){
           hideValidate(this);
        });
    });

    function validate (input) {
        if($(input).attr('type') == 'email' || $(input).attr('name') == 'email') {
            if($(input).val().trim().match(/^([a-zA-Z0-9_\-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([a-zA-Z0-9\-]+\.)+))([a-zA-Z]{1,5}|[0-9]{1,3})(\]?)$/) == null) {
                return false;
            }
        }
        else {
            if($(input).val().trim() == ''){
                return false;
            }
        }
    }

    function showValidate(input) {
        var thisAlert = $(input).parent();

        $(thisAlert).addClass('alert-validate');
    }

    function hideValidate(input) {
        var thisAlert = $(input).parent();

        $(thisAlert).removeClass('alert-validate');
    }
    
	
	function CheckInByAttendance() {
		var xhttp = new XMLHttpRequest();
		//xhttp.onreadystatechange = function() {
		//	 if (this.readyState == 4 && this.status == 200) {
		//		 alert(this.responseText);
		//	 }
		//};
		
		//alert(document.getElementById("serverUrl").innerHTML.trim() + "/CNAMAttendance/webresources/ISSAEServices/getStudentAttendance/"+document.getElementById("Email").value+"/"+document.getElementById("Password").value);
		
		xhttp.open("GET", document.getElementById("serverUrl").innerHTML.trim() + "/CNAMAttendance/webresources/ISSAEServices/getStudentAttendance/"+document.getElementById("Email").value+"/"+document.getElementById("Password").value, true);
		
		xhttp.send();
	   
	//	xhttp.onreadystatechange = function() {alert("1:" + xhttp.responseText + "/" + xhttp.status + "/" + xhttp.readyState);
	//		 if (this.readyState == 4 && this.status == 200) {
	//			 alert(xhttp.responseText);
	//		 }
	//			 alert("4:" + xhttp.responseText);
	//			 //jsonResponse = req.responseJSON
	//  	};

		xhttp.onload = function() { 
		  //alert("2:" + this.responseText + "/" + this.status + "/" + this.readyState);
		  if (this.status >= 200 && this.status < 400) {
			// Success!
			var str = this.response;
			var res = str.substring(0, 1);
            if (res =="["){
			  //call attendance list page 
			  window.open("attendancelist.html?Email="+document.getElementById("Email").value+"&Password="+document.getElementById("Password").value + "&attList="+str + "&serverUrl="+ document.getElementById("serverUrl").innerHTML,"_self");
			}
			else {
			  alert(str);
			}
			//var data = JSON.parse(this.response);
		  } else {
			// We reached our target server, but it returned an error
            alert("unable to reach server >>responseText:" + this.responseText + "status:" + this.status + ";readyState:" + this.readyState);
		  }
		};

		xhttp.onerror = function() {
		  //alert("3:" + this.responseJSON + "/" + this.status + "/" + this.readyState);
		  // There was a connection error of some sort
		  alert("connection error");
		};
	 
		//alert(window.location.protocol + "//" + window.location.host+"/CNAMAttendance/webresources/ISSAEServices/getStudentAttendance/"+document.getElementById("Email").value+"/"+document.getElementById("Password").value);
	    }
	
	
	
    /*==================================================================
    [ Show pass ]*/
    var showPass = 0;
    $('.btn-show-pass').on('click', function(){
        if(showPass == 0) {
            $(this).next('input').attr('type','text');
            $(this).find('i').removeClass('zmdi-eye');
            $(this).find('i').addClass('zmdi-eye-off');
            showPass = 1;
        }
        else {
            $(this).next('input').attr('type','password');
            $(this).find('i').addClass('zmdi-eye');
            $(this).find('i').removeClass('zmdi-eye-off');
            showPass = 0;
        }
        
    });


})(jQuery);

function requestListener() {//alert(this.responseText);
	document.getElementById("serverUrl").innerHTML =  this.responseText;
 };

onload = function() {//alert(1);
  var request = new XMLHttpRequest();
  request.onload = requestListener;//alert(1.2);
  request.open("GET", "resource/config.properties", true);//alert(1.5);
  request.send();//alert(2);
 //alert(this.responseText);
};