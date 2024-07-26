import numpy as np #(működik a Moodle-ben is)
import math
import csv
######################## 1. feladat, entrópiaszámítás #########################
def get_entropy(n_cat1: int, n_cat2: int) -> float:

    if n_cat1 == 0 or n_cat2 == 0:
        return 0
    
    entropy = 0
    ossz =  n_cat1+n_cat2
    px_n_cat1 = n_cat1 / ossz
    px_n_cat2 = n_cat2 / ossz

    entropy -= px_n_cat1 * math.log(px_n_cat1,2)
    entropy -= px_n_cat2 * math.log(px_n_cat2,2)

    return entropy

def get_information_gain(features,labels,tulajdonsag,hatar) -> float:
    megfelel =0
    for i in range(len(features)):
        if labels[i] == 1:
            megfelel+=1
    HL = get_entropy(len(features)-megfelel,megfelel)
    
    hatar_alatt=0
    megfelelE=0
    for i in range(len(features)):
        if features[i][tulajdonsag] <= hatar:
            hatar_alatt+=1
            if labels[i] == 1:
                megfelelE+=1

    HLe = get_entropy(hatar_alatt-megfelelE,megfelelE)

    hatar_felett = 0
    megfelelF =0
    for i in range(len(features)):
        if features[i][tulajdonsag] > hatar:
            hatar_felett+=1
            if labels[i] == 1:
                megfelelF+=1
    HLf = get_entropy(hatar_felett-megfelelF,megfelelF)

    L = len(features)
    e = hatar_alatt
    f = L-e

    IG = HL - ( (e / L) * HLe + (f / L) * HLf)
    return IG


###################### 2. feladat, optimális szeparáció #######################
def get_best_separation(features: list,labels: list) -> (int, int):
    best_separation_feature, best_separation_value = 0, 0
    maxIG = -99

    for tulajdonsag in range(len(features[0])):
        for hatar in range(len(features)):
           hatar_ertek = features[hatar][tulajdonsag]
           IG = get_information_gain(features,labels,tulajdonsag,hatar_ertek)
           if IG > maxIG:
               maxIG = IG
               best_separation_feature = tulajdonsag
               best_separation_value = hatar_ertek 

    return best_separation_feature, best_separation_value

def separate(features:list, labels: list, hatar_ertek: int, tulajdonsag: int):
    featuresE = []
    labelsE = []
    featuresF = []
    labelsF = []

    for i in range(len(features)):
        for j in range(len(features[0])):
            if features[tulajdonsag][j] <= hatar_ertek:
                featuresE.append(features[i])
                labelsE.append(labels[i])
            else:
                featuresF.append(features[i])
                labelsF.append(labels[i])
        separate(list,labels,get_best_separation(featuresE,labelsE))
        separate(list,labels,get_best_separation(featuresF,labelsF))
        
def homogen(labels):
    dontes = labels[0]
    for sor in labels:
        if sor!= dontes:
            return False
    return True

def beolvas_csv_tanulnivalo(file_path):
    features = []
    labels = []

    with open(file_path, 'r') as file:
        csv_reader = csv.reader(file)
        for row in csv_reader:
            row = list(map(int, row))
            features.append(row[:-1])
            labels.append(row[-1])

    return features, labels

def beolvas_csv_megoldani(file_path):
    tablazat = []

    with open(file_path, 'r') as file:
        csv_reader = csv.reader(file)
        for row in csv_reader:
            row = list(map(int, row))
            tablazat.append(row)

    return tablazat

def kiir_csv(megoldasok):
    with open('results.csv', 'w', newline='') as file:
        csv_writer = csv.writer(file)
        for megoldas in megoldasok:
            csv_writer.writerow([megoldas])


class CsomoPont():
    def __init__(self, tulajdonsag_index=None, hatar_ertek=None, bal=None, jobb=None,dontes=None):

        #Szulő csomopont
        self.tulajdonsag_index = tulajdonsag_index
        self.hatar_ertek = hatar_ertek
        self.bal = bal
        self.jobb = jobb

        #levél csomopont
        self.dontes = dontes

class DontesiFa():
    def __init__(self):

        self.root = None


    def fa_epetese(self, features, labels):
        if homogen(labels) == False and len(features[0]) > 0:

            tulajdonsag, hatar_ertek  = get_best_separation(features, labels)

            featuresE = []
            labelsE = []
            featuresF = []
            labelsF = []

            for i in range(len(features)):
                if features[i][tulajdonsag] <= hatar_ertek:
                    featuresE.append(features[i])
                    labelsE.append(labels[i])
                else:
                    featuresF.append(features[i])
                    labelsF.append(labels[i])

            bal_reszfa = self.fa_epetese(featuresE, labelsE)
            jobb_reszfa = self.fa_epetese(featuresF, labelsF)

            return CsomoPont(tulajdonsag, hatar_ertek, bal_reszfa, jobb_reszfa)
        
        dontes_itt = max(set(labels), key=labels.count)
        return CsomoPont(dontes=dontes_itt)
    
    def joslas(self, adatsor, fa:CsomoPont):

        if fa.dontes!= None:
            return fa.dontes

        tulajdosag_ertek = adatsor[fa.tulajdonsag_index]
        if tulajdosag_ertek <= fa.hatar_ertek:
            return self.joslas(adatsor,fa.bal)
        else:
            return self.joslas(adatsor, fa.jobb)
        
    def sok_joslas(self, tablazat):
        joslasok = [self.joslas(sor,self.root) for sor in tablazat]
        return joslasok
    
    def fit(self,features,labels):
        self.root = self.fa_epetese(features,labels)


################### 3. feladat, döntési fa implementációja ####################
def main():
    file = 'train.csv'

    features, labels = beolvas_csv_tanulnivalo(file)

    tudas = DontesiFa()

    tudas.fit(features,labels)

    megoldani = beolvas_csv_megoldani('test.csv')
    
    megoldas = tudas.sok_joslas(megoldani)

    kiir_csv(megoldas)

    return 0

if __name__ == "__main__":
    main()