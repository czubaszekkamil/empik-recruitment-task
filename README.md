# empik-recruitment-task

## Przygotowanie środowiska deweloperskiego


### Baza danych
1. Wejść do katalogu docker
2. Uruchomić komendę `docker-compose up -d`
3. Sprawdzić czy kontener się uruchomił: `docker ps`

### Uruchamianie aplikacji
1. Skopiować plik `application-dev-template.properties` i wkleić w tej samej lokalizacji zmieniając nazwę na 
`application-dev.properties`
2. Odkomentować wszystkie linie w pliku
3. Ustawić profil dla aplikacji na `dev`
4. Uruchomić aplikację

### Swagger
Adres do swaggera: `http://localhost:8080/swagger-ui.html`