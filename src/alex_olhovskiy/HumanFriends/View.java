package alex_olhovskiy.HumanFriends;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class View {
	private Scanner input=new Scanner(System.in);
	private Presenter observer;
	
	public View(Presenter observer) {
		this.observer=observer;
	}
	
	public String showMenu() {
		System.out.println("Menu");
		System.out.println("Add new animal                        enter 'a'");
		System.out.println("Add new command                       enter 'c'");
		System.out.println("Show all                              enter 'p'");
		System.out.println("Find by type,name,b-date or command   enter 'f'");
		System.out.println("Sort result by adding position        enter 'sa'");
		System.out.println("Sort result by birth date             enter 'sb'");
		System.out.println("Load list from file                   enter 'l'");
		System.out.println("Save list to file    	              enter 's'");
		System.out.println("Edit everything                       enter 'e'");
		System.out.println("Amount                                enter 'm'");
		System.out.println("Exit                   	              enter 'q'");
		
		return input.next();
	}
	
	public String editMenu(int id) {
		System.out.println(observer.getAnimal(id));
		System.out.println("What do you want to change?");
		System.out.println("Name       -> enter 'n'");
		System.out.println("Status     -> enter 's'");
		System.out.println("Birth date -> enter 'b'");
		System.out.println("Commands   -> enter 'c'");
		
		return input.next();
	}
	
	public String editSubMenu() {
		System.out.println("Enter 'y' -> status='present'");
		System.out.println("Enter 'n' -> status='absent'");
		
		return input.next();
	}
	
	public void menu() {
		String c="",type="",name="",date="",commands="";
		int id=0;
		while(!((c=showMenu()).equals("q"))) {
			switch(c) {
				case "a":
					System.out.println("Add new animal");
					System.out.println("Enter animal type");
					type=input.next();
					System.out.println("Enter animal name");
					name=input.next();
					System.out.println("Enter animal b-date(yyyy-mm-dd)");
					date=input.next();
					observer.addAnimal(type,name,date);
					break;
				case "c":
					System.out.println("Add new command");
					System.out.println("Enter animal id");
					id=input.nextInt();
					System.out.println(observer.getAnimal(id));
					System.out.println("Enter 1 new command");
					observer.addCommand(id,input.next());
					break;
				case "p":
					System.out.println("Show all");
					System.out.println(observer.print());
					break;
				case "f":
					System.out.println("Find by type,status,name,b-date or command");
					System.out.println(observer.search(input.next()));
					break;
				case "sa":
					System.out.println("Sort result by adding position");
					System.out.println(observer.sortById());
					break;
				case "sb":
					System.out.println("Sort result by birth date");
					System.out.println(observer.sortByBDate());
					break;
				case "e":
					System.out.println("Edit");
					System.out.println("Enter id for editing");
					id=input.nextInt();
					while(!((c=editMenu(id)).equals("q"))){
						switch(c) {
							case "n":
								System.out.println("Enter new name");
								observer.reName(id,input.next());
								break;
							case "b":
								System.out.println("Enter new b-date (yyyy-mm-dd)");
								observer.reDate(id,input.next());
								break;
							case "c":
								System.out.println("Enter new commands(s -> command1,command2,...)");
								observer.reCommands(id,input.next());
								break;
							case "s":
								while(!((c=editSubMenu()).equals("q"))){
									switch(c) {
										case "y":
											observer.changeStatus(id,true);
											break;
										case "n":
											observer.changeStatus(id,false);
											break;
									}
								}
								c="";
						}
					}
					c="";
					
					break;
				case "m":
					System.out.println("Amount: "+observer.count());
					break;
				case "l":
					System.out.println("Load list from file");
					observer.load();
					break;
				case "s":
					observer.save();
					System.out.println("Save list to file");
					break;
				//case "q":System.out.println("Exit");break;
			}
		}
	}

}
