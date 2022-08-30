import configparser
import random


def main():
    config = configparser.ConfigParser()
    config.read_file(open(r'settings.txt'))

    num_righe = int(config.get('Settings', 'num_righe'))
    num_colonne = int(config.get('Settings', 'num_colonne'))
    pattern_path = config.get('Settings', 'pattern_path')

    with open(pattern_path, 'r') as f_pattern:
                #riga casta a numeri                     #cicla per ogni linea
        pattern = [[int(num) for num in linea.split(' ')] for linea in f_pattern]

    if num_colonne < len(pattern) or num_righe < len(pattern[0]):
        print("Dimensione del pattern non proporzionata alla matrice")
        return

    scelta = input("Scegli le opzioni:\n1) Genera una nuova matrice casuale \n2) Leggi la matrice dal file "
                   "/matrix.txt \n ")

    if scelta == '2':
        print("Leggo la matrice...")
        with open('matrix.txt', 'r') as f_matrix:
            #leggo ogni numero in una riga e la metto in una lista e poi per ogni line e lista di liste, matrice
            matrix = [[int(num) for num in line.split(' ')] for line in f_matrix]
    else:
        print("Genero la matrice...")
        matrix = ([random.choices(range(0, 2), k=num_colonne) for _ in range(num_righe)])
        with open('matrix.txt', 'w') as out:
            for riga in matrix:
                out.write(' '.join([str(a) for a in riga]) + '\n')

    ruot90 = ruotare_matrix(pattern)
    ruot180 = ruotare_matrix(ruot90)
    ruot270 = ruotare_matrix(ruot180)
#li casto a lista
    res = check_matrix(list(trova_pattern(matrix, pattern)))
    res_90 = check_matrix(list(trova_pattern(matrix, ruot90)))
    res_180 = check_matrix(list(trova_pattern(matrix, ruot180)))
    res_270 = check_matrix(list(trova_pattern(matrix, ruot270)))

    if res or res_90 or res_180 or res_270:
        print("Pattern individuato,\n")
        if res:
            print("Rotazione: 0 gradi")
        if res_90:
            print("Rotazione: 90 gradi")
        if res_180:
            print("Rotazione: 180 gradi")
        if res_270:
            print("Rotazione: 270 gradi")
    else:
        print("Pattern non presente")


def ruotare_matrix(matrix):
    #zip lo usiamo per avere una lista di tuple
    lista_di_tuple = zip(*matrix[::-1])
    #Forzatura cast a list per ogni elemento della lista di tuple
    return [list(elem) for elem in lista_di_tuple]


def check_matrix(matrix):
    res = False
    for i in range(len(matrix)):
        if True in matrix[i]:
            res = True
            break
    return res

#LISTA DI true o false, uno per ogni sottomatrice, Yield genera al volo i valori e li ritorna.
# Ritorna matrice di matrici
#Per ogni riga della matrice di valori vado a controllare il valoro dentro la matrice di valori
def trova_pattern(matrix, pattern):
    sotto_matrice_di_matrici = generazione_finestre(matrix, (len(pattern), len(pattern[0])))
    for riga_sotto_matrice in sotto_matrice_di_matrici:
        yield [sotto_matrice == pattern for sotto_matrice in riga_sotto_matrice]

#Lista di sottomatrici delle dimensioni del pattern. 
def generazione_finestre(matrix, formato_finestra):
    altezza, larghezza = len(matrix), len(matrix[0])
    finestra_a, finestra_l = formato_finestra

    #Generazione di righe e colonne
    for y in range(altezza - finestra_a + 1):
        #Genera Lista di matrici per y in range
        yield [
            #una porzione di riga in una porzione della matrice
            [riga[x:x + finestra_l] for riga in matrix[y:y + finestra_a]]
            for x in range(larghezza - finestra_l + 1)
        ]


main()
