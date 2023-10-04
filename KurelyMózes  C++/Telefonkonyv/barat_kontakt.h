#ifndef BARAT_KONTAKT_H
#define BARAT_KONTAKT_H

#include <iostream>
#include <string>
#include <fstream>
#include <sstream>
#include "kontakt.h"
///a Kontakt osztaly leszarmazott osztalya
///ugyan ugy van neve es telefonszama
///de e mellett van beceneve es privat telefonszama
class Barat : public Kontakt {
private:
    std::string becenev;        //a kontakt beceneve
    std::string privat_szam;    //a kontakt privat telefonszama

public:
    ///Konstruktor amivel beallithatoak az Ososztaly attributumai
    ///valamint a becenev es a privat szam attributumokat
    Barat(const std::string& nev="", const std::string& telefonszam="", const std::string& becenev="", const std::string& privat_szam="")
        : Kontakt(nev, telefonszam), becenev(becenev), privat_szam(privat_szam){}

    ///Konstruktor a beolvasáshoz
    Barat(int a) :Kontakt(1){               //Eloszor meghívjuk az Os osztaly beolvasasahoz szukseges konstruktort
        std::cout<<"Becenev: ";
        std::cin >> std::ws;                //white spacek eldobasa
        std::getline(std::cin,becenev);
        std::cout<<"Privat telefonszam: ";
        std::cin >> std::ws;
        std::getline(std::cin,privat_szam);
    }
    ///lekerdezo fv a kontakt becenevere
    std::string GetBecenev() const {
        return becenev;
    }
    ///lekerdezo fv a kontakt privat telefonszamara
    std::string GetPrivatSzam() const {
        return privat_szam;
    }
    ///visszaadja hogy milyen tipusu a kontakt
    char GetTipus() const{
        return 'B';     //B - barat kontakt típus nev, telefonszam és becenev valamint privat szam attributumok
    }
    ///klónozás az operator= megvalósításához
    ///es a helyes memoriakezeleshez
    Barat* clone();

    ///Kiirja a kontakt adatait a szabványos kimenetre
    void Kiir() const;

    /// a kontakt modosítását valósítja meg
    void Modosit();

    ///azt vizsgalja hogy a parameterkent kapott stringet tartalmazza e
    ///a kontakt bármely attributuma
    bool BenneVanE(const std::string& szo) const;

    ///kiirja a Kontakt adatait a parameterkent kapott file-ba.
    void KiirToFile(std::ofstream& file) const;

    ///eltarolja a kontakt adatait egy fajlbol beolvasott sorbol
    void BeolvasFromFile(const std::string& sor) override;
};

#endif // BARAT_KONTAKT_H
