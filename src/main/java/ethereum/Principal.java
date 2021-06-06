package ethereum;

import java.math.BigInteger;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

public class Principal {

	
	  private  BigInteger GAS_LIMIT = BigInteger.valueOf(6721975L);
	  private  BigInteger GAS_PRICE = BigInteger.valueOf(20000000000L);
	
	  private int portProvider = 8545 ; 
	  private Web3j web3 ; 
	
	
	
	
	
	
	
	public BigInteger getGAS_LIMIT() {
		return GAS_LIMIT;
	}


	public void setGAS_LIMIT(BigInteger gAS_LIMIT) {
		GAS_LIMIT = gAS_LIMIT;
	}


	public BigInteger getGAS_PRICE() {
		return GAS_PRICE;
	}


	public void setGAS_PRICE(BigInteger gAS_PRICE) {
		GAS_PRICE = gAS_PRICE;
	}


	public int getPortProvider() {
		return portProvider;
	}


	public void setPortProvider(int portProvider) {
		this.portProvider = portProvider;
	}


	public Web3j getWeb3() {
		return web3;
	}


	public void setWeb3(Web3j web3) {
		this.web3 = web3;
	}


	public static char[] getHexArray() {
		return HEX_ARRAY;
	}


	public void getConnection() {
		
		 this.web3 = Web3j.build(new HttpService("http://localhost:"+this.getPortProvider()));
		 
		
	}
	
	
	public static byte[] hexStringToByteArray(String s) {
        byte[] b = new byte[s.length() / 2];
        for (int i = 0; i < b.length; i++) {
          int index = i * 2;
          int v = Integer.parseInt(s.substring(index, index + 2), 16);
          b[i] = (byte) v;
        }
        return b;
      }
   
    
    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }
    
	
	
	
	
	
	
	
	
	
	
}
