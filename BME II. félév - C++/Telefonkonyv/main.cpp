#include <iostream>
#include "telefonkonyv.h"
#include "menu.h"
#include "memtrace.h"
///az telefonkonyv alkalmazas menujenek futtatasahoz
int main() {

    Telefonkonyv be;                        //A telefonkonyv letrehozasa
    be.BeolvasFajlbol("telefonkonyv.txt");
    Telefonkonyv telefonkonyv=be;           //masolo konstruktor tesztelese


    Menu menu;                              //Menu letrehozasa 0 kezdoallapottal

    while (menu.allapot != 6) {//ciklus a kilepesi allapotig
        switch (menu.allapot) { //switch - case a menu alloptai kozott
            case 0:
                menu.fomenu();                  //fomenu megjelenites
                break;
            case 1:
                menu.kereses(telefonkonyv);     //keresesi menu megjelenites
                break;
            case 2:
                menu.ujkontakt(telefonkonyv);   //uj kontakt hozzadasa menupont megjelenites
                break;
            case 3:
                menu.listazas(telefonkonyv);    //listazas menu megjelenites
                break;
            case 4:
                menu.modositas(telefonkonyv);   //modositas menu megjelenites
                break;
            case 5:
                menu.torles(telefonkonyv);      //torles menu megjelenites
                break;

            default:
                menu.fomenu();
                break;
        }
    }
    menu.kilepes();     //kijelentkezesi menu megjelenites
    Telefonkonyv ki=be;
    ki=telefonkonyv;    //operator= teszteles
    ki.KiirFajlba("telefonkonyv.txt");  //az uj adatok mentese fajlba
}
