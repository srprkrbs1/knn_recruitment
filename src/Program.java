import java.io.FileInputStream;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Program extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JLabel lblFirst, lblSecond, lblThird, lblFourth, lblFifth, lblResult;
	private JTextField txtFirst, txtSecond, txtThird, txtFourth, txtFifth;
	private JButton btnCalculate;

	public static Candidate[] readsample() {
		Candidate[] candidates = new Candidate[1000];
		try {
			FileInputStream fis = new FileInputStream("data.csv");
			Scanner scan = new Scanner(fis);
			scan.nextLine();
			int i = 0;
			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				double social = Double.parseDouble(line.split(",")[0]);
				double algorithm = Double.parseDouble(line.split(",")[1]);
				double gpa = Double.parseDouble(line.split(",")[2]);
				double age = Double.parseDouble(line.split(",")[3]);
				int hired = Integer.parseInt(line.split(",")[4]);
				candidates[i] = new Candidate(social, algorithm, gpa, age, hired);
				i++;
			}
			scan.close();
		} catch (Exception e) {
			System.out.println("Error");
		}
		return candidates;
	}

	public static double distance(Candidate a, Candidate b) {
		double total = Math.pow(a.getSocial() - b.getSocial(), 2) + Math.pow(a.getAlgorithm() - b.getAlgorithm(), 2)
				+ Math.pow(a.getGpa() - b.getGpa(), 2) + Math.pow(a.getAge() - b.getAge(), 2);
		return Math.pow(total, 0.5);
	}

	public static void mergeSort(double[] data, int left, int right) {
		if (right > left) {
			int mid = (right + left) / 2;
			mergeSort(data, left, mid);
			mergeSort(data, mid + 1, right);
			merge(data, left, mid, right);
		}

	}

	public static void merge(double[] data, int left, int mid, int right) {
		int sizeleft = mid - left + 1;
		int sizeright = right - mid;

		double[] subL = new double[sizeleft];
		double[] subR = new double[sizeright];

		for (int i = 0; i < sizeleft; i++) {
			subL[i] = data[left + i];
		}
		for (int j = 0; j < sizeright; j++) {
			subR[j] = data[mid + 1 + j];
		}

		int i = 0, j = 0;
		int k = left;
		while (i < sizeleft && j < sizeright) {
			if (subL[i] <= subR[j]) {
				data[k] = subL[i];
				i++;
			} else {
				data[k] = subR[j];
				j++;
			}
			k++;
		}
		while (i < sizeleft) {
			data[k] = subL[i];
			i++;
			k++;
		}
		while (j < sizeright) {
			data[k] = subR[j];
			j++;
			k++;
		}
	}

	public static void searchknn(Candidate[] sorteddata, Candidate newcand, int k) {
		int count_hired = 0;
		int count_nothired = 0;
		for (int i = 0; i < k; i++) {
			if (sorteddata[i].isHired() == 1)
				count_hired++;
			else
				count_nothired++;
		}
		if (count_hired > count_nothired)
			newcand.setHired(1);
		else
			newcand.setHired(0);
	}

	public static Candidate[] equalarrays(double[] sorted, Candidate[] candidates, Candidate newcand) {
		Candidate[] sortedcands = new Candidate[sorted.length];
		int i = 0;
		while (i < sorted.length) {
			for (int j = 0; j < candidates.length; j++) {
				if (sorted[i] == distance(candidates[j], newcand))
					sortedcands[i] = candidates[j];
			}
			i++;
		}
		return sortedcands;
	}

	public static void hire(Candidate newcand, int knn) throws Exception {
		Candidate[] candidates = readsample();
		double[] distances = new double[candidates.length];
		for (int i = 0; i < candidates.length; i++) {
			distances[i] = distance(newcand, candidates[i]);
		}
		mergeSort(distances, 0, distances.length - 1);
		Candidate[] sorted = equalarrays(distances, candidates, newcand);
		searchknn(sorted, newcand, knn);
		System.out.println(newcand.isHired());
	}

	public Program() {

		setLayout(null);
		lblFirst = new JLabel("Social:");
		lblFirst.setLocation(50, 100);
		lblFirst.setSize(100, 40);
		add(lblFirst);
		txtFirst = new JTextField();
		txtFirst.setLocation(50, 150);
		txtFirst.setSize(100, 40);
		add(txtFirst);
		lblSecond = new JLabel("Algorithm:");
		lblSecond.setLocation(190, 100);
		lblSecond.setSize(100, 40);
		add(lblSecond);
		txtSecond = new JTextField();
		txtSecond.setLocation(190, 150);
		txtSecond.setSize(100, 40);
		add(txtSecond);
		btnCalculate = new JButton("Check");
		btnCalculate.setLocation(330, 250);
		btnCalculate.setSize(100, 40);
		add(btnCalculate);
		lblThird = new JLabel("Gpa:");
		lblThird.setLocation(330, 100);
		lblThird.setSize(100, 40);
		add(lblThird);
		txtThird = new JTextField();
		txtThird.setLocation(330, 150);
		txtThird.setSize(100, 40);
		add(txtThird);
		lblFourth = new JLabel("Age:");
		lblFourth.setLocation(470, 100);
		lblFourth.setSize(100, 40);
		add(lblFourth);
		txtFourth = new JTextField();
		txtFourth.setLocation(470, 150);
		txtFourth.setSize(100, 40);
		add(txtFourth);
		lblFifth = new JLabel("Knn:");
		lblFifth.setLocation(610, 100);
		lblFifth.setSize(100, 40);
		add(lblFifth);
		txtFifth = new JTextField();
		txtFifth.setLocation(610, 150);
		txtFifth.setSize(100, 40);
		add(txtFifth);
		lblResult = new JLabel();
		lblResult.setLocation(300, 300);
		lblResult.setSize(300, 200);
		add(lblResult);
		btnCalculate.addActionListener(this);
		setSize(760, 500);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource().equals(btnCalculate)) {
			try {
				double social = Double.parseDouble(txtFirst.getText().trim());
				double algorithm = Double.parseDouble(txtSecond.getText().trim());
				double gpa = Double.parseDouble(txtThird.getText().trim());
				double age = Double.parseDouble(txtFourth.getText().trim());
				int knn = Integer.parseInt(txtFifth.getText().trim());
				Candidate cand = new Candidate(social, algorithm, gpa, age);
				hire(cand, knn);
				if (cand.isHired() == 1)
					lblResult.setText("Congratulations, You got the job.");
				else
					lblResult.setText("You didn't get the job.");

			} catch (Exception e1) {
				System.out.println("Invalid data...");
			}
		}
	}

	public static void main(String[] args) throws Exception {
		new Program();
	}
}

/*
 * 
 * 
 * read data and hold it
 * 
 * distance in 4 dimension method
 * 
 * merge sort data array with distance to given point
 * 
 * search its k nearest points
 * 
 * set hired as true or false
 * 
 * 
 * 
 */
