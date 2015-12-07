Data and Algorithm Analysis
Project 1: Phase 2
December 9, 2015

Members: Ben Robohn, Brittany Barnes, Ryan Karel

-------------------------------------------------------------------------------
SUBMISSION DETAILS

The submission CS_4104_project.zip includes:
- APLSIP.jar
- plot.jpg
- submission_output
	- output_file_1449451037331
	- output_file_1449451037644
	- output_file_1449451037988
	- output_file_1449451038394
	- output_file_1449451038863
	- output_file_1449451039347
	- output_file_1449451041847

"APLSIP.jar" is executable, and contains all source code and compiled machine code.
"plot.jpg" shows the run times of the tests for the seven included files.
"submission_output" is a directory containing the seven output files which were produced in our final test run.

-------------------------------------------------------------------------------
RUNNING INSTRUCTIONS

To compile and run all cases (n = 10, 100, 500, 1000, 2000, 10000, 20000):

On Linux/Mac:
Navigate to the unzipped submission directory and simply type the command:
./run.sh

On Windows:
Navigate to the unzipped submission directory and simply type the commands:
run.bat

This produces seven output files named "output_file_<start time in ms>". Each contains the number of lines used in the test and the intersections reported.

To view the source code, type "jar xf APLSIP.jar". All source code and compiled machine code is included in that jar. If you want to recompile the source code, type "./make.sh" (Linux/Mac) or "make.bat" (Windows).

-------------------------------------------------------------------------------
IMPLEMENTATION NOTES

We utilized Java's built-in Collections.sort() method which uses a modified merge sort algorithm which guarantees time complexity of O(n log(n)) and space complexity of O(n) in the worst case.
We used our own implementation of a BST, which has been thoroughly tested.
