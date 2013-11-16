<?php

define("DB_HOSTNAME", '127.0.0.1');
define("DB_DATABASE", 'thefoodchain');
define("DB_USERNAME", 'root');
define("DB_PASSWORD", 'dshr3VCRWDR3j5@gbr@');	
if(gethostname() == "beachhouse" || gethostname() == "beachhouse.local"){
	define("DB_PORT", '33306');
}else{
	define("DB_PORT", '3306');
}

function getPDOHandle() {
	$dsn = 'mysql:dbname='.DB_DATABASE.';host='.DB_HOSTNAME
			.';port='.DB_PORT;
	try{
		$db = new PDO($dsn, DB_USERNAME, DB_PASSWORD);
	}catch (PDOException $e){
		throw new Exception('Could not connect to database: '.$e->getMessage());
		return false;
	}
	$db->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
	return $db;
}
