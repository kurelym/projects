#include "barat_kontakt.h"
#include "memtrace.h"

///Kiirja a kontakt adatait a szabványos kimenetre
void Barat::Kiir() const {
    Kontakt::Kiir();        //kiirja az ososztaly attributumait
    std::cout << "Becenev: " << GetBecenev() << std::endl;          //majd a sajat attributumokat
    std::cout << "Privat szam: " << GetPrivatSzam() << std::endl;
}
/// a kontakt modosítását valósítja meg
void Barat::Modosit(){
    Kiir();                             //kiirja a jelenlegi adatokat, hogy tudjaa a felhasznalo mit modosit
    Kontakt::Modosit();                 //meghivja az os osztallyal kozos attributomok modositasat
    std::cout << "Uj Becenev: ";        //bekeri az attributumok uj erteket
    std::cin >> std::ws;                //eldobjuk a whitespaceket
    std::getline(std::cin, becenev);
    std::cout << "Uj Privat szam: ";
    std::cin >> std::ws;
    std::getline(std::cin,privat_szam);
}
///azt vizsgalja hogy a parameterkent kapott stringet tartalmazza e az objektum
///a kontakt bármely attributuma
bool Barat::BenneVanE(const std::string& szo) const {
    //megnezi az ososztaly attributumait eloszor
    //majd a Barat osztaly sajat attributumait ellenorzi
    return Kontakt::BenneVanE(szo) || (becenev.find(szo) != std::string::npos) || (privat_szam.find(szo) != std::string::npos);
}

///kiirja a Kontakt adatait a parameterkent kapott file-ba.
void Barat::KiirToFile(std::ofstream& file) const  {
    file << "B;";                                   //kiirja a kontat tipusanak azonositojat
    Kontakt::KiirToFile(file);                      //kiirja a fajlba az ososztaly adattagjait
    file << "," << becenev << "," << privat_szam;   //majd a sajat attributumokat vesszovel elvalasztva
}

///eltarolja a kontakt adatait egy fajlbol beolvasott sorbol
void Barat::BeolvasFromFile(const std::string& sor) {
    std::stringstream ss(sor);  //a stringstream-re tesszuk az atvett sort
    std::string adat;

    //eloszor az ososztaly attributumait
    std::getline(ss, adat, ';'); // KontaktAzonosito
    std::getline(ss, adat, ';'); // K

    std::getline(ss, adat, ','); // Nev
    nev = adat;

    std::getline(ss, adat, ','); // Telefonszam
    telefonszam = adat;

    //majd a sajat attributumokat
    std::getline(ss, adat, ','); // Becenev
    becenev = adat;

    std::getline(ss, adat, ','); // Privat szam
    privat_szam = adat;
}

///klónozás az operator= megvalósításához
Barat* Barat::clone(){
    return new Barat(*this);//visszater egy uj Barat objektumra mutato pinterrel
}
