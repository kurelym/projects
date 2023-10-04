#include "menu.h"
#include "memtrace.h"
///Torli a konzol tartalmát
void tablatorles(){
    //std::system("cls");
}

///a fomenu megjeleniteset teszi lehetove
///a parameterkent atvett telefonkonyvel dolgozik
void Menu::fomenu(){
    int n;
    tablatorles();
    std::cout<<"------- Telefonkonyv -------"<<std::endl;
    std::cout<<"----------- Menu -----------"<<std::endl;
    std::cout<<std::endl;
    std::cout<<"         1 - Kereses         "<<std::endl;
    std::cout<<"         2 - Uj kontakt         "<<std::endl;
    std::cout<<"         3 - Listazas         "<<std::endl;
    std::cout<<"         4 - Modositas         "<<std::endl;
    std::cout<<"         5 - Torles         "<<std::endl;
    std::cout<<"         6 - Kilepes         "<<std::endl<<std::endl;
    std::cout<<"                ";
    std::cin>>n;    //bekeri melyik menupontba kivan tovabb menni a felhasznalo
    if(n<8 && n>=1) allapot=n;
    else allapot=0; //ervenytelen allapot eseten a fomenube iranyit
}

///a Kereses menupont megjeleniteset teszi lehetove
///a parameterkent atvett telefonkonyvel dolgozik
void Menu::kereses(Telefonkonyv& telefonkonyv){
    std::string keresendo;
    tablatorles();
    std::cout<<"------- Telefonkonyv -------"<<std::endl;
    std::cout<<"--------- Kereses ----------"<<std::endl<<std::endl;
    std::cout<<"Keresett reszlet:";
    std::cin >> std::ws;
    std::getline(std::cin, keresendo);  //bekeri a keresendo reszletet
    Telefonkonyv talalatok = telefonkonyv.GenKereses(BenneVanE(keresendo)); //generikus kereses Pred osztallyal
    std::cout<<std::endl;
    std::cout<<"Talalatok " <<keresendo<<" reszlettel:"<<std::endl;
    std::cout<<std::endl;
    talalatok.KiirKontaktok();  //kiirja a megfelelo kontaktokat
    std::cout<<std::endl;
    std::cout<<"       Vissza a menube       "<<std::endl;
    std::cout<<"              0"<<std::endl;
    std::string e;
    std::cin >> std::ws;
    std::getline(std::cin,e);
    allapot=0;
}

///az uj kontakt hozzaadasa menupont megjeleniteset teszi lehetove
///a parameterkent atvett telefonkonyvel dolgozik
void Menu::ujkontakt(Telefonkonyv& telefonkonyv){
    char tipus;
    std::string keresendo;
    tablatorles();
    std::cout<<"------- Telefonkonyv -------"<<std::endl;
    std::cout<<"------- Uj Kontakt ---------"<<std::endl<<std::endl;
    std::cout<<"Kontakt tipusa:"<<std::endl;
    std::cout<<"K - Kontakt"<<std::endl;
    std::cout<<"B - Barat"<<std::endl;
    std::cout<<"U - Uzletfel"<<std::endl;
    std::cout<<"E - Ezermester"<<std::endl;
    std::cout<<std::endl;
    std::cin>>tipus;
    tablatorles();
    std::cout<<"------- Telefonkonyv -------"<<std::endl;
    std::cout<<"------- Uj Kontakt ---------"<<std::endl<<std::endl;
    switch(tipus){  //a kivant tipusu kontaktot hoz letre
        case 'B':
            telefonkonyv.AddKontakt(new Barat(1));
            break;
        case 'U':
            telefonkonyv.AddKontakt(new Uzletfel(2));
            break;
        case 'E':
            telefonkonyv.AddKontakt(new Ezermester(3));
            break;
        default:
            telefonkonyv.AddKontakt(new Kontakt(4));
            break;
    }
    std::cout<<"-------- Hozzaadas ---------"<<std::endl;
    std::cout<<"--------- Sikeres ----------"<<std::endl<<std::endl;
    std::cout<<std::endl;
    std::cout<<"       Vissza a menube       "<<std::endl;
    std::cout<<"              0"<<std::endl;
    std::string e;
    std::cin >> std::ws;
    std::getline(std::cin,e);
    allapot=0;
}

///a Kontaktok listazasa menupont megjeleniteseert felel
///kivalaszthato milyen tipusu kontaktokat jelenitsen meg
///a parameterkent atvett telefonkonyvel dolgozik
void Menu::listazas(Telefonkonyv& telefonkonyv){
    tablatorles();
    std::cout<<"------- Telefonkonyv -------"<<std::endl;
    std::cout<<"--------- Listazas ---------"<<std::endl<<std::endl;
    std::cout<<"     A - Minden tipus"<<std::endl;
    std::cout<<"     B - Barat tipus"<<std::endl;
    std::cout<<"     U - Uzlet tipus"<<std::endl;
    std::cout<<"     E - Mester tipus"<<std::endl;
    char e;
    std::cin>>e;    //kivalaszthato milyen tipusu kontaktokat jelenitsen meg
    Telefonkonyv eredmeny; //generikus kereseshez
    switch (e){
        case 'A':{  //minden kontakt megjelenitese
            tablatorles();
            std::cout<<"------- Telefonkonyv -------"<<std::endl;
            std::cout<<"------ Minden Kontakt ------"<<std::endl<<std::endl;
            telefonkonyv.KiirKontaktok();
            std::cout<<std::endl;
            std::cout<<"       Vissza a menube       "<<std::endl;
            std::cout<<"              0"<<std::endl;
            std::string e;
            std::cin >> std::ws;
            std::getline(std::cin,e);
            allapot=0;
            break;
        }
        case 'B':{ //baratok megjelenitese
            tablatorles();
            std::cout<<"------- Telefonkonyv -------"<<std::endl;
            std::cout<<"------ Barat Kontaktok ------"<<std::endl<<std::endl;
            eredmeny=telefonkonyv.GenKereses(BaratE());
            eredmeny.KiirKontaktok();
            std::cout<<std::endl;
            std::cout<<"       Vissza a menube       "<<std::endl;
            std::cout<<"              0"<<std::endl;
            std::string e;
            std::cin >> std::ws;
            std::getline(std::cin,e);
            allapot=0;
            break;
        }
        case 'U':{  //uzletfelek megjelenitese
            tablatorles();
            std::cout<<"------- Telefonkonyv -------"<<std::endl;
            std::cout<<"---- Uzletfel Kontaktok ----"<<std::endl<<std::endl;
            eredmeny=telefonkonyv.GenKereses(UzletfelE());
            eredmeny.KiirKontaktok();
            std::cout<<std::endl;
            std::cout<<"       Vissza a menube       "<<std::endl;
            std::cout<<"              0"<<std::endl;
            std::string e;
            std::cin >> std::ws;
            std::getline(std::cin,e);
            allapot=0;
            break;
        }
        case 'E':{  //ezermesterek megjelenitese
            tablatorles();
            std::cout<<"------- Telefonkonyv -------"<<std::endl;
            std::cout<<"--- EzerMester Kontaktok ---"<<std::endl<<std::endl;
            eredmeny=telefonkonyv.GenKereses(EzermesterE());
            eredmeny.KiirKontaktok();
            std::cout<<std::endl;
            std::cout<<"       Vissza a menube       "<<std::endl;
            std::cout<<"              0"<<std::endl;
            std::string e;
            std::cin >> std::ws;
            std::getline(std::cin,e);
            allapot=0;
        }
    }
}

///a Meglevo kontakt modositasa menupont megjeleniteseert felel
///a parameterkent atvett telefonkonyvel dolgozik
void Menu::modositas(Telefonkonyv& telefonkonyv){
    tablatorles();
    std::cout<<"------- Telefonkonyv -------"<<std::endl;
    std::cout<<"-------- Kontaktok ---------"<<std::endl<<std::endl;
    int idx;
    telefonkonyv.Listazas(); //kilistazza az osszes kontakt nevet
    std::cout<<std::endl;
    std::cout<<"Modositando kontakt szama: ";
    std::cin>>idx;      //kivalaszthato sorszam alapjan, hogy melyiket modositsa
    tablatorles();
    std::cout<<"------- Telefonkonyv -------"<<std::endl;
    std::cout<<"-------- Modositas ---------"<<std::endl<<std::endl;
    telefonkonyv[idx-1]->Modosit();     //meghivja a megfelelo modosito fv-t
    std::cout<<"-------- Modositas ---------"<<std::endl;
    std::cout<<"--------- Sikeres ----------"<<std::endl<<std::endl;
    std::cout<<std::endl;
    std::cout<<"       Vissza a menube       "<<std::endl;
    std::cout<<"              0"<<std::endl;
    std::string e;
    std::cin >> std::ws;
    std::getline(std::cin,e);
    allapot=0;
}

///Meglevo kontaktok torleset megvalosito menupont
///a parameterkent atvett telefonkonyvel dolgozik
void Menu::torles(Telefonkonyv& telefonkonyv){
    tablatorles();
    std::cout<<"------- Telefonkonyv -------"<<std::endl;
    std::cout<<"-------- Kontaktok ---------"<<std::endl<<std::endl;
    int idx;
    telefonkonyv.Listazas();    //kilistazza az osszes kontakt nevet
    std::cout<<std::endl;
    std::cout<<"Torlendo kontakt szama: ";
    std::cin>>idx;              //kivalaszthato sorszam alapjan, hogy melyiket torolje
    telefonkonyv.Torles(idx-1); //torli
    std::cout<<"--------- Torles ----------"<<std::endl;
    std::cout<<"--------- Sikeres ----------"<<std::endl<<std::endl;
    std::cout<<std::endl;
    std::cout<<"       Vissza a menube       "<<std::endl;
    std::cout<<"              0"<<std::endl;
    std::string e;
    std::cin >> std::ws;
    std::getline(std::cin,e);
    allapot=0;
}

///Programbol kilepteto menupont
void Menu::kilepes(){
    tablatorles();
    std::cout<<"------- Telefonkonyv -------"<<std::endl;
    std::cout<<"-------- Kilepes ---------"<<std::endl<<std::endl;
}


