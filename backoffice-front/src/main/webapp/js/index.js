var token = "";
let domain = "localhost";
let portService = 8081;


function getServiceSync( service, callback, id ) {

	const xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
    	if (this.readyState == 4 && this.status == 200) {
  			callback(JSON.parse(this.responseText));
		} else if (this.status == 400 ) { alert(this.responseText);  }
	}
	if( id == null || id==undefined){
		xhttp.open("GET", "http://"+domain+":"+portService+"/sales-order/"+service+"?token="+token, false);
	}else{
		xhttp.open("GET", "http://"+domain+":"+portService+"/sales-order/"+service+"/"+id+"?token="+token, false);
	}
	xhttp.send();
};

function getService( service, callback, id ) {

	const xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
    	if (this.readyState == 4 && this.status == 200) {
  			callback(JSON.parse(this.responseText));
		} else if (this.status == 400 ) { alert(this.responseText); }
	}
	if( id == null || id==undefined){
		xhttp.open("GET", "http://"+domain+":"+portService+"/sales-order/"+service+"?token="+token);
	}else{
		xhttp.open("GET", "http://"+domain+":"+portService+"/sales-order/"+service+"/"+id+"?token="+token);
	}
	xhttp.send();
};

function deleteService( service, callback, id ) {

	const xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
    	if (this.readyState == 4 && this.status == 200) {
  			callback(JSON.parse(this.responseText));
		} else if (this.status == 400 ) { alert(this.responseText); }
	}
	xhttp.open("DELETE", "http://"+domain+":"+portService+"/sales-order/"+service+"/"+id+"?token="+token);	
	xhttp.send();
};

function setService( service, callback, id, jsonBody ) {

	const xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
    	if (this.readyState == 4 && this.status == 200) {
  			callback(JSON.parse(this.responseText));
		} else if (this.status == 400 ) { alert(this.responseText); }
	}
	
	if( id == null || id == 0 ){
		xhttp.open("POST", "http://"+domain+":"+portService+"/sales-order/"+service+"?token="+token);
	}else{
		xhttp.open("PUT", "http://"+domain+":"+portService+"/sales-order/"+service+"/"+id+"?token="+token);
	}
	xhttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
	xhttp.send( JSON.stringify(jsonBody) );
};

function getFilteredByKey(array, key, value) {
  return array.filter(function(e) {
    return e[key] == value;
  });
}