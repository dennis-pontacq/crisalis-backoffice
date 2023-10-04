const orderService = "order";
const productandserviceListaService = "productandservice";
const cutomerandcompanyService = "cutomerandcompany";
var orderTemp;

function getproductandserviceList(){
  let list;
  getServiceSync(productandserviceListaService+"/list", (response) => list= response  );
  return list;
}

function getcutomerandcompanyList(){
  let list;
  getServiceSync(cutomerandcompanyService+"/list", (response) => list= response  );
  return list;
}

function drawOrderList(orderList){

 let gridContainer = document.getElementById("gridContainer");

 let grid =" <h2>Pedidos</h2>"+
            "<button type='button' class='btn btn-primary' data-bs-toggle='modal' data-bs-target='#myModal' onclick='newOrder()'>Nuevo</button>"+
            "<table class='table table-hover'><thead><tr><th>Código</th><th>Fecha</th><th>Cliente </th><th>Total </th></tr></thead> <tbody>";

  for( let pedido of orderList){
    grid += "<tr  data-bs-toggle='modal' data-bs-target='#myModal' onclick= 'showOrder( "+pedido.id+" )' id='"+pedido.id+"'  >"+
            "<td>Pedido - "+pedido.id+"</td><td>"+pedido.fecha+"</td><td>"+pedido.clienteNombre+"</td><td>"+pedido.montoTotal+"</td></tr>";
  }
  grid += "</tbody></table>";
  gridContainer.innerHTML=grid;
}

function showOrderList(dummy){
  getService(orderService+"/list", drawOrderList );
}

function showOrder(id) {
  getService(orderService, drawOrder, id );
}

function drawOrder(order){
  orderTemp = order;
  let taxesList =  getTaxesList();
  let productandserviceList =  getproductandserviceList();
  let cutomerandcompanyList =  getcutomerandcompanyList();

  let header ="<div class='modal-header'>"+
              "<h4 class='modal-title'>"+( order.id == 0 ? "Pedido Nuevo" : "Pedido - "+order.id)+"</h4>"+
              "<button type='button' class='btn-close' data-bs-dismiss='modal'></button></div>";
      
  let footer ="</div><div class='modal-footer'>"+
              ( order.id == 0 ? "" : "<button type='button' class='btn btn-primary' data-bs-dismiss='modal' onclick= 'deleteOrder("+order.id+")' >Borrar</button>" )+
              "<button type='button' class='btn btn-primary' data-bs-dismiss='modal' onclick= 'saveOrder("+order.id+")' >Guardar</button></div>";

  let body = "<div class='modal-body row row-cols-2'>";

  body += "<div class='mb-3 mt-3 col'>"+
          "<label for='fecha' class='form-label'>Fecha</label>"+
          "<input type='date' class='form-control' id='fechaform' placeholder='Ingrese Fecha' onChange='changeFecha()' value='"+(order.fecha==null ? "" : order.fecha) +
          "' name='fecha'></div>";

  body += "<div class='mb-3 mt-3 col'>"+
          "<label for='clienteForm' class='form-label'>Cliente</label>"+
          "<select class='form-control' id='clienteform' name='clienteForm' onChange='changeCliente()' ><option value=0 ></option>";
          for( let customer of cutomerandcompanyList ){
            if( order.customerAndCompanyID.toString() == customer.id.toString() ){
              body += "<option value="+customer.id+" selected>"+customer.nombre+ "</option>";
            }else {
              body += "<option value="+customer.id+" >"+customer.nombre+"</option>";
            }
          }
  body += "</select></div>";

  body += "<div class='mb-3 mt-3 col'>"+
          "<label for='montoTotal' class='form-label'>Total</label>"+
          "<input type='number' class='form-control' id='montoTotal' placeholder='Total' onChange='changeMontoTotal()' value='"+(order.montoTotal==null ? "" :order.montoTotal)+
          "' name='montoTotal'></div>";

  body += "<div class='mb-3 mt-3 col'></div>";


  body += "<div class='mb-3 mt-3 col'> <div class='row'> <div class='col-10'> Productos / Servicios </div>"+
		  " <div class='col-2'><button type='button' class='btn btn-light' onclick= 'newItem()' >+</button> </div> </div>";

  body += "<table class='table table-hover' ><thead><tr><th>Nombre</th><th>Precio </th> <th>Cantidad </th><th></th>"+
          "</tr></thead> <tbody id='productsAndServices'>";
/*
            "productID": "",
            "quantity": 2.5,
            "additionalCost":[ 2 ]
*/
  for (  let itemPedido of order.productsAndServices ){

    let itemProducto = getFilteredByKey( productandserviceList, "id", itemPedido.productID )[0];

//data-bs-toggle='modal' data-bs-target='#myModal2'  onclick= 'showOrderItem( "+itemPedido.productID+" )''

  body +="<tr  id='"+itemProducto.id+"'   >"+
              "<td onclick= 'showOrderItem( "+itemPedido.productID+" )' >"+itemProducto.nombre+"</td>"+ 
              "<td onclick= 'showOrderItem( "+itemPedido.productID+" )'  >"+itemProducto.precio+"</td>"+
              "<td onclick= 'showOrderItem( "+itemPedido.productID+" )' >"+itemPedido.quantity+"</td>"+
              "<td onclick= 'removeItem(this)' class='text-black-50' >\u00D7</td></tr>";
  }
  
  body +=" </tbody></table>  </div>";




  body += "<div class='mb-3 mt-3 col'> <div class='row'> <div class='col-6'> Cargos adicionales </div> "+
          "<div class='col-4'> <select  class='form-control form-control-sm' id='addTax' name=''>";

          for( let tax of taxesList ){
                body += "<option value="+tax.id+" >"+tax.nombre+"</option>";
            }
  body += "</select> </div> <div class='col-2'><button type='button' class='btn btn-light' onclick= 'addTax()' >+</button> </div> </div>"+
          "<table class='table table-hover' ><thead><tr><th>Nombre</th><th>Precio </th> <th>Porcentaje </th><th></th>"+
          "</tr></thead> <tbody id='cargosAdicionalesProducto'>";

  for (  let taxID of order.additionalCost ){

    let tax = getFilteredByKey( taxesList, "id", taxID )[0];

     body +="<tr  id='"+tax.id+"' ><td>"+tax.nombre+"</td><td>"+tax.precio+"</td><td>"+tax.porc+
     "</td><td onclick= 'removeTax(this)' class='text-black-50' >\u00D7</td></tr>";
  }
  
  body +=" </tbody></table>  </div>";


  document.getElementById("myModelContent").innerHTML=header+body+footer;
}

function showOrderItem(itemPedidoID){

let itemProducto = getFilteredByKey( orderTemp.productsAndServices, "productID", itemPedidoID )[0];
drawOrderItem(itemProducto);


}

function drawOrderItem(orderItem){

  let taxesList =  getTaxesList();
  let productandserviceList =  getproductandserviceList();

  let itemProducto = getFilteredByKey( productandserviceList, "id", orderItem.productID )[0];

  let header ="<div class='modal-header'>"+
              "<h4 class='modal-title'>"+( orderItem.productID == 0 ? "Item Nuevo" : "Item")+"</h4>"+
              "<button type='button' class='btn-close' onclick= 'cancelOrderItem()'></button></div>";

  let footer ="</div><div class='modal-footer'>"+
              ( orderItem.productID  == 0 ? "" : "<button type='button' class='btn btn-primary' onclick= 'deleteOrderItem()' >Borrar</button>" )+
              "<button type='button' class='btn btn-primary'  onclick= 'saveOrderItem()' >Guardar</button></div>";

    let body = "<div class='modal-body row row-cols-2'> ";   

    body += "<div class='mb-3 mt-3 col'>"+
          "<label for='nombre' class='form-label'>Producto / Servicio</label>"+
          "<select class='form-control' id='productoform' name='productoform' onChange='changeProduct()'><option value=0 ></option>";
          for( let producto of productandserviceList ){
            if( producto.id.toString() == orderItem.productID.toString() ){
              body += "<option value="+producto.id+" selected>"+producto.nombre+"</option>";
            }else {
                let productInOrder = getFilteredByKey( orderTemp.productsAndServices, "productID", producto.id )[0];
                if ( productInOrder == null ) 
                  body += "<option value="+producto.id+" >"+producto.nombre+"</option>";
            }
          }
    body += "</select></div>";
 
    body += "<div class='mb-3 mt-3 col'>"+
          "<label for='precio' class='form-label'>Precio</label>"+
          "<input type='number' readonly class='form-control' id='precioform' placeholder='Precio' value='"+(itemProducto==null ? 0 :itemProducto.precio)+
          "' name='montoTotal'></div>";


    body += "<div class='mb-3 mt-3 col'>"+
          "<label for='catidadform' class='form-label'>Cantidad</label>"+
          "<input type='number' class='form-control' id='catidadform' placeholder='Cantidad' value='"+(orderItem==null ? 0 :orderItem.quantity)+
          "' name='catidadform'></div>";

  

  body += "<div class='mb-3 mt-3 col'> <div class='row'> <div class='col-6'> Cargos adicionales </div> "+
          "<div class='col-4'> <select  class='form-control form-control-sm' id='addTax' name=''>";

          for( let tax of taxesList ){
                body += "<option value="+tax.id+" >"+tax.nombre+"</option>";
            }
  body += "</select> </div> <div class='col-2'><button type='button' class='btn btn-light' onclick= 'addTax()' >+</button> </div> </div>"+
          "<table class='table table-hover' ><thead><tr><th>Nombre</th><th>Precio </th> <th>Porcentaje </th><th></th>"+
          "</tr></thead> <tbody id='cargosAdicionalesProducto'>";

  for (  let taxID of orderItem.additionalCost ){

    let tax = getFilteredByKey( taxesList, "id", taxID )[0];

     body +="<tr  id='"+tax.id+"' ><td>"+tax.nombre+"</td><td>"+tax.precio+"</td><td>"+tax.porc+
     "</td><td onclick= 'removeTax(this)' class='text-black-50' >\u00D7</td></tr>";
  }
  
  body +=" </tbody></table>  </div>";



    body += "</div>";       

  document.getElementById("myModelContent").innerHTML=header+body+footer;

}


function changeProduct() {

  let productID = document.getElementById("productoform").value ;
  let productandserviceList =  getproductandserviceList();
  let product = getFilteredByKey( productandserviceList, "id", productID )[0];

	let orderItem = {"productID": productID,"quantity":0,"additionalCost":product.taxes};

	drawOrderItem(orderItem);
}

function newItem(){

  let orderItem = {"productID": 0,"quantity":0,"additionalCost":[]};
  drawOrderItem(orderItem);
}


function deleteOrderItem(){
  let productID = document.getElementById("productoform").value ;
  let product = getFilteredByKey( orderTemp.productsAndServices, "productID", productID )[0];

  if ( product != null ) {
    let newItemList = [];
    for( item of orderTemp.productsAndServices ){
      if ( item.productID.toString() != productID.toString() )
        newItemList.push(item);
    }
    orderTemp.productsAndServices = newItemList;
  }
  drawOrder(orderTemp);
}

function saveOrderItem(){

  let productID = document.getElementById("productoform").value ;
  let product = getFilteredByKey( orderTemp.productsAndServices, "productID", productID )[0];

  let quantity = document.getElementById("catidadform").value ;
  let listTaxes = document.getElementById("cargosAdicionalesProducto");
  let taxes = [];

  for (  let taxRow of listTaxes.children ){
    taxes.push(taxRow.id);
  }

  if ( product == null ) {
   orderTemp.productsAndServices.push({"productID": productID,"quantity": quantity,"additionalCost":taxes} );

  } else {
   let index = orderTemp.productsAndServices.indexOf(product);
   orderTemp.productsAndServices[index] = {"productID": productID,"quantity": quantity,"additionalCost":taxes};

  }
  
  drawOrder(orderTemp);
}

function cancelOrderItem() {
  drawOrder(orderTemp);
}

function saveOrder (id) {

  let fecha = document.getElementById("fechaform").value;
  let montoTotal = document.getElementById("montoTotal").value;
  let customerAndCompanyID = document.getElementById("clienteForm").value;

  let listTaxes = document.getElementById("cargosAdicionalesProducto");
  let taxes = [];

  for (  let taxRow of listTaxes.children ){
    taxes.push(taxRow.id);
  }

  let order = { "id":id, "fecha": fecha , "montoTotal": montoTotal,  "customerAndCompanyID": customerAndCompanyID, "productsAndServices": orderTemp.productsAndServices , "additionalCost":taxes}
  orderTemp = null;
  setService(orderService, showOrderList, id, order );

}

function deleteOrder (id) {
  deleteService( orderService, showOrderList, id );
}

function changeCliente() {
	let customerAndCompanyID = document.getElementById("clienteForm").value;
	orderTemp.customerAndCompanyID = customerAndCompanyID;
}

function changeFecha() {
	let fecha = document.getElementById("fechaform").value;
	orderTemp.fecha = fecha;
}

function changeMontoTotal() {
	let montoTotal = document.getElementById("montoTotal").value;
	orderTemp.montoTotal = montoTotal;
}



