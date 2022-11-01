#include <stdio.h>
#include <stdlib.h>
#define m 2

enum boolean  
{
    TRUE,
    FALSE
};
struct dict
{
    int key;
    int a;
    enum boolean status;
};

struct dict *hashArray[m];
struct dict *element;
typedef struct dict *elementToDelete;

int h(int a)
{
    return a % m;
}


int member(int a)
{
    
    int key = h(a);
     
    if(key < m )
    {
       
        while(hashArray[key]->a != a) {
            if(hashArray[key]!= NULL )
            {
                
                key++;
            
                 key = h(key);
                 printf("key: %d\n", hashArray[key]->a );
            }
            else
            {
                return -1;
                break;
            }
            

            
            
        }  
        return 1;    
   }
   else if(key >= m)
   {
    return -1;
   }
   
}

int insert(int a)
{
    element = (struct dict *)malloc(sizeof(struct dict));
    element->a = a;
    element->key = h(a);

    while (hashArray[element->key] != NULL && hashArray[element->key]->key != -1)
    {
        // go to next cell
        element->key++;
        if (element->key > 2)
        {
            return 0;
            break;
        }
    }

    hashArray[element->key] = element;

    return 1;
}



int delete(int a)
{
    int key = h(a);

    if (key > 0)
    {

        while (hashArray[key]->a != a)
        {
            printf("key: %d", key);
            if (hashArray[key]->a == a)
            {
                
                free(hashArray[key]);
                return 1;
   
            }
            key++;
            key%=m;
         
        }
        
        
        return -1;
    }
    else
    {
        
        return -1;
    }
}