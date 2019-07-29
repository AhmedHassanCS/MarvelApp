# Marvel characters **Android Kotlin** application
Application has three features and they are:
- View Marvel characters with loading more capability.
- Search Marvel characters by name
- View character details which are (Name, Description, Comics, Series, Stories, Events, Web links).

## Architecture
Project is built in **Clean Architecture**. It's divided into three layers (Presentation, Domain, Data).
The project also uses **MVVM design pattern** to handle the front side.

## Android Architecture Components
Project uses the following Android Architecture Components:
1. ViewModel
2. LiveData
3. Navigation
4. Room

## Network
Project uses the following tools to handle remote fetching
1. Retrofit
2. Picasso
3. Kotlin Coroutines (suspend functions in integration with retrofit)

##  Dependency Injection
project uses **Dagger2** for dependency injection. Also uses **Singleton design pattern** to provide Retrofit

##  Unit Testing
Application contains one unit test ApiAccessTest which tests the response of one function that fetchs data from server

##  All Tools
* Kotlin Coroutines
* Dagger2
* Retrofit2
* Navigation
* ViewModel
* LiveData
* Room
* Picasso