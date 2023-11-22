from flask import Flask
from src.routes.routes import *

app = Flask(__name__)
app.secret_key = b'\xa4i\xee\x86Z\xe3\x073\x15x\xb9\xa5V\xa4\xa9\x8b\x91\xec\xe8\xf5\xf5\x12_k'

app.register_error_handler(routes["not_found_route"],routes["not_found_controller"])
app.add_url_rule(routes["home_route"],view_func=routes["home_controller"])
app.add_url_rule(routes["senha_route"],view_func=routes["senha_controller"])
app.add_url_rule(routes["busca_usuario_por_cpf_route"],view_func=routes["busca_usuario_por_cpf_controller"])
app.add_url_rule(routes["reconhecimento_facial"],view_func=routes["reconhecimento_facial_controller"])
app.add_url_rule(routes["video_feed"],view_func=routes["video_controller"])

