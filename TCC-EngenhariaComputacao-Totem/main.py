from src.app import app
import base64


HOST = 'localhost'
PORT = 4000
DEBUG = True

if(__name__ == '__main__'):
    app.run(HOST, PORT ,DEBUG)




