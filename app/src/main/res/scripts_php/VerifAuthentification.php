<?php

$db = new PDO("mysql:host=localhost;dbname=livreursdb","root","");
$results["error"] = false;
$results["message"] = [];

if(!empty($_POST)){


	if(!empty($_POST['nomUser'])  && !empty($_POST['motDePasse'])){
		$nomUser = $_POST['nomUser'];
		$motDePasse = $_POST['motDePasse']; 

        $requete = $db->prepare("select * from livreur where nomUser=:nomUser");
        $requete->execute([":nomUser" => $nomUser]);
		$row = $requete->fetch(PDO::FETCH_OBJ);
			if($row){
				if(password_verify($motDePasse, $row->motDePasse)){
					$results["error"] = false;
		            $results["codeLivreur"] = $row->codeLivreur;
		            $results["nomUser"] = $row->nomUser;
				}else{
					$results["error"] = true;
		            $results["message"]= "Le nom d utilisateur ou le mot de passe sont incorrecte";
				}

			}else{
			     	$results["error"] = true;
		            $results["message"] = "Le nom d utilisateur bbb ou le mot de passe sont incorrecte";
			}
		}else{
			        $results["error"] = true;
		            $results["message"] = "Veuillez remplir tous les champs";
		}

	echo json_encode($results);
}

?>