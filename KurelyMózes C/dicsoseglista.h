#ifndef DICSOSEGLISTA_H
#define DICSOSEGLISTA_H
//a dicsoseglista elemetit lancolt listaban tarolom
//a lista elemeinek a strukturaja a kovetkezo:
typedef struct DicsLista {

    char *nev;              //a felhasznalo neve
    int pontszam;           //a felhasznalo elert pontszama
    struct DicsLista *kov;   //a lista kovetkezo elemere mutato pointer
} DicsLista;

//ez a fv olvassa be a dicsoseg_lista.txt.ben található adatokat
//majd ezeket eltárolja az erre a célra készült láncolt listában
//a fv az elso elemre mutato pointert adja visszateresiertekul
DicsLista* dicsbeolvasas(void);

//ennek a fvnek a segitsegevel epitjuk fel a lancolt listat
//a fv a lista elso elemet, az uj elem nev valotozojat valamint a pontszam valtozojat varja
//visszateresi erteke pedig a lista elso elemere mutato pointer
//az elmet olyan helyre szurja be a fv hogy az elemek csokkeno sorrendben legyenek a listaban
DicsLista *beszur(DicsLista *elso,char  *ujnev, int ujpont);

// a fv egy lista elejere mutato pointert var es kiiratja a lista elso 5 elemet
void dicslistakiiratas(DicsLista *eleje);

//a fv egy korabban mar deifinialt tipusu lista elso elemere mutato pointert var
//a fv felszabaditja a listat, valamint az abban dinamikusan tarolt stringeket is
void dicslista_free(DicsLista *eleje);

//A fv egy stringet var és visszater egy pointerrel ami egy dinamikusan tarolt memoria teruletre mutat, ahol a megadott string talalhato
char* dinamikusantarolas(char *szo);

//a fv egy nevet es egy pontzsamot var
//visszateresi erteke 0 ha nem sikerult menteni az eredmenyt
//1 ha sikerult
int eredmenymentes(char nev[50],int pontszam);
#endif // DICSOSEGLISTA_H

