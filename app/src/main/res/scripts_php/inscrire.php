<?php

$db = new PDO("mysql:host=localhost;dbname=livreursdb","root","");
$results["error"] = false;
$results["message"] = [];

if (isset($_POST)) {
	if(!empty($_POST['username']) && !empty($_POST['email']) && !empty($_POST['password']) && !empty($_POST['password2'])){
		$username = $_POST['username'];
		$email = $_POST['email'];
		$password = $_POST['password'];
		$password2 = $_POST['password2'];


		//on verifie le pseudo 

		if(strlen($username) < 2 || !preg_match("/^[a-zA-Z0-9 _-]+$/", $username) || strlen($username) > 60){
			$results["error"] = true;
		    $results['message']["username"] = "username invalide";
		}else{
			//verifie que le pseido n existe pas 
			$requete = $db->prepare("select id from admins where username=:username");
			$requete->execute([':username' => $username]);
			$row = $requete->fetch();
			if($row){
				$results["error"] = true;
		        $results["message"]['username'] = "Le username est deja pris";
			}
		}

		//verficiation de l email
		if(!filter_var($email, FILTER_VALIDATE_EMAIL)){
			$results["error"] = true;
		    $results["message"]['email']= "Email Invalide";
		}else{
			//verifie que l emaiil n existe pas 
			$requete = $db->prepare("select id from admins where email=:email");
			$requete->execute([":email"=>$email]);
			$row = $requete->fetch();
			if($row){
				$results["error"] = true;
		        $results["message"]['email'] = "L'email est deja pris";
			}
		}

		if($password !== $password2){
             $results["error"] = true;
		    $results["message"] = "Les mots de passe doivent etre identiques ";
		}


        if($results["error"] === false){
        	$password = password_hash($password, PASSWORD_BCRYPT);

        	//insertion
        	$sql = $db->prepare("Insert into admins(username,email,password) values (:username,:email,:password)");

        	$sql->execute([":username"=>$username,":email"=>$email,":password"=>$password]);

        	if(!$sql){
        		$results["error"] = true;
		        $results["message"] = "erreur lors de l insertion";
        	}
        }
	}else{
		$results["error"] = true;
		$results["message"] = "Veuillez remplir tous les champs ";
	}

	echo json_encode($results);
}

?>