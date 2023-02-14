import os

from flask import Flask, redirect, render_template, request

import pytesseract
import pickle
import numpy as np
import cv2
from tensorflow import keras

#Kako OCR api
import json
import requests
import sys
# import cv2

import re
from datetime import datetime

LIMIT_PX = 1024
LIMIT_BYTE = 1024*1024  # 1MB
LIMIT_BOX = 40


def kakao_ocr_resize(image_path: str):
    """
    ocr detect/recognize api helper
    ocr api의 제약사항이 넘어서는 이미지는 요청 이전에 전처리가 필요.

    pixel 제약사항 초과: resize
    용량 제약사항 초과  : 다른 포맷으로 압축, 이미지 분할 등의 처리 필요. (예제에서 제공하지 않음)

    :param image_path: 이미지파일 경로
    :return:
    """
    image = cv2.imread(image_path)
    height, width, _ = image.shape

    if LIMIT_PX < height or LIMIT_PX < width:
        ratio = float(LIMIT_PX) / max(height, width)
        image = cv2.resize(image, None, fx=ratio, fy=ratio)
        height, width, _ = height, width, _ = image.shape

        # api 사용전에 이미지가 resize된 경우, recognize시 resize된 결과를 사용해야함.
        image_path = "{}_resized.jpg".format(image_path)
        cv2.imwrite(image_path, image)

        return image_path
    return None


def kakao_ocr(image_path: str, appkey: str):
    """
    OCR api request example
    :param image_path: 이미지파일 경로
    :param appkey: 카카오 앱 REST API 키
    """
    API_URL = 'https://dapi.kakao.com/v2/vision/text/ocr'

    headers = {'Authorization': 'KakaoAK {}'.format(appkey)}

    image = cv2.imread(image_path)
    jpeg_image = cv2.imencode(".jpg", image)[1]
    data = jpeg_image.tobytes()


    return requests.post(API_URL, headers=headers, files={"image": data})

def create_app(test_config=None):
    # create and configure the app
    app = Flask(__name__, instance_relative_config=True) # create web application
    
    # solve error code
    if __name__ ==  '__main__':
        app.run(debug=True)

    app.config.from_mapping(
        SECRET_KEY='dev',
        DATABASE=os.path.join(app.instance_path, 'flaskr.sqlite'),
    )

    if test_config is None:
        # load the instance config, if it exists, when not testing
        app.config.from_pyfile('config.py', silent=True)
    else:
        # load the test config if passed in
        app.config.from_mapping(test_config)

    # ensure the instance folder exists
    try:
        os.makedirs(app.instance_path)
    except OSError:
        pass


    # app.register_blueprint(project.bp)

    # default page
    @app.route('/')
    def home():
        
        return render_template('home.html')

    # default page
    @app.route('/ocr-home')
    def ocr_home_page():
        
        return render_template('ocr-home.html')

    # ocr main page
    @app.get('/ocr-main')
    def ocr_main_page():

        return render_template('ocr-main.html')

    # ocr picture form
    @app.get('/take-picture')
    def show_take_picture_form():

        return render_template('take-picture.html')

    # ocr scan form
    @app.get('/take-scan')
    def show_take_scan_form():

        return render_template('take-scan.html')



    # ocr picture data upload
    @app.post("/upload-picture")
    def upoload_picture():

        image_path, appkey = 'instance/data-files/identify-TableNo01.png', '3606a90ae3f0a7d4df69fab8ccaaa457' # 손병우 REST API

        picture = request.files['picture']
        # picture.save(picture.filename)

        mnist_data = np.frombuffer(picture.read(), np.uint8)  # 파일 데이터 -> numpy array
        imgt = cv2.imdecode( mnist_data, cv2.IMREAD_COLOR)   # numpy array -> image formatted numpy array
        # imgt = cv2.cvtColor(imgt, cv2.COLOR_BGR2RGB)
        cv2.imwrite(image_path, imgt)

        
        
        resize_impath = kakao_ocr_resize(image_path)
        if resize_impath is not None:
            image_path = resize_impath
            # print("원본 대신 리사이즈된 이미지를 사용합니다.")

        output = kakao_ocr(image_path, appkey).json()

        #아스키코드 해제해줘야 한국어로 나옴
        # print("[OCR] output:\n{}\n".format(json.dumps(output, ensure_ascii=False,sort_keys=True, indent=2)))

        exp = re.compile("\d{2}([0]\d|[1][0-2])([0][1-9]|[1-2]\d|[3][0-1])[-]*[1-4]\d{6}")
        
        id_no = "재인증 필요"
        for data in output["result"]:
            recognition_word = data["recognition_words"][0]
            # print(recognition_word)            
            if exp.match(recognition_word):
                id_no = recognition_word

        if (id_no == "재인증 필요"):
            print('신분증 재인증이 필요합니다.')
            return render_template('ocr-main.html')
        else:
            birthDate = id_no.split('-')

            birthYear = int(birthDate[0][:1])
            thisYear = datetime.today().year
            
            if (int(birthDate[1][0]) == 1 or 2):
                print('신분증 인식이 완료되었습니다.')
                return redirect('http://localhost:3000/tablet/')
            else:
                if (thisYear - (2000 + birthYear) >= 19):
                    print('신분증 인식이 완료되었습니다.')
                    return redirect('http://localhost:3000/tablet/')
                else:
                    print('20세 미만입니다.')
                    return render_template('ocr-main.html')

        

    # ocr scan data upload
    # @app.post('/upload-scan')
    # def upload_picture3():

    #     picture = request.files['picture']
    #     picture_data = np.frombuffer(picture.read(), np.uint8)  # 파일 데이터 -> numpy array
    #     img = cv2.imdecode( picture_data, cv2.IMREAD_COLOR)

    #     # print(img.shape)
    #     # cv2.imwrite("picture.png", img)

    #     pytesseract.pytesseract.tesseract_cmd = r'C:\Program Files\Tesseract-OCR\tesseract.exe'
    #     data = pytesseract.image_to_string(image=img, lang="eng+kor")

    #     return data

    return app