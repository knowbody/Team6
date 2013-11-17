<!doctype html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>The Food Chain</title>
		<link rel="stylesheet" href="<?php echo url('stylesheets/bootstrap.min.css'); ?>">
		<link rel="stylesheet" href="<?php echo url('stylesheets/style.css'); ?>">
	</head>
	<body>
		<header>
			<img src="img/logo.png">
			<container>
				<nav class="clearfix">
					<div class="navitem<?php echo $page == 'upload' ? ' current' : ''; ?>">
						<a href="upload">Upload database</a>
					</div>
					<div class="navitem<?php echo $page == 'routes' ? ' current' : ''; ?>">
						<a href="routes">View routes</a>
					</div>
				</nav>
			</container>
		</header>
