package br.ufscar.dc.dsw.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.dao.IUsuarioDAO;

@Component
public class UniqueNomeValidator implements ConstraintValidator<UniqueNome, String> {

    @Autowired
    private IUsuarioDAO dao;

    @Override
    public boolean isValid(String nome, ConstraintValidatorContext context) {
        if (dao != null) {
            Usuario aluno = dao.findByNome(nome);
            return aluno == null;
        } else {
            // Durante a execução da classe MvcApplication
            // não há injeção de dependência
            return true;
        }
    }
}
