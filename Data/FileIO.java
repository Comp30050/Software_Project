package Data;
import java.io.*;

public class FileIO {

	/*Stores a specified tsv file in a buffered reader
	 * @param filename: specifies target file
	 * @return buffered reader of file  */
	public static BufferedReader bufferFile( String filename){
		BufferedReader buffer = null;
		try {
			buffer = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}			
		return buffer;	
	}
	
	/*@param buffer: the targeted BufferedReader
	 * @return the next line of buffer as a string */
	public static String nextLine(BufferedReader buffer){
		String line = null;
		try {
			line = buffer.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return line;
	}
}