var banana = {};
banana.service = {
	
	getBag: function()
	{
		$.get("http://localhost:8080/BananaGram/gameServices/getCurrentBag?mid=1&uid=1", function(data){
			var html = "";
			for(var letter in data.letters)
			{
				html += "<div id=\"Letter_Wrapper\" class=\"draggable\"><div id=\"Letter_Letter\"><span>"+ leter.letter +"</span></div></div>";
			}
			
			$("#Body_Bag_Container").html(html);
		});
	}
	
};

banana.pageLoad = {
	
	init: function()
	{
		banana.service.getBag();
	}
	
};