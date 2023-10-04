#ifndef MEGJELENITES_H
#define MEGJELENITES_H
#include "dicsoseglista.h"

//a fv megjeleniti a menut es visszater a felhasznalo alltal valasztott menupont ertekevel
//0-kilepes a programbol. 1- uj jatek, 2- dicsoséglista
char menu(void);

// A fv egy szamot var, es az akasztofa annyiadik fazisat fogja kirajzolni (1-tol 12-ig)
//12 utan ugyan azt a vegso allapotot adja vissza
void akasztofa(int fazis);

//ez a fv a lancolt listaban tarolt dicsoseglista elso elemere mutato pointert varja
//megjeleniti a dicsoseglista elso 5 elemet
void dicsoseg_tabla_megjelenites(DicsLista *eleje);

//ez a fv jeleniti meg a kepernyon a dicsoseglista menupontot ha az meg ures/nem talalhato
void dicsoseg_tabla_ures_megjelenites(void);

//ez a fv jeleniti meg a konzolon az uj jatek menupontot ha a szavak.txt-nem talalhato
void szavak_nem_talalhatoak_megjelenites(void);

//a fv megjeleniti a jatek kezdetekor a fejlecet
void jatekfejlec(void);

//a fv ket karaktertombot var
//az egyik a szo, a masik pedig a megengedett karakterek
//a fv kiirja a szo karakteret ha az adott karakter megtalalhato a megengedett karakterek tomben
//ha nem talalhato meg akkor pedig '_' karaktert ir helyette
void eltalatakkiiratasas(char*s,char*karakterek);

//ez a fv ket karaktertombot var, egyikben a helyes tippekkel, masikban a rossz tippekkel
//ezeket jeleniti meg
void rossz_helyes_tippek(char rossz[100],char helyes[10]);

//ez a fv jeleniti meg a keprenyon a megoldast ha a jatekos feladta ajatekot
//egy stringet var ami a megjelenitendo megoldas
void jatekvege_feladta(char *kivalasztott);

//ez a fv irja felul a konzol tartalmat ha a jatekos az osszes betut kitalalta a szobol
//a kitalalt szot es a jatekos pontszamat varja ugyanis itt tudja menteni a pontszamot is
void jatekvege_kitalalta_pontszammentes(char *kivalasztott,int pontszam);
#endif // MEGJELENITES_H
