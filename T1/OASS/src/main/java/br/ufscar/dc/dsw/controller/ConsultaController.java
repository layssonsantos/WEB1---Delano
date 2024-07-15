package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.ConsultaDAO;
import br.ufscar.dc.dsw.domain.Consulta;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/consultas/*")
public class ConsultaController extends HttpServlet {

    private static final long serialVersionUID = 1L; 
    private ConsultaDAO dao;

    @Override
    public void init() {
        dao = new ConsultaDAO();
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
        List<Consulta> listaConsultas = dao.getAll();
        request.setAttribute("listaConsultas", listaConsultas);
        request.setAttribute("contextPath", request.getContextPath().replace("/", ""));
        RequestDispatcher dispatcher = request.getRequestDispatcher("/consulta/lista.jsp");
        dispatcher.forward(request, response);
    }
    
    private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/consulta/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void apresentaFormEdicao(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Consulta consulta = dao.get(id);
        request.setAttribute("consulta", consulta);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/consulta/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void insere(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        Date data = Date.valueOf(request.getParameter("data"));
        Time hora = Time.valueOf(request.getParameter("hora"));
        Long CPFCliente = Long.parseLong(request.getParameter("CPFCliente"));
        Long CPFProfissional = Long.parseLong(request.getParameter("CPFProfissional"));
        
        Consulta consulta = new Consulta(data, hora, CPFCliente, CPFProfissional);
        dao.insert(consulta);
        response.sendRedirect("lista");
    }

    private void atualize(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        Long id = Long.parseLong(request.getParameter("id"));
        Date data = Date.valueOf(request.getParameter("data"));
        Time hora = Time.valueOf(request.getParameter("hora"));
        Long CPFCliente = Long.parseLong(request.getParameter("CPFCliente"));
        Long CPFProfissional = Long.parseLong(request.getParameter("CPFProfissional"));
        
        Consulta consulta = new Consulta(id, data, hora, CPFCliente, CPFProfissional);
        dao.update(consulta);
        response.sendRedirect("lista");
    }

    private void remove(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));

        Consulta consulta = new Consulta(id);
        dao.delete(consulta);
        response.sendRedirect("lista");
    }
}
