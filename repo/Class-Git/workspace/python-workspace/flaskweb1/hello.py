from flask import Flask
from flask import render_template

app = Flask(__name__)

@app.route("/")
def hello_index():

    return "<h1>Hello, Flask Web Application !!!</h1>"

@app.route("/hello/<name>")
def hello_name(name):

    return "<h1>Hello, " + name + "</h1>"

@app.route("/auth/register")
def register():

    return render_template("auth/register.html")

@app.route("/hello2")
@app.route("/hello2/<name>")
def hello_name2(name=None):

    return render_template("hello.html", name=name)