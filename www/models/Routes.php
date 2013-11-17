<?php

class Routes {
	private $db;
	public function __construct($db){
		$this->db = $db;
	}
	public function getAll(){
		$ret = array('status' => 0);

		$query = 'SELECT r.id, r.waypoints, r.kitchen, k.name as kitchen_name
		FROM route r
		JOIN kitchen k
		ON r.kitchen = k.id';
		$st = $this->db->query($query);
		$st->execute();
		$ret['routes'] = $st->fetchAll(PDO::FETCH_ASSOC);

		return $ret;
	}
}
