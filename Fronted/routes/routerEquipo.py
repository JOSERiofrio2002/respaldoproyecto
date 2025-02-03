
from flask import Flask, json, flash,Blueprint, url_for, jsonify, make_response, request, render_template, redirect, abort
import requests
routerEquipo = Blueprint('routerEquipo', __name__)

#CRUD DE EQUIPO   
    #listar equipos
@routerEquipo.route('/admin/equipo/list')
def listEquipo():
    r = requests.get("http://localhost:8078/myapp/equipo/list")
    print(type(r.json()))
    print(r.json())
    data = r.json()
    return render_template('fragmento/equipo/listaEquipo.html', list = data["data"])

    #registrar equipo
@routerEquipo.route('/admin/equipo/register')
def view_register_equipo():
    r = requests.get("http://localhost:8078/myapp/dirigente/listType")
    r2 = requests.get("http://localhost:8078/myapp/dirigente/listTypeGenero")
    data = r.json()
    data2 = r2.json()
    print(r.json())
    return render_template('fragmento/equipo/registroEquipo.html', lista = data["data"], lista2 = data2["data"])

    #guardar equipo
@routerEquipo.route('/admin/equipo/save', methods=["POST"])
def save_equipo():
    headers = {'Content-type': 'application/json'}
    form = request.form
    dataF = {
        "nombre": form["nomE"],
    }
    r = requests.post("http://localhost:8078/myapp/equipo/save", data=json.dumps(dataF), headers=headers)
    dat = r.json()

    if r.status_code == 200:
        flash("Se ha guardado correctamente", category='info')
    else:
        flash(str(dat["data"]), category='error')
    return redirect("/admin/equipo/list")

    #editar equipo
@routerEquipo.route('/admin/equipo/edit/<int:id>')
def view_edit_equipo(id): 
    r = requests.get(f"http://localhost:8078/myapp/equipo/get/{id}") 
    data = r.json()

    
    if r.status_code == 200:
        return render_template('fragmento/equipo/editarEquipo.html', equipo=data["data"])
    else:
        flash(data["data"], category='error')
        return redirect("/admin/equipo/list")
    
    #actualizar dirigente
@routerEquipo.route('/admin/equipo/update', methods=["POST"])
def update_equipo():
    headers = {'Content-type': 'application/json'}
    form = request.form
    dataF = {
    "id": form["id"],
    "nombre": form["nomE"],
    }
    r = requests.post("http://localhost:8078/myapp/equipo/update", data=json.dumps(dataF), headers=headers)
    dat = r.json()
    if r.status_code == 200:
        flash("Se ha guardado correctamente", category='info')
    else:
        flash(str(dat["data"]), category='error')
    return redirect("/admin/equipo/list")
    
    
    #eliminar equipo
@routerEquipo.route('/admin/equipo/delete/<int:id>')
def delete_equipo(id):
    r = requests.get(f"http://localhost:8078/myapp/equipo/delete/{id}")
    dat = r.json()
    if r.status_code == 200:
        flash("Se ha eliminado correctamente", category='info')
    else:
        flash(str(dat["data"]), category='error')
    return redirect("/admin/equipo/list")
