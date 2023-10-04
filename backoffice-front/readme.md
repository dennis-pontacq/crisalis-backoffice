--------------------------------------------------------
  TAXES
--------------------------------------------------------  

GET - http://localhost:8081/sales-order/taxes/list?token=

RESPONSE:
[
  {
    "id": 1, 
    "nombre": "IVA", 
    "porc": 21, 
    "precio": 0
  }, 
  {
    "id": 2, 
    "nombre": "IIBB", 
    "porc": 3.5, 
    "precio": 0
  }, 
  {
    "id": 3, 
    "nombre": "Soporte", 
    "porc": 0, 
    "precio": 50
  }
]

---------------------------------------------------
Servicio
---------------------------------------------------

GET - http://localhost:8081/sales-order/service/list?token=

RESPONSE:
[
  
  {
    "id": 3, 
    "nombre": "soporte", 
    "precio": "800", 
    "taxes": [
      "1"
    ]
  }, 
  {
    "id": 4, 
    "nombre": "Asistencia", 
    "precio": 100, 
    "taxes": [
      1, 
      2, 
      3
    ]
  }
]
---------------------------------------------------
GET - http://localhost:8081/sales-order/service/id?token=

RESPONSE:

{
  "id": 4, 
  "nombre": "Asistencia", 
  "precio": 100, 
  "taxes": [
    1, 
    2, 
    3
  ]
}

---------------------------------------------------
POST - http://localhost:8081/sales-order/service?token=

BODY: 
{
  "id": 0,
  "nombre": "soporte",
  "precio": "",
  "taxes": [1]
}

---------------------------------------------------
PUT - http://localhost:8081/sales-order/service/id?token=

BODY:
{
  "id": 3,
  "nombre": "soporte",
  "precio": "800",
  "taxes": [
    1
  ]
}
---------------------------------------------------
DELETE - http://localhost:8081/sales-order/service/id?token=


---------------------------------------------------
PRODUCTO
---------------------------------------------

GET - http://localhost:8081/sales-order/product/list?token=

RESPONSE:
[
  {
    "id": 1, 
    "nombre": "Silla", 
    "precio": 250, 
    "taxes": [
      1
    ]
  }, 
  {
    "id": 3, 
    "nombre": "Camas", 
    "precio": "800", 
    "taxes": [
      "1"
    ]
  }, 
  {
    "id": 4, 
    "nombre": "Asistencia", 
    "precio": 100, 
    "taxes": [
      1, 
      2, 
      3
    ]
  }
]
---------------------------------------------------
GET - http://localhost:8081/sales-order/product/id?token=

RESPONSE:

{
  "id": 4, 
  "nombre": "Asistencia", 
  "precio": 100, 
  "taxes": [
    1, 
    2, 
    3
  ]
}

---------------------------------------------------
POST - http://localhost:8081/sales-order/product?token=

BODY: 
{
  "id": 0,
  "nombre": "asdasd",
  "precio": "",
  "taxes": []
}

---------------------------------------------------
PUT - http://localhost:8081/sales-order/product/id?token=

BODY:
{
  "id": 3,
  "nombre": "Camas",
  "precio": "800",
  "taxes": [
    "1"
  ]
}
---------------------------------------------------
DELETE - http://localhost:8081/sales-order/product/id?token=




---------------------------------------------------
   EMPRESAS   
---------------------------------------------

GET -  http://localhost:8081/sales-order/client/company/list?token=

RESPONSE:
[
  {
    "cuit": "20-31563531-8", 
    "fechaInicio": "2020-05-17", 
    "id": 1, 
    "personaFisica": 0, 
    "razonSocial": "IKEA"
  }, 
  {
    "cuit": "21-31563531-9", 
    "fechaInicio": "2020-11-17", 
    "id": 2, 
    "personaFisica": 1, 
    "razonSocial": "DUMMY S.A."
  }, 
  {
    "cuit": "20-38563531-8", 
    "fechaInicio": "2020-09-17", 
    "id": 3, 
    "personaFisica": 3, 
    "razonSocial": "ROTTEM"
  }
]
---------------------------------------------------

GET - http://localhost:8081/sales-order/client/company/id?token=

RESPONSE:
{
  "cuit": "21-31563531-9", 
  "fechaInicio": "2020-11-17", 
  "id": 2, 
  "personaFisica": 1, 
  "razonSocial": "DUMMY S.A."
}
---------------------------------------------------

POST - http://localhost:8081/sales-order/client/company?token=

BODY:
{
  "id": 0,
  "razonSocial": "nombre",
  "fechaInicio": "2022-11-24",
  "cuit": "asd",
  "personaFisica": "2"
}

---------------------------------------------------
PUT - http://localhost:8081/sales-order/client/company/id?token=

{
  "id": 3,
  "razonSocial": "ROTTEM2",
  "fechaInicio": "2020-09-17",
  "cuit": "20-38563531-8",
  "personaFisica": "3"
}

---------------------------------------------------
DELETE - http://localhost:8081/sales-order/client/company/id?token=





---------------------------------------------------
   CLIENTES   
---------------------------------------------------
 GET - http://localhost:8081/sales-order/client/person/list?token=

RESPONSE:
[
  {
    "surname": "Doe", 
    "DNI": 31563531, 
    "id": 1, 
    "nombre": "John"
  }, 
  {
    "surname": "Moe", 
    "DNI": 31563531, 
    "id": 2, 
    "nombre": "Mary"
  }, 
  {
    "surname": "Dooley", 
    "DNI": 38563531, 
    "id": 3, 
    "nombre": "July"
  }
]

---------------------------------------------------

GET - http://localhost:8081/sales-order/client/person/id?token=

RESPONSE:
{
  "surname": "Moe", 
  "DNI": 31563531, 
  "id": 2, 
  "nombre": "Mary"
}

---------------------------------------------------

POST - http://localhost:8081/sales-order/client/person?token=

BODY:
{
  "surname": "Moe", 
  "DNI": "31563531", 
  "id": "2", 
  "nombre": "Mary"
}

---------------------------------------------------
PUT - http://localhost:8081/sales-order/client/person/id?token=

BODY:
{
  "surname": "Moe", 
  "DNI": "31563531", 
  "id": "2", 
  "name": "Mary"
}

---------------------------------------------------
DELETE - http://localhost:8081/sales-order/client/person/id?token=


---------------------------------------------------
   product   + service
---------------------------------------------------
GET -  http://localhost:8081/sales-order/productandservice/list?token=
[ 
{
  "id": 4, 
  "name": "Asistencia", 
  "precio": 100, 
  "taxes": [
    1, 
    2, 
    3
  ]
},
{
  "id": 3,
  "name": "Camas",
  "precio": "800",
  "taxes": [
    "1"
  ]
}
]

---------------------------------------------------
   cutomer  +  company    
---------------------------------------------------
GET -  http://localhost:8081/sales-order/cutomerandcompany/list?token=

RESPONSE: 
[ 
  {
    "id": 1, 
    "name": "joe"
  }, 
  {
    "id": 2, 
    "name": DUMMY S.A.
  }, 
  {
    "id": 5, 
    "name":  Rose
  }
]

---------------------------------------------------
   PEDIDOS   
---------------------------------------------

GET -  http://localhost:8081/sales-order/order/list?token=

RESPONSE:
[ 
  {
    "id": 1, 
    "fecha": "2020-05-17", 
    "clientename": joe, 
    "montoTotal": 505
  }, 
  {
    "id": 2, 
    "fecha": "2020-05-17", 
    "clientename": joe, 
    "montoTotal": 505
  }, 
  {
    "id": 2, 
    "fecha": "2020-05-19", 
    "clientename": Rose, 
    "montoTotal": 9000
  }
]

---------------------------------------------------

GET - http://localhost:8081/sales-order/order/id?token=

RESPONSE:
{
    "id": "",
    "fecha": "yyyy-MM-dd",
    "customerAndCompanyID": 3, 
    "productsAndServices": [
        {
            "productID": "",
            "quantity": 2.5,
            "additionalCost":[ 2 ]
        },
        {
            "productID": "",
            "quantity": 4,
            "additionalCost":[]
        }
    ],
    "additionalCost":[1,3],
    "montoTotal" : 500
}



---------------------------------------------------

POST - http://localhost:8081/sales-order/order?token=
BODY:
{
    "fecha": "yyyy-MM-dd",
    "customerAndCompanyID":2,
    "productsAndServices": [
        {
            "productID": 3,
            "quantity": 2.5,
            "additionalCost":[ 2]
        },
        {
            "productID": 2,
            "quantity": 4,
            "additionalCost":[]
        }
    ],
    "additionalCost":[]
}


PUT - http://localhost:8081/sales-order/order?token=
BODY:
{
    "id": "",
    "fecha": "yyyy-MM-dd",
    "customerAndCompanyID": 4, 
    "productsAndServices": [
        {
            "productID": "",
            "quantity": 2.5,
            "additionalCost":[2,1]
        },
        {
            "productID": "",
            "quantity": 4,
            "additionalCost":[]
        }
    ],
    "additionalCost":[]
}










