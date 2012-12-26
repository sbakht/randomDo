package com.example.randomtodo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener,
		OnCheckedChangeListener {

	TextView task, priority;
	Button addTask;
	RadioGroup priorityList;
	EditText text;
	// String gotBread;
	String addData;
	// Boolean isChecked;
	int pri;
	private static ArrayList<String> pHigh = new ArrayList<String>();
	private static ArrayList<String> pMed = new ArrayList<String>();
	private static ArrayList<String> pLow = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main2);
		initialize();
	}

	private void initialize() {
		task = (TextView) findViewById(R.id.tvTask);
		priority = (TextView) findViewById(R.id.tvPriority);
		addTask = (Button) findViewById(R.id.bAddtask);
		addData = "";
		// isChecked=false;
		pri = 0;
		addTask.setOnClickListener(this);
		priorityList = (RadioGroup) findViewById(R.id.rgPriority);
		priorityList.setOnCheckedChangeListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (addData.equals("")) {
			AlertDialog.Builder adb = new AlertDialog.Builder(this);
			adb.setTitle("Missing Task");
			adb.setMessage("Please enter a task");
			adb.setPositiveButton("Ok",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							dialog.cancel();
						}
					});
			// create alert dialog
			AlertDialog alertDialog = adb.create();

			// show it
			alertDialog.show();
			
		} else {
			addData = text.getText().toString();

			if (pri > 0) {
				try {
					addNew(pri, addData);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				AlertDialog.Builder adb = new AlertDialog.Builder(this);
				adb.setTitle("Missing Priority");
				adb.setMessage("Please choose a priority level");
				adb.setPositiveButton("High",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								try {
									addNew(1, addData);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						});
				adb.setNeutralButton("Medium",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								try {
									addNew(2, addData);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						});
				adb.setNeutralButton("Low",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								try {
									addNew(3, addData);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						});
				// create alert dialog
				AlertDialog alertDialog = adb.create();
 
				// show it
				alertDialog.show();
				
				// FrameLayout fl = (FrameLayout)
				// findViewById(android.R.id.custom);
				// fl.addView(myView, new LayoutParams(MATCH_PARENT,
				// WRAP_CONTENT));
			}
		}
	}

	@Override
	public void onCheckedChanged(RadioGroup arg0, int arg1) {
		// TODO Auto-generated method stub
		switch (arg1) {
		case R.id.rHigh:
			pri = 1;
			break;
		case R.id.rMed:
			pri = 2;
			break;
		case R.id.rLow:
			pri = 3;
			break;
		}
		// test.setText(setData);
	}

	public static void readFile() throws FileNotFoundException {

		File f1 = new File("p1.txt");
		File f2 = new File("p2.txt");
		File f3 = new File("p3.txt");
		Scanner sc1 = new Scanner(f1);
		Scanner sc2 = new Scanner(f2);
		Scanner sc3 = new Scanner(f3);
		while (sc1.hasNext()) {
			pHigh.add(sc1.next());
			sc1.nextLine();
		}
		while (sc2.hasNext()) {
			pMed.add(sc2.next());
			sc2.nextLine();
		}
		while (sc3.hasNext()) {
			pLow.add(sc3.next());
			sc3.nextLine();
		}
		// Collections.shuffle(pHigh);
		// Collections.shuffle(pMed);
		// Collections.shuffle(pLow);
	}

	public void deleteLast(ArrayList<String> array) {
		int size = array.size();
		array.remove(size - 1);
		array.trimToSize();
	}

	public String showTask() {
		if (pHigh.size() != 0) {
			Collections.shuffle(pHigh);
			return pHigh.get(pHigh.size() - 1);
		}
		if (pMed.size() != 0) {
			Collections.shuffle(pMed);
			return pMed.get(pMed.size() - 1);
		}
		if (pLow.size() != 0) {
			Collections.shuffle(pLow);
			return pLow.get(pLow.size() - 1);
		}
		return "No tasks recorded";
	}

	public void addNew(int priority, String value) throws IOException {
		if (priority == 1) {
			File file = new File("p1.txt");
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("/n" + value);
			bw.close();
			pHigh.add(value);
		} else if (priority == 2) {
			File file = new File("p2.txt");
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("/n" + value);
			bw.close();
		} else {
			File file = new File("p3.txt");
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("/n" + value);
			bw.close();
		}

	}
}
