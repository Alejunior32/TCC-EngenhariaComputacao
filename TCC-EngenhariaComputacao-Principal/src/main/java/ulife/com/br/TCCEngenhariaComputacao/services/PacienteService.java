package ulife.com.br.TCCEngenhariaComputacao.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ulife.com.br.TCCEngenhariaComputacao.dto.convenio.ConvenioMapper;
import ulife.com.br.TCCEngenhariaComputacao.dto.paciente.CadastroPacienteDTO;
import ulife.com.br.TCCEngenhariaComputacao.models.Convenio;
import ulife.com.br.TCCEngenhariaComputacao.models.Paciente;
import ulife.com.br.TCCEngenhariaComputacao.models.Usuario;
import ulife.com.br.TCCEngenhariaComputacao.repositories.PacienteRepository;

import java.util.List;
import java.util.Random;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private AgendamentoConsultaService agendamentoConsultaService;

    @Autowired
    private ConvenioService convenioService;

    @Autowired
    private AgendamentoExameService agendamentoExameService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private EmailService emailService;

    public Paciente buscarPorId(Long idPaciente) {
        return pacienteRepository.findById(idPaciente).orElseThrow(() -> new EntityNotFoundException("Paciente não existe!"));
    }

    public Paciente salvar(Paciente paciente, Usuario usuario, Convenio convenio) {
        Random random = new Random();
        String senha = String.valueOf(random.nextInt(900000) + 100000);
        usuario.setSenha(new BCryptPasswordEncoder().encode("NovaSenha"+senha));

        Usuario usuarioSalvo = usuarioService.salvarUsuario(usuario);
        paciente.setUsuario(usuarioSalvo);

        Convenio convenioSalvo = convenioService.salvar(convenio);
        paciente.setConvenio(convenioSalvo);

        emailService.sendEmail(usuario.getLogin(),"Primeiro acesso do Paciente","Senha para fazer o primeiro acesso na aplicação: NovaSenha"+senha);
        return pacienteRepository.save(paciente);
    }

    public List<Paciente> listar(String palavra) {
        if (palavra != null)
            return pacienteRepository.findAllByPalavraBarraDePesquisa(palavra);

        return pacienteRepository.findAll();
    }

    public Paciente editar(CadastroPacienteDTO cadastroPacienteDTO, Long idPaciente, Long idConvenio) {
        Paciente paciente = buscarPorId(idPaciente);

        paciente.setNome(cadastroPacienteDTO.getNome());
        paciente.setRg(cadastroPacienteDTO.getRg());
        paciente.setCpf(cadastroPacienteDTO.getCpf());
        paciente.setDataNascimento(cadastroPacienteDTO.getDataNascimento());

        Convenio convenio = ConvenioMapper.fromDto(cadastroPacienteDTO.getConvenio());
        convenio.setId(idConvenio);
        convenioService.salvar(convenio);

        paciente.getUsuario().setLogin(cadastroPacienteDTO.getEmail());
        usuarioService.salvarUsuario(paciente.getUsuario());

        return pacienteRepository.save(paciente);
    }

    public Paciente findByUsuario(Usuario usuario) {
        return pacienteRepository.findByUsuario(usuario);
    }

    public boolean usuarioExistente(String email){
        return usuarioService.existsByLogin(email);
    }

    public void excluirPorId(Long idPaciente) throws EntityNotFoundException {
        Paciente paciente = buscarPorId(idPaciente);
        agendamentoConsultaService.excluirAgendamentosPaciente(paciente);
        agendamentoExameService.excluirAgendamentosPaciente(paciente);
        pacienteRepository.delete(paciente);
    }

}
