
const customerService = "client/person";

function getCustomerList(){
  let list;
  getServiceSync(customerService+"/list", (response) => list= response  );
  return list;
}

function showCustomerList(dummy){
  getService( customerService+"/list", drawCustmerList );
}

function drawCustmerList(customersList){

 let gridContainer = document.getElementById("gridContainer");

 let grid =" <h2>Clientes</h2>"+
            "<button type='button' class='btn btn-primary' data-bs-toggle='modal' data-bs-target='#myModal' onclick='newCustomer()'>Nuevo</button>"+
            "<table class='table table-hover'><thead><tr><th>Nombre</th><th>Apellido</th><th>DNI</th></tr></thead>";

  for( let customer of customersList){
    grid += "<tr  data-bs-toggle='modal' data-bs-target='#myModal' onclick= 'showCustomer(this)' id='"+customer.id+"'  >"+
            "<td>"+customer.name+"</td><td>"+customer.surname+"</td><td>"+customer.DNI+"</td></tr>";
  }
  grid += "</tbody></table>";
  gridContainer.innerHTML=grid;
}

function showCustomer(obj) {
  getService(customerService, drawCustomer, obj.id );
}

function drawCustomer(customer){

  let header ="<div class='modal-header'>"+
              "<h4 class='modal-title'>"+( customer.id == 0 ? "Cliente Nuevo" : customer.name+" "+customer.surname )+"</h4>"+
              "<button type='button' class='btn-close' data-bs-dismiss='modal'></button></div>";
      
  let footer ="</div><div class='modal-footer'>"+
              ( customer.id == 0 ? "" : "<button type='button' class='btn btn-primary' data-bs-dismiss='modal' onclick= 'deleteCustomer("+customer.id+")' >Borrar</button>" )+
              "<button type='button' class='btn btn-primary' data-bs-dismiss='modal' onclick= 'saveCustomer("+customer.id+")' >Guardar</button></div>";

  let body = "<div class='modal-body row row-cols-2'>";

  body += "<div class='mb-3 mt-3 col'>"+
          "<label for='nombre' class='form-label'>Nombre</label>"+
          "<input type='nombre' class='form-control' id='nombreform' placeholder='Ingrese nombre' value='"+(customer.name==null ? "" : customer.name) +
          "' name='nombre'></div>";

  body += "<div class='mb-3 mt-3 col'>"+
          "<label for='nombre' class='form-label'>Apellido</label>"+
          "<input type='nombre' class='form-control' id='Apellidoform' placeholder='Ingrese Apellido' value='"+(customer.surname==null ? "" :customer.surname)+
          "' name='nombre'></div>";

  body += "<div class='mb-3 mt-3 col'>"+
          "<label for='nombre' class='form-label'>DNI</label>"+
          "<input type='nombre' class='form-control' id='DNIform' placeholder='Ingrese DNI' value='"+(customer.DNI==null ? "" :customer.DNI)+
          "' name='nombre'></div>";

  document.getElementById("myModelContent").innerHTML=header+body+footer;
}

function newCustomer() {
  let customer = { "id":0, "name": "", "surname": "", "DNI":"" };
  drawCustomer(customer);
}

function deleteCustomer(id){
  deleteService( customerService, showCustomerList, id );
}

function saveCustomer(id){
  let nombre = document.getElementById("nombreform").value;
  let apellido = document.getElementById("Apellidoform").value;
  let dni = document.getElementById("DNIform").value;

  let customer = { "id":id, "name": nombre, "surname": apellido, "DNI":dni };
  setService( customerService, showCustomerList, id, customer );
}


