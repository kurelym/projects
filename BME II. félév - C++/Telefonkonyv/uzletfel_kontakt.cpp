#include "uzletfel_kontakt.h"
#include "memtrace.h"
///Kiirja a kontakt adatait a szabványos kimenetre
void Uzletfel::Kiir() const {
    Kontakt::Kiir();        //kiirja az ososztaly attributumait
    std::cout << "Ceg: " << GetCeg() << std::endl;      //majd a sajat attributumokat
    std::cout << "Beosztas: " << GetBeosztas() << std::endl;
}
/// a kontakt modosítását valósítja meg
void Uzletfel::Modosit() {
    Kiir();                             //kiirja a jelenlegi adatokat, hogy tudjaa a felhasznalo mit modosit
    Kontakt::Modosit();                 //meghivja az os osztallyal kozos attributomok modositasat
    std::cout << "Uj Ceg: ";            //bekeri az attributumok uj erteket
    std::cin >> std::ws;                //eldobjuk a whitespaceket
    std::getline(std::cin,ceg);
    std::cout << "Uj Beosztas: ";
    std::cin >> std::ws;
    std::getline(std::cin,beosztas);
}
///azt vizsgalja hogy a parameterkent kapott stringet tartalmazza e
///a kontakt bármely attributuma
bool Uzletfel::BenneVanE(const std::string& szo) const {
    //megnezi az ososztaly attributumait eloszor
    //majd a Uzletfel osztaly sajat attributumait ellenorzi
    return Kontakt::BenneVanE(szo) || (ceg.find(szo) != std::string::npos) || (beosztas.find(szo) != std::string::npos);
}
///kiirja a Kontakt adatait a parameterkent kapott file-ba.
void Uzletfel::KiirToFile(std::ofstream& file) const {
    file << "U;";                                   //kiirja a kontat tipusanak azonositojat
    Kontakt::KiirToFile(file);                      //kiirja a fajlba az ososztaly adattagjait
    file << "," << ceg << "," << beosztas;          //majd a sajat attributumokat vesszovel elvalasztva
}
///eltarolja a kontakt adatait egy fajlbol beolvasott sorbol
void Uzletfel::BeolvasFromFile(const std::string& sor) {
    std::stringstream ss(sor);  //a stringstream-re tesszuk az atvett sort
    std::string adat;

    std::getline(ss, adat, ';'); // KontaktAz
    std::getline(ss, adat, ';'); // K

    std::getline(ss, adat, ','); // Nev
    nev = adat;

    std::getline(ss, adat, ','); // Telefonszam
    telefonszam =adat;

    std::getline(ss, adat, ','); // Ceg
    ceg = adat;

    std::getline(ss, adat, ','); // Beosztas
    beosztas = adat;
}
///klónozás az operator= megvalósításához
///es a helyes memoriakezeleshez
Uzletfel* Uzletfel::clone(){
    return new Uzletfel(*this);//visszater egy uj Uzletfel objektumra mutato pinterrel
}
