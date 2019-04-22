#!/bin/sh
clear
make clean
make all
echo "Do you wish to run all tests or one at a time?(a/1)?"
read input
clear
if [ "$input" == "a" ]; then
	make run -i
else
	echo -e "this test should fail!\nPress -Enter- to continue\n"
	read x
    make test1Output.txt;
    echo -e "\n\nPress -Enter- to continue\n"
    read x;
    clear;
	echo -e "\nthis test should pass\nPress -Enter- to continue\n"
	read x
    make testValidOutput.txt;
    echo -e "\n\nPress -Enter- to continue\n"
    read x;
    clear;
	echo -e "All tests are done!\n"
fi