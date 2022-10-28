main : main.c main.o libmy.a
	gcc -o main main.c -L. -lmy

 dict.o:  dict.c
	gcc -c  dict.c

libmy.a : libmy.a  dict.o
	ar r libmy.a  dict.o

clean: 
	rm dict.o
	rm libmy.a main.o main 