<?php

$db = new PDO("mysql:host=localhost;dbname=livreursdb","root","");
$results["error"] = false;
$results["message"] = [];

$_POST['nomUser']="faysal";
$_POST['nom']="faysal";
$_POST['prenom']="faysal";
$_POST['adresse']="faysal";
$_POST['motDePasse']="faysal";
 $_POST['motDePasse1']="faysal";

if (isset($_POST)) {
	if(!empty($_POST['nomUser']) && !empty($_POST['nom']) && !empty($_POST['prenom']) && !empty($_POST['adresse']) && !empty($_POST['motDePasse']) && !empty($_POST['motDePasse1'])){
		$nomUser = $_POST['nomUser'];
		$nom = $_POST['nom'];
		$prenom = $_POST['prenom'];
		$adresse = $_POST['adresse'];
		$motDePasse = $_POST['motDePasse'];
		$motDePasse1 = $_POST['motDePasse1'];

		//on verifie le pseudo 

		if(strlen($nomUser) < 2 || !preg_match("/^[a-zA-Z0-9 _-]+$/", $nomUser) || strlen($nomUser) > 60){
			$results["error"] = true;
		    $results['message']["username"] = "nomUser invalide";
		}else{
			//verifie que le pseido n existe pas 
			$requete = $db->prepare("select codeLivreur from livreur where nomUser=:nomUser");
			$requete->execute([':nomUser' => $nomUser]);
			$row = $requete->fetch();
			if($row){
				$results["error"] = true;
		        $results["message"]['nomUser'] = "Le nomUser est deja pris";
			}
		}

		//verficiation de nom
		if(strlen($nom) < 2 || !preg_match("/^[a-zA-Z0-9 _-]+$/", $nom) || strlen($nom) > 60){
			$results["error"] = true;
		    $results['message']["nom"] = "nom invalide";
		}

		//verficiation de prenom
		if(strlen($prenom) < 2 || !preg_match("/^[a-zA-Z0-9 _-]+$/", $prenom) || strlen($prenom) > 60){
			$results["error"] = true;
		    $results['message']["prenom"] = "prenom invalide";
		}


		//verficiation de adresse
		if(strlen($adresse) < 2 || !preg_match("/^[a-zA-Z0-9 _-]+$/", $adresse) || strlen($adresse) > 60){
			$results["error"] = true;
		    $results['message']["adresse"] = "adresse invalide";
		}

		if($motDePasse !== $motDePasse1){
             $results["error"] = true;
		    $results["message"] = "Les mots de passe doivent etre identiques ";
		}


        if($results["error"] === false){
        	$motDePasse = password_hash($motDePasse, PASSWORD_BCRYPT);

        	//insertion 
        	$sql = $db->prepare("Insert into livreur(nom,prenom,adresse,nomUser,motDePasse,dateDernierAcces) values (:nom,:prenom,:adresse,:nomUser,:motDePasse,NOW() )");

        	$sql->execute([":nom"=>$nom,":prenom"=>$prenom,":adresse"=>$adresse,":nomUser"=>$nomUser,":motDePasse"=>$motDePasse]);

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