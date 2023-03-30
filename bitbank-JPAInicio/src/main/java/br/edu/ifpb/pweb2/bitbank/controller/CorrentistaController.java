package br.edu.ifpb.pweb2.bitbank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifpb.pweb2.bitbank.model.Correntista;
import br.edu.ifpb.pweb2.bitbank.repository.CorrentistaRepository;

@Controller
@RequestMapping("/correntistas")
public class CorrentistaController {

    @Autowired
    CorrentistaRepository correntistaRepository;

    @RequestMapping("/form")
    public ModelAndView getForm(Correntista correntista, ModelAndView mav) {
        mav.addObject("correntista", correntista);
        mav.setViewName("correntistas/form");
        return mav;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView save(Correntista correntista, ModelAndView mav, RedirectAttributes attrs) {
        correntistaRepository.save(correntista);
        mav.setViewName("redirect:correntistas");
        attrs.addFlashAttribute("mensagem", "Correntista cadastrado com sucesso!");
        return mav;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView listAll(ModelAndView mav) {
        mav.addObject("correntistas", correntistaRepository.findAll());
        mav.addObject("menu", "correntistas");
        mav.setViewName("correntistas/list");
        return mav;
    }

    @RequestMapping("/{id}")
    public ModelAndView getCorrentistaById(@PathVariable(value = "id") Integer id, ModelAndView mav) {
        mav.addObject("correntista", correntistaRepository.findById(id));
        mav.setViewName("correntistas/form");
        return mav;
    }

    @RequestMapping("/{id}/delete")
    public ModelAndView deleteById(@PathVariable("id") Integer id, ModelAndView mav, RedirectAttributes attr) {
        System.out.println("sdklfjsdlkfjsdlkjlsdkfjlsdkjfklsdjflk;dsj");
        correntistaRepository.deleteById(id);
        attr.addFlashAttribute("mensagem", "Correntista removido com sucesso!");
        mav.setViewName("redirect:/correntistas");
        return mav;
    }
}