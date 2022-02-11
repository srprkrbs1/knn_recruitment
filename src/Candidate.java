
public class Candidate {
	private double social,algorithm,gpa,age;
	private int hired;

	public Candidate(double social, double algorithm, double gpa, double age,int hired) {
		super();
		this.social = social;
		this.algorithm = algorithm;
		this.gpa = gpa;
		this.age = age;
		this.hired=hired;
	}
	
	public Candidate(double social, double algorithm, double gpa, double age) {
		super();
		this.social = social;
		this.algorithm = algorithm;
		this.gpa = gpa;
		this.age = age;
		hired=0;
	}

	public double getSocial() {
		return social;
	}

	public void setSocial(double social) {
		this.social = social;
	}

	public double getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(double algorithm) {
		this.algorithm = algorithm;
	}

	public double getGpa() {
		return gpa;
	}

	public void setGpa(double gpa) {
		this.gpa = gpa;
	}

	public double getAge() {
		return age;
	}

	public void setAge(double age) {
		this.age = age;
	}
	
	public int isHired() {
		return hired;
	}

	public void setHired(int hired) {
		this.hired = hired;
	}
}