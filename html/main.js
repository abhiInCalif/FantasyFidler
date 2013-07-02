var ethnic = {};
ethnic.controller = {};
ethnic.service = {};
ethnic.baseUrl = "zipidat.com:8080";

// Ethnic controller goes here;
ethnic.controller = {
	
	init: function()
	{
		this.bind();
	},
	
	bind: function()
	{
		$("#submit").click(function()
		{
			var sub;
			// call the server side script that handles this.
			var input = $("#inputBox").val();
			sub = input.split(" ");
			ethnic.service.classify(sub[0], sub[1]);
			$("#inputBox").val("");
		});
	}
	
}

ethnic.service = {
	
	classify: function(first, last, callback)
	{
		if(!callback)
		{
			$.getJSON("http://"+ ethnic.baseUrl +"/EthnicBuilder/main/classify?first="+ first +"&last="+ last + "&callback=?", function(data)
			{
				$("#ethnicResponse").html(data);
				$("#resultOverlay").jqm();
				$("#resultOverlay").jqmShow();
			});
		}
	}
}