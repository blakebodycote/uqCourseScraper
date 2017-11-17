package course;

import java.util.List;

public class Course {
	private final String courseCode;
	private String courseName;
	private boolean firstSem;
	private boolean secondSem;
	private boolean summerSem;
	private List<String> preReqs;
	
	public Course(String code, String name, boolean sem1, boolean sem2, boolean summerSem, List<String> preReqs){
		this.courseCode=code;
		this.courseName=name;
		this.firstSem=sem1;
		this.secondSem=sem2;
		this.summerSem=summerSem;
		this.preReqs=preReqs;
	}
	
	public String getCourseCode() {
		return courseCode;
	}

	public String getCourseName() {
		return courseName;
	}
	
	public boolean isFirstSem() {
		return firstSem;
	}
	
	public boolean isSecondSem() {
		return secondSem;
	}
	public boolean isSummerSem() {
		return summerSem;
	}

	public List<String> getPreReqs() {
		return preReqs;
	}
	
}
