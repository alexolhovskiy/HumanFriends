package alex_olhovskiy.HumanFriends;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.json.JSONArray;
import org.json.JSONObject;

public class Model {
	private ArrayList<Animal>animals;
	private ArrayList<Animal>result;
	
	public Model() {
		animals=new ArrayList<>();
		result=new ArrayList<>();
	}
	
	public void loadFromFile(String file) {
		StringBuffer sb=new StringBuffer();
		try(BufferedReader br=new BufferedReader(new FileReader(file))){
			String str="";
			while((str=br.readLine())!=null) {
				sb.append(str);
			}
		}catch(IOException ex){
			System.out.println(ex.getMessage());
		}
		
		JSONArray ja=new JSONArray(sb.toString());
		
		for(int i=0;i<ja.length();i++) {
			JSONObject jo=ja.getJSONObject(i);
//			JSONArray jSubArr=jo.getJSONArray("Commands");
//			String[]arr=new String[jSubArr.length()];
//			for(int j=0;j<arr.length;j++) {
//				arr[j]=jSubArr.getString(j);
//				//System.out.println(arr[j]);
//			}
			String[]arr=jo.getString("Commands").split(",");
			//System.out.println(Arrays.toString(arr));
			
			loadAnimal(jo.getInt("ID"),jo.getString("Type"),jo.getString("Name"),jo.getString("BirthDate"),arr,jo.getInt("Status"));
			
			//animals.add(new Animal(jo.getString("Name"),jo.getString("BirthDate"),arr));
		}
	}
	
	public void loadAnimal(int id,String type,String name,String date,String[]arr,int status) {
			switch(type) {
			case "Dog":animals.add(new Dog(id,name,date,arr,status));break;
			case "Cat":animals.add(new Cat(id,name,date,arr,status));break;
			case "Hamster":animals.add(new Hamster(id,name,date,arr,status));break;
			case "Horse":animals.add(new Horse(id,name,date,arr,status));break;
			case "Camel":animals.add(new Camel(id,name,date,arr,status));break;
			case "Donkey":animals.add(new Donkey(id,name,date,arr,status));break;
		}
	}
	
	public void createAnimal(String type,String name,String date) {
			switch(type) {
			case "Dog":animals.add(new Dog(name,date));break;
			case "Cat":animals.add(new Cat(name,date));break;
			case "Hamster":animals.add(new Hamster(name,date));break;
			case "Horse":animals.add(new Horse(name,date));break;
			case "Camel":animals.add(new Camel(name,date));break;
			case "Donkey":animals.add(new Donkey(name,date));break;
		}
	}
	
	public Animal getAnimal(int id) {
		return animals.get(id-1);
	}
	
	public void editName(int id,String name) {
		animals.get(id-1).setName(name);
	}
	
	public void editDate(int id,String date) {
		try {
			animals.get(id-1).setbDate(animals.get(id).getFormatter().parse(date));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void editCommands(int id,String commands) {
		ArrayList<Commands>newCommands=new ArrayList<>();
		animals.get(id-1).setCommands((ArrayList<Commands>) Arrays.stream(commands.split(",")).map(e->Commands.valueOf(e)).collect(Collectors.toList()));
	}
	
	public void editStatus(int id,boolean status) {
		animals.get(id-1).setStatus(status);
	}
	
	public void addCommand(int id,String command) {
		ArrayList<Commands>temp=animals.get(id-1).getCommands();
		temp.add(Commands.valueOf(command));
		animals.get(id-1).setCommands(temp);
	}
	
	public List<Animal> sortById() {
		return result.stream().sorted((e1,e2)->Integer.compare(e1.getId(),e2.getId())).collect(Collectors.toList());
	}
	
	public List<Animal> sortByBDate() {
		return result.stream().
				sorted((e1,e2)->{
					if(e1.getbDate().equals(e2.getbDate())) {
						return 0;
					}else{
						return e1.getbDate().before(e2.getbDate())?-1:1;
					}
				}).collect(Collectors.toList());
	}
	
	public void search(String str) {
		//ArrayList<Animal>temp=new ArrayList<>();
		result.clear();
		for(Animal animal:animals) {
			if(animal.toString().toLowerCase().indexOf(str.toLowerCase())>0) {
				result.add(animal);
			}
		}
		//return temp;
	}
	
	public String print() {
		StringBuilder sb=new StringBuilder();
		for(Animal animal:result) {
			sb.append(animal);
		}
		return sb.toString();
	}
	
	public void getAll() {
		result.clear();
		for(Animal animal:animals) {
			result.add(animal);
		}
	}
	
	public void save() {
		JSONArray ja=new JSONArray();
		for(Animal animal:animals) {
			JSONObject jo=new JSONObject();
			jo.put("ID", animal.getId());
			jo.put("Name", animal.getName());
			jo.put("Type", animal.getClass().getSimpleName());
			jo.put("BirthDate", animal.getFormatter().format(animal.getbDate()));
			jo.put("Status",animal.isStatus()?1:0);
			StringBuilder sb=new StringBuilder();
			for(int i=0;i<animal.getCommands().size();i++) {
				sb.append(animal.getCommands().get(i));
				if(i<animal.getCommands().size()-1) {
					sb.append(",");
				}
			}
			jo.put("Commands", sb.toString());
			ja.put(jo);
		}
		
		try(FileWriter fw=new FileWriter("BD.json",false)){
			fw.write(ja.toString());
			fw.flush();
		}catch(IOException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	
	public int count() {
		return result.size();
	}
	
	
}
