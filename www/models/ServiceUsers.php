<?php

class ServiceUsers {
	private $db;
	public function __construct($db){
		$this->db = $db;
	}
	public function getAll(){
		$ret = array('status' => 0);

		$query = 'SELECT u.id, u.name, u.status, u.postcode, u.booking_id, u.birthdate, COUNT(*) AS no_meals
		FROM service_user u
		JOIN service_user_meal m
		ON u.id = m.service_user
		GROUP BY u.id';
		$st = $this->db->query($query);
		$st->execute();
		$ret['service_users'] = $st->fetchAll(PDO::FETCH_ASSOC);

		return $ret;
	}
	public function getUserById($id){
		$ret = array('status' => 0);

		$query = 'SELECT id, name, status, postcode, booking_id, birthdate FROM service_user WHERE id = ?';
		$st = $this->db->prepare($query);
		$st->execute(array($id));
		$ret['service_user'] = $st->fetchAll(PDO::FETCH_ASSOC)[0];

		return $ret;
	}
}
