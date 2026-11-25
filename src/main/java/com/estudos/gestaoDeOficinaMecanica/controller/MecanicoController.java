package com.estudos.gestaoDeOficinaMecanica.controller;

import com.estudos.gestaoDeOficinaMecanica.database.model.Mecanico;
import com.estudos.gestaoDeOficinaMecanica.database.model.dto.MecanicoDto;
import com.estudos.gestaoDeOficinaMecanica.service.MecanicoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;

@Controller
@RequestMapping("/mecanicos")
public class MecanicoController {

    private final MecanicoService mecanicoService;

    public MecanicoController(MecanicoService mecanicoService) {
        this.mecanicoService = mecanicoService;
    }

    // Listar todos os mecânicos
    @GetMapping
    public String listar(Model model) {
        try {
            model.addAttribute("mecanicos", mecanicoService.listarTodosMecanicos());
            return "mecanicos/lista";
        } catch (Exception e) {
            model.addAttribute("mensagemErro", "Erro ao carregar mecânicos: " + e.getMessage());
            return "mecanicos/lista";
        }
    }

    // Abrir formulário para criar novo mecânico
    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("mecanico", new Mecanico());
        return "mecanicos/formulario";
    }

    // Salvar mecânico (criar ou editar)
    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Mecanico mecanico, RedirectAttributes redirectAttributes) {
        try {
            mecanicoService.salvar(mecanico);
            redirectAttributes.addFlashAttribute("mensagemSucesso", "Mecânico salvo com sucesso!");
            return "redirect:/mecanicos";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Erro ao salvar mecânico: " + e.getMessage());
            return "redirect:/mecanicos/novo";
        }
    }

    // Abrir formulário para editar mecânico
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable UUID id, Model model, RedirectAttributes redirectAttributes) {
        try {
            MecanicoDto mecanicoDto = mecanicoService.buscarPorId(id);
            model.addAttribute("mecanico", mecanicoDto.maeparMecanico());
            return "mecanicos/formulario";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Mecânico não encontrado: " + e.getMessage());
            return "redirect:/mecanicos";
        }
    }

    // Excluir mecânico
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable UUID id, RedirectAttributes redirectAttributes) {
        try {
            mecanicoService.excluir(id);
            redirectAttributes.addFlashAttribute("mensagemSucesso", "Mecânico excluído com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Erro ao excluir mecânico: " + e.getMessage());
        }
        return "redirect:/mecanicos";
    }
}
