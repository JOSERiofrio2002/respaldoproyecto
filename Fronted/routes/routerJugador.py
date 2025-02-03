
from flask import Flask, json, flash,Blueprint, url_for, jsonify, make_response, request, render_template, redirect, abort
import requests
routerJugador = Blueprint('routerJugador', __name__)


#CRUD DE JUGADOR
    #listar jugadores
@routerJugador.route('/admin/jugador/list')
def listJugador():
    r = requests.get("http://localhost:8078/myapp/jugador/list")
    print(type(r.json()))
    print(r.json())
    data = r.json()
    return render_template('fragmento/jugador/listaJugador.html', list = data["data"])


    #registrar jugador
@routerJugador.route('/admin/jugador/register')
def view_register_jugador():
    r = requests.get("http://localhost:8078/myapp/jugador/listType")
    r2 = requests.get("http://localhost:8078/myapp/jugador/listTypeGenero")
    data = r.json()
    data2 = r2.json()
    print(r.json())
    return render_template('fragmento/jugador/registroJugador.html', lista = data["data"], lista2 = data2["data"])
    
    #guardar jugador
@routerJugador.route('/admin/jugador/save', methods=["POST"])
def save_jugador():
    headers = {'Content-type': 'application/json'}
    form = request.form
    dataF = {
        "apellido": form["ape"],
        "nombre": form["nom"],
        "tipo": form["tipo"],
        "identificacion": int(form["ident"]) if form["ident"].isdigit() else None,
        "celular": form["fono"],
        "genero": form["genero"],
        "numCamiseta": int(form["nCam"]) if form["nCam"].isdigit() else 0,  
        "fechaNacimiento": form["fecha"]
    }
    r = requests.post("http://localhost:8078/myapp/jugador/save", data=json.dumps(dataF), headers=headers)
    dat = r.json()
    if r.status_code == 200:
        flash("Se ha guardado correctamente", category='info')
    else:
        flash(str(dat["data"]), category='error')
    return redirect("/admin/jugador/list")    

    #editar jugador
@routerJugador.route('/admin/jugador/edit/<int:id>')
def view_edit_jugador(id): 
    r1 = requests.get(f"http://localhost:8078/myapp/jugador/get/{id}") 

    data1 = r1.json()

    
    print(data1)
        
    if r1.status_code == 200:
        return render_template('fragmento/jugador/editarJugador.html', jugador=data1["data"])

#actualizar jugador
@routerJugador.route('/admin/jugador/update', methods=["POST"])
def update_jugador():
    headers = {'Content-type': 'application/json'}
    form = request.form
    dataF = {
        "id": form["id"],
        "apellido": form["ape"],
        "nombre": form["nom"],
        "celular": form["fono"],
        "numCamiseta": int(form["nCam"]) if form["nCam"].isdigit() else 0,  
    }
    r = requests.post("http://localhost:8078/myapp/jugador/update", data=json.dumps(dataF), headers=headers)
    dat = r.json()
    if r.status_code == 200:
        flash("Se ha guardado correctamente", category='info')
    else:
        flash(str(dat["data"]), category='error')
    return redirect("/admin/jugador/list")

    #eliminar jugador
@routerJugador.route('/admin/jugador/delete/<int:id>')
def delete_jugador(id):
    r = requests.get(f"http://localhost:8078/myapp/jugador/delete/{id}")
    dat = r.json()
    if r.status_code == 200:
        flash("Se ha eliminado correctamente", category='info')
    else:
        flash(str(dat["data"]), category='error')
    return redirect("/admin/jugador/list")