import re
import string
from unidecode import unidecode

def analyze_frequency(text):
    # Cria um dicionário para armazenar as frequências das letras
    frequencies = {}
    for char in text:
        # Se o caracter já estiver no dicionário, soma 1 à frequência
        if char in frequencies:
            frequencies[char] += 1
        # Se não, adiciona o caracter ao dicionário
        else:
            frequencies[char] = 1

    return frequencies

# Dado um dict de frequencias retorna um array com os valores ordenado com as mesmas
def sort_frequency(frequencies):
    # Retorna um array com os valores ordenados por frequencia
    return sorted(frequencies.values())

# Dado dois arrays de frequencias retorna true se ambos são iguais
def compare_frequency(frequencies1, frequencies2):
    return frequencies1 == frequencies2

def main():
    # Texto cifrado para descriptografar
    ciphertext = parse("Texto_conhecido/Cifrado/Mono/Grupo08_texto_cifrado.txt")
    # Texto aberto para comparar com o texto descriptografado
    plaintext = parse("Texto_desconhecido/policarpo_quaresma.txt")

    ciphertext_frequencies = sort_frequency(analyze_frequency(ciphertext))

    results = []
    # Compara frequencia a frequencia do texto cifrado com o texto aberto, de 100 em 100 caracteres
    for i in range(0, len(plaintext)-100):
        plaintext_frequencies = sort_frequency(analyze_frequency(plaintext[i:i+100]))
        if compare_frequency(ciphertext_frequencies, plaintext_frequencies):
            results.append(i)
    print(results)






# Recebe um arquivo txt como parâmetro e retorna um texto apenas com letras minúsculas, sem acentos e sem caracteres especiais
def parse(file_name):

	# abre o arquivo para leitura
	with open(file_name, 'r') as arquivo_entrada:
	    # lê o conteúdo do arquivo
		conteudo = arquivo_entrada.read()

	# transforma as letras em minúsculas
	conteudo = conteudo.lower()

	# remove os acentos das vogais
	conteudo = unidecode(conteudo)

	# remove todos os caracteres que não são letras
	conteudo = re.sub(r'[^a-z]', '', conteudo)
	return conteudo


if __name__ == "__main__":
    main()

