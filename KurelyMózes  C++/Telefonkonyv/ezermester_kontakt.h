#ifndef EZERMESTER_KONTAKT_H
#include <iostream>
#include <string>
#include "kontakt.h"
#include <fstream>
#include <sstream>
///a Kontakt osztaly leszarmazott osztalya
///ugyan ugy van neve es telefonszama
///de e mellett van szakma es megjegyzes attributuma
class Ezermester : public Kontakt {
private:
    std::string szakma;        //a kontakt szakma attributuma
    std::string megjegyzes;    //a kontakt megjegyzes attributuma

public:
    ///Konstruktor amivel beallithatoak az Ososztaly attributumai
    ///valamint a szakma es a megjegyzes
    Ezermester(const std::string nev = "" , std::string telefonszam = "" , const std::string szakma = "" , const std::string megjegyzes = "")
        : Kontakt(nev, telefonszam), szakma(szakma), megjegyzes(megjegyzes) {}

    ///Konstruktor a beolvas�shoz
    Ezermester(int a):Kontakt(1){           //Eloszor megh�vjuk az Os osztaly beolvasasahoz szukseges konstruktort
        std::cout<<"Szakma: ";
        std::cin >> std::ws;                //white spacek eldobasa
        std::getline(std::cin,szakma);
        std::cout<<"Megjegyzes: ";
        std::cin >> std::ws;
        std::getline(std::cin,megjegyzes);
    }
    ///lekerdezo fv a kontakt szakma attributumara
    std::string GetSzakma() const {
        return szakma;
    }
    ///lekerdezo fv a kontakt megjegyzes attributumara
    std::string GetMegjegyzes() const {
        return megjegyzes;
    }
    ///visszaadja hogy milyen tipusu a kontakt
    char GetTipus() const{
        return 'E'; //E - Ezermester kontakt t�pus nev, telefonszam �s szakma valamint megjegyzes attributumok
    }
    /// a kontakt modos�t�s�t val�s�tja meg
    void Modosit();

    ///Kiirja a kontakt adatait a szabv�nyos kimenetre
    void Kiir() const;

    ///azt vizsgalja hogy a parameterkent kapott stringet tartalmazza e
    ///a kontakt b�rmely attributuma
    bool BenneVanE(const std::string& szo) const;

    ///kiirja a Kontakt adatait a parameterkent kapott file-ba.
    void KiirToFile(std::ofstream& file) const;

    ///eltarolja a kontakt adatait egy fajlbol beolvasott sorbol
    void BeolvasFromFile(const std::string& sor);

    ///kl�noz�s az operator= megval�s�t�s�hoz
    ///es a helyes memoriakezeleshez
    Ezermester* clone();
};
#endif // EZERMESTER_KONTAKT_H
