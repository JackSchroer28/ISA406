import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

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

	public static void importData() throws EncryptedDocumentException, IOException {
		
		String file = null;
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		
		int returnValue = jfc.showOpenDialog(null);
		
		if(returnValue==JFileChooser.APPROVE_OPTION) {
			File selectedFile = jfc.getSelectedFile();
			file = selectedFile.getAbsolutePath();
		}
		Workbook wb = null;
		try {
			wb = WorkbookFactory.create(new File(file));
		} catch (Exception e) {
			System.out.println("Error : cannot read that file. Please select a valid file.");
			System.exit(1);
		}
		

		// get sheet from workbook
		Sheet sheet = wb.getSheetAt(0);

		// iterate over rows and columns of sheet
		Iterator<Row> rowIterator = sheet.rowIterator();

		// skip row one because it is the header
		rowIterator.next();

		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			Student stu = new Student();
			// iterate over columns of curr row
			Iterator<Cell> cellIterator = row.cellIterator();
			Cell cell = cellIterator.next();

			stu.setId((int) cell.getNumericCellValue());
			cell = cellIterator.next();
			stu.setFirstName(cell.getStringCellValue());
			cell = cellIterator.next();
			stu.setLastName(cell.getStringCellValue());
			cell = cellIterator.next();
			stu.setGender(cell.getStringCellValue());
			cell = cellIterator.next();
			stu.setBlueScore((int) cell.getNumericCellValue());
			cell = cellIterator.next();
			stu.setGreenScore((int) cell.getNumericCellValue());
			cell = cellIterator.next();
			stu.setRedScore((int) cell.getNumericCellValue());
			cell = cellIterator.next();
			stu.setYellowScore((int) cell.getNumericCellValue());
			cell = cellIterator.next();
			stu.setSection(cell.getStringCellValue());
			stu.setHighColor(stu.getHighColor());

			studentList.add(stu);
		}
		wb.close();
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
		if ((f2.greenScore + f2.yellowScore) >= higher) {
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

		if (var != 0) {
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
		if ((f2.greenScore + f2.yellowScore) >= higher) {
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

		if (var != 0) {
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
		int groupNum = 5;
		for (int i = 0; i < sectA.size(); i++) {
			sectA.get(i).setGroup(groupNum++ / 5);
			studentList.add(sectA.get(i));
		}
		for (int i = 0; i < sectB.size(); i++) {
			sectB.get(i).setGroup(groupNum++ / 5);
			studentList.add(sectB.get(i));
		}
		for (int i = 0; i < sectC.size(); i++) {
			sectC.get(i).setGroup(groupNum++ / 5);
			studentList.add(sectC.get(i));
		}
		for (int i = 0; i < sectD.size(); i++) {
			sectD.get(i).setGroup(groupNum++ / 5);
			studentList.add(sectD.get(i));
		}
	}

	public static void displayTeams() {
		int count = 0;
		for (int i = 0; i < studentList.size(); i++) {
			System.out.println(studentList.get(i).toString());
			count++;
			if (count % 5 == 0)
				System.out.println();
		}
	}

	public static void exportData() throws IOException {

		String[] columns = { "id", "Group #", "First Name", "Last Name", "Gender", "Blue Score", "Green Score",
				"Red Score", "Yellow Score", "Section" };
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("Student");

		// create font for styling header cells
		Font headerFont = workbook.createFont();
		headerFont.setBold(true);
		headerFont.setFontHeightInPoints((short) 14);
		headerFont.setColor(IndexedColors.RED.getIndex());

		// create cellstyle with the font
		CellStyle headerCellStyle = workbook.createCellStyle();
		headerCellStyle.setFont(headerFont);

		// create a row
		Row headerRow = sheet.createRow(0);

		// create cells
		for (int i = 0; i < columns.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(columns[i]);
		}

		// create remaining rows and fill w data
		int rowNum = 1;
		for (Student s : studentList) {
			Row row = sheet.createRow(rowNum++);

			row.createCell(0).setCellValue(s.getId());
			row.createCell(1).setCellValue(s.getGroup());
			row.createCell(2).setCellValue(s.getFirstName());
			row.createCell(3).setCellValue(s.getLastName());
			row.createCell(4).setCellValue(s.getGender());
			row.createCell(5).setCellValue(s.getBlueScore());
			row.createCell(6).setCellValue(s.getGreenScore());
			row.createCell(7).setCellValue(s.getRedScore());
			row.createCell(8).setCellValue(s.getYellowScore());
			row.createCell(9).setCellValue(s.getSection());
		}

		// reseize columns to fit content size
		for (int i = 0; i < columns.length; i++) {
			sheet.autoSizeColumn(i);
		}

		// write output to file

		FileOutputStream fileOut = new FileOutputStream("output.xlsx");
		workbook.write(fileOut);
		fileOut.close();

		// close workbook
		workbook.close();

	}

	public static void main(String[] args) throws IOException {
		System.out.println("Importing Data...");
		importData();

		System.out.println("Sorting by section...");
		sortBySection();

		System.out.println("Sorting section A...");
		sectASort();

		System.out.println("Sorting section B...");
		sectBSort();

		System.out.println("Sorting section C...");
		sectCSort();

		System.out.println("Sorting section D...");
		sectDSort();

		System.out.println("Merging sorted sections back to main list...");
		mergeSections();

		System.out.println("Exporting list to excel doc...");
		exportData();

	}
}
