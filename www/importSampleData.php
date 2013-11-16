<?php

require_once('classes/TFCParser.php');
require_once('classes/TFCDBPopulator.php');
require_once('includes/database.php');

$sampleDataPath = 'data/sampledata/';
$fileKitchen = file_get_contents($sampleDataPath . 'kitchen.csv');
$fileServiceUser = file_get_contents($sampleDataPath . 'serviceUser.csv');
$fileVolunteer = file_get_contents($sampleDataPath . 'volunteer.csv');

$parser = new TFCParser($fileKitchen, $fileServiceUser, $fileVolunteer);
$parser->parse();
$populator = new TFCDBPopulator(getPDOHandle(), $parser->data());
$populator->populate();
