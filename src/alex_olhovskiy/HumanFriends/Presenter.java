package alex_olhovskiy.HumanFriends;

import java.util.ArrayList;
import java.util.List;

public class Presenter {
	
	private Model model;
	private View view;
	
	public Presenter() {
		this.model=new Model();
		this.view=new View(this);
		view.menu();
	}
	
	public void addAnimal(String type,String name,String date) {
		model.createAnimal(type, name, date);
	}
	public void changeStatus(int id,boolean status) {
		model.editStatus(id, status);
	}
	public void addCommand(int id,String command) {
		model.addCommand(id,command);
	}
	public Animal getAnimal(int id) {
		return model.getAnimal(id);
	}
	public void reName(int id,String name) {
		model.editName(id,name);
	}
	public void reDate(int id,String date) {
		model.editDate(id,date);
	}
	public void reCommands(int id,String commands) {
		model.editCommands(id,commands);
	}
	
	public List<Animal> sortById() {
		return model.sortById();
	}
	
	public List<Animal> sortByBDate() {
		return model.sortByBDate();
	}
	
	public String search(String str) {
		model.search(str);
		return model.print();
	}
	
	public String print(){
		model.getAll();
		return model.print();
	}
	
	public void load() {
		model.loadFromFile("BD.json");
	}
	
	public void save() {
		model.save();
	}
	
	public int count() {
		return model.count();
	}
}
