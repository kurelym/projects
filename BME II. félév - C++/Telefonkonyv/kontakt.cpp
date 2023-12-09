#include "kontakt.h"
#include "memtrace.h"

///Kiirja a kontakt adatait a szabv�nyos kimenetre
 void Kontakt::Kiir() const {
    std::cout << "Nev: " << GetNev() << std::endl;
    std::cout << "Telefonszam: " << GetTelefonszam() << std::endl;
}
/// a kontakt modos�t�s�t val�s�tja meg
void Kontakt::Modosit(){
    Kiir();
    std::cout << "Uj Nev: ";            //bekerjuk az attributumok uj erteket
    std::cin >> std::ws;                //eldobjuk a whitespaceket
    std::getline(std::cin,nev);
    std::cout << "Uj Telefonszam: ";
    std::cin >> std::ws;
    std::getline(std::cin, telefonszam);
}



///azt vizsgalja hogy a parameterkent kapott stringet tartalmazza e
///a kontakt b�rmely attributuma
 bool Kontakt::BenneVanE(const std::string& szo) const {
     //igazzal ter vissza ha vagy a nevben vagy a telefonszamban megtalalhato a parameterkent atvett string
    return (nev.find(szo) != std::string::npos) || (telefonszam.find(szo) != std::string::npos);
}

///kiirja a Kontakt adatait a parameterkent kapott file-ba.
 void Kontakt::KiirToFile(std::ofstream& file) const {
    file << "K;";                       //a kontakt tipusa K
    file << nev << "," << telefonszam;  //az adattagok besszovel elvalasztva
}

///eltarolja a kontakt adatait egy fajlbol beolvasott sorbol
 void Kontakt::BeolvasFromFile(const std::string& sor) {
    std::stringstream ss(sor);  //a stringstream-re tesszuk az atvett sort
    std::string adat;

    std::getline(ss, adat, ';'); // KontaktAzonos�t�, ezt nem kell eltarolni

    std::getline(ss, adat, ','); // N�v
    nev = adat;                  // Ezt eltaroljuk

    std::getline(ss, adat, ','); // Telefonsz�m
    telefonszam = adat;
 }

///kl�noz�s az operator= megval�s�t�s�hoz
Kontakt* Kontakt::clone(){
    return new Kontakt(*this); //visszater egy uj Kontakt objektumra mutato pinterrel
 }
