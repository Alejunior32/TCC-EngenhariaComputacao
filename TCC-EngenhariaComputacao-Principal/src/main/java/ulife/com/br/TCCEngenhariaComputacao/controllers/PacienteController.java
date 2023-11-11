package ulife.com.br.TCCEngenhariaComputacao.controllers;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ulife.com.br.TCCEngenhariaComputacao.dto.convenio.CadastroConvenioDTO;
import ulife.com.br.TCCEngenhariaComputacao.dto.convenio.ConvenioMapper;
import ulife.com.br.TCCEngenhariaComputacao.dto.paciente.CadastroPacienteDTO;
import ulife.com.br.TCCEngenhariaComputacao.dto.paciente.PacienteMapper;
import ulife.com.br.TCCEngenhariaComputacao.dto.usuario.UsuarioMapper;
import ulife.com.br.TCCEngenhariaComputacao.services.PacienteService;


@Controller
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    PacienteService pacienteService;

    @GetMapping
    public ModelAndView listarPacientes(@RequestParam(name = "palavra", required = false) String palavra ) {
        ModelAndView mv = new ModelAndView("paciente/lista.html");
        mv.addObject("pacientes", pacienteService.listar(palavra));
        return mv;
    }

    @GetMapping("/detalhes")
    public ModelAndView detalhesPaciente(@RequestParam Long idPaciente){
        ModelAndView mv = new ModelAndView("medico/detalhes.html");

        try {
            mv.addObject("medico", pacienteService.buscarPorId(idPaciente));
        }catch (EntityNotFoundException exception){
            mv.addObject("erroBusca",exception.getMessage());
        }
        return mv;
    }

    @GetMapping("cadastrar")
    public ModelAndView formularioCadastro(){
        ModelAndView mv = new ModelAndView("paciente/form.html");
        mv.addObject("pacienteDTO", new CadastroPacienteDTO());
        mv.addObject("convenioDTO", new CadastroConvenioDTO());
        return mv;
    }

    @PostMapping("cadastrar")
    public ModelAndView cadastrarPaciente(@Valid CadastroPacienteDTO cadastroPacienteDTO, CadastroConvenioDTO cadastroConvenioDTO, RedirectAttributes redirectAttributes){
        ModelAndView mv;

        if (pacienteService.usuarioExistente(cadastroPacienteDTO.getEmail())){
            mv = new ModelAndView("redirect:/paciente/cadastrar");
            mv.addObject("erro","Usuário já cadastrado");
            return mv;
        }

        mv = new ModelAndView("redirect:/paciente");
        mv.addObject("mensagem", "Paciente cadastrado com sucesso!");
        pacienteService.salvar(PacienteMapper.fromDto(cadastroPacienteDTO),UsuarioMapper.fromPaciente(cadastroPacienteDTO), ConvenioMapper.fromDto(cadastroConvenioDTO));

        return  mv;
    }

}