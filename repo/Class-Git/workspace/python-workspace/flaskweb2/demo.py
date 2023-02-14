from flask import Blueprint, Response
from flask import request
import json
import pymysql

bp = Blueprint('demo', __name__, url_prefix='/demo') # create router module ( spring의 controller )

@bp.get("/get-greetings")
def get_greetings():
    name = request.args.get("name", None)
    if name == None:
        return "Hello, Java !!!!!"
    else:
        return "Hello, " + name

def select_winning_numbers():
    import random
    numbers = []
    while True:
        current_number = int( random.random() * 45 + 1 )
        isDup = False
        for number in numbers:
            if current_number == number:
                isDup = True
                break        
        if not isDup:
            numbers.append(current_number)
        if len(numbers) > 6:
            break
    numbers.sort()
    return numbers

@bp.get("/get-winning-numbers")
def get_winning_numbers():
    import json
    numbers = select_winning_numbers()

    json_numbers = json.dumps({"선택된 번호" : numbers}, ensure_ascii=False).encode("utf-8")
    return json_numbers
    # return Response(json_numbers, mimetype="application/json")

@bp.post("/upload-file")
def upload_file():

    file1 = request.files.get("file1", None)
    if file1:
        print(file1.filename)
        file1.save(file1.filename)
    else:
        print("file not uploaded 1")

    file2 = request.files.get("file2", None)    
    if file2:
        print(file2.filename)
        file2.save(file2.filename)
    else:
        print("file not uploaded 2")

    return Response("success", 200)