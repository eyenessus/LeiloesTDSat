import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import javax.swing.JTable;
import javax.swing.JFrame;
public class vendasVIEW  extends JFrame
{
    private JTable listaProdutos; // Declare listaProdutos
    
    public vendasVIEW() {
        listarProdutos();
        initComponents(); // Initialize components after listing products
    }
    private void initComponents() {
        listaProdutos = new JTable(new DefaultTableModel(
            new Object[][]{}, 
            new String[]{"ID", "Nome", "Valor", "Status"}
        ));
        add(new javax.swing.JScrollPane(listaProdutos)); // Add JTable to a JScrollPane
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        pack();
    }

    
    
    private void listarProdutos() {
        ProdutosDAO produtosdao = new ProdutosDAO();
        ArrayList<ProdutosDTO> produtos = produtosdao.listarProdutosVendidos();
        DefaultTableModel model = (DefaultTableModel) listaProdutos.getModel();
        model.setRowCount(0); // Limpa a tabela antes de adicionar novos dados
        for (ProdutosDTO produto : produtos) {
            model.addRow(new Object[]{
                produto.getId(),
                produto.getNome(),
                produto.getValor(),
                produto.getStatus()
            });
        }
    }
    
}
