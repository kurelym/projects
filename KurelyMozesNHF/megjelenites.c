#include <stdio.h>
#include <stdlib.h>
#include "debugmalloc.h"
#include "megjelenites.h"
#include "dicsoseglista.h"

//a fv megjeleniti a menut es visszater a felhasznalo alltal valasztott menupont ertekevel
//0-kilepes a programbol. 1- uj jatek, 2- dicsos�glista
char menu(void)
{
    char iranyitas;
    system("cls");
    printf("*********************AKASZT�FA**********************\n\n");
    printf("************************MEN�************************\n\n");
    printf("            1                           2           \n");
    printf("         �j J�t�k                Dicsos�g T�bla     \n\n\n");
    printf("                    J�t�k V�ge                    \n");
    printf("                        0                           \n\n");
    printf("                        ");
    scanf(" %c",&iranyitas);
    while(iranyitas!='0' && iranyitas!='1' && iranyitas!='2')
    {
        printf("                 Nincs ilyen men�pont!                 \n");
        printf("                        ");
        scanf(" %c",&iranyitas);
    }
    return iranyitas;
}

// A fv egy szamot var, es az akasztofa annyiadik fazisat fogja kirajzolni (1-tol 12-ig)
//12 utan ugyan azt a vegso allapotot adja vissza
void akasztofa(int fazis)
{
    if(fazis==0) printf("\n");
    if(fazis==1)
    {
        printf("\n"); //9
        printf("\n"); //8
        printf("\n"); //7
        printf("\n"); //6
        printf("\n"); //5
        printf("\n"); //4
        printf("\n"); //3
        printf("\n"); //2
        printf("  ________\n"); //1
    }
    if(fazis==2)
    {
        printf("\n"); //9
        printf("  |\n"); //8
        printf("  |\n"); //7
        printf("  |\n"); //6
        printf("  |\n"); //5
        printf("  |\n"); //4
        printf("  |\n"); //3
        printf("  |\n"); //2
        printf("  |________\n"); //1

    }
    if(fazis==3)
    {
        printf("  __________________\n"); //9
        printf("  |\n"); //8
        printf("  |\n"); //7
        printf("  |\n"); //6
        printf("  |\n"); //5
        printf("  |\n"); //4
        printf("  |\n"); //3
        printf("  |\n"); //2
        printf("  |________\n"); //1
    }
    if(fazis==4)
    {
        printf("  __________________\n"); //9
        printf("  |\n"); //8
        printf("  |\n"); //7
        printf("  |\n"); //6
        printf("  |\n"); //5
        printf("  |\n"); //4
        printf("  |\n"); //3
        printf("  |\\\n"); //2
        printf("  |_\\______\n"); //1
    }
    if(fazis==5)
    {
        printf("  __________________\n"); //9
        printf("  | /\n"); //8
        printf("  |/\n"); //7
        printf("  |\n"); //6
        printf("  |\n"); //5
        printf("  |\n"); //4
        printf("  |\n"); //3
        printf("  |\\\n"); //2
        printf("  |_\\______\n"); //1
    }
    if(fazis==6)
    {
        printf("  __________________\n"); //9
        printf("  | /             |\n"); //8
        printf("  |/              |\n"); //7
        printf("  |\n"); //6
        printf("  |\n"); //5
        printf("  |\n"); //4
        printf("  |\n"); //3
        printf("  |\\\n"); //2
        printf("  |_\\______\n"); //1
    }
    if(fazis==7)
    {
        printf("  __________________\n"); //9
        printf("  | /             |\n"); //8
        printf("  |/              |\n"); //7
        printf("  |               O\n"); //6
        printf("  |\n"); //5
        printf("  |\n"); //4
        printf("  |\n"); //3
        printf("  |\\\n"); //2
        printf("  |_\\______\n"); //1
    }
    if(fazis==8)
    {
        printf("  __________________\n"); //9
        printf("  | /             |\n"); //8
        printf("  |/              |\n"); //7
        printf("  |               O\n"); //6
        printf("  |               |\n"); //5
        printf("  |               |\n"); //4
        printf("  |\n"); //3
        printf("  |\\\n"); //2
        printf("  |_\\______\n"); //1
    }
    if(fazis==9)
    {
        printf("  __________________\n"); //9
        printf("  | /             |\n"); //8
        printf("  |/              |\n"); //7
        printf("  |               O\n"); //6
        printf("  |               |/\n"); //5
        printf("  |               |\n"); //4
        printf("  |\n"); //3
        printf("  |\\\n"); //2
        printf("  |_\\______\n"); //1
    }

    if(fazis==10)
    {
        printf("  __________________\n"); //9
        printf("  | /             |\n"); //8
        printf("  |/              |\n"); //7
        printf("  |               O\n"); //6
        printf("  |              \\|/\n"); //5
        printf("  |               |\n"); //4
        printf("  |\n"); //3
        printf("  |\\\n"); //2
        printf("  |_\\______\n"); //1
    }
    if(fazis==11)
    {
        printf("  __________________\n"); //9
        printf("  | /             |\n"); //8
        printf("  |/              |\n"); //7
        printf("  |               O\n"); //6
        printf("  |              \\|/\n"); //5
        printf("  |               |\n"); //4
        printf("  |                \\\n"); //3
        printf("  |\\\n"); //2
        printf("  |_\\______\n"); //1
    }
    if(fazis==12)
    {
        printf("  __________________\n"); //9
        printf("  | /             |\n"); //8
        printf("  |/              |\n"); //7
        printf("  |  HIVATALOSAN  O\n"); //6
        printf("  |  VESZTETT�L! \\|/     Add fel vagy folytasd!\n"); //5
        printf("  |               |\n"); //4
        printf("  |              / \\\n"); //3
        printf("  |\\\n"); //2
        printf("  |_\\______\n"); //1
    }
    if(fazis>12)
    {
        printf("  __________________\n"); //9
        printf("  | /             |\n"); //8
        printf("  |/              |\n"); //7
        printf("  |     MINDIG    O\n"); //6
        printf("  |      VAN     \\|/\n"); //5
        printf("  |     REM�NY    |\n"); //4
        printf("  |              / \\\n"); //3
        printf("  |\\\n"); //2
        printf("  |_\\______\n"); //1
    }
}

//ez a fv a lancolt listaban tarolt dicsoseglista elso elemere mutato pointert varja
//megjeleniti a dicsoseglista elso 5 elemet
void dicsoseg_tabla_megjelenites(DicsLista *eleje)
{
    system("cls");
    printf("*********************AKASZT�FA**********************\n\n");
    printf("*******************DICS�S�GLISTA********************\n\n");
    dicslistakiiratas(eleje); //megjelenitjuk a legjobb 5 pontzsamot
    printf("\n*******************VISZZA A MEN�BE*******************\n");
    printf("                         0\n");
    char vissza;
    printf("                         ");
    scanf(" %c",&vissza);
    while(vissza!='0')
    {
        printf("                 Nincs ilyen men�pont!                 \n");
        printf("                         ");
        scanf(" %c",&vissza);
    }
}

//ez a fv jeleniti meg a kepernyon a dicsoseglista menupontot ha a dicsoseglista meg ures
void dicsoseg_tabla_ures_megjelenites(void)
{
    system("cls");
    printf("*********************AKASZT�FA**********************\n\n");
    printf("*******************DICS�S�GLISTA********************\n\n");
    printf("           A dicsoseglista jelenleg �res.            \n");
    printf("       A mentett eredm�nyek itt fognak megjelenni.    \n");
    printf("\n*******************VISZZA A MEN�BE*******************\n");
    printf("                         0\n");
    char vissza;
    printf("                         ");
    scanf(" %c",&vissza);
    while(vissza!='0')
    {
        printf("                 Nincs ilyen men�pont!                 \n");
        printf("                         ");
        scanf(" %c",&vissza);
    }
}

//ez a fv jeleniti meg a konzolon az uj jatek menupontot ha a szavak.txt-nem talalhato
void szavak_nem_talalhatoak_megjelenites(void)
{
    system("cls"); //toroli a konzol addigi tartalmat
    printf("*********************AKASZT�FA***********************\n\n");
    printf("**********************�J J�T�K***********************\n\n");
    printf("             A szavak.txt nem tal�lhat�.              \n");
    printf("       E f�jl n�lk�l nem tud �j j�t�kot kezdeni.     \n");
    printf("\n*******************VISZZA A MEN�BE*******************\n");
    printf("                         0\n");
    char vissza;
    printf("                         ");
    scanf(" %c",&vissza);
    while(vissza!='0')
    {
    printf("                 Nincs ilyen men�pont!                 \n");
    printf("                         ");
    scanf(" %c",&vissza);
    }
}

//a fv megjeleniti a jatek kezdetekor a fejlecet
void jatekfejlec(void)
{
    system("cls"); //toroli a konzol addigi tartalmat
    printf("*********************AKASZT�FA***********************\n\n");
    printf("****************Felad�s - 0-�s gomb******************\n");
}

//a fv ket karaktertombot var
//az egyik a szo, a masik pedig a megengedett karakterek
//a fv kiirja a szo karakteret ha az adott karakter megtalalhato a megengedett karakterek tomben
//ha nem talalhato meg akkor pedig '_' karaktert ir helyette
void eltalatakkiiratasas(char*s,char*karakterek)
{
    printf("\n A sz�: ");
    //a ciklus vegigmegy a szo karakterein
    for(int i=0;i<strlen(s);i++)
    {
        int db=strlen(karakterek);
        //a ciklus vegigmegy a megengedett karaktereken
        for(int j=0;j<strlen(karakterek);j++)
        {
            if(s[i]!=karakterek[j]) db--;
        }
        //ha a szo adott karaktere nem egyezik meg egyik megengedett karakterrel sem akkor '_'-t jelenit meg a fv
        if(db==0) printf(" _ ");
        else printf("%c ",s[i]); //egyeb esetben pedig a karaktert magat
    }
    printf("\n");

}

//ez a fv ket karaktertombot var, egyikben a helyes tippekkel, masikban a rossz tippekkel
//ezeket jeleniti meg
void rossz_helyes_tippek(char rossz[100],char helyes[10])
{
    printf("\033[0;31m"); //pirisra allitjuk a betuszint
    printf("\nEddigi rossz tippek: ");
    for(int i=0;i<=100;i++)
    {
        printf("%c ",rossz[i]); //helytelen karakterek kiirasa
    }
    printf("\n");
    printf("\033[0m"); //visszaallitjuk a betuszint
    printf("\033[0;32m");//zoldre allitjuk a betuszint
    printf("Eddigi j� tippek: ");
    for(int i=0;i<10;i++)
    {
        printf("%c ",helyes[i]);//helyes karakterek kiirasa
    }
    printf("\033[0m\n");//visszaalitjuk a betuszint
}

//ez a fv jeleniti meg a keprenyon a megoldast ha a jatekos feladta ajatekot
//egy stringet var ami a megjelenitendo megoldas
void jatekvege_feladta(char *kivalasztott)
{
    printf("\n**********************\033[0;31mFELADVA\033[0m**********************\n");
    printf("\n              A sz� a/az\033[0;32m %s\033[0m volt.              \n",kivalasztott);
    printf("\n*******************VISSZA A MEN�BE*******************\n");
    printf("                         0\n");
    char vissza;
    printf("                         ");
    scanf(" %c",&vissza);
    while(vissza!='0')
    {
        printf("                 Nincs ilyen men�pont!                 \n");
        printf("                         ");
        scanf(" %c",&vissza);
    }
}

//ez a fv irja felul a konzol tartalmat ha a jatekos az osszes betut kitalalta a szobol
//a kitalalt szot es a jatekos pontszamat varja ugyanis itt tudja menteni a pontszamot is
void jatekvege_kitalalta_pontszammentes(char *kivalasztott,int pontszam)
{
    system("cls");
    printf("*********************AKASZT�FA***********************\n\n");
    printf("\n*********************\033[0;33mKITAL�LVA\033[0m*********************\n");
    printf("\n              A sz� a/az\033[0;32m %s\033[0m volt.\n",kivalasztott);
    printf("                  Pontsz�m: %d\n",pontszam);
    printf("          K�v�nja menteni a pontsz�mot? I\\N\n");
    char dontes;
    printf("                         ");
    scanf(" %c",&dontes);
    while(dontes!='i' && dontes!='I' && dontes!='n' && dontes!='N')
    {
        printf("                 Nincs ilyen opcio!                 \n");
        printf("                         ");
        scanf(" %c",&dontes);
    }
    if(dontes=='i' || dontes=='I')
    {
        char nev[50];
        printf("\nN�v: ");
        scanf("%s",&nev);
        if(eredmenymentes(nev,pontszam)==1) printf("Az eredmeny ment�se sikeres.\n");
        else printf("Az eredmeny mentese sikertelen");
    }
        printf("\n*******************VISZZA A MEN�BE*******************\n");
        printf("                         0\n");
        char vissza;
        printf("                         ");
        scanf(" %c",&vissza);
        while(vissza!='0')
        {
            printf("                 Nincs ilyen men�pont!                 \n");
            printf("                         ");
            scanf(" %c",&vissza);
        }
}
