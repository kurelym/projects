#include <time.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <stdbool.h>
#include "debugmalloc.h"
#include "dicsoseglista.h"
#include "szotar_kezeles.h"
//ez a fv olvassa be a szavak.txt-ben tarolt szavakat
//amik 4 es 10 kozotti karakterhosszal rendelkeznek
SzoLista* beolvasas(void)
{
    SzoLista *eleje=NULL;   //letrehozzuk a lancolt lista elso elemet
    FILE * fPointer;        //megnyitjuk a file-t olvasasra
    fPointer=fopen("szavak.txt","r");
    if (fPointer == NULL)
    {
        return NULL;    // nem talalhato a fajl -> NULL pointert returnolunk
    }
    else
    {
        char line[100];
        while(!feof(fPointer)) //amig vege nincs a fajlnak
        {
            fscanf(fPointer,"%s",line);
            if(strlen(line)<=10 && strlen(line)>=4 ) // a szotarbol nekünk csak a 4 és 10 karakterhossz közötti szavakat kell eltárolnunk
            {
                char *uj=dinamikusantarolas(line); //dinamikusan eltaroljuk az uj szot
                lista_elejere_beszur(&eleje,uj);   //hozzafuzzuk a listahoz
            }
        }
        fclose(fPointer);   //bezarjuk az olvasasra megnyitott file-t
        return eleje;       //a fv visszater a lista elso elemere mutato pointerrel
    }

}
//Ennek a fv-nek a segítségével építjük fel a listát,
void lista_elejere_beszur(SzoLista **eleje,char  *ujszo)
{
    SzoLista *u;
    u = (SzoLista*) malloc(sizeof(SzoLista));   //dinamikusan helyet foglalunk a lista uj elemenek
    u->kov = *eleje; //az uj elem kov-je az addigi elso elemre mutat
    u->hossz=strlen(ujszo);
    u->szo = ujszo;
    *eleje=u; //a lista eleje az uj elem lesz
}
//a fv egy lista elejere mutato pointert var
//visszateresi erteke pedig egy pointer ami a lista egy velelenszeruen kivalasztott elemének szavára mutat
char* random_szo_listabol(SzoLista * eleje)
{
    int hely=random_szam_modulussal(lista_hossz(eleje)); //veletlenszeruen valasztunk egy szamot 1 es a lista hossza kozott
    SzoLista *mozgo;
    int db=0;
    //a forciklus vegmegy a lista elemein
    for (mozgo = eleje; mozgo != NULL; mozgo = mozgo->kov)
    {
        db++;
        if(db==hely) return mozgo->szo; //visszateritjek a veletlenszeruen kivalasztott szamadik elemet a listanak
    }
    return NULL;
}
//a fv a parameterkent kapott lista elemei kozul kitorli azokat amelyek tartalmazzak a szinten parameterkent kapott karaktert
//nincs visszateresi erteke hiszen parameterkent veszi at a megvaltoztatando lista elejere mutato pointert
void eltavolitas_karakter_alapjan(SzoLista **eleje, char s)
{
    SzoLista *mozgo=*eleje;
    //a while ciklus addig megy amig el nem er a lista vegere, azaz minden elemet megvizsgal
    while(mozgo != NULL)
    {
        //ha a szo tartalmazza a megadott karaktert
        if(strchr(mozgo->szo,s)!=NULL)
        {
            elemtorles(&*eleje,mozgo->szo); //ha igen akkor toroljuk a listabol
            mozgo=*eleje; //es ujra elindul a lista elejereol nehogy veletlen kihagyjon egyet is
        }
        else mozgo = mozgo->kov; //ha nem akkor megy tovabb a kovetkezo elemre
    }
}
//felszabadítja a lancolt listankat
//valamint az abban dinamikusan tarolt stringeket is
void lista_free(SzoLista *eleje)
{
    SzoLista *iter = eleje;
    while (iter != NULL) //a lista vegeig megy a ciklus
    {
        SzoLista *kov = iter->kov;
        free(iter->szo);    // a szo felszabaditasa
        free(iter);         //az elem felszabaditasa
        iter = kov;
    }
}
//felszabadítja a lancolt listankat
//de az abban dinamikusan tarolt stringeket nem
void selected_lista_free(SzoLista *eleje)
{
    SzoLista *iter = eleje;
    while (iter != NULL) //a lista vegeig megy a ciklus
    {
        SzoLista *kov = iter->kov;
       // free(iter->szo);    // a szo felszabaditasa
        free(iter);         //az elem felszabaditasa
        iter = kov;
    }
}
//a fv egy SzoLista elejére mutato pointert var valamint egy 4 es 10 kozotti szamot
//visszateresi erteke egy olyan pointer ami a kapott listabol kivalogatott, adott hosszu szavakat tartalmazo lista elso elemere mutat
SzoLista * random_hosszu_szavak_select(SzoLista * eredeti)
{

    int h=rand()%6+4; // 4 es 10 kozotti szam generalasa
    SzoLista *mozgo;
    SzoLista *valogatottak=NULL;  //az uj lista elso elemere mutato pointer
    //a ciklus vegigmegy az eredeti lista osszes elemen
    for (mozgo = eredeti; mozgo != NULL; mozgo = mozgo->kov)
    {
        if(mozgo->hossz==h) //ha a szo hossza megegyezik az altalunk generalt hosszal
        {
            lista_elejere_beszur(&valogatottak,mozgo->szo); //akkor hozzafuzzuk az uj listahoz
        }
    }
    return valogatottak; // visszaterunk a kivalogatott szavakat tartalmazo lista elso elemere mutato pointerrel
}
//a fv egy lista elejere mutato pointert var, és egy stringet, amit torolnie kell a listabol
//visszaterési erteke a módósított lista elejére mutato pointer
void elemtorles(SzoLista** eleje,char* torlendo)
{
    SzoLista *lemarado = NULL;
    SzoLista *mozgo = *eleje;
    //addig megy a ciklus amig meg nem talalja a keresett szot
    //vagy amig vege nincs a listanak
    while (mozgo != NULL && strcmp(torlendo,mozgo->szo)!=0)
    {
        lemarado = mozgo;
        mozgo = mozgo->kov;
    }

    /* megtalált elem törlése */
    if (mozgo == NULL) /* nincs ilyen elem */
    {
        /* nincs teendõ */
    } else if(lemarado == NULL) /* az elsõ elemet kell törölni */
            {
            SzoLista *ujeleje=mozgo->kov;
            *eleje=ujeleje;
            free(mozgo);
            } else {                       /* a közepérol/végérol törlünk */
                lemarado->kov = mozgo->kov;
                free(mozgo);
                }
}
//ez a fv egy szamot var
//visszateresi erteke pedig egy random szam 1 es a megadott szamo kozott
int random_szam_modulussal(int mod)
{
    srand(time(0));
    return rand()%mod+1;
}
//A fv egy lista elejere mutato pointert var és visszateresi erteke a lista elemszama
int lista_hossz(SzoLista *eleje)
{
    int db=0;
    SzoLista *mozgo;
    //a ciklus vegigmegy a lista elemein es minden egyes elemnel noveli a db valtozot
    for (mozgo = eleje; mozgo != NULL; mozgo = mozgo->kov)
    {
         db++;
    }
    return db;
}
//a fv egy lista elso elemere mutato pointert var, valamint egy karaktert
//a fv visszateresi erteke igza ha az osszes olyan szot eltavolitva a listabol amiben az a karakter van nem ures listat kapunk
//ellenkezo esetben hamis
bool torolhetoe(SzoLista *eleje, char s)
{
    int db=0;
    SzoLista *mozgo=eleje;
    while(mozgo != NULL)
    {
        if(strchr(mozgo->szo,s)!=NULL)
        {
            db++; //noveljuk a db valtozot ha a lista egy szava tartalmazza a karakter kis/nagybetus valtozatat
        }
        mozgo = mozgo->kov;
    }
    if(db==lista_hossz(eleje)) return false; //ha az osszes karakter tartalmazza akkor nem torolheto a lista az alapjan a karakter alapjan
    else return true; //ha akar egy elem nem tartalmazza akkor mar torolheto
}
//a fv egy szot var, egy karaktert, es egy olyan tombot amiben 0-sok es 1-esek vannak
//a fv igazat ad vissza ha a szoban csak azon a helyen van a megadott karakter, ahol a szamtomben 1-esek vannak
//ahol pedig a szamtombben a megfelelo helyen 0-s van ott a megadott karakteren kivul barmilyen karakter lehet
bool helyteszt(char *s,int t[10],char k)
{
    int db=0;
    for(int i=0;i<strlen(s);i++)
    {
        if(s[i]==k && t[i]==1) db++; //ha a karakter megegyezik, es a tomben is 1-es van nnoveljuk a db-ot
        if(s[i]!=k && t[i]==0)  db++; //ha a karakter nem egyezik es a tomben is 0-s van akkor is noveljuk a db-ot
    }
    if(db==strlen(s)) return true; //ha a szo minden karaktere teljesitette valamelyik feltetelt akkor megfelelo a szo
    else return false; //ha nem akkor nem megfelelo
}
//ez a fv a parameterkent kapott lista elemei kozul kitorli azokat amelyek nem csak ugyan azon a helyen tartalmazzak a megadott karaktert mint a szinten parameterkent kapott szamtomben az 1-esek vannak
void eltavolitas_karakterhely_alapjan(SzoLista **eleje, int T[10],char k)
{
    SzoLista *mozgo=*eleje;
    while(mozgo != NULL)
    {
        // ha a szo nem felel meg a helytesztnek akkor eltavolitjuk a szot a listabol
        if(!helyteszt(mozgo->szo,T[10],k))
        {
            elemtorles(&*eleje,mozgo->szo);
            mozgo=*eleje;
        }
        else mozgo = mozgo->kov;
    }
}
// a fv egy lista elejére mutató pointert vár és kiíratja a lista összes elemét
void lista_kiiratas(SzoLista *eleje)
{
    SzoLista *mozgo;
    int db=0;
    for (mozgo = eleje; mozgo != NULL; mozgo = mozgo->kov)
    {
        printf("%s ", mozgo->szo);
        db++;
        if(db%12==0) printf("\n");
    }

}
