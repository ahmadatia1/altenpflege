main : main.c main.o libmy.a
	gcc -o main main.c -L. -lmy

function1.o function2.o function3.o to_upper_case.o: function1.c function2.c function3.c to_upper_case.c
	gcc -c function1.c function2.c function3.c to_upper_case.c

libmy.a : libmy.a function1.o function2.o function3.o to_upper_case.o
	ar r libmy.a function1.o function2.o function3.o to_upper_case.o

clean: 
	rm function1.o function2.o function3.o to_upper_case.o
	rm libmy.a main.o main