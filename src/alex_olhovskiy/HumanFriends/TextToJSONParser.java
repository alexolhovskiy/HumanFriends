package alex_olhovskiy.HumanFriends;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

public class TextToJSONParser {
	
	public JSONArray textParser(String myTxt) {
		JSONArray ja=new JSONArray();
	
		int count=1;

		try(BufferedReader br= new BufferedReader(new FileReader(myTxt))) {
			String str;
			while((str=br.readLine())!=null){
				JSONObject jo = new JSONObject();
				String[]arr=str.split("\t");
				
				jo.put("ID",count++);
				jo.put("Name", arr[1].trim());
				jo.put("Type", arr[2].trim());
				jo.put("BirthDate", arr[3].trim());
				jo.put("Commands", arr[4].trim().split(","));
				
				ja.put(jo);
			}
			br.close();
		}catch(IOException ex){
			System.out.println(ex.getMessage());
		}
		
		System.out.println(ja);
		return ja;
	}
	
	public void writeToFile(String name,JSONArray ja) {
		try(FileWriter fw=new FileWriter(name,false))
		{
			fw.write(ja.toString());
			fw.flush();
		}catch(IOException ex){
			System.out.println(ex.getMessage());
		}
	}
}
