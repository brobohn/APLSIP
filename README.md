Data and Algorithm Analysis
Project 1: Phase 2
December 9, 2015

Members: Ben Robohn, Brittany Barnes, Ryan Karel

-------------------------------------------------------------------------------
SUBMISSION DETAILS

The submission CS_4104_project.zip includes:
- APLSIP.jar
- plot.jpg
- run.bat
- run.sh
- submission_output
	- output_file_1449636254885
	- output_file_1449636254977
	- output_file_1449636255086
	- output_file_1449636255219
	- output_file_1449636255372
	- output_file_1449636255545
	- output_file_1449636256885

"APLSIP.jar" is executable, and contains all source code and compiled machine code.
"plot.jpg" shows the run times of the tests for the seven included files.
"submission_output" is a directory containing the seven output files which were produced in our final test run.
"run.sh" and "run.bat" run all 7 test cases on Linux/Mac and Windows respectively.

-------------------------------------------------------------------------------
RUNNING INSTRUCTIONS

To run all cases (n = 10, 100, 500, 1000, 2000, 10000, 20000):

On Linux/Mac:
Navigate to the unzipped submission directory and simply type the command:
./run.sh

On Windows:
Navigate to the unzipped submission directory and simply type the command:
run.bat

NOTE: If for any reason these scripts do not run correctly, type:

java -jar APLSIP.jar [NUM]

where NUM is the n-value to send the program. This will run the brute force and optimized algorithm for the specified n-value.

This produces seven output files named "output_file_<start time in ms>". Each contains the number of lines used in the test, intersections reported, and the time taken by both the brute force and optimized algorithm.

To view the source code, type "jar xf APLSIP.jar". All source code and compiled machine code is included in that jar.

-------------------------------------------------------------------------------
DESCRIPTION OF MAJOR CLASSES
- Driver: Contains the main method and handles console output. Kicks of both algorithms in each invocation.
- LineGenerator: Generates n vertical and n horizontal lines. Passes them to Driver as an array.
- Algorithm: Contains both the brute force algorithm and the optimized algorithm. Runs both algorithms and outputs results to a file.
- BST: A binary search tree of Vertical Line Segments ordered by x-value.
- EventList: An ordered list of Events.
- SweepLine: Holds the BST and reacts to Events in the EventList.

-------------------------------------------------------------------------------
IMPLEMENTATION NOTES

We utilized Java's built-in Collections.sort() method which uses a modified merge sort algorithm which guarantees time complexity of O(n log(n)) and space complexity of O(n) in the worst case.
We used our own implementation of a BST, which has been thoroughly tested.
