# keeptrack
### О Проекте
**keeptrack** - web-приложение для учета и планирования рабочего времени на проектах.

### Описание предметной области
Компания (Company)занимается разработкой программного обеспечения. Для заказчиков (Customer)выполняются проекты (Project). 
У проекта есть дата начала и окончания. Проект состоит из фаз (Sprint). Следующая фаза может начинаться только после завершение предыдущей. Фазы состоят из задач (Task). 
У задач есть прогнозируемое время выполнения в часах (Estimate). Руководитель (Project  Manager) назначает задачи сотрудникам (Employee), указывая дату и время выполнения.
Сотрудники подтверждают получение задачи, фиксируют ее выполнение (с фактическим временем выполнения).

### Роли
**Administrator** – создает в системе такие объекты как Employee, Customer, Project, назначает Project Manager.  
**Project Manager** – управляет проектом, создает фазы, задачи, назначает задачи сотрудникам.  
**Employee** – подтверждает получение задания, фиксирует факт выполнения задания, подает запрос об изменении времени выполнения.  
**Customer** – мониторит прогресс выполнения своего проекта.  

### Стек технологий
**Java:**
- Java 8 
- большая часть бизнесс-логики реализована с помощью Lambda, Stream API
- также для дат использовался Java 8 Date API

**Spring:**
- Spring Core
- Spring MVC
- Spring Security
- Spring Data JPA

**Build tools:**
- Maven
- Maven checkstyle plugin
- Maven PMD plugin
- Maven FindBugs

**Continuous Integration:**
- Travis CI (вышеуказанные плагины использовались в связке с данным CI) 

**WEB:**
- Spring MVC
- Java Servlet API
- JSP. JSTL
- HTML, CSS, JavaScript
- JQuery
- Bootstrap 3
- Apache Tiles

**JPA, ORM:**
- Spring Data JPA 
- DAO писать умею :), но т.к. одной из главных целей проекта было изучение Spring - решил использовать Spring Data JPA
- Hibernate 5

**Databases:**
- PostgreSQL - как основная БД
- HSQL - для тестирования

**Security, validation:**
- Spring security
- Spring security taglibs
- Hibernate validatior

**Testing:**
- JUnit 4
- Mockito
- Старался покрывать тестами как мог :)

**VCS:**
- Git + GitHub

**Deploy:**
- Heroku Cloud Application Platform



