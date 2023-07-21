import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class ExamSeatingSystem {
	public static void main(String args[]) {
		System.out.println("_-_-_EXAM SEATING ARRANGEMENT SYSTEM_-_-_");
		Scanner sc = new Scanner(System.in);
		System.out.println("\n**CLASSROOM INFO**");
		System.out.print("Enter the classroom no.: ");
		int classroom = sc.nextInt();
		System.out.print("Enter the number of rows: ");
		int numRows = sc.nextInt();
		System.out.print("Enter the number of columns: ");
		int numCols = sc.nextInt();

		System.out.println("\n**STUDENT BATCH INFO**");
		System.out.print("Which year student and degree is seating in this classroom for the exam?: ");
		String std = sc.next();
		System.out.print("Students Roll No. starts from: ");
		int rollStart = sc.nextInt();
		System.out.print("Till: ");
		int rollEnd = sc.nextInt();
		int numStudents = rollEnd - rollStart + 1;

		int numSeats = numRows * numCols;

		if (numStudents > numSeats) {
			System.out.println("There are not enough seats for all the students!");
			return;
		} else {
			System.out.println("Total students are: " + numStudents);
		}

		int[][] seatingArrangement = new int[numRows][numCols];

		int studentNumber = rollStart;
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				if (studentNumber <= rollEnd) {
					seatingArrangement[i][j] = studentNumber;
					studentNumber++;
				}
			}
		}

		System.out.println("Seating Arrangement in classroom no. " + classroom + ": ");
		System.out.println("\t<- Facing this way <-");
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				if (seatingArrangement[i][j] == 0) {
					System.out.print(" ");
				} else {
					System.out.printf("%6d", seatingArrangement[i][j]);
				}
			}
			System.out.println();
		}
		// File Handling
		try {
			FileWriter writer = new FileWriter("seating_arrangement_" + std + "_in_" + classroom + ".txt");
			writer.write("Seating Arrangement of " + std + " students in classroom no. " + classroom + ":\n");
			writer.write("Benches in: Rows = " + numRows + ", Columns = " + numCols + ", Total = " + numSeats + "\n");
			writer.write("Total students are: " + numStudents + "\n");
			writer.write("\t<- Facing this way <-\n");
			for (int i = 0; i < numRows; i++) {
				for (int j = 0; j < numCols; j++) {
					if (seatingArrangement[i][j] == 0) {
						writer.write(" ");
					} else {
						writer.write(String.format("%6d", seatingArrangement[i][j]));
					}
				}
				writer.write("\n");
			}
			writer.write("Seats vacant: " + (numSeats - numStudents) + "\n");
			writer.close();
			System.out.println("This seating arrangement is registered in seating_arrangement_" + std + "_in_"
					+ classroom + ".txt file.");
		} catch (IOException e) {
			System.out.println("An error occurred while writing to the file: " + e);
		}
	}
}