<?php

require_once('../../classes/TFCParser.php');
require_once('../../classes/TFCDBPopulator.php');
require_once('../../includes/database.php');

$ret = array('status' => 0);
$fileInputs = array('kitchen', 'serviceUser', 'volunteer');
$files = array();

foreach($fileInputs as $fileInput){
	if(!array_key_exists($fileInput, $_FILES)){
		$ret['status'] = 1;
		echo json_encode($ret);
		die();
	}
}

foreach($fileInputs as $fileInput){
	if($_FILES[$fileInput]['error'] != 0) {  
		$ret['status'] = 2;
		echo json_encode($ret);
		die();
	}
	$files[$fileInput] = file_get_contents($_FILES[$fileInput]['tmp_name']);
}

$parser = new TFCParser($files['kitchen'], $files['serviceUser'], $files['volunteer']);
$parser->parse();
$populator = new TFCDBPopulator(getPDOHandle(), $parser->data());
$populator->populate();

echo json_encode($ret);
