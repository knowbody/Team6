<?php

require_once('base.php');
require_once(PATH.'/../includes/database.php');

// pages
$page = empty($_GET['page']) ? '' :
str_replace('/','',strtolower($_GET['page']));
$subpage = empty($_GET['subpage']) ? '' : str_replace('/','',strtolower($_GET['subpage']));
if(empty($subpage)) $subpage = 'main';

if(empty($page)) {
	$page = "home";
}else if(!file_exists(path('views/main-'.$page.'.php'))) {
	$page = "not-found";
}

if($page == 'home') $page = 'upload';

if(file_exists(path('controllers/'.$page.'.php'))){
	$mainController = $page;
}else{
	$mainController = 'main';
}

controller('header', array(
	'page' => $page,
	'subpage' => $subpage
));
controller($mainController, array(
	'page' => $page,
	'subpage' => $subpage,
	'db' => getPDOHandle()
));
controller('footer', array(
	'page' => $page,
	'subpage' => $subpage
));
