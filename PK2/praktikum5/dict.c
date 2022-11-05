#include <stdio.h>
#include <stdlib.h>

#define size 2

struct dict
{
    int key;
    struct dict *next;
};

struct dict *hArray[size];
struct dict *top[size] = {NULL};

int h(int a)
{
    return a % size;
}

int insert(int a)
{

    int hKey = h(a);
    hArray[hKey] = (struct dict *)malloc(sizeof(struct dict));
    if(hArray[hKey]!=NULL)
    {

        hArray[hKey]->key = a;
        hArray[hKey]->next = top[hKey];
        top[hKey] = hArray[hKey];
        return 1;
     }
     else
     {
        return 0;
     }

    
}

int member(int a)
{
    int hKey = h(a);
    struct dict *tmp = top[hKey];
    while (tmp != NULL)
    {

        if (tmp->key == a)
        {

            return 1;
        }
        else
        {

            tmp = tmp->next;
        }
    }

    return 0;
}

int delete(int a)
{
    int hKey = h(a);
    struct dict *tmp = top[hKey];
    while (tmp != NULL)
    {

        if (tmp->key == a)
        {
            top[hKey] = top[hKey]->next;
            free(tmp);
            return 1;
        }
        else
        {

            tmp = tmp->next;
        }
    }

    return 0;
}



void display(int a)
{
    int hKey = h(a);
    while (top[hKey] != NULL)
    {
        printf("\n--%d--\n", top[hKey]->key);
        top[hKey] = top[hKey]->next;
    }
}