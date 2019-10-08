function validate()
{
	var ok = true;
	
	var p = document.getElementById("principal").value;
	if (isNaN(p) || p <= 0)
	{
		alert("Eneter positive Principal amount!");
		document.getElementById("principalE").innerHTML = "*";
		ok = false;
	}
	
	
	p = document.getElementById("interest").value;
	if (isNaN(p) || p <= 0 || p > 100)
	{
		alert("Eneter positive interest amount and must be in [1,100].");
		document.getElementById("interestE").innerHTML = "*";
		ok = false;
	}
	
	p = document.getElementById("period").value;
	if (isNaN(p) || p <= 0)
	{
		alert("Eneter positive Period amount!");
		document.getElementById("periodE").innerHTML = "*";
		ok = false;
	}
	return ok;
}



function doSimpleAjax(address)
{
	var ok = true;
	var data='';
	
	var p = document.getElementById("principal").value;
	var r = document.getElementById("interest").value;
	var n = document.getElementById("period").value;
	var g = document.getElementById("grace").checked;
	
	if(p == "" || p <= 0)
	{
		alert("Enter positive principal value.");
		//document.getElementById("principalE").innerHTML = "*";
		ok = false;
	}
	if (r =="" || r <= 0 || r > 100)
	{
		alert("Enter positive interest value between [1,100].");
		//document.getElementById("interestE").innerHTML = "*";
		ok = false;
	}
	if(n == "" || n <= 0)
	{
		alert("Enter positive period value.");
		//document.getElementById("periodE").innerHTML = "*";
		ok = false;
	}	
	if(ok != false)
	{
		var request = new XMLHttpRequest();
		data = "?ajax=1&principal="+ p + "&interest="+r+"&period="+n;
		
		if(g == true)
		{
			data = data + "&grace="+g ;
		}
		
		request.open("GET", (address + data), true);
		
		request.onreadystatechange = function() {
			handler(request);
		};
		
		
		request.send();
	}
		
}


function handler(request)
{
	if ((request.readyState == 4) && (request.status == 200))
	{
		//var target = document.getElementById("ajaxTarget");
		//target.innerHTML = request.responseText;
		var a =request.responseText.split(",");
		document.getElementById("graceI").style.display = "table-row";
		document.getElementById("monthlyP").style.display = "table-row";
		var ginterest = document.getElementById("ginterest");
		var mpayment = document.getElementById("mpayment");
		ginterest.innerHTML = a[0]; 
		mpayment.innerHTML = a[1];
	}
}