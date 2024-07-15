package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.ClienteDAO;
import br.ufscar.dc.dsw.domain.Cliente;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;

@WebServlet(urlPatterns = "/clientes/*")
public class ClienteController extends HttpServlet {

    private static final long serialVersionUID = 1L; 
    private ClienteDAO dao;

    @Override
    public void init() {
        dao = new ClienteDAO();
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
                default:
                    lista(request, response);
                    break;
            }
        } catch (RuntimeException | IOException | ServletException e) {
            throw new ServletException(e);
        }
    }

    private void lista(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        List<Cliente> listaClientes = dao.getAll();
        request.setAttribute("listaClientes", listaClientes);
        request.setAttribute("contextPath", request.getContextPath().replace("/", ""));
        RequestDispatcher dispatcher = request.getRequestDispatcher("/cliente/lista.jsp");
        dispatcher.forward(request, response);
    }
    
    private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/cliente/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void apresentaFormEdicao(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        Long CPF = Long.parseLong(request.getParameter("CPF"));
        Cliente cliente = dao.get(CPF);
        request.setAttribute("cliente", cliente);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/cliente/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void insere(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        Long CPF = Long.parseLong(request.getParameter("CPF"));
        Long telefone = Long.parseLong(request.getParameter("telefone"));
        String sexo = request.getParameter("sexo");
        String dataDeNascimento = request.getParameter("dataDeNascimento");

        Date dataDeNascimentoSQL = covertStringToDate(dataDeNascimento);
        
        Cliente cliente = new Cliente(name, email, senha, CPF, telefone, sexo, dataDeNascimento);
        dao.insert(cliente);
        response.sendRedirect("lista");
    }

    private void atualize(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        Long CPF = Long.parseLong(request.getParameter("CPF"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        Long telefone = Long.parseLong(request.getParameter("telefone"));
        String sexo = request.getParameter("sexo");
        String dataDeNascimento = request.getParameter("dataDeNascimento");
        
        Cliente cliente = new Cliente(CPF, name, email, senha, telefone, sexo, dataDeNascimento);
        dao.update(cliente);
        response.sendRedirect("lista");
    }

    private void remove(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        Long CPF = Long.parseLong(request.getParameter("CPF"));

        Cliente cliente = new Cliente(CPF);
        dao.delete(cliente);
        response.sendRedirect("lista");
    }
}
