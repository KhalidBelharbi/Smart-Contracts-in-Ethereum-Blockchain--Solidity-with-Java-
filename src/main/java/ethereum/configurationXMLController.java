package ethereum;

import java.io.IOException;
import java.math.BigInteger;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.protocol.core.methods.response.EthGasPrice;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class configurationXMLController {

	
	
	
public  void initialisation(Web3ClientVersion clientVersion,EthBlockNumber blockNumber,EthGasPrice gasPrice) {
		
		clientVersionField.setText(clientVersion.getWeb3ClientVersion());
		BlocknumberField.setText(blockNumber.getBlockNumber()+"");
		gaspriceField.setText(gasPrice.getGasPrice()+"");		
	}
	
	
	
	
	
	/*
	
	public configurationXMLController() {
		
		initialisation(connectionXMLController.clientVersion,connectionXMLController.blockNumber,connectionXMLController.gasPrice);
	}
	*/
	


public static String SC_adress;

	public static Credentials credentials ;
	public  String SmartContract_adress;
	
	@FXML
	public TextField PrivKeyField;
	
	@FXML
	public TextField clientVersionField;
	
	@FXML
	public TextField gaspriceField;
	@FXML
	public TextField BlocknumberField;
	
	
	@FXML
	public TextField PublicAdressField,newPkField,newScField;
	
	public static Stage finalStage;	  
	  
	  @FXML
	  public Label sc_label,pk_label;
	  
	  @FXML
	  public  TextField GasPriceField_, GasLimitField_,ScField ; 

	
	//@FXML
	//public Label aField,bField;
	
	
	
	
	public void GenerationOfPublicKey() {
		if(!PrivKeyField.getText().isEmpty()) {
			try {
				credentials = Credentials.create(PrivKeyField.getText());
				PublicAdressField.setText(credentials.getAddress());
				pk_label.setTextFill(Color.web("green"));
				pk_label.setText("Correct Private Key");

			//	ECCxLabel.setText("Public Key:");
		//		ECCyLabel.setText("Private Key:");
		//		ECCxFieldX.setText(credentials.getEcKeyPair().getPublicKey()+"");
		//		ECCyLabelY.setText(credentials.getEcKeyPair().getPrivateKey()+"");				
			}catch(Exception e) {				
				pk_label.setText("Incorrect Private Key!");
				pk_label.setTextFill(Color.web("red"));				
			}			
		}		
	}
	
	
public static void initialiseSC_PK(String sm, Credentials pk) {
	configurationXMLController.credentials = pk ; 
	configurationXMLController.SC_adress = sm ;
	
}
	  
	  
	  
	  
	public  void SaveConfiguration() {
		
		finalStage = new Stage();
		
		try {
			
			
			SmartContract_adress = ScField.getText();
			
			
			if(SmartContract_adress.isEmpty()) {
				
				sc_label.setText("Enter SC adresse");
			}else {
				if(PrivKeyField.getText().isEmpty()) {
					pk_label.setText("Enter PK value");
					
				}else {
					connectionXMLController.GAS_LIMIT = new BigInteger(GasLimitField_.getText());
					connectionXMLController.GAS_PRICE = new BigInteger(GasPriceField_.getText());
					initialiseSC_PK(SmartContract_adress, credentials);

					FXMLLoader loader_ = new FXMLLoader(this.getClass().getResource("interfaceXML.fxml"));		
					
					
					Parent root = (Parent) loader_.load();
					Scene scene = new Scene(root);
					//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					finalStage.setScene(scene);
					finalStage.setTitle("Protect your document");		
					finalStage.setResizable(false);
					
					
					
					
					
					interfaceXMLController ll = loader_.getController();
					
					ll.init();
					
					//labelState.setVisible(false);
					connectionXMLController.ConfigStage.hide();			
					finalStage.show();
					
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
				//	System.out.println(configurationXMLController.credentials.getAddress());
				//	System.out.println(configurationXMLController.SC_adress);
				//	
					
					
					
					
					
				}
				
			}
			
					
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	

	public static Credentials LoadAccount(String private_key) {
		
		return Credentials.create(private_key);
		
		
		
		
	}
	
	
	

}
