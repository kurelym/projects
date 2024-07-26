-- general settings
-- ================
-- do not echo
set echo off
-- do not print substitution before/after text
set verify off
-- set date format
alter session set NLS_DATE_FORMAT='YYYY-MM-DD';
-- allow PL/SQL output
set serveroutput on
-- disable feedback, eg. anonymous block completed
set feedback off

-- táblaeldobások ide
DROP TABLE rendelesek;
DROP TABLE eszkozok;
DROP TABLE rendezvenyek;


prompt <tasks>

prompt <task n="2">
prompt <![CDATA[

CREATE TABLE eszkozok(
        eszkoz_id           NUMBER NOT NULL,
        gyartasi_szam       VARCHAR2(20 CHAR) NOT NULL UNIQUE,
        marka               VARCHAR2(30 CHAR) NOT NULL,
        nev                 VARCHAR2(30 CHAR) NOT NULL,
        tipus               VARCHAR2(30 CHAR) NOT NULL,
        koltseg_estenkent   NUMBER DEFAULT 48000 NOT NULL,
        vasarlas_datum      DATE NOT NULL,
        
        CONSTRAINT eszkozok_elsodleges_kulcs PRIMARY KEY (eszkoz_id),
        
        CONSTRAINT eszkozok_tipus_ellenorzes CHECK
        (tipus in ('hang', 'fény', 'látvány', 'dekoráció', 'szórakoztató')),
        
        CONSTRAINT  eszkozok_koltseg_ellenorzes CHECK
        ((15000 <= koltseg_estenkent) AND (koltseg_estenkent <= 60000)),
        
        CONSTRAINT eszkozok_vasarlas_datum_ellenorzes CHECK
        (vasarlas_datum >= DATE'2008-01-01')

);
--COMMENT ON TABLE eszkozok is 'Az eszközök adatait tároló tábla.';

CREATE TABLE rendezvenyek(
        rendezveny_id       NUMBER NOT NULL,
        kod                 VARCHAR2(10 CHAR) NOT NULL UNIQUE,
        datum               DATE NOT NULL,
        helyszin            VARCHAR2(40 CHAR) NOT NULL,
        napszak             VARCHAR2(10 CHAR) NOT NULL,
        megrendelo_neve     VARCHAR2(40 CHAR) NOT NULL,
        keretosszeg         NUMBER NOT NULL,
        zene                VARCHAR2(5 CHAR) NOT NULL,
        biztonsagi_orzes    VARCHAR2(5 CHAR) NOT NULL,
        vendegek_szama      NUMBER,
        
        CONSTRAINT rendezvenyek_elsodleges_kulcs PRIMARY KEY (rendezveny_id),

        CONSTRAINT rendezvenyek_datum_ellenorzes CHECK
        (datum >= DATE'2017-01-01'),
        
        CONSTRAINT rendezvenyek_napszak_ellenorzes CHECK
        (napszak in ('nappal', 'éjszaka')),
        
        CONSTRAINT rendezvenyek_keretosszeg_ellenorzes CHECK
        (keretosszeg >= 60000),
        
        CONSTRAINT rendezvenyek_zene_ellenorzes CHECK
       (zene in ('igen', 'nem')),
        
       CONSTRAINT rendezvenyek_biztonsagi_orzes_ellenorzes CHECK
       (biztonsagi_orzes IN ('igen', 'nem')),

       CONSTRAINT rendezvenyek_biztonsagi_orzes_napszak_ellenorzes CHECK
       (NOT(napszak = 'éjszaka') OR (biztonsagi_orzes = 'igen'))
);

CREATE TABLE rendelesek(
        rendeles_id         NUMBER NOT NULL,
        eszkoz_id           NUMBER NOT NULL,
        rendezveny_id       NUMBER NOT NULL,
        felelos             VARCHAR2(30 CHAR) NOT NULL,
        szallitas_oda       VARCHAR2(7 CHAR) DEFAULT 'autó' NOT NULL,
        szallitas_vissza    VARCHAR2(7 CHAR) DEFAULT 'autó' NOT NULL,
        mejegyzes           CLOB,
        
        CONSTRAINT rendelesek_elsodleges_kulcs PRIMARY KEY (rendeles_id),
        
        CONSTRAINT rendelesek_eszkoz_idegen_kulcs FOREIGN KEY (eszkoz_id)
        REFERENCES eszkozok (eszkoz_id),
        
        CONSTRAINT rendelesek_rendezveny_idegen_kulcs FOREIGN KEY (rendezveny_id)
        REFERENCES rendezvenyek (rendezveny_id),
        
        CONSTRAINT rendelesek_szallitas_oda_ellenorzes CHECK
        (szallitas_oda IN ('autó', 'hajó', 'busz', 'gyalog', 'egyéb')),
        
        CONSTRAINT rendelesek_szallitas_vissza_ellenorzes CHECK
        (szallitas_vissza IN ('autó', 'hajó', 'busz', 'gyalog', 'egyéb'))
);


prompt ]]>
prompt </task>


prompt </tasks>
