#ifndef TELEFONKONYV_H
#define TELEFONKONYV_H
#include <iostream>
#include "kontakt.h"
#include "barat_kontakt.h"
#include "ezermester_kontakt.h"
#include "uzletfel_kontakt.h"
#include <iostream>
///Kontaktokra mutato pointereket tarolo Telefonkonyv osztaly
class Telefonkonyv {
private:
    Kontakt** kontaktok;        //tomb ami Kontaktokra mutato pointereket tarol
    int meret;                  //a tomb maximalis merete(novelheto)
    int aktMeret;               //a tomben aktualisan levo pointerek szama

    ///Ketszeresere noveli a telefonkonyvben tarolhato kontaktok maximalis szamat
    void MeretNoveles();

public:
    ///Konstruktor 0 parameterrel
    ///Kezdõ méretet 5-re allitja
    ///Az aktuális meret 0
    Telefonkonyv() : meret(5), aktMeret(0) {
        kontaktok = new Kontakt*[meret];
    }

    ///A telefonkonyvben levo kontaktok szamat adja vissza
    int GetKontaktCount() const {
        return aktMeret;
    }

    ///masolo konstruktor
    Telefonkonyv(const Telefonkonyv& masik);

    ///Ertekado operator
    Telefonkonyv& operator=(const Telefonkonyv& masik);

    ///Indexelo operator
    ///Hibás index esetén kivételt dob
    ///Visszateresi erteke egy Kontakt objektumra mutato pointer referencia
    Kontakt*& operator[](int index);

    ///Hozzaad Kontaktot a telefonkonyvhoz
    ///Ha betelt a telefonkonyv akkor ketszerezi a meretet
    void AddKontakt(Kontakt* kontakt);

    ///Kiirja a telefonkonyvben levo osszes kontakt minden adatat
    void KiirKontaktok();

    ///Kiirja a telefonkönyvben levo osszes kontakt nevet es sorszamat
    void Listazas();

    ///a telefonkomyv tartalmat abc sorrendbe rendezi
    void AbcRendezes();

    ///adott indexu kontaktot torli a telefonkonyvbol
    void Torles(int index);

    ///a parameterkent kapott fajlba kiirja a telefonkonyvben levo kontaktokat
    ///a megfelelo formatumban, soronkent
    void KiirFajlba(const std::string& fajlnev) const;

    ///beolvassuk a parameterkent kapott nevu fajlbol a telefonkonyv tartalmat
    void BeolvasFajlbol(const std::string& fajlnev);

    ///Torli a telefonkönyvben levo osszes kontaktot
    void reset();

    ///Destruktor
    ///Torli a telefonkonyvben levo osszes kontaktot
    ///Es a telefonkonyvet is
    ~Telefonkonyv();

    ///generikus kereses
    ///visszateresi erteke egy telefonkonyv amiben a predikatumnak megfelelo kontaktok kerultek
    template <typename Predikatum>
    Telefonkonyv GenKereses(Predikatum pred) const{
        Telefonkonyv talalatok;                             //uj telefonkonyv a megtalalt Kontaktoknak
        for (int i = 0; i < aktMeret; i++) {
            if (pred(*kontaktok[i])) {                      //ha megfelel a Predikatumnak hozzadjuk a megtalaltakhoz
                talalatok.AddKontakt(kontaktok[i]->clone());
            }
        }
        return talalatok;                                      //vissza adjuk a megtalalt elemeket tartalmazo telefonkonyvet
    }

};

#endif // TELEFONKONYV_H
