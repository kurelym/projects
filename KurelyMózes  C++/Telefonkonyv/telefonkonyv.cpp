#include "telefonkonyv.h"
#include "memtrace.h"


///a telefonkomyv tartalmat abc sorrendbe rendezi
void Telefonkonyv::AbcRendezes() {
    for (int i = 0; i < aktMeret - 1; i++) {
        for (int j = 0; j < aktMeret - i - 1; j++) {
            if (*(kontaktok[j]) > *(kontaktok[j + 1])) {
                Kontakt* temp = kontaktok[j];
                kontaktok[j] = kontaktok[j + 1];
                kontaktok[j + 1] = temp;
            }
        }
    }
}


///masolo konstruktor
Telefonkonyv::Telefonkonyv(const Telefonkonyv& masik) : meret(masik.meret), aktMeret(masik.aktMeret) {
    kontaktok = new Kontakt*[meret];
    for (int i = 0; i < aktMeret; i++) {
        kontaktok[i] = masik.kontaktok[i]->clone();
    }
}

///Torli a telefonkönyvben levo osszes kontaktot
void Telefonkonyv::reset(){
    for (int i = 0; i < aktMeret; i++) {    //vegigmegy a kontaktokatat tartalmazo tombon
        delete kontaktok[i];                //torli a kontaktot
    }
    aktMeret=0;                             //mivel minden kontaktot toroltunk ezert a meret 0
}

///Destruktor
///Torli a telefonkonyvben levo osszes kontaktot
///Es a telefonkonyvet is
Telefonkonyv::~Telefonkonyv() {
    reset();            //Torli a telefonkönyvben levo osszes kontaktot
    delete[] kontaktok; //Vegul magat a telefonkonyvet is toroli
}

///Indexelo operator
///Hibás index esetén kivételt dob
///Visszateresi erteke egy Kontakt objektumra mutato pointer referencia
Kontakt*& Telefonkonyv::operator[](int index) {
    if (index < 0 || index >= meret) {
        throw std::out_of_range("Hibas index!");
    }
    return kontaktok[index];
}

///Hozzaad Kontaktot a telefonkonyvhoz
///Ha betelt a telefonkonyv akkor ketszerezi a meretet
void Telefonkonyv::AddKontakt(Kontakt* kontakt) {
    if (aktMeret >= meret) {
        MeretNoveles();             //ha nincs mar hely a tomben akkor megketszerezi a meretet
    }
    kontaktok[aktMeret] = kontakt;   //az utolso elem lesz az uj elem
    aktMeret++;                      //noveli 1.el a meretet
    this->AbcRendezes();            //vegul ABC sorrendbe rendezi a kontaktokat
}

///Kiirja a telefonkonyvben levo osszes kontakt minden adatat
void Telefonkonyv::KiirKontaktok() {
    for (int i = 0; i < aktMeret; i++) {
        std::cout<<"------------"<<i+1<<".------------"<<std::endl;
        kontaktok[i]->Kiir();
        std::cout << std::endl;
    }
}

///Kiirja a telefonkönyvben levo osszes kontakt nevet es sorszamat
void Telefonkonyv::Listazas() {
    for (int i = 0; i < aktMeret; i++) {
        std::cout<<i+1<<".: ";
        std::cout<<kontaktok[i]->GetNev(); //csak a nevet irjuk ki
        std::cout << std::endl;
    }
}

///Ertekado operator
Telefonkonyv& Telefonkonyv::operator=(const Telefonkonyv& masik) {
    if (this == &masik) {
            return *this;
    }
    reset();    // Eddigi adatok torlése
    delete[] kontaktok;

    meret = masik.meret;
    aktMeret = masik.aktMeret;
    kontaktok = new Kontakt*[meret];
    for (int i = 0; i < aktMeret; i++) {
        kontaktok[i] = masik.kontaktok[i]->clone();     // Uj adatok masolasa
    }
    return *this;
}


///adott indexu kontaktot torli a telefonkonyvbol
void Telefonkonyv::Torles(int index) {
    if (index < 0 || index >= aktMeret) {
        throw std::out_of_range("Hibas index!");
    }

    delete kontaktok[index];
    for (int i = index; i <= aktMeret; i++) {
        kontaktok[i] = kontaktok[i + 1];        //az összes többi kontaktot 1-el el kell tolni
    }
    aktMeret--; //a meret csokken 1-el
}

///Ketszeresere noveli a telefonkonyvben tarolhato kontaktok maximalis szamat
void Telefonkonyv::MeretNoveles() {
    int ujMeret = meret * 2;                        //ketszerese lesz az ujmeret
    Kontakt** ujKontaktok = new Kontakt*[ujMeret];  //lefoglalja a helyet

    for (int i = 0; i < aktMeret; i++) {
        ujKontaktok[i] = kontaktok[i];              //atmasolja az adatokat az ujba
    }

    delete[] kontaktok;                             //torli a regit
    kontaktok = ujKontaktok;
    meret = ujMeret;
}


///a parameterkent kapott fajlba kiirja a telefonkonyvben levo kontaktokat
///a megfelelo formatumban, soronkent
void Telefonkonyv::KiirFajlba(const std::string& fajlnev) const {
    std::ofstream file(fajlnev);        //megnyitja a file-t
    if (!file.is_open()) {
        throw std::ios_base::failure("Nem sikerult megnyitni a fajlt.");        //hibauzenet ha nem lehet megnyitni
    }

    for (int i = 0; i < aktMeret; i++) {
        kontaktok[i]->KiirToFile(file); //meghivja a kontaktot fajlba kiro fv-t
        file <<std::endl;
    }
    file.close();   //bezarja a filet
}

///beolvassuk a parameterkent kapott nevu fajlbol a telefonkonyv tartalmat
void Telefonkonyv::BeolvasFajlbol(const std::string& fajlnev) {
    reset(); //a benne levo kontaktokat torli
    std::ifstream file(fajlnev);
    if (!file.is_open()) {
        throw std::ios_base::failure("Nem sikerult megnyitni a fajlt.");        //hibauzenet ha nem lehet megnyitni
    }

    std::string sor;                    //a file egy sora
    while (std::getline(file, sor)){    //ciklus amig van a file-ban sor
        if (!sor.empty()) {
            char kontaktTipus = sor[0]; //a sor elso karaktere azonositja a kontakt tipusat
            Kontakt* ujkontakt;

            switch (kontaktTipus) {     //a kontakt tipusanak megfelelo objektumot hoz letre
                case 'B':
                    ujkontakt = new Barat();
                    break;
                case 'U':
                    ujkontakt = new Uzletfel();
                    break;
                case 'E':
                    ujkontakt = new Ezermester();
                    break;
                case 'K':
                    ujkontakt = new Kontakt();
                    break;
                default:
                    throw "Ismeretlen kontakt tipus";
            }

            ujkontakt->BeolvasFromFile(sor);    //feltolti a sorban levo adatokkal az objektumot
            AddKontakt(ujkontakt);              //hozza adja az uj kontaktot a telefonkonyvhoz
        }
    }
    file.close();   //bezarja a filet
}

