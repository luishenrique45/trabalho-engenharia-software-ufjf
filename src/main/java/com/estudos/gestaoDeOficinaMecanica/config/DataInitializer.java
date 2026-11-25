package com.estudos.gestaoDeOficinaMecanica.config;

import com.estudos.gestaoDeOficinaMecanica.database.model.Especialidade;
import com.estudos.gestaoDeOficinaMecanica.database.model.Mecanico;
import com.estudos.gestaoDeOficinaMecanica.database.model.Peca;
import com.estudos.gestaoDeOficinaMecanica.database.model.Usuario;
import com.estudos.gestaoDeOficinaMecanica.database.repository.MecanicoRepository;
import com.estudos.gestaoDeOficinaMecanica.database.repository.PecaRepository;
import com.estudos.gestaoDeOficinaMecanica.database.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initData(UsuarioRepository usuarioRepository,
                                     MecanicoRepository mecanicoRepository,
                                     PecaRepository pecaRepository,
                                     PasswordEncoder passwordEncoder) {
        return args -> {
            // Cria mecânicos de exemplo se a tabela estiver vazia
            Mecanico mec1 = null;
            if (mecanicoRepository.count() == 0) {
                mec1 = new Mecanico(null, "João Silva", Especialidade.Motor);
                Mecanico mec2 = new Mecanico(null, "Carlos Santos", Especialidade.Supensâo);
                mecanicoRepository.save(mec1);
                mecanicoRepository.save(mec2);
            }

            // Cria usuário inicial admin/admin para primeiro acesso
            if (usuarioRepository.count() == 0) {
                Usuario admin = new Usuario();
                admin.setLogin("admin");
                admin.setSenha(passwordEncoder.encode("admin"));
                admin.setPapel("ADMIN");
                admin.setMecanico(mec1);
                usuarioRepository.save(admin);
            }

            // Cria peças de exemplo para testar as regras de estoque
            if (pecaRepository.count() == 0) {
                Peca p1 = new Peca();
                p1.setNome("Filtro de Óleo");
                p1.setQuantidadeEstoque(10);
                p1.setPreco(50.0);
                p1.setFornecedor("AutoPeças Brasil");
                pecaRepository.save(p1);

                Peca p2 = new Peca();
                p2.setNome("Amortecedor Dianteiro");
                p2.setQuantidadeEstoque(0); // Sem estoque para testar a exceção
                p2.setPreco(250.0);
                p2.setFornecedor("Distribuidora Central");
                pecaRepository.save(p2);
            }
        };
    }
}
