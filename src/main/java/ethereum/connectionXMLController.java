package ethereum;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;
import org.web3j.utils.Convert.Unit;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.protocol.core.methods.response.EthGasPrice;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;

public class connectionXMLController {

	
	
	@FXML
	public Label stateLabel;
	
	@FXML 
	public TextField f_,GasLimitField_,GasPriceField_;	
    public static Web3j web3 ;
  //  public static Web3ClientVersion clientVersion;
  //  public static EthBlockNumber blockNumber;    
  //  public static  EthGasPrice gasPrice ;
    public static Stage ConfigStage;     
    public static   BigInteger GAS_LIMIT = BigInteger.valueOf(6721975L); // default values
	public static  BigInteger GAS_PRICE = BigInteger.valueOf(20000000000L);
    
	public void getConnection() {


		String str =new String();
		str = f_.getText();
		
		
		if(str.equalsIgnoreCase("HTTP://127.0.0.1:8545") || str.equalsIgnoreCase("HTTP://localhost:8545")) {
			if(!f_.getText().isEmpty()) {
				 try {
						web3 = Web3j.build(new HttpService(str));						
					//	  clientVersion = web3.web3ClientVersion().send();
				   //      blockNumber = web3.ethBlockNumber().send();
				  //       gasPrice = web3.ethGasPrice().send();         
				         ConfigStage = new Stage();		         
				         System.out.println(web3.toString());			         
				         Parent root = FXMLLoader.load(this.getClass().getResource("configurationXML.fxml"));
							Scene scene = new Scene(root);
							//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
							ConfigStage.setScene(scene);
							ConfigStage.setTitle("Protect your document");		
							ConfigStage.setResizable(false);
							//labelState.setVisible(false);
							Main.connectStage.hide();
							ConfigStage.show();						
							GasLimitField_.setText(GAS_LIMIT+"");						
							GasPriceField_.setText(GAS_PRICE+"");						
						} catch (Exception e) {
							// TODO Auto-generated catch block
							stateLabel.setText("Please check the adresse of your web3 provider");
						}		
			}
			
		}else {			
			stateLabel.setText("Please enter the url of your web3 provider");
    	}
		
		
		
	}
	
	
	@FXML
	public TextField clientVersionField;
	
	@FXML
	public TextField gaspriceField;
	@FXML
	public TextField BlocknumberField;
	
public  void initialisation(Web3ClientVersion clientVersion,EthBlockNumber blockNumber,EthGasPrice gasPrice) {
		
		clientVersionField.setText(clientVersion.getWeb3ClientVersion());
		BlocknumberField.setText(blockNumber.getBlockNumber()+"");
		gaspriceField.setText(gasPrice.getGasPrice()+"");		
		
		
		System.out.println(clientVersion.getWeb3ClientVersion());
		System.out.println(blockNumber.getBlockNumber());
		System.out.println(gasPrice.getGasPrice());
		
		
		
		
		
	}
	
	
	
	
	
	
}
