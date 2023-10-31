import difflib

def text_diff(original, modified):
    d = difflib.Differ()
    diff = d.compare(original.splitlines(), modified.splitlines())
    return '\n'.join(diff)

original = """Número de vagas: 49

Deputados estaduais eleitos:
1 - PASTOR JUNIOR TERCIO (PP, 183.735 votos)
2 - CORONEL ALBERTO FEITOSA (PL, 146.847 votos)
3 - DELEGADA GLEIDE ANGELO (PSB, 118.869 votos)
4 - ANTONIO COELHO (UNIÃO, 91.698 votos)
5 - RODRIGO NOVAES (PSB, 85.107 votos)
6 - ERIBERTO FILHO (PSB, 78.980 votos)
7 - *JOÃO PAULO (PT, 74.441 votos)
8 - *GILMAR JUNIOR (PV, 68.359 votos)
9 - CHAPARRAL (UNIÃO, 66.842 votos)
10 - FRANCISMAR (PSB, 66.621 votos)
11 - GUSTAVO GOUVEIA (SOLIDARIEDADE, 66.110 votos)
12 - *DORIEL (PT, 65.838 votos)
13 - AGLAILSON VICTOR (PSB, 64.714 votos)
14 - ROMERO SALES FILHO (UNIÃO, 64.366 votos)
15 - LUCIANO DUQUE (SOLIDARIEDADE, 61.411 votos)
16 - DANNILO GODOY (PSB, 56.366 votos)
17 - WILLIAM BRIGIDO (REPUBLICANOS, 55.358 votos)
18 - ANTONIO MORAES (PP, 54.756 votos)
19 - CLAUDIANO FILHO (PP, 53.024 votos)
20 - SIMONE SANTANA (PSB, 53.001 votos)
21 - FRANCE HACKER (PSB, 52.009 votos)
22 - ADALTO SANTOS (PP, 51.371 votos)
23 - JEFERSON TIMÓTEO (PP, 51.324 votos)
24 - *DEBORA ALMEIDA (PSDB, 51.282 votos)
25 - PASTOR CLEITON COLLINS (PP, 50.510 votos)
26 - FABRIZIO FERRAZ (SOLIDARIEDADE, 48.794 votos)
27 - MARIO RICARDO (REPUBLICANOS, 48.699 votos)
28 - *JOAQUIM LIRA (PV, 48.293 votos)
29 - ROMERO (UNIÃO, 46.345 votos)
30 - RENATO ANTUNES (PL, 46.226 votos)
31 - *ALVARO PORTO (PSDB, 46.026 votos)
32 - KAIO MANIÇOBA (PP, 45.791 votos)
33 - JARBAS FILHO (PSB, 45.331 votos)
34 - RODRIGO FARIAS (PSB, 45.220 votos)
35 - WALDEMAR BORGES (PSB, 44.857 votos)
36 - HENRIQUE QUEIROZ FILHO (PP, 43.822 votos)
37 - JOSÉ PATRIOTA (PSB, 43.586 votos)
38 - ABIMAEL SANTOS (PL, 43.530 votos)
39 - SILENO (PSB, 43.195 votos)
40 - *ROSA AMORIM (PT, 42.632 votos)
41 - *JOÃO PAULO COSTA (PC do B, 42.474 votos)
42 - *DANI PORTELA (PSOL, 38.215 votos)
43 - JOEL DA HARPA (PL, 35.938 votos)
44 - SOCORRO PIMENTEL (UNIÃO, 35.515 votos)
45 - LULA CABRAL (SOLIDARIEDADE, 34.798 votos)
46 - *JOÃO DE NADEGI (PV, 29.019 votos)
47 - JOÃOZINHO TENÓRIO (PATRIOTA, 28.048 votos)
48 - *IZAIAS REGIS (PSDB, 27.104 votos)
49 - NINO DE ENOQUE (PL, 24.851 votos)

Candidatos mais votados (em ordem decrescente de votação e respeitando número de vagas):
1 - PASTOR JUNIOR TERCIO (PP, 183.735 votos)
2 - CORONEL ALBERTO FEITOSA (PL, 146.847 votos)
3 - DELEGADA GLEIDE ANGELO (PSB, 118.869 votos)
4 - ANTONIO COELHO (UNIÃO, 91.698 votos)
5 - RODRIGO NOVAES (PSB, 85.107 votos)
6 - ERIBERTO FILHO (PSB, 78.980 votos)
7 - *JOÃO PAULO (PT, 74.441 votos)
8 - *GILMAR JUNIOR (PV, 68.359 votos)
9 - CHAPARRAL (UNIÃO, 66.842 votos)
10 - FRANCISMAR (PSB, 66.621 votos)
11 - GUSTAVO GOUVEIA (SOLIDARIEDADE, 66.110 votos)
12 - *DORIEL (PT, 65.838 votos)
13 - AGLAILSON VICTOR (PSB, 64.714 votos)
14 - ROMERO SALES FILHO (UNIÃO, 64.366 votos)
15 - LUCIANO DUQUE (SOLIDARIEDADE, 61.411 votos)
16 - DANNILO GODOY (PSB, 56.366 votos)
17 - WILLIAM BRIGIDO (REPUBLICANOS, 55.358 votos)
18 - ANTONIO MORAES (PP, 54.756 votos)
19 - CLAUDIANO FILHO (PP, 53.024 votos)
20 - SIMONE SANTANA (PSB, 53.001 votos)
21 - FRANCE HACKER (PSB, 52.009 votos)
22 - ADALTO SANTOS (PP, 51.371 votos)
23 - JEFERSON TIMÓTEO (PP, 51.324 votos)
24 - *DEBORA ALMEIDA (PSDB, 51.282 votos)
25 - PASTOR CLEITON COLLINS (PP, 50.510 votos)
26 - FABRIZIO FERRAZ (SOLIDARIEDADE, 48.794 votos)
27 - MARIO RICARDO (REPUBLICANOS, 48.699 votos)
28 - *JOAQUIM LIRA (PV, 48.293 votos)
29 - ROMERO (UNIÃO, 46.345 votos)
30 - RENATO ANTUNES (PL, 46.226 votos)
31 - *ALVARO PORTO (PSDB, 46.026 votos)
32 - KAIO MANIÇOBA (PP, 45.791 votos)
33 - JARBAS FILHO (PSB, 45.331 votos)
34 - RODRIGO FARIAS (PSB, 45.220 votos)
35 - WALDEMAR BORGES (PSB, 44.857 votos)
36 - HENRIQUE QUEIROZ FILHO (PP, 43.822 votos)
37 - JOSÉ PATRIOTA (PSB, 43.586 votos)
38 - ABIMAEL SANTOS (PL, 43.530 votos)
39 - SILENO (PSB, 43.195 votos)
40 - DIOGO MORAES (PSB, 43.117 votos)
41 - ROBERTA ARRAES (PP, 42.778 votos)
42 - *ROSA AMORIM (PT, 42.632 votos)
43 - *JOÃO PAULO COSTA (PC do B, 42.474 votos)
44 - TIAGO PONTES (REPUBLICANOS, 42.391 votos)
45 - DAVI MUNIZ (PSB, 40.978 votos)
46 - CLAUDIA LUPERCIO (REPUBLICANOS, 38.436 votos)
47 - *DANI PORTELA (PSOL, 38.215 votos)
48 - DELEGADO LESSA (PP, 37.839 votos)
49 - JOEL DA HARPA (PL, 35.938 votos)

Teriam sido eleitos se a votação fosse majoritária, e não foram eleitos:
(com sua posição no ranking de mais votados)
40 - DIOGO MORAES (PSB, 43.117 votos)
41 - ROBERTA ARRAES (PP, 42.778 votos)
44 - TIAGO PONTES (REPUBLICANOS, 42.391 votos)
45 - DAVI MUNIZ (PSB, 40.978 votos)
46 - CLAUDIA LUPERCIO (REPUBLICANOS, 38.436 votos)
48 - DELEGADO LESSA (PP, 37.839 votos)

Eleitos, que se beneficiaram do sistema proporcional:
(com sua posição no ranking de mais votados)
50 - SOCORRO PIMENTEL (UNIÃO, 35.515 votos)
51 - LULA CABRAL (SOLIDARIEDADE, 34.798 votos)
63 - *JOÃO DE NADEGI (PV, 29.019 votos)
64 - JOÃOZINHO TENÓRIO (PATRIOTA, 28.048 votos)
67 - *IZAIAS REGIS (PSDB, 27.104 votos)
71 - NINO DE ENOQUE (PL, 24.851 votos)

Votação dos partidos e número de candidatos eleitos:
1 - PSB - 40, 1.237.696 votos (1.200.769 nominais e 36.927 de legenda), 13 candidatos eleitos
2 - PP - 11, 738.630 votos (720.105 nominais e 18.525 de legenda), 8 candidatos eleitos
3 - UNIÃO - 44, 484.166 votos (460.153 nominais e 24.013 de legenda), 5 candidatos eleitos
4 - PL - 22, 456.482 votos (422.456 nominais e 34.026 de legenda), 5 candidatos eleitos
5 - PT - 13, 446.662 votos (334.890 nominais e 111.772 de legenda), 3 candidatos eleitos
6 - SOLIDARIEDADE - 77, 384.664 votos (367.225 nominais e 17.439 de legenda), 4 candidatos eleitos
7 - REPUBLICANOS - 10, 246.553 votos (235.974 nominais e 10.579 de legenda), 2 candidatos eleitos
8 - PSDB - 45, 227.888 votos (208.470 nominais e 19.418 de legenda), 3 candidatos eleitos
9 - PATRIOTA - 51, 165.178 votos (162.869 nominais e 2.309 de legenda), 1 candidato eleito
10 - PV - 43, 163.373 votos (159.153 nominais e 4.220 de legenda), 3 candidatos eleitos
11 - PC do B - 65, 88.569 votos (85.101 nominais e 3.468 de legenda), 1 candidato eleito
12 - PSOL - 50, 86.445 votos (82.988 nominais e 3.457 de legenda), 1 candidato eleito
13 - PDT - 12, 73.659 votos (63.575 nominais e 10.084 de legenda), 0 candidato eleito
14 - CIDADANIA - 23, 62.103 votos (59.232 nominais e 2.871 de legenda), 0 candidato eleito
15 - REDE - 18, 48.475 votos (46.436 nominais e 2.039 de legenda), 0 candidato eleito
16 - PSD - 55, 32.050 votos (3.019 nominais e 29.031 de legenda), 0 candidato eleito
17 - AVANTE - 70, 24.688 votos (20.265 nominais e 4.423 de legenda), 0 candidato eleito
18 - PRTB - 28, 11.816 votos (11.018 nominais e 798 de legenda), 0 candidato eleito
19 - AGIR - 36, 7.991 votos (7.365 nominais e 626 de legenda), 0 candidato eleito
20 - PTB - 14, 6.299 votos (2.633 nominais e 3.666 de legenda), 0 candidato eleito
21 - PCB - 21, 5.905 votos (4.900 nominais e 1.005 de legenda), 0 candidato eleito
22 - PMB - 35, 4.465 votos (4.039 nominais e 426 de legenda), 0 candidato eleito
23 - DC - 27, 2.602 votos (2.289 nominais e 313 de legenda), 0 candidato eleito
24 - PMN - 33, 2.436 votos (1.532 nominais e 904 de legenda), 0 candidato eleito
25 - UP - 80, 2.048 votos (1.765 nominais e 283 de legenda), 0 candidato eleito
26 - PSTU - 16, 1.688 votos (940 nominais e 748 de legenda), 0 candidato eleito
27 - PROS - 90, 1.054 votos (690 nominais e 364 de legenda), 0 candidato eleito
28 - PCO - 29, 138 votos (0 nominal e 138 de legenda), 0 candidato eleito
29 - MDB - 15, 0 voto (0 nominal e 0 de legenda), 0 candidato eleito
30 - PODE - 19, 0 voto (0 nominal e 0 de legenda), 0 candidato eleito
31 - PSC - 20, 0 voto (0 nominal e 0 de legenda), 0 candidato eleito
32 - NOVO - 30, 0 voto (0 nominal e 0 de legenda), 0 candidato eleito

Primeiro e último colocados de cada partido:
1 - PP - 11, PASTOR JUNIOR TERCIO (11222, 183.735 votos) / LINDA VITAL (11321, 44 votos)
2 - PL - 22, CORONEL ALBERTO FEITOSA (22222, 146.847 votos) / CÍCERA É AGORA (22227, 153 votos)
3 - PSB - 40, DELEGADA GLEIDE ANGELO (40190, 118.869 votos) / ISIS PAQUITA (40033, 79 votos)
4 - UNIÃO - 44, ANTONIO COELHO (44000, 91.698 votos) / ROBERTO MAGLIANO (44155, 133 votos)
5 - PT - 13, JOÃO PAULO (13113, 74.441 votos) / SÉRGIO URT (13500, 693 votos)
6 - PV - 43, GILMAR JUNIOR (43333, 68.359 votos) / FABIANA SCHREINER (43222, 103 votos)
7 - SOLIDARIEDADE - 77, GUSTAVO GOUVEIA (77555, 66.110 votos) / VÂNIA SANTOS  (77010, 50 votos)
8 - REPUBLICANOS - 10, WILLIAM BRIGIDO (10123, 55.358 votos) / FERNANDA COSTA (10381, 36 votos)
9 - PSDB - 45, DEBORA ALMEIDA (45555, 51.282 votos) / RON PRODUÇÕES (45145, 305 votos)
10 - PC do B - 65, JOÃO PAULO COSTA (65123, 42.474 votos) / CARLOS MORAIS (65001, 345 votos)
11 - PSOL - 50, DANI PORTELA (50000, 38.215 votos) / JAIME MARCELINO (50144, 67 votos)
12 - PDT - 12, ZÉ QUEIROZ (12345, 33.923 votos) / LUCÉLIA LIMA (12888, 115 votos)
13 - PATRIOTA - 51, JOÃOZINHO TENÓRIO (51555, 28.048 votos) / PEDRO BARBOSA (51028, 4 votos)
14 - CIDADANIA - 23, PROF JADSON CAETANO (23222, 15.769 votos) / SAMIA A VERAS (23577, 100 votos)
15 - REDE - 18, SOUZA VIGILANTE (18111, 9.728 votos) / MÔNICA COLETIVO ARCO-ÍRIS (18222, 130 votos)
16 - AVANTE - 70, EDUARDO VASCONCELOS (70123, 7.596 votos) / MANU TORRES (70112, 27 votos)
17 - PRTB - 28, MANÉTIKIN (28000, 6.039 votos) / THAMIRES SILVA (28011, 0 voto)
18 - PCB - 21, LUIZA MELO (21021, 4.761 votos) / CRISTIANO ROMÃO (21000, 139 votos)
19 - PSD - 55, JOAQUIM (55555, 2.962 votos) / FRANCISCA DE NALDO DO BARRACO (55155, 57 votos)
20 - AGIR - 36, JORGE GOMES (36999, 1.084 votos) / SUELEN CAMILA (36085, 0 voto)
21 - UP - 80, NATALIA LÚCIA (80180, 1.028 votos) / RAFAEL WANDERLEY (80000, 737 votos)
22 - PMB - 35, ERNESTO JUNIOR (35123, 701 votos) / LUCIA MAIMONE (35002, 20 votos)
23 - PROS - 90, LU QUEIROZ (90000, 635 votos) / LUCIMAR AMORIM (90999, 55 votos)
24 - DC - 27, PROFESSOR FELIX (27000, 606 votos) / CLAUDINHA (27497, 3 votos)
25 - PMN - 33, JÚNIOR DELIVERY (33123, 576 votos) / AECIO VIANA (33233, 8 votos)
26 - PTB - 14, ADRIAN MAIA (14022, 524 votos) / SORAIA ALVES (14101, 62 votos)
27 - PSTU - 16, EULINA COUTINHO  (16066, 469 votos) / KATIANY  (16160, 65 votos)

Eleitos, por faixa etária (na data da eleição):
      Idade < 30: 4 (8,16%)
30 <= Idade < 40: 15 (30,61%)
40 <= Idade < 50: 11 (22,45%)
50 <= Idade < 60: 10 (20,41%)
60 <= Idade     : 9 (18,37%)

Eleitos, por gênero:
Feminino:  6 (12,24%)
Masculino: 43 (87,76%)

Total de votos válidos:    5.013.723
Total de votos nominais:   4.669.851 (93,14%)
Total de votos de legenda: 343.872 (6,86%)"""

modified = """Número de vagas: 49

Deputados estaduais eleitos:
1 - PASTOR JUNIOR TERCIO (PP, 183.735 votos)
2 - CORONEL ALBERTO FEITOSA (PL, 146.847 votos)
3 - DELEGADA GLEIDE ANGELO (PSB, 118.869 votos)
4 - ANTONIO COELHO (UNIÃO, 91.698 votos)
5 - RODRIGO NOVAES (PSB, 85.107 votos)
6 - ERIBERTO FILHO (PSB, 78.980 votos)
7 - *JOÃO PAULO (PT, 74.441 votos)
8 - *GILMAR JUNIOR (PV, 68.359 votos)
9 - CHAPARRAL (UNIÃO, 66.842 votos)
10 - FRANCISMAR (PSB, 66.621 votos)
11 - GUSTAVO GOUVEIA (SOLIDARIEDADE, 66.110 votos)
12 - *DORIEL (PT, 65.838 votos)
13 - AGLAILSON VICTOR (PSB, 64.714 votos)
14 - ROMERO SALES FILHO (UNIÃO, 64.366 votos)
15 - LUCIANO DUQUE (SOLIDARIEDADE, 61.411 votos)
16 - DANNILO GODOY (PSB, 56.366 votos)
17 - WILLIAM BRIGIDO (REPUBLICANOS, 55.358 votos)
18 - ANTONIO MORAES (PP, 54.756 votos)
19 - CLAUDIANO FILHO (PP, 53.024 votos)
20 - SIMONE SANTANA (PSB, 53.001 votos)
21 - FRANCE HACKER (PSB, 52.009 votos)
22 - ADALTO SANTOS (PP, 51.371 votos)
23 - JEFERSON TIMÓTEO (PP, 51.324 votos)
24 - *DEBORA ALMEIDA (PSDB, 51.282 votos)
25 - PASTOR CLEITON COLLINS (PP, 50.510 votos)
26 - FABRIZIO FERRAZ (SOLIDARIEDADE, 48.794 votos)
27 - MARIO RICARDO (REPUBLICANOS, 48.699 votos)
28 - *JOAQUIM LIRA (PV, 48.293 votos)
29 - ROMERO (UNIÃO, 46.345 votos)
30 - RENATO ANTUNES (PL, 46.226 votos)
31 - *ALVARO PORTO (PSDB, 46.026 votos)
32 - KAIO MANIÇOBA (PP, 45.791 votos)
33 - JARBAS FILHO (PSB, 45.331 votos)
34 - RODRIGO FARIAS (PSB, 45.220 votos)
35 - WALDEMAR BORGES (PSB, 44.857 votos)
36 - HENRIQUE QUEIROZ FILHO (PP, 43.822 votos)
37 - JOSÉ PATRIOTA (PSB, 43.586 votos)
38 - ABIMAEL SANTOS (PL, 43.530 votos)
39 - SILENO (PSB, 43.195 votos)
40 - *ROSA AMORIM (PT, 42.632 votos)
41 - *JOÃO PAULO COSTA (PC do B, 42.474 votos)
42 - *DANI PORTELA (PSOL, 38.215 votos)
43 - JOEL DA HARPA (PL, 35.938 votos)
44 - SOCORRO PIMENTEL (UNIÃO, 35.515 votos)
45 - LULA CABRAL (SOLIDARIEDADE, 34.798 votos)
46 - *JOÃO DE NADEGI (PV, 29.019 votos)
47 - JOÃOZINHO TENÓRIO (PATRIOTA, 28.048 votos)
48 - *IZAIAS REGIS (PSDB, 27.104 votos)
49 - NINO DE ENOQUE (PL, 24.851 votos)

Candidatos mais votados (em ordem decrescente de votação e respeitando número de vagas):
1 - PASTOR JUNIOR TERCIO (PP, 183.735 votos)
2 - CORONEL ALBERTO FEITOSA (PL, 146.847 votos)
3 - DELEGADA GLEIDE ANGELO (PSB, 118.869 votos)
4 - ANTONIO COELHO (UNIÃO, 91.698 votos)
5 - RODRIGO NOVAES (PSB, 85.107 votos)
6 - ERIBERTO FILHO (PSB, 78.980 votos)
7 - *JOÃO PAULO (PT, 74.441 votos)
8 - *GILMAR JUNIOR (PV, 68.359 votos)
9 - CHAPARRAL (UNIÃO, 66.842 votos)
10 - FRANCISMAR (PSB, 66.621 votos)
11 - GUSTAVO GOUVEIA (SOLIDARIEDADE, 66.110 votos)
12 - *DORIEL (PT, 65.838 votos)
13 - AGLAILSON VICTOR (PSB, 64.714 votos)
14 - ROMERO SALES FILHO (UNIÃO, 64.366 votos)
15 - LUCIANO DUQUE (SOLIDARIEDADE, 61.411 votos)
16 - DANNILO GODOY (PSB, 56.366 votos)
17 - WILLIAM BRIGIDO (REPUBLICANOS, 55.358 votos)
18 - ANTONIO MORAES (PP, 54.756 votos)
19 - CLAUDIANO FILHO (PP, 53.024 votos)
20 - SIMONE SANTANA (PSB, 53.001 votos)
21 - FRANCE HACKER (PSB, 52.009 votos)
22 - ADALTO SANTOS (PP, 51.371 votos)
23 - JEFERSON TIMÓTEO (PP, 51.324 votos)
24 - *DEBORA ALMEIDA (PSDB, 51.282 votos)
25 - PASTOR CLEITON COLLINS (PP, 50.510 votos)
26 - FABRIZIO FERRAZ (SOLIDARIEDADE, 48.794 votos)
27 - MARIO RICARDO (REPUBLICANOS, 48.699 votos)
28 - *JOAQUIM LIRA (PV, 48.293 votos)
29 - ROMERO (UNIÃO, 46.345 votos)
30 - RENATO ANTUNES (PL, 46.226 votos)
31 - *ALVARO PORTO (PSDB, 46.026 votos)
32 - KAIO MANIÇOBA (PP, 45.791 votos)
33 - JARBAS FILHO (PSB, 45.331 votos)
34 - RODRIGO FARIAS (PSB, 45.220 votos)
35 - WALDEMAR BORGES (PSB, 44.857 votos)
36 - HENRIQUE QUEIROZ FILHO (PP, 43.822 votos)
37 - JOSÉ PATRIOTA (PSB, 43.586 votos)
38 - ABIMAEL SANTOS (PL, 43.530 votos)
39 - SILENO (PSB, 43.195 votos)
40 - DIOGO MORAES (PSB, 43.117 votos)
41 - ROBERTA ARRAES (PP, 42.778 votos)
42 - *ROSA AMORIM (PT, 42.632 votos)
43 - *JOÃO PAULO COSTA (PC do B, 42.474 votos)
44 - TIAGO PONTES (REPUBLICANOS, 42.391 votos)
45 - DAVI MUNIZ (PSB, 40.978 votos)
46 - CLAUDIA LUPERCIO (REPUBLICANOS, 38.436 votos)
47 - *DANI PORTELA (PSOL, 38.215 votos)
48 - DELEGADO LESSA (PP, 37.839 votos)
49 - JOEL DA HARPA (PL, 35.938 votos)

Teriam sido eleitos se a votação fosse majoritária, e não foram eleitos:
(com sua posição no ranking de mais votados)
40 - DIOGO MORAES (PSB, 43.117 votos)
41 - ROBERTA ARRAES (PP, 42.778 votos)
44 - TIAGO PONTES (REPUBLICANOS, 42.391 votos)
45 - DAVI MUNIZ (PSB, 40.978 votos)
46 - CLAUDIA LUPERCIO (REPUBLICANOS, 38.436 votos)
48 - DELEGADO LESSA (PP, 37.839 votos)

Eleitos, que se beneficiaram do sistema proporcional:
(com sua posição no ranking de mais votados)
50 - SOCORRO PIMENTEL (UNIÃO, 35.515 votos)
51 - LULA CABRAL (SOLIDARIEDADE, 34.798 votos)
63 - *JOÃO DE NADEGI (PV, 29.019 votos)
64 - JOÃOZINHO TENÓRIO (PATRIOTA, 28.048 votos)
67 - *IZAIAS REGIS (PSDB, 27.104 votos)
71 - NINO DE ENOQUE (PL, 24.851 votos)

Votação dos partidos e número de candidatos eleitos:
1 - PSB - 40, 1.237.696 votos (1.200.769 nominais e 36.927 de legenda), 13 candidatos eleitos
2 - PP - 11, 738.630 votos (720.105 nominais e 18.525 de legenda), 8 candidatos eleitos
3 - UNIÃO - 44, 484.166 votos (460.153 nominais e 24.013 de legenda), 5 candidatos eleitos
4 - PL - 22, 456.482 votos (422.456 nominais e 34.026 de legenda), 5 candidatos eleitos
5 - PT - 13, 446.662 votos (334.890 nominais e 111.772 de legenda), 3 candidatos eleitos
6 - SOLIDARIEDADE - 77, 384.664 votos (367.225 nominais e 17.439 de legenda), 4 candidatos eleitos
7 - REPUBLICANOS - 10, 246.553 votos (235.974 nominais e 10.579 de legenda), 2 candidatos eleitos
8 - PSDB - 45, 227.888 votos (208.470 nominais e 19.418 de legenda), 3 candidatos eleitos
9 - PATRIOTA - 51, 165.178 votos (162.869 nominais e 2.309 de legenda), 1 candidato eleito
10 - PV - 43, 163.373 votos (159.153 nominais e 4.220 de legenda), 3 candidatos eleitos
11 - PC do B - 65, 88.569 votos (85.101 nominais e 3.468 de legenda), 1 candidato eleito
12 - PSOL - 50, 86.445 votos (82.988 nominais e 3.457 de legenda), 1 candidato eleito
13 - PDT - 12, 73.659 votos (63.575 nominais e 10.084 de legenda), 0 candidato eleito
14 - CIDADANIA - 23, 62.103 votos (59.232 nominais e 2.871 de legenda), 0 candidato eleito
15 - REDE - 18, 48.475 votos (46.436 nominais e 2.039 de legenda), 0 candidato eleito
16 - PSD - 55, 32.050 votos (3.019 nominais e 29.031 de legenda), 0 candidato eleito
17 - AVANTE - 70, 24.688 votos (20.265 nominais e 4.423 de legenda), 0 candidato eleito
18 - PRTB - 28, 11.816 votos (11.018 nominais e 798 de legenda), 0 candidato eleito
19 - AGIR - 36, 7.991 votos (7.365 nominais e 626 de legenda), 0 candidato eleito
20 - PTB - 14, 6.299 votos (2.633 nominais e 3.666 de legenda), 0 candidato eleito
21 - PCB - 21, 5.905 votos (4.900 nominais e 1.005 de legenda), 0 candidato eleito
22 - PMB - 35, 4.465 votos (4.039 nominais e 426 de legenda), 0 candidato eleito
23 - DC - 27, 2.602 votos (2.289 nominais e 313 de legenda), 0 candidato eleito
24 - PMN - 33, 2.436 votos (1.532 nominais e 904 de legenda), 0 candidato eleito
25 - UP - 80, 2.048 votos (1.765 nominais e 283 de legenda), 0 candidato eleito
26 - PSTU - 16, 1.688 votos (940 nominais e 748 de legenda), 0 candidato eleito
27 - PROS - 90, 1.054 votos (690 nominais e 364 de legenda), 0 candidato eleito
28 - PCO - 29, 138 votos (0 nominal e 138 de legenda), 0 candidato eleito
29 - MDB - 15, 0 voto (0 nominal e 0 de legenda), 0 candidato eleito
30 - PODE - 19, 0 voto (0 nominal e 0 de legenda), 0 candidato eleito
31 - PSC - 20, 0 voto (0 nominal e 0 de legenda), 0 candidato eleito
32 - NOVO - 30, 0 voto (0 nominal e 0 de legenda), 0 candidato eleito

Primeiro e último colocados de cada partido:
1 - PP - 11, PASTOR JUNIOR TERCIO (11222, 183.735 votos) / LINDA VITAL (11321, 44 votos)
2 - PL - 22, CORONEL ALBERTO FEITOSA (22222, 146.847 votos) / CÍCERA É AGORA (22227, 153 votos)
3 - PSB - 40, DELEGADA GLEIDE ANGELO (40190, 118.869 votos) / ISIS PAQUITA (40033, 79 votos)
4 - UNIÃO - 44, ANTONIO COELHO (44000, 91.698 votos) / ROBERTO MAGLIANO (44155, 133 votos)
5 - PT - 13, JOÃO PAULO (13113, 74.441 votos) / SÉRGIO URT (13500, 693 votos)
6 - PV - 43, GILMAR JUNIOR (43333, 68.359 votos) / FABIANA SCHREINER (43222, 103 votos)
7 - SOLIDARIEDADE - 77, GUSTAVO GOUVEIA (77555, 66.110 votos) / VÂNIA SANTOS  (77010, 50 votos)
8 - REPUBLICANOS - 10, WILLIAM BRIGIDO (10123, 55.358 votos) / FERNANDA COSTA (10381, 36 votos)
9 - PSDB - 45, DEBORA ALMEIDA (45555, 51.282 votos) / RON PRODUÇÕES (45145, 305 votos)
10 - PC do B - 65, JOÃO PAULO COSTA (65123, 42.474 votos) / CARLOS MORAIS (65001, 345 votos)
11 - PSOL - 50, DANI PORTELA (50000, 38.215 votos) / JAIME MARCELINO (50144, 67 votos)
12 - PDT - 12, ZÉ QUEIROZ (12345, 33.923 votos) / LUCÉLIA LIMA (12888, 115 votos)
13 - PATRIOTA - 51, JOÃOZINHO TENÓRIO (51555, 28.048 votos) / PEDRO BARBOSA (51028, 4 votos)
14 - CIDADANIA - 23, PROF JADSON CAETANO (23222, 15.769 votos) / SAMIA A VERAS (23577, 100 votos)
15 - REDE - 18, SOUZA VIGILANTE (18111, 9.728 votos) / MÔNICA COLETIVO ARCO-ÍRIS (18222, 130 votos)
16 - AVANTE - 70, EDUARDO VASCONCELOS (70123, 7.596 votos) / MANU TORRES (70112, 27 votos)
17 - PRTB - 28, MANÉTIKIN (28000, 6.039 votos) / THAMIRES SILVA (28011, 0 voto)
18 - PCB - 21, LUIZA MELO (21021, 4.761 votos) / CRISTIANO ROMÃO (21000, 139 votos)
19 - PSD - 55, JOAQUIM (55555, 2.962 votos) / FRANCISCA DE NALDO DO BARRACO (55155, 57 votos)
20 - AGIR - 36, JORGE GOMES (36999, 1.084 votos) / SUELEN CAMILA (36085, 0 voto)
21 - UP - 80, NATALIA LÚCIA (80180, 1.028 votos) / RAFAEL WANDERLEY (80000, 737 votos)
22 - PMB - 35, ERNESTO JUNIOR (35123, 701 votos) / LUCIA MAIMONE (35002, 20 votos)
23 - PROS - 90, LU QUEIROZ (90000, 635 votos) / LUCIMAR AMORIM (90999, 55 votos)
24 - DC - 27, PROFESSOR FELIX (27000, 606 votos) / CLAUDINHA (27497, 3 votos)
25 - PMN - 33, JÚNIOR DELIVERY (33123, 576 votos) / AECIO VIANA (33233, 8 votos)
26 - PTB - 14, ADRIAN MAIA (14022, 524 votos) / SORAIA ALVES (14101, 62 votos)
27 - PSTU - 16, EULINA COUTINHO  (16066, 469 votos) / KATIANY  (16160, 65 votos)

Eleitos, por faixa etária (na data da eleição):
      Idade < 30: 4 (8,16%)
30 <= Idade < 40: 15 (30,61%)
40 <= Idade < 50: 11 (22,45%)
50 <= Idade < 60: 10 (20,41%)
60 <= Idade     : 9 (18,37%)

Eleitos, por gênero:
Feminino:  6 (12,24%)
Masculino: 43 (87,76%)

Total de votos válidos:    5.013.723
Total de votos nominais:   4.669.851 (93,14%)
Total de votos de legenda: 343.872 (6,86%)"""

result = text_diff(original, modified)
print(result)
