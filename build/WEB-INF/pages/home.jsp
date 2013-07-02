<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>untitled</title>
	<meta name="generator" content="TextMate http://macromates.com/">
	<meta name="author" content="Abhinav  Khanna">
	<link rel="stylesheet" href="main.css" />
	<script src="http://code.jquery.com/jquery-1.8.2.js"></script>
  <script src="http://code.jquery.com/ui/1.9.1/jquery-ui.js"></script>
	<script src="services.js"></script>
	<script>
		$(function()
		{
			$(".draggable").draggable({snap: ".Body_GameBoard_LetterDrop"});
		});
		
		$(document).ready(function()
		{
			banana.pageLoad.init();
		});
	
	</script>
	
	<!-- Date: 2012-11-15 -->
</head>
<body>
	<div id="Body_Wrapper">
		<div id="Body_Header">
			<!--This is the header information.....Could be a nav bar? Could just contain the score / Turn # -->
			<div id="Body_Title_LOGO"><span>LOGO</span></div>
			<div id="Body_Title_TurnAndScore"><span>Player 1's Turn | Score (By Player): 10 0 0 10</span></div>
		</div>
		<div id="Body_GameBoard">
			<!--Game board-->
			<!--Needs to represent a grid in some sense as things have to snap to a certain location..... an ordered list perhaps?-->
			<div class="Body_GameBoard_Row">
					<div class="Body_GameBoard_Column"><span class="Body_GameBoard_LetterDrop"></span></div>
					<div class="Body_GameBoard_Column"><span class="Body_GameBoard_LetterDrop"></span></div>
					<div class="Body_GameBoard_Column"><span class="Body_GameBoard_LetterDrop"></span></div>
					<div class="Body_GameBoard_Column"><span class="Body_GameBoard_LetterDrop"></span></div>
					<div class="Body_GameBoard_Column"><span class="Body_GameBoard_LetterDrop"></span></div>
					<div class="Body_GameBoard_Column"><span class="Body_GameBoard_LetterDrop"></span></div>
					<div class="Body_GameBoard_Column"><span class="Body_GameBoard_LetterDrop"></span></div>
			</div>
			<div class="Body_GameBoard_Row">
				<div class="Body_GameBoard_Column"><span class="Body_GameBoard_LetterDrop"></span></div>
				<div class="Body_GameBoard_Column"><span class="Body_GameBoard_LetterDrop"></span></div>
				<div class="Body_GameBoard_Column"><span class="Body_GameBoard_LetterDrop"></span></div>
				<div class="Body_GameBoard_Column"><span class="Body_GameBoard_LetterDrop"></span></div>
				<div class="Body_GameBoard_Column"><span class="Body_GameBoard_LetterDrop"></span></div>
				<div class="Body_GameBoard_Column"><span class="Body_GameBoard_LetterDrop"></span></div>
				<div class="Body_GameBoard_Column"><span class="Body_GameBoard_LetterDrop"></span></div>
			</div>
			<div class="Body_GameBoard_Row">
				<div class="Body_GameBoard_Column"><span class="Body_GameBoard_LetterDrop"></span></div>
				<div class="Body_GameBoard_Column"><span class="Body_GameBoard_LetterDrop"></span></div>
				<div class="Body_GameBoard_Column"><span class="Body_GameBoard_LetterDrop"></span></div>
				<div class="Body_GameBoard_Column"><span class="Body_GameBoard_LetterDrop"></span></div>
				<div class="Body_GameBoard_Column"><span class="Body_GameBoard_LetterDrop"></span></div>
				<div class="Body_GameBoard_Column"><span class="Body_GameBoard_LetterDrop"></span></div>
				<div class="Body_GameBoard_Column"><span class="Body_GameBoard_LetterDrop"></span></div>
			</div>
			<div class="Body_GameBoard_Row">
				<div class="Body_GameBoard_Column"><span class="Body_GameBoard_LetterDrop"></span></div>
				<div class="Body_GameBoard_Column"><span class="Body_GameBoard_LetterDrop"></span></div>
				<div class="Body_GameBoard_Column"><span class="Body_GameBoard_LetterDrop"></span></div>
				<div class="Body_GameBoard_Column"><span class="Body_GameBoard_LetterDrop"></span></div>
				<div class="Body_GameBoard_Column"><span class="Body_GameBoard_LetterDrop"></span></div>
				<div class="Body_GameBoard_Column"><span class="Body_GameBoard_LetterDrop"></span></div>
				<div class="Body_GameBoard_Column"><span class="Body_GameBoard_LetterDrop"></span></div>
			</div>
			<div class="Body_GameBoard_Row">
				<div class="Body_GameBoard_Column"><span class="Body_GameBoard_LetterDrop"></span></div>
				<div class="Body_GameBoard_Column"><span class="Body_GameBoard_LetterDrop"></span></div>
				<div class="Body_GameBoard_Column"><span class="Body_GameBoard_LetterDrop"></span></div>
				<div class="Body_GameBoard_Column"><span class="Body_GameBoard_LetterDrop"></span></div>
				<div class="Body_GameBoard_Column"><span class="Body_GameBoard_LetterDrop"></span></div>
				<div class="Body_GameBoard_Column"><span class="Body_GameBoard_LetterDrop"></span></div>
				<div class="Body_GameBoard_Column"><span class="Body_GameBoard_LetterDrop"></span></div>
			</div>
			<div class="Body_GameBoard_Row">
				<div class="Body_GameBoard_Column"><span class="Body_GameBoard_LetterDrop"></span></div>
				<div class="Body_GameBoard_Column"><span class="Body_GameBoard_LetterDrop"></span></div>
				<div class="Body_GameBoard_Column"><span class="Body_GameBoard_LetterDrop"></span></div>
				<div class="Body_GameBoard_Column"><span class="Body_GameBoard_LetterDrop"></span></div>
				<div class="Body_GameBoard_Column"><span class="Body_GameBoard_LetterDrop"></span></div>
				<div class="Body_GameBoard_Column"><span class="Body_GameBoard_LetterDrop"></span></div>
				<div class="Body_GameBoard_Column"><span class="Body_GameBoard_LetterDrop"></span></div>
			</div>
		</div>
		<div id="Body_Bag">
			<!--Your bag of characters-->
			<!-- Snippet for the Tiles -->
			<div id="Body_Bag_Container">
				<div id="Letter_Wrapper" class="draggable">
					<div id="Letter_Letter"><span>L</span></div>
				</div>
				<!-- Snippet for the Tiles -->
				<div id="Letter_Wrapper" class="draggable">
					<div id="Letter_Letter"><span>L</span></div>
				</div>
				<!-- Snippet for the Tiles -->
				<div id="Letter_Wrapper" class="draggable">
					<div id="Letter_Letter"><span>L</span></div>
				</div>
				<!-- Snippet for the Tiles -->
				<div id="Letter_Wrapper" class="draggable">
					<div id="Letter_Letter"><span>L</span></div>
				</div>
			</div>
		</div>
	</div>
	
</body>
</html>
