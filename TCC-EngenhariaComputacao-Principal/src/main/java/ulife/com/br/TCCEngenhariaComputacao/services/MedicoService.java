package ulife.com.br.TCCEngenhariaComputacao.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ulife.com.br.TCCEngenhariaComputacao.dto.medico.CadastroMedicoDTO;
import ulife.com.br.TCCEngenhariaComputacao.models.Medico;
import ulife.com.br.TCCEngenhariaComputacao.models.Usuario;
import ulife.com.br.TCCEngenhariaComputacao.repositories.MedicoRepository;

import java.util.List;
import java.util.Random;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    AgendamentoConsultaService agendamentoConsultaService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private EmailService emailService;

    public List<Medico> listar(String palavra) {
        if (palavra != null)
            return medicoRepository.findAllByPalavraBarraDePesquisa(palavra);

        return medicoRepository.findAll();
    }

    public Medico findById(Long idMedico) {
        return medicoRepository.findById(idMedico).orElseThrow(() -> new EntityNotFoundException("Medico não encontrado!"));
    }

    public Medico save(Medico medico, Usuario usuario) {
        Random random = new Random();
        String senha = String.valueOf(random.nextInt(900000) + 100000);
        usuario.setSenha(new BCryptPasswordEncoder().encode("NovaSenha"+senha));

        Usuario usuarioSalvo = usuarioService.salvarUsuario(usuario);
        medico.setUsuario(usuarioSalvo);

        emailService.sendEmail(usuario.getLogin(),"Primeiro acesso médico","Senha para fazer o primeiro acesso na aplicação: NovaSenha"+senha);
        return medicoRepository.save(medico);
    }

    public Medico findByUsuario(Usuario usuario) {
        return medicoRepository.findByUsuario(usuario);
    }

    public List<Medico> findAllByEspecialidadeId(Long especialidadeId){
        return medicoRepository.findAllByEspecialidadeId(especialidadeId);
    }

    public boolean usuarioExistente(String email){
        return usuarioService.existsByLogin(email);
    }

    public void excluirPorId(Long idMedico) throws EntityNotFoundException {
        Medico medico = findById(idMedico);
        agendamentoConsultaService.excluirAgendamentosMedico(medico);
        medicoRepository.delete(medico);
    }

    public Medico editar(CadastroMedicoDTO cadastroMedicoDTO,Long idMedico) {
        Medico medico = findById(idMedico);

        medico.setNome(cadastroMedicoDTO.getNome());
        medico.setCrm(cadastroMedicoDTO.getCrm());
        medico.setEspecialidade(cadastroMedicoDTO.getEspecialidade());

        medico.getUsuario().setLogin(cadastroMedicoDTO.getEmail());
        usuarioService.salvarUsuario(medico.getUsuario());

        return medicoRepository.save(medico);
    }
}
