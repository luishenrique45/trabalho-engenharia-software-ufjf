package com.estudos.gestaoDeOficinaMecanica.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EspecialidadeIncompativelException.class)
    public String handleEspecialidadeIncompativel(EspecialidadeIncompativelException ex,
                                                  RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("mensagemErro", ex.getMessage());
        return "redirect:/ordens/nova";
    }

    @ExceptionHandler(PecaIndisponivelException.class)
    public String handlePecaIndisponivel(PecaIndisponivelException ex,
                                          RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("mensagemErro", ex.getMessage());
        return "redirect:/ordens";
    }
}
