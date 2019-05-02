import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ImportExcelFile {

	static Student f1 = new Student();
	static Student f2 = new Student();
	static ArrayList<Student> studentList = new ArrayList<Student>();
	static ArrayList<Student> sectA = new ArrayList<Student>();
	static ArrayList<Student> sectB = new ArrayList<Student>();
	static ArrayList<Student> sectC = new ArrayList<Student>();
	static ArrayList<Student> sectD = new ArrayList<Student>();
	static ArrayList<Student> highBlue = new ArrayList<Student>();
	static ArrayList<Student> highGreen = new ArrayList<Student>();
	static ArrayList<Student> highRed = new ArrayList<Student>();
	static ArrayList<Student> highYellow = new ArrayList<Student>();
	static int high = 0;
	static int index = 0;

	public static void importData() {
		// Import scanner
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);

		// Prompt user to enter location of file
		// System.out.println("Enter file location:");
		// String file = scan.nextLine();
		String file = "/home/jack/Desktop/mockdata2.txt";

		try {
			@SuppressWarnings("resource")
			BufferedReader bReader = new BufferedReader(new FileReader(file));

			String line;
			bReader.readLine(); // to make sure first line isn't read

			while ((line = bReader.readLine()) != null) {
				String array[] = line.split("\t");
				char c = array[3].charAt(0);
				studentList.add(new Student(Integer.parseInt(array[0]), // id
						array[1], // first name
						array[2], // last name
						c, // gender
						Integer.parseInt(array[4]), // blue score
						Integer.parseInt(array[5]), // green score
						Integer.parseInt(array[6]), // red score
						Integer.parseInt(array[7]), // yellow score
						array[8]));
			}
			// Throw exception error if file is not found
		} catch (Exception e) {
			System.out.println("Error: file not found");

		}
	}

	public static void sortBySection() {
		for (int i = 0; i < studentList.size(); i++) {
			if (studentList.get(i).section.equals("A")) {
				sectA.add(studentList.get(i));
				studentList.remove(i);
				i--;
			} else if (studentList.get(i).section.equals("B")) {
				sectB.add(studentList.get(i));
				studentList.remove(i);
				i--;
			} else if (studentList.get(i).section.equals("C")) {
				sectC.add(studentList.get(i));
				studentList.remove(i);
				i--;
			} else {
				sectD.add(studentList.get(i));
				studentList.remove(i);
				i--;
			}
		}
	}

	public static void sectASort() {
		// add highest scores to colors, if that is the student's highest color
		for (int j = 0; j < 10; j++) {
			// traverse 4 times to get maximum of high blue
			for (int i = 0; i < 4; i++) {
				index = 0;
				high = sectA.get(0).blueScore;
				// list traversal
				for (int o = 1; o < sectA.size(); o++) {
					if (sectA.get(o).blueScore > high) {
						index = o;
						high = sectA.get(o).blueScore;
					}
				}
				// if student w highest blue scores highest color is blue, add to high blue
				// array
				if (sectA.get(index).getHighColor().equals("blue") && highBlue.size() < 4) {
					highBlue.add(sectA.get(index));
					sectA.remove(index);
				}
			}

			// traverse 4 times to get maximum of high green
			for (int i = 0; i < 4; i++) {
				index = 0;
				high = sectA.get(0).greenScore;
				// list traversal
				for (int o = 1; o < sectA.size(); o++) {
					if (sectA.get(o).greenScore > high) {
						index = o;
						high = sectA.get(o).greenScore;
					}
				}
				// if student w highest green scores highest color is green, add to high green
				// array
				if (sectA.get(index).getHighColor().equals("green") && highGreen.size() < 4) {
					highGreen.add(sectA.get(index));
					sectA.remove(index);
				}
			}
			// traverse 4 times to get maximum of high red
			for (int i = 0; i < 4; i++) {
				index = 0;
				high = sectA.get(0).redScore;
				// list traversal
				for (int o = 1; o < sectA.size(); o++) {
					if (sectA.get(o).redScore > high) {
						index = o;
						high = sectA.get(o).redScore;
					}
				}
				// if student w highest red scores highest color is red, add to high red array
				if (sectA.get(index).getHighColor().equals("red") && highRed.size() < 4) {
					highRed.add(sectA.get(index));
					sectA.remove(index);
				}
			}
			// traverse 4 times to get maximum of high yellow
			for (int i = 0; i < 4; i++) {
				index = 0;
				high = sectA.get(0).yellowScore;
				// list traversal
				for (int o = 1; o < sectA.size(); o++) {
					if (sectA.get(o).yellowScore > high) {
						index = o;
						high = sectA.get(o).yellowScore;
					}
				}
				// if student w highest yellow scores highest color is yellow, add to high
				// yellow array
				if (sectA.get(index).getHighColor().equals("yellow") && highYellow.size() < 4) {
					highYellow.add(sectA.get(index));
					sectA.remove(index);
				}
			}
		}
		// ================================================== add remaining (aren't top
		// score but still highest)
		while (highBlue.size() < 4) {
			index = 0;
			high = sectA.get(0).blueScore;
			for (int i = 1; i < sectA.size(); i++) {
				if (sectA.get(i).blueScore > high) {
					index = i;
					high = sectA.get(i).blueScore;
				}
			}
			highBlue.add(sectA.get(index));
			sectA.remove(index);
		}
		while (highGreen.size() < 4) {
			index = 0;
			high = sectA.get(0).greenScore;
			for (int i = 1; i < sectA.size(); i++) {
				if (sectA.get(i).greenScore > high) {
					index = i;
					high = sectA.get(i).greenScore;
				}
			}
			highGreen.add(sectA.get(index));
			sectA.remove(index);
		}
		while (highRed.size() < 4) {
			index = 0;
			high = sectA.get(0).redScore;
			for (int i = 1; i < sectA.size(); i++) {
				if (sectA.get(i).redScore > high) {
					index = i;
					high = sectA.get(i).redScore;
				}
			}
			highRed.add(sectA.get(index));
			sectA.remove(index);
		}
		while (highYellow.size() < 4) {
			index = 0;
			high = sectA.get(0).yellowScore;
			for (int i = 1; i < sectA.size(); i++) {
				if (sectA.get(i).yellowScore > high) {
					index = i;
					high = sectA.get(i).yellowScore;
				}
			}
			highYellow.add(sectA.get(index));
			sectA.remove(index);
		}
		// ==================================================== flex groups
		// remaining yellow
		index = 0;
		high = sectA.get(0).yellowScore;
		for (int i = 1; i < sectA.size(); i++) {
			if (sectA.get(i).yellowScore > high) {
				index = i;
				high = sectA.get(i).yellowScore;
			}
		}
		highYellow.add(sectA.get(index));
		sectA.remove(index);

		// remaining red
		index = 0;
		high = sectA.get(0).redScore;
		for (int i = 1; i < sectA.size(); i++) {
			if (sectA.get(i).redScore > high) {
				index = i;
				high = sectA.get(i).redScore;
			}
		}
		highRed.add(sectA.get(index));
		sectA.remove(index);

		// remaining green
		index = 0;
		high = sectA.get(0).greenScore;
		for (int i = 1; i < sectA.size(); i++) {
			if (sectA.get(i).greenScore > high) {
				index = i;
				high = sectA.get(i).greenScore;
			}
		}
		highGreen.add(sectA.get(index));
		sectA.remove(index);

		// add remaining to blue
		highBlue.add(sectA.get(0));
		sectA.remove(0);
		
		// ================================== put students back so they are balanced
		sectA.add(highBlue.get(0));
		sectA.add(highGreen.get(1));
		sectA.add(highRed.get(2));
		sectA.add(highYellow.get(3));
		sectA.add(highYellow.get(4));
		
		sectA.add(highYellow.get(0));
		sectA.add(highBlue.get(1));
		sectA.add(highGreen.get(2));
		sectA.add(highRed.get(3));
		sectA.add(highRed.get(4));
		
		sectA.add(highRed.get(0));
		sectA.add(highYellow.get(1));
		sectA.add(highBlue.get(2));
		sectA.add(highGreen.get(3));
		sectA.add(highGreen.get(4));
		
		sectA.add(highGreen.get(0));
		sectA.add(highRed.get(1));
		sectA.add(highYellow.get(2));
		sectA.add(highBlue.get(3));
		sectA.add(highBlue.get(4));
		
		// ==================================== empty high color arrays
		highBlue.clear();
		highGreen.clear();
		highRed.clear();
		highYellow.clear();
		
	}
	
	public static void sectBSort() {
		// add highest scores to colors, if that is the student's highest color
		for (int j = 0; j < 10; j++) {
			// traverse 4 times to get maximum of high blue
			for (int i = 0; i < 2; i++) {
				index = 0;
				high = sectB.get(0).blueScore;
				// list traversal
				for (int o = 1; o < sectB.size(); o++) {
					if (sectB.get(o).blueScore > high) {
						index = o;
						high = sectB.get(o).blueScore;
					}
				}
				// if student w highest blue scores highest color is blue, add to high blue
				// array
				if (sectB.get(index).getHighColor().equals("blue") && highBlue.size() < 2) {
					highBlue.add(sectB.get(index));
					sectB.remove(index);
				}
			}

			// traverse 4 times to get maximum of high green
			for (int i = 0; i < 2; i++) {
				index = 0;
				high = sectB.get(0).greenScore;
				// list traversal
				for (int o = 1; o < sectB.size(); o++) {
					if (sectB.get(o).greenScore > high) {
						index = o;
						high = sectB.get(o).greenScore;
					}
				}
				// if student w highest green scores highest color is green, add to high green
				// array
				if (sectB.get(index).getHighColor().equals("green") && highGreen.size() < 2) {
					highGreen.add(sectB.get(index));
					sectB.remove(index);
				}
			}
			// traverse 4 times to get maximum of high red
			for (int i = 0; i < 2; i++) {
				index = 0;
				high = sectB.get(0).redScore;
				// list traversal
				for (int o = 1; o < sectB.size(); o++) {
					if (sectB.get(o).redScore > high) {
						index = o;
						high = sectB.get(o).redScore;
					}
				}
				// if student w highest red scores highest color is red, add to high red array
				if (sectB.get(index).getHighColor().equals("red") && highRed.size() < 2) {
					highRed.add(sectB.get(index));
					sectB.remove(index);
				}
			}
			// traverse 4 times to get maximum of high yellow
			for (int i = 0; i < 2; i++) {
				index = 0;
				high = sectB.get(0).yellowScore;
				// list traversal
				for (int o = 1; o < sectB.size(); o++) {
					if (sectB.get(o).yellowScore > high) {
						index = o;
						high = sectB.get(o).yellowScore;
					}
				}
				// if student w highest yellow scores highest color is yellow, add to high
				// yellow array
				if (sectB.get(index).getHighColor().equals("yellow") && highYellow.size() < 2) {
					highYellow.add(sectB.get(index));
					sectB.remove(index);
				}
			}
		}
		// ================================================== add remaining (aren't top
		// score but still highest)
		while (highBlue.size() < 2) {
			index = 0;
			high = sectB.get(0).blueScore;
			for (int i = 1; i < sectB.size(); i++) {
				if (sectB.get(i).blueScore > high) {
					index = i;
					high = sectB.get(i).blueScore;
				}
			}
			highBlue.add(sectB.get(index));
			sectB.remove(index);
		}
		while (highGreen.size() < 2) {
			index = 0;
			high = sectB.get(0).greenScore;
			for (int i = 1; i < sectB.size(); i++) {
				if (sectB.get(i).greenScore > high) {
					index = i;
					high = sectB.get(i).greenScore;
				}
			}
			highGreen.add(sectB.get(index));
			sectB.remove(index);
		}
		while (highRed.size() < 2) {
			index = 0;
			high = sectB.get(0).redScore;
			for (int i = 1; i < sectB.size(); i++) {
				if (sectB.get(i).redScore > high) {
					index = i;
					high = sectB.get(i).redScore;
				}
			}
			highRed.add(sectB.get(index));
			sectB.remove(index);
		}
		while (highYellow.size() < 2) {
			index = 0;
			high = sectB.get(0).yellowScore;
			for (int i = 1; i < sectB.size(); i++) {
				if (sectB.get(i).yellowScore > high) {
					index = i;
					high = sectB.get(i).yellowScore;
				}
			}
			highYellow.add(sectB.get(index));
			sectB.remove(index);
		}
		
		// ==================================================== flex groups
		f1 = new Student(sectB.get(0));
		f2 = new Student(sectB.get(1));
		sectB.clear();
		
		int higher = f1.greenScore + f1.yellowScore, var = 0;
		if((f2.greenScore + f2.yellowScore) >= higher) {
			sectB.add(f2);
			var++;
		} else {
			sectB.add(f1);
		}
		
		// ================================== put students back so they are balanced
		sectB.add(highBlue.get(0));
		sectB.add(highGreen.get(1));
		sectB.add(highRed.get(0));
		sectB.add(highYellow.get(1));
		
		if(var!=0) {
			sectB.add(f1);
		} else {
			sectB.add(f2);
		}
		
		sectB.add(highBlue.get(1));
		sectB.add(highGreen.get(0));
		sectB.add(highRed.get(1));
		sectB.add(highYellow.get(0));
		
		// ==================================== empty high color arrays
		highBlue.clear();
		highGreen.clear();
		highRed.clear();
		highYellow.clear();
		
	}
	
	public static void sectCSort() {
		// add highest scores to colors, if that is the student's highest color
		for (int j = 0; j < 10; j++) {
			// traverse 4 times to get maximum of high blue
			for (int i = 0; i < 2; i++) {
				index = 0;
				high = sectC.get(0).blueScore;
				// list traversal
				for (int o = 1; o < sectC.size(); o++) {
					if (sectC.get(o).blueScore > high) {
						index = o;
						high = sectC.get(o).blueScore;
					}
				}
				// if student w highest blue scores highest color is blue, add to high blue
				// array
				if (sectC.get(index).getHighColor().equals("blue") && highBlue.size() < 2) {
					highBlue.add(sectC.get(index));
					sectC.remove(index);
				}
			}

			// traverse 4 times to get maximum of high green
			for (int i = 0; i < 2; i++) {
				index = 0;
				high = sectC.get(0).greenScore;
				// list traversal
				for (int o = 1; o < sectC.size(); o++) {
					if (sectC.get(o).greenScore > high) {
						index = o;
						high = sectC.get(o).greenScore;
					}
				}
				// if student w highest green scores highest color is green, add to high green
				// array
				if (sectC.get(index).getHighColor().equals("green") && highGreen.size() < 2) {
					highGreen.add(sectC.get(index));
					sectC.remove(index);
				}
			}
			// traverse 4 times to get maximum of high red
			for (int i = 0; i < 2; i++) {
				index = 0;
				high = sectC.get(0).redScore;
				// list traversal
				for (int o = 1; o < sectC.size(); o++) {
					if (sectC.get(o).redScore > high) {
						index = o;
						high = sectC.get(o).redScore;
					}
				}
				// if student w highest red scores highest color is red, add to high red array
				if (sectC.get(index).getHighColor().equals("red") && highRed.size() < 2) {
					highRed.add(sectC.get(index));
					sectC.remove(index);
				}
			}
			// traverse 4 times to get maximum of high yellow
			for (int i = 0; i < 2; i++) {
				index = 0;
				high = sectC.get(0).yellowScore;
				// list traversal
				for (int o = 1; o < sectC.size(); o++) {
					if (sectC.get(o).yellowScore > high) {
						index = o;
						high = sectC.get(o).yellowScore;
					}
				}
				// if student w highest yellow scores highest color is yellow, add to high
				// yellow array
				if (sectC.get(index).getHighColor().equals("yellow") && highYellow.size() < 2) {
					highYellow.add(sectC.get(index));
					sectC.remove(index);
				}
			}
		}
		// ================================================== add remaining (aren't top
		// score but still highest)
		while (highBlue.size() < 2) {
			index = 0;
			high = sectC.get(0).blueScore;
			for (int i = 1; i < sectC.size(); i++) {
				if (sectC.get(i).blueScore > high) {
					index = i;
					high = sectC.get(i).blueScore;
				}
			}
			highBlue.add(sectC.get(index));
			sectC.remove(index);
		}
		while (highGreen.size() < 2) {
			index = 0;
			high = sectC.get(0).greenScore;
			for (int i = 1; i < sectC.size(); i++) {
				if (sectC.get(i).greenScore > high) {
					index = i;
					high = sectC.get(i).greenScore;
				}
			}
			highGreen.add(sectC.get(index));
			sectC.remove(index);
		}
		while (highRed.size() < 2) {
			index = 0;
			high = sectC.get(0).redScore;
			for (int i = 1; i < sectC.size(); i++) {
				if (sectC.get(i).redScore > high) {
					index = i;
					high = sectC.get(i).redScore;
				}
			}
			highRed.add(sectC.get(index));
			sectC.remove(index);
		}
		while (highYellow.size() < 2) {
			index = 0;
			high = sectC.get(0).yellowScore;
			for (int i = 1; i < sectC.size(); i++) {
				if (sectC.get(i).yellowScore > high) {
					index = i;
					high = sectC.get(i).yellowScore;
				}
			}
			highYellow.add(sectC.get(index));
			sectC.remove(index);
		}
		
		// ==================================================== flex groups
		f1 = new Student(sectC.get(0));
		f2 = new Student(sectC.get(1));
		sectC.clear();
		
		int higher = f1.greenScore + f1.yellowScore, var = 0;
		if((f2.greenScore + f2.yellowScore) >= higher) {
			sectC.add(f2);
			var++;
		} else {
			sectC.add(f1);
		}
		
		// ================================== put students back so they are balanced
		sectC.add(highBlue.get(0));
		sectC.add(highGreen.get(1));
		sectC.add(highRed.get(0));
		sectC.add(highYellow.get(1));
		
		if(var!=0) {
			sectC.add(f1);
		} else {
			sectC.add(f2);
		}
		
		sectC.add(highBlue.get(1));
		sectC.add(highGreen.get(0));
		sectC.add(highRed.get(1));
		sectC.add(highYellow.get(0));
		
		// ==================================== empty high color arrays
		highBlue.clear();
		highGreen.clear();
		highRed.clear();
		highYellow.clear();
		
	}
	
	public static void sectDSort() {
		// add highest scores to colors, if that is the student's highest color
		for (int j = 0; j < 10; j++) {
			// traverse 4 times to get maximum of high blue
			for (int i = 0; i < 4; i++) {
				index = 0;
				high = sectD.get(0).blueScore;
				// list traversal
				for (int o = 1; o < sectD.size(); o++) {
					if (sectD.get(o).blueScore > high) {
						index = o;
						high = sectD.get(o).blueScore;
					}
				}
				// if student w highest blue scores highest color is blue, add to high blue
				// array
				if (sectD.get(index).getHighColor().equals("blue") && highBlue.size() < 4) {
					highBlue.add(sectD.get(index));
					sectD.remove(index);
				}
			}

			// traverse 4 times to get maximum of high green
			for (int i = 0; i < 4; i++) {
				index = 0;
				high = sectD.get(0).greenScore;
				// list traversal
				for (int o = 1; o < sectD.size(); o++) {
					if (sectD.get(o).greenScore > high) {
						index = o;
						high = sectD.get(o).greenScore;
					}
				}
				// if student w highest green scores highest color is green, add to high green
				// array
				if (sectD.get(index).getHighColor().equals("green") && highGreen.size() < 4) {
					highGreen.add(sectD.get(index));
					sectD.remove(index);
				}
			}
			// traverse 4 times to get maximum of high red
			for (int i = 0; i < 4; i++) {
				index = 0;
				high = sectD.get(0).redScore;
				// list traversal
				for (int o = 1; o < sectD.size(); o++) {
					if (sectD.get(o).redScore > high) {
						index = o;
						high = sectD.get(o).redScore;
					}
				}
				// if student w highest red scores highest color is red, add to high red array
				if (sectD.get(index).getHighColor().equals("red") && highRed.size() < 4) {
					highRed.add(sectD.get(index));
					sectD.remove(index);
				}
			}
			// traverse 4 times to get maximum of high yellow
			for (int i = 0; i < 4; i++) {
				index = 0;
				high = sectD.get(0).yellowScore;
				// list traversal
				for (int o = 1; o < sectD.size(); o++) {
					if (sectD.get(o).yellowScore > high) {
						index = o;
						high = sectD.get(o).yellowScore;
					}
				}
				// if student w highest yellow scores highest color is yellow, add to high
				// yellow array
				if (sectD.get(index).getHighColor().equals("yellow") && highYellow.size() < 4) {
					highYellow.add(sectD.get(index));
					sectD.remove(index);
				}
			}
		}
		// ================================================== add remaining (aren't top
		// score but still highest)
		while (highBlue.size() < 4) {
			index = 0;
			high = sectD.get(0).blueScore;
			for (int i = 1; i < sectD.size(); i++) {
				if (sectD.get(i).blueScore > high) {
					index = i;
					high = sectD.get(i).blueScore;
				}
			}
			highBlue.add(sectD.get(index));
			sectD.remove(index);
		}
		while (highGreen.size() < 4) {
			index = 0;
			high = sectD.get(0).greenScore;
			for (int i = 1; i < sectD.size(); i++) {
				if (sectD.get(i).greenScore > high) {
					index = i;
					high = sectD.get(i).greenScore;
				}
			}
			highGreen.add(sectD.get(index));
			sectD.remove(index);
		}
		while (highRed.size() < 4) {
			index = 0;
			high = sectD.get(0).redScore;
			for (int i = 1; i < sectD.size(); i++) {
				if (sectD.get(i).redScore > high) {
					index = i;
					high = sectD.get(i).redScore;
				}
			}
			highRed.add(sectD.get(index));
			sectD.remove(index);
		}
		while (highYellow.size() < 4) {
			index = 0;
			high = sectD.get(0).yellowScore;
			for (int i = 1; i < sectD.size(); i++) {
				if (sectD.get(i).yellowScore > high) {
					index = i;
					high = sectD.get(i).yellowScore;
				}
			}
			highYellow.add(sectD.get(index));
			sectD.remove(index);
		}
		// ==================================================== flex groups
		// remaining yellow
		index = 0;
		high = sectD.get(0).yellowScore;
		for (int i = 1; i < sectD.size(); i++) {
			if (sectD.get(i).yellowScore > high) {
				index = i;
				high = sectD.get(i).yellowScore;
			}
		}
		highYellow.add(sectD.get(index));
		sectD.remove(index);

		// remaining red
		index = 0;
		high = sectD.get(0).redScore;
		for (int i = 1; i < sectD.size(); i++) {
			if (sectD.get(i).redScore > high) {
				index = i;
				high = sectD.get(i).redScore;
			}
		}
		highRed.add(sectD.get(index));
		sectD.remove(index);

		// remaining green
		index = 0;
		high = sectD.get(0).greenScore;
		for (int i = 1; i < sectD.size(); i++) {
			if (sectD.get(i).greenScore > high) {
				index = i;
				high = sectD.get(i).greenScore;
			}
		}
		highGreen.add(sectD.get(index));
		sectD.remove(index);

		// add remaining to blue
		highBlue.add(sectD.get(0));
		sectD.remove(0);
		
		// ================================== put students back so they are balanced
		sectD.add(highBlue.get(0));
		sectD.add(highGreen.get(1));
		sectD.add(highRed.get(2));
		sectD.add(highYellow.get(3));
		sectD.add(highYellow.get(4));
		
		sectD.add(highYellow.get(0));
		sectD.add(highBlue.get(1));
		sectD.add(highGreen.get(2));
		sectD.add(highRed.get(3));
		sectD.add(highRed.get(4));
		
		sectD.add(highRed.get(0));
		sectD.add(highYellow.get(1));
		sectD.add(highBlue.get(2));
		sectD.add(highGreen.get(3));
		sectD.add(highGreen.get(4));
		
		sectD.add(highGreen.get(0));
		sectD.add(highRed.get(1));
		sectD.add(highYellow.get(2));
		sectD.add(highBlue.get(3));
		sectD.add(highBlue.get(4));
		
		// ==================================== empty high color arrays
		highBlue.clear();
		highGreen.clear();
		highRed.clear();
		highYellow.clear();
		
	}

	public static void mergeSections() {
		for(int i = 0;i<sectA.size();i++) {
			studentList.add(sectA.get(i));
		}
		for(int i = 0;i<sectB.size();i++) {
			studentList.add(sectB.get(i));
		}
		for(int i = 0;i<sectC.size();i++) {
			studentList.add(sectC.get(i));
		}
		for(int i = 0;i<sectD.size();i++) {
			studentList.add(sectD.get(i));
		}	
	}
	
	public static void displayTeams() {
		int count = 0;
		for(int i = 0;i<studentList.size();i++) {
			System.out.println(studentList.get(i).toString());
			count++;
			if(count%5 == 0) 
				System.out.println();
		}
	}
	
	public static void main(String[] args) {
		importData();
		System.out.println("Size of array: " + studentList.size() + " students.");

		System.out.println("Sorting by section...");
		sortBySection();

		System.out.println("Section A size: " + sectA.size());
		System.out.println("Section B size: " + sectB.size());
		System.out.println("Section C size: " + sectC.size());
		System.out.println("Section D size: " + sectD.size());
		System.out.println("Size of array after grouping: " + studentList.size() + " students.");
		
		//sort each section
		System.out.println("------------------------------------------------------------------------------------------");
		sectASort();
		System.out.println("Section A sorted: ");
		for (Student stu : sectA) {
			System.out.println(stu.toString());
		}
		sectBSort();
		System.out.println("Section B sorted: ");
		for (Student stu : sectB) {
			System.out.println(stu.toString());
		}
		sectCSort();
		System.out.println("Section B sorted: ");
		for (Student stu : sectB) {
			System.out.println(stu.toString());
		}
		sectDSort();
		System.out.println("Section D sorted: ");
		for (Student stu : sectD) {
			System.out.println(stu.toString());
		}
		System.out.println();
		System.out.println("------------------------------------------------------------------------------------------");
		mergeSections();
		displayTeams();
	}
}
