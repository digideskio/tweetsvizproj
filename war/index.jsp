<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="en" style="">

<head>
<meta http-equiv="X-UA-Compatible" content="chrome=1" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet" type="text/css" href="./res/css/searchstyles.css" />
<link rel="stylesheet" type="text/css" href="./res/css/maincss.css" />
<link rel="stylesheet" type="text/css" href="./res/css/tabbed.css" />
<link rel="stylesheet" type="text/css" href="./res/css/blue.css" />
<link rel="stylesheet" type="text/css" href="./res/css/animate.min.css" />
<link rel="stylesheet" type="text/css" href="./res/css/scatter_plot_3d.css"/>
<title>Tweets Sentiment Visualization</title>
</head>

<body style="zoom: 1;">

	<script type="text/javascript" src="./res/js/jquery.min.js"></script>
	<script type="text/javascript" src="./res/js/plugins.js"></script>
	<script type="text/javascript" src="./res/js/script.js"></script>
	<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/d3/3.4.1/d3.min.js"></script>  -->
	<script type="text/javascript" src="./res/js/highcharts.js"></script>
	<script type="text/javascript" src="./res/js/highcharts-3d.js"></script>
	<script type="text/javascript" src="./res/js/exporting.js"></script>
	<script type="text/javascript" src="./res/js/scatter_plot_3d.js"></script>
    	
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

			<div id="searchfd">
				<form>
					<input type="text" id="keyword" placeholder="Type anything you wanna know here" />
					<input type="submit" id="search" class="solid" value="Search" />
					<div id="tempresults" style="height:1500px; width:1000px;"></div>
				</form>
			</div>
		</div>

		<div id="resultcontent" class="wrapperresult" >
			<section id="tabbed"> <!-- First tab input and label --> <input
				id="t-1" name="tabbed-tabs" type="radio" checked="checked" /> <label
				for="t-1" class="tabs shadow entypo-pencil">Content</label> <!-- Second tab input and label -->
			<input id="t-2" name="tabbed-tabs" type="radio" /> <label for="t-2"
				class="tabs shadow entypo-paper-plane">Form</label> <!-- Third tab input and label -->
			<input id="t-3" name="tabbed-tabs" type="radio" /> <label for="t-3"
				class="tabs shadow entypo-menu">Table</label> <!-- Fourth tab input and label -->
			<input id="t-4" name="tabbed-tabs" type="radio" /> <label for="t-4"
				class="tabs shadow entypo-star">Icons</label> <!-- Fifth tab input and label -->
			<input id="t-5" name="tabbed-tabs" type="radio" /> <label for="t-5"
				class="tabs shadow entypo-play">Video</label> <!-- Sixth tab input and label -->
			<input id="t-6" name="tabbed-tabs" type="radio" /> <label for="t-6"
				class="tabs shadow entypo-layout">Grid</label> <!-- Tabs wrapper -->
			<div class="wrapper shadow">

				<!-- Tab 1 content -->
				<div class="tab-1">
					<div class="row">
						<div class="cell w-100">
							<!-- Large title -->
							<div class="title large">Large heading</div>
						</div>
					</div>
					<div class="row">
						<div class="cell w-100">
							<!-- Large paragraph -->
							<p class="large">
								<b>This is a large paragraph</b>. Mauris urna. Mauris velit
								parturient sit penatibus sed cras pulvinar, elementum vut, augue
								duis egestas nec platea diam dignissim, magnis! Etiam platea!
								Augue penatibus, porta. Turpis scelerisque ut, risus duis.
							</p>
						</div>
					</div>
					<div class="clrfx mt-20"></div>
					<div class="row">
						<div class="cell w-66">
							<div class="cell w-100">
								<!-- Normal title -->
								<div class="title normal">Medium heading</div>
							</div>
							<div class="cell w-100">
								<!-- Medium paragraph -->
								<p class="normal">
									<b>This is a medium paragraph</b>. Non parturient lorem dolor
									parturient vel a platea cum pellentesque elit nisi sagittis
									placerat rhoncus mauris et scelerisque ultricies natoque
									aliquet ut vel vut magna tincidunt. Non parturient lorem dolor
									parturient vel a platea cum pellentesque elit nisi sagittis
									placerat.
								</p>
							</div>
							<div class="cell w-100">
								<!-- Quote paragraph -->
								<p class="quote">
									<b>This is basic quote paragraph.</b> On parturient lorem dolor
									parturient vel a platea cum pellentesque elit nisi sagit
									placerat rhoncus mauris et scelerisque ultricies natoque
									aliquet ut vel vut magna tincidunt magna, magna augue eu.
								</p>
							</div>
						</div>
						<div class="cell w-32">
							<!-- Framed quote paragraph with left arrow -->
							<p class="framed-quote arrow-left">
								<span class="title inline">Framed quote</span>This is framed
								quote paragraph. Mauris urna. Mauris velit parturient sit
								penatibus sed cras pulvinar, elementum vut, augue duis egestas
								nec platea diam dignissim.<br /> <br /> <span
									class="icon entypo-clock"></span>Non parturient lorem dolor
								parturient vel a platea cum pellentesque elit nisi sagittis
								placerat.
							</p>
						</div>
					</div>
					<div class="clrfx mt-20"></div>
					<div class="row">
						<div class="cell w-32">
							<div class="cell w-100">
								<!-- Small title -->
								<div class="title small">1. Small heading</div>
							</div>
							<div class="cell w-100">
								<!-- Small paragraph -->
								<p class="small">
									<b>This is a small paragraph</b>. Non parturient lorem dolor
									parturient vel a platea cum pellentesque elit nisi sagittis
									placerat rhoncus mauris et scelerisque ultricies natoque
									aliquet ut vel vut magna tincidunt magna, magna augue eu.
									Ultrices purus lectus elit. Nunc penatibus.
								</p>
							</div>
						</div>
						<div class="cell w-32">
							<div class="cell w-100">
								<!-- Small title -->
								<div class="title small">2. Small heading</div>
							</div>
							<div class="cell w-100">
								<!-- Small paragraph -->
								<p class="small">
									<b>This is a small paragraph</b>. Non parturient lorem dolor
									parturient vel a platea cum pellentesque elit nisi sagittis
									placerat rhoncus mauris et scelerisque ultricies natoque
									aliquet ut vel vut magna tincidunt magna, magna augue eu.
									Ultrices purus lectus elit. Nunc penatibus.
								</p>
							</div>
						</div>
						<div class="cell w-32">
							<div class="cell w-100">
								<!-- Small title -->
								<div class="title small">3. Small heading</div>
							</div>
							<div class="cell w-100">
								<!-- Small paragraph -->
								<p class="small">
									<b>This is a small paragraph</b>. Non parturient lorem dolor
									parturient vel a platea cum pellentesque elit nisi sagittis
									placerat rhoncus mauris et scelerisque ultricies natoque
									aliquet ut vel vut magna tincidunt magna, magna augue eu.
									Ultrices purus lectus elit. Nunc penatibus.
								</p>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="cell w-66">
							<!-- Buttons -->
							<div class="cell w-100">
								<div class="cell w-49">
									<a class="button blue entypo-right-circled" href="#">Wide
										blue button</a>
								</div>
								<div class="cell w-32">
									<a class="button blue entypo-right-circled" href="#">Button</a>
								</div>
								<div class="cell w-15">
									<a class="button grey light entypo-right-circled" href="#">Go</a>
								</div>
							</div>
							<div class="cell w-100">
								<div class="cell w-49">
									<a class="button black entypo-right-circled" href="#">Wide
										dark button</a>
								</div>
								<div class="cell w-32">
									<a class="button black entypo-right-circled" href="#">Button</a>
								</div>
								<div class="cell w-15">
									<a class="button grey dark entypo-right-circled" href="#">Go</a>
								</div>
							</div>
						</div>
						<div class="cell w-32">
							<div class="clrfx mt-15"></div>
							<!-- List style -->
							<ul>
								<div class="title inline">List style</div>
								<li>Non parturient purus lectus</li>
								<li>parturient vel a platea cum</li>
								<li>elit nisi placerat rhoncus mauris</li>
								<li>placerat rhoncus scelerisque ultricies</li>
								<li>scelerisque ultricies arturient lorem</li>
							</ul>
						</div>
					</div>
				</div>
				<!-- / Tab 1 content -->

				<!-- Tab 2 content -->
				<div class="tab-2">
					<div class="row">
						<div class="cell w-100">
							<div class="title large">Forms</div>
						</div>
					</div>
					<div class="row">
						<div class="cell w-100">
							<p class="normal">
								<span class="icon entypo-paper-plane"></span>Responsive form is
								ideal for any kind of opportunity. Create simple forms within
								tabs for you visitors.
							</p>
						</div>
					</div>
					<div class="row">
						<!-- Form -->
						<div class="cell w-49">
							<form id="tab-form" name="tab-form">
								<!-- Name input -->
								<div class="cell w-100">
									<input name="name" placeholder="Your name" type="text"
										id="name" required />
								</div>
								<div class="clrfx mt-10"></div>
								<!-- Email input -->
								<div class="cell w-100">
									<input name="email" placeholder="your@email.com" type="email"
										id="email" required />
								</div>
								<div class="clrfx mt-10"></div>
								<!-- Phone input -->
								<div class="cell w-100">
									<input name="phone" placeholder="+44 123 456" type="tel"
										id="phone" required />
								</div>
								<div class="clrfx mt-10"></div>
								<!-- Subject input -->
								<div class="cell w-100">
									<select name="subject" id="subject" class="grayed"
										onClick="this.className=this.options[this.selectedIndex].className">
										<option value="" disabled="disabled" selected="selected"
											class="disabled">Subject</option>
										<option value="Option 1">1. Option</option>
										<option value="Option 2">2. Option</option>
										<option value="Option 3">3. Option</option>
									</select>
									<div id="arrow-select"></div>
									<svg id="arrow-select-svg"></svg>
								</div>
								<div class="clrfx mt-10"></div>
								<!-- Message -->
								<div class="cell w-100">
									<textarea name="message" placeholder="Message" id="message"
										class="message" required></textarea>
								</div>
								<div class="clrfx mt-30"></div>
								<!-- Send button -->
								<div class="cell w-32">
									<input type="submit" value="Submit" class="button black"
										form="tab-form" />
								</div>
								<!-- Reset button -->
								<div class="cell w-66">
									<div class="clrfx mt-20"></div>
									<input type="reset" id="reset-form" class="reset"
										value="&#8226; Reset form" />
								</div>
							</form>
						</div>
						<!-- Map -->
						<div class="cell w-49">
							<!-- Google maps -->
							<div id="map-canvas" class="map fl-right"></div>
						</div>
					</div>
				</div>
				<!-- / Tab 2 content -->

				<!-- Tab 3 content -->
				<div class="tab-3">
					<div class="row">
						<div class="cell w-100">
							<div class="title large">Table preview</div>
						</div>
					</div>
					<div class="row">
						<div class="cell w-100">
							<p class="normal">
								<b>This is a sample table preview</b>. Ridiculus habitasse mus
								amet dignissim penatibus nec cursus massa turpis a dis hac sed,
								nec! Lectus eu. Platea aliquet lorem phasellus enim ut in lorem
								et? Penatibus facilisis, tristique elit.
							</p>
						</div>
					</div>
					<div class="row">
						<div class="cell w-100">
							<div class="title small icon entypo-chart-line">Data
								preview</div>
						</div>
					</div>
					<div class="clrfx mt-10"></div>
					<!-- Blue headline table -->
					<div class="row">
						<!-- Column 1 -->
						<div class="cell w-15 table first blue">
							<div class="cell w-100">
								<div class="title table headline">Headline</div>
							</div>
							<div class="cell w-100">
								<p class="normal">Section 1</p>
							</div>
							<div class="cell w-100">
								<p class="normal">Section 2</p>
							</div>
							<div class="cell w-100">
								<p class="normal">Section 3</p>
							</div>
							<div class="cell w-100">
								<p class="normal">Section 4</p>
							</div>
							<div class="cell w-100">
								<p class="normal">Section 5</p>
							</div>
							<div class="clrfx mt-5"></div>
						</div>
						<!-- Column 2 -->
						<div class="cell w-15">
							<div class="cell w-100">
								<div class="title table">Title 1</div>
							</div>
							<div class="cell w-100">
								<p class="normal">Data 1</p>
							</div>
							<div class="cell w-100">
								<p class="normal">Data 2</p>
							</div>
							<div class="cell w-100">
								<p class="normal">Data 3</p>
							</div>
							<div class="cell w-100">
								<p class="normal">Data 4</p>
							</div>
							<div class="cell w-100">
								<p class="normal">Data 5</p>
							</div>
						</div>
						<!-- Column 3 -->
						<div class="cell w-15">
							<div class="cell w-100">
								<div class="title table">Title 3</div>
							</div>
							<div class="cell w-100">
								<p class="normal">Data 1</p>
							</div>
							<div class="cell w-100">
								<p class="normal">Data 2</p>
							</div>
							<div class="cell w-100">
								<p class="normal">Data 3</p>
							</div>
							<div class="cell w-100">
								<p class="normal">Data 4</p>
							</div>
							<div class="cell w-100">
								<p class="normal">Data 5</p>
							</div>
						</div>
						<!-- Column 4 -->
						<div class="cell w-15">
							<div class="cell w-100">
								<div class="title table">Title 4</div>
							</div>
							<div class="cell w-100">
								<p class="normal">Data 1</p>
							</div>
							<div class="cell w-100">
								<p class="normal">Data 2</p>
							</div>
							<div class="cell w-100">
								<p class="normal">Data 3</p>
							</div>
							<div class="cell w-100">
								<p class="normal">Data 4</p>
							</div>
							<div class="cell w-100">
								<p class="normal">Data 5</p>
							</div>
						</div>
						<!-- Column 5 -->
						<div class="cell w-15 table exposed">
							<div class="cell w-100">
								<div class="title table">Title 5</div>
							</div>
							<div class="cell w-100">
								<p class="normal">Data 1</p>
							</div>
							<div class="cell w-100">
								<p class="normal">Data 2</p>
							</div>
							<div class="cell w-100">
								<p class="normal">Data 3</p>
							</div>
							<div class="cell w-100">
								<p class="normal">Data 4</p>
							</div>
							<div class="cell w-100">
								<p class="normal">Data 5</p>
							</div>
						</div>
						<!-- Column 6 -->
						<div class="cell w-15">
							<div class="cell w-100">
								<div class="title table">Title 6</div>
							</div>
							<div class="cell w-100">
								<p class="normal">Data 1</p>
							</div>
							<div class="cell w-100">
								<p class="normal">Data 2</p>
							</div>
							<div class="cell w-100">
								<p class="normal">Data 3</p>
							</div>
							<div class="cell w-100">
								<p class="normal">Data 4</p>
							</div>
							<div class="cell w-100">
								<p class="normal">Data 5</p>
							</div>
						</div>
					</div>
					<div class="clrfx mt-10"></div>
					<!-- Black headline table -->
					<div class="row">
						<!-- Column 1 -->
						<div class="cell w-15 table first black">
							<div class="cell w-100">
								<div class="title table headline">Headline</div>
							</div>
							<div class="cell w-100">
								<p class="normal">Section 1</p>
							</div>
							<div class="cell w-100">
								<p class="normal">Section 2</p>
							</div>
							<div class="cell w-100">
								<p class="normal">Section 3</p>
							</div>
							<div class="cell w-100">
								<p class="normal">Section 4</p>
							</div>
							<div class="cell w-100">
								<p class="normal">Section 5</p>
							</div>
							<div class="clrfx mt-5"></div>
						</div>
						<!-- Column 2 -->
						<div class="cell w-15">
							<div class="cell w-100">
								<div class="title table">Title 1</div>
							</div>
							<div class="cell w-100">
								<p class="normal">Data 1</p>
							</div>
							<div class="cell w-100">
								<p class="normal">Data 2</p>
							</div>
							<div class="cell w-100">
								<p class="normal">Data 3</p>
							</div>
							<div class="cell w-100">
								<p class="normal">Data 4</p>
							</div>
							<div class="cell w-100">
								<p class="normal">Data 5</p>
							</div>
						</div>
						<!-- Column 3 -->
						<div class="cell w-15">
							<div class="cell w-100">
								<div class="title table">Title 3</div>
							</div>
							<div class="cell w-100">
								<p class="normal">Data 1</p>
							</div>
							<div class="cell w-100">
								<p class="normal">Data 2</p>
							</div>
							<div class="cell w-100">
								<p class="normal">Data 3</p>
							</div>
							<div class="cell w-100">
								<p class="normal">Data 4</p>
							</div>
							<div class="cell w-100">
								<p class="normal">Data 5</p>
							</div>
						</div>
						<!-- Column 4 -->
						<div class="cell w-15 table exposed">
							<div class="cell w-100">
								<div class="title table">Title 4</div>
							</div>
							<div class="cell w-100">
								<p class="normal">Data 1</p>
							</div>
							<div class="cell w-100">
								<p class="normal">Data 2</p>
							</div>
							<div class="cell w-100">
								<p class="normal">Data 3</p>
							</div>
							<div class="cell w-100">
								<p class="normal">Data 4</p>
							</div>
							<div class="cell w-100">
								<p class="normal">Data 5</p>
							</div>
						</div>
						<!-- Column 5 -->
						<div class="cell w-15">
							<div class="cell w-100">
								<div class="title table">Title 5</div>
							</div>
							<div class="cell w-100">
								<p class="normal">Data 1</p>
							</div>
							<div class="cell w-100">
								<p class="normal">Data 2</p>
							</div>
							<div class="cell w-100">
								<p class="normal">Data 3</p>
							</div>
							<div class="cell w-100">
								<p class="normal">Data 4</p>
							</div>
							<div class="cell w-100">
								<p class="normal">Data 5</p>
							</div>
						</div>
						<!-- Column 6 -->
						<div class="cell w-15">
							<div class="cell w-100">
								<div class="title table">Title 6</div>
							</div>
							<div class="cell w-100">
								<p class="normal">Data 1</p>
							</div>
							<div class="cell w-100">
								<p class="normal">Data 2</p>
							</div>
							<div class="cell w-100">
								<p class="normal">Data 3</p>
							</div>
							<div class="cell w-100">
								<p class="normal">Data 4</p>
							</div>
							<div class="cell w-100">
								<p class="normal">Data 5</p>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="cell w-100">
							<p class="small">
								<span class="icon entypo-clock"></span>Augue risus turpis a
								aliquam augue, nisi. Cum magna ultrices adipiscing platea sed
								vel porttitor in diam.
							</p>
						</div>
					</div>
				</div>
				<!-- / Tab 3 content -->

				<!-- Tab 4 content -->
				<div class="tab-4">
					<div class="row">
						<div class="cell w-100">
							<div class="title large">Icons usage</div>
						</div>
					</div>
					<div class="row">
						<div class="cell w-100">
							<p class="normal">
								You can choose from large pack of <a
									href="http://www.entypo.com/" target="_blank">Entypo icons
									set</a>, which are scalable to any resolution without quality loss.<br />
								Over 250 pictograms avaliable for quick use, which are made by <a
									href="http://danielbruce.se/" target="_blank">Daniel Bruce</a>.
								Below is a demo with few examples of icon sizes.
							</p>
						</div>
					</div>
					<div class="clrfx mt-10"></div>
					<!-- Large icons usage section -->
					<div class="cell w-100">
						<div class="title normal">Large size icons</div>
					</div>
					<div class="row">
						<div class="cell w-15">
							<p class="large">
								<span class="icon entypo-chart-pie"></span>Address
							</p>
						</div>
						<div class="cell w-15">
							<p class="large">
								<span class="icon entypo-adjust"></span>Adjusting
							</p>
						</div>
						<div class="cell w-15">
							<p class="large">
								<span class="icon entypo-briefcase"></span>Briefcase
							</p>
						</div>
						<div class="cell w-15">
							<p class="large">
								<span class="icon entypo-calendar"></span>Calendar
							</p>
						</div>
						<div class="cell w-15">
							<p class="large">
								<span class="icon entypo-clipboard"></span>Clipboard
							</p>
						</div>
						<div class="cell w-15">
							<p class="large">
								<span class="icon entypo-paper-plane"></span>Paper plane
							</p>
						</div>
					</div>
					<div class="row">
						<div class="cell w-15">
							<p class="large">
								<span class="icon entypo-globe"></span>Globe
							</p>
						</div>
						<div class="cell w-15">
							<p class="large">
								<span class="icon entypo-folder"></span>Folder
							</p>
						</div>
						<div class="cell w-15">
							<p class="large">
								<span class="icon entypo-heart"></span>Full heart
							</p>
						</div>
						<div class="cell w-15">
							<p class="large">
								<span class="icon entypo-basket"></span>Basket
							</p>
						</div>
						<div class="cell w-15">
							<p class="large">
								<span class="icon entypo-lock"></span>Closed lock
							</p>
						</div>
						<div class="cell w-15">
							<p class="large">
								<span class="icon entypo-monitor"></span>Monitor
							</p>
						</div>
					</div>
					<div class="row">
						<div class="cell w-15">
							<p class="large">
								<span class="icon entypo-magnet"></span>Magnet
							</p>
						</div>
						<div class="cell w-15">
							<p class="large">
								<span class="icon entypo-location"></span>Location
							</p>
						</div>
						<div class="cell w-15">
							<p class="large">
								<span class="icon entypo-print"></span>Print
							</p>
						</div>
						<div class="cell w-15">
							<p class="large">
								<span class="icon entypo-search"></span>Search
							</p>
						</div>
						<div class="cell w-15">
							<p class="large">
								<span class="icon entypo-retweet"></span>Retweet
							</p>
						</div>
						<div class="cell w-15">
							<p class="large">
								<span class="icon entypo-home"></span>Home
							</p>
						</div>
					</div>
					<div class="clrfx mt-30"></div>
					<!-- Normal icons usage section -->
					<div class="cell w-100">
						<div class="title small">NORMAL SIZE ICONS</div>
					</div>
					<div class="row">
						<div class="cell w-15">
							<p class="normal">
								<span class="icon entypo-chart-pie"></span>Address
							</p>
						</div>
						<div class="cell w-15">
							<p class="normal">
								<span class="icon entypo-adjust"></span>Adjusting
							</p>
						</div>
						<div class="cell w-15">
							<p class="normal">
								<span class="icon entypo-briefcase"></span>Briefcase
							</p>
						</div>
						<div class="cell w-15">
							<p class="normal">
								<span class="icon entypo-calendar"></span>Calendar
							</p>
						</div>
						<div class="cell w-15">
							<p class="normal">
								<span class="icon entypo-clipboard"></span>Clipboard
							</p>
						</div>
						<div class="cell w-15">
							<p class="normal">
								<span class="icon entypo-paper-plane"></span>Paper plane
							</p>
						</div>
					</div>
					<div class="row">
						<div class="cell w-15">
							<p class="normal">
								<span class="icon entypo-globe"></span>Globe
							</p>
						</div>
						<div class="cell w-15">
							<p class="normal">
								<span class="icon entypo-folder"></span>Folder
							</p>
						</div>
						<div class="cell w-15">
							<p class="normal">
								<span class="icon entypo-heart"></span>Full heart
							</p>
						</div>
						<div class="cell w-15">
							<p class="normal">
								<span class="icon entypo-basket"></span>Basket
							</p>
						</div>
						<div class="cell w-15">
							<p class="normal">
								<span class="icon entypo-lock"></span>Closed lock
							</p>
						</div>
						<div class="cell w-15">
							<p class="normal">
								<span class="icon entypo-monitor"></span>Monitor
							</p>
						</div>
					</div>
					<div class="row">
						<div class="cell w-15">
							<p class="normal">
								<span class="icon entypo-magnet"></span>Magnet
							</p>
						</div>
						<div class="cell w-15">
							<p class="normal">
								<span class="icon entypo-location"></span>Location
							</p>
						</div>
						<div class="cell w-15">
							<p class="normal">
								<span class="icon entypo-print"></span>Print
							</p>
						</div>
						<div class="cell w-15">
							<p class="normal">
								<span class="icon entypo-search"></span>Search
							</p>
						</div>
						<div class="cell w-15">
							<p class="normal">
								<span class="icon entypo-retweet"></span>Retweet
							</p>
						</div>
						<div class="cell w-15">
							<p class="normal">
								<span class="icon entypo-home"></span>Home
							</p>
						</div>
					</div>
					<div class="clrfx mt-30"></div>
					<!-- Small icons usage section -->
					<div class="cell w-100">
						<div class="title mini">SMALL SIZE ICONS</div>
					</div>
					<div class="row">
						<div class="cell w-15">
							<p class="small">
								<span class="icon entypo-chart-pie"></span>Address
							</p>
						</div>
						<div class="cell w-15">
							<p class="small">
								<span class="icon entypo-adjust"></span>Adjusting
							</p>
						</div>
						<div class="cell w-15">
							<p class="small">
								<span class="icon entypo-briefcase"></span>Briefcase
							</p>
						</div>
						<div class="cell w-15">
							<p class="small">
								<span class="icon entypo-calendar"></span>Calendar
							</p>
						</div>
						<div class="cell w-15">
							<p class="small">
								<span class="icon entypo-clipboard"></span>Clipboard
							</p>
						</div>
						<div class="cell w-15">
							<p class="small">
								<span class="icon entypo-paper-plane"></span>Paper plane
							</p>
						</div>
					</div>
					<div class="row">
						<div class="cell w-15">
							<p class="small">
								<span class="icon entypo-globe"></span>Globe
							</p>
						</div>
						<div class="cell w-15">
							<p class="small">
								<span class="icon entypo-folder"></span>Folder
							</p>
						</div>
						<div class="cell w-15">
							<p class="small">
								<span class="icon entypo-heart"></span>Full heart
							</p>
						</div>
						<div class="cell w-15">
							<p class="small">
								<span class="icon entypo-basket"></span>Basket
							</p>
						</div>
						<div class="cell w-15">
							<p class="small">
								<span class="icon entypo-lock"></span>Closed lock
							</p>
						</div>
						<div class="cell w-15">
							<p class="small">
								<span class="icon entypo-monitor"></span>Monitor
							</p>
						</div>
					</div>
					<div class="row">
						<div class="cell w-15">
							<p class="small">
								<span class="icon entypo-magnet"></span>Magnet
							</p>
						</div>
						<div class="cell w-15">
							<p class="small">
								<span class="icon entypo-location"></span>Location
							</p>
						</div>
						<div class="cell w-15">
							<p class="small">
								<span class="icon entypo-print"></span>Print
							</p>
						</div>
						<div class="cell w-15">
							<p class="small">
								<span class="icon entypo-search"></span>Search
							</p>
						</div>
						<div class="cell w-15">
							<p class="small">
								<span class="icon entypo-retweet"></span>Retweet
							</p>
						</div>
						<div class="cell w-15">
							<p class="small">
								<span class="icon entypo-home"></span>Home
							</p>
						</div>
					</div>
				</div>
				<!-- / Tab 4 content -->

				<!-- Tab 5 content -->
				<div class="tab-5">
					<div class="row">
						<div class="cell w-100">
							<div class="title large">Video preview</div>
						</div>
					</div>
					<div class="row">
						<div class="cell w-100">
							<p class="normal">
								<b>Play any video inside the tabs.</b> Ridiculus habitasse mus
								amet dignissim penatibus nec cursus massa turpis a dis hac sed,
								nec! Lectus eu. Platea aliquet lorem phasellus enim ut in lorem
								et? Penatibus facilisis, tristique elit.
							</p>
						</div>
					</div>
					<div class="clrfx mt-20"></div>
					
					<!-- TEST 3D SCATTER PLOT -->
					<div id="container" style="height: 700px"></div>
					
					<div class="row">
						<div class="cell w-100">
						<!-- 
							<iframe
								src="http://www.youtube.com/embed/vsObnzivL1A?&rel=0&loop=1&showinfo=0&disablekb=1&modestbranding=1&controls=0&hd=1&autohide=1&color=white&wmode=opaque"
								frameborder="0" allowfullscreen> </iframe>
						-->
						</div>
					</div>
				</div>
				<!-- / Tab 5 content -->

				<!-- Tab 6 content -->
				<div class="tab-6">
					<div class="row">
						<div class="cell w-100">
							<div class="title large">Grid layout</div>
						</div>
					</div>
					<div class="row">
						<div class="cell w-100">
							<p class="normal">
								<span class="icon entypo-monitor"></span>Responsive grid is made
								out of 6 columns, which adapt dynamically to the visitors screen
								size.
							</p>
						</div>
					</div>
					<!-- 6/6 -->
					<div class="row">
						<div class="cell w-15">
							<p class="demo-cell">15%</p>
						</div>
						<div class="cell w-15">
							<p class="demo-cell">15%</p>
						</div>
						<div class="cell w-15">
							<p class="demo-cell">15%</p>
						</div>
						<div class="cell w-15">
							<p class="demo-cell">15%</p>
						</div>
						<div class="cell w-15">
							<p class="demo-cell">15%</p>
						</div>
						<div class="cell w-15">
							<p class="demo-cell">15%</p>
						</div>
					</div>
					<!-- 1/3 + 2/3 -->
					<div class="row">
						<div class="cell w-32">
							<p class="demo-cell">32%</p>
						</div>
						<div class="cell w-66">
							<p class="demo-cell">66%</p>
						</div>
					</div>
					<!-- 1/2 + 1/2 -->
					<div class="row">
						<div class="cell w-49">
							<p class="demo-cell">49%</p>
						</div>
						<div class="cell w-49">
							<p class="demo-cell">49%</p>
						</div>
					</div>
					<!-- 2/3 + 1/3 -->
					<div class="row">
						<div class="cell w-66">
							<p class="demo-cell">66%</p>
						</div>
						<div class="cell w-32">
							<p class="demo-cell">32%</p>
						</div>
					</div>
					<!-- 5/6 + 1/6 -->
					<div class="row">
						<div class="cell w-83">
							<p class="demo-cell">83%</p>
						</div>
						<div class="cell w-15">
							<p class="demo-cell">15%</p>
						</div>
					</div>
					<!-- 1/1 -->
					<div class="row">
						<div class="cell w-100">
							<p class="demo-cell">100%</p>
						</div>
					</div>
					<div class="row">
						<div class="cell w-100">
							<p class="small">
								<span class="icon entypo-info"></span>When resizing the screen,
								columns will automatically stack to 100% width for best
								compatibility with various screen sizes.
							</p>
						</div>
					</div>
				</div>
				<!-- / Tab 6 content -->

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