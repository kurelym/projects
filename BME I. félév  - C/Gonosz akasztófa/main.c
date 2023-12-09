#include <time.h>
#include <stdio.h>
#include <stdlib.h>
#include <conio.h>
#include "jatek.h"
#include "debugmalloc.h"
#include "megjelenites.h"
#include "dicsoseglista.h"
#ifdef _WIN32
    #include <windows.h>
#endif
int main()
{
    //ez ahhoz kell hogy a konzolon helyesen jelenjenek meg a magyar abc ekezetes betui
    #ifdef _WIN32
    SetConsoleCP(1250);
    SetConsoleOutputCP(1250);
    #endif
    srand(time(0));   //random szam generalashoz
    SzoLista *elsoszo=beolvasas(); //beolvassuk a szavakat lancolt listaba,a  lista elso elemere mutato poiner az elsoszo
    char iranyitas;
    while(iranyitas!='0')
    {

        iranyitas=menu(); //megjelenitjuk a menut
        if(iranyitas=='1') //uj jatek
        {
            if(elsoszo!=NULL) ujjatek(elsoszo); //uj jatekot inditunk
            else szavak_nem_talalhatoak_megjelenites(); //ha nem talalhato a szavak.txt akkor nem indithato uj jatek
        }
        if(iranyitas=='2') //dicsoseglista
        {
            DicsLista *eleje=dicsbeolvasas();      //beolvasas
            if(eleje!=NULL)
            {
                dicsoseg_tabla_megjelenites(eleje);     //megejelnites
                dicslista_free(eleje);                  //felszabaditas
            }
            else dicsoseg_tabla_ures_megjelenites(); //vagy eltavolitottak vagy meg nem jott letra a dicsoseglista.txt


        }
    }
    lista_free(elsoszo); //felszabaditas
    printf("          Köszönjük hogy velünk játszottál!\n\n");
    getch();
    return 0;
}
