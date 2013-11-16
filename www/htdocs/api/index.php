<?php

require_once('../../helpers/database.php');
$db = getPDOHandle();

require 'Slim/Slim.php';
\Slim\Slim::registerAutoloader();
$app = new \Slim\Slim();

$app->get('/', function(){
	?>
	Available API calls:
	/kitchen
	/volunteer
	/service_user
	/service_user_meal
	/service_user_meal/:service_user
	<?php
});

$app->get('/kitchen', function(){
	global $db;
	$ret = array('status' => 0);

	$query = 'SELECT id, organisation_id, name, organisation, address, postcode, capacity FROM kitchen';
	$st = $db->query($query);
	$st->execute();
	$ret['kitchens'] = $st->fetchAll(PDO::FETCH_ASSOC);

	echo json_encode($ret);
});

$app->get('/volunteer', function(){
	global $db;
	$ret = array('status' => 0);

	$query = 'SELECT id, name, kitchen, mobile_phone, role FROM volunteer';
	$st = $db->query($query);
	$st->execute();
	$ret['volunteers'] = $st->fetchAll(PDO::FETCH_ASSOC);

	echo json_encode($ret);
});

$app->get('/service_user', function(){
	global $db;
	$ret = array('status' => 0);

	$query = 'SELECT id, name, status, postcode, booking_id, birthdate FROM service_user';
	$st = $db->query($query);
	$st->execute();
	$ret['service_users'] = $st->fetchAll(PDO::FETCH_ASSOC);

	echo json_encode($ret);
});

$app->get('/service_user_meal', function(){
	global $db;
	$ret = array('status' => 0);

	$query = 'SELECT id, service_user, meal_type, comments, kitchen_comments FROM service_user_meal';
	$st = $db->query($query);
	$st->execute();
	$ret['service_user_meals'] = $st->fetchAll(PDO::FETCH_ASSOC);

	echo json_encode($ret);
});

$app->get('/service_user_meal/:service_user', function($service_user){
	global $db;
	$ret = array('status' => 0);

	$query = 'SELECT id, service_user, meal_type, comments, kitchen_comments FROM service_user_meal WHERE service_user = ?';
	$st = $db->prepare($query);
	$st->execute(array($service_user));
	$ret['service_user_meals'] = $st->fetchAll(PDO::FETCH_ASSOC);

	echo json_encode($ret);
});

$app->run();
