# PIMK

Kod automatyzacji moze zostac uruchominy w kilku roznych trybach:

* "ci" - tryb ktory jest odpowiedzialny za tworzenie nowego zloszenia, jako argumenty wywolania przyjmuje wartosci: 
    * klucz projeku
    * nazwa zgloszenia, dostepne wartosci: null, "" lub kazda inna wartosc definiująca nazwe zgloszenia
* "esi" - tryb ktory jest odpowiedzialny za edycje nazwy zloszenia, jako argumenty wywolania przyjmuje wartosci:
    * klucz projeku
    * numer zgloszenia
* "edi" - tryb ktory jest odpowiedzialny za edycje opisu zloszenia, jako argumenty wywolania przyjmuje wartosci:
    * klucz projeku
    * numer zgloszenia
* "epi" - tryb ktory jest odpowiedzialny za edycje opisu zloszenia, jako argumenty wywolania przyjmuje wartosci:
    * klucz projeku
    * numer zgloszenia    
    * nowy priorytet, dostepne wartosci: "Lowest", "Low", "Medium", "High" oraz "Highest"
* "si" - tryb ktory jest odpowiedzialny wyszukiwanie zloszenia, jako argumenty wywolania przyjmuje wartosci:
    * klucz projeku
    * jql
* "di" - tryb ktory jest odpowiedzialny usuwanie zloszenia, jako argumenty wywolania przyjmuje wartosci:
    * klucz projeku
    * numer zgloszenia
* "cp" - tryb ktory jest odpowiedzialny tworzenie projektu, jako argumenty wywolania przyjmuje wartosci:
    * nazwa projektu, dostepne wartosci: null, "" lub kazda inna wartosc definiująca nazwe projektu
    * numer zgloszenia, dostepne wartosci:"SCRUM-TEAM", "SCRUM-FIRM", "KANBAN-TEAM", "KANBAN-FIRM" oraz "BUG-TRACKING"
* "ep" - tryb ktory jest odpowiedzialny edytowanie projektu, jako argumenty wywolania przyjmuje wartosci:
    * klucz projektu
* "dp" - tryb ktory jest odpowiedzialny usuwanie projektu, jako argumenty wywolania przyjmuje wartosci:
    * klucz projektu   
* "t01" - tryb ktory jest odpowiedzialny za egzekucje testu 01    
* "t02" - tryb ktory jest odpowiedzialny za egzekucje testu 02
* "t03" - tryb ktory jest odpowiedzialny za egzekucje testu 03    
* "t04" - tryb ktory jest odpowiedzialny za egzekucje testu 04    
* "t05" - tryb ktory jest odpowiedzialny za egzekucje testu 04    

Przykladowymi argumentami wywolania funkcji main() są: 
    main("ci", "PIMK", "New test issue")

* Przed egzekucja automatyzacji, nastepujace wartosci zmiennych w klasie APIConnection, musza zostac zdefiowane:
  - jiraURLMainProjectRepo
  - basicAuth
  - atlToken (opcjonalnie)