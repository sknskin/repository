from tensorflow import keras
import cv2
import numpy as np

mnist_model = keras.models.load_model("models/mnist_model.h5")

cap = cv2.VideoCapture(0)

if cap.isOpened(): # 카메라가 잘 열렸다면
    idx = 0
    while True:
        idx += 1
        ret, img = cap.read() # 한 프레임 읽기
        if ret: # 잘 읽었다면
            g_img = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY) # 회색조(단색) 이미지 생성
            _, bin_img = cv2.threshold(g_img, 127, 255, cv2.THRESH_BINARY_INV) # 경계를 기준으로 2개의 색상으로 전환

            contours, hierarchy = cv2.findContours(bin_img, cv2.RETR_EXTERNAL, cv2.CHAIN_APPROX_SIMPLE)

            try :
                for contour in contours:
                    (x, y), radius = cv2.minEnclosingCircle(contour) # 각 점을 모두 포함하는 가장 작은 원 정보 반환 
                    if radius > 5:
                        xs, xe = int(x - radius), int(x + radius)
                        ys, ye = int(y - radius), int(y + radius)

                        cv2.rectangle(bin_img, (xs, ys), (xe, ye), (200, 0, 0), 1) # 사각형 그리기

                        r_img = bin_img[ys:ye, xs:xe] # 사각형 이미지 추출
                        scaled_img = cv2.resize(r_img, dsize=(50,50), interpolation=cv2.INTER_AREA)
                        scaled_img = cv2.resize(scaled_img, dsize=(26,26), interpolation=cv2.INTER_AREA)

                        input_img = np.zeros((28, 28)) # 빈 사각형 이미지 만들기
                        input_img[1:-1, 1:-1] = scaled_img[:, :] # 경계선 1을 제외한 나머지 영역을 위에서 검출한 이미지로 대체
                        input_img = input_img.reshape(-1, 28, 28, 1) # 모델의 입력 포맷으로 변환

                        num = np.argmax(mnist_model.predict(input_img)) # 검출한 이미지를 모델에 넣어서 예측값 도출
                        # print("predicted number : ", num)
                        
                        cv2.putText(bin_img, str(num), (xs, ys), cv2.FONT_HERSHEY_PLAIN, 2, (200, 2, 2))                        

            except Exception as e:
                print(e)
                
            cv2.imshow('Camera', bin_img)
            if cv2.waitKey(10)&0xFF == 27:
                break
                
        else:
            print("No Frame")

else:
    print("Camera not available")


cap.release()
cv2.destroyAllWindows()
cv2.waitKey(1)
cv2.waitKey(1)
cv2.waitKey(1)
cv2.waitKey(1)