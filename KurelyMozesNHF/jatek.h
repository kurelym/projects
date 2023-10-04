#ifndef JATEK_H
#define JATEK_H
#include <stdio.h>
#include <string.h>
#include "szotar_kezeles.h"

//ez a fv az osszes szot tartalmazo listat varja
//ez allitja elo az uj jatekot
void ujjatek(SzoLista *eleje);

//a fv ket karaktertombot var
//az egyik a szo, a masik pedig a megengedett karakterek
//a fv visszateresi erteke true ha a parameterkent atvett szo osszes karaktere megtalalhato
//a paramterekent atvett helyes tombben
//false ha nem
bool megfejtettede(char *kivalasztott,char helyes[10]);

//ez a fv szam egy tombot, egy szot, es egy karaktert var
//ahanyadik karakterek a szoban megegyeznek a parameterkent megadott karakterrel,
//a szamtomb annyiadik elemei 1-esek leszenk
void helytombfeltolto (int *tomb,char *kivalasztott, char tipp);

#endif // JATEK_H
