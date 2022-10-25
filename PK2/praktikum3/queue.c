#define anzahl 2
#include <stdio.h>
int queue[anzahl];
int top = -1;


void enqueue(int i)
{


    if(top < anzahl -1)
    {
        top++;
        queue[top] = i;
    }
    else
    {
        queue[top] = i;
    }



}



int dequeue()
{
    
    int res = queue[0];
    if(top < 0)
    {
        return -1;
    }
    else
    {
         for(int i=0; i< top; i++)
            {
                queue[i] = queue[i + 1];
        
            }
            top = top -1;

            return res; 

    }

   
}



