package br.ufscar.dc.dsw.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.ufscar.dc.dsw.dao.IClienteDAO;
import br.ufscar.dc.dsw.domain.Cliente;

@Component
public class UniqueCPFValidator implements ConstraintValidator<UniqueCPF, String> {

    @Autowired
    private IClienteDAO dao;

    @Override
    public boolean isValid(String CPF, ConstraintValidatorContext context) {
        if (dao != null) {
            Cliente aluno = dao.findByCPF(CPF);
            return aluno == null;
        } else {
            // Durante a execução da classe MvcApplication
            // não há injeção de dependência
            return true;
        }
    }
}
