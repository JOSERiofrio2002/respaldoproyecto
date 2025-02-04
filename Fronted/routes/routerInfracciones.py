from flask import Flask, json, flash, Blueprint, url_for, jsonify, make_response, request, render_template, redirect, abort
import requests
routerInfracciones = Blueprint('routerInfracciones', __name__)

# CRUD DE INFRACCIONES
    # listar infracciones
@routerInfracciones.route('/admin/infraccion/list')
def listInfraccion():
    r = requests.get("http://localhost:8078/myapp/infraccion/list")
    print(type(r.json()))
    print(r.json())
    data = r.json()
    return render_template('fragmento/Infracciones/listaInfracciones.html', list=data["data"])

    # registrar infracción
@routerInfracciones.route('/admin/infraccion/register')
def view_register_infracciones():
    return render_template('fragmento/Infracciones/registroInfracciones.html')

    # guardar infracción
@routerInfracciones.route('/admin/infraccion/save', methods=["POST"])
def save_infraccion():
    headers = {'Content-type': 'application/json'}
    form = request.form
    dataF = {
        "numTarjeta": form["numTarjeta"],
        "identificacionJugador": form["identificacionJugador"],
        "colorTarjeta": form["colorTarjeta"]
        
    }
    r = requests.post("http://localhost:8078/myapp/infraccion/save", data=json.dumps(dataF), headers=headers)
    dat = r.json()
    if r.status_code == 200:
        flash("Infracción guardada correctamente", category='info')
    else:
        flash(str(dat["data"]), category='error')
    return redirect("/admin/infraccion/list")

    # editar infracción
@routerInfracciones.route('/admin/infraccion/edit/<int:id>')
def edit_infraccion(id):
    r = requests.get(f"http://localhost:8078/myapp/infraccion/get/{id}")
    data = r.json()
    
    if r.status_code == 200:
        return render_template('fragmento/Infracciones/editarInfracciones.html', infraccion=data["data"])
    else:
        flash("Error al obtener la infraccion", category='error')
        return redirect(url_for('router.list_infraccion'))
    
    # actualizar infracción
@routerInfracciones.route('/admin/infraccion/update', methods=["POST"])
def update_infraccion():
    headers = {'Content-type': 'application/json'}
    form = request.form
    dataF = {
       "numTarjeta": form["numTarjeta"],
        "identificacionJugador": form["identificacionJugador"],
        "colorTarjeta": form["colorTarjeta"]
    }
    r = requests.post("http://localhost:8078/myapp/infraccion/update", data=json.dumps(dataF), headers=headers)
    print(r)
    dat = r.json()
    if r.status_code == 200:
        flash("Infracción actualizada correctamente", category='info')
    else:
        flash(str(dat["data"]), category='error')
    return redirect("/admin/infraccion/list")
    
    # eliminar infracción
@routerInfracciones.route('/admin/infraccion/delete/<int:id>', methods=["POST"])
def delete_infraccion(id):
    # Realiza una solicitud DELETE a la API
    r = requests.delete(f"http://localhost:8078/myapp/infraccion/delete/{id}")
    
    # Verifica la respuesta de la API
    if r.status_code == 200:
        flash("Infracción eliminada exitosamente.", category='info')
    else:
        flash("No se pudo eliminar la infracción.", category='error')
    
    return redirect(url_for('routerInfracciones.listInfraccion'))

@routerInfracciones.route('/admin/infraccion/buscar')
def buscar_infracciones():
    numTarjeta = request.args.get('NumeroTarjeta')
    identifiacionJugador = request.args.get('IdentifiacionJugador')
    colorTarjeta = request.args.get('ColorTarjeta')
 
    params = {}
    
    if numTarjeta:
        params['NumeroTarjeta'] = numTarjeta
    if identifiacionJugador:
        params['IdentifiacionJugador'] = identifiacionJugador
    if colorTarjeta:
        params['ColorTarjeta'] = colorTarjeta

    try:
        response = requests.get('http://localhost:8078/myapp/infraccion/buscar', params=params)
        return jsonify(response.json())
    except Exception as e:
        return jsonify({'msg': 'Error', 'data': str(e)}), 500

# ordenar encuentros
@routerInfracciones.route('/admin/infraccion/ordenar')
def ordenar_encuentros():
    order_by = request.args.get('by')
    direction = request.args.get('direction')

    try:
        response = requests.get(
            'http://localhost:8078/myapp/infraccion/ordenar',
            params={'by': order_by, 'direction': direction}
        )
        return jsonify(response.json())
    except Exception as e:
        return jsonify({'msg': 'Error', 'data': str(e)}), 500