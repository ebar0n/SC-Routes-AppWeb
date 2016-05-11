# SC-Routes-AppWeb

## Requerimientos minimos:

0. Los usuarios autorizados del sistema, pueden registrar rutas de transporte público; asi como el precio del pasaje, empresa que la cubre, punto de inicio y fin de la ruta, también las vías principales por donde pasa el bus o buseta, por ejm. Inicio, Terminal de pasajeros, Prolongación 5ta Av. Av. 19 de Abril, punto fin Centro Comercial del Este.

0. Consulta de rutas por parte de los usuarios del sistema, el podrá buscar por punto de inicio, fin o puntos intermedios, a partir de allí se puede visualizar la empresa que cubre la ruta y el costo del pasaje.

0. Como una manera de mejorar el servicio prestado por las empresas de transporte se pone a disposición de los usuarios un buzón de críticas y sugerencias, donde los usuarios podrán dejar alguna queja, sugerencia o comentario, este módulo le solicitará al usuario su nombre, apellido, correo electrónico, teléfono y en caso de que desee referirse a una de las empresas la podrá seleccionar de una lista.

0. El personal de la autorizado podrá visualizar los comentarios realizados por los usuarios desde un listado.

## Desarrollado con JSP [PlayFramework](https://playframework.com/) usando [Ebean-ORM](http://ebean-orm.github.io/docs/).

Probado en **Ubuntu 14.04.4 x64**

0. Pre-requisitos [Java8](https://www.java.com/es/)

		$ add-apt-repository ppa:webupd8team/java
		$ apt-get update
		$ apt-get install oracle-java8-installer
		$ java -version

0. Dev
	
		$ ./bin/activator clean run

0. Producción

		$ ./bin/activator clean stage
		$ ./target/universal/stage/bin/sc-routes &

0. Visitar [127.0.0.1:9000](https://127.0.0.1:9000/)
