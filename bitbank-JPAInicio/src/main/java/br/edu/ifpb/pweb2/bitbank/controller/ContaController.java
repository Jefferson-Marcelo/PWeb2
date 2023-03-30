package br.edu.ifpb.pweb2.bitbank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifpb.pweb2.bitbank.model.Conta;
import br.edu.ifpb.pweb2.bitbank.model.Correntista;
import br.edu.ifpb.pweb2.bitbank.repository.ContaRepository;
import br.edu.ifpb.pweb2.bitbank.repository.CorrentistaRepository;

@Controller
@RequestMapping("/contas")
public class ContaController {

    @Autowired
    ContaRepository contaRepository;

    @Autowired
    CorrentistaRepository correntistaRepository;

    @RequestMapping("/form")
    public ModelAndView getForm(ModelAndView modelAndView) {
        modelAndView.setViewName("contas/form");
        modelAndView.addObject("conta", new Conta(new Correntista()));
        return modelAndView;
    }

    @ModelAttribute("correntistaItems")
    public List<Correntista> getCorrentistas() {
        return correntistaRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView save(Conta conta, ModelAndView modelAndView, RedirectAttributes attrs) {
        Correntista correntista = correntistaRepository.findById(conta.getCorrentista().getId());
        conta.setCorrentista(correntista);
        contaRepository.save(conta);
        modelAndView.setViewName("redirect:contas");
        attrs.addFlashAttribute("mensagem", "Conta cadastrada com sucesso!");
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView listAll(ModelAndView modelAndView) {
        modelAndView.setViewName("contas/list");
        modelAndView.addObject("menu", "contas");
        modelAndView.addObject("contas", contaRepository.findAll());
        return modelAndView;
    }

    @RequestMapping("/{id}")
    public ModelAndView getContaById(@PathVariable(value = "id") Integer id, ModelAndView mav) {
        mav.addObject("conta", contaRepository.findById(id));
        mav.setViewName("contas/form");
        return mav;
    }

    @RequestMapping("/{id}/delete")
    public ModelAndView deleteById(@PathVariable("id") Integer id, ModelAndView mav, RedirectAttributes attr) {
        contaRepository.deleteById(id);
        attr.addFlashAttribute("mensagem", "Conta removida com sucesso!");
        mav.setViewName("redirect:/contas");
        return mav;
    }
}
