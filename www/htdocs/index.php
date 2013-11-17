<?php

require_once('base.php');

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

controller('header', array(
	'page' => $page,
	'subpage' => $subpage
));
controller('main', array(
	'page' => $page,
	'subpage' => $subpage
));
controller('footer', array(
	'page' => $page,
	'subpage' => $subpage
));
