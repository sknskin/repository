from flask import Flask
from flask import request, Response
import json


app = Flask(__name__) # web application 

@app.get("/")
def home():
    return "home"

@app.get("/get-greetings")
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

@app.get("/get-winning-numbers")
def get_winning_numbers():
    import json
    numbers = select_winning_numbers()

    json_numbers = json.dumps({"선택된 번호" : numbers}, ensure_ascii=False).encode("utf-8")
    return json_numbers
    # return Response(json_numbers, mimetype="application/json")

import pymysql

@app.route('/find-member-to-login', methods=["GET", "POST"])
def find_member_to_login():

    if request.method == "POST" : 
        id = request.form.get("id", "")
        passwd = request.form.get('passwd', '')
    else:
        id = request.args.get("id", "")
        passwd = request.args.get('passwd', '')

    conn = pymysql.connect(host="localhost", user="testuser", password="mysql", database="demoweb", charset="utf8")
    cursor = conn.cursor()
    sql = "SELECT memberid, email, usertype, regdate, active FROM member WHERE memberid = %s AND passwd = %s"
    cursor.execute(sql, (id, passwd))

    member = cursor.fetchone()

    member = list(member)
    member[3] = str(member[3])

    json_response = json.dumps(member, ensure_ascii=False).encode("utf-8")

    return json_response


