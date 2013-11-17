<?php

require_once(PATH.'/../models/Routes.php');
require_once(PATH.'/../models/ServiceUsers.php');

$routes = new Routes($db);
$serviceUsers = new ServiceUsers($db);

$allRoutes = $routes->getAll()['routes'];
$serviceUsersForRoutes = array();
$expandedRoutes = array();

foreach($allRoutes as $indexRoute => $route){
	$expandedRoute = $route;
	$expandedRoute['waypoints'] = explode(',', $route['waypoints']);
	$expandedRoute['postcodes'] = array();
	foreach($expandedRoute['waypoints'] as $waypoint){
		$serviceUsersForRoute[$waypoint] = $serviceUsers->getUserById($waypoint)['service_user'];
		$expandedRoute['postcodes'][] = $serviceUsersForRoute[$waypoint]['postcode'];
	}
	$expandedRoutes[$indexRoute] = $expandedRoute;
	$serviceUsersForRoutes = array_merge($serviceUsersForRoute,
										$serviceUsersForRoutes);
}

view('main-view', array(
	'serviceUsersForRoutes' => $serviceUsersForRoutes,
	'allRoutes' => $allRoutes,
	'expandedRoutes' => $expandedRoutes
));
