#include <stdio.h>
#define anzahl 9

double min(double a[], int n)
{
   double min =  a[n];
   double max = a[n];
    for(int i = 0; i < anzahl; i++)
    {
        if(a[i] < min)
        {
            min = a[i];
        }
        else if(a[i] > max)
        {
            max = a[i];
        }
        

    }
    printf("\n Max : %f", max);
    return min;
}


int main ()
{

    double a[anzahl] = {10.2, 32.1, 2.2, 9.3, 10.2, 11.2, 12.0, 11.2, 5.0};
    printf("\n %f", min(a, 2));
    return 0;
}