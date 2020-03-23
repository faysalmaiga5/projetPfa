<?php

$db = new PDO("mysql:host=localhost;dbname=livreursdb","root","");
$results["error"] = false;
$results["message"] = [];

if(!empty($_POST)){


	if(!empty($_POST['username'])  && !empty($_POST['password'])){
		$username = $_POST['username'];
		$password = $_POST['password']; 

        $requete = $db->prepare("select * from admins where username=:username");
        $requete->execute([":username" => $username]);
		$row = $requete->fetch(PDO::FETCH_OBJ);
			if($row){
				if(password_verify($password, $row->password)){
					$results["error"] = false;
		            $results["id"] = $row->id;
		            $results["username"] = $row->username;
				}else{
					$results["error"] = true;
		            $results["message"]= "Le nom d utilisateur ou le mot de passe sont incorrecte";
				}

			}else{
			     	$results["error"] = true;
		            $results["message"] = "Le nom d utilisateur ou le mot de passe sont incorrecte";
			}
		}else{
			        $results["error"] = true;
		            $results["message"] = "Veuillez remplir tous les champs";
		}

	echo json_encode($results);
}

?>