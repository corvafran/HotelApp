# HotelApp

Aplicación Android de peliculas realizada como muesta de conceptos utilizando APIs sugeridas de informacion de hoteles.

# Features
Esta app contiene 4 pantallas: una lista de hoteles, detalle del hotel, detalle de los comentarios de los hoteles y el detalle de la imagen del hotel. Tiene Soporte offline para los hoteles de pantalla principal.

# Arquitectura utilizada

Se empleó la arquitectura MVVM (Model-View-ViewModel) como capa de presentación.

https://github.com/corvafran/HotelApp/blob/master/art/viewmodel_diagram.png

Activity / Fragment: Es el encargado de mostrar datos y enviar enventos de UI. La vista y el viewModel se comunican usando LiveData. LiveData es un observable que notifica a la UI por actualizaciones. Es tambien consciente del ciclo de vida y esto evita crashes parando las actualizaciones.
Las clases son:

MainActivity
HotelDetailActivity
PhotoDetailActivity
ReviewsActivity

ViewModel: Prepara los datos para su visualizacion en la UI y reacciona a las interacciones del usuario. La vista se subscribe al correspondiente LiveData.

Las clases son:

MainViewModel
HotelDetailViewModel

Repository: Es el encargado de obtener los datos ya sea localmente o alojado en la nube.

Clase: HotelsRepository

Database: Almacena los datos localmente. Fuente de datos SQLite usando objetos. Se utilizó Room y RxJava

Clase: HotelDatabase

Remote data: Comunicación con el backend usando Retrofit y RxJava

Clase: ApiServices

Inyeccion de dependencias(DI): Es el encargado de proveer las dependencias a las clases que van a ser instanciadas utilizando Dagger2

Clase: AppComponent, Modules

# Bibliotecas Utilizadas
  - Dagger2: Se Utiliza para la inyeccion de dependencias. Se opto por esta biblioteca porque nos deja un codigo mas limpio y modular.
  - Room: ORM para la utilizacion de SQLLite, es el que nos sugiere Android Dev. Tiene la ventaja de que nos permite obtener Observables Rx y también LiveData de las querys.
  - Retrofit: Se utiliza para el intercambio de datos con el servidor. 
  - Glide: Biblioteca de carga de imagenes, la utilizamos para la carga y cacheo de imagenes en la app.

# Testing

El proyecto usa test instrumentacion muy basicas que corren en el dispositivo y pruebas de Unit Testing para probar el correcto funcionamiento del ViewModel.
 - UI Tests: Se empleó Espresso.
 - Database Tests: Se crea una base de datos en memoria
 - Unit Testing: Se utilizo Mockito.
 


