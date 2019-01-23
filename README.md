Aplikacja na zaliczenie z Archtektury

Wymagana JAVA 8 SDK w wersji 1.8.0_172 lub nowsza (1.8.0_???).
Stawiacie bazę MYSQL na komputerze (u siebie lokalnie) i zmieniacie w pliku ..\spring-login\src\main\resources\application.properties
dane logowania na własne.
Apka sama stworzy potrzebne tabelki do logowania.
Nie chce mi się robić modularyzacji więc wrzucamy wszystko do jedno wora:
Controlery do folderu controllers, DTO do dto, itd.

Szybkie wyjaśnienie:

DTO (data transfer object) - służy do pobierania/wyrzucania danych z/na strony, składa się z prostych typów zmiennych

Controller - przekierowuje zapytania z paska adresu; wpisujesz adres w pasku adresu -> controller decyduje co z tym dalej zrobić

Service - warstwa logiczna (wszystkie funkcje które coś 'robią' przetwarzają

Repository - warstwa do pobierania danych z bazy, można pisać native query (czysty sql) lub korzystać z JPA (automatyczne generowanie zapyania przez nazwę funkcji, podpowiedzi w klasach java, dla kotlina nie podpowiada)

Model - to entity czyli ogólnie mówiąc odwzorowanie tabel z bazy danych, a hibernate je automatycznie mapuje



LINKI:

Rejestracja:
- http://localhost:8080/registration

Logowanie:
- http://localhost:8080/login

Lista boisk:
- http://localhost:8080/places/list/

Dodawanie nowego boiska:
- http://localhost:8080/places/add/
