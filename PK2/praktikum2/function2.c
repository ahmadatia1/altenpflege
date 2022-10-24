#include <stdio.h>
void function2 (int a, int b)
{
    for (int i = 1; i <= a; i++)
    {
       
        for (int j = a; j <= b; j++)
        {
            printf(" %d ", i*j);
        }
        printf("\n");
        
    }
    
}