
from flask import Flask, json, flash,Blueprint, url_for, jsonify, make_response, request, render_template, redirect, abort
import requests
router = Blueprint('router', __name__)

@router.route('/')
def home():
    return render_template('template.html')


@router.route('/admin')
def admin():
    return render_template('fragmento/inicial/admin.html')


@router.route('/admin/person/list')
def list():
    r = requests.get("http://localhost:8078/myapp/person/list")
    print(type(r.json()))
    print(r.json())
    data = r.json()
    return render_template('fragmento/persona/lista.html', list = data["data"])



@router.route('/admin/person/edit/<int:id>')  
def view_edit_person(id):  
    r = requests.get("http://localhost:8078/myapp/person/list")
    data = r.json()
    
   
    r1 = requests.get(f"http://localhost:8078/myapp/person/get/{id}")  
    data1 = r1.json()
    
    if r1.status_code == 200:
        return render_template('fragmento/persona/editar.html', list=data["data"], person=data1["data"]) 
    else:
        flash(data1["data"], category='error')
        return redirect("/admin/person/list")


@router.route('/admin/person/register')
def view_register_person():
    r = requests.get("http://localhost:8078/myapp/person/list")
    data = r.json()
    print(r.json())
    return render_template('fragmento/persona/registro.html', list = data["data"])

@router.route('/admin/person/save', methods=["POST"])
def save_person():
    headers = {'Content-type': 'application/json'}
    form = request.form
    dataF = {
        "apellido": form["ape"],
        "nombre": form["nom"],
        "dni": form["dni"],
        "celular": form["fono"],
    }
    
    r = requests.post("http://localhost:8078/myapp/person/save", data=json.dumps(dataF), headers=headers)
    dat = r.json()
    
    # Manejo de la respuesta
    if r.status_code == 200:
        flash("Se ha guardado correctamente", category='info')
    else:
        flash(str(dat["data"]), category='error')
    
    # Redirigir después de guardar
    return redirect("/admin/person/list")


@router.route('/admin/person/delete/<int:id>', methods=["POST"])
def delete_person(id):
    # Realiza una solicitud DELETE a la myapp
    r = requests.delete(f"http://localhost:8078/myapp/person/delete/{id}")
    
    # Verifica la respuesta de la myapp
    if r.status_code == 200:
        flash("Cliente eliminado exitosamente.", category='info')
    else:
        flash("No se pudo eliminar el cliente.", category='error')
    
    return redirect(url_for('router.list'))

@router.route('/admin/person/update', methods=["POST"])
def update_person():
    headers = {'Content-type': 'application/json'}
    form = request.form
    dataF = {
        "idPersona": form["id"],
        "apellido": form["ape"],
        "nombre": form["nom"],
        "dni": form["dni"],
        "celular": form["fono"],
    }
    # Realizar la solicitud POST a la myapp
    r = requests.post("http://localhost:8078/myapp/person/update", data=json.dumps(dataF), headers=headers)
    print(r)
    dat = r.json()
    # Manejo de la respuesta
    if r.status_code == 200:
        flash("Se ha guardado correctamente", category='info')
    else:
        flash(str(dat["data"]), category='error')
    return redirect("/admin/person/list")

# CRUD DE ARBITRO
@router.route('/admin/arbitro/list')
def list_arbitros():
    r = requests.get("http://localhost:8078/myapp/arbitro/list")
    data = r.json()
    
    if r.status_code == 200:
        return render_template('fragmento/Arbitro/listaArbitro.html', list=data["data"])
    else:
        flash("Error al obtener la lista de árbitros", category='error')
        return redirect(url_for('router.index'))

@router.route('/admin/arbitro/register')
def view_register_arbitro():
    return render_template('fragmento/Arbitro/registroArbitro.html')

@router.route('/admin/arbitro/edit/<int:id>')
def edit_arbitro(id):
    r = requests.get(f"http://localhost:8078/myapp/arbitro/get/{id}")
    data = r.json()
    
    if r.status_code == 200:
        return render_template('fragmento/Arbitro/editarArbitro.html', arbitro=data["data"])
    else:
        flash("Error al obtener el árbitro", category='error')
        return redirect(url_for('router.list_arbitros'))

@router.route('/admin/arbitro/save', methods=["POST"])
def save_arbitro():
    headers = {'Content-type': 'application/json'}
    form = request.form
    dataF = {
        "nombre": form["nombre"],
        "apellido": form["apellido"],
        "identificacion": form["identificacion"],
        "celular": form["celular"],
        "genero": form["genero"],
        "fechaNacimiento": form["fechaNacimiento"]
    }
    
    r = requests.post("http://localhost:8078/myapp/arbitro/save", data=json.dumps(dataF), headers=headers)
    dat = r.json()
    
    if r.status_code == 200:
        flash("Se ha guardado correctamente", category='info')
    else:
        flash(str(dat["data"]), category='error')
    return redirect("/admin/arbitro/list")

@router.route('/admin/arbitro/update', methods=["POST"])
def update_arbitro():
    headers = {'Content-type': 'application/json'}
    form = request.form
    dataF = {
         "nombre": form["nombre"],
        "apellido": form["apellido"],
        "identificacion": form["identificacion"],
        "celular": form["celular"],
        "genero": form["genero"],
        "fechaNacimiento": form["fechaNacimiento"]
    }
    
    r = requests.post("http://localhost:8078/myapp/arbitro/update", data=json.dumps(dataF), headers=headers)
    dat = r.json()
    
    if r.status_code == 200:
        flash("Se ha actualizado correctamente", category='info')
    else:
        flash(str(dat["data"]), category='error')
    return redirect("/admin/arbitro/list")

@router.route('/admin/arbitro/delete/<int:id>', methods=["POST"])
def delete_arbitro(id):
    headers = {'Content-type': 'application/json'}
    dataF = {"idArbitro": id}
    
    r = requests.post("http://localhost:8078/myapp/arbitro/delete", data=json.dumps(dataF), headers=headers)
    
    if r.status_code == 200:
        flash("Árbitro eliminado exitosamente", category='info')
    else:
        flash("No se pudo eliminar el árbitro", category='error')
    
    return redirect(url_for('router.list_arbitros'))

@router.route('/admin/arbitro/buscar')
def  buscar_arbitros():
    nombre = request.args.get('nombre')
    apellido = request.args.get('apellido')
    identifiacion = request.args.get('identifiacion')
    asociacion = request.args.get('asociacion')
    genero = request.args.get('genero')

  
    params = {}
    if nombre:
        params['nombre'] = nombre
    if apellido:
        params['apellido'] = apellido
    if identifiacion:
        params['identifiacion'] = identifiacion
    if asociacion:
        params['asociacion'] = asociacion
    if genero:
        params['genero'] = genero    


    try:
        response = requests.get('http://localhost:8078/myapp/arbitro/buscar', params=params)
        return jsonify(response.json())
    except Exception as e:
        return jsonify({'msg': 'Error', 'data': str(e)}), 500

@router.route('/admin/arbitro/ordenar')
def ordenar_arbitros():
    order_by = request.args.get('by')
    direction = request.args.get('direction')

    try:
        response = requests.get(
            'http://localhost:8078/myapp/arbitro/ordenar',
            params={'by': order_by, 'direction': direction}
        )
        return jsonify(response.json())
    except Exception as e:
        return jsonify({'msg': 'Error', 'data': str(e)}), 500










