#include <stdio.h>
#include <math.h>
void function3 (int x)
{
    if(x >= 0 && x <= 65535)
    {
        printf("Zahl: %d\n", x);
        printf("Binär: ");
        for(int i=0;0<x;i++)    
        {    
            printf("%d",x%2);    
            x=x/2;  
            
        }  
        printf("\n");

    }
  

}