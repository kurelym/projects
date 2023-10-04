#ifndef SZOTAR_KEZELES_H
#define SZOTAR_KEZELES_H
#include <stdio.h>
#include <string.h>
#include <stdbool.h>
//a szotar elemetit lancolt listaban tarolom
//a lista elemeinek a strukturaja a kovetkezo:
typedef struct SzoLista
{
    char *szo;              // pointer a szot dinamikusan tarolo memoriateruletre
    int hossz;              //a szo hossza
    struct SzoLista *kov;   //a lista kovetkezo elemere mutato pointer
} SzoLista;

//ez a fv olvassa be a szavak.txt-ben tarolt szavakat
//amik 4 es 10 kozotti karakterhosszal rendelkeznek
SzoLista* beolvasas(void);

//Ennek a fv-nek a segítségével építjük fel a listát,
void lista_elejere_beszur(SzoLista **eleje,char  *ujszo);

//a fv egy lista elejere mutato pointert var
//visszateresi erteke pedig egy pointer ami a lista egy velelenszeruen kivalasztott elemere mutat
char* random_szo_listabol(SzoLista * eleje);

//a fv a parameterkent kapott lista elemei kozul kitorli azokat amelyek tartalmazzak a szinten parameterkent kapott karaktert
//nincs visszateresi erteke hiszen parameterkent veszi at a megvaltoztatando lista elejere mutato pointert
void eltavolitas_karakter_alapjan(SzoLista **eleje, char s);

//felszabadítja a lancolt listankat
//valamint az abban dinamikusan tarolt stringeket is
void lista_free(SzoLista *eleje);

//felszabadítja a lancolt listankat
//de az abban dinamikusan tarolt stringeket nem
void selected_lista_free(SzoLista *eleje);

//a fv egy SzoLista elejére mutato pointert var valamint egy 4 es 10 kozotti szamot
//visszateresi erteke egy olyan pointer ami a kapott listabol kivalogatott, adott hosszu szavakat tartalmazo lista elso elemere mutat
SzoLista * random_hosszu_szavak_select(SzoLista * eredeti);

//a fv egy lista elejere mutato pointert var, és egy stringet, amit torolnie kell a listabol
//visszaterési erteke a módósított lista elejére mutato pointer
void elemtorles(SzoLista** eleje,char* torlendo);

//ez a fv egy szamot var
//visszateresi erteke pedig egy random szam 1 es a megadott szamo kozott
int random_szam_modulussal(int mod);

//A fv egy lista elejere mutato pointert var és visszateresi erteke a lista elemszama
int lista_hossz(SzoLista *eleje);

//a fv egy lista elso elemere mutato pointert var, valamint egy karaktert
//a fv visszateresi erteke igza ha az osszes olyan szot eltavolitva a listabol amiben az a karakter van nem ures listat kapunk
//ellenkezo esetben hamis
bool torolhetoe(SzoLista *eleje, char s);

//a fv egy szot var, egy karaktert, es egy olyan tombot amiben 0-sok es 1-esek vannak
//a fv igazat ad vissza ha a szoban csak azon a helyen van a megadott karakter, ahol a szamtomben 1-esek vannak
//ahol pedig a szamtombben a megfelelo helyen 0-s van ott a megadott karakteren kivul barmilyen karakter lehet
bool helyteszt(char *s,int t[10],char k);

//ez a fv a parameterkent kapott lista elemei kozul kitorli azokat amelyek nem csak ugyan azon a helyen tartalmazzak a megadott karaktert mint a szinten parameterkent kapott szamtomben az 1-esek vannak
void eltavolitas_karakterhely_alapjan(SzoLista **eleje, int T[10],char k);

// a fv egy lista elejére mutató pointert vár és kiíratja a lista összes elemét
void lista_kiiratas(SzoLista *eleje);

#endif // SZOTAR_KEZELES_H
