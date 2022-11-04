#include <stdio.h>
#include <stdlib.h>
#define size 2

enum status
{
    belegt = 1,
    frei,
    geloescht
};
typedef struct  
{ 
    int key;
    enum status status;
    
} dict;

//Zeiger auf dict von typ array
dict hArray[size];

//zeiger auf dict
dict element;

// Hashwert erstellen 
int h(int a)
{
    return a % size;
}


int member(int a)
{
    static int anzahl = 0;
    
    int hKey = h(a);
    
    

    while (anzahl < size)
    {
            if (hArray[hKey].key == a)
            {
                return 1;
            }
            else if(hArray[hKey].status == geloescht)
            {
                hKey++;
                hKey %= size;
                anzahl++;

            }
    }  
    
    return -1;
}

int insert(int a)
{
    
    static int anzahl = 0;
    int hKey = h(a);
    for(int i = 0; i < size ; i++)
    {
        
        if(hArray[hKey].status == belegt)
        {
            hKey++;
            hKey %= size;
            anzahl++;

        }
        else
        {
            element.key = a;
            element.status = belegt;
            hArray[hKey] = element;
            return 1;
        }


    }

    if(anzahl >=2)
    {
        return -1;
    }

    return -1;
       
}


int delete (int a)
{
    int hKey = h(a);
    static int anzahl = 0;
    while (hArray[hKey].status == belegt && anzahl < size)
    {
            if (hArray[hKey].key == a)
            {
                
                hArray[hKey].status = geloescht;
                hArray[hKey].key = -1;
                return 1;
            }
            else if(hArray[hKey].status == geloescht)
            {
                hKey++;
                anzahl++;
                hKey %= size;

            }
    }  
 
     return -1;
   
}
