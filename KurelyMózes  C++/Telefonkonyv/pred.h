#ifndef PRED_H
#define PRED_H
#include <iostream>
#include "kontakt.h"
///predikatum osztalyokat tartalmaza fajl
///ezeknek az osztalyoknak a segitsegevel valosithato meg generikus kereses

///predikatum osztaly arra hogy a kontakt tartalmazza e az adott szot
class BenneVanE {
private:
    std::string szo;
public:
    BenneVanE(const std::string& szo) : szo(szo) {}

    bool operator()(const Kontakt& kontakt) const {
        return kontakt.BenneVanE(szo);
    }
};

///predikátum osztaly arra, hogy uzletfel e az adott kontakt
struct UzletfelE {
    UzletfelE() {}
    bool operator()(const Kontakt& kontakt) const {
        return (kontakt.GetTipus() == 'U');
    }
};

///predikatum osztaly arra, hogy ezermester e az adott kontakt
struct EzermesterE{
    EzermesterE() {}
    bool operator()(const Kontakt& kontakt) const {
        return (kontakt.GetTipus() == 'E');
    }
};
///predikatum osztaly arra, hogy ezermester e az adott kontakt
struct BaratE {
    BaratE() {}
    bool operator()(const Kontakt& kontakt) const {
        return (kontakt.GetTipus() == 'B');
    }
};
#endif // PRED_H
