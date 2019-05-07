import java.util.ArrayList;

public class Student implements Comparable {
	
	String firstName,lastName, ethnicity, highColor, section, gender;
	int id, group;
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
		return "Student [firstName=" + firstName + ", lastName=" + lastName + ", ethnicity=" + ethnicity
				+ ", highColor=" + highColor + ", section=" + section + ", gender=" + gender + ", id=" + id + ", group="
				+ group + ", blueScore=" + blueScore + ", greenScore=" + greenScore + ", redScore=" + redScore
				+ ", yellowScore=" + yellowScore + "]";
	}

	//Constructors
	public Student() {
		
	}
	
	public Student(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	
	//constructor for input
	public Student(int id, String lastName, String firstName, String gender, int blue, int green, int red, int yellow, String section) {
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
	
	public Student(Student s) {
		this.id = s.id;
		this.lastName = s.lastName;
		this.firstName = s.firstName;
		this.gender = s.gender;
		this.blueScore = s.blueScore;
		this.greenScore = s.greenScore;
		this.redScore = s.redScore;
		this.yellowScore = s.yellowScore;
		this.highColor = s.getHighColor();
		this.section = s.section;
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEthnicity() {
		return ethnicity;
	}

	public void setEthnicity(String ethnicity) {
		this.ethnicity = ethnicity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getBlueScore() {
		return blueScore;
	}

	public void setBlueScore(int blueScore) {
		this.blueScore = blueScore;
	}

	public int getGreenScore() {
		return greenScore;
	}

	public void setGreenScore(int greenScore) {
		this.greenScore = greenScore;
	}

	public int getRedScore() {
		return redScore;
	}
	
	public void setRedScore(int redScore) {
		this.redScore = redScore;
	}

	public int getYellowScore() {
		return yellowScore;
	}

	public void setYellowScore(int yellowScore) {
		this.yellowScore = yellowScore;
	}

	public void setHighColor(String highColor) {
		this.highColor = highColor;
	}

	public int getGroup() {
		return group;
	}

	public void setGroup(int group) {
		this.group = group;
	}
	
}
