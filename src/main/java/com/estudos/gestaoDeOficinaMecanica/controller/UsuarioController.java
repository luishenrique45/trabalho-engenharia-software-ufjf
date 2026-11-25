package com.estudos.gestaoDeOficinaMecanica.controller;

import com.estudos.gestaoDeOficinaMecanica.database.model.Usuario;
import com.estudos.gestaoDeOficinaMecanica.service.MecanicoService;
import com.estudos.gestaoDeOficinaMecanica.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;

@Controller
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final MecanicoService mecanicoService;

    public UsuarioController(UsuarioService usuarioService, MecanicoService mecanicoService) {
        this.usuarioService = usuarioService;
        this.mecanicoService = mecanicoService;
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/ordens";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/usuarios")
    public String listar(Model model) {
        try {
            model.addAttribute("usuarios", usuarioService.listarTodos());
            return "usuarios/lista";
        } catch (Exception e) {
            model.addAttribute("mensagemErro", "Erro ao carregar usuários: " + e.getMessage());
            return "usuarios/lista";
        }
    }

    @GetMapping("/usuarios/novo")
    public String novo(Model model) {
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("mecanicos", mecanicoService.listarTodosMecanicos());
        return "usuarios/formulario";
    }

    @PostMapping("/usuarios/salvar")
    public String salvar(@ModelAttribute Usuario usuario, RedirectAttributes redirectAttributes) {
        try {
            usuarioService.salvar(usuario);
            redirectAttributes.addFlashAttribute("mensagemSucesso", "Usuário salvo com sucesso!");
            return "redirect:/usuarios";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Erro ao salvar usuário: " + e.getMessage());
            return "redirect:/usuarios/novo";
        }
    }

    @GetMapping("/usuarios/excluir/{id}")
    public String excluir(@PathVariable UUID id, RedirectAttributes redirectAttributes) {
        try {
            usuarioService.excluir(id);
            redirectAttributes.addFlashAttribute("mensagemSucesso", "Usuário excluído com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Erro ao excluir usuário: " + e.getMessage());
        }
        return "redirect:/usuarios";
    }
}
