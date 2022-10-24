main : main.c main.o libmy.a
	gcc -o main main.c -L. -lmy

to_upper_case.o queue.o: to_upper_case.c queue.c
	gcc -c to_upper_case.c queue.c

libmy.a : libmy.a to_upper_case.o queue.o
	ar r libmy.a to_upper_case.o queue.o

clean: 
	rm to_upper_case.o queue.o
	rm libmy.a main.o main 