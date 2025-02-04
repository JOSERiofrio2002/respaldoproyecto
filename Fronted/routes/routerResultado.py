from flask import Flask, json, flash, Blueprint, url_for, jsonify, make_response, request, render_template, redirect, abort
import requests
routerResultado = Blueprint('routerResultado', __name__)

# CRUD DE RESULTADO
    # listar resultados
@routerResultado.route('/admin/resultado/list')
def listResultado():
    r = requests.get("http://localhost:8078/myapp/resultado/list")
    print(type(r.json()))
    print(r.json())
    data = r.json()
    return render_template('fragmento/Resultado/listaResultado.html', list=data["data"])

    # registrar resultado
@routerResultado.route('/admin/resultado/register')
def view_register_resultado():
    return render_template('fragmento/Resultado/registroResultado.html')

    # guardar resultado
@routerResultado.route('/admin/resultado/save', methods=["POST"])
def save_resultado():
    headers = {'Content-type': 'application/json'}
    form = request.form
    dataF = {
        "equipoGanador": form["equipoGanador"],
        "equipoPerdedor": form["equipoPerdedor"],
        "golesEquipo1": form["golesEquipo1"],
        "golesEquipo2": form["golesEquipo2"],
        "empate": form["empate"],
        "puntosEncuentro": form["puntosEncuentro"]
    }
    r = requests.post("http://localhost:8078/myapp/resultado/save", data=json.dumps(dataF), headers=headers)
    dat = r.json()
    if r.status_code == 200:
        flash("Resultado guardado correctamente", category='info')
    else:
        flash(str(dat["data"]), category='error')
    return redirect("/admin/resultado/list")

@routerResultado.route('/admin/resultado/edit/<int:id>')
def edit_resultado(id):
    r = requests.get(f"http://localhost:8078/myapp/resultado/get/{id}")
    data = r.json()
    
    if r.status_code == 200:
        return render_template('fragmento/Resultado/editarResultado.html', resultado=data["data"])
    else:
        flash("Error al obtener el resultado", category='error')
        return redirect(url_for('router.list_resultado'))

    
    # actualizar resultado
@routerResultado.route('/admin/resultado/update', methods=["POST"])
def update_resultado():
    headers = {'Content-type': 'application/json'}
    form = request.form
    dataF = {
        "equipoGanador": form["equipoGanador"],
        "equipoPerdedor": form["equipoPerdedor"],
        "golesEquipo1": form["golesEquipo1"],
        "golesEquipo2": form["golesEquipo2"],
        "empate": form["empate"],
        "puntosEncuentro": form["puntosEncuentro"]
    }
    r = requests.post("http://localhost:8078/myapp/resultado/update", data=json.dumps(dataF), headers=headers)
    print(r)
    dat = r.json()
    if r.status_code == 200:
        flash("Resultado actualizado correctamente", category='info')
    else:
        flash(str(dat["data"]), category='error')
    return redirect("/admin/resultado/list")
    
    # eliminar resultado
@routerResultado.route('/admin/resultado/delete/<int:id>', methods=["POST"])
def delete_resultado(id):
    # Realiza una solicitud DELETE a la API
    r = requests.delete(f"http://localhost:8078/myapp/resultado/delete/{id}")
    
    # Verifica la respuesta de la API
    if r.status_code == 200:
        flash("Resultado eliminado exitosamente.", category='info')
    else:
        flash("No se pudo eliminar el resultado.", category='error')
    
    return redirect(url_for('routerResultado.listResultado'))

@routerResultado.route('/admin/resultado/buscar')
def buscar_resultado():
    equipoGanador = request.args.get('EquipoGanador')
    equipoPededor = request.args.get('EquipoPerdedor')
    golesEquipo1 = request.args.get('GolesEquipo1')
    golesEquipo2 = request.args.get('GolesEquipo2')
    puntosEncuentro = request.args.get('PuntosEncuentro')
    
    params = {}

    if equipoGanador:
        params['equipoGanador'] = equipoGanador
    if equipoPerdedor:
        params['equipoPerdedor'] = equipoPerdedor
    if golesEquipo1:
        params['golesEquipo1'] = golesEquipo1
    if golesEquipo2:
        params['golesEquipo2'] = golesEquipo2
    if empate:
        params['empate'] = empate
    if puntosEncuentro:
        params['puntosEncuentro'] = puntosEncuentro
   

    try:
        response = requests.get('http://localhost:8078/myapp/resultado/buscar', params=params)
        return jsonify(response.json())
    except Exception as e:
        return jsonify({'msg': 'Error', 'data': str(e)}), 500

# ordenar encuentros
@routerResultado.route('/admin/infraccion/ordenar')
def ordenar_encuentros():
    order_by = request.args.get('by')
    direction = request.args.get('direction')

    try:
        response = requests.get(
            'http://localhost:8078/myapp/resultado/ordenar',
            params={'by': order_by, 'direction': direction}
        )
        return jsonify(response.json())
    except Exception as e:
        return jsonify({'msg': 'Error', 'data': str(e)}), 500