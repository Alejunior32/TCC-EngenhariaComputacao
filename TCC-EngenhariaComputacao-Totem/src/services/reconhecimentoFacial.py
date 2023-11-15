from io import BytesIO
import cv2
import face_recognition
from PIL import Image


def verificar_pessoa(caminho_imagem_paciente, imagem_teste):
    # Carrega a imagem do paciente
    imagem_paciente = face_recognition.load_image_file(caminho_imagem_paciente)
    encoding_paciente = face_recognition.face_encodings(imagem_paciente)[0]

    # Executa o reconhecimento facial
    face_locations = face_recognition.face_locations(imagem_teste)
    face_encodings = face_recognition.face_encodings(imagem_teste, face_locations)

    for encoding_teste, face_location in zip(face_encodings, face_locations):
        match = face_recognition.compare_faces([encoding_paciente], encoding_teste)[0]

        if match:
            # Retorne True se a pessoa for reconhecida
            return True

        # Retorne False se a pessoa não for reconhecida
        return False


def reconhecer_face(imagem_paciente):

    imagem = Image.open(BytesIO(imagem_paciente))
    imagem.save("src/temp/paciente.jpg", "JPEG")
    caminho_imagem_paciente = 'src/temp/paciente.jpg'

    # Inicialize a câmera
    cap = cv2.VideoCapture(0)

    # Capture um único quadro da câmera
    ret, frame = cap.read()

    # Converta o quadro de BGR para RGB (necessário para face_recognition)
    rgb_frame = cv2.cvtColor(frame, cv2.COLOR_BGR2RGB)

    # Realize o reconhecimento facial e exiba o nome no console
    resultado_reconhecimento = verificar_pessoa(caminho_imagem_paciente, rgb_frame)

    if resultado_reconhecimento:
        cv2.imwrite('src/temp/foto.jpg', frame)

    return resultado_reconhecimento
