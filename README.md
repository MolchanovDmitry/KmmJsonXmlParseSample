# Простой пример парсинга JSON и работы с БД на android и ios.
Парсинг XML работает пока только под android.

### Приемущества:
* Код Domain и Data можно написать 1 раз на 4 платформы.
* Тесты также пишутся 1 раз.
### Недостатки:
* Более долгая сорка.
* Новая технология. Необходимо время на ее исследование.

## Как запустить iOS проект?
* Запустить Xcode
* Открыть workspace файл директории iosApp

## Примечания.
* [Мультиплатформенные библиотеки](https://github.com/AAkira/Kotlin-Multiplatform-Libraries)
* [Кто еще использует KMM](https://kotlinlang.org/lp/mobile/case-studies)
* [Особенности мультипоточки в нативе](https://github.com/Kotlin/kotlinx.coroutines/issues/462)
* Асинхронность сетевых запросов ktor на iOS достигается компилированием KMM кода в NSURLConnection, NSURLSession.. 