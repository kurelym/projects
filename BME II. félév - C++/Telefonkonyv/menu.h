#ifndef MENU_H
#define MENU_H
#include <iostream>
#include <cstdlib>
#include <string>
#include "telefonkonyv.h"
#include "pred.h"
///Menu osztaly a telefonkönyv alkalmazas iranyitasahoz
struct Menu {
    int allapot;
public:
    Menu(int all=0): allapot(all){}

    ///a fomenu megjeleniteset teszi lehetove
    void fomenu();

    ///a Kereses menupont megjeleniteset teszi lehetove
    ///a parameterkent atvett telefonkonyvel dolgozik
    void kereses(Telefonkonyv& telefonkonyv);

    ///az uj kontakt hozzaadasa menupont megjeleniteset teszi lehetove
    ///a parameterkent atvett telefonkonyvel dolgozik
    void ujkontakt(Telefonkonyv& telefonkonyv);

    ///a Kontaktok listázása menupont megjeleniteseert felel
    ///a parameterkent atvett telefonkonyvel dolgozik
    void listazas(Telefonkonyv& telefonkonyv);

    ///a Meglevo kontakt modositasa menupont megjeleniteseert felel
    ///a parameterkent atvett telefonkonyvel dolgozik
    void modositas(Telefonkonyv& telefonkonyv);

    ///Meglevo kontaktok torleset megvalosito menupont
    ///a parameterkent atvett telefonkonyvel dolgozik
    void torles(Telefonkonyv& telefonkonyv);

    ///Programbol kilepteto menupont
    void kilepes();
};

#endif // MENU_H
