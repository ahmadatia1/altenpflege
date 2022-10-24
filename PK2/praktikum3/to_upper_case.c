#include <stdio.h>
#include <string.h>
char to_upper_case(char c)
{
    if (c >= 97 && c <= 122)
    {
        return c - 32;
    }
    else
    {
        return '0';
    }
     
}

void upper(char *text)
{
    int anzahl = strlen(text);
    
    for(int i = 0; i < anzahl; i++)
    
    {
       
        printf("%c", to_upper_case(text[i]));
    }
     
}