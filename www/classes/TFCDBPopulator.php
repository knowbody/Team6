<?php

class TFCDBPopulator {
	private $data;
	private $db;
	private $doDrop;
	public function __construct($db, $data, $doDrop = true){
		$this->db = $db;
		$this->data = $data;
		$this->doDrop = $doDrop;
	}
	public function populate(){
		foreach($this->data as $tableName => $rows){
			if($this->doDrop){
				$query = 'TRUNCATE TABLE ' . $tableName;
				$st = $this->db->query($query);
				$st->execute();
			}
			foreach($rows as $rowID => $columns){
				$columnNames = array_keys($columns);
				$columnLabels = array_keys($columns);
				foreach($columnLabels as &$columnLabel) $columnLabel = ':' . $columnLabel;

				$query = 'INSERT INTO ' . $tableName . ' (' . implode(',', $columnNames) . ') VALUES ('
						. implode(',', $columnLabels) . ')';

				$st = $this->db->prepare($query);
				foreach(array_keys($columnNames) as $columnID){
					$st->bindParam($columnLabels[$columnID], $columns[$columnNames[$columnID]]);
				}
				$st->execute();
			}
		}
	}
}
