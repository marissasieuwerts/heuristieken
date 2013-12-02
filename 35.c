#include <cs50.h>
#include <ctype.h>
#include <libgen.h>
#include <stdio.h>
#include <string.h>
#include <time.h>
#include <math.h>
#include <stdbool.h>
#include <stdlib.h>

typedef struct Tegel
{
    int nummer;
    int aantal;
    int hoogte;
    int breedte;
}
Tegel;

void powershell(int grid[ ], int aantaltegels, int verschillendetegels, Tegel* tegelarg[ ]);
void optel(int grid[ ], int vinger[ ], int aantaltegels, int opgeslagengridhoogte[ ], int opgeslagentegels[ ], int opgeteld, int verschillendetegels, Tegel* tegelarg[ ], int ping, int totaalaantaltegels,  int tegels1, int tegels2, int tegels3, int tegels4, FILE* bad, FILE* good);

int main(void)
{
    /*initializatie*/
    //grid
    int grid[2];
    grid[0]=17; // breedte
    grid[1]=17; // hoogte
    
    // aantaltegels
    int aantaltegels = 14;
    int verschillendetegels = 4;
    
    // alle tegels
    Tegel* tegelarg[4];
    tegelarg[0] = malloc(sizeof(Tegel));
    tegelarg[0]->nummer = 7;
    tegelarg[0]->aantal = 3;
    tegelarg[0]->hoogte = 7;
    tegelarg[0]->breedte = 7;           
    tegelarg[1] = malloc(sizeof(Tegel));
    tegelarg[1]->nummer = 5;
    tegelarg[1]->aantal = 3;
    tegelarg[1]->hoogte = 5;
    tegelarg[1]->breedte = 5;     
    tegelarg[2] = malloc(sizeof(Tegel));
    tegelarg[2]->nummer = 3;
    tegelarg[2]->aantal = 7;
    tegelarg[2]->hoogte = 3;
    tegelarg[2]->breedte = 3; 
    tegelarg[3] = malloc(sizeof(Tegel));
    tegelarg[3]->nummer = 2;
    tegelarg[3]->aantal = 1;
    tegelarg[3]->hoogte = 2;
    tegelarg[3]->breedte = 2;        
    
    /*test om te kijken of werkt*/
    printf("START(%d; %d)\n\n", aantaltegels, verschillendetegels);
    for(int i = 0; i < 4; i++)
    {
        printf("Nummer = %d\n", tegelarg[i]->nummer);   
        printf("Aantal = %d\n", tegelarg[i]->aantal);
        printf("Hoogte = %d\n", tegelarg[i]->hoogte);
        printf("Breedte = %d\n", tegelarg[i]->breedte);
        printf("\n");
    }    
    
    // maak naar schrijf files
    FILE* badopen;
    badopen = fopen ("Bad.txt", "w");
    fclose (badopen);
    FILE* goodopen;
    goodopen = fopen ("Good.txt", "w");
    fclose (goodopen);
    
    powershell(grid, aantaltegels, verschillendetegels, tegelarg);
    return 0;  
}  

void powershell(int grid[ ], int aantaltegels, int verschillendetegels, Tegel* tegelarg[ ])
{
    //onthoud waar we gebleven zijn met het leggen van de tegels
    int vinger[3];
    vinger[0] = 0; //breedte
    vinger[1] = 0; //hoogte
    vinger[2] = 0; //geef plek aan in opslag array
    
    /* maakt array waar de tegel combinatie in opslaan */
    //initialisatie
    int opgeslagentegels[aantaltegels];
    // op 0 zetten
    for (int j = 0; j < aantaltegels; j++)
    {
        opgeslagentegels[j] = 0; 
    }
    // test print
    for(int j = 0; j < aantaltegels; j++)
    {
        printf("tegelarray[%d] = %d\n", j, opgeslagentegels[j]); 
    }
    
    /* maakt array waar we de gebruikte hoogte in opslaan */
    //initialisatie
    int opgeslagengridhoogte[grid[0]];
    // op 0 zetten
    for (int j = 0; j < grid[0]; j++)
    {
        opgeslagengridhoogte[j] = 0;
    }
    // test print
    for(int j = 0; j < grid[0]; j++)
    {
        printf("HOOGTE[%d] = %d\n", j, opgeslagengridhoogte[j]); 
    }
    
    //test vingeraanwijzers
    printf("\nBreedte = %d\n", vinger[0]); 
    printf("Hoogte = %d\n", vinger[1]);
    
    int opgeteld = 0;
    int ping = 0;
    // simpele omzeiling van een struct probleem (als je de aantallen weergeeft met een pointer dan wijzig je dat mem adress. Geef met recursie die pointer mee en alles blijft gewijzigd / het keert niet terug naar een orginele staat)
    int totaal = 14;
    int tegels1 = 4;
    int tegels2 = 5;    
    int tegels3 = 8;
    int tegels4 = 1;
    
    FILE* bad = fopen ("Bad.txt", "a");
    FILE* good = fopen ("Good.txt", "a");        
    optel(grid, vinger, aantaltegels, opgeslagengridhoogte, opgeslagentegels, opgeteld, verschillendetegels, tegelarg, ping, totaal, tegels1, tegels2, tegels3, tegels4, bad, good);
}

void optel(int grid[ ], int vinger[ ], int aantaltegels, int opgeslagengridhoogte[ ], int opgeslagentegels[ ], int opgeteld, int verschillendetegels, Tegel* tegelarg[ ], int ping, int totaalaantaltegels, int tegels1, int tegels2, int tegels3, int tegels4, FILE* bad, FILE* good)
{
    //check if juiste aantal tegels
    //printf("ok");
    if ((aantaltegels > 0)&& (aantaltegels < 16))
    {
        //printf("ok");
        // maak copy aan die je (later) aanpast om door te geven met de recursie
        int gridhoogte2[grid[0]];
        int opgeteld2;
        for (int j = 0; j < grid[1]; j++)
        {
            gridhoogte2[j] = opgeslagengridhoogte[j];
        }
        int tegels21 = tegels1;
        int tegels22 = tegels2;
        int tegels23 = tegels3;
        int tegels24 = tegels4;
        
        //kies de juiste tegel
        for(int i = 0; i < aantaltegels; i++)
        {
            Tegel* paktegel;
            // eerste 7x7 tegel
            if(i < tegels1)
            {
                paktegel = tegelarg[0];
                // pas aan voor de recursie
                tegels21 = (tegels1 - 1); 
            }
            // tweede 5x5 tegel
            else if(i < (tegels1 + tegels2))
            {
                paktegel = tegelarg[1];
                // pas aan voor de recursie
                tegels22 = (tegels2 - 1); 
            }
            // derder 3x3 tegel
            else if(i < (tegels1 + tegels2 + tegels3))
            {
                paktegel = tegelarg[2];
                // pas aan voor de recursie
                tegels23 = (tegels3 - 1); 
            }
            // vierde 2x2 tegel
            else if(i < (tegels1 + tegels2 + tegels3 + tegels4))
            {
                paktegel = tegelarg[3];
                // pas aan voor de recursie
                tegels24 = (tegels4 - 1); 
            }

            // test de breedte, vraag gaat het de grens van 17 over?        
            opgeteld2 = (opgeteld + paktegel->breedte);
            // de som is kleiner de grid breedte eis
            if(opgeteld2 < grid[0])
            {
                //check hoogte
                bool test = true;
                for(int k = 0; k < paktegel->breedte; k++)
                {
                    gridhoogte2[(vinger[0]+k)] = (opgeslagengridhoogte[vinger[0]+k] + paktegel->hoogte);  
                    // als hoogte groter dan grid hoogte ga stop process in
                    if(gridhoogte2[vinger[0]+k] > grid[1]) 
                    {
                        test = false;
                    }    
                }
                // als stop process niet is getest
                if(test == true)
                {
                    // zorg ervoor dat het nummer van de tegel in de bewaar array beland
                    opgeslagentegels[vinger[2]] = (paktegel->nummer);
                    // schuif de bewaar array plek aanwijzer 1 op
                    vinger[2]++;
                    // verander aantal tegels nog aanwezig  
                    int aantaltegels2 = (totaalaantaltegels - ping);
                    // verander de breedte plekaanwijzer                    
                    vinger[0] = opgeteld2;
                   
                   fprintf(good, "[");
                   for (int j = 0; j < 14; j++)
                   {
                       fprintf(good, "%d", opgeslagentegels[j]); 
                   }
                   fprintf(good, "] - OKless\n");
                   
                   //recursie
                   optel(grid, vinger, aantaltegels2, gridhoogte2, opgeslagentegels, opgeteld2, verschillendetegels, tegelarg, (ping+1), totaalaantaltegels, tegels21, tegels22, tegels23, tegels24, bad, good);
                    
                    //verander terug
                    vinger[2]--;
                    vinger[0] = opgeteld;
                }
                else
                {
                    opgeslagentegels[vinger[2]] = (paktegel->nummer); 
                    fprintf(bad, "[");
                    for (int j = 0; j < 14; j++)
                    {
                        fprintf(bad, "%d", opgeslagentegels[j]); 
                    }
                    fprintf(bad, "] - BAD\n");
                }
            }
            
            else if(opgeteld2 == grid[0])
            {
                //check hoogte
                bool test = true;
                bool test2 = true;
                for(int k = 0; k < paktegel->breedte; k++)
                {
                    gridhoogte2[(vinger[0]+k)] = (opgeslagengridhoogte[vinger[0]+k] + paktegel->hoogte);  
                
                    if(gridhoogte2[vinger[0]+k] > grid[1]) 
                    {
                        // als hoogte groter dan grid hoogte ga stop process in
                        test = false;
                    }    
                    // test of dit mischien de top is waar alles 17 is?
                    if(gridhoogte2[vinger[0]+k] != grid[1]) 
                    {
                        test2 = false;
                    }
                }
                if(test == true)
                {
                    if(test2 == true) 
                    {
                        opgeslagentegels[vinger[2]] = (paktegel->nummer); 
                        fprintf(good, "[");
                        for (int j = 0; j < 14; j++)
                        {
                            fprintf(good, "%d", opgeslagentegels[j]); 
                        }
                        fprintf(good, "] - GOOD\n");
                    }
                    else if(test2 == false) 
                    {                
                        // zorg ervoor dat het nummer van de tegel in de bewaar array beland
                        opgeslagentegels[vinger[2]] = (paktegel->nummer);
                        // schuif de bewaar array plek aanwijzer 1 op
                        vinger[2]++;
                        // verander aantal tegels nog aanwezig  
                        int aantaltegels2 = (totaalaantaltegels - ping);
                       // verander de breedte plekaanwijzer                    
                       vinger[0] = 0;
                       opgeteld2 = 0;
                                            
                        fprintf(good, "[");
                        for (int j = 0; j < 14; j++)
                        {
                            fprintf(good, "%d", opgeslagentegels[j]); 
                        }
                        fprintf(good, "] - OK=\n");
 
                       //recursie
                       optel(grid, vinger, aantaltegels2, gridhoogte2, opgeslagentegels, opgeteld2, verschillendetegels, tegelarg, (ping+1), totaalaantaltegels, tegels21, tegels22, tegels23, tegels24, bad, good);
 
                       //verander terug
                       vinger[2]--;
                       vinger[0] = opgeteld;
                   }
                }
                else
                {
                    opgeslagentegels[vinger[2]] = (paktegel->nummer); 
                    fprintf(bad, "[");
                    for (int j = 0; j < 14; j++)
                    {
                        fprintf(bad, "%d", opgeslagentegels[j]); 
                    }
                    fprintf(bad, "] - BAD\n");
                }
            }
            else
            {
                opgeslagentegels[vinger[2]] = (paktegel->nummer); 
                fprintf(bad, "[");
                for (int j = 0; j < 14; j++)
                {
                    fprintf(bad, "%d", opgeslagentegels[j]); 
                }                        
                fprintf(bad, "] - BAD\n");
            }
        }
    }
}