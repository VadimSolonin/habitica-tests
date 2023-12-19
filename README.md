<h1>Проект автоматизации тестирования Habitica.com</h1>

<p align="center">
<img src="images/logo/habitica_logo.png"  >
</p>

## Содержание
+ [Описание](#Описание)
+ [Стек технологий](#Стек-технологий)
+ [Тестовые сценарии](#Реализованные-проверки)
+ [Запуск тестов](#Запуск-тестов)
    + [Допустимые комбинации](#Допустимые-комбинации)
    + [Локальный запуск тестов](#Локальный-запуск-тестов)
    + [Удаленный запуск тестов](#Удаленный-запуск-тестов)
+ [Cборка тестов в Jenkins](#Cборка-тестов-в-Jenkins)
+ [Интеграция с Allure Report](#интеграция-с-allure-report)
    + [Диаграммы прохождения тестов](#Диаграммы-прохождения-тестов)
    + [Развернутый результат прохождения тестов](#Развернутый-результат-прохождения-тестов)
+ [Интеграция с Allure TestOps](#Интеграция-с-Allure-TestOps)
+ [Интеграция с Jira](#Интеграция-с-Jira)
+ [Уведомления в Telegram с использованием бота](#Уведомления-в-Telegram-с-использованием-бота)

## Описание
Habitica — трекер задач, который совместил философию RPG и GTD(доведение дел до завершения) в своей веб-версии и приложениях для Android и iOS.
Проект состоит из UI-тестов, API и мобильных тестов на Android.
Особенности проекта:
- Page Object шаблон проектирования
- Использование техноголии Owner для придания тестам гибкости и легкости конфигурации
- Возможность запуска тестов: локально, удалённо, по тегам
- Использование Faker для генерации данных
- Использование Lombok для моделей в API тестах
- По итогу прохождения автотестов генерируется Allure отчет. Содержание отчета:
    - Шаги теста
    - Скриншот страницы на последнем шаге
    - Исходный код страницы в браузере
    - Логи консоли браузера
    - Видео выполнения автотеста
- Возможность запуска тестов напрямую из Allure TestOps
- Интеграция с Jira
- Уведомление о результатах прохождения в Telegram

## Стек технологий
| IDEA | Java | GitHub | JUnit5 | Gradle | Selenide | Selenoid | Allure | Jenkins | Allure TO| Jira |
| ------ | ------ | ------ | ------ | ------ | ------ | ------ | ------ | ------ | ------ | ------ |
| <a href="https://www.jetbrains.com/idea/"><img src="images/logo/Idea.svg" width="50" height="50"  alt="IDEA" title="vs IDEA"/></a> | <a href="https://www.java.com/"><img src="images/logo/Java.svg" width="50" height="50"  alt="Java" title="vs Java"/></a> | <a href="https://github.com/"><img src="images/logo/GitHub.svg" width="50" height="50"  alt="Github" title="vs Github"/></a> | <a href="https://junit.org/junit5/"><img src="images/logo/Junit5.svg" width="50" height="50"  alt="JUnit 5" title="vs JUnit 5"/></a> | <a href="https://gradle.org/"><img src="images/logo/Gradle.svg" width="50" height="50"  alt="Gradle" title="vs Gradle"/></a> | <a href="https://selenide.org/"><img src="images/logo/Selenide.svg" width="50" height="50" alt="Selenide" title="vs Selenide"/></a>| <a href="https://aerokube.com/selenoid/"><img src="images/logo/Selenoid.svg" width="50" height="50"  alt="Selenoid" title="vs Selenoid"/></a> | <a href="https://github.com/allure-framework/allure2"><img src="images/logo/Allure.svg" width="50" height="50"  alt="Allure" title="vs Allure"/></a> | <a href="https://www.jenkins.io/"><img src="images/logo/Jenkins.svg" width="50" height="50"  alt="Jenkins" title="vs Jenkins"/></a> | <a href="https://docs.qameta.io/allure-testops/"><img width="50" height="50"  alt="AllureTestOps" src="images/logo/Allure_TO.svg" title="vs AllureTestOps"></a> | <a href="https://www.atlassian.com/ru/software/jira"><img width="50" height="50"  alt="Jira" src="images/logo/Jira.svg" title="vs Jira"></a>

## Реализованные проверки
### Web
- [x] Проверка заголовка домашней страницы
- [x] Рендеринг на странице особого элемента, выбранного в фильтре
- [x] Рендеринг на странице особого элемента при поиске его в строке поиска
- [x] Проверка текста всплывающей подсказки на кнопке идентификатора пользователя
- [x] Проверка заголовков страниц, открываемых при нажатии на ссылки выпадающего меню "Инвентарь" (@ParameterizedTest)
- [x] Быстрое добавление привычки в список
- [x] Переадресация на страницу регистарции при нажатии кнопки "Get Started" домашней страницы
- [x] Неудачная регистрация без заполнения всех полей

### Api
- [x] Выполнение успешного запроса на авторизацию
- [x] Выполнение неудачного запроса на вход с пустым паролем
- [x] Выполнение неудачного запроса на вход с пустым телом
- [x] Запрос текущего списка тегов
- [x] Выполнение запроса на удаление тега

### Mobile
- [x] Неудачная попытка регистрации с недействительным адресом электронной почты.
- [x] Неудачная попытка регистрации без заполнения всех полей
- [x] Неудачная попытка регистрации со слишком коротким паролем

### Ручные проверки:
- [x] Быстрое добавление ежедневного дела в список
- [x] Быстрое добавление награды в список

## Запуск тестов:

### Допустимые комбинации

```mermaid 
flowchart LR
    A(gradle) --> B(clean)
    B --> C{Выбрать тег}
    C --> D[test]
    C --> E[web]
    C --> F[api]
    C --> G[android]
    E --> H[-DenvWeb=local]
    E --> I[-DenvWeb=remote]
    G --> J[-DenvMobile=browserstack]
    G --> K[-DenvMobile=emulator]
    G --> L[-DenvMobile=localDevice]
```

### Локальный запуск тестов

#### Запуск всех тестов

```
gradle clean test
```

#### WEB

```
gradle clean web
```


#### API
```
gradle clean api 
```

#### Mobile

```
gradle clean android -DenvMobile=${ENV_MOBILE}
```
Для запуска мобильных тестов нужно определить значение envMobile:
- [ ] <code>-DenvMobile=browserstack</code> : тесты будут запущены в облачной платформе <a target="_blank" href="https://www.browserstack.com/"> Browserstack </a> 
- [ ] <code>-DenvMobile=emulator</code> : тесты будут запущены в эмуляторе, созданном средствами Appium Server & Appium Inspector. <br/> <a target="_blank" href="https://autotest.how/appium-setup-for-local-android-tutorial"> Инструкция по настройке </a> 
- [ ] <code>-DenvMobile=localDevice</code> : тесты будут запущены на устройстве, подключенному по usb. <br/> Так же требуется настройка Appium Server & Appium Inspector

<details>
   <summary>Дополнительные команды:</summary>
  
1. Выполнить запрос на формирование отчета:
```
gradle allureReport
```
2. Открыть отчет в браузере:
```
gradle allureServe
```

</details>

### Удаленный запуск тестов

```
clean
${TASK}
-DbrowserName=${BROWSER}
-DbrowserVersion=${BROWSER_VERSION}
-DbrowserSize=${BROWSER_SIZE}
-DremoteUrl=${REMOTE_URL}
"-DenvMobile=${ENV_MOBILE}"
```
>  `${TASK}` - запускаемая группа тестов. `test` запустит все тесты . `web` `api` `android` запустит тесты, отмеченные соответствующим тегом 
> 
> `${BROWSER}` - наименование браузера. По умолчанию <code>chrome</code>
> 
> `${BROWSER_VERSION}` - номер версии браузера. По умолчанию <code>100.0</code>
> 
> `${BROWSER_SIZE}` - размер окна браузера. По умолчанию - <code>1980x1080</code>
>
> `${REMOTE_URL}` - адрес удаленного сервера, на котором будут запускаться тесты
>
> `${ENV_MOBILE}` - переменная определения среды для запуска мобильных тестов

## Cборка тестов в <b><a target="_blank" href="https://jenkins.autotests.cloud/job/C22-VadimSolonin-habitica-project/">Jenkins</a></b>
<img src="images/screenshots/jenkins-project.png">

## Интеграция с <b><a target="_blank" href="https://jenkins.autotests.cloud/job/C22-VadimSolonin-habitica-project/13/allure/">Allure report</a></b>
#### Диаграммы прохождения тестов:
<img src="images/screenshots/allure-main-report.png">

#### Развернутый результат прохождения тестов:
1. Общий список автотестов
2. Содержание автотеста
3. Вложения
<img src="images/screenshots/allure-suites.png">


## Интеграция с <b><a target="_blank" href="https://allure.autotests.cloud/project/3876/dashboards">Allure TestOps</a></b>
Диаграммы прохождения тестов:
<img src="images/screenshots/allure-testops-dashboards.png">

## Интеграция с <b><a target="_blank" href="https://jira.autotests.cloud/browse/HOMEWORK-1005">Jira</a></b>
<img src="images/screenshots/jira-integration.png">

## Уведомления в Telegram с использованием бота
<img src="images/screenshots/telegram-notification.png">

