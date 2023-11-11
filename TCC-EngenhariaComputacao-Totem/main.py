from src.app import app
from cryptography.hazmat.primitives.ciphers import Cipher, algorithms, modes
from cryptography.hazmat.backends import default_backend
import base64


HOST = 'localhost'
PORT = 4000
DEBUG = True

if(__name__ == '__main__'):
    app.run(HOST, PORT ,DEBUG)




