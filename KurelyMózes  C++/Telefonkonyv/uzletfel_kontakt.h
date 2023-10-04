#ifndef UZLETFEL_KONTAKT_H

#include <iostream>
#include <string>
#include <fstream>
#include <sstream>
#include "kontakt.h"
///a Kontakt osztaly leszarmazott osztalya
///ugyan ugy van neve es telefonszama
///de e mellett van ceg es beosztas attributuma
class Uzletfel : public Kontakt {
private:
    std::string ceg;        //a kontakt ceg attributuma
    std::string beosztas;    //a kontakt beosztas attributuma

public:
    ///Konstruktor amivel beallithatoak az Ososztaly attributumai
    ///valamint a ceg es a beosztas
    Uzletfel(const std::string& nev="", const std::string& telefonszam="", const std::string& ceg="", const std::string& beosztas="")
        : Kontakt(nev, telefonszam), ceg(ceg), beosztas(beosztas) {}

    ///Konstruktor a beolvasáshoz
    Uzletfel(int a): Kontakt(1){        //Eloszor meghívjuk az Os osztaly beolvasasahoz szukseges konstruktort
        std::cout<<"Vallalat: ";
        std::cin >> std::ws;            //white spacek eldobasa
        std::getline(std::cin,ceg);
        std::cout<<"Beosztas: ";
        std::cin >> std::ws;
        std::getline(std::cin,beosztas);
    }
    ///lekerdezo fv a kontakt ceg attributumara
    std::string GetCeg() const {
        return ceg;
    }
    ///lekerdezo fv a kontakt beosztas attributumara
    std::string GetBeosztas() const {
        return beosztas;
    }
    ///visszaadja hogy milyen tipusu a kontakt
    char GetTipus() const{
        return 'U';     //U - Uzletfel kontakt tipus nev, telefonszam és ceg valamint beosztas attributumok
    }
    /// a kontakt modosítását valósítja meg
    void Modosit();
    ///Kiirja a kontakt adatait a szabványos kimenetre
    void Kiir() const;

    ///azt vizsgalja hogy a parameterkent kapott stringet tartalmazza e
    ///a kontakt bármely attributuma
    bool BenneVanE(const std::string& szo) const;

    ///kiirja a Kontakt adatait a parameterkent kapott file-ba.
    void KiirToFile(std::ofstream& file) const;

    ///eltarolja a kontakt adatait egy fajlbol beolvasott sorbol
    void BeolvasFromFile(const std::string& sor);

    ///klónozás az operator= megvalósításához
    ///es a helyes memoriakezeleshez
    Uzletfel* clone();
};
#endif // UZLETFEL_KONTAKT_H
