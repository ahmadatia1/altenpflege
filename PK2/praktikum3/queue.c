#define anzahl 10
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
    
    return queue[top--];
    
    
}

 