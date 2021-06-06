package ethereum;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple4;
import org.web3j.utils.Convert;
import org.web3j.utils.Convert.Unit;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

import java.awt.FileDialog;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.Vector;


public class interfaceXMLController implements Initializable {

	
	  public static Web3j web3 = connectionXMLController.web3 ;
	  public static   BigInteger GAS_LIMIT = connectionXMLController.GAS_LIMIT; 
	  public static  BigInteger GAS_PRICE = connectionXMLController.GAS_PRICE;
	  public static Credentials credentials = configurationXMLController.credentials;
	  public  static String SmartContract =  configurationXMLController.SC_adress; 
	
	
	  
		@Override
		public void initialize(URL location, ResourceBundle resources) {
			// TODO Auto-generated method stub
			
		}
		
		
		public void init() {
			
			newPkField.setText(credentials.getEcKeyPair().getPrivateKey()+"");
			newScField.setText(SmartContract);
		}
	
	  
	  
	
	  @FXML
		public TextField newPkField,newScField,pathField,signField,timestampField;
		
	
	
	public void getNewPrivateKey() {
		
		credentials = configurationXMLController.LoadAccount(newPkField.getText()); 
		
		
	}
	
	public void getNewSmartContract() {
		
		SmartContract = newScField.getText();
		
	}
	
	
	
	
	// add document *********************************
	
	
	
	
	
	
	
	public String getTextFromPdf(String path) {
		String text = null;
		try {
		//Loading an existing document
	      File file = new File(path);
	      PDDocument document = PDDocument.load(file);

	      //Instantiate PDFTextStripper class
	      PDFTextStripper pdfStripper = new PDFTextStripper();

	      //Retrieving text from PDF document
	      text = pdfStripper.getText(document);
	      System.out.println(text);

	      //Closing the document
	      
			document.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      
	      return text ; 
		
	}
	
	private static String bytesToHex(byte[] hash) {
	    StringBuffer hexString = new StringBuffer();
	    for (int i = 0; i < hash.length; i++) {
	    String hex = Integer.toHexString(0xff & hash[i]);
	    if(hex.length() == 1) hexString.append('0');
	        hexString.append(hex);
	    }
	    return hexString.toString();
	}
	
	
	
	public static String getHash(String textes) {
		MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] encodedhash = digest.digest(textes.getBytes(StandardCharsets.UTF_8));
		return bytesToHex(encodedhash);
	}
	
	
	
	
	
	
	public static String hexValue = new String() ;
	public void importPdf(ActionEvent e) {        
	    FileDialog f =     new java.awt.FileDialog((java.awt.Frame) null);
	    f.setVisible(true);   
	    f.setTitle("Specify your PDF file");   	    
	      String str; 	    
	    if( f.getDirectory() != null && f.getFile() != null ) {
	        String selFile = f.getFile();
	        File dir = new File( f.getDirectory() );
	        File fich =  new File( dir, selFile );        
	        pathField.setText(fich.getAbsolutePath());
	        str = this.getTextFromPdf(fich.getAbsolutePath());
	    
	        
	        String hexHash = getHash(str);
	        
	        hexValue = hexHash;
	       // signField.setText(hexHash);
	        
	    }			
	}
	
	
/*
    public byte[] concatBytes(Vector<byte[]>  stream) {
         byte[] result =null;
       if(stream.size() > 1){
        
         result = stream.get(0);      
            
       for(int i=1;i<stream.size() ; i++)            
          Bytes.concat(result,stream.get(i));
            
        
            
        }
       
        return result;
        
    }
    
    
    
    
    public String concatVectString(Vector<String> p){
        StringBuilder s = new StringBuilder();
            for(String x : p)
                s.append(x);
        
        return new String(s);
    }
	
	
	
	*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void signPdf(ActionEvent e) {
		
		signField.setText(hexValue);		
		
	}
	
	
	@FXML
	public Label ss;
	
	@FXML
	public TextField aF,bF,cF;
	
	public static Stage stageAddFile ; // interface de Send file 
	
	public static Abi copyrightContract;
	// add to BC
	public void getSignature(ActionEvent e) {
		
		
		 copyrightContract = Abi.load(SmartContract, web3, credentials,GAS_PRICE,GAS_LIMIT);
		
		
		 stageAddFile = new Stage();
		
		  Parent root;
		try {
			
			
			FXMLLoader loaderN = new FXMLLoader(this.getClass().getResource("DoTransaction.fxml"));
			
			
			
			
			root = (Parent) loaderN.load();
		
			
			
			
			
			Scene scene = new Scene(root);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stageAddFile.setScene(scene);
			stageAddFile.setTitle("Protect your document");		
			stageAddFile.setResizable(false);	
			
			DoTransactionController contr = loaderN.getController() ;
			contr.initialisation(credentials, SmartContract,copyrightContract, hexValue,web3);
		
			
			
			stageAddFile.show();
			
			
		
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
	
	@FXML
	public TextField dataFormatField;
	
	public void completeIt(boolean bb) {
		if (bb) {
			try {
			RemoteCall<Tuple4<Boolean, Boolean, BigInteger, String>> vect = copyrightContract.getStateOfDoc(hexStringToByteArray(hexValue));
			
			Tuple4<Boolean, Boolean, BigInteger, String> v;
			
				v = vect.send();
		
			
			
			timestampField.setText(v.getValue3()+"");
			
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
	}
	
	
	@FXML
	public Label resultatF ; 
	// add file
	public void SendTransaction(ActionEvent e) {
		try {
			BigInteger w = new BigInteger(bF.getText());		
			TransactionReceipt recept = 	copyrightContract.AddFile(hexStringToByteArray(hexValue), w).send();
		
			txtFFF.setText(recept.toString());
		
		
		
		}catch(Exception eee) {
		}
	
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@FXML
	
	public TextField signDeleteField;
	
	@FXML
	public TextArea txtArea,txtFFF,rrF;
	
	// delete document *********************************

	
	public void deletDoc() {
		
		if(!signDeleteField.getText().isEmpty()) {
			
			try {
				TransactionReceipt recept = 	copyrightContract.deleteDoc(hexStringToByteArray(signDeleteField.getText())).send();
			
				txtArea.setText(recept.toString());
			
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		
	}
	
	
	
	
	
	
	
	
	// verify document *********************************

	
	@FXML
	public TextField  hashToVerifiedField,ownerField;
	
	public void verifyForMyAdress() {
		
		
		Tuple4<Boolean, Boolean, BigInteger, String> recept = null ;
		
			try {
			 recept = copyrightContract.getStateOfDoc(hexStringToByteArray(hashToVerifiedField.getText())).send();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	if(recept != null) {
		StringBuilder stt = new StringBuilder();
		if(ownerField.getText().isEmpty()) { // for my adress
			
			
			if(credentials.getAddress().equals(recept.getValue4())){				
				
				stt.append("Verified --- The adress of the owner = the adresse of the sender \n");
				stt.append("The owner = "+ recept.getValue4()+" \n");
				if(recept.getValue1()==true) 				stt.append("State= Validated \ntimestamp of validation "+recept.getValue3());
				else stt.append("State= Deleted \ntimestamp of deletion "+recept.getValue3());				
			}else {
				stt.append("NOT TRUE --- The adress of the owner != the adresse of the sender \n");
				stt.append("The owner = "+ recept.getValue4()+" \n");
				if(recept.getValue1()==true) 				stt.append("State= Validated \ntimestamp of validation "+recept.getValue3());
				else stt.append("State= Deleted \ntimestamp of deletion "+recept.getValue3());					
			}
		
		}else {  // for other adresse
		
			if(ownerField.getText().equals(recept.getValue4())){				
				stt.append("Verified --- The adress of the owner = the adresse of this user "+ownerField.getText()+" \n");
				stt.append("The owner = "+ recept.getValue4()+" \n");
				if(recept.getValue1()==true) 				stt.append("State= Validated \ntimestamp of validation "+recept.getValue3());
				else stt.append("State= Deleted \ntimestamp of deletion "+recept.getValue3());				
			}else {
				stt.append("NOT TRUE --- The adress of the owner != the adresse of this user "+ownerField.getText()+" \n");
				stt.append("The owner = "+ recept.getValue4()+" \n");
				if(recept.getValue1()==true) 				stt.append("State= Validated \ntimestamp of validation "+recept.getValue3());
				else stt.append("State= Deleted \ntimestamp of deletion "+recept.getValue3());					
			}
		
	    }
		
		
		rrF.setText(new String(stt));		
	}
			
			
		
		
		

	}
	
	
	
	
	
	
	
	
	
	
	
	
	public static byte[] hexStringToByteArray(String s) {
	    int len = s.length();
	    byte[] data = new byte[len / 2];
	    for (int i = 0; i < len; i += 2) {
	        data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
	                             + Character.digit(s.charAt(i+1), 16));
	    }
	    return data;
	}
	
	
	
	public Date getDatefromTS(Timestamp ts) {
		          return new Date(ts.getTime());
	}
	

	public BigDecimal getBalance_Ether() {
		EthGetBalance balanceWei;
		BigDecimal balanceInEther;
		try {
			
			System.out.println("adr = "+credentials.getAddress());
			
			 balanceWei= web3.ethGetBalance( credentials.getAddress(),DefaultBlockParameterName.LATEST).send();
			 
			 System.out.println(balanceWei);
			 
			 balanceInEther = Convert.fromWei(balanceWei.getBalance().toString(), Unit.ETHER);
		
		return balanceInEther;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return null;
	}
	
	
	
	
	
	
	
	
	public void changementAdresse(ActionEvent e) {
		
		credentials = Credentials.create(newPkField.getText());
	}
	
	public void changementSCadress(ActionEvent e) {
		
		SmartContract = newScField.getText();
	}


	
	
	
	
	
	
}
