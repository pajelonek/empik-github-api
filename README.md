# Empik GitHub API

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=pajelonek_empik-github-api&metric=alert_status)](https://sonarcloud.io/dashboard?id=pajelonek_empik-github-api)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=pajelonek_empik-github-api&metric=bugs)](https://sonarcloud.io/dashboard?id=pajelonek_empik-github-api)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=pajelonek_empik-github-api&metric=code_smells)](https://sonarcloud.io/dashboard?id=pajelonek_empik-github-api)
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=pajelonek_empik-github-api&metric=duplicated_lines_density)](https://sonarcloud.io/dashboard?id=pajelonek_empik-github-api)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=pajelonek_empik-github-api&metric=coverage)](https://sonarcloud.io/summary/new_code?id=pajelonek_empik-github-api)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=pajelonek_empik-github-api&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=pajelonek_empik-github-api)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=pajelonek_empik-github-api&metric=reliability_rating)](https://sonarcloud.io/dashboard?id=pajelonek_empik-github-api)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=pajelonek_empik-github-api&metric=security_rating)](https://sonarcloud.io/dashboard?id=pajelonek_empik-github-api)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=pajelonek_empik-github-api&metric=vulnerabilities)](https://sonarcloud.io/dashboard?id=pajelonek_empik-github-api)
[![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=pajelonek_empik-github-api&metric=sqale_index)](https://sonarcloud.io/summary/new_code?id=pajelonek_empik-github-api)
![Known Vulnerabilities](https://snyk.io/test/github/pajelonek/empik-github-api/badge.svg)



## Zadanie rekrutacyjne backend

### Stwórz prosty RESTowy serwis, który zwróci informacje

- Identyfikator
- Login
- Nazwa
- Typ
- Url do avatara
- Data stworzenia
- Obliczenia

### API serwisu powinno wyglądać jak poniżej:
```
{
    "id": "...",
    "login": "...",
    "name": "...",
    "type": "...",
    "avatarUrl": "...",
    "createdAt": "...",
    "calculations": "..."
}
```

### Serwis powinien pobierać dane z https://api.github.com/users/{login} (np. https://api.github.com/users/octocat) i przekazać je w API. W polu calculations powinien być zwrócony wynik działania  6 / liczba_followers * (2 + liczba_public_repos).

### Dokumentacja: https://docs.github.com/en/free-pro-team@latest/rest/users/users?apiVersion=2022-11-28#get-a-user

### Serwis powininen zapisywać w bazie danych liczbę wywołań API dla każdego loginu. Baza danych pwoinna zawierać dwie kolumny: LOGIN oraz REQUEST_COUNT powinna być zwiększona o jeden.

### Serwis powinien być zaimplementowany w Java. Projekt powiniene być możliwy do zbudowania za pomocą Maven lub Gradle. Możesz wspierać się dowolnymi, łatwo dostępnymi technologiami (silniki BD, biblioteki, frameworki).

### Pamiętaj o zastosowaniu dobrych praktyk programowania.

### Projekt umieść na dowolnym repozytorium i udostępnij nam link.