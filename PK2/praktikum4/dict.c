#include <stdio.h>
#include <stdlib.h>
#define m 2

struct dict 
{
    int key;
    int a;
};

struct dict* hashArray[m];
struct dict* element;
typedef struct dict* elementToDelete;


int h(int a)
{
    return a % m;
}


int insert(int a)
{
    element = (struct dict*) malloc(sizeof(struct dict));
    element->a = a;
    element->key = h(a);
    printf("%d", element->key);
/*     while(hashArray[element->key]->key > -1)
    {
       element->key++;
       printf("%d", element->key);
    } */
    hashArray[element->key] = element;

    if(hashArray[element->key]!= NULL)
    {
        return 1;
    }
    else{
        return -1;
    }
    
}

