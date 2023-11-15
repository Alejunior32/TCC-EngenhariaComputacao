import cv2
from cryptography.hazmat.primitives.ciphers import Cipher, algorithms, modes
from flask import request, render_template, redirect, current_app, flash, url_for, Response
from cryptography.hazmat.backends import default_backend
from flask.views import MethodView
from src.db import mysql
import base64

from src.services.reconhecimentoFacial import reconhecer_face


class HomeController(MethodView):
    def get(self):
        return render_template('home.html')


class SenhaController(MethodView):
    def get(self):
        # Obter o último número gerado da variável de aplicação
        last_number = current_app.config.get('last_number', 0)

        # Gerar uma nova senha (assumindo que você deseja uma senha numérica sequencial)
        senha = last_number + 1

        # Atualizar a variável de aplicação com o novo número
        current_app.config['last_number'] = senha

        # Passar o novo número para o template
        return render_template('senha.html', new_number=senha)


class BuscarUsuarioPorCpfController(MethodView):
    def get(self):
        return render_template('form-cpf.html')

    def post(self):
        cpf = request.form['cpf']
        # print(cpf)
        # cpf_encriptado = self.encrypt_cpf(cpf)

        with mysql.cursor() as cur:
            cur.execute("SELECT * FROM paciente WHERE cpf = %s", (cpf))
            data = cur.fetchall()

        if data:
            # Usuário encontrado
            primeiro_registro = data[0]
            imagem_paciente = primeiro_registro[6]
            resultado_reconhecimento = reconhecer_face(imagem_paciente)
            if resultado_reconhecimento:
                return redirect(url_for('reconhecimento-facial'))
            else:
                flash('Paciente encontrado,\n mas não reconhecido!')
                return redirect(url_for('buscar-usuario'))
        else:
            # Usuário não encontrado
            flash('Paciente não encontrado', 'error')  # 'error' é uma classe de estilo para destacar a mensagem de erro
            return redirect(url_for('buscar-usuario'))


class ReconhecimentoFacialController(MethodView):
    def get(self):
        flash('Paciente encontrado\n e reconhecido!')
        return render_template('reconhecimento.html')


class VideoController(MethodView):
    def video_feed(self):
        def generate():
            while True:
                frame = cv2.imread('src/temp/foto.jpg')  # Obter o quadro processado do OpenCV
                ret, jpeg = cv2.imencode('.jpg', frame)
                if ret:
                    frame_bytes = jpeg.tobytes()
                    yield (b'--frame\r\n'
                           b'Content-Type: image/jpeg\r\n\r\n' + frame_bytes + b'\r\n\r\n')

        return Response(generate(), mimetype='multipart/x-mixed-replace; boundary=frame')

    def get(self):
        return self.video_feed()

    # def __init__(self):
    #     self.IV = b'AAAAAAAAAAAAAAAA'
    #     self.key = "0123456789abcdef".encode('utf-8')

    # def decrypt_cpf(self, encrypted_cpf):
    #     encrypted_data = base64.b64decode(encrypted_cpf)
    #     cipher = Cipher(algorithms.AES(self.key), modes.CBC(self.IV), backend=default_backend())
    #     decryptor = cipher.decryptor()
    #     decrypted_data = decryptor.update(encrypted_data) + decryptor.finalize()
    #     return decrypted_data.decode('utf-8')

    # def encrypt_cpf(self, cpf):
    #     cipher = Cipher(algorithms.AES(self.key), modes.CBC(self.IV), backend=default_backend())
    #     encryptor = cipher.encryptor()
    #     cpf_bytes = cpf.encode('utf-8')
    #     padded_cpf = cpf_bytes + b'\0' * (16 - len(cpf_bytes) % 16)
    #     ciphertext = encryptor.update(padded_cpf) + encryptor.finalize()
    #     return base64.b64encode(ciphertext).decode('utf-8')