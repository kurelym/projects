#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "debugmalloc.h"
#include "dicsoseglista.h"

//ez a fv olvassa be a dicsoseg_lista.txt-ben tarolt adatokat
//visszeter a lista elso elemere mutato pointerrel
DicsLista* dicsbeolvasas(void)
{
    DicsLista *eleje=NULL; //letrehozzuk a lancolt lista elso elemet
    FILE * fPointer;
    fPointer=fopen("dicsoseg_lista.txt","r"); //megnyitjuk a file-t olvasasra
    if (fPointer == NULL)
    {
        return NULL;    // nem talalhato a fajl -> NULL pointert returnolunk
    }
    else
    {
        char line[100];
        char nev[50];
        int score;
        while(fgets(line,100,fPointer)!=NULL) //addig olvassuk be a file sorait amgig tudjuk
        {
            sscanf(line,"%s %d",nev,&score);
            char *uj=dinamikusantarolas(nev); //dinamikusan eltaroljuk a felhasznalonevet
            eleje=beszur(eleje,uj,score); //a ket adatot hozzafuzzuk a lancolt listahoz
        }
        fclose(fPointer); //bezarjuk az olvasasra megnyitott file-t
        return eleje; //a fv visszater a lista elso elemere mutato pointerrel
    }

}
//ennek a fvnek a seitsegevel epitjuk fel a dicsoseg lista elemeit pontszam szerint csokkeno sorrendben tarolo lancolt listat
DicsLista *beszur(DicsLista *elso,char  *ujnev, int ujpont)
{
    DicsLista *uj = (DicsLista *)malloc(sizeof(DicsLista)); //dinamikusan helyet foglalunk a lista uj elemenek
    uj->nev = ujnev;
    uj->pontszam = ujpont;

    if (elso == NULL || elso->pontszam < ujpont) //ha az elso elemnel nagyobb akkor az uj elem lesz az elso
        {
        uj->kov = elso;
        elso = uj;
        }
    else //egyeb esetben vagy a kozepere vagy a vegere kell beszurnunk az uj elemet
    {
        DicsLista *futo, *lemarado;
        //a forciklus addig megy a láncolt listában amig vagy meg nem talalja az elso olyan elemet aminek a pontszama kisebb mint az uj elem pontszama
        //vagy amig el nem er a lista vegehez
        for (futo = elso; futo != NULL && futo->pontszam >= ujpont; futo = futo->kov)
        {
            lemarado = futo;
        }
        //amikor megtalalta hogy hova kell beszurni akkor be is szurja
        uj->kov = futo;
        lemarado->kov = uj;
    }
    //visszater a lista elejere mutato pointerrel
    return elso;
}
// a fv egy lista elejere mutato pointert var es kiiratja a lista elso 5 elemet
void dicslistakiiratas(DicsLista *eleje)
{

    DicsLista *mozgo;
    int db=1;   //a db valtozoval tartjuk szamon eddig hany elemet irattunk ki
    //a forciklus vegigmegy a lista elemein
    for (mozgo = eleje; mozgo != NULL; mozgo = mozgo->kov)
    {
        if(db<=5)
        {
            //kiiratas
            printf("                    %d.: %s - %d \n",db, mozgo->nev,mozgo->pontszam);
            db++;   //noveljuk a szamlalot
        }
    }

}
//felszabaditja a listat, valamint az abban dinamikusan tarolt stringeket is
void dicslista_free(DicsLista *eleje)
{
    DicsLista *iter = eleje;
    while (iter != NULL) //a while ciklus a lista utolso elemeig megy
    {
        DicsLista *kov = iter->kov;
        free(iter->nev); //a dinamikusan tarolt nev felszabaditasa
        free(iter); //a listaelem felszabaditasa
        iter = kov;
    }
}
//A fv egy stringet var és visszater egy pointerrel ami egy dinamikusan tarolt memoria teruletre mutat, ahol a megadott string talalhato
char* dinamikusantarolas(char *szo)
{
    char *ptr = malloc((strlen(szo)+1)*sizeof(char)); //lefoglaljuk a stringet a memorait
    strcpy(ptr, szo);
    return ptr; // visszaterunk a pointerrel
}
//a fv egy nevet es egy pontzsamot var
//visszateresi erteke 0 ha nem sikerult menteni az eredmenyt
//1 ha sikerult
int eredmenymentes(char nev[50],int pontszam)
{

    FILE *fp; // fájl mutató (file pointer/handle)
    fp = fopen("dicsoseg_lista.txt", "a"); // megnyitás
    fprintf(fp,"%s %d\n",nev,pontszam);
    fclose(fp);

    fp = fopen("dicsoseg_lista.txt", "a"); // megnyitás ellenorzes celjabol
    if(fp==NULL) return 0;
    fclose(fp);
    return 1;

}

