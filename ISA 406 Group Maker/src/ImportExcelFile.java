import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ImportExcelFile {

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
		sectASort();
		System.out.println("High Blue Size: " + highBlue.size());
		for (Student stu : highBlue) {
			System.out.println(stu.toString());
		}
		System.out.println("High Green Size: " + highGreen.size());
		for (Student stu : highGreen) {
			System.out.println(stu.toString());
		}
		System.out.println("High Red Size: " + highRed.size());
		for (Student stu : highRed) {
			System.out.println(stu.toString());
		}
		System.out.println("High Yellow Size: " + highYellow.size());
		for (Student stu : highYellow) {
			System.out.println(stu.toString());
		}
		System.out.println(sectA.size());

	}
}
