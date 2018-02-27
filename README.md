Evaluación técnica JAVA Developer (SSr y Sr).

Broker API REST.

###-La API esta realizada sobre SpringBoot 1.5.10.

###-Para la comunicación con el COTIZADOR (jar entregado) se uso RestTemplate, el mismo esta configurado en application.properties con los siguientes valores:
	
	### Cotizador Service
	cotizador.service.api.url=http://localhost
	cotizador.service.api.port=8888
	cotizador.service.api.op=stocks
	
Por lo tanto tiene que levantarse el jar del COTIZADOR de la siguiente manera:  
	* `$ java -DPORT="8888" -jar cotizador.jar`
(Si desea levantar en otro puerto recordar cambiar la propertie).

###-Para correr la aplicacion de manera local, correr:
	``mvn clean install```
Luego se puede levantar corriendo:
	``mvn spring-boot:run``

###-La API expone los siguientes recursos:
Mapped "{[/api/status],methods=[GET],produces=[application/json]}"
Mapped "{[/api/stocks/{ticker}],methods=[GET],produces=[application/json]}"
Mapped "{[/api/stocks],methods=[POST],produces=[application/json]}"

#GET: http://localhost:8080/api/stocks/GOOGL
#RESP:
		200 OK {"name": "Google","ticker": "GOOGL","price": 224.18886}			
		404 Not Found {"statusCode": "NOT_FOUND","message": "Error trying to obtain price from COTIZADOR"}
		
#GET: http://localhost:8080/api/stocks/GOOGL-INEXISTENTE
#RESP: 
		404 Not Found {"statusCode": "NOT_FOUND","message": "Ticker don't Exist"}
		
#POST: http://localhost:8080/api/stocks/
#BODY:{
	"listStocks": [
      {"ticker":"BABA","amount":900},
      {"ticker":"AAPL","amount":10000},
      {"ticker":"GOOGL","amount":900}
    ]
}
#RESP: 
		500 Internal Server Error {"statusCode": "INTERNAL_SERVER_ERROR","message": "Stock insuficiente"}
		(No alcanza el stock para vender todas las acciones de APPL, ya que el Stock es de 25 y se quieren comprar 42 según cotización actual)


#POST: http://localhost:8080/api/stocks/
#BODY:{
	"listStocks": [
      {"ticker":"BABA","amount":900},
      {"ticker":"AAPL","amount":900},
      {"ticker":"GOOGL","amount":900}
    ]
}
#RESP: 
		200 OK {"stocksBuyed": {"BABA": 3,"GOOGL": 4,"AAPL": 3}}

En consola se puede ver el Stock antes de realizar la compra y despues de la misma, tambien se puede ver el precio obtenido del cotizador para cada acción:

Stocks before purchase = -{MSFT=7, BABA=49, GOOGL=175, AAPL=25, GDDY=65, EBAY=150, IBM=10, YHOO=5, FB=500, AMZN=200}
Stocks after purchase = -{MSFT=7, BABA=46, GOOGL=171, AAPL=22, GDDY=65, EBAY=150, IBM=10, YHOO=5, FB=500, AMZN=200}
CotizadorServiceResponse [ticker=BABA, value=256.09686] ==>> Por lo tanto se pueden comprar 900/256.09 = 3 acciones de BABA
CotizadorServiceResponse [ticker=AAPL, value=237.74536] ==>> Por lo tanto se pueden comprar 900/237.74 = 3 acciones de AAPL
CotizadorServiceResponse [ticker=GOOGL, value=218.40239] =>> Por lo tanto se pueden comprar 900/218.40 = 4 acciones de GOOGL




