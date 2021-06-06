package ethereum;

public class Docs {

	
	private String path;
	private int SizeOfBlock; 
	
	
	
	
	private String CryptoAlgorithm;
	public static final String K = "" ; 
	
	
	
	
	
	
	
	
	
	  public static byte[] hexStringToByteArray(String s) {
		    int len = s.length();
		    byte[] data = new byte[len / 2];
		    for (int i = 0; i < len; i += 2) {
		        data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
		                             + Character.digit(s.charAt(i+1), 16));
		    }
		    return data;
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
