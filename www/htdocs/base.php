<?php

define('PATH', realpath(dirname(__FILE__)));

if(gethostname()=="beachhouse" || gethostname()=="beachhouse.local"){
	define("BASE_URL","/codeforgood/team6/www/htdocs");
}else {
    define("BASE_URL","");
}

function path($resource){
	return PATH.'/'.$resource;
}

function url($resource, $options = array()){
	return BASE_URL.'/'.$resource;
}

function view($__view_name, $__vars = array()){
    extract($__vars);
    require(path('views/'.$__view_name.'.php'));
	error_log(path('views/'.$__view_name.'.php'));
}

function controller($__controller_name, $__vars = array()){
    extract($__vars);
    require(path('controllers/'.$__controller_name.'.php'));
	error_log(path('controllers/'.$__controller_name.'.php'));
}
