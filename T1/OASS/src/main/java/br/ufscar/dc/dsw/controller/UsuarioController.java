package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.UsuarioDAO;
import br.ufscar.dc.dsw.domain.Usuario;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/usuarios/*")
public class UsuarioController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private UsuarioDAO dao;

    @Override
    public void init() {
        dao = new UsuarioDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getPathInfo();
        if (action == null) {
            action = "";
        }

        try {
            switch (action) {
                case "/cadastro":
                    apresentaFormCadastro(request, response);
                    break;
                case "/insercao":
                    insere(request, response);
                    break;
                case "/remocao":
                    remove(request, response);
                    break;
                case "/edicao":
                    apresentaFormEdicao(request, response);
                    break;
                case "/atualizacao":
                    atualize(request, response);
                    break;
                case "/login":
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/Usuarios/login.jsp");
                    dispatcher.forward(request, response);
                    break;
                case "/valida":
                    validaLogin(request, response);
                    break;
                case "/home":
                    apresentaHome(request, response);
                    break;
                case "/CRUD":
                    lista(request, response);
                    break;
            }
        } catch (RuntimeException | IOException | ServletException e) {
            throw new ServletException(e);
        }
    }

    private void validaLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        Usuario usuario = dao.get(email, senha);

        if (usuario.getEmail() == email && usuario.getSenha() == senha){
            request.getSession().setAttribute("usuarioLogado", usuario);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/Usuarios/home.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect("/OASS/usuarios/login");
        }
    }

    private void apresentaHome(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Usuarios/home.jsp");
        dispatcher.forward(request, response);
    }

    private void lista(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Usuario> listaUsuarios = dao.getAll();
        request.setAttribute("listaUsuarios", listaUsuarios);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Usuarios/lista.jsp");
        dispatcher.forward(request, response);
    }

    private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Usuarios/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void apresentaFormEdicao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long cpf = Long.parseLong(request.getParameter("CPF"));
        Usuario usuario = dao.get(cpf);
        request.setAttribute("usuario", usuario);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Usuarios/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void insere(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        Long cpf = Long.parseLong(request.getParameter("CPF"));
        String papel = request.getParameter("papel");

        Usuario usuario = new Usuario(nome, email, senha, cpf, papel);

        if (papel.equals("CLIENTE") || papel.equals("AMBOS")) {
            Long telefone = Long.parseLong(request.getParameter("telefone"));
            String sexo = request.getParameter("sexo");
            LocalDate dataDeNascimento = LocalDate.parse(request.getParameter("dataDeNascimento"), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            usuario.setTelefone(telefone);
            usuario.setSexo(sexo);
            usuario.setDataDeNascimento(dataDeNascimento);
        }

        if (papel.equals("PROFISSIONAL") || papel.equals("AMBOS")) {
            String especialidade = request.getParameter("especialidade");
            usuario.setEspecialidade(especialidade);
        }

        dao.insert(usuario);
        response.sendRedirect("lista");
    }

    private void atualize(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Long cpf = Long.parseLong(request.getParameter("CPF"));
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String papel = request.getParameter("papel");

        Usuario usuario = new Usuario(nome, email, senha, cpf, papel);

        if (papel.equals("CLIENTE") || papel.equals("AMBOS")) {
            Long telefone = Long.parseLong(request.getParameter("telefone"));
            String sexo = request.getParameter("sexo");
            LocalDate dataDeNascimento = LocalDate.parse(request.getParameter("dataDeNascimento"), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            usuario.setTelefone(telefone);
            usuario.setSexo(sexo);
            usuario.setDataDeNascimento(dataDeNascimento);
        }

        if (papel.equals("PROFISSIONAL") || papel.equals("AMBOS")) {
            String especialidade = request.getParameter("especialidade");
            usuario.setEspecialidade(especialidade);
        }

        dao.update(usuario);
        response.sendRedirect("lista");
    }

    private void remove(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Long cpf = Long.parseLong(request.getParameter("CPF"));

        Usuario usuario = new Usuario(cpf);
        dao.delete(usuario);
        response.sendRedirect("lista");
    }
}
