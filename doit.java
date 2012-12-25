package mainpac;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class doit {
  private static ArrayList<String> pHigh=new ArrayList<String>();
	private static ArrayList<String> pMed=new ArrayList<String>();
	private static ArrayList<String> pLow=new ArrayList<String>();
	
	public static void readFile() throws FileNotFoundException{
		
		File f1=new File("p1.txt");
		File f2=new File("p2.txt");
		File f3=new File("p3.txt");
		Scanner sc1=new Scanner(f1);
		Scanner sc2=new Scanner(f2);
		Scanner sc3=new Scanner(f3);
		while(sc1.hasNext()){
			pHigh.add(sc1.next());
			sc1.nextLine();
		}
		while(sc2.hasNext()){
			pMed.add(sc2.next());
			sc2.nextLine();
		}
		while(sc3.hasNext()){
			pLow.add(sc3.next());
			sc3.nextLine();
		}
		//Collections.shuffle(pHigh);
		//Collections.shuffle(pMed);
		//Collections.shuffle(pLow);
	}
	
	public void deleteLast(ArrayList<String> array){
		int size=array.size();
		array.remove(size-1);
		array.trimToSize();
	}
	
	public String showTask(){
		if(pHigh.size()!=0){
			Collections.shuffle(pHigh);
			return pHigh.get(pHigh.size()-1);
		}
		if(pMed.size()!=0){
			Collections.shuffle(pMed);
			return pMed.get(pMed.size()-1);
		}
		if(pLow.size()!=0){
			Collections.shuffle(pLow);
			return pLow.get(pLow.size()-1);
		}
		return "No tasks recorded";
	}
	
	public void addNew(int priority, String value) throws IOException{
		if(priority==1){
			File file=new File("p1.txt");
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("/n"+value);
			bw.close();
			pHigh.add(value);
			//Collections.shuffle(pHigh);
		}else if(priority==2){
			File file=new File("p2.txt");
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("/n"+value);
			bw.close();
			//Collections.shuffle(pMed);
		}else{
			File file=new File("p3.txt");
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("/n"+value);
			bw.close();
			//Collections.shuffle(pLow);
		}
		
		
	}
	public static void main(String[] args) throws FileNotFoundException{
		//readFile();
		
	}
}
