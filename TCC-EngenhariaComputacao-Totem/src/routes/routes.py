from src.controllers.controller import *
from src.controllers.errors import *

routes = {
    "not_found_route":404,"not_found_controller":NotFoundController.as_view("not_found"),
    "home_route":"/", "home_controller":HomeController.as_view("home"),
    "senha_route":"/senha", "senha_controller":SenhaController.as_view("senha"),
    "busca_usuario_por_cpf_route":"/buscar-usuario-cpf", "busca_usuario_por_cpf_controller":BuscarUsuarioPorCpfController.as_view("buscar-usuario"),
    "reconhecimento_facial":"/reconhecimento-facial", "reconhecimento_facial_controller": ReconhecimentoFacialController.as_view("reconhecimento-facial"),
    "video_feed":"/video_feed","video_controller":VideoController.as_view("video"),
}