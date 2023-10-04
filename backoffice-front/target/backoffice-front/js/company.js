
const companyService = "client/company";

function getCompanyList(){
  let list;
  getServiceSync(companyService+"/list", (response) => list= response  );
  return list;
}

function showCompanyList(dummy){
  getService( companyService+"/list", drawCompanyList );
}

function drawCompanyList(companyList){

 let gridContainer = document.getElementById("gridContainer");

 let grid =" <h2>Empresas</h2>"+
            "<button type='button' class='btn btn-primary' data-bs-toggle='modal' data-bs-target='#myModal' onclick='newCompany()'>Nuevo</button>"+
            "<table class='table table-hover'><thead><tr><th>Razón Social</th><th>Fecha Inicio de Actividades </th><th>CUIT</th></tr></thead>";

  for( let company of companyList){
    grid += "<tr  data-bs-toggle='modal' data-bs-target='#myModal' onclick= 'showCompany(this)' id='"+company.id+"'  >"+
            "<td>"+company.razonSocial+"</td><td>"+company.fechaInicio+"</td><td>"+company.cuit+"</td></tr>";
  }
  grid += "</tbody></table>";
  gridContainer.innerHTML=grid;
}

function showCompany(obj) {
  getService(companyService, drawCompany, obj.id )
}

function drawCompany(company){
  let customerList =  getCustomerList();

  let header ="<div class='modal-header'>"+
              "<h4 class='modal-title'>"+( company.id == 0 ? "Empresa Nueva" : company.razonSocial)+"</h4>"+
              "<button type='button' class='btn-close' data-bs-dismiss='modal'></button></div>";
      
  let footer ="</div><div class='modal-footer'>"+
              ( company.id == 0 ? "" : "<button type='button' class='btn btn-primary' data-bs-dismiss='modal' onclick= 'deleteCompany("+company.id+")' >Borrar</button>" )+
              "<button type='button' class='btn btn-primary' data-bs-dismiss='modal' onclick= 'saveCompany("+company.id+")' >Guardar</button></div>";

  let body = "<div class='modal-body row row-cols-2'>";

  body += "<div class='mb-3 mt-3 col'>"+
          "<label for='nombre' class='form-label'>Razón social</label>"+
          "<input type='nombre' class='form-control' id='razonSocialform' placeholder='Ingrese Razón social' value='"+(company.razonSocial==null ? "" : company.razonSocial) +
          "' name='nombre'></div>";

  body += "<div class='mb-3 mt-3 col'>"+
          "<label for='nombre' class='form-label'>Fecha de inicio de actividades</label>"+
          "<input type='date' class='form-control' id='fechaInicioform' placeholder='Ingrese fecha de inicio de actividades' value='"+(company.fechaInicio==null ? "" :company.fechaInicio)+
          "' name='nombre'></div>";

  body += "<div class='mb-3 mt-3 col'>"+
          "<label for='nombre' class='form-label'>CUIT</label>"+
          "<input type='nombre' class='form-control' id='cuitform' placeholder='Ingrese CUIT' value='"+(company.cuit==null ? "" :company.cuit)+
          "' name='nombre'></div>";

  body += "<div class='mb-3 mt-3 col'>"+
          "<label for='nombre' class='form-label'>Persona física</label>"+
          "<select class='form-control' id='personaFisicaform' name='personaFisicaform'><option value=0 ></option>";
          for( let customer of customerList ){
            if( company.personaFisica.toString() == customer.id.toString() ){
              body += "<option value="+customer.id+" selected>"+customer.name+" "+customer.surname+"</option>";
            }else {
              body += "<option value="+customer.id+" >"+customer.name+" "+customer.surname+"</option>";
            }
          }
  body += "</select></div>";


  document.getElementById("myModelContent").innerHTML=header+body+footer;
}

function newCompany() {
  let company = { "id":0, "razonSocial": "", "fechaInicio": "", "cuit":"", "personaFisica":"" };
  drawCompany(company);
}

function deleteCompany(id){
  deleteService( companyService, showCompanyList, id );
}

function saveCompany(id){
  let razonSocial = document.getElementById("razonSocialform").value;
  let fechaInicio = document.getElementById("fechaInicioform").value;
  let cuit = document.getElementById("cuitform").value;
  let personaFisica = document.getElementById("personaFisicaform").value;

  let company = { "id":id, "razonSocial": razonSocial, "fechaInicio": fechaInicio, "cuit":cuit, "personaFisica":personaFisica };
  setService( companyService, showCompanyList, id, company );
}
