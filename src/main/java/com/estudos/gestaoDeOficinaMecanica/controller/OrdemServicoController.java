package com.estudos.gestaoDeOficinaMecanica.controller;

import com.estudos.gestaoDeOficinaMecanica.database.model.OrdemdeServico;
import com.estudos.gestaoDeOficinaMecanica.database.repository.PecaRepository;
import com.estudos.gestaoDeOficinaMecanica.service.MecanicoService;
import com.estudos.gestaoDeOficinaMecanica.service.OrdemServicoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;

@Controller
@RequestMapping("/ordens")
public class OrdemServicoController {

    private final OrdemServicoService ordemService;
    private final MecanicoService mecanicoService;
    private final PecaRepository pecaRepository;

    public OrdemServicoController(OrdemServicoService ordemService,
                                  MecanicoService mecanicoService,
                                  PecaRepository pecaRepository) {
        this.ordemService = ordemService;
        this.mecanicoService = mecanicoService;
        this.pecaRepository = pecaRepository;
    }

    // Dashboard - listar todas as OS
    @GetMapping
    public String dashboard(Model model) {
        try {
            model.addAttribute("ordens", ordemService.listarTodas());
            return "ordens/dashboard";
        } catch (Exception e) {
            model.addAttribute("mensagemErro", "Erro ao carregar ordens: " + e.getMessage());
            return "ordens/dashboard";
        }
    }

    // Formulário para abrir nova OS
    @GetMapping("/nova")
    public String formularioNovaOrdem(Model model) {
        model.addAttribute("ordem", new OrdemdeServico());
        model.addAttribute("mecanicos", mecanicoService.listarTodosMecanicos());
        model.addAttribute("pecas", pecaRepository.findAll());
        return "ordens/formulario";
    }

    // Salvar OS
    @PostMapping("/salvar")
    public String salvar(@ModelAttribute OrdemdeServico ordem, RedirectAttributes redirectAttributes) {
        try {
            ordemService.salvar(ordem);
            redirectAttributes.addFlashAttribute("mensagemSucesso", "Ordem de Serviço criada com sucesso!");
            return "redirect:/ordens";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagemErro", e.getMessage());
            return "redirect:/ordens/nova";
        }
    }

    // Iniciar serviço (mudar status para "Em Andamento")
    @PostMapping("/{id}/iniciar")
    public String iniciarServico(@PathVariable UUID id, RedirectAttributes redirectAttributes) {
        try {
            ordemService.iniciarServico(id);
            redirectAttributes.addFlashAttribute("mensagemSucesso", "Serviço iniciado com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagemErro", e.getMessage());
        }
        return "redirect:/ordens";
    }
}
