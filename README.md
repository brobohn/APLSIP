Data and Algorithm Analysis
Project 1: Phase 2
December 9, 2015

Members: Ben Robohn, Brittany Barnes, Ryan Karel

To compile and run all cases (n = 10, 100, 500, 1000, 2000, 10000, 20000):

ON LINUX/MAC:
Navigate to the unzipped submission directory and simply type the command:
./run.sh

ON WINDOWS:
Navigate to the unzipped submission directory and simply type the commands:
run.bat

This produces seven output files named "output_file_<start time in ms>". Each contains the number of lines 

Included Output Files in directory submission_output:
1. output_file_1449451037331
2. output_file_1449451037644
3. output_file_1449451037988
4. output_file_1449451038394
5. output_file_1449451038863
6. output_file_1449451039347
7. output_file_1449451041847

The file "plot.jpg" shows the run times of the tests for the seven included files.

To view the source code, type "jar xf APLSIP.jar". All source code and compiled machine code is included in that jar. If you want to recompile the source code, type "./make.sh" (Linux/Mac) or "make.bat" (Windows).

Outside Code Utilized:
- Java's sort() function

We used our own implementation of a BST, which has been thoroughly tested.