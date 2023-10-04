const productService = "inventory/product";
const taxesService = "taxes/list";


function getTaxesList(){
  let list;
  getServiceSync(taxesService, (response) => list= response  );
  return list;
}

function getProductList(){
  let list;
  getServiceSync(productService+"/list", (response) => list= response  );
  return list;
}


function drawProductList(productList){

 let gridContainer = document.getElementById("gridContainer");

 let grid =" <h2>Productos</h2>"+
            "<button type='button' class='btn btn-primary' data-bs-toggle='modal' data-bs-target='#myModal' onclick='newProduct()'>Nuevo</button>"+
            "<table class='table table-hover'><thead><tr><th>Nombre</th><th>Precio </th></tr></thead> <tbody>";

  for( let product of productList){
    grid += "<tr  data-bs-toggle='modal' data-bs-target='#myModal' onclick= 'showProduct( "+product.id+" )' id='"+product.id+"'  >"+
            "<td>"+product.nombre+"</td><td>"+product.precio+"</td></tr>";
  }
  grid += "</tbody></table>";
  gridContainer.innerHTML=grid;
}

function showProductList(dummy){
  getService(productService+"/list", drawProductList );
}




function drawProduct(product){
  let taxesList =  getTaxesList();

  let header ="<div class='modal-header'>"+
              "<h4 class='modal-title'>"+( product.id == 0 ? "Producto Nuevo" : product.nombre)+"</h4>"+
              "<button type='button' class='btn-close' data-bs-dismiss='modal'></button></div>";
      
  let footer ="</div><div class='modal-footer'>"+
              ( product.id == 0 ? "" : "<button type='button' class='btn btn-primary' data-bs-dismiss='modal' onclick= 'deleteProduct("+product.id+")' >Borrar</button>" )+
              "<button type='button' class='btn btn-primary' data-bs-dismiss='modal' onclick= 'saveProduct("+product.id+")' >Guardar</button></div>";

  let body = "<div class='modal-body row row-cols-2'>";

  body += "<div class='mb-3 mt-3 col'>"+
          "<label for='nombre' class='form-label'>Nombre</label>"+
          "<input type='text' class='form-control' id='nombreform' placeholder='Ingrese Nombre' value='"+(product.nombre==null ? "" : product.nombre) +
          "' name='nombre'></div>";


  body += "<div class='mb-3 mt-3 col'>"+
          "<label for='precio' class='form-label'>Precio</label>"+
          "<input type='number' class='form-control' id='precioform' placeholder='Ingrese el precio' value='"+(product.precio==null ? "" :product.precio)+
          "' name='precio'></div>";


  body += "<div class='mb-3 mt-3 col'> <div class='row'> <div class='col-6'> Cargos adicionales </div> "+
          "<div class='col-4'> <select  class='form-control form-control-sm' id='addTax' name=''>";

          for( let tax of taxesList ){
                body += "<option value="+tax.id+" >"+tax.nombre+"</option>";
            }
  body += "</select> </div> <div class='col-2'><button type='button' class='btn btn-light' onclick= 'addTax()' >+</button> </div> </div>"+
          "<table class='table table-hover' ><thead><tr><th>Nombre</th><th>Precio </th> <th>Porcentaje </th><th></th>"+
          "</tr></thead> <tbody id='cargosAdicionalesProducto'>";

  for (  let taxID of product.taxes ){

    let tax = getFilteredByKey( taxesList, "id", taxID )[0];

     body +="<tr  id='"+tax.id+"' ><td>"+tax.nombre+"</td><td>"+tax.precio+"</td><td>"+tax.porc+
     "</td><td onclick= 'removeTax(this)' class='text-black-50' >\u00D7</td></tr>";
  }
  
  body +=" </tbody></table>  </div>";


  document.getElementById("myModelContent").innerHTML=header+body+footer;
}

function showProduct(id) {
  getService(productService, drawProduct, id );
}

function newProduct() {
  let product = { "id":0, "nombre": "", "precio": "", "taxes": [] };
  drawProduct(product);
}

function deleteProduct(id){
  deleteService( productService, showProductList, id );
}

function saveProduct(id){
  let nombre = document.getElementById("nombreform").value;
  let precio = document.getElementById("precioform").value;

  let listTaxes = document.getElementById("cargosAdicionalesProducto");
  let taxes = [];

  for (  let taxRow of listTaxes.children ){
    taxes.push(taxRow.id);
  }

  let product = { "id":id, "nombre": nombre, "precio": precio, "taxes":taxes };
  setService(productService, showProductList, id, product );
}

function addTax() {

   let taxSeected = document.getElementById("addTax").value;
   let listTaxes = document.getElementById("cargosAdicionalesProducto");
   let taxesList =  getTaxesList();

   let newRow = listTaxes.insertRow();
   let newCel1 = newRow.insertCell();
   let newCel2 = newRow.insertCell();
   let newCel3 = newRow.insertCell();
   let newCel4 = newRow.insertCell();

   for( let tax of taxesList ){

      if( taxSeected.toString() != '' && taxSeected.toString()== tax.id.toString() ){
        newCel1.innerHTML = tax.nombre;
        newCel2.innerHTML = tax.precio;
        newCel3.innerHTML = tax.porc; 
        newCel4.innerHTML = "\u00D7";
        newCel4.setAttribute('onclick', 'removeTax(this)');
        newCel4.setAttribute('class', 'text-black-50');
        newRow.setAttribute('id',tax.id );
      }

    }
}

function removeTax(celda) {
  let indice = celda.parentElement.rowIndex-1;

  let listTaxes = document.getElementById("cargosAdicionalesProducto");
  listTaxes.deleteRow(indice);

}
