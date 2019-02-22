<?php
	//error_reporting(0);
	//ini_set('display_errors', 0);

	if (isset($_POST['name'])) {
		$name = trim($_POST['name']);
		$email = trim($_POST['email']);

		echo "Hello " . $name . "\n";
		echo "Your email address is " . $email;
	}
?>