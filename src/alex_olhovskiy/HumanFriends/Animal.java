package alex_olhovskiy.HumanFriends;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Animal {
	private static int count;
	static {
		count=1;
	}
	private int id;
	private boolean status;
	private String name;
	private Date bDate;
	private SimpleDateFormat formatter;
	private ArrayList<Commands>commands=new ArrayList<>();
	
	public Animal(int id,String name,String date,String[]commands,int status) {
		this.id=id;
		count=id+1;
		this.name=name;
		formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			bDate = formatter.parse(date);
			//System.out.println(date);
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		for(String command:commands) {
			this.commands.add(Commands.valueOf(command));
		}
		if(status>0) {
			this.status=true;
		}else {
			this.status=false;
		}
		
	}
	
	public Animal(String name,String date) {
		System.out.println("Animal");
		this.id=count;
		this.name=name;
		formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			bDate = formatter.parse(date);
			//System.out.println(date);
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		status=true;
		System.out.println("Done");
	}
	
	public String commandsToString() {
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<commands.size();i++) {
			sb.append(commands.get(i));
			if(i<commands.size()-1) {
				sb.append(", ");
			}
		}
		return sb.toString();
	}
	
	public String toString() {
		StringBuffer sb=new StringBuffer();
		sb.append("\n");
		sb.append("ID: ");
		sb.append(id);
		sb.append("; ");
		sb.append("Name: ");
		sb.append(name);
		sb.append("; ");
		sb.append("Type: ");
		sb.append(this.getClass().getSimpleName());
		sb.append("; ");
		sb.append("BirthDate: ");
		sb.append(formatter.format(bDate));
		sb.append("; ");
		sb.append("Status: ");
		if(status) {
			sb.append("present");
		}else {
			sb.append("absent");
		}
		sb.append("; ");
		sb.append("Commands: ");
		sb.append(commandsToString());
		sb.append(";");
		
		return sb.toString();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getbDate() {
		return bDate;
	}

	public void setbDate(Date bDate) {
		this.bDate = bDate;
	}

	public ArrayList<Commands> getCommands() {
		return commands;
	}

	public void setCommands(ArrayList<Commands> commands) {
		this.commands = commands;
	}

	public SimpleDateFormat getFormatter() {
		return formatter;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
	
}
