#include <stdio.h>
#include "function1.h"
#include "function2.h"
#include "function3.h"
#include "to_upper_case.h"
int main()
{
    /*
    // Aufgabe 1
    char ascii;
    printf("ASCII Zeichen eingebe: ");
    ascii = getchar();
    
    if (ascii <= 126 && ascii >= 32)
    {
        printf("Zeichen: %c, Zahl: %d \n", function1(ascii), function1(ascii));
    }
    else
    {
        
    }

    
    // Aufgabe 2
    
    int a, b;
    printf("*********************Einmaleins*********************\n ");

    printf("Von: ");
    scanf("%d",& a);

    printf("Bis: ");
    scanf("%d",& b);

    function2(a, b);

    // Aufgabe 3
    printf("*********************Dualzahl*********************\n ");
    int x;

    printf("Zahl eingeben: ");
    scanf("%d",& x);
    function3(x);

    */
    // Aufgabe 4
    printf("*********************ToUpperCase*********************\n ");
    char c;
    printf("Buchstabe eingeben: ");
    c = getchar();
    printf("Zeichen: %c\n", to_upper_case(c));

    fflush(stdin);
   
    return 0;
}