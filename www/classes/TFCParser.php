<?php

class TFCParser {
	private $csv;
	private $data;
	public function __construct($fileKitchen, $fileServiceUser, $fileVolunteer){
		$this->csv = array();
		$this->data = array();
		$this->loadFromCSV('kitchen', $fileKitchen);
		$this->loadFromCSV('service_user', $fileServiceUser);
		$this->loadFromCSV('volunteer', $fileVolunteer);
	}
	public function parse(){
		$this->parseKitchen();
		$this->parseServiceUser();
		$this->parseVolunteer();
	}
	public function data(){
		return $this->data;
	}
	private function loadFromCSV($name, $file){
		$lines = explode("\n", $file);
		$data = array();
		foreach ($lines as $line) {
			$data[] = str_getcsv($line);
		}
		$this->csv[$name] = $data;
	}
	private function statusCodeFromText($text){
		$onService = strpos($text, 'On') !== false;
		if($onService) return 0;
		else return 1;
	}
	private function parseKitchen(){
		$this->data['kitchen'] = array();
		$rows = $this->csv['kitchen'];
		foreach($rows as $rowIndex => $row){
			if($rowIndex == 0 || empty($row[1])) continue; // must have field 1 (Name)
			$this->data['kitchen'][] = array(
				'organisation_id' => $row[0],
				'name' => $row[1],
				'organisation' => $row[2],
				'address' => $row[3],
				'postcode' => $row[4],
				'capacity' => $row[5]
			);
		}
	}
	private function parseServiceUser(){
		$this->data['service_user'] = array();
		$this->data['service_user_meal'] = array();
		$rows = $this->csv['service_user'];
		foreach($rows as $rowIndex => $row){
			if($rowIndex == 0 || empty($row[5])) continue; // must have field 5 (Booking ID)
			if(empty($row[11])) $row[11] = ''; // kitchen comments are often empty
			if(!empty($row[0])){
				$this->data['service_user'][] = array(
					'name' => $row[0],
					'status' => $this->statusCodeFromText($row[1]),
					'postcode' => $row[2],
					'booking_id' => $row[5],
					'birthdate' => date('Y-m-d', strtotime($row[9]))
				);
			}
			$this->data['service_user_meal'][] = array(
				'service_user' => count($this->data['service_user']),
				'meal_type' => $row[4],
				'comments' => $row[6],
				'kitchen_comments' => $row[11]
			);
		}
	}
	private function parseVolunteer(){
		$this->data['volunteer'] = array();
		$rows = $this->csv['volunteer'];
		foreach($rows as $rowIndex => $row){
			if($rowIndex == 0 || empty($row[0])) continue; // must have field 0 (Name)
			$newVolunteer = array(
				'name' => $row[0],
				'kitchen' => $row[1],
				'mobile_phone' => $row[2]
			);
			for($i = 3; $i <= 5; $i++){
				if($row[$i] == '1'){
					$newVolunteer['role'] = $rows[0][$i];
				}
			}
			$this->data['volunteer'][] = $newVolunteer;
		}
	}
}
