const serviceService = "service";

function getServiceList(){
  let list;
  getServiceSync(serviceService+"/list", (response) => list= response  );
  return list;
}

function drawServiceList(serviceList){

 let gridContainer = document.getElementById("gridContainer");

 let grid =" <h2>Servicios</h2>"+
            "<button type='button' class='btn btn-primary' data-bs-toggle='modal' data-bs-target='#myModal' onclick='newService()'>Nuevo</button>"+
            "<table class='table table-hover'><thead><tr><th>Nombre</th><th>Precio </th></tr></thead> <tbody>";

  for( let product of serviceList){
    grid += "<tr  data-bs-toggle='modal' data-bs-target='#myModal' onclick= 'showService( "+product.id+" )' id='"+product.id+"'  >"+
            "<td>"+product.nombre+"</td><td>"+product.precio+"</td></tr>";
  }
  grid += "</tbody></table>";
  gridContainer.innerHTML=grid;
}

function showServiceList(dummy){
  getService(serviceService+"/list", drawServiceList );
}

function drawService(product){
  let taxesList =  getTaxesList();

  let header ="<div class='modal-header'>"+
              "<h4 class='modal-title'>"+( product.id == 0 ? "Servicio Nuevo" : product.nombre)+"</h4>"+
              "<button type='button' class='btn-close' data-bs-dismiss='modal'></button></div>";
      
  let footer ="</div><div class='modal-footer'>"+
              ( product.id == 0 ? "" : "<button type='button' class='btn btn-primary' data-bs-dismiss='modal' onclick= 'deleteService2("+product.id+")' >Borrar</button>" )+
              "<button type='button' class='btn btn-primary' data-bs-dismiss='modal' onclick= 'saveService("+product.id+")' >Guardar</button></div>";

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

function showService(id) {
  getService(serviceService, drawService, id );
}

function newService() {
  let product = { "id":0, "nombre": "", "precio": "", "taxes": [] };
  drawService(product);
}

function deleteService2(id){
  deleteService( serviceService, showServiceList, id );
}

function saveService(id){
  let nombre = document.getElementById("nombreform").value;
  let precio = document.getElementById("precioform").value;

  let listTaxes = document.getElementById("cargosAdicionalesProducto");
  let taxes = [];

  for (  let taxRow of listTaxes.children ){
    taxes.push(taxRow.id);
  }

  let product = { "id":id, "nombre": nombre, "precio": precio, "taxes":taxes };
  setService(serviceService, showServiceList, id, product );
}


