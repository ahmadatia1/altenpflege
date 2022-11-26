#include <stdio.h>
#define anzahl 10
int countspace(char s[])
{
    int iAz = 0;
    for(int i = 0; i < 10; i++)
    {
        if(s[i] == ' ')
        {
            iAz++;
        }
    }
    
    return iAz;
}

int main()
{
    char s[anzahl] = "Hel loW";
    printf("%d", countspace(s));

    return 0;
}
