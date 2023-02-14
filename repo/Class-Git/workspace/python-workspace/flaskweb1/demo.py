from flask import Flask

from flask import render_template, request

import pickle

import numpy as np
import cv2
from tensorflow import keras

import pytesseract

app = Flask(__name__)

# @app.route("/", methods=['GET'])
@app.get("/")
@app.get("/home")
def home():
    return render_template("home.html")

@app.get("/request-data-test")
def process_get_request():
    email = request.args['email']
    # passwd = request.args['passwd'] # key가 없으면 key error 발생
    passwd = request.args.get('passwd', 'password not specified')

    return "GET: Email : {0} / Password : {1}".format(email, passwd)

@app.post("/request-data-test")
def process_post_request():
    email = request.form['email']
    passwd = request.form['passwd']

    return "POST: Email : {0} / Password : {1}".format(email, passwd)


@app.post("/game1")
def game1():
    user = int(request.form.get('selection', 0))

    if user == 0:
        return "Invalid User Selection"

    import random
    com = random.randint(1, 3)

    result = None
    if (user == 1 and com == 3) or \
       (user == 2 and com == 1) or \
       (user == 3 and com == 2):
       result = 'User Win'
    elif user == com:
        result = "Draw"
    else:
        result = "Computer Win"
        
    return "RESULT : {0}".format(result)

@app.post("/lotto")
def select_golden_numbers():
    import random
    cnt = request.form.get("cnt", 1)
    try:
        cnt = int(cnt)
    except:
        cnt = 1

    numbers_list = []
    for rnd in range(cnt):
        numbers = []
        while True:
            number = random.randint(1, 45)
            if number not in numbers:
                numbers.append(number)
            
            if len(numbers) == 6:
                break
                        
        numbers = sorted(numbers)
        numbers_list.append(numbers)

    return render_template('lotto-result.html', numbers_list=numbers_list)

@app.post("/upload-picture")
def upload_picture():
    picture = request.files['picture']
    picture.save(picture.filename)

    return "<h1>Succeeded Upload Picture</h1>"


import pymysql

connection_info = { "host": "127.0.0.1", "user" : "pythonuser", "password" : "pythonuser", "database" : "pythondemo", "charset": "utf8" }

@app.post("/save-data")
def save_data():
    email = request.form.get("email", "")
    phone = request.form.get("phone", "")

    # conn = pymysql.connect(host=connection_info['host'], 
    #                        database=connection_info['database'],
    #                        user=connection_info['user'], password=connection_info['password'], 
    #                        charset=connection_info['charset'])
    conn = pymysql.connect(**connection_info) # (host="127.0.0.1", user="pythonuser", ....)

    cursor = conn.cursor() # cursor : 명령 실행 객체

    sql = """INSERT INTO person (email, phone) 
             VALUES (%s, %s)"""
    cursor.execute(sql, (email, phone))

    conn.commit() # 이전에 실행한 명령을 적용
    cursor.close()
    conn.close()

    return "succeeded save data"

@app.get("/load-data")
def load_data():
    conn = pymysql.connect(**connection_info)
    cursor = conn.cursor()

    sql = """SELECT idx, email, phone
            FROM person"""
    cursor.execute(sql)
    rows = cursor.fetchall() # select 명령인 경우 사용 : 데이터 꺼내기

    return render_template('person-list.html', persons=rows)


@app.post('/predict-species')
def predict_species():
    
    sl = float(request.form.get("sl"))
    sw = float(request.form.get("sw"))
    pl = float(request.form.get("pl"))
    pw = float(request.form.get("pw"))

    with open("model/iris_estimator.pkl", "rb") as f:
        iris_model = pickle.load(f)

    predicted_value = iris_model.predict([[sl, sw, pl, pw]])
    if predicted_value[0] == 0:
        species = 'setosa'
    elif predicted_value[0] == 1:
        species = 'versicolor'
    else:
        species = 'virginica'

    # species = 'setosa' if species == 0 else 'versicolor' if species == 1 else 'virginica'

    return species

@app.post("/predict-number")
def predict_number():
    import io

    mnist_img = request.files['mnist']  # 파일 읽기
    mnist_data = np.frombuffer(mnist_img.read(), np.uint8)  # 파일 데이터 -> numpy array
    img = cv2.imdecode( mnist_data, cv2.IMREAD_GRAYSCALE)   # numpy array -> image formatted numpy array

    # process img fit to train model
    reshaped_img = img.reshape(-1, 28, 28, 1)
    reshaped_scaled_img = reshaped_img / reshaped_img.max()
    
    # print("------------------> {0} / {1}".format(reshaped_scaled_img.shape, reshaped_scaled_img.max()))

    mnist_model = keras.models.load_model(filepath="model/mnist_model.h5")
    predicted_value = mnist_model.predict(reshaped_scaled_img)
    selected_number = np.argmax(predicted_value)

    # print("=========================> {0}".format(selected_number))

    return "NUMBER IN IMAGE IS {0}".format(selected_number)

@app.get("/take-picture")
def show_take_picture_form():

    return render_template("webcam-take-picture.html")


@app.post("/upload-picture2")
def upoload_picture2():

    picture = request.files['picture']
    # picture.save(picture.filename)

    mnist_data = np.frombuffer(picture.read(), np.uint8)  # 파일 데이터 -> numpy array
    imgt = cv2.imdecode( mnist_data, cv2.IMREAD_COLOR)   # numpy array -> image formatted numpy array
    # imgt = cv2.cvtColor(imgt, cv2.COLOR_BGR2RGB)
    cv2.imwrite("color-data.png", imgt)

    img = cv2.imdecode( mnist_data, cv2.IMREAD_GRAYSCALE)   # numpy array -> image formatted numpy array
    _, bin_img = cv2.threshold(img, 127, 256, cv2.THRESH_BINARY_INV)

    # cv2.imwrite("number3.png", bin_img)

    contours, hierarchy = cv2.findContours(bin_img, cv2.RETR_EXTERNAL, cv2.CHAIN_APPROX_SIMPLE)

    if len(contours) == 0:
        return "no number detected 1"
    else:
        (x, y), radius = cv2.minEnclosingCircle(contours[0]) # 각 점을 모두 포함하는 가장 작은 원 정보 반환 
        print("=======================>{0}".format(radius))
        if radius > 5:
            xs, xe = int(x - radius), int(x + radius)
            ys, ye = int(y - radius), int(y + radius)            

            r_img = bin_img[ys:ye, xs:xe] # 사각형 이미지 추출            
            scaled_img = cv2.resize(r_img, dsize=(50,50), interpolation=cv2.INTER_AREA)
            scaled_img = cv2.resize(scaled_img, dsize=(26,26), interpolation=cv2.INTER_AREA)

            input_img = np.zeros((28, 28)) # 빈 사각형 이미지 만들기
            input_img[1:-1, 1:-1] = scaled_img[:, :] # 경계선 1을 제외한 나머지 영역을 위에서 검출한 이미지로 대체
            cv2.imwrite("number2.png", input_img)

            input_img = input_img.reshape(-1, 28, 28, 1) # 모델의 입력 포맷으로 변환

            mnist_model = keras.models.load_model(filepath="model/mnist_model.h5")
            predicted_value = mnist_model.predict(input_img)
            selected_number = np.argmax(predicted_value)

            return "NUMBER IN IMAGE IS {0}".format(selected_number) 
        else:
            return "no number detected 2"


@app.get("/take-picture2")
def show_take_picture_form2():

    return render_template("webcam-take-picture2.html")

@app.post('/upload-picture3')
def upload_picture3():

    picture = request.files['picture']
    picture_data = np.frombuffer(picture.read(), np.uint8)  # 파일 데이터 -> numpy array
    img = cv2.imdecode( picture_data, cv2.IMREAD_COLOR)

    # print(img.shape)
    # cv2.imwrite("picture.png", img)

    pytesseract.pytesseract.tesseract_cmd = r'C:\Program Files\Tesseract-OCR\tesseract.exe'
    data = pytesseract.image_to_string(image=img, lang="eng+kor")

    return data
    