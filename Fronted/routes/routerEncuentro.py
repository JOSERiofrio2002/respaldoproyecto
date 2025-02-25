from flask import Flask, json, flash, Blueprint, url_for, jsonify, make_response, request, render_template, redirect, abort
import requests
routerEncuentro = Blueprint('routerEncuentro', __name__)

# CRUD DE ENCUENTRO
# listar encuentros
@routerEncuentro.route('/admin/encuentro/list')
def listEncuentro():
    r = requests.get("http://localhost:8078/myapp/encuentro/list")
    print(type(r.json()))
    print(r.json())
    data = r.json()
    return render_template('fragmento/Encuentro/listaEncuentro.html', list=data["data"])

# registrar encuentro
@routerEncuentro.route('/admin/encuentro/register')
def view_register_encuentro():
    return render_template('fragmento/Encuentro/registroEncuentro.html')

# guardar encuentro
@routerEncuentro.route('/admin/encuentro/save', methods=["POST"])
def save_encuentro():
    headers = {'Content-type': 'application/json'}
    form = request.form
    dataF = {
        "idInscrito1": form["idInscrito1"],
        "idInscrito2": form["idInscrito2"],
        "ubicacion": form["ubicacion"],
        "identificacion": form["identificacion"],
        "estado": form["estado"],
        "horaInicio": form["horaInicio"]

    }
    r = requests.post("http://localhost:8078/myapp/encuentro/save", data=json.dumps(dataF), headers=headers)
    dat = r.json()
    if r.status_code == 200:
        flash("Se ha guardado correctamente", category='info')
    else:
        flash(str(dat["data"]), category='error')
    return redirect("/admin/encuentro/list")

# editar encuentro
@routerEncuentro.route('/admin/encuentro/edit/<int:id>')
def edit_encuentro(id):
    r = requests.get(f"http://localhost:8078/myapp/encuentro/get/{id}")
    data = r.json()
    
    if r.status_code == 200:
        return render_template('fragmento/Encuentro/editarEncuentro.html', encuentro=data["data"])
    else:
        flash("Error al obtener el encuentro", category='error')
        return redirect(url_for('router.list_encuentro'))

# actualizar encuentro
@routerEncuentro.route('/admin/encuentro/update', methods=["POST"])
def update_encuentro():
    headers = {'Content-type': 'application/json'}
    form = request.form
    dataF = {
        
        "idInscrito1": form["idInscrito1"],
        "idInscrito2": form["idInscrito2"],
        "ubicacion": form["ubicacion"],
        "identificacion": form["identificacion"],
        "estado": form["estado"],
        "horaInicio": form["horaInicio"]
    }
    r = requests.post("http://localhost:8078/myapp/encuentro/update", data=json.dumps(dataF), headers=headers)
    print(r)
    dat = r.json()
    if r.status_code == 200:
        flash("Se ha actualizado correctamente", category='info')
    else:
        flash(str(dat["data"]), category='error')
    return redirect("/admin/encuentro/list")

# eliminar encuentro
@routerEncuentro.route('/admin/encuentro/delete/<int:id>', methods=["POST"])
def delete_encuentro(id):
    # Realiza una solicitud DELETE a la API
    r = requests.delete(f"http://localhost:8078/myapp/encuentro/delete/{id}")

     # Verifica la respuesta de la API
    if r.status_code == 200:
        flash("Encuentro eliminado exitosamente.", category='info')
    else:
        flash("No se pudo eliminar el encuentro.", category='error')
    
    return redirect(url_for('routerEncuentro.list'))

@routerEncuentro.route('/admin/encuentro/buscar')
def buscar_encuentros():
    idInscrito1 = request.args.get('idInscrito1')
    idInscrito2 = request.args.get('idInscrito2')
    ubicacion = request.args.get('ubicacion')
    identificacion = request.args.get('identificacion')
    horaInicio = request.args.get('horaInicio')

    params = {}
    
    if idInscrito1:
        params['idInscrito1'] = idInscrito1
    if idInscrito2:
        params['idInscrito2'] = idInscrito2
    if ubicacion:
        params['ubicacion'] = ubicacion
    if identificacion:
        params['identificacion'] = identificacion
    if horaInicio:
        params['horaInicio'] = horaInicio   

    try:
        response = requests.get('http://localhost:8078/myapp/encuentro/buscar', params=params)
        return jsonify(response.json())
    except Exception as e:
        return jsonify({'msg': 'Error', 'data': str(e)}), 500

# ordenar encuentros
@routerEncuentro.route('/admin/encuentro/ordenar')
def ordenar_encuentros():
    order_by = request.args.get('by')
    direction = request.args.get('direction')

    try:
        response = requests.get(
            'http://localhost:8078/myapp/encuentro/ordenar',
            params={'by': order_by, 'direction': direction}
        )
        return jsonify(response.json())
    except Exception as e:
        return jsonify({'msg': 'Error', 'data': str(e)}), 500