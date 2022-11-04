#include <stdio.h>
#include <stdlib.h>
#define m 2

enum boolean
{
    true,
    false
};
struct dict 
{
    int key;
    int a;
    enum boolean status;
};

struct dict *hArray[m];
struct dict *element;


int h(int a)
{
    return a % m;
}

int member(int a)
{

    int hKey = h(a);


    while (hArray[hKey] != NULL)
    {

        if (hArray[hKey]->a == a)
            return 1;

    
        hKey++;

        hKey %= m;
    }

    return -1;
}

int insert(int a)
{

    element = malloc(sizeof(struct dict));
    int hKey = h(a);

    while (hArray[hKey] != NULL && hArray[hKey]->key != -1)
    {

        if (member(a) && !hArray[hKey]->status)
        {

            ++hKey;
            hKey %= m;
        }
        else
        {

            break;
            return 0;
        }

        return 1;
    }

    element->a = a;
    element->key = hKey;
    element->status = true;

    hArray[hKey] = element;

    return 1;
}

int delete (int a)
{
    int hKey = h(a);

   
    while (hArray[hKey] != NULL)
    {

        if (hArray[hKey]->a == a)
        {

            free(hArray[hKey]);
            hArray[hKey]->status = false;
            return 1;
        }

  
        hKey++;

       
        hKey %= m;
    }

    return -1;
}
