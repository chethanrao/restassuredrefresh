from flask import Flask, request, jsonify

app = Flask(__name__)

# Some dummy data to work with
data = {}


@app.route('/data', methods=['GET'])
def get_data():
    return jsonify(data)


@app.route('/data', methods=['POST'])
def add_data():
    global data
    new_data = request.json
    data = new_data

    return jsonify(new_data)


@app.route('/data', methods=['DELETE'])
def delete_data():
    data = {}
    return jsonify({'result': False})


if __name__ == '__main__':
    app.run()
