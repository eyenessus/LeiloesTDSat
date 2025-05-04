/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdutosDAO {

    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    public void cadastrarProduto(ProdutosDTO produto) {
        try {
            conn = new conectaDAO().connectDB();

            String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";
            conn.prepareStatement(sql);
            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());
            prep.executeUpdate();
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto: " + e.getMessage());
        } finally {
            try {
                if (conn != null)
                    conn.close();
                if (prep != null)
                    prep.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }

    public ArrayList<ProdutosDTO> listarProdutos() {
        try {
            conn = new conectaDAO().connectDB();
            String sql = "SELECT * FROM produtos";
            prep = conn.prepareStatement(sql);
            resultset = prep.executeQuery();

            while (resultset.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                produto.setStatus(resultset.getString("status"));
                listagem.add(produto);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar produtos: " + e.getMessage());
        } finally {
            try {
                if (conn != null)
                    conn.close();
                if (prep != null)
                    prep.close();
                if (resultset != null)
                    resultset.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar conexão: " + e.getMessage());
            }
        }
        return listagem;
    }

    public boolean venderProduto(int id, String status) {
        try {
            conn = new conectaDAO().connectDB();
            String sql = "UPDATE produtos SET status = ? WHERE id = ?";
            prep = conn.prepareStatement(sql);
            prep.setString(1, status);
            prep.setInt(2, id);
            prep.executeUpdate();
            JOptionPane.showMessageDialog(null, "Produto vendido com sucesso!");
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao vender produto: " + e.getMessage());
            return false;
        } finally {
            try {
                if (conn != null)
                    conn.close();
                if (prep != null)
                    prep.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }

    public ArrayList<ProdutosDTO> listarProdutosVendidos() {
        try {
            conn = new conectaDAO().connectDB();
            String sql = "SELECT * FROM produtos WHERE status = 'Vendido'";
            prep = conn.prepareStatement(sql);
            resultset = prep.executeQuery();

            while (resultset.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                produto.setStatus(resultset.getString("status"));
                listagem.add(produto);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar produtos vendidos: " + e.getMessage());
        } finally {
            try {
                if (conn != null)
                    conn.close();
                if (prep != null)
                    prep.close();
                if (resultset != null)
                    resultset.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar conexão: " + e.getMessage());
            }
        }
        return listagem;
    } 
}
