package ethereum;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.util.ResourceBundle;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.utils.Convert;
import org.web3j.utils.Convert.Unit;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DoTransactionController implements Initializable {

	public String SmartContract;
	public Credentials cred;
	public Abi copyrightContract;
	public String hexValue;
	
	public Web3j web3;
	
	
	
	
	@FXML
	public TextArea txtFFF;
	
	// add file
	public void SendTransaction(ActionEvent e) {
		try {
			BigInteger w = new BigInteger(bFF.getText());		
			TransactionReceipt recept = 	copyrightContract.AddFile(interfaceXMLController.hexStringToByteArray(hexValue), w).send();
			
			
			if(!recept.toString().isEmpty())
			txtFFF.setText(recept.toString());
			else txtFFF.setText("Not excuted!");
			
		
		}catch(Exception eee) {
		}
	
		
	}




	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	public TextField aF,cF;
	
	
	@FXML
	public Label ss;
	
	public void initialisation(Credentials c, String smart,Abi a,String hexValuee,Web3j web3) {
		
		this.cred = c ; 
		this.SmartContract = smart ; 
		this.copyrightContract = a;
		this.hexValue = hexValuee ; 
		
		
		aF.setText(this.cred.getAddress());
		cF.setText(SmartContract);
		
		
		EthGetBalance balanceWei;
		try {
			balanceWei = web3.ethGetBalance( cred.getAddress(),DefaultBlockParameterName.LATEST).send();
		
		BigDecimal balanceInEther = Convert.fromWei(balanceWei.getBalance().toString(), Unit.ETHER);
		ss.setText("Solde="+balanceInEther+" Eth");
		aF.setText(cred.getAddress());
		bFF.setText("5000000000000000000");
		cF.setText(copyrightContract.getContractAddress());
		
		} catch (IOException e) {
	
		}
		
		
		
		
		
	}
	
	
	@FXML
	public Label eth_label;
	@FXML 
	public TextField bFF;
	
	
	@FXML
	public void echange22(ActionEvent e) {
		
try {
	
//	eth_label.setText("="+Convert.fromWei(bFF.getText(), Unit.ETHER)+" Eth"); 
	
	System.out.println(bFF.getText());
	System.out.println(eth_label.getText());
	
}catch(Exception ee ) {
	
}
		
		
		
	}
	
	
	
}
