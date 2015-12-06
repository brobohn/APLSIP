import java.io.*;

/**
 * Initializes data structures, parses input, runs the sweep line algorithm,
 * reports intersections.
 */
public class Driver {

	static SweepLine sweeper;
	static EventList E;
	static LineGenerator lg;
	static Algorithm A;

	/**
	 * Initializes the Sweep Line and the Event List.
	 */
	static void init() {
		sweeper = new SweepLine();
		E = new EventList();
		lg = new LineGenerator();
	 
		String str = "output_file_" + System.currentTimeMillis();
		RandomAccessFile raf = null;
		try {
			raf = new RandomAccessFile(new File(str), "rw");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		A = new Algorithm(raf);
	}

	/**
	 * Run the algorithm.
	 * 
	 * @param brute
	 *            if true, run the brute-force algorithm.
	 * @param lines
	 */
	static void runAlgorithm(boolean brute, LineSegment[] lines) {
		if (brute) {
			try {
				A.bruteForceAlgorithm(lines);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			A.algorithm(lines, E, sweeper);
		}
	}

	/**
	 * Main.
	 * 
	 * Run as: java Driver [NUM] OR java Driver -b [NUM]
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		boolean brute = false;

		int n = 0;
		LineSegment[] lines;

		// parse command line args
		if (args.length == 1) {
			try {
				n = Integer.parseInt(args[0]);
			} catch (NumberFormatException nfe) {
				usage();
				System.exit(-1);
			}
		} else if (args.length == 2) {
			if (args[0].compareTo("-b") == 0) {
				brute = true;
				try {
					n = Integer.parseInt(args[1]);
				} catch (NumberFormatException nfe) {
					usage();
					System.exit(-1);
				}
			} else {
				usage();
				System.exit(-1);
			}
		} else {
			usage();
			System.exit(-1);
		}

		if (brute) {
			System.out.println(String.format(
					"Running brute force algorithm with %d lines...", n));
		} else {
			System.out.println(String.format(
					"Running algorithm with %d lines...", n));
		}

		init();
		lines = lg.generateLines(n);
		runAlgorithm(brute, lines);

		long end = System.currentTimeMillis();
		long time = end - start;

		System.out.println(String.format("%d ms", time));
	}

	private static void usage() {
		System.out.println("Run as: java Driver [NUM]");
		System.out.println("        OR");
		System.out.println("        java Driver -b [NUM]");
	}

}
