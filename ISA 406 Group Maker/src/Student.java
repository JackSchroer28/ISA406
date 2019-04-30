import java.util.ArrayList;

public class Student implements Comparable {
	
	String firstName,lastName, ethnicity, highColor, section;
	int id;
	char gender;
	int blueScore, greenScore, redScore, yellowScore;
	
	
	
	void insertScores(int blue, int green, int red, int yellow) {
		this.blueScore = blue;
		this.greenScore = green;
		this.redScore = red;
		this.yellowScore = yellow;
	}
	
	public String getHighColor() {
		int max = blueScore;
		if(greenScore>max) max = greenScore;
		if(redScore>max) max = redScore;
		if(yellowScore>max) max = yellowScore;
		
		if(greenScore == max) return "green";
		if(redScore == max) return "red";
		if(yellowScore == max) return "yellow";
		
		return "blue";
	}
	
	
	@Override
	public String toString() {
		return "Student [firstName=" + firstName + ", lastName=" + lastName + ", section=" + section
				+ ", highColor=" + highColor + ", id=" + id + ", gender=" + gender + ", blueScore=" + blueScore
				+ ", greenScore=" + greenScore + ", redScore=" + redScore + ", yellowScore=" + yellowScore + "]";
	}

	//Constructors
	public Student(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	
	//constructor for input
	public Student(int id, String lastName, String firstName, char gender, int blue, int green, int red, int yellow, String section) {
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.gender = gender;
		this.blueScore = blue;
		this.greenScore = green;
		this.redScore = red;
		this.yellowScore = yellow;
		this.highColor = this.getHighColor();
		this.section = section;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public int compareTo(Object o) {
        return this.getSection().compareTo(((Student) o).getSection());
   }
	
}
