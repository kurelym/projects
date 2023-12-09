#ifndef KONTAKT_H
#define KONTAKT_H

#include <iostream>
#include <string>
#include <fstream>
#include <sstream>

///Olyan os osztaly melyben nevet es telefonszamot lehet tarolni.
class Kontakt {
protected:
    std::string nev;            //a kontakt neve
    std::string telefonszam;    //a kontakt telefonszama

public:

    ///Konstruktor amivel beallithato a kontakt neve es telefonszama
    Kontakt(const std::string& nev="", const std::string& telefonszam="")
    :nev(nev), telefonszam(telefonszam){}

    ///Konstruktor a beolvasáshoz
    Kontakt(int a){
        std::cout << "Nev: ";
        std::cin >> std::ws;                   //white spacek eldobasa
        std::getline(std::cin, nev);           //mivel a nev tartalmazhat szokozt ezert getline-al olvasunk be
        std::cout << "Telefonszam: ";
        std::cin >> std::ws;
        std::getline(std::cin, telefonszam);
    }

    ///lekerdezo fv a kontakt nevere
    std::string GetNev() const{
        return nev;
    }
    ///lekerdezo fv a kontakt telefonszamara
    std::string GetTelefonszam() const{
        return telefonszam;
    }
    ///visszaadja hogy milyen tipusu a kontakt
    virtual char GetTipus() const{
        return 'K';     //K - sima kontakt típus csak nev es telefonszam
    }

    ///klónozás az operator= megvalósításához
    ///es a helyes memoriakezeleshez
    virtual Kontakt* clone();

    ///Kiirja a kontakt adatait a szabványos kimenetre
    virtual void Kiir() const;

    /// a kontakt modosítását valósítja meg
    virtual void Modosit();

    ///azt vizsgalja hogy a parameterkent kapott stringet tartalmazza e
    ///a kontakt bármely attributuma
    virtual bool BenneVanE(const std::string& szo) const;

    ///kiirja a Kontakt adatait a parameterkent kapott file-ba.
    virtual void KiirToFile(std::ofstream& file) const;

    ///eltarolja a kontakt adatait egy fajlbol beolvasott sorbol
    virtual void BeolvasFromFile(const std::string& sor);

    ///operator az abc rendezeshez
    bool operator>(const Kontakt& masik) const {
        return nev > masik.nev;
    }
    ///operator az abc rendezeshez
    bool operator<(const Kontakt& masik) const {
        return nev < masik.nev;
    }
    ///virtualis destruktor
    virtual ~Kontakt(){}
};
#endif // KONTAKT_H
