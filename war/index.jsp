<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="en" style="">

<head>
<meta http-equiv="X-UA-Compatible" content="chrome=1" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="./res/css/searchstyles.css" />
<link rel="stylesheet" type="text/css" href="./res/css/maincss.css" />
<link rel="stylesheet" type="text/css" href="./res/css/tabbed.css" />
<link rel="stylesheet" type="text/css" href="./res/css/blue.css" />
<link rel="stylesheet" type="text/css" href="./res/css/animate.min.css" />
<link rel="stylesheet" type="text/css" href="./res/css/scatter_plot_3d.css"/>

  <script src="res/js/pace.min.js"></script>
  <link href="res/css/themes/pace-theme-barber-shop.css" rel="stylesheet" />
<title>Tweets Sentiment Visualization</title>
</head>

<body style="zoom: 1;">
	
	<script type="text/javascript" src="./res/js/jquery.min.js"></script>
	<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
	<script type="text/javascript" src="./res/js/plugins.js"></script>
	<script type="text/javascript" src="./res/js/script.js"></script>
	<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/d3/3.4.1/d3.min.js"></script>  -->
	<script type="text/javascript" src="./res/js/highcharts.js"></script>
	<script type="text/javascript" src="./res/js/highcharts-3d.js"></script>
	<script type="text/javascript" src="./res/js/exporting.js"></script>
	<script type="text/javascript" src="./res/js/scatter_plot_3d.js"></script>
    <script type="text/javascript" src="./res/js/compare.js"></script>
	<div class="hide konami"></div>
	<header>
	<div class="ie-lame">it seems internet explorer is too lame to
		see all the features on this website.</div>
	<div class="content">
		<section class="insta-wrapper">
		<div class="brand">
			<span class="bike">&nbsp;</span>
			<h1 class="logo">Tweets Visualization</h1>
			<br>
			<h2 class="">a Tweets Sentiment Analysis toolkit</h2>
		</div>
		<div class="insta-wrapperbk"></div>
		</section>
	</div>
	</header>

	<div class="content">
		<section class="skills wrapper">
		<ul>
			<li class="html animate do-anim">
				<div class="icon">
					<span></span>
				</div>
				<h3>#Commerical Focus</h3>
				<p>Unlike other sentiment analyser, our tweet sentiment
					visualiser has a unique focus on commercial products. bla bla bla
					bla bla bla bla bla bla</p>
			</li>
			<li class="ui animate do-anim">
				<div class="icon">
					<span></span>
				</div>
				<h3>#Hybrid Analysis</h3>
				<p>bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla
					bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla
					bla bla bla bla</p>
			</li>
			<li class="design animate do-anim">
				<div class="icon">
					<span></span>
				</div>
				<h3>#Rich Infographics</h3>
				<p>bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla
					bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla
					bla bla bla bla</p>
			</li>
		</ul>
		</section>
	</div>

	<div class="content about">
		<section class="wrapper clearfix">
		
		<div class="photo animate left do-anim">
			&nbsp;
		</div>
		
		<div class="copy right">
			<h3 class="animate">Howdy!</h3>
			<p class="animate">We present TweetViz, an enhanced
				commercial-oriented Twitter sentiment visualization toolkit;</p>
			<p class="animate">This is a remote collaborative course project
				work done by Yuexing Luo and Mingyu Liu from University of Waterloo;</p>
			<p class="animate">Let's start a new inquiry now and find out how
				awesome it is!</p>
			<a href="#" class="tooltip">
			    #Tooltip
			    <span>
			        
			        <p>Check 'Compare' to search and compare two things together</p><br />
			      
			    </span>
			</a>
			<br/><br/>
			<a href="http://www.java-tutorial.ch/maven/retrieve-tweets-using-twitter4j#twitter_search_operator">Learn more about search operators</a>
			<br/><br/>
			<div id="searchfd">
				<form>
					<input type="text" id="keyword" placeholder="Type anything here" />
					<input type="text" id="keyword2" style="display:none" placeholder="Something else to compare with" />
					<input type="checkbox" id="compare">Compare<br>
					
					<input type="submit" id="search" class="solid" value="Search" />
					<div id="tempresults" style="height:1500px; width:1000px;"></div>
				</form>
			</div>
		</div>

		<div id="resultcontent" class="wrapperresult" >
			<section id="tabbed"> <!-- First tab input and label --> <input
				id="t-1" name="tabbed-tabs" type="radio" /> <label
				for="t-1" class="tabs shadow entypo-pencil">Compare</label> <!-- Second tab input and label -->
			<input id="t-2" name="tabbed-tabs" type="radio" checked="checked"/> <label for="t-2"
				class="tabs shadow entypo-paper-plane">3D View</label> <!-- Third tab input and label -->
			
			<div class="wrapper shadow">

				

				

				<!-- Tab 3 content -->
				<div class="tab-1">
				
				
				<div id="containerCompare1" style="width: 380px; float: left; margin: 0 auto"></div>
				<div id="containerCompare2" style="width: 380px; float: left; margin: 0 auto"></div>
				<div class="row">
						<div class="cell w-49">
							<div class="cell w-100">
								<!-- Small title -->
								<div class="title small">1. Most Frequent Words</div>
							</div>
							<div class="cell w-100">
								<!-- Small paragraph -->
								<p class="normal" id="topWords1">
									
								</p>
							</div>
							<div class="cell w-100">
								<!-- Small title -->
								<div class="title small">2. Top 5 Tweets with Likes or Retweets</div>
							</div>
							<div class="cell w-100">
								<!-- Small paragraph -->
								<p class="normal" id="topTweets1">
									
								</p>
							</div>
						</div>
						<div class="cell w-49">
							<div class="cell w-100">
								<!-- Small title -->
								<div class="title small">1. Most Frequent Words</div>
							</div>
							<div class="cell w-100">
								<!-- Small paragraph -->
								<p class="normal" id="topWords2">
									
								</p>
							</div>
							<div class="cell w-100">
								<!-- Small title -->
								<div class="title small">2. Top 5 Tweets with Likes or Retweets</div>
							</div>
							<div class="cell w-100">
								<!-- Small paragraph -->
								<p class="normal" id="topTweets2">
									
								</p>
							</div>
						</div>
						
					</div>
				
				</div>
				<!-- / Tab 3 content -->


				<!-- Tab 5 content -->
				<div class="tab-2">
					
					
					<!-- TEST 3D SCATTER PLOT -->
					<div id="container" style="height: 700px"></div>
					
					
				</div>
				<!-- / Tab 5 content -->

				

			</div>
			<div class="clrfx mt-30"></div>
			<div class="clrfx mt-30"></div>
			<!-- / Tabs wrapper --> </section>


		</div>

		</section>

	</div>


	<footer>
	<div id="contact-form" class="contact-form clearfix">
		<div class="error"></div>
		<div class="contact">
			<section class="wrapper clearfix">
			<div class="success">
				<h3>Need to be done</h3>
			</div>
			<div class="form-group">
				<h3>Find out how this works.</h3>


			</div>
			</section>
		</div>
	</div>

	</footer>

</body>
</html>