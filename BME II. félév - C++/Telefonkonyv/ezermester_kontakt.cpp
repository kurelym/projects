#include "ezermester_kontakt.h"
#include "memtrace.h"
///Kiirja a kontakt adatait a szabványos kimenetre
void Ezermester::Kiir() const {
    Kontakt::Kiir();        //kiirja az ososztaly attributumait
    std::cout << "Szakma: " << GetSzakma() << std::endl;        //majd a sajat attributumokat
    std::cout <<"Megjegyzes: " << GetMegjegyzes() << std::endl;
}
 /// a kontakt modosítását valósítja meg
void Ezermester::Modosit(){
    Kiir();                         //kiirja a jelenlegi adatokat, hogy tudjaa a felhasznalo mit modosit
    Kontakt::Modosit();             //meghivja az os osztallyal kozos attributomok modositasat
    std::cout << "Uj Szakma: ";     //bekeri az attributumok uj erteket
    std::cin >> std::ws;            //eldobjuk a whitespaceket
    std::getline(std::cin,szakma);
    std::cout << "Uj Megjegyzes: ";
    std::cin >> std::ws;
    std::getline(std::cin,megjegyzes);
}
///azt vizsgalja hogy a parameterkent kapott stringet tartalmazza e az objektum
///a kontakt bármely attributuma
bool Ezermester::BenneVanE(const std::string& szo) const {
    //megnezi az ososztaly attributumait eloszor
    //majd az Ezermester osztaly sajat attributumait ellenorzi
    return Kontakt::BenneVanE(szo) || (szakma.find(szo) != std::string::npos) || (megjegyzes.find(szo) != std::string::npos);
}
///kiirja a Kontakt adatait a parameterkent kapott file-ba.
void Ezermester::KiirToFile(std::ofstream& file) const {
    file << "E;";                                   //kiirja a kontat tipusanak azonositojat
    Kontakt::KiirToFile(file);                      //kiirja a fajlba az ososztaly adattagjait
    file << "," << szakma << "," << megjegyzes;     //majd a sajat attributumokat vesszovel elvalasztva
}
///eltarolja a kontakt adatait egy fajlbol beolvasott sorbol
void Ezermester::BeolvasFromFile(const std::string& sor) {
    std::stringstream ss(sor);  //a stringstream-re tesszuk az atvett sort
    std::string adat;

    std::getline(ss, adat, ';'); // KontaktAz
    std::getline(ss, adat, ';'); // K

    std::getline(ss, adat, ','); // Nev
    nev = adat;

    std::getline(ss, adat, ','); // Telefonszam
    telefonszam = adat;

    std::getline(ss, adat, ','); // Szakma
    szakma = adat;

    std::getline(ss, adat, ','); // Megjegyzes
    megjegyzes = adat;
}
///klónozás az operator= megvalósításához
///es a helyes memoriakezeleshez
Ezermester* Ezermester::clone(){
    return new Ezermester(*this); //visszater egy uj Ezermester objektumra mutato pinterrel
}


