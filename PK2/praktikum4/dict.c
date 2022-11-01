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

struct dict *hashArray[m];
struct dict *element;
typedef struct dict *elementToDelete;

int h(int a)
{
    return a % m;
}

int member(int a)
{

    int hashedKey = h(a);

    // move in array until an empty
    while (hashArray[hashedKey] != NULL)
    {

        if (hashArray[hashedKey]->a == a)
            return 1;

        // go to next cell
        hashedKey++;

        // wrap around the table
        hashedKey %= m;
    }

    return -1;
}

int insert(int a)
{

    element = (struct dict *)malloc(sizeof(struct dict));
    int hashedKey = h(a);

    while (hashArray[hashedKey] != NULL && hashArray[hashedKey]->key != -1)
    {

        if (member(a) && !hashArray[hashedKey]->status)
        {

            ++hashedKey;
            hashedKey %= m;
        }
        else
        {

            break;
            return 0;
        }

        return 1;
    }

    element->a = a;
    element->key = hashedKey;
    element->status = true;

    hashArray[hashedKey] = element;

    return 1;
}

int delete (int a)
{
    int hashedKey = h(a);

    // move in array until an empty
    while (hashArray[hashedKey] != NULL)
    {

        if (hashArray[hashedKey]->a == a)
        {

            free(hashArray[hashedKey]);
            hashArray[hashedKey]->status = false;
            return 1;
        }

        // go to next cell
        hashedKey++;

        // wrap around the table
        hashedKey %= m;
    }

    return -1;
}
