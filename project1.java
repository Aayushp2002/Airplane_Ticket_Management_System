import java.util.*;

public class project1 {

	public static void main(String[] args) {
		boolean seatList[][] = new boolean[13][6];

		// initialize all seats to false
		bookKarSeat(seatList);

		// show options and get the input
		String input = displayOptions(seatList);

		// validation
		while (!input.equals("N") && !input.equals("n")) {
			if (input.equals("Y") || input.equals("y")) {
				// assign a seat
				TeriMeriSeat(seatList);

				// show menu and grab user input
				input = displayOptions(seatList);
			} else {
				// grab user input
				System.out.println("To reserve a seat enter Y/y(Yes) or N/n(No): ");
				Scanner sc = new Scanner(System.in);
				input = sc.nextLine();
			}
		}
		// exit
		System.out.println("\n\nProcess will be finished with exit code 0");
	}

	public static void bookKarSeat(boolean[][] list) {
		// initialize all index in seatingList to false using nested for loop
		for (int row = 0; row < 13; row++) {
			for (int col = 0; col < 6; col++)
				list[row][col] = false;
		}
	}

	public static void displaySeatAssignments(boolean[][] list) {
		// String to row header
		String sarasKar = String.format("%18s", "A B C D E F");
		System.out.println(sarasKar);

		// format and display each row and column
		for (int row = 1; row <= 13; row++) {
			sarasKar = String.format("%-7s", "Row " + row + " ");
			for (int column = 0; column < 6; column++) {
				if (list[row - 1][column] == true)
					sarasKar += "X ";
				else
					sarasKar += "* ";
			}
			System.out.println(sarasKar);
		}
		System.out.println();
	}

	public static String displayOptions(boolean[][] list) {
		System.out
				.println("This code assigns seats for a commercial airplane.\n" + "The current seat assignments is: .");

		// String to row header
		String sidhu = String.format("%18s", "A B C D E F");
		System.out.println(sidhu);

		// format and display each row and column
		for (int i = 1; i <= 13; i++) {
			sidhu = String.format("%-7s", "Row " + i + " ");
			for (int y = 0; y < 6; y++) {
				if (list[i - 1][y] == true)
					sidhu += "X ";
				else
					sidhu += "* ";
			}
			System.out.println(sidhu);
		}

		// get and return user input for yes or no
		System.out.println("* --> Available seat\nX --> Occupied seat\n");
		System.out.println("Rows one and two are for first class passengers.\n"
				+ "Rows 3 through 7 are for business class passengers.\n"
				+ "Rows 8 through 13 are for economy class passengers\n"
				+ "To book a seat enter Y/y(Yes) or N/n(No): ");
		Scanner sca = new Scanner(System.in);
		return sca.nextLine();
	}

	public static void TeriMeriSeat(boolean[][] list) {
		// get user input for ticket type
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter ticket class: B/b (business class);  (F/f) (first class); E/e (economy class): ");
		String sentinel2 = scan.nextLine();
		System.out.println();

		// validation for ticket type
		while (!sentinel2.equals("F") && !sentinel2.equals("f") && !sentinel2.equals("B") && !sentinel2.equals("b")
				&& !sentinel2.equals("E") && !sentinel2.equals("e")) {
			System.out.print("Enter ticket class: F/f (first class);  (B/b) (business class); E/e (economy class): ");
			sentinel2 = scan.nextLine();
			System.out.println();
		}

		// implement bounds
		int nB = 0;
		int uB = 0;
		if (sentinel2.equals("F") || sentinel2.equals("f")) {
			nB = 1;
			uB = 2;
		} else if (sentinel2.equals("B") || sentinel2.equals("b")) {
			nB = 3;
			uB = 7;
		} else if (sentinel2.equals("E") || sentinel2.equals("e")) {
			nB = 8;
			uB = 13;
		}

		// check if seat is not taken twice
		boolean freeHai = false;
		while (!freeHai) {
			// grab user input for row number
			System.out.print("Enter Row number " + nB + "-" + uB + ": ");
			int rI = scan.nextInt();
			System.out.println();

			// check if user input in bounds
			while (rI < nB || rI > uB) {
				System.out.print("Enter Row number " + nB + "-" + uB + ": ");
				rI = scan.nextInt();
				System.out.println();
			}

			// grab user input for column
			Scanner scann = new Scanner(System.in);
			System.out.print("Enter seat number (A - F): ");
			String cI = scann.nextLine();
			System.out.println();

			// validate user input
			while (!cI.equals("A") && !cI.equals("B") && !cI.equals("C") && !cI.equals("D") && !cI.equals("E")
					&& !cI.equals("F")) {
				System.out.print("Enter seat number (A - F): ");
				cI = scann.nextLine();
				System.out.println();
			}

			// col letter to col num
			int nOfCol = 0;
			if (cI.equals("A"))
				nOfCol = 0;
			else if (cI.equals("B"))
				nOfCol = 1;
			else if (cI.equals("C"))
				nOfCol = 2;
			else if (cI.equals("D"))
				nOfCol = 3;
			else if (cI.equals("E"))
				nOfCol = 4;
			else if (cI.equals("F"))
				nOfCol = 5;

			// check if seat available
			if (list[rI - 1][nOfCol] == false) {
				// set seat to be reserved
				list[rI - 1][nOfCol] = true;
				System.out.println("This seat is reserved or booked for you");
				
				freeHai = true;
			}
			// return message seat not there
			else {
				System.out.println("*#*#*#*# This seat is occupied *#*#*#*#\n" + "Please select another one");
			}
		}
		// show updated seat
		displaySeatAssignments(list);
	}

}
