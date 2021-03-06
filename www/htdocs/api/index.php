<?php

require_once('../../includes/database.php');
require_once('../../models/Routes.php');
require_once('../../models/ServiceUsers.php');
$db = getPDOHandle();

require 'Slim/Slim.php';
use Slim\Slim;
\Slim\Slim::registerAutoloader();
$app = new \Slim\Slim();

$app->get('/', function(){
	?>
	<strong>Available API calls:</strong><br>
	<br>
	<em>GET</em><br>
	/kitchen<br>
	/volunteer<br>
	/service_user<br>
	/service_user/:id<br>
	/service_user_meal<br>
	/service_user_meal/:service_user<br>
	/route<br>
	<br>
	<em>POST</em><br>
	/route -> waypoints, kitchen
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

$app->get('/route', function(){
	global $db;
	$route = new Routes($db);
	echo json_encode($route->getAll());
});

$app->post('/route', function(){
	global $db;
	$ret = array('status' => 0);
	$request = Slim::getInstance()->request();
	$waypoints = $request->params('waypoints');
	$kitchen = $request->params('kitchen');
	$driver = substr(sha1(mt_rand()), 0, 6);

	$query = "INSERT INTO route (waypoints, kitchen, driver) VALUES (?, ?, ?)";
	$st = $db->prepare($query);
	$st->execute(array($waypoints, $kitchen, $driver));

	echo json_encode($ret);
});

$app->get('/service_user', function(){
	global $db;
	$serviceUsers = new ServiceUsers($db);
	echo json_encode($serviceUsers->getAll());
});

$app->get('/service_user/:id', function($id){
	global $db;
	$serviceUsers = new ServiceUsers($db);
	echo json_encode($serviceUsers->getUserById($id));
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
