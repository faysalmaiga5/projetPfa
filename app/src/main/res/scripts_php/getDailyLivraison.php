<?php
require_once './Connection.php';

if(isset($_POST['code']) && !empty($_POST['code']) ){
	$code = $_POST['code'];
	getDailyLivraisons($code);
}else{
	echo "failed-CodeLivreur";
}

function getDailyLivraisons($code){
		$database = new Connection();
		//open Connection
		$db = $database->open();
		try{

			$query="SELECT `codeLivraison`, `dateLivraison`, `etat`, `Description`, `codeLivreur`, `codeCommande` FROM `livraison` WHERE codeLivreur = :code ";
			$reponse=$db->prepare($query);
			$reponse->bindValue('code',$code);
			$reponse->execute();
			$data = array();
			$today = date("Y-m-d");
			if($reponse->rowCount() == 1) {
			  	while($row=$reponse->fetch(PDO::FETCH_ASSOC)){
			       	if ($today == $row['dateLivraison']) {
			        	$data['dateLivraison']= $row['dateLivraison'];
			        	$data['etat']= $row['etat'];
			        	$data['Description']= $row['Description'];
			        	$data['codeCommande']= $row['codeCommande'];
			        }
				}
			}
			echo json_encode($data);
		}
		catch(PDOException $e){
			echo $e->getMessage();
		}

		//close connection
		$database->close();
}
?>