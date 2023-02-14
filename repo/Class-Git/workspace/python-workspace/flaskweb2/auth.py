from flask import Blueprint
from flask import request
import json
import pymysql

bp = Blueprint('auth', __name__, url_prefix='/auth') # create router module ( spring의 controller )

@bp.route('/find-member-to-login', methods=["GET", "POST"])
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