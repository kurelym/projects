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

    ///Konstruktor a beolvas�shoz
    Barat(int a) :Kontakt(1){               //Eloszor megh�vjuk az Os osztaly beolvasasahoz szukseges konstruktort
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
        return 'B';     //B - barat kontakt t�pus nev, telefonszam �s becenev valamint privat szam attributumok
    }
    ///kl�noz�s az operator= megval�s�t�s�hoz
    ///es a helyes memoriakezeleshez
    Barat* clone();

    ///Kiirja a kontakt adatait a szabv�nyos kimenetre
    void Kiir() const;

    /// a kontakt modos�t�s�t val�s�tja meg
    void Modosit();

    ///azt vizsgalja hogy a parameterkent kapott stringet tartalmazza e
    ///a kontakt b�rmely attributuma
    bool BenneVanE(const std::string& szo) const;

    ///kiirja a Kontakt adatait a parameterkent kapott file-ba.
    void KiirToFile(std::ofstream& file) const;

    ///eltarolja a kontakt adatait egy fajlbol beolvasott sorbol
    void BeolvasFromFile(const std::string& sor) override;
};

#endif // BARAT_KONTAKT_H
