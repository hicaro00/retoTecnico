
## Dominio

### Tipo de Cambio (Exchange Rate)

***Entidad***: Representa un tipo de cambio entre dos monedas.
   
***Atributos***: Valor del tipo de cambio, moneda origen, moneda destino.


### Servicio de Dominio:
   servicio que realiza la conversión de moneda utilizando el 
   tipo de cambio actual.


### Repositorios:
   ***Repositorio de Tipo de Cambio:***

   Interactúa con la base de datos para recuperar y almacenar tipos de cambio.

### Servicios de Aplicación:
 ***Servicio de Conversión:***

   Utiliza el tipo de cambio para realizar la conversión de moneda.



###  Los controladores deben centrarse en recibir solicitudes, validarlas y orquestar las llamadas a los servicios de aplicación
***Pruebas:***
   Realiza pruebas unitarias garantizar que tus componentes funcionen como se espera.
   