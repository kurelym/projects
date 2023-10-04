#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <stdbool.h>
#include "jatek.h"
#include "debugmalloc.h"
#include "megjelenites.h"
#include "szotar_kezeles.h"

//ez a fv az osszes szot tartalmazo listat varja
//ez allitja elo az uj jatekot
void ujjatek(SzoLista *eleje)
{
    SzoLista *selected=random_hosszu_szavak_select(eleje);
    eltavolitas_karakter_alapjan(&selected,'-');
    int feladas=0; //a feladas erteke 1 lesz ha a felhasznalo be kivanja fejezni a jatekot az elott hogy kitalalta  a megfejtest
    int fazis=0; //ezen a szamlalon tartja a program az akasztofa kirajzolasanak melyik fazisa kovetkezik
    int helyesdb=0; //hany karaktert talalt ki a felhasznalo eddig

    char rossz[100]={0}; //ebben a tomben a rossz tippeket tarolja a program
    char helyes[10]={0}; //ebben a tomben a helyes tippeket tarolja a program
    char tipp; //ebben a valtozoban tarolodik el az eppen aktualis tipp
    char *kivalasztott=random_szo_listabol(selected);//kivalasztunk egy random szot a listabol
    while(!megfejtettede(kivalasztott,helyes) && feladas!=1)
    {
        jatekfejlec();
        //lista_kiiratas(selected);
        akasztofa(fazis); //kirajzolja a program az akasztofa aktualis allasat
        if(fazis!=0) rossz_helyes_tippek(rossz,helyes);
        eltalatakkiiratasas(kivalasztott,helyes); //ki irja a program az eddig eltalat karaktereket
        printf("\nÚj tipp: ");
        scanf(" %c",&tipp);
        if(tipp=='0') feladas=1; //0-s gomb megnyomasaval lehet feladni a jatekot
        if(torolhetoe(selected,tipp)) //ha az osszes olyan szot amiben a tipp betu megtalalhato torolnenk akkor ures lenne a lista?
        {
            rossz[fazis++]=tipp; //a rossz tomben eltaroljuk a tippet //noveljuk a fazist
            if(fazis==100) feladas=1;
            eltavolitas_karakter_alapjan(&selected,tipp); //eltavolitjuk a selected listabol az osszes olyan szot ami tartalmazza a betut
            if(strchr(kivalasztott,tipp)!=NULL) //a a jelenlegi kivalasztott is tartalmazza akkor uj szot kell valasztani
            {
                kivalasztott=random_szo_listabol(selected);
            }
        }
        else
        {
            helyes[helyesdb++]=tipp; //a tippet eltaroljuk a helyes tomben
            int helytomb[10]={0}; //inicializaljuk a helytombot
            helytombfeltolto(helytomb,kivalasztott,tipp); //feltoltjuk a megfelel szamu 1-essel
            SzoLista *mozgo=selected;
            while(mozgo!= NULL) //vegigmegyunk a lista elemein, ami nem felel meg a helytomb altal eloirtakhoz akotat toroljuk a listabol
            {
                int db=0;
                for(int i=0;i<strlen(mozgo->szo);i++)
                {
                    if((mozgo->szo[i]==tipp )&& helytomb[i]==1) db++;
                    if((mozgo->szo[i]!=tipp ) && helytomb[i]==0)  db++;
                }
                if(db!=strlen(mozgo->szo))
                {
                    elemtorles(&selected,mozgo->szo);
                    mozgo=selected;
                }
                if(db==strlen(mozgo->szo))
                {
                    mozgo = mozgo->kov;
                }

            }
        }
    }
    int pontszam=(11-strlen(kivalasztott))*helyesdb-(fazis)+10; //kiszamitjuk a pontszamot
    if(feladas==1)
    {
        jatekvege_feladta(kivalasztott); // ha a jatekos feladta
    }
    else jatekvege_kitalalta_pontszammentes(kivalasztott,pontszam); // ha kitalalta aszot
    selected_lista_free(selected);
}
//a fv ket karaktertombot var
//az egyik a szo, a masik pedig a megengedett karakterek
//a fv visszateresi erteke true ha a parameterkent atvett szo osszes karaktere megtalalhato
//a paramterekent atvett helyes tombben
//false ha nem
bool megfejtettede(char *kivalasztott,char helyes[10])
{
    int eltalalt=0;
    for(int i=0;i<10;i++)
    {
        for(int j=0;j<strlen(kivalasztott);j++)
        {
            if(helyes[i]==kivalasztott[j]) eltalalt++;
        }
    }
    if(eltalalt>=strlen(kivalasztott)) return true;
    else return false;
}
//ez a fv szam egy tombot, egy szot, es egy karaktert var
//ahanyadik karakterek a szoban megegyeznek a parameterkent megadott karakterrel,
//a szamtomb annyiadik elemei 1-esek leszenk
void helytombfeltolto (int *tomb,char *kivalasztott, char tipp)
{
    int h=strlen(kivalasztott);
    for(int i=0;i<h;i++)
    {
        if(kivalasztott[i]==tipp) tomb[i]=1;
        else tomb[i]=0;
    }
}
